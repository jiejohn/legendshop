package com.done.model;
import java.util.Date;

/**
 * @author He-WenQiang. Create Time is 2007-07-13 23:38:38
 */

public class LoginHistory extends PagerForm implements java.io.Serializable {

  private String id;

  private String userName;

  private String ip;

  private Date time;
  
  private Date startTime;
  
  private Date endTime;
  
  private Integer loginTimes;


  public LoginHistory() {
  }

  public String getId() {
    return id;
  }
  public void setId(String id){
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName){
    this.userName = userName;
  }

  public String getIp() {
    return ip;
  }
  public void setIp(String ip){
    this.ip = ip;
  }

  public Date getTime() {
    return time;
  }
  public void setTime(Date time){
    this.time = time;
  }

public Date getEndTime() {
	return endTime;
}

public void setEndTime(Date endTime) {
	this.endTime = endTime;
}

public Date getStartTime() {
	return startTime;
}

public void setStartTime(Date startTime) {
	this.startTime = startTime;
}

public Integer getLoginTimes() {
	return loginTimes;
}

public void setLoginTimes(Integer loginTimes) {
	this.loginTimes = loginTimes;
}

public LoginHistory(String userName, Integer loginTimes) {
	super();
	this.userName = userName;
	this.loginTimes = loginTimes;
}

}
