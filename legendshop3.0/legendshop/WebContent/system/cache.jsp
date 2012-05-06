<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="com.legendshop.util.ContextServiceLocator"%>
<%@page import="net.sf.ehcache.CacheManager"%>
<%@ page import="java.util.*, java.io.*"%>
<%@page import="java.text.NumberFormat"%>
<br>系统缓存<br>
<%
 	int total = 1000;
	CacheManager cacheManager = (CacheManager)ContextServiceLocator.getInstance().getBean("ehcacheFactory");
	String[] cacheNames = cacheManager.getCacheNames();
	if(cacheNames != null){
		for(int i = 0; i <cacheNames.length; i++){
			Cache cache = cacheManager.getCache(cacheNames[i]) ;
			if(cache != null){
				List names = cache.getKeys();
				if(names != null){
					int len =names.size() > total ? total :names.size();
					for(int j = 0; j <len; j++){
						%>
						<table border="1" width="100%">
							<tr>
								<td width="150px"><%=j%> : <%=cache.getName() %> </td>
								<td  width="30px"><%=cache.getSize() %> </td>
								<td><%=cache.get(names.get(j)) %></td>
							</tr>
						</table>
						<%
					}
				}
			}
		}
		
	}
%>

