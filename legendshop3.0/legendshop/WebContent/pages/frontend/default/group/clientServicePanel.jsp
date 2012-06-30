<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

      <div class="sidebox">
        <div class="boxtit">
           <span><a href="#">更多 &gt;&gt;</a></span><b>客户服务</b>
         </div>
         <div class="phone">
           <p class="pimg"><img src="${pageContext.request.contextPath}/img/group/phone.png" width="75" height="56" /></p>
           <div class="pword">
             <p class="phonenumber">400-100-1234</p>
             <p class="phonetime">周一至周日 9:00-20:00</p>
           </div>
           <div class="clear"></div>
        </div>
         <div class="word">
          <c:forEach items="${requestScope.groupNewsTopList}" var="news">
           	<p><span><img src="${pageContext.request.contextPath}/img/group/dot.gif" width="7" height="7" /></span>
           	<span><a href="<ls:url address='/news/${news.newsId}'/>"  target="_blank">${news.newsTitle}</a></span></p>
           </c:forEach>
         </div>
         <div class="clear"></div>
      </div>