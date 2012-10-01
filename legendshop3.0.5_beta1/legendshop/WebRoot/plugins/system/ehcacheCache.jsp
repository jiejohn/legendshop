<%@page import="net.sf.ehcache.Ehcache"%>
<%@page import="com.legendshop.core.cache.LegendCache"%>
<%@page import="com.legendshop.util.SafeHtml"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@page import="org.springframework.cache.Cache"%>
<%@page import="com.legendshop.util.ContextServiceLocator"%>
<%@page import="org.springframework.cache.CacheManager"%>
<%@ page import="java.util.*, java.io.*"%>
<%@page import="java.text.NumberFormat"%>

<br>Ehcache系统缓存<br>
<table border="1" width="100%" style="border-collapse: collapse;">
			       <tr style="font-weight: bold;">
							   <td width="100px">顺序</td>
								<td width="150px">Cache Name</td>
								<td  width="30px">Cache Size</td>
								<td>Cache Detail</td>
						</tr>
<%
	SafeHtml safeHtml = new SafeHtml();
 	int total = 10000;
 	int cacheNum = 0;
 	int num = 0;
	CacheManager cacheManager = (CacheManager)ContextServiceLocator.getInstance().getBean("cacheManager");
	Collection<String> cacheNames = cacheManager.getCacheNames();
	if(cacheNames != null){
	Iterator<String> itr = cacheNames.iterator();
	while(itr.hasNext()){
		   cacheNum++;
			Ehcache cache = ((LegendCache)cacheManager.getCache(itr.next())).getNativeCache();
			if(cache != null){
				List names = cache.getKeys();
				if(names != null){
					int len =names.size() > total ? total :names.size();
					for(int j = 0; j <len; j++){
				   num++;
					if(cacheNum % 2 == 0){
						%>
			       <tr style="background: #ccc">
			       <% }else{ %>
			       <tr>
			    <%} %>
							   <td width="100px"><%=num%> </td>
								<td width="150px"><%=j%> : <%=cache.getName() %> </td>
								<td  width="30px"><%=cache.getSize()%> </td>
								<td><%=cache.get(names.get(j)) == null ? null : safeHtml.makeSafe(cache.get(names.get(j)).toString()) %></pre> </td>
						</tr>
						<%
					}
				}
			}
		}
	}
%>

</table>

