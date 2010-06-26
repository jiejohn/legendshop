<%@ include file="/common/jsp/taglib.jsp"%>
<%@ include file="/common/jsp/common.jsp"%>
<%-- Error Messages --%>
<c:if test="${not empty springMessages}">
	<div class="message">
        <c:forEach var="msg" items="${springMessages}">
			<img src="${root}/img/iconInformation.gif" alt="Info"/>${msg}<br/>
        </c:forEach>
	</div>    
</c:if>

<%-- Info Messages --%>
<c:if test="${not empty springErrors}">
	<div class="error">
        <c:forEach var="errorMsg" items="${springErrors}">
			<img src="${root}/img/iconWarning.gif" alt="Warning"/>${errorMsg}<br/>
        </c:forEach>
	</div>    
</c:if>



