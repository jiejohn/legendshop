<%@page import="com.legendshop.core.cache.EhCacheCacheManager"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.legendshop.core.cache.MemCachedManager"%>
<%@page import="net.sf.ehcache.Ehcache"%>
<%@page import="com.legendshop.core.cache.LegendCache"%>
<%@page import="com.legendshop.util.SafeHtml"%>

<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@page import="org.springframework.cache.Cache"%>
<%@page import="com.legendshop.util.ContextServiceLocator"%>
<%@page import="org.springframework.cache.CacheManager"%>
<%@ page import="java.util.*, java.io.*"%>
<%@page import="java.text.NumberFormat"%>


<%
	CacheManager cacheManager = (CacheManager)ContextServiceLocator.getInstance().getBean("cacheManager");
	if(cacheManager instanceof MemCachedManager){
	%>
	<jsp:forward page="memcache.jsp"></jsp:forward>
	<%
	}else if(cacheManager instanceof EhCacheCacheManager){
	%>
	 <jsp:forward page="ehcacheCache.jsp"></jsp:forward>
	<%
	}
%>


