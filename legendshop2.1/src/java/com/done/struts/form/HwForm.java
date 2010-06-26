package com.done.struts.form;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

/**
 * @author hewq 2009-08-11
 */
public class HwForm extends ActionForm {
    public static final String ERROR_PROPERTY_MAX_LENGTH_EXCEEDED = "org.apache.struts.webapp.upload.MaxLengthExceeded";
    private String curPageNO = "1";
    private Integer hwId;

    private Integer sortId;

    private Integer nsortId;

    private Integer subNsortId;

    private String hwName;

    private Long hwPrice;

    private Long hwCash;

    private String hwBrief;

    private String hwContent;

    private Integer hwViews;

    private Integer hwBuys;

    private Date hwDate;

    protected FormFile hwPic;

    private String hwPicName;

    private String commend;

    private Integer status;

    private Date modifyDate;

    private String userId;

    private String userName;

    /**
     * Method validate
     * 
     * @param mapping
     * @param request
     * @return ActionErrors
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = null;
        // has the maximum length been exceeded?
        Boolean maxLengthExceeded = (Boolean) request
                .getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
        if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue())) {
            errors = new ActionErrors();
            errors.add(ERROR_PROPERTY_MAX_LENGTH_EXCEEDED, new ActionMessage("maxLengthExceeded"));
        }
        return errors;
    }

    /**
     * Method reset
     * 
     * @param mapping
     * @param request
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        // TODO Auto-generated method stub
    }

    public String getCommend() {
        return commend;
    }

    public void setCommend(String commend) {
        this.commend = commend;
    }

    public String getHwBrief() {
        return hwBrief;
    }

    public void setHwBrief(String hwBrief) {
        this.hwBrief = hwBrief;
    }

    public Integer getHwBuys() {
        return hwBuys;
    }

    public void setHwBuys(Integer hwBuys) {
        this.hwBuys = hwBuys;
    }

    public Long getHwCash() {
        return hwCash;
    }

    public void setHwCash(Long hwCash) {
        this.hwCash = hwCash;
    }

    public String getHwContent() {
        return hwContent;
    }

    public void setHwContent(String hwContent) {
        this.hwContent = hwContent;
    }

    public Date getHwDate() {
        return hwDate;
    }

    public void setHwDate(Date hwDate) {
        this.hwDate = hwDate;
    }

    public Integer getHwId() {
        return hwId;
    }

    public void setHwId(Integer hwId) {
        this.hwId = hwId;
    }

    public String getHwName() {
        return hwName;
    }

    public void setHwName(String hwName) {
        this.hwName = hwName;
    }

    public FormFile getHwPic() {
        return hwPic;
    }

    public void setHwPic(FormFile hwPic) {
        this.hwPic = hwPic;
    }

    public String getHwPicName() {
        return hwPicName;
    }

    public void setHwPicName(String hwPicName) {
        this.hwPicName = hwPicName;
    }

    public Long getHwPrice() {
        return hwPrice;
    }

    public void setHwPrice(Long hwPrice) {
        this.hwPrice = hwPrice;
    }

    public Integer getHwViews() {
        return hwViews;
    }

    public void setHwViews(Integer hwViews) {
        this.hwViews = hwViews;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getNsortId() {
        return nsortId;
    }

    public void setNsortId(Integer nsortId) {
        this.nsortId = nsortId;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCurPageNO() {
        return curPageNO;
    }

    public void setCurPageNO(String curPageNO) {
        this.curPageNO = curPageNO;
    }

    public Integer getSubNsortId() {
        return subNsortId;
    }

    public void setSubNsortId(Integer subNsortId) {
        this.subNsortId = subNsortId;
    }

}
