/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */

public class News implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5866824730655175491L;

	/** The news id. */
	private Long newsId;

    /** The news category id. */
    private Long newsCategoryId;
    //新闻栏目
    /** The news category. */
    private NewsCategory newsCategory;
    
    /** The news category name. */
    private String newsCategoryName;
    //分类
    /** The sort. */
    private Sort sort;
    //关联查询出来
    /** The sort id. */
    private Long sortId;
    
    /** The sort name. */
    private String sortName;
    
    /** The news title. */
    private String newsTitle;

    /** The news content. */
    private String newsContent;

    /** The news date. */
    private Date newsDate;
    
    /** 1:上线 0：下线. */
    private Integer status;
    
    /** 位置 *. */
    private Integer position;
    
    //是否高亮
    /** The high line. */
    private Integer highLine;

    /** The user id. */
    private String userId;

    /** The user name. */
    private String userName;
    
    //新闻提要
    /** The news brief. */
    private String newsBrief;

    // Constructors


	/**
	 * Instantiates a new news.
	 * 
	 * @param newsId
	 *            the news id
	 * @param newsCategoryId
	 *            the news category id
	 * @param newsCategoryName
	 *            the news category name
	 * @param sortId
	 *            the sort id
	 * @param sortName
	 *            the sort name
	 * @param newsTitle
	 *            the news title
	 * @param newsContent
	 *            the news content
	 * @param newsDate
	 *            the news date
	 * @param status
	 *            the status
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name
	 * @param highLine
	 *            the high line
	 */
    public News(Long newsId, Long newsCategoryId,
			String newsCategoryName, Long sortId, String sortName,
			String newsTitle, String newsContent, Date newsDate,
			Integer status, Integer position, String userId, String userName,Integer highLine) {
		super();
		this.newsId = newsId;
		this.newsCategoryId = newsCategoryId;
		this.newsCategoryName = newsCategoryName;
		this.sortId = sortId;
		this.sortName = sortName;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsDate = newsDate;
		this.status = status;
		this.position = position;
		this.userId = userId;
		this.userName = userName;
		this.highLine = highLine;
	}

	/**
	 * default constructor.
	 */
    public News() {
    }

    /**
	 * minimal constructor.
	 * 
	 * @param newsId
	 *            the news id
	 */
    public News(Long newsId) {
        this.newsId = newsId;
    }

    /**
	 * Gets the news id.
	 * 
	 * @return the news id
	 */
    public Long getNewsId() {
        return this.newsId;
    }

    /**
	 * Sets the news id.
	 * 
	 * @param newsId
	 *            the new news id
	 */
    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }
    
    /**
	 * Gets the news category id.
	 * 
	 * @return the news category id
	 */
    public Long getNewsCategoryId() {
        return newsCategoryId;
      }
      
      /**
		 * Sets the news category id.
		 * 
		 * @param newsCategoryId
		 *            the new news category id
		 */
      public void setNewsCategoryId(Long newsCategoryId){
        this.newsCategoryId = newsCategoryId;
      }

    /**
	 * Gets the news title.
	 * 
	 * @return the news title
	 */
    public String getNewsTitle() {
        return this.newsTitle;
    }

    /**
	 * Sets the news title.
	 * 
	 * @param newsTitle
	 *            the new news title
	 */
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    /**
	 * Gets the news content.
	 * 
	 * @return the news content
	 */
    public String getNewsContent() {
        return this.newsContent;
    }

    /**
	 * Sets the news content.
	 * 
	 * @param newsContent
	 *            the new news content
	 */
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    /**
	 * Gets the news date.
	 * 
	 * @return the news date
	 */
    public Date getNewsDate() {
        return this.newsDate;
    }

    /**
	 * Sets the news date.
	 * 
	 * @param newsDate
	 *            the new news date
	 */
    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    /**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
    public String getUserId() {
        return this.userId;
    }

    /**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
    public String getUserName() {
        return this.userName;
    }

    /**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
	 * Gets the status.
	 * 
	 * @return the status
	 */
    public Integer getStatus() {
        return status;
    }

    /**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
    public void setStatus(Integer status) {
        this.status = status;
    }


	/**
	 * Gets the sort id.
	 * 
	 * @return the sort id
	 */
	public Long getSortId() {
		return sortId;
	}

	/**
	 * Sets the sort id.
	 * 
	 * @param sortId
	 *            the new sort id
	 */
	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	/**
	 * Gets the sort name.
	 * 
	 * @return the sort name
	 */
	public String getSortName() {
		return sortName;
	}

	/**
	 * Sets the sort name.
	 * 
	 * @param sortName
	 *            the new sort name
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	/**
	 * Gets the sort.
	 * 
	 * @return the sort
	 */
	public Sort getSort() {
		return sort;
	}

	/**
	 * Sets the sort.
	 * 
	 * @param sort
	 *            the new sort
	 */
	public void setSort(Sort sort) {
		this.sort = sort;
	}

	/**
	 * Gets the news category.
	 * 
	 * @return the news category
	 */
	public NewsCategory getNewsCategory() {
		return newsCategory;
	}

	/**
	 * Sets the news category.
	 * 
	 * @param newsCategory
	 *            the new news category
	 */
	public void setNewsCategory(NewsCategory newsCategory) {
		this.newsCategory = newsCategory;
	}

	/**
	 * Gets the news category name.
	 * 
	 * @return the news category name
	 */
	public String getNewsCategoryName() {
		return newsCategoryName;
	}

	/**
	 * Sets the news category name.
	 * 
	 * @param newsCategoryName
	 *            the new news category name
	 */
	public void setNewsCategoryName(String newsCategoryName) {
		this.newsCategoryName = newsCategoryName;
	}

	/**
	 * Gets the high line.
	 * 
	 * @return the high line
	 */
	public Integer getHighLine() {
		return highLine;
	}

	/**
	 * Sets the high line.
	 * 
	 * @param highLine
	 *            the new high line
	 */
	public void setHighLine(Integer highLine) {
		this.highLine = highLine;
	}

	/**
	 * Gets the news brief.
	 * 
	 * @return the news brief
	 */
	public String getNewsBrief() {
		return newsBrief;
	}

	/**
	 * Sets the news brief.
	 * 
	 * @param newsBrief
	 *            the new news brief
	 */
	public void setNewsBrief(String newsBrief) {
		this.newsBrief = newsBrief;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.model.entity.BaseEntity#getId()
	 */
	public Serializable getId() {
		return newsId;
	}

	/**
	 * Gets the position.
	 * 
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 * 
	 * @param position
	 *            the new position
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

}