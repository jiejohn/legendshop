package com.done.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.cas.CasAuthenticationToken;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.acegisecurity.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bingosoft.jcf.auth.AuthDao;
import bingosoft.jcf.auth.GrantedFunction;
import bingosoft.jcf.auth.model.userdetail.User;
import bingosoft.jcf.service.impl.ContextServiceLocator;

public class UserManagement {
    private static Logger log = LoggerFactory.getLogger(UserManagement.class);

    public static String getUsername() {
        User user = getUser();
        if (user != null) {
            return user.getUsername();
        } else {
            return null;
        }
    }

    public static String getUserId() {
        User user = getUser();
        if (user != null) {
            return user.getId();
        } else {
            return null;
        }

    }

    public synchronized static User getUser() {
        User user = null;
        if ((SecurityContextHolder.getContext() != null)
                && (SecurityContextHolder.getContext().getAuthentication() != null)) {
            if (SecurityContextHolder.getContext().getAuthentication() instanceof CasAuthenticationToken) {
                user = (User) ((CasAuthenticationToken) SecurityContextHolder.getContext().getAuthentication())
                        .getUserDetails();
            }
        } else {
            //System.out.println("SecurityContextHolder Context is Null");
        }
        return user;
    }

    /** */
    /**
     * ȡ�õ�ǰ�û�����
     * 
     */
    public static String getPassword() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx != null) {
            Authentication auth = ctx.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal instanceof UserDetails) {
                    return ((UserDetails) principal).getPassword();
                } else {
                    return null;
                }
            }

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
    public static Collection getPrincipalFunctionByAuthorities() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
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

    public static boolean hasFunction(String function) {
        boolean result = false;
        Collection<GrantedFunction> functions = getPrincipalFunctionByAuthorities();
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
        UserManagement um = new UserManagement();
        System.out.println(UserManagement.getUsername());
        System.out.println(UserManagement.getPassword());
        System.out.println(UserManagement.getSessionID());

    }

}
