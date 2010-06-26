package com.done.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.cas.CasAuthenticationToken;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bingosoft.jcf.auth.AuthDao;
import bingosoft.jcf.auth.GrantedFunction;
import bingosoft.jcf.auth.model.userdetail.User;
import bingosoft.jcf.service.impl.ContextServiceLocator;

public class SessionUserManagement {
    private static Logger log = LoggerFactory.getLogger(SessionUserManagement.class);

    public static String getUsername(HttpSession session) {
        SecurityContext context = (SecurityContext) session.getAttribute("ACEGI_SECURITY_CONTEXT");
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if ((authentication != null) && (authentication instanceof CasAuthenticationToken)) {
                User user = (User) ((CasAuthenticationToken) authentication).getUserDetails();
                return user.getUsername();
            } else {
                //log.warn("Authentication is NULL!");
            }
        } else {
            //log.warn("SecurityContext is NULL!");
        }
        return null;
    }

    public static String getUserId(HttpSession session) {
        SecurityContext context = (SecurityContext) session.getAttribute("ACEGI_SECURITY_CONTEXT");
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if ((authentication != null) && (authentication instanceof CasAuthenticationToken)) {
                User user = (User) ((CasAuthenticationToken) authentication).getUserDetails();
                return user.getId();
            } else {
                log.warn("Authentication is NULL!");
            }
        } else {
            log.warn("SecurityContext is NULL!");
        }
        return null;
    }

    public synchronized static User getUser(HttpSession session) {
        SecurityContext context = (SecurityContext) session.getAttribute("ACEGI_SECURITY_CONTEXT");
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if ((authentication != null) && (authentication instanceof CasAuthenticationToken)) {
                User user = (User) ((CasAuthenticationToken) authentication).getUserDetails();
                return user;
            } else {
                log.warn("Authentication is NULL!");
            }
        } else {
            log.warn("SecurityContext is NULL!");
        }
        return null;
    }

    /** */
    /**
     * ȡ�õ�ǰ�û�����
     * 
     */
    public static String getPassword(HttpSession session) {
        SecurityContext context = (SecurityContext) session.getAttribute("ACEGI_SECURITY_CONTEXT");
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if ((authentication != null) && (authentication instanceof CasAuthenticationToken)) {
                User user = (User) ((CasAuthenticationToken) authentication).getUserDetails();
                return user.getPassword();
            } else {
                log.warn("Authentication is NULL!");
            }
        } else {
            log.warn("SecurityContext is NULL!");
        }
        return null;
    }

    /** */
    /**
     * ȡ�õ�ǰ�û�SessionId
     * 
     */
    public static String getSessionID() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx != null) {
            if (ctx instanceof SecurityContext) {
                SecurityContext sc = ctx;
                Authentication auth = sc.getAuthentication();
                if (auth != null) {
                    Object details = auth.getDetails();
                    if (details instanceof WebAuthenticationDetails) {
                        return ((WebAuthenticationDetails) details).getSessionId();
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 得到用的的Authentication，并且从Authentication中获得 Authorities，进而得到 授予用户的
     * Function
     * 
     * @return Collection
     */
    public static Collection getPrincipalFunctionByAuthorities(HttpSession session) {
        SecurityContext context = (SecurityContext) session.getAttribute("ACEGI_SECURITY_CONTEXT");
        if (null == context) {
            return Collections.EMPTY_LIST;
        }
        Authentication currentUser = context.getAuthentication();
        if (null == currentUser) {
            return Collections.EMPTY_LIST;
        }

        if ((null == currentUser.getAuthorities()) || (currentUser.getAuthorities().length < 1)) {
            return Collections.EMPTY_LIST;
        }
        // currentUser.getAuthorities() 返回的是 GrantedAuthority[]
        List granted = Arrays.asList(currentUser.getAuthorities());
        //AuthDao authDao =(AuthDao) AppContext.getInstance().getAppContext().getBean("authDao");
        AuthDao authDao = (AuthDao) ContextServiceLocator.getInstance().getBean("authDao");
        Collection grantedFunctions = authDao.getFunctionsByRoles(granted);
        return grantedFunctions;
    }

    public static boolean hasFunction(HttpSession session, String function) {
        boolean result = false;
        Collection<GrantedFunction> functions = getPrincipalFunctionByAuthorities(session);
        for (GrantedFunction gf : functions) {
            if (gf.equals(function)) {
                return true;
            }
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SessionUserManagement um = new SessionUserManagement();
        System.out.println(SessionUserManagement.getSessionID());

    }

}
