package com.done.struts.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.done.common.Constants;
import com.done.model.Ad;
import com.done.model.Advertisement;
import com.done.model.Basket;
import com.done.model.Hotsearch;
import com.done.model.Hw;
import com.done.model.HwDetail;
import com.done.model.Indexjpg;
import com.done.model.Logo;
import com.done.model.Myleague;
import com.done.model.News;
import com.done.model.Nsort;
import com.done.model.Pub;
import com.done.model.ShopDetail;
import com.done.model.Sort;
import com.done.model.Sub;
import com.done.model.TUser;
import com.done.model.UserDetail;
import com.done.service.BusinessService;
import com.done.struts.BaseAction;
import com.done.struts.form.BasketForm;
import com.done.struts.form.MemberForm;
import com.done.struts.form.SearchForm;
import com.done.struts.form.UserForm;
import com.done.util.ConvertBeanUtil;
import com.done.util.MD5Util;
import com.done.util.SessionUserManagement;
import com.done.util.UserManagement;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.service.impl.ContextServiceLocator;
import bingosoft.jcf.util.AppUtils;

/**
 * @author hewq 2009-0217
 */
public class BizAction extends BaseAction {
    private BusinessService service = ContextServiceLocator.getInstance().getBean(BusinessService.class);
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int pageSize = Constants.PAGE_SIZE;
    private String defaultValue = "0";

    private Integer defaultInt = 0;

    public ActionForward index(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String shopName = request.getParameter(Constants.SHOP_NAME);
        ShopDetail shopDetail = null;
        if (!AppUtils.isBlank(shopName)) {
            //检查是否有该店
            shopDetail = service.getShopDetail(shopName);
            if (shopDetail == null) {
                log.warn("shopName does not exists " + shopName);
                shopName = Constants.DEFAULT_SHOP;
                shopDetail = service.getShopDetail(shopName);
            }
            setShopName(request, response, shopName);
        } else {
            shopName = getShopName(request, response);
            shopDetail = service.getShopDetail(shopName);
        }
        if (shopDetail == null) {
            log.warn("shopName does not exists " + shopName);
            shopName = Constants.DEFAULT_SHOP;
            shopDetail = service.getShopDetail(shopName);
            if (shopDetail == null) {
                request.setAttribute("error.message", "error.message");
                return mapping.findForward("error");
            }
        }
        String userName = SessionUserManagement.getUsername(request.getSession());
        log
                .info("[{}],{},{},index", new Object[] { request.getRemoteAddr(), userName == null ? "" : userName,
                        shopName });

        List<Hw> hwList = service.getCommend(shopName, 20);
        if (!AppUtils.isBlank(hwList)) {
            request.setAttribute("hwList", hwList);
        }
        //最新产品
        List<Hw> newestList = service.getNewest(shopName, 10);
        if (!AppUtils.isBlank(newestList)) {
            request.setAttribute("newestList", newestList);
        }

        List<Ad> adList = service.getAdOrderbybs(shopName);
        if (!AppUtils.isBlank(adList)) {
            request.setAttribute("adList", adList);
        }
        ////////for top.jsp
        setSessionAttribute(request, "shopDetail", shopDetail);
        Logo logo = service.getLogo(shopName);
        if (AppUtils.isBlank(logo)) {
            logo = service.getLogo(Constants.DEFAULT_SHOP);
        }
        request.setAttribute("logo", logo);
        if (logo != null) {
            setSessionAttribute(request, "LOGO_IMG", logo.getBanner());
        }

        List<Sort> sortList = service.getSort(shopName, true);
        if (!AppUtils.isBlank(sortList)) {
            request.setAttribute("sortList", sortList);
        }

        //热门搜索
        List<Hotsearch> searchList = service.getSearch(shopName);
        if (!AppUtils.isBlank(searchList)) {
            request.setAttribute("searchList", searchList);
        }

        //////// Notice
        List<Pub> pubList = service.getPub(shopName);
        if (!AppUtils.isBlank(pubList)) {
            request.setAttribute("pubList", pubList);
        }

        ////// topNews
        List<News> newList = service.getNews(shopName,6);
        if (!AppUtils.isBlank(newList)) {
            request.setAttribute("newList", newList);
        }

        // topOffLineNews
        List<News> offLineNewsList = service.getOffLineNews(shopName);
        if (!AppUtils.isBlank(offLineNewsList)) {
            request.setAttribute("offLineNewsList", offLineNewsList);
        }

        /////for couplet
        List<Advertisement> advertisement = service.getAdvertisement(shopName);
        if (!AppUtils.isBlank(advertisement)) {
            for (Advertisement adv : advertisement) {
                request.setAttribute(adv.getType(), adv);
            }
        }

        List<Indexjpg> indexJpgList = service.getIndexJpeg(shopName);
        if (!AppUtils.isBlank(indexJpgList)) {
            request.setAttribute("MaxScreen", indexJpgList.size());
            JSONArray jsonArray = JSONArray.fromObject(indexJpgList);
            request.setAttribute("indexJSON", jsonArray);
        } else {
            request.setAttribute("MaxScreen", 0);
        }
        boolean shopExists = service.isShopExists(userName);
        request.setAttribute("shopExists", service.isShopExists(userName));      
        request.setAttribute("leagueShopExists", service.isLeagueShopExists(shopName));
        request.setAttribute("canbeLeagueShop", service.canbeLeagueShop(shopExists,userName,shopName));
        
        setSessionAttribute(request, Constants.SHOP_NAME, shopName);
        return mapping.findForward("success");
    }
    
    public ActionForward league(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	String shopName =(String)getSessionAttribute(request, Constants.SHOP_NAME);
    	if(shopName == null){
    		shopName = Constants.DEFAULT_SHOP;
    		 setShopName(request, response, shopName);
    	}
    	List<Myleague> leagues = service.myLeagueShop(shopName);
    	
    	request.setAttribute("leagues", leagues);
    	return mapping.findForward("success");
    }

    public ActionForward top(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String shopName = getShopName(request, response);
        String userName = SessionUserManagement.getUsername(request.getSession());
        ShopDetail shopDetail = service.getShopDetail(shopName);
        if (shopDetail == null) {
            request.setAttribute("error.message", "error.message");
            return mapping.findForward("error");
        }
        setSessionAttribute(request, "shopDetail", shopDetail);

        Logo logo = service.getLogo(shopName);
        if (AppUtils.isBlank(logo)) {
            logo = service.getLogo(Constants.DEFAULT_SHOP);
        }
        request.setAttribute("logo", logo);
        List<Sort> sortList = service.getSort(shopName, true);
        if (!AppUtils.isBlank(sortList)) {
            request.setAttribute("sortList", sortList);
        }
        //热门搜索
        List<Hotsearch> searchList = service.getSearch(shopName);
        if (!AppUtils.isBlank(searchList)) {
            request.setAttribute("searchList", searchList);
        }
        // topOffLineNews
        List<News> offLineNewsList = service.getOffLineNews(shopName);
        if (!AppUtils.isBlank(offLineNewsList)) {
            request.setAttribute("offLineNewsList", offLineNewsList);
        }       
        boolean shopExists = service.isShopExists(userName);
        request.setAttribute("shopExists", service.isShopExists(userName));      
        request.setAttribute("leagueShopExists", service.isLeagueShopExists(shopName));
        request.setAttribute("canbeLeagueShop", service.canbeLeagueShop(shopExists,userName,shopName));
        return mapping.findForward("success");
    }

    public ActionForward islogon(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String name = (String) request.getSession().getAttribute("user_name");
        // Member member = service.getMember(name);
        //		if(!AppUtils.isBlank(member)){
        //			request.setAttribute("member", member);
        //		}

        return mapping.findForward("success");
    }

    public ActionForward copy(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward("success");
    }

    public ActionForward topsort(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //		List<Sort> sortList = service.getSort(getShopName(request,response),true);
        //		   if(!AppUtils.isBlank(sortList)){
        //			   request.setAttribute("sortList", sortList);
        //		   }
        return mapping.findForward("success");
    }

    //已经取消首页的公告
    @Deprecated
    public ActionForward notice(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String shopName = getShopName(request, response);
        List<Pub> pubList = service.getPub(shopName);
        if (!AppUtils.isBlank(pubList)) {
            request.setAttribute("pubList", pubList);
        }
        return mapping.findForward("success");
    }

    public ActionForward paihang(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String name = getShopName(request, response);
        List<Hw> paihangList = service.getPaihang(name);
        if (!AppUtils.isBlank(paihangList)) {
            request.setAttribute("paihangList", paihangList);
        }

        return mapping.findForward("success");
    }

    public ActionForward topnews(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String name = getShopName(request, response);


        String topsortnews = request.getParameter("topsortnews");
        if ((topsortnews != null)) {
            List<News> newList = service.getNews(name,Constants.PAGE_SIZE);
            if (!AppUtils.isBlank(newList)) {
                request.setAttribute("newList", newList);
            }
            return mapping.findForward("topsortnews");
        }else{
            List<News> newList = service.getNews(name,6);
            if (!AppUtils.isBlank(newList)) {
                request.setAttribute("newList", newList);
            }
        	return mapping.findForward("success");
        }

        
    }

    public ActionForward friendlink(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String name = getShopName(request, response);
        List<Ad> adList = service.getAdOrderbybs(name);
        if (!AppUtils.isBlank(adList)) {
            request.setAttribute("adList", adList);
        }

        return mapping.findForward("success");
    }

    //搜索,此方法已经不用，改用post的方式
    @Deprecated
    public ActionForward search_bak(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String sortId = request.getParameter("sortId");
        String keyword = request.getParameter("keyword");//需要搜索的内容
        String curPageNO = request.getParameter("curPageNO");
        if (AppUtils.isBlank(curPageNO)) {
            curPageNO = "1";
        }
        String myaction = "search.do";
        Sort sort = null;
        List<Nsort> nsortList = null;
        //数字转换
        Integer IsortId = 0;
        if (AppUtils.isBlank(keyword)) {
            return mapping.findForward("index");
        } else {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            keyword = keyword.trim();
            myaction = "search.do?keyword=" + URLEncoder.encode(keyword, "UTF-8");
            request.setAttribute("keyword", keyword);
        }
        if (!AppUtils.isBlank(sortId) && !defaultValue.equals(sortId)) {
            myaction = myaction + "&sortId=" + sortId;
            try {
                IsortId = Integer.valueOf(sortId);
            } catch (Exception e) {
                return mapping.findForward("index");
            }
            sort = service.getSort(IsortId);
            if (sort != null) {
                setShopName(request, response, sort.getUserName());
            }
            request.setAttribute("sort", sort);
            request.setAttribute("CurrentSortId", sort.getSortId());
            nsortList = service.getNSort(IsortId);
            request.setAttribute("nsortList", nsortList);
        }

        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(Hw.class, curPageNO, myaction);
            cq.setCurPage(curPageNO);
            cq.setPageSize(pageSize);
            cq.addOrder("desc", "hwId");

            Criterion c = null;
            if (!AppUtils.isBlank(keyword)) {
                String[] keywords = AppUtils.searchByKeyword(keyword);
                for (String word : keywords) {
                    Criterion temp = Restrictions.like("hwName", "%" + word + "%");
                    if (c == null) {
                        c = temp;
                    } else {
                        c = Restrictions.or(c, temp);
                    }
                }
            }
            if (!AppUtils.isBlank(sortId) && !defaultValue.equals(sortId)) {
                if (c == null) {
                    c = Restrictions.eq("sortId", IsortId);
                } else {
                    c = Restrictions.and(c, Restrictions.eq("sortId", IsortId));
                }

            }
            if (c != null) {
                cq.add(c);
            }
            PageSupport ps = service.getHwDetail(cq);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("hwDetailList", ps.getResultList());
            if (ps.hasMutilPage()) {
                request.setAttribute("toolBar", ps.getToolBar());
            }
        } catch (Exception e) {
            log.error("getHwDetail", e);
            return mapping.findForward("index");
        }
        return mapping.findForward("success");
    }

    //
    public ActionForward search(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        SearchForm searchForm = (SearchForm) actionForm;
        String myaction = "javascript:searchPager";
        Sort sort = null;
        List<Nsort> nsortList = null;
        //1、关键字不能为空
        if (AppUtils.isBlank(searchForm.getKeyword())) {
            log.error("search keyword can't be null!");
            return mapping.findForward("index");
        }
        //2、查找对应的Sort,defaultValue=0表示没有选择类型
        if (!AppUtils.isBlank(searchForm.getSortId()) && !defaultInt.equals(searchForm.getSortId())) {
            sort = service.getSort(searchForm.getSortId());
            if (sort != null) {
                setShopName(request, response, sort.getUserName());
                request.setAttribute("sort", sort);
                request.setAttribute("CurrentSortId", sort.getSortId());
                nsortList = service.getNSort(searchForm.getSortId());
                request.setAttribute("nsortList", nsortList);
            }
        }

        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(Hw.class, searchForm.getCurPageNOTop(), myaction);
            cq.setPageSize(pageSize);
            cq.addOrder("desc", "hwId");
            Criterion c = null;
            if (!AppUtils.isBlank(searchForm.getKeyword())) {
                String[] keywords = AppUtils.searchByKeyword(searchForm.getKeyword());
                for (String word : keywords) {
                    Criterion temp = Restrictions.like("hwName", "%" + word + "%");
                    if (c == null) {
                        c = temp;
                    } else {
                        c = Restrictions.or(c, temp);
                    }
                }
            }
            if (!AppUtils.isBlank(searchForm.getSortId()) && !defaultInt.equals(searchForm.getSortId())) {
                if (c == null) {
                    c = Restrictions.eq("sortId", searchForm.getSortId());
                } else {
                    c = Restrictions.and(c, Restrictions.eq("sortId", searchForm.getSortId()));
                }

            }
            if (c != null) {
                cq.add(c);
            }
            PageSupport ps = service.getHwDetail(cq);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("hwDetailList", ps.getResultList());
            request.setAttribute("searchForm", searchForm);
            if (ps.hasMutilPage()) {
                request.setAttribute("toolBar", ps.getToolBar((Locale) request.getSession().getAttribute(
                        Globals.LOCALE_KEY)));
            }
        } catch (Exception e) {
            log.error("getHwDetail", e);
            return mapping.findForward("index");
        }
        return mapping.findForward("success");
    }

    //大分类商品
    public ActionForward sort(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String sortId = request.getParameter("sortId");
        String curPageNO = request.getParameter("curPageNO");
        if (AppUtils.isBlank(curPageNO)) {
            curPageNO = "1";
        }
        String myaction = "javascript:pager";
        //数字转换
        Integer IsortId = 0;
        try {
            IsortId = Integer.valueOf(sortId);
        } catch (Exception e) {
            log.error("sortId转换失败", e);
            return mapping.findForward("index");
        }
        Sort sort = service.getSort(IsortId);
        if (sort != null) {
            setShopName(request, response, sort.getUserName());
        }
        List<Nsort> nsortList = service.getNSort(IsortId);
        request.setAttribute("sort", sort);
        request.setAttribute("nsortList", nsortList);
        String userName = SessionUserManagement.getUsername(request.getSession());
        log.info("[{}],{},{},sort", new Object[] { request.getRemoteAddr(), userName == null ? "" : userName,
                getSessionAttribute(request, Constants.SHOP_NAME) });
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(Hw.class, curPageNO, myaction);
            cq.setCurPage(curPageNO);
            cq.setPageSize(20);
            cq.addOrder("desc", "modifyDate");
            cq.eq("sortId", IsortId);
            cq.eq("status", Constants.HW_ONLINE);
            cq.add();
            PageSupport ps = service.getHwDetail(cq);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            request.setAttribute("hwDetailList", ps.getResultList());
            if (ps.hasMutilPage()) {
                request.setAttribute("toolBar", ps.getToolBar((Locale) request.getSession().getAttribute(
                        Globals.LOCALE_KEY), "SimplePageProvider"));
            }
        } catch (Exception e) {
            log.error("sort", e);
            return mapping.findForward("index");
        }
        return mapping.findForward("success");
    }

    //小分类商品
    public ActionForward nsort(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String sortId = request.getParameter("sortId");
        String nsortId = request.getParameter("nsortId");
        String subNsortId = request.getParameter("subNsortId");
        String curPageNO = request.getParameter("curPageNO");
        if (AppUtils.isBlank(curPageNO)) {
            curPageNO = "1";
        }
        String myaction = "javascript:pager";
        if ((sortId == null) || (nsortId == null)) {
            log.error("sortId or nsortId is null! ");
            return mapping.findForward("index");
        }
        //数字转换
        Integer IsortId = convertStringToInteger(sortId);
        Integer InsortId = convertStringToInteger(nsortId);
        Integer IsubBsortId = convertStringToInteger(subNsortId);
        if ((IsortId == null) || (InsortId == null)) {
            throw new RuntimeException("sortId or nsortid cann't be null sortId = " + IsortId + " , nsortId ="
                    + InsortId);
        }
        String userName = SessionUserManagement.getUsername(request.getSession());
        log.info("{},{},{},{},{},nsort", new Object[] { request.getRemoteAddr(), userName == null ? "" : userName,
                getSessionAttribute(request, Constants.SHOP_NAME), IsortId, InsortId });
        Sort sort = service.getSort(IsortId);
        request.setAttribute("sort", sort);
        Nsort nsort = service.getNsort(InsortId);
        if ((nsort != null) && !AppUtils.isBlank(nsort.getSubSort())) {
            request.setAttribute("hasSubSort", true);
        }
        if (IsubBsortId != null) {
            Nsort subNsort = service.getNsort(IsubBsortId);
            request.setAttribute("subNsort", subNsort);
            if (subNsort != null) {
                request.setAttribute("CurrentSubNsortId", subNsort.getNsortId());
            }
        }

        List<Nsort> nsortList = service.getNsortList(IsortId);
        request.setAttribute("nsort", nsort);

        request.setAttribute("nsortList", service.getOthorNsort(nsortList));
        request.setAttribute("subNsortList", service.getOthorSubNsort(InsortId, nsortList));
        if (nsort != null) {
            request.setAttribute("CurrentNsortId", nsort.getNsortId());
        }
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(Hw.class, curPageNO, myaction);
            cq.setCurPage(curPageNO);
            cq.setPageSize(pageSize);
            cq.addOrder("desc", "hwId");
            cq.eq("sortId", IsortId);
            cq.eq("nsortId", InsortId);
            cq.eq("subNsortId", IsubBsortId);
            cq.add();
            PageSupport ps = service.getHwDetail(cq);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("hwDetailList", ps.getResultList());
            if (ps.hasMutilPage()) {
                request.setAttribute("toolBar", ps.getToolBar((Locale) request.getSession().getAttribute(
                        Globals.LOCALE_KEY), "SimplePageProvider"));
            }
        } catch (Exception e) {
            log.error("getHwDetail", e);
            return mapping.findForward("index");
        }
        return mapping.findForward("success");
    }

    private Integer convertStringToInteger(String id) {
        try {
            Integer result = Integer.valueOf(id);
            if (result == 0) {
                return null;
            } else {
                return result;
            }
        } catch (Exception e) {
            log.error("can not convert id " + id);
            return null;
        }
    }

    public ActionForward hoton(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String sortId = request.getParameter("sortId");
        List<Hw> hotonList = service.getHotOn(sortId);
        request.setAttribute("hotonList", hotonList);
        return mapping.findForward("success");
    }

    //查看产品
    public ActionForward views(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String id = request.getParameter("hwId");
        Integer hwId = 0;
        try {
            hwId = Integer.valueOf(id);
        } catch (Exception e) {
            log.error("views product", e);
            return mapping.findForward("index");
        }
        HwDetail hw = service.getHwDetail(hwId);
        if (hw != null) {
            request.setAttribute("hw", hw);
            setShopName(request, response, hw.getUserName());
            //更新查看次数
            service.updateHwViews(hwId);
            String userName = SessionUserManagement.getUsername(request.getSession());
            log.info("{},{},{},{},viewshw", new Object[] { request.getRemoteAddr(), userName == null ? "" : userName,
                    getSessionAttribute(request, Constants.SHOP_NAME), hw.getHwName() });
        }

        return mapping.findForward("success");
    }

    public ActionForward basket(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String id = request.getParameter("hwId");
        Integer hwId = 0;
        try {
            hwId = Integer.valueOf(id);
        } catch (Exception e) {
            // TODO: handle exception
        }
        Hw hw = service.getHwById(hwId);
        request.setAttribute("hw", hw);
        return mapping.findForward("success");
    }

    public ActionForward bought(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String userName = (String) request.getSession().getAttribute("userName");
        List<Basket> baskets = service.getBasketByuserName(userName, getShopName(request, response));
        Double totalcash = 0d;
        if (!AppUtils.isBlank(baskets)) {
            for (Basket bo : baskets) {
                try {
                    Double count = Double.valueOf(bo.getBasketCount());
                    Integer cash = Integer.valueOf(bo.getHwCash());
                    bo.setTotal(count * cash);
                    totalcash = totalcash + bo.getTotal();
                } catch (Exception e) {
                    log.error("convert count", e);
                }

            }
            request.setAttribute("baskets", baskets);
            request.setAttribute("totalcash", totalcash);
        }

        return mapping.findForward("success");
    }

    public ActionForward orderDetail(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String userName = UserManagement.getUsername();
        String subNumber = request.getParameter("subNumber");
        List<Basket> baskets = service.getBasketBySubNumber(userName, subNumber);
        Double totalcash = 0d;
        if (!AppUtils.isBlank(baskets)) {//每一个订单最少应该有一个商品
            for (Basket bo : baskets) {
                try {
                    Double count = Double.valueOf(bo.getBasketCount());
                    Integer cash = Integer.valueOf(bo.getHwCash());
                    bo.setTotal(count * cash);
                    totalcash = totalcash + bo.getTotal();
                } catch (Exception e) {
                    log.error("convert count", e);
                }

            }
            Sub sub = service.findSubBySubNumber(subNumber);
            request.setAttribute("sub", sub);
            request.setAttribute("baskets", baskets);
            request.setAttribute("totalcash", totalcash);
        }

        return mapping.findForward("success");
    }

    //管理员查看所有的订单
    public ActionForward adminOrderDetail(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String userName = request.getParameter("userName");
        String subNumber = request.getParameter("subNumber");
        List<Basket> baskets = service.getBasketBySubNumber(userName, subNumber);
        Double totalcash = 0d;
        if (!AppUtils.isBlank(baskets)) {//每一个订单最少应该有一个商品
            for (Basket bo : baskets) {
                try {
                    Double count = Double.valueOf(bo.getBasketCount());
                    Integer cash = Integer.valueOf(bo.getHwCash());
                    bo.setTotal(count * cash);
                    totalcash = totalcash + bo.getTotal();
                } catch (Exception e) {
                    log.error("convert count", e);
                }
            }
            Sub sub = service.findSubBySubNumber(subNumber);
            String loginName = UserManagement.getUsername();
            if (!UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA) && !sub.getShopName().equals(loginName)) {
                throw new RuntimeException(loginName + " cann't view Sub id is " + sub.getSubId());
            }
            request.setAttribute("sub", sub);
            request.setAttribute("baskets", baskets);
            request.setAttribute("totalcash", totalcash);
        }
        return mapping.findForward("success");
    }

    public ActionForward buy(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BasketForm form = (BasketForm) actionForm;
        String userName = UserManagement.getUsername();
        if (userName == null) {
            return mapping.findForward("nologin");
        }
        String shopName = getShopName(request, response);
        if ("buy".equals(form.getAction())) {
            Basket basket = service.getBasketByIdName(form.getHwId(), userName, shopName);
            if (basket == null) {//没有保存过		
                Basket b = new Basket();
                //bindEntity(form, b);
                b.setHwId(form.getHwId());
                b.setUserName(userName);
                b.setBasketCount(form.getCount());
                b.setHwName(form.getHwName());
                b.setHwCash(form.getHwCash());
                b.setBasketDate(new Date());
                b.setBasketCheck("False");
                b.setBasketAdmincheck("False");
                b.setShopName(shopName);
                service.saveBasket(b);
            } else {
                Integer count = 0;
                try {
                    count = Integer.valueOf(basket.getBasketCount()) + Integer.valueOf(form.getCount());
                } catch (Exception e) {
                    log.error("convert BasketCount", e);
                }
                basket.setBasketCount(String.valueOf(count));
                service.updateBasket(basket);
            }
        }
        request.getSession().setAttribute("userName", userName);
        return mapping.findForward("success");
    }

    public ActionForward clear(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String userName = UserManagement.getUsername();
        if (userName == null) {
            return mapping.findForward("nologon");
        }
        String basketId = request.getParameter("basketId");
        if (basketId == null) {
            service.deleteBasketByUserName(userName);
        } else {
            try {
                Integer id = Integer.valueOf(basketId);
                service.deleteBasketById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        request.getSession().setAttribute("userName", userName);
        return mapping.findForward("success");
    }

    public ActionForward news(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String newsId = request.getParameter("newsId");
        if (newsId != null) {
            News news = service.getNewsById(Integer.valueOf(newsId));
            if (news != null) {
                setShopName(request, response, news.getUserName());
                request.setAttribute("news", news);
            }

        }

        return mapping.findForward("success");
    }

    //所有的新闻
    public ActionForward allNews(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String curPageNO = request.getParameter("curPageNO");
        if (AppUtils.isBlank(curPageNO)) {
            curPageNO = "1";
        }
        String name = getShopName(request, response);
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(News.class, curPageNO, "javascript:pager");
            cq.setCurPage(curPageNO);
            cq.setPageSize(pageSize * 2);
            cq.eq("status", Constants.ONLINE);
            cq.eq("userName", name);
            cq.addOrder("desc", "newsDate");
            cq.add();
            PageSupport ps = service.getNews(cq);
            List<News> list = ps.getResultList();

            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            if (!AppUtils.isBlank(list)) {
            	/*
                for (News news : list) {
                    if (news.getNewsTitle().length() > 20) {
                        news.setNewsTitle(news.getNewsTitle().substring(0, 20) + "...");
                    }
                }
                */
                request.setAttribute("newsList", list);
                if (ps.hasMutilPage()) {
                    request.setAttribute("toolBar", ps.getToolBar((Locale) request.getSession().getAttribute(
                            Globals.LOCALE_KEY)));
                }
            }

        } catch (Exception e) {
            log.error("getAllNews", e);
            return mapping.findForward("index");
        }
        return mapping.findForward("success");
    }

    /**
     * 准备保存订单
     */
    public ActionForward cashsave(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String total = request.getParameter("total");
        if (total != null) {
            request.setAttribute("total", total);
        } else {
            total = defaultValue;//没有找到总的cash
        }
        String userName = UserManagement.getUsername();
        if (AppUtils.isBlank(userName)) {
            mapping.findForward("nologon");
        }
        List<Basket> baskets = service.getBasketByuserName(userName, getShopName(request, response));
        String basketId = "";
        if (!AppUtils.isBlank(baskets)) {
            String subNember = service.getSubNember(userName);
            request.setAttribute("subNember", subNember);

            for (Basket backet : baskets) {
                basketId = basketId + backet.getBasketId() + ",";

            }

            if (!AppUtils.isBlank(basketId)) {
                basketId = basketId.substring(0, basketId.length() - 1);
            }

            request.setAttribute("basketId", basketId);

            UserDetail member = service.getUserDetail(userName);
            if (!AppUtils.isBlank(member)) {
                request.setAttribute("member", member);
            }
        }

        request.getSession().setAttribute("userName", userName);
        return mapping.findForward("success");
    }

    /**
     * 保存订单
     */
    public ActionForward saveto(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        MemberForm form = (MemberForm) actionForm;
        String userName = UserManagement.getUsername();
        if (AppUtils.isBlank(form.getSubNember())) {
            mapping.findForward("nologon");
        }
        form.setUserName(userName);
        Sub sub = service.findSubBySubNumber(form.getSubNember());
        Sub bo = makeSub(form);
        bo.setShopName(getShopName(request, response));
        boolean isSave = true;//是否要更新订购数量
        if (sub != null) {
            //已经保存过，更新
            isSave = false;
            bo.setSubId(sub.getSubId());
        }
        service.saveSub(bo);
        //更新basketId
        String basketId = form.getBasketId();
        if (!AppUtils.isBlank(basketId)) {
            String[] ids = StringUtils.split(basketId, ",");
            if (!AppUtils.isBlank(ids)) {
                for (String id : ids) {
                    try {
                        Basket basket = service.getBasketById(Integer.valueOf(id));
                        if (basket != null) {
                            basket.setSubNumber(form.getSubNember());
                            basket.setBasketCheck("True");
                            service.updateBasket(basket);
                            //更新订购数量,true表示是新增的
                            if (isSave) {
                                service.updateHwBuys(Integer.valueOf(basket.getHwId()), basket.getBasketCount());
                            }
                        }
                    } catch (Exception e) {
                        log.error("load basket", e);
                    }

                }
            }
        }

        request.setAttribute("member", form);
        request.getSession().setAttribute("userName", userName);
        request.setAttribute("subNember", form.getSubNember());
        return mapping.findForward("success");
    }

    private Sub makeSub(MemberForm form) {
        Sub sub = new Sub();
        sub.setUserName(form.getUserName());
        sub.setSubNumber(form.getSubNember());
        sub.setSubDate(new Date());
        sub.setTotal(form.getTotal());
        sub.setSubTel(form.getUserTel());
        sub.setSubPost(form.getUserPostcode());
        sub.setSubMail(form.getUserMail());
        sub.setSubAdds(form.getUserAdds());
        //sub.setQian(form.getPayType());
        sub.setOther(form.getOther());
        sub.setBasketId(form.getBasketId());
        sub.setSubCheck("False");
        sub.setOrderName(form.getOrderName());
        return sub;
    }

    public ActionForward home(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward("success");
    }

    //处理的订单
    public ActionForward processOrder(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String userName = UserManagement.getUsername();
        String shopName = getShopName(request, response);
        if (userName == null) {
            return mapping.findForward("index");
        }
        String curPageNO = request.getParameter("curPageNO");
        String subNumber = request.getParameter("subNumber");
        if (!AppUtils.isBlank(subNumber)) {
            subNumber = subNumber.trim();
        }
        String subCheck = "False";
        if (request.getParameter("subCheck") != null) {
            subCheck = "True";
        }
        if (AppUtils.isBlank(curPageNO)) {
            curPageNO = "1";
        }
        String myaction = "javascript:pager";
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(Sub.class, curPageNO, myaction);
            cq.setCurPage(curPageNO);
            cq.setPageSize(18);
            cq.eq("userName", userName);
            cq.eq("shopName", shopName);
            if (!AppUtils.isBlank(subNumber)) {
                cq.like("subNumber", subNumber + "%");
            }
            cq.addOrder("desc", "subDate");
            cq.eq("subCheck", subCheck);
            cq.add();
            PageSupport ps = service.processOrder(cq);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            List<Sub> processOrderList = ps.getResultList();
            request.setAttribute("processOrderList", ConvertBeanUtil.convertShort(processOrderList));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            if (ps.hasMutilPage()) {
                request.setAttribute("toolBar", ps.getToolBar((Locale) request.getSession().getAttribute(
                        Globals.LOCALE_KEY)));
            }
        } catch (Exception e) {
            log.error("processOrder", e);
            return mapping.findForward("index");
        }
        if (subCheck == "False") {
            return mapping.findForward("processingOrder");
        }
        return mapping.findForward("processedOrder");
    }

    //管理员处理订单
    public ActionForward adminProcessOrder(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String userName = request.getParameter("userName");
        String curPageNO = request.getParameter("curPageNO");
        String subNumber = request.getParameter("subNumber");
        String loginName = UserManagement.getUsername();
        if (!AppUtils.isBlank(subNumber)) {
            subNumber = subNumber.trim();
        }
        String subCheck = "False";
        if (request.getParameter("subCheck") != null) {
            subCheck = "True";
        }
        if (AppUtils.isBlank(curPageNO)) {
            curPageNO = "1";
        }
        String myaction = "adminProcessOrder.do";
        try {
            //Qbc查找方式
            CriteriaQuery cq = new CriteriaQuery(Sub.class, curPageNO, myaction);
            cq.setCurPage(curPageNO);
            cq.setPageSize(pageSize);
            if (UserManagement.hasFunction(Constants.FUNCTION_VIEW_ALL_DATA)) {
                if (!AppUtils.isBlank(userName)) {
                    cq.eq("shopName", userName);
                }
            } else {
                cq.eq("shopName", loginName);
            }

            if (!AppUtils.isBlank(subNumber)) {
                cq.like("subNumber", subNumber + "%");
            }

            cq.eq("subCheck", subCheck);
            cq.addOrder("desc", "subDate");
            cq.add();
            PageSupport ps = service.getByCQ(cq);
            request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
            request.setAttribute("processOrderList", ConvertBeanUtil.convertShort(ps.getResultList()));
            request.setAttribute("offset", new Integer(ps.getOffset() + 1));
            if (ps.hasMutilPage()) {
                request.setAttribute("toolBar", ps.getToolBar());
            }
        } catch (Exception e) {
            log.error("processOrder", e);
            return mapping.findForward("index");
        }
        if ("False".equals(subCheck)) {
            return mapping.findForward("processingOrder");
        }
        return mapping.findForward("processedOrder");
    }

    private String getShopName(HttpServletRequest request, HttpServletResponse response) {
        String shopName = (String) getSessionAttribute(request, Constants.SHOP_NAME);
        if (!AppUtils.isBlank(shopName)) {
            return shopName;
        }
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constants.SHOP_NAME.equalsIgnoreCase(cookie.getName())) {
                    shopName = cookie.getValue();
                }
            }
        }
        if (AppUtils.isBlank(shopName)) {
            shopName = Constants.DEFAULT_SHOP;
            setShopName(request, response, shopName);
        }
        return shopName;
    }

    private void setShopName(HttpServletRequest request, HttpServletResponse response, String shopName) {
        setSessionAttribute(request, Constants.SHOP_NAME, shopName);
        Cookie cookie = new Cookie(Constants.SHOP_NAME, shopName);
        //生命周期    
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /*
     * private String getShopName(HttpServletRequest request){ String
     * name =
     * (String)request.getSession().getAttribute(Constants.SHOP_NAME);
     * if(name == null){ name = Constants.DEFAULT_SHOP;
     * request.getSession().setAttribute(Constants.SHOP_NAME, name); }
     * return name; }
     */

    /**
     * 保存用户，用户注册
     */
    public ActionForward userReg(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        UserForm form = (UserForm) actionForm;
        //检查是否重名
        if (service.isUserExist(form.getName())) {
            ActionMessages messages = new ActionMessages();
            messages.add("ERROR", new ActionMessage("user.isExist", form.getName()));
            saveErrors(request, messages);
            request.setAttribute("ERROR", true);
            return mapping.findForward("success");
        }

        TUser user = new TUser();
        UserDetail userDetail = new UserDetail();
        bindEntity(form, user);
        bindEntity(form, userDetail);
        Date date = new Date();
        user.setPassword(MD5Util.Md5Password(user.getName(), user.getPassword()));
        userDetail.setUserRegtime(date);
        userDetail.setModifyTime(date);
        userDetail.setUserRegip(request.getRemoteAddr());
        service.saveUser(user, userDetail,request.getParameter("openShop") != null);
        request.setAttribute("OK", "OK"); //表示操作成功
        return mapping.findForward("success");
    }

    /**
     * 我的帐户，用于显示用户信息
     */
    public ActionForward myaccount(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String userName = UserManagement.getUsername();
        if (userName == null) {
            return mapping.findForward("login");
        }
        UserDetail userDetail = service.getUserDetail(userName);
        //request.setAttribute("shopExists", service.isShopExists(userName));
        request.setAttribute("user", userDetail);
        return mapping.findForward("success");
    }

    public ActionForward leaveword(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String userName = UserManagement.getUsername();
        return mapping.findForward("success");
    }

    /**
     * 修改我的帐户，用于修改用户信息
     */
    public ActionForward updateAccount(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        UserForm form = (UserForm) actionForm;
        String userName = UserManagement.getUsername();
        TUser user = service.getUserByName(userName);
        if (user == null) {
            ActionMessages messages = new ActionMessages();
            messages.add("ERROR", new ActionMessage("user is null"));
            saveErrors(request, messages);
            return mapping.findForward("success");
        }
        UserDetail userDetail = service.getUserDetail(userName);
        if (!AppUtils.isBlank(form.getPassword())) {
            if (!user.getPassword().equals(MD5Util.Md5Password(userName, form.getPasswordOld()))) {
                log.warn("old password not right!");
                ActionMessages messages = new ActionMessages();
                messages.add("ERROR", new ActionMessage("passwordOld.notequal"));
                saveErrors(request, messages);
                return mapping.findForward("success");
            }
        }
        boolean update = true;
        if (userDetail == null) {
            update = false;
            userDetail = new UserDetail();   
        }
        Date date = new Date();
        userDetail.setNickName(form.getNickName());
        userDetail.setUserMail(form.getUserMail());
        userDetail.setUserAdds(form.getUserAdds());
        userDetail.setUserTel(form.getUserTel());
        userDetail.setUserPostcode(form.getUserPostcode());
        userDetail.setPassword(form.getPassword());
        userDetail.setFax(form.getFax());
        userDetail.setModifyTime(date);
        userDetail.setUserId(user.getId());
        if (update) {
            service.updateUser(userDetail);
            service.updateShopDetail(userDetail,request.getParameter("openShop") != null);
        } else {
            userDetail.setUserRegip(request.getRemoteAddr());
            userDetail.setUserRegtime(date);
            userDetail.setUserId(user.getId());
            userDetail.setUserName(userName);
            service.saveUerDetail(userDetail,request.getParameter("openShop") != null);
            service.changePassword(userDetail);
        }
        request.setAttribute("OK", "OK"); //表示操作成功
        return mapping.findForward("success");
    }

}
