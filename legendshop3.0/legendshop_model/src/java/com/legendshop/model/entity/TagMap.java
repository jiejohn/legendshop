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
 * The Class TagMap.
 */
public class TagMap implements BaseEntity{

  /** The tag map id. */
  private Long tagMapId;

  /** The tag id. */
  private Long tagId;

  /** The refer id. */
  private Long referId;

  /** The type. */
  private String type;

  /** The start time. */
  private Date startTime;

  /** The end time. */
  private Date endTime;


  /**
	 * Instantiates a new tag map.
	 */
  public TagMap() {
  }

  /**
	 * Gets the tag map id.
	 * 
	 * @return the tag map id
	 */
  public Long getTagMapId() {
    return tagMapId;
  }
  
  /**
	 * Sets the tag map id.
	 * 
	 * @param tagMapId
	 *            the new tag map id
	 */
  public void setTagMapId(Long tagMapId){
    this.tagMapId = tagMapId;
  }

  /**
	 * Gets the tag id.
	 * 
	 * @return the tag id
	 */
  public Long getTagId() {
    return tagId;
  }
  
  /**
	 * Sets the tag id.
	 * 
	 * @param tagId
	 *            the new tag id
	 */
  public void setTagId(Long tagId){
    this.tagId = tagId;
  }

  /**
	 * Gets the refer id.
	 * 
	 * @return the refer id
	 */
  public Long getReferId() {
    return referId;
  }
  
  /**
	 * Sets the refer id.
	 * 
	 * @param referId
	 *            the new refer id
	 */
  public void setReferId(Long referId){
    this.referId = referId;
  }

  /**
	 * Gets the type.
	 * 
	 * @return the type
	 */
  public String getType() {
    return type;
  }
  
  /**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
  public void setType(String type){
    this.type = type;
  }

  /**
	 * Gets the start time.
	 * 
	 * @return the start time
	 */
  public Date getStartTime() {
    return startTime;
  }
  
  /**
	 * Sets the start time.
	 * 
	 * @param startTime
	 *            the new start time
	 */
  public void setStartTime(Date startTime){
    this.startTime = startTime;
  }

  /**
	 * Gets the end time.
	 * 
	 * @return the end time
	 */
  public Date getEndTime() {
    return endTime;
  }
  
  /**
	 * Sets the end time.
	 * 
	 * @param endTime
	 *            the new end time
	 */
  public void setEndTime(Date endTime){
    this.endTime = endTime;
  }

/* (non-Javadoc)
 * @see com.legendshop.model.entity.BaseEntity#getId()
 */
public Serializable getId() {
	return tagMapId;
}

}
