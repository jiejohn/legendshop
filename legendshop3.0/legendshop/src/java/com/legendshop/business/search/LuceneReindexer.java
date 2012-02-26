/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.search;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.dao.LuceneDao;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.search.LuceneIndexer;
import com.legendshop.search.LuceneReindexArgs;
import com.legendshop.search.LuceneSearch;
import com.legendshop.search.SearchEntity;
import com.legendshop.search.SearchFacade;
import com.legendshop.search.exception.SearchException;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 * 
 * ----------------------------------------------------------------------------
 */
public class LuceneReindexer {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(LuceneReindexer.class);

	/** The search facade. */
	private SearchFacade searchFacade;
	
	/** The lucene dao. */
	private LuceneDao luceneDao;

	/**
	 * Start background process.
	 * 
	 * @param args
	 *            the args
	 * @param recreate
	 *            the recreate
	 */
	public void startBackgroundProcess(final LuceneReindexArgs args, final boolean recreate) {
		Runnable indexingJob = new Runnable() {
			@Override
			public void run() {
				reindex(args, recreate);
			}
		};

		PropertiesUtil.setObject(ParameterEnum.LUCENE_CURRENTLY_INDEXING, "1");

		Thread thread = new Thread(indexingJob);
		thread.start();
	}

	/**
	 * Reindex.
	 * 
	 * @param args
	 *            the args
	 * @param recreate
	 *            the recreate
	 */
	private void reindex(final LuceneReindexArgs args, final boolean recreate) {
		try {
			if (recreate) {
				searchFacade.getLuceneSettings().createIndexDirectory(recreate);
			}
		} catch (IOException e) {
			throw new SearchException(e);
		}

		IndexSearcher searcher = null;
		LuceneSearch luceneSearch = ((LuceneManager) searchFacade.getSearchManager()).luceneSearch();
		LuceneIndexer luceneIndexer = ((LuceneManager) searchFacade.getSearchManager()).luceneIndexer();

		long fetchCount = PropertiesUtil.getObject(ParameterEnum.LUCENE_INDEXER_DB_FETCH_COUNT, Long.class);

		try {
			if (!recreate) {
				searcher = new IndexSearcher(searchFacade.getLuceneSettings().directory());
			}

			boolean hasMorePosts = true;
			long processStart = System.currentTimeMillis();

			long firstPostId = args.filterByMessage() ? args.getFirstPostId() : luceneDao.getFirstPostIdByDate(args
					.getEntityType(), args.getFromDate());

			long lastPostId = args.filterByMessage() ? args.getLastPostId() : luceneDao.getLastPostIdByDate(args
					.getEntityType(), args.getToDate());

			int counter = 1;
			int indexTotal = 0;
			long indexRangeStart = System.currentTimeMillis();

			while (hasMorePosts) {
				long toPostId = firstPostId + fetchCount < lastPostId ? firstPostId + fetchCount : lastPostId;

				try {
					List list = luceneDao.getPostsToIndex(args.getEntityType(), firstPostId, toPostId);

					if (counter >= 5000) {
						long end = System.currentTimeMillis();
						log.debug("Indexed ~5000 documents in " + (end - indexRangeStart) + " ms (" + indexTotal
								+ " so far)");
						indexRangeStart = end;
						counter = 0;
					}

					for (Iterator iter = list.iterator(); iter.hasNext();) {
						if ("0".equals(PropertiesUtil.getObject(ParameterEnum.LUCENE_CURRENTLY_INDEXING, String.class))) {
							hasMorePosts = false;
							break;
						}
						Object obj = iter.next();
						SearchEntity searchEntity = CommonServiceUtil.createSearchEntity(obj);

						if (!recreate && args.avoidDuplicatedRecords()) {
							if (luceneSearch.findDocumentById(args.getEntityType(), searchEntity.getProdId()) != null) {
								continue;
							}
						}

						luceneIndexer.batchCreate(searchEntity);

						counter++;
						indexTotal++;
					}

					firstPostId += fetchCount;
					hasMorePosts = hasMorePosts && list.size() > 0;
				} catch (Exception e) {
					log.error("", e);
				}
			}

			long end = System.currentTimeMillis();

			log.debug("**** Total: " + (end - processStart) + " ms");
		} catch (IOException e) {
			throw new SearchException(e);
		} finally {
			PropertiesUtil.setObject(ParameterEnum.LUCENE_CURRENTLY_INDEXING, "0");
			luceneIndexer.flushRAMDirectory();
			if (searcher != null) {
				try {
					searcher.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * List.
	 */
	public void list() {
		IndexReader reader = null;
		try {
			reader = IndexReader.open(searchFacade.getLuceneSettings().directory(), false);
		} catch (IOException e) {
			log.error("", e);
			throw new SearchException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Sets the search facade.
	 * 
	 * @param searchFacade
	 *            the new search facade
	 */
	public void setSearchFacade(SearchFacade searchFacade) {
		this.searchFacade = searchFacade;
	}

	/**
	 * Sets the lucene dao.
	 * 
	 * @param luceneDao
	 *            the new lucene dao
	 */
	public void setLuceneDao(LuceneDao LuceneDao) {
		this.luceneDao = LuceneDao;
	}

}
