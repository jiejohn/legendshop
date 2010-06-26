package com.done.model;
import java.util.Date;

/**
 * @author He-WenQiang. Create Time is 2010-06-25 22:17:19
 */

public class NewsCategory implements java.io.Serializable {

  private Integer newsCategoryId;

  private String newsCategoryName;

  private Short status;

  private Date newsCategoryDate;

  private String userId;

  private String userName;


  public NewsCategory() {
  }

  public Integer getNewsCategoryId() {
    return newsCategoryId;
  }
  public void setNewsCategoryId(Integer newsCategoryId){
    this.newsCategoryId = newsCategoryId;
  }

  public String getNewsCategoryName() {
    return newsCategoryName;
  }
  public void setNewsCategoryName(String newsCategoryName){
    this.newsCategoryName = newsCategoryName;
  }

  public Short getStatus() {
    return status;
  }
  public void setStatus(Short status){
    this.status = status;
  }

  public Date getNewsCategoryDate() {
    return newsCategoryDate;
  }
  public void setNewsCategoryDate(Date newsCategoryDate){
    this.newsCategoryDate = newsCategoryDate;
  }

  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId){
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName){
    this.userName = userName;
  }

}
