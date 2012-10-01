<%@page import="net.spy.memcached.NodeLocator"%>
<%@page import="java.net.SocketAddress"%>
<%@page import="net.spy.memcached.MemcachedClient"%>
<%@page import="com.legendshop.core.cache.MemcachedCache"%>
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

<br>Memcached系统缓存<br>
<table border="1" width="100%" style="border-collapse: collapse;">
			       <tr style="font-weight: bold;">
							   <td width="100px">顺序</td>
								<td width="250px">Cache Name</td>
								<td  width="100px">Cache Size</td>
								<td>Cache Detail</td>
						</tr>
<%
	SafeHtml safeHtml = new SafeHtml();
 	int total = 10000;
 	int cacheNum = 0;
 	int addressNum = 0;
	CacheManager cacheManager = (CacheManager)ContextServiceLocator.getInstance().getBean("cacheManager");
	Collection<String> cacheNames = cacheManager.getCacheNames();
	if(cacheNames != null){
	Iterator<String> itr = cacheNames.iterator();
	while(itr.hasNext()){
	       String cacheName = itr.next();
		   cacheNum++;
			MemcachedClient cache = ((MemcachedCache)cacheManager.getCache(cacheName)).getNativeCache();
			if(cache != null){
			Map<SocketAddress, Map<String,String>> map = cache.getStats();
				Set<SocketAddress> socketAddresses = map.keySet();
				Collection<Map<String,String>>  values = map.values();
				for(SocketAddress address: socketAddresses){
				 addressNum++;
						%>
			       <tr style="background: #bbb">
							   <td colspan="4"><%=cacheNum%> Cache Name: <%=cacheName %>,  address: <%=address.toString() %></td>
						</tr>
						<%
				   Map<String,String> valueMap =  map.get(address);
					Set<String> keyValue  = valueMap.keySet();
					int valueNum = 0;
					for(String key: keyValue){
					   valueNum++;
						String valueValue = valueMap.get(key);
					if(cacheNum % 2 == 0){
						%>
			       <tr style="background: #ccc">
			       <% }else{%>
			       <tr>
			    <%} %>
							   <td width="100px"><%=addressNum%> </td>
								<td width="150px"><%=valueNum%> : <%=key %> </td>
								<td  width="30px"><%=keyValue.size() %> </td>
								<td><pre><%=valueValue == null ? null : safeHtml.makeSafe(valueValue).toString() %></pre> </td>
						</tr>
						<%
					 }
				   }
				}
			}
		}
%>

</table>

