package com.done.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

/**
 * MyEclipse Struts Creation date: 11-14-2005
 * 
 * XDoclet definition:
 * 
 * @struts.form name="fileUploadForm"
 */
public class FileUploadForm extends ActionForm {
    public static final String ERROR_PROPERTY_MAX_LENGTH_EXCEEDED = "org.apache.struts.webapp.upload.MaxLengthExceeded";
    protected FormFile picture;
    protected String pictureName;
    private String sortName;
    private Integer seq;
    private Integer sortId;

    public FormFile getPicture() {
        return picture;
    }

    public void setPicture(FormFile picture) {
        this.picture = picture;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

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
        //has the maximum length been exceeded?
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

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}
