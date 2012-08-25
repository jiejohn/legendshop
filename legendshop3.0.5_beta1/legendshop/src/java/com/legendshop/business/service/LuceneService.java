package com.legendshop.business.service;

import java.util.Date;
import java.util.List;

public interface LuceneService {

	/**
	 * First post id by date.
	 * 
	 * @param entityType
	 *            the entity type
	 * @param fromDate
	 *            the from date
	 * @return the long
	 */
	public abstract long getFirstPostIdByDate(int entityType, Date fromDate);

	/**
	 * Gets the posts to index.
	 * 
	 * @param entityType
	 *            the entity type
	 * @param firstPostId
	 *            the first post id
	 * @param toPostId
	 *            the to post id
	 * @return the posts to index
	 */
	public abstract List getPostsToIndex(int entityType, long firstPostId, long toPostId);

	/**
	 * Last post id by date.
	 * 
	 * @param entityType
	 *            the entity type
	 * @param toDate
	 *            the to date
	 * @return the long
	 */
	public abstract long getLastPostIdByDate(int entityType, Date toDate);

}