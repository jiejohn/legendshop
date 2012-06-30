<%@ include file="/pages/common/taglib.jsp"%>
<%@ include file="/pages/common/common.jsp"%>
<%-- Error Messages --%>
<c:if test="${not empty springMessages}">
	<div class="message">
        <c:forEach var="msg" items="${springMessages}">
			<img src="${pageContext.request.contextPath}/common/images/default/iconInformation.gif" alt="Info"/>${msg}<br/>
        </c:forEach>
	</div>    
</c:if>

<%-- Info Messages --%>
<c:if test="${not empty springErrors}">
	<div class="error">
        <c:forEach var="errorMsg" items="${springErrors}">
			<img src="${pageContext.request.contextPath}/common/images/default/iconWarning.gif" alt="Warning"/>${errorMsg}<br/>
        </c:forEach>
	</div>    
</c:if>



