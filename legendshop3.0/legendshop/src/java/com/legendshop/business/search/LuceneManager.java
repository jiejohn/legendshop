/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.legendshop.search.LuceneIndexer;
import com.legendshop.search.LuceneSearch;
import com.legendshop.search.LuceneSettings;
import com.legendshop.search.SearchArgs;
import com.legendshop.search.SearchEntity;
import com.legendshop.search.SearchManager;
import com.legendshop.search.SearchResult;
import com.legendshop.search.exception.SearchException;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class LuceneManager implements SearchManager {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(LuceneManager.class);
	
	/** The search. */
	private LuceneSearch search;
	
	/** The settings. */
	private LuceneSettings settings;
	
	/** The indexer. */
	private LuceneIndexer indexer;

	/* (non-Javadoc)
	 * @see com.legendshop.search.SearchManager#init(java.lang.String)
	 */
	public void init(String luceneIndexPath) {
		try {
			// Analyzer analyzer = new ChineseAnalyzer();
			// Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
			Analyzer analyzer = new IKAnalyzer();
			this.settings = new LuceneSettings(analyzer);

			this.settings.useFSDirectory(luceneIndexPath);

			this.removeLockFile();

			this.indexer = new LuceneIndexer(this.settings);

			this.search = new LuceneSearch(this.settings, new LuceneContentCollector(this.settings));

			this.indexer.watchNewDocuDocumentAdded(this.search);

		} catch (Exception e) {
			throw new SearchException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.search.SearchManager#getLuceneSettings()
	 */
	public LuceneSettings getLuceneSettings() {
		return this.settings;
	}

	/**
	 * Lucene search.
	 * 
	 * @return the lucene search
	 */
	public LuceneSearch luceneSearch() {
		return this.search;
	}

	/**
	 * Lucene indexer.
	 * 
	 * @return the lucene indexer
	 */
	public LuceneIndexer luceneIndexer() {
		return this.indexer;
	}

	/**
	 * Removes the lock file.
	 */
	public void removeLockFile() {
		try {
			if (IndexWriter.isLocked(this.settings.directory())) {
				IndexWriter.unlock(this.settings.directory());
			}
		} catch (Exception e) {
			throw new SearchException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.search.SearchManager#create(com.legendshop.search.SearchEntity)
	 */
	public void create(SearchEntity searchEntity) {
		this.indexer.create(searchEntity);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.search.SearchManager#update(com.legendshop.search.SearchEntity)
	 */
	public void update(SearchEntity searchEntity) {
		this.indexer.update(searchEntity);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.search.SearchManager#search(com.legendshop.search.SearchArgs)
	 */
	public SearchResult search(SearchArgs args) {
		return this.search.search(args);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.search.SearchManager#delete(com.legendshop.search.SearchEntity)
	 */
	public void delete(SearchEntity searchEntity) {
		this.indexer.delete(searchEntity);
	}
}
