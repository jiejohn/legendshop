<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
      
      <div class="sidebox mar12">
        <div class="boxtit">
           <span><a href="${pageContext.request.contextPath}/group/question${applicationScope.WEB_SUFFIX}">更多 &gt;&gt;</a></span><b>常见问题</b>
         </div>
         <ul class="lista listimg1">
          <c:forEach items="${requestScope.groupNewsBottomList}" var="news" varStatus="status">
            <li><a href="<ls:url address='/news/${news.newsId}'/>" target="_blank">${news.newsTitle}</a></li>
            </c:forEach>
         </ul>         
      </div>
