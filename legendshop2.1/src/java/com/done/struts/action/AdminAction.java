package com.done.struts.action;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.done.common.Constants;
import com.done.model.Hw;
import com.done.model.HwDetail;
import com.done.model.Indexjpg;
import com.done.model.News;
import com.done.model.Nsort;
import com.done.model.ShopDetail;
import com.done.model.Sort;
import com.done.model.UserComment;
import com.done.model.UserDetail;
import com.done.service.AdminService;
import com.done.struts.BaseAction;
import com.done.struts.form.FileUploadForm;
import com.done.struts.form.HwForm;
import com.done.struts.form.IndexJpgForm;
import com.done.struts.form.NewsForm;
import com.done.util.RealPathUtil;
import com.done.util.UserManagement;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.sql.ConfigCode;
import bingosoft.jcf.util.AppUtils;
import bingosoft.jcf.util.FileProcessor;

/**
 * @author hewq 2009-0217
 */
public class AdminAction extends BaseAction {
    private static Logger log = LoggerFactory.getLogger(AdminAction.class);
    

    /**
     * 新增新闻
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addNews(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NewsForm form = (NewsForm) actionForm;
        String name = UserManagement.getUsername();
        News news = new News();
        bindEntity(form, news);
        Integer newsId = news.getNewsId();
        if(newsId!=null){//update
        	News entity = getBean(AdminService.class).getNewsById(newsId);
            news.setUserId(entity.getUserId());
            news.setUserName(entity.getUserName());
        }else{//save
            news.setUserId(UserManagement.getUserId());
            news.setUserName(name);
        }
        news.setNewsDate(new Date());

        getBean(AdminService.class).saveNews(news);
        request.setAttribute(CALL_BACK, "admin/news/query.c");
        return mapping.findForward("success");
    }

    /**
     * 更新新闻
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateNews(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String newsId = request.getParameter("newsId");
        try {
            Integer id = Integer.valueOf(newsId);
            News news = getBean(AdminService.class).getNewsById(id);
            String name = UserManagement.getUsername();
            if (!UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA) && !name.equals(news.getUserName())) {
                throw new RuntimeException(name + " Can't find goods does not own you!");
            }
            request.setAttribute("news", news);
            return mapping.findForward("success");
        } catch (Exception e) {
            log.error("ERROR:", e);
            return mapping.findForward("error");
        }

    }

    public ActionForward sortList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String curPageNO = request.getParameter("curPageNO");

        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String userName = request.getParameter("userName") == null ? "" : request.getParameter("userName");
        String myaction = appendParamToURI("sortList.do", "search", search);
        myaction = appendParamToURI(myaction, "userName", userName);
        removeRequestAttribute(mapping, request);
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(Sort.class, curPageNO, myaction);
            cq.setPageSize(12);
            if (!AppUtils.isBlank(search)) {
                cq.like("sortName", "%" + search + "%");
            }
            if (UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA)) {
                if (!AppUtils.isBlank(userName)) {
                    cq.eq("userName", StringUtils.trim(userName));
                }
            } else {
                cq.eq("userName", UserManagement.getUsername());
            }
            cq.addOrder("asc", "seq");
            cq.add();
            PageSupport ps = getBean(AdminService.class).getDataByCQ(cq);
            request.setAttribute("search", search);
            request.setAttribute("userName", userName);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("list", ps.getResultList());
            //if(ps.hasMutilPage())
            request.setAttribute("toolBar", ps.getToolBar());
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * indexjpg
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward indexjpgList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String curPageNO = request.getParameter("curPageNO");
        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String myaction = appendParamToURI("indexjpgList.do", "search", search);
        removeRequestAttribute(mapping, request);
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(Indexjpg.class, curPageNO, myaction);
            cq.setPageSize(Constants.PAGE_SIZE);
            if (UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA)) {
                if (!AppUtils.isBlank(search)) {
                    cq.like("userName", "%" + search + "%");
                }
            } else {
                cq.eq("userName", UserManagement.getUsername());
            }
            cq.add();
            PageSupport ps = getBean(AdminService.class).getDataByCQ(cq);
            request.setAttribute("search", search);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("list", ps.getResultList());
            if(ps.hasMutilPage())
            request.setAttribute("toolBar", ps.getToolBar());
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * 用户Detail管理
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward userDetailList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String curPageNO = request.getParameter("curPageNO");
        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String myaction = appendParamToURI("userDetailList.do", "search", search);
        removeRequestAttribute(mapping, request);
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(UserDetail.class, curPageNO, myaction);
            cq.setPageSize(Constants.PAGE_SIZE);
            if (!AppUtils.isBlank(search)) {
                cq.like("userName", "%" + search + "%");
                cq.add();
            }

            PageSupport ps = getBean(AdminService.class).getDataByCQ(cq);
            request.setAttribute("search", search);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("list", ps.getResultList());
            //if(ps.hasMutilPage())
            request.setAttribute("toolBar", ps.getToolBar());
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward shopDetailList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String curPageNO = request.getParameter("curPageNO");

        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String myaction = appendParamToURI("shopDetailList.do", "search", search);

        removeRequestAttribute(mapping, request);
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(ShopDetail.class, curPageNO, myaction);
            cq.setPageSize(Constants.PAGE_SIZE);
            if (UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA)) {
                if (!AppUtils.isBlank(search)) {
                    cq.like("storeName", "%" + search + "%");
                }
            } else {
                String name = UserManagement.getUsername();
                if (name == null) {
                    throw new RuntimeException("you are not logon yet!");
                }
                cq.eq("storeName", name);
            }
            cq.add();
            PageSupport ps = getBean(AdminService.class).getDataByCQ(cq);
            request.setAttribute("search", search);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("list", ps.getResultList());
            //if(ps.hasMutilPage())
            request.setAttribute("toolBar", ps.getToolBar());
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * 子分类型列表
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward nsortList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String curPageNO = request.getParameter("curPageNO");
        String sortId = request.getParameter("sortId");
        if (sortId == null) {
            Integer isortId = (Integer) request.getAttribute("sortId");
            if (isortId != null) {
                sortId = String.valueOf(isortId);
            }
        }

        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String myaction = appendParamToURI("nsortList.do", "search", search);

        removeRequestAttribute(mapping, request);
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(Nsort.class, curPageNO, myaction);
            cq.setPageSize(Constants.PAGE_SIZE);
            cq.eq("sortId", Integer.valueOf(sortId));
            if (!AppUtils.isBlank(search)) {
                cq.like("nsortName", "%" + search + "%");
            }
            cq.add();
            PageSupport ps = getBean(AdminService.class).getDataByCQ(cq);
            request.setAttribute("search", search);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("list", ps.getResultList());

            //if(ps.hasMutilPage())
            request.setAttribute("toolBar", ps.getToolBar());

            Sort sort = getBean(AdminService.class).getSort(Integer.valueOf(sortId));
            request.setAttribute("sortId", sort.getSortId());
            request.setAttribute("sortName", sort.getSortName());
        } catch (Exception e) {
            log.error("Exception happened : ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteSort(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String sortId = request.getParameter("sortId");
        Integer isortId;
        try {
            isortId = Integer.valueOf(sortId);
            Sort sort = getBean(AdminService.class).getSort(isortId);
            String name = UserManagement.getUsername();
            if (!UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA) && !name.equals(sort.getUserName())) {
                throw new RuntimeException(name + " Can't find Sort does not own to you!");
            }
            getBean(AdminService.class).deleteSort(isortId);
            FileProcessor.deleteFile(getRealPath(request) + sort.getPicture());
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    public ActionForward deleteindexJpg(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Integer iId;
        try {
            iId = Integer.valueOf(id);
            Indexjpg index = getBean(AdminService.class).getIndexJpg(iId);
            String name = UserManagement.getUsername();
            if (!UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA) && !name.equals(index.getUserName())) {
                throw new RuntimeException(name + " Can't find goods does not own you!");
            }
            getBean(AdminService.class).deleteIndexJpg(iId);
            FileProcessor.deleteFile(getRealPath(request) + index.getImg());
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", e.getMessage());
        }
        return mapping.findForward("success");
    }

    public ActionForward deleteNSort(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String nsortId = request.getParameter("nsortId");
        Integer insortId;
        try {
            insortId = Integer.valueOf(nsortId);
            Nsort nsort = getBean(AdminService.class).getNsort(insortId);
            request.setAttribute("sortId", nsort.getSortId());
            getBean(AdminService.class).deleteNsort(nsort);
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * 保存Sort
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveNSort_bak(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String sortId = request.getParameter("sortId");
        Integer isortId;
        try {
            isortId = Integer.valueOf(sortId);
            getBean(AdminService.class).deleteSort(isortId);
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * 上传文件
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward upload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            FileUploadForm uploadform = (FileUploadForm) form;
            String subPath = UserManagement.getUsername() + "/sort/";
            String filename = null;
            //保存数据库记录
            String orginPicture = null;
            Sort sort = null;
            FormFile formFile = uploadform.getPicture();//取得上传的文件
            if (!AppUtils.isBlank(uploadform.getSortId())) {
                //before update file delete it
                sort = getBean(AdminService.class).getSort(uploadform.getSortId());
                if ((formFile != null) && (formFile.getFileSize() > 0)) {
                    orginPicture = getRealPath(request) + sort.getPicture();
                }

            } else {
                sort = new Sort();
            }

            String encoding = request.getCharacterEncoding();
            log.debug("encoding = " + encoding);
            if ((encoding != null) && (encoding.equalsIgnoreCase("UTF-8"))) {
                response.setContentType("text/html; charset=UTF-8");//如果没有指定编码，编码格式为gb2312
            }
            Date date = new Date();
            if ((formFile != null) && (formFile.getFileSize() != 0)) {
                String validateResult = validateFile(formFile);
                if (validateResult == null) {
                    filename = FileProcessor.uploadFile(formFile, getRealPath(request) + subPath, true, false);
                } else {
                    return handleException(mapping, request, "", validateResult);
                }
            }
            if ((formFile != null) && (formFile.getFileSize() > 0)) {
                sort.setPicture(subPath + filename);
            } else {
                sort.setPicture(uploadform.getPictureName());
            }

            sort.setSortId(uploadform.getSortId());
            sort.setSeq(uploadform.getSeq());
            sort.setSortName(uploadform.getSortName());
            //只有第一次插入记录的时候才会更新所有者
            if (AppUtils.isBlank(uploadform.getSortId())) {
                sort.setUserId(UserManagement.getUserId());
                sort.setUserName(UserManagement.getUsername());
            }
            getBean(AdminService.class).saveSort(sort);
            if (orginPicture != null) {
                FileProcessor.deleteFile(orginPicture);
            }
            request.setAttribute(CALL_BACK, "admin/sortList.do");
            return mapping.findForward("success");
        } catch (Exception e) {
            return handleException(mapping, request, "", getExceptionMessage(e));
        }

    }

    public ActionForward updateSort(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String sortId = request.getParameter("sortId");
        Integer isortId;
        try {
            isortId = Integer.valueOf(sortId);
            Sort sort = getBean(AdminService.class).getSort(isortId);
            String name = UserManagement.getUsername();
            if (!UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA) && !name.equals(sort.getUserName())) {
                throw new RuntimeException(name + " Can't find goods does not own you!");
            }
            request.setAttribute("sort", sort);
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * 更新首页JPG
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward updateIndexJpg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String id = request.getParameter("id");
        Integer iId;
        try {
            iId = Integer.valueOf(id);
            Indexjpg index = getBean(AdminService.class).getIndexJpg(iId);
            String name = UserManagement.getUsername();
            if (!UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA) && !name.equals(index.getUserName())) {
                throw new RuntimeException(name + " Can't find goods does not own you!");
            }
            request.setAttribute("index", index);
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * 保存首页图片
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveIndexJpg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        if (form instanceof IndexJpgForm) {
            String name = UserManagement.getUsername();
            Long num = getBean(AdminService.class).getIndexJpgNum(name);
            Long maxNum = Long.parseLong(ConfigCode.getInstance().getCode("config.MaxIndexJpg"));
            if ((num != null) && (num > maxNum)) {
                return handleException(mapping, request, ActionMessages.GLOBAL_MESSAGE, "error.File.Exception");
            }
            IndexJpgForm uploadform = (IndexJpgForm) form;
            String subPath = UserManagement.getUsername() + "/indexjpg/";
            String filename = null;
            String encoding = request.getCharacterEncoding();
            log.debug("encoding = " + encoding);
            if ((encoding != null) && (encoding.equalsIgnoreCase("UTF-8"))) {
                response.setContentType("text/html; charset=UTF-8");//如果没有指定编码，编码格式为gb2312
            }
            FormFile formFile = uploadform.getImg();//取得上传的文件
            Date date = new Date();
            if ((formFile != null) && (formFile.getFileSize() > 0)) {
                String validateResult = validateFile(formFile);
                if (validateResult == null) {
                    filename = FileProcessor.uploadFile(formFile, getRealPath(request) + subPath, true, false);
                } else {
                    return handleException(mapping, request, "上传文件格式错误", validateResult);
                }

            }

            //保存数据库记录
            Indexjpg indexJpg = null;
            String orginImg = null;
            if (!AppUtils.isBlank(uploadform.getId())) {
                //before update file delete it
                indexJpg = getBean(AdminService.class).getIndexJpg(uploadform.getId());
                if ((formFile != null) && (formFile.getFileSize() > 0)) {
                    orginImg = getRealPath(request) + indexJpg.getImg();
                }
            } else {
                indexJpg = new Indexjpg();
            }

            if ((formFile != null) && (formFile.getFileSize() > 0)) {
                indexJpg.setImg(subPath + filename);
            } else {
                indexJpg.setImg(uploadform.getImgName());
            }

            indexJpg.setAlt(uploadform.getAlt());
            indexJpg.setHref(uploadform.getHref());
            indexJpg.setId(uploadform.getId());
            indexJpg.setLink(uploadform.getLink());
            indexJpg.setStitle(uploadform.getStitle());
            indexJpg.setTitle(uploadform.getTitle());
            indexJpg.setTitleLink(uploadform.getTitleLink());
            indexJpg.setUserId(UserManagement.getUserId());
            indexJpg.setUserName(name);
            getBean(AdminService.class).saveOrUpdate(indexJpg);
            if (orginImg != null) {
                FileProcessor.deleteFile(orginImg);
            }
            request.setAttribute(CALL_BACK, "admin/indexjpgList.do");
            return mapping.findForward("success");
        }
        return handleException(mapping, request, ActionMessages.GLOBAL_MESSAGE, "error.File.Exception");
    }

    /**
     * 用户评论列表
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward userCommentList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String curPageNO = request.getParameter("curPageNO");

        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String status = request.getParameter("status") == null ? "0" : request.getParameter("status");//默认显示为未读
        String userName = request.getParameter("userName") == null ? "" : request.getParameter("userName");
        String myaction = appendParamToURI("userCommentList.do", "search", search);
        myaction = appendParamToURI(myaction, "status", status);
        myaction = appendParamToURI(myaction, "userName", userName);
        removeRequestAttribute(mapping, request);
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(UserComment.class, curPageNO, myaction);
            cq.setPageSize(Constants.PAGE_SIZE);
            if (!AppUtils.isBlank(status)) {
                cq.eq("status", Integer.valueOf(status));

            }
            if (UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA)) {
                if (!AppUtils.isBlank(search)) {
                    cq.like("toUserName", "%" + search + "%");
                }
            } else {
                String name = UserManagement.getUsername();
                if (name == null) {
                    throw new RuntimeException("you are not logon yet!");
                }
                cq.eq("toUserName", name);
            }
            if (!AppUtils.isBlank(userName)) {
            	cq.like("userName", "%"+userName+"%");
            }
            cq.addOrder("desc", "addtime");
            cq.add();
            PageSupport ps = getBean(AdminService.class).getDataByCQ(cq);
            request.setAttribute("search", search);
            request.setAttribute("status", status);
            request.setAttribute("userName", userName);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("list", ps.getResultList());
            if(ps.hasMutilPage())
            request.setAttribute("toolBar", ps.getToolBar());
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * 删除用户评论
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteUserComment(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Integer iId = null;
        try {
            iId = Integer.valueOf(id);
            getBean(AdminService.class).deleteById(UserComment.class, iId);
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * 更新阅读状态
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewUserComment(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Integer iId = null;
        try {
            iId = Integer.valueOf(id);
            UserComment comment = getBean(AdminService.class).get(UserComment.class, iId);
            if (!UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA)) {
            	if(!comment.getStatus().equals(Constants.COMMENT_READED)){
                    comment.setStatus(Constants.COMMENT_READED);
                    getBean(AdminService.class).saveOrUpdate(comment);
            	}
            }
            request.setAttribute("comment", comment);
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    //产品列表,备用
    /**
     * @deprecated
     */
    @Deprecated
    public ActionForward hwList_bak(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String curPageNO = request.getParameter("curPageNO");
        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String myaction = appendParamToURI("hwList.do", "search", search);
        removeRequestAttribute(mapping, request);
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(HwDetail.class, curPageNO, myaction);
            cq.setPageSize(Constants.PAGE_SIZE);
            if (!AppUtils.isBlank(search)) {
                cq.like("hwName", "%" + search + "%");
            }
            cq.addOrder("desc", "hwDate");
            cq.add();
            PageSupport ps = getBean(AdminService.class).getDataByCQ(cq);
            request.setAttribute("search", search);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("list", ps.getResultList());
            //if(ps.hasMutilPage())
            request.setAttribute("toolBar", ps.getToolBar());
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    //	产品列表
    public ActionForward hwList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HwForm hwForm = (HwForm) actionForm;
        removeRequestAttribute(mapping, request);
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(HwDetail.class, hwForm.getCurPageNO());
            cq.setPageSize(Constants.PAGE_SIZE);
            if (!AppUtils.isBlank(hwForm.getHwName())) {
                cq.like("hwName", "%" + hwForm.getHwName() + "%");
            }

            cq.eq("sortId", hwForm.getSortId());
            cq.eq("nsortId", hwForm.getNsortId());
            cq.eq("subNsortId", hwForm.getSubNsortId());
            cq.eq("commend", hwForm.getCommend());
            cq.eq("status", hwForm.getStatus());

            if (UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA)) {
                if (!AppUtils.isBlank(hwForm.getUserName())) {
                    cq.eq("userName", StringUtils.trim(hwForm.getUserName()));
                }
            } else {
                String name = UserManagement.getUsername();
                request.setAttribute("userName", name);
                if (name == null) {
                    throw new RuntimeException("you are not logon yet!");
                }
                cq.eq("userName", name);
            }

            cq.addOrder("desc", "hwDate");
            cq.add();
            PageSupport ps = getBean(AdminService.class).getDataByCriteriaQuery(cq);

            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("list", ps.getResultList());
            request.setAttribute("hw", hwForm);
            if (ps.hasMutilPage()) {
                request.setAttribute("toolBar", ps.getToolBar((Locale) request.getSession().getAttribute(
                        Globals.LOCALE_KEY)));
            }
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    //保存产品
    public ActionForward saveHw(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        HwForm hwForm = (HwForm) form;
        String name = UserManagement.getUsername();
        String id = UserManagement.getUserId();
        Hw origin = null;
        String orginHwPic = null;
        String smallHwPic = null;
        FormFile formFile = hwForm.getHwPic();//取得上传的文件
        if (hwForm.getHwId() != null) {
            origin = getBean(AdminService.class).get(Hw.class, hwForm.getHwId());
            if (origin.getUserName() != null) {
                if (!UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA) && !name.equals(origin.getUserName())) {
                    throw new RuntimeException("Can't edit goods does not onw to you!");
                }
            }
            if ((formFile != null) && (formFile.getFileSize() > 0)) {
                orginHwPic = getRealPath(request) + origin.getHwPic();
                smallHwPic = getSmallPath(request) + origin.getHwPic();

            }

        }

        String subPath = name + "/hw/";
        String filename = null;
        String encoding = request.getCharacterEncoding();
        log.debug("encoding = " + encoding);
        if ((encoding != null) && (encoding.equalsIgnoreCase("UTF-8"))) {
            response.setContentType("text/html; charset=UTF-8");//如果没有指定编码，编码格式为gb2312
        }

        Date date = new Date();
        if ((formFile != null) && (formFile.getFileSize() > 0)) {
            String validateResult = validateFile(formFile);
            if (validateResult == null) {
                filename = FileProcessor.uploadFile(formFile, getRealPath(request) + subPath, true, false);
            } else {
                return handleException(mapping, request, "上传文件错误", validateResult);
            }
        } else {//没有选择文件则是删除文件	
            /*
             * try { log.debug(" 选择文件则是删除文件 : " + getRealPath(request) +
             * filename); int i =
             * FileProcessor.deleteFile(getRealPath(request) +
             * filename); log.debug("删除结果为 : " + i); } catch
             * (Exception e) { // TODO Auto-generated catch block
             * e.printStackTrace(); }
             */
        }

        //保存数据库记录	        
        Hw hw = new Hw();
        bindEntity(hwForm, hw);

        if ((formFile != null) && (formFile.getFileSize() != 0)) {
            hw.setHwPic(subPath + filename);
        } else {
            hw.setHwPic(hwForm.getHwPicName());
        }
        if (origin != null) {
            //update
            hw.setModifyDate(date);
            hw.setUserId(origin.getUserId());
            hw.setUserName(origin.getUserName());
            hw.setStatus(origin.getStatus());
            hw.setHwDate(origin.getHwDate());
            hw.setHwViews(origin.getHwViews());
            hw.setHwBuys(origin.getHwBuys());
        } else {
            //save
            hw.setStatus(Constants.HW_ONLINE);
            hw.setHwDate(date);
            hw.setModifyDate(date);
            hw.setHwViews(0);
            hw.setHwBuys(0);
            hw.setUserId(id);
            hw.setUserName(name);
        }

        getBean(AdminService.class).saveOrUpdate(hw);
        if (orginHwPic != null) {
            FileProcessor.deleteFile(orginHwPic);
            FileProcessor.deleteFile(smallHwPic);
        }
        return mapping.findForward("success");

    }

    public ActionForward updateHw(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String hwId = request.getParameter("hwId");
        Integer ihwId;
        try {
            ihwId = Integer.valueOf(hwId);
            Hw hw = getBean(AdminService.class).get(Hw.class, ihwId);
            String name = UserManagement.getUsername();
            if (!UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA) && !name.equals(hw.getUserName())) {
                throw new RuntimeException(name + " Can't find goods does not own you!");
            }
            request.setAttribute("hw", hw);
        } catch (Exception e) {
            log.error("ERROR happened: ", e);
            return handleException(mapping, request, "", getExceptionMessage(e));
        }
        return mapping.findForward("success");
    }

    /**
     * //取当前系统路径
     * 
     * @param request
     * @return
     */
    private String getRealPath(HttpServletRequest request) {
        return RealPathUtil.getRealPath(request.getSession().getServletContext());
    }

    /**
     * //取缩略图当前系统路径
     * 
     * @param request
     * @return
     */
    private String getSmallPath(HttpServletRequest request) {
        return RealPathUtil.getSmallPath(request.getSession().getServletContext());
    }
}
