<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<%@include file='/common/jsp/common.jsp'%>
<%
			int offset=((Integer)request.getAttribute("offset")).intValue();
	%>
	<script type="text/javascript">
<!--
		function pager(curPageNO){
			window.location.href="${root}/allNews.do?curPageNO="+curPageNO;
		}
//-->
</script>
          <table cellspacing="0" cellpadding="0" width="740px">
            <tr> 
                    <td width="0%" height="28" class="headerbg" align="center">
                    <font color="#FFFFFF"><b><bean:message key="newsCenter"/></b></font></td>
                  </tr>
           <c:forEach items="${requestScope.newsList}" var="news" varStatus="status">
			<tr> 
                <td height="2"> 
					<img src="${root}/img/pics/biao.gif" width="15" height="18">
<%--					&nbsp;<img src="${root}/common/images/number/num_${status.index+1}.gif">--%>
					
					<a href="${root}/news/${news.newsId}">${news.newsTitle}</a>
<%--					(<fmt:formatDate value="${news.newsDate}" pattern="yyyy-MM-dd"/>)  --%>
				</td>
			 </tr>
			</c:forEach>  
            <tr> 
                      <td width=478 height="23"> 
                        <p align="right"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></p>
                      </table>

