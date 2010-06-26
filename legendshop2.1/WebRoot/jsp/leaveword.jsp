<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:directive.page import="com.done.util.UserManagement" />
<%@include file='/common/jsp/taglib.jsp'%>
<%
    session.setAttribute("root", request.getContextPath());
%>
<script src="${root}/common/js/jquery.validate.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="${root}/common/css/indexJpgForm.css" />
<script type="text/javascript">
   function checkform() {
	  if(document.getElementById("yourName").value==null||document.getElementById("yourName").value=='')
		{
			alert('<bean:message key="errors.required" arg0="user name"/>');
			document.getElementById("yourName").focus();
			return false;
		}
	  if(document.getElementById("content").value==null||document.getElementById("content").value=='')
		{
		if(document.getElementById("content").value==null||document.getElementById("content").value==''){
			alert('<bean:message key="errors.required" arg0="Content"/>');
			document.getElementById("content").focus();
			return false;
		}

		}		
		return true ;
  }
  
  
jQuery(document).ready(function() {
    jQuery("#form1").validate({
        rules: {
            yourName: {
                required: true,
                minlength: 4
            },
           content: {
                required: true,
                minlength: 3
            }
        },
        messages: {
            yourName: {
                required: '<bean:message key="username.required"/>',
                minlength: '<bean:message key="username.minlength"/>'
            },
            content: {
                required: '<bean:message key="errors.required" arg0=""/>',
                minlength: '<bean:message key="errors.minlength" arg0="" arg1="3"/>'
            }
        }
    })
});  
  
</script>
<TABLE width=740 align="center" cellPadding=0 cellSpacing=0>
	<TR>

		<TD width=740>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="48">
				<tr>

					<td height="108">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td width="100%" height="28" class="titlebg">
									<div align="center">
										<font color="#FFFFFF"><b><bean:message
													key="leave.word" />
										</b>
										</font>
									</div>
								</td>
							</tr>
							<tr>

								<td height="63" bgcolor="#ECECEC">
									<div align="center">
										<table width="100%" height="30" border="0" cellspacing="1">
											<tr>
												<td width="100%" height="37">
													<form method="POST"
														action="${root}/businessAction/leaveWord.c" id="form1"
														name="form1">
														<input id="userId" name="userId"
															value="<%=UserManagement.getUserId()%>" type="hidden" />
														<input id="userName" name="userName"
															value="<%=UserManagement.getUsername()%>" type="hidden" />
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0" align="center">
															<tr>
																<td height="110" valign="middle" align="left">
																	<table border="0" align="center" cellpadding="0"
																		cellspacing="5">
																		<tr>
																			<td width="29%">
																				<div align="center">
																					<font color="#ff0000">*</font>
																					<bean:message key="user.name" />
																					：
																				</div>
																			</td>
																			<td width="71%">
																				<div align="left">
																					<p>
																						<input type="text" class=input id="yourName"
																							name="yourName" size="12" maxlength="50">
																					</p>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<div align="right">
																					<font color="#ff0000">*</font>
																					<bean:message key="content" />
																					：
																				</div>
																			</td>
																			<td>
																				<div align="left">
																					<p>
																						<textarea rows="10" cols="50" id="content"
																							name="content"></textarea>
																					</p>
																				</div>
																			</td>
																		</tr>
																	</table>
																	<table width="90%" border="0" cellspacing="5"
																		cellpadding="0" align="center">
																		<tr>
																			<td width="80">
																			</td>
																			<td>
																				<div align="center">
																					<input type="submit"
																						value="<bean:message key="submit"/>" class="s" />
																				</div>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</form>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</TD>
	</TR>
</TABLE>