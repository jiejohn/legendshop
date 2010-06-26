<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<jsp:directive.page import="com.done.util.UserManagement"/>
<html>

<head>
<%
    session.setAttribute("userName",UserManagement.getUsername());
%>
<%@include file='/common/jsp/common.jsp'%>
<title>

<c:choose>
   <c:when test="${userName != null}">
   	${userName}  <bean:message key="shopingCar"/>
   </c:when>
   <c:otherwise>
  	<bean:message key="shopingCar"/>
   </c:otherwise>
</c:choose>
</title>
</head>
<body>
  <c:choose>
   <c:when test="${userName == null}">
   	   <logic:forward name="nologon"/>
   </c:when>
  <c:otherwise>
  <html:form method="POST" action="buy.do">
<table border="0" width="500" cellspacing="0" cellpadding="0" align="center" >
  <tr>
    <td align="center" height="5" width="100%">
   </td>
  </tr>
<tr class="titlebg">
    <td height="28" align="center" width="100%">
    <font color="white"><bean:message key="product.subscribing.list"/></font>
    </td>
  </tr>
  
    <tr> 
    <td width="100%"> 
    <input name="action" value="buy" type="hidden"/>
    <input name="hwId" value="${hw.hwId}" type="hidden"/>
    <input name="hwName" value="${hw.hwName}" type="hidden"/>
    <input name="hwCash" value="${hw.hwCash}" type="hidden"/>
        <table border="0" width="100%" cellspacing="0" cellpadding="0">
          <tr bgcolor="#FFFFFF"> 
            <td bgcolor="#ECECEC">
            <div align="center">
              <center>
              <table width="100%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
                <tr> 
                  <td height="25"> <div align="center"><bean:message key="product.name"/></div></td>
                  <td height="25"> <div align="center"><bean:message key="product.price"/></div></td>
                  <td height="25"> <div align="center"><bean:message key="product.number"/></div></td>
                  <td height="25"> <div align="center"><bean:message key="product.order"/></div></td>
                </tr>
                <tr> 
                  <td height="30"> <div align="center">${hw.hwName}</div></td>
                  <td height="30"> <div align="center"> 
                      ${hw.hwCash}
                      </div></td>
                  <td height="30"> <div align="center"> 
                      <select size="1" name="count">
                        <%for(int i=1;i<=15;i++){
						%>
                        <option><%=i%></option>
                        <%}
						%>
                      </select>
                    </div></td>
                  <td height="30"> 
                  <div align="center">
                  <input type="submit" value="<bean:message key="submit"/>" class="s" />
                   </div>
                  </td>
                </tr>
              </table>
              </center>
            </div></tr>
        </table>
      
      </td>
  </tr>

  <tr> 
    <td height="35" valign="middle" bgcolor="#ECECEC">
    <table width="83%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="33%" valign="middle"  height="35">
	          <a href="${root}/buy.do" onMouseOver="btnMouseOver('mbtn0')" onMouseOut="btnMouseOut('mbtn0')" onMouseDown="btnMouseDown('mbtn0')">
	            <img name=mbtn0 border=0 width="92" height="26" /></a>
          </td>
          <td width="33%" valign="middle"  height="35">
                <a href="${root}/clear.do" onMouseOver="btnMouseOver('mbtn1')" onMouseOut="btnMouseOut('mbtn1')" onMouseDown="btnMouseDown('mbtn1')">
                    <img name=mbtn1 border=0 width="92" height="26" /></a>
          </td>
          <td width="33%" valign="middle"  height="35">
            <a href="${root}/jsp/cash.jsp" onMouseOver="btnMouseOver('mbtn2')" onMouseOut="btnMouseOut('mbtn2')" onMouseDown="btnMouseDown('mbtn2')" >
                <img name=mbtn2 border=0 width="92" height="26" /></a>
          </td>
        </tr>
      </table></td>
  </tr>

  <tr> 
    <td height="28" align="center" class="titlebg"><font color="white"><bean:message key="product.subscribed.list"/></font></td>
  </tr>
  <jsp:include flush="true" page="/bought.do" />

</table>
  </html:form>
   </c:otherwise>
</c:choose>
<script type="text/javascript">
<!--
var btnCount = 3;
var staCount = 3;
var btnImages = new Array();
for (i= 0; i< btnCount; i++)
{
    btnImages[i] = new Array();
    for (j= 0; j< staCount; j++)
    {
        btnImages[i][j] = new Image();
        btnImages[i][j].src = '<bean:message key="img.btn"/>' + i + '_' + j + '.gif';
    }

}

    document.images['mbtn0'].src = '<bean:message key="img.btn"/>' + '0_0.gif';
    document.images['mbtn1'].src = '<bean:message key="img.btn"/>' + '1_0.gif';
    document.images['mbtn2'].src = '<bean:message key="img.btn"/>' + '2_0.gif';
    
function btnMouseOut(img)
{
    document.images[img].src = btnImages[img.substring(img.indexOf('mbtn')+4,img.length)][0].src;
};
function btnMouseOver(img)
{
    document.images[img].src = btnImages[img.substring(img.indexOf('mbtn')+4,img.length)][1].src;
};
function btnMouseDown(img)
{
    document.images[img].src = btnImages[img.substring(img.indexOf('mbtn')+4,img.length)][2].src;
};
//-->
</script>
