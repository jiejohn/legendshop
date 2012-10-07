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
 * 
 * The Class Message.
 * 发件箱和收件箱
 */
public class Message implements BaseEntity {

  /** The msg id. */
  private String msgId;

  /** The content. */
  private String content;

  /** The del by sender. */
  private Integer delBySender;

  /** The del by receiver. */
  private Integer delByReceiver;

  /** The isread. */
  private Integer isread;

  /** The isdraft. */
  private Integer isdraft;

  /** The title. */
  private String title;

  /** The sender. */
  private String sender;

  /** The receiver. */
  private String receiver;

  /** The create date. */
  private Date createDate;

  /** The modify date. */
  private Date modifyDate;


  /**
   * Instantiates a new message.
   */
  public Message() {
  }

  /**
   * Gets the msg id.
   *
   * @return the msg id
   */
  public String getMsgId() {
    return msgId;
  }
  
  /**
   * Sets the msg id.
   *
   * @param msgId the new msg id
   */
  public void setMsgId(String msgId){
    this.msgId = msgId;
  }

  /**
   * Gets the content.
   *
   * @return the content
   */
  public String getContent() {
    return content;
  }
  
  /**
   * Sets the content.
   *
   * @param content the new content
   */
  public void setContent(String content){
    this.content = content;
  }

  /**
   * Gets the del by sender.
   *
   * @return the del by sender
   */
  public Integer getDelBySender() {
    return delBySender;
  }
  
  /**
   * Sets the del by sender.
   *
   * @param delBySender the new del by sender
   */
  public void setDelBySender(Integer delBySender){
    this.delBySender = delBySender;
  }

  /**
   * Gets the del by receiver.
   *
   * @return the del by receiver
   */
  public Integer getDelByReceiver() {
    return delByReceiver;
  }
  
  /**
   * Sets the del by receiver.
   *
   * @param delByReceiver the new del by receiver
   */
  public void setDelByReceiver(Integer delByReceiver){
    this.delByReceiver = delByReceiver;
  }

  /**
   * Gets the isread.
   *
   * @return the isread
   */
  public Integer getIsread() {
    return isread;
  }
  
  /**
   * Sets the isread.
   *
   * @param isread the new isread
   */
  public void setIsread(Integer isread){
    this.isread = isread;
  }

  /**
   * Gets the isdraft.
   *
   * @return the isdraft
   */
  public Integer getIsdraft() {
    return isdraft;
  }
  
  /**
   * Sets the isdraft.
   *
   * @param isdraft the new isdraft
   */
  public void setIsdraft(Integer isdraft){
    this.isdraft = isdraft;
  }

  /**
   * Gets the title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * Sets the title.
   *
   * @param title the new title
   */
  public void setTitle(String title){
    this.title = title;
  }

  /**
   * Gets the sender.
   *
   * @return the sender
   */
  public String getSender() {
    return sender;
  }
  
  /**
   * Sets the sender.
   *
   * @param sender the new sender
   */
  public void setSender(String sender){
    this.sender = sender;
  }

  /**
   * Gets the receiver.
   *
   * @return the receiver
   */
  public String getReceiver() {
    return receiver;
  }
  
  /**
   * Sets the receiver.
   *
   * @param receiver the new receiver
   */
  public void setReceiver(String receiver){
    this.receiver = receiver;
  }

  /**
   * Gets the creates the date.
   *
   * @return the creates the date
   */
  public Date getCreateDate() {
    return createDate;
  }
  
  /**
   * Sets the creates the date.
   *
   * @param createDate the new creates the date
   */
  public void setCreateDate(Date createDate){
    this.createDate = createDate;
  }

  /**
   * Gets the modify date.
   *
   * @return the modify date
   */
  public Date getModifyDate() {
    return modifyDate;
  }
  
  /**
   * Sets the modify date.
   *
   * @param modifyDate the new modify date
   */
  public void setModifyDate(Date modifyDate){
    this.modifyDate = modifyDate;
  }

/* (non-Javadoc)
 * @see com.legendshop.model.entity.BaseEntity#getId()
 */
public Serializable getId() {
	return msgId;
}

}
