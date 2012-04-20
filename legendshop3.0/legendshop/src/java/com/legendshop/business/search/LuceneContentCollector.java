/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.search;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.dao.ShopDetailDao;
import com.legendshop.core.ContextServiceLocator;
import com.legendshop.core.constant.LuceneIndexerEnum;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.search.HTMLParser;
import com.legendshop.search.LuceneResultCollector;
import com.legendshop.search.LuceneSettings;
import com.legendshop.search.SearchArgs;
import com.legendshop.search.SearchEntity;
import com.legendshop.search.SearchFacade;
import com.legendshop.search.SearchFields;
import com.legendshop.search.exception.SearchException;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。 ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ---------------------------------------------------------------------------- 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class LuceneContentCollector implements LuceneResultCollector {
	
	/** The settings. */
	private final LuceneSettings settings;
	
	private final ProductDao productDao =  (ProductDao)ContextServiceLocator.getInstance().getBean("productDao");
	
	private final ShopDetailDao shopDetailDao =  (ShopDetailDao)ContextServiceLocator.getInstance().getBean("shopDetailDao");

	/**
	 * Instantiates a new lucene content collector.
	 * 
	 * @param settings
	 *            the settings
	 */
	public LuceneContentCollector(LuceneSettings settings) {
		this.settings = settings;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.search.LuceneResultCollector#collect(com.legendshop.search.SearchArgs, org.apache.lucene.search.ScoreDoc[], org.apache.lucene.search.IndexSearcher, org.apache.lucene.search.Query)
	 */
	@Override
	public List collect(SearchArgs args, ScoreDoc[] hits, IndexSearcher search, Query query) {
		try {
			Long[] postIds = new Long[Math.min(args.fetchCount(), hits.length)];
			for (int docIndex = args.startFrom(), i = 0; docIndex < args.startFrom() + args.fetchCount()
					&& docIndex < hits.length; docIndex++, i++) {
				Document doc = search.doc(hits[docIndex].doc);
				if (LuceneIndexerEnum.SEARCH_ENTITY_PROD.equals(args.getEntityType())) {
					if (doc.get(SearchFields.Keyword.PROD_ID) != null) {
						postIds[i] = Long.parseLong(doc.get(SearchFields.Keyword.PROD_ID));
					}
				} else if (LuceneIndexerEnum.SEARCH_ENTITY_SHOPDETAIL.equals(args.getEntityType())) {
					if (doc.get(SearchFields.Keyword.SHOP_ID) != null) {
						postIds[i] = Long.parseLong(doc.get(SearchFields.Keyword.SHOP_ID));
					}
				}

			}

			return this.retrieveRealEntity(args, postIds, query);
		} catch (Exception e) {
			throw new SearchException(e.toString(), e);
		}
	}

	/**
	 * Retrieve real entity.
	 * 
	 * @param args
	 *            the args
	 * @param postIds
	 *            the post ids
	 * @param query
	 *            the query
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InvalidTokenOffsetsException
	 *             the invalid token offsets exception
	 */
	private List retrieveRealEntity(SearchArgs args, Long[] postIds, Query query) throws IOException,
			InvalidTokenOffsetsException {
		List result = new ArrayList();
		if (AppUtils.isBlank(postIds)) {
			return result;
		}
		if (LuceneIndexerEnum.SEARCH_ENTITY_PROD.equals(args.getEntityType())) {
			result = retrieveProd(args, postIds, query);

		} else if (LuceneIndexerEnum.SEARCH_ENTITY_SHOPDETAIL.equals(args.getEntityType())) {
			result = retrieveShopDetail(args, postIds, query);
		} else if (LuceneIndexerEnum.SEARCH_ENTITY_NEWS.equals(args.getEntityType())) {
		}
		return result;
	}

	/**
	 * Retrieve shop detail.
	 * 
	 * @param args
	 *            the args
	 * @param postIds
	 *            the post ids
	 * @param query
	 *            the query
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InvalidTokenOffsetsException
	 *             the invalid token offsets exception
	 */
	private List retrieveShopDetail(SearchArgs args, Long[] postIds, Query query) throws IOException,
			InvalidTokenOffsetsException {
		List<Long> postIdList = new ArrayList<Long>();
		StringBuffer sb = new StringBuffer("from ShopDetailView sd where sd.status = 1 and sd.shopId in (");
		for (int i = 0; i < postIds.length - 1; i++) {
			if (postIds[i] != null) {
				sb.append("?,");
				postIdList.add(postIds[i]);
			}
		}
		if (postIdList.size() == 0) {
			return null;
		}
		sb.setLength(sb.length() - 1);
		sb.append(")");
		//List<ShopDetailView> shopDetails = getBaseDao().findByHQL(sb.toString(), postIdList.toArray());
		List<ShopDetailView> shopDetails = shopDetailDao.getShopDetail(postIds);
		for (Iterator<ShopDetailView> iter = shopDetails.iterator(); iter.hasNext();) {
			ShopDetailView shopDetail = iter.next();
			Scorer scorer = new QueryScorer(query);
			SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<span style='color:#ff0000'>", "</span>");// 设定高亮显示的格式，也就是对高亮显示的词组加上前缀后缀
			Highlighter highlighter = new Highlighter(simpleHtmlFormatter, scorer);

			String content = "";
			if (AppUtils.isNotBlank(shopDetail.getDetailDesc())) {
				content = HTMLParser.textExtractingByVisitor(shopDetail.getDetailDesc());
			}
			TokenStream tokenStream = this.settings.analyzer().tokenStream(SearchFields.Indexed.CONTENTS,
					new StringReader(content));

			String fragment = highlighter.getBestFragment(tokenStream, content);
			if (fragment != null) {
				shopDetail.setDetailDesc(fragment);
			} else {
				String c = content;
				if (c.length() > 100) {
					c = content.substring(0, 100) + "...";
				}
				shopDetail.setDetailDesc(c);
			}

			String sitename = shopDetail.getSitename();
			tokenStream = this.settings.analyzer().tokenStream(SearchFields.Indexed.CONTENTS,
					new StringReader(sitename));

			fragment = highlighter.getBestFragment(tokenStream, sitename);
			if (fragment != null) {
				shopDetail.setSitename(fragment);
			} else {
				String c = sitename;
				if (c.length() > 100) {
					c = sitename.substring(0, 100) + "...";
				}
				shopDetail.setSitename(c);
			}
		}
		if (shopDetails != null && postIds != null && shopDetails.size() < postIds.length) {
			deleteShopDetailEntity(shopDetails, postIds);
		}
		return shopDetails;
	}

	/**
	 * Retrieve prod.
	 * 
	 * @param args
	 *            the args
	 * @param postIds
	 *            the post ids
	 * @param query
	 *            the query
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InvalidTokenOffsetsException
	 *             the invalid token offsets exception
	 */
	private List<ProductDetail> retrieveProd(SearchArgs args, Long[] postIds, Query query) throws IOException,
			InvalidTokenOffsetsException {
		if (AppUtils.isBlank(postIds)) {
			return new ArrayList<ProductDetail>();
		}

		List<ProductDetail> prods = productDao.getProdDetail(postIds);
		for (Iterator<ProductDetail> iter = prods.iterator(); iter.hasNext();) {
			ProductDetail prod = iter.next();
			Scorer scorer = new QueryScorer(query);
			SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<span style='color:#ff0000'>", "</span>");// 设定高亮显示的格式，也就是对高亮显示的词组加上前缀后缀
			Highlighter highlighter = new Highlighter(simpleHtmlFormatter, scorer);

			String content = "";
			if (AppUtils.isNotBlank(prod.getContent())) {
				content = HTMLParser.textExtractingByVisitor(prod.getContent());
			}
			TokenStream tokenStream = this.settings.analyzer().tokenStream(SearchFields.Indexed.CONTENTS,
					new StringReader(content));

			String fragment = highlighter.getBestFragment(tokenStream, content);
			if (fragment != null) {
				prod.setContent(fragment);
			} else {
				String c = content;
				if (c.length() > 100) {
					c = content.substring(0, 100) + "...";
				}
				prod.setContent(c);
			}

			String prodName = prod.getName();
			tokenStream = this.settings.analyzer().tokenStream(SearchFields.Indexed.CONTENTS, new StringReader(prodName));
			fragment = highlighter.getBestFragment(tokenStream, prodName);
			if (fragment != null) {
				prod.setName(fragment);
			} else {
				String c = prodName;
				if (c.length() > 100) {
					c = prodName.substring(0, 100) + "...";
				}
				prod.setName(c);
			}

		}
		if (prods != null && postIds != null && prods.size() < postIds.length) {
			deleteProdDetailEntity(prods, postIds);
		}
		return prods;
	}

	/**
	 * Delete prod detail entity.
	 * 
	 * @param prods
	 *            the prods
	 * @param postIds
	 *            the post ids
	 */
	private void deleteProdDetailEntity(List<ProductDetail> prods, Long[] postIds) {
		for (Long id : postIds) {
			boolean found = false;
			for (ProductDetail productDetail : prods) {
				if (id != null) {
					if (id.equals(productDetail.getProdId())) {
						found = true;
						break;
					}
				}
			}
			if (id != null && !found) {
				SearchEntity searchEntity = new SearchEntity();
				searchEntity.setProdId(id);
				searchEntity.setEntityType(LuceneIndexerEnum.SEARCH_ENTITY_PROD);
				getSearchFacade().delete(searchEntity);
			}
		}
	}

	/**
	 * Delete shop detail entity.
	 * 
	 * @param shopDetails
	 *            the shop details
	 * @param postIds
	 *            the post ids
	 */
	private void deleteShopDetailEntity(List<ShopDetailView> shopDetails, Long[] postIds) {
		for (Long id : postIds) {
			boolean found = false;
			for (ShopDetailView shopDetail : shopDetails) {
				if (id.equals(shopDetail.getShopId())) {
					found = true;
					break;
				}
			}
			if (id != null && !found) {
				SearchEntity searchEntity = new SearchEntity();
				searchEntity.setShopId(id);
				searchEntity.setEntityType(LuceneIndexerEnum.SEARCH_ENTITY_SHOPDETAIL);
				getSearchFacade().delete(searchEntity);
			}
		}
	}


	/**
	 * Gets the search facade.
	 * 
	 * @return the search facade
	 */
	private SearchFacade getSearchFacade() {
		return (SearchFacade) ContextServiceLocator.getInstance().getBean("searchFacade");
	}
}
