<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:directive.include file="includes/top.jsp" />
<form:form method="post" id="fm1" cssClass="fm-v clearfix"
	commandName="${commandName}" htmlEscape="true">
	<form:errors path="*" cssClass="errors" id="status" element="div" />

	<c:if test="${not empty isInitialPassword }">
		<div class="info">
			<spring:message code="password.initial.instructions" />
		</div>
	</c:if>
	<c:if test="${not empty isExpiredPassword}">
		<div class="info">
			<spring:message code="password.expired.instructions" />
		</div>
	</c:if>

	<div class="box" id="login">

		<div class="row">
			<label for="password"> <spring:message
					code="screen.welcome.label.password.old" />
			</label>
			<form:password cssClass="required" cssErrorClass="error"
				id="password" size="25" tabindex="2" path="password"
				htmlEscape="true" autocomplete="off" />
		</div>


		<div class="row">
			<label for="newPassword"> <spring:message
					code="screen.welcome.label.password.new" />
			</label>
			<form:password cssClass="required" cssErrorClass="error"
				id="password" size="25" tabindex="2" path="newPassword"
				htmlEscape="true" autocomplete="off" />
		</div>
		<div>
			<spring:message code="password.instructions" />
		</div>

		<div class="row">
			<label for="confirmPassword"> <spring:message
					code="screen.welcome.label.password.confirm" />
			</label>
			<form:password cssClass="required" cssErrorClass="error"
				id="password" size="25" tabindex="2" path="confirmPassword"
				htmlEscape="true" autocomplete="off" />
		</div>

		<div class="row btn-row">
			<input type="hidden" name="lt" value="${flowExecutionKey}" /> <input
				type="hidden" name="_eventId" value="submit" /> <input
				class="btn-submit" name="submit" accesskey="c"
				value="<spring:message code="password.button.confirm" />"
				tabindex="4" type="submit" />
		</div>
	</div>
</form:form>
<jsp:directive.include file="includes/bottom.jsp" />
