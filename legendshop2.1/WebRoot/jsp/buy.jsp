<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:directive.page import="com.done.util.UserManagement"/>
<%@include file='/common/jsp/taglib.jsp'%>
<%
    session.setAttribute("userName",UserManagement.getUsername());
 %>
<html>
<head>
<%@include file='/common/jsp/common.jsp'%>
    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
<title>

<c:choose>
   <c:when test="${userName != null}">
    ${userName} <bean:message key="shopingCar"/>
   </c:when>
   <c:otherwise>
    <bean:message key="shopingCar"/>
   </c:otherwise>
</c:choose>
</title>
</head> 
<body topmargin="0">

<table border="0" width="500" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px">
  <tr> 
    <td height="28" align="center" class="titlebg"><font color="white"><bean:message key="product.subscribed.list"/></font></td>    
  </tr>

<jsp:include flush="true" page="/bought.do"></jsp:include>

  <tr> 
<c:choose>
                           <c:when test="${not empty baskets }">
                                  <td align="center" class="headerbg" height="35px" width="100%" valign="middle">
                                  <a href="${root}/buy.do" onMouseOver="btnMouseOver('mbtn0')" onMouseOut="btnMouseOut('mbtn0')" onMouseDown="btnMouseDown('mbtn0')">
                                    <img name=mbtn0 border=0 width="92" height="26" /></a>&nbsp;
                                     <a href="${root}/clear.do" onMouseOver="btnMouseOver('mbtn1')" onMouseOut="btnMouseOut('mbtn1')" onMouseDown="btnMouseDown('mbtn1')">
                                       <img name=mbtn1 border=0 width="92" height="26" /></a>&nbsp;
                                      <a href="${root}/jsp/cash.jsp" onMouseOver="btnMouseOver('mbtn2')" onMouseOut="btnMouseOut('mbtn2')" onMouseDown="btnMouseDown('mbtn2')" >
                                            <img name=mbtn2 border=0 width="92" height="26" /></a>
                                  </td>
                           </c:when>
                           <c:otherwise>
                                <td align="center" class="titlebg" height="30">   
                                    <font color="#FF6600"><bean:message key="cash.alerm"/></font>
                                </td>
                           </c:otherwise>
                        </c:choose>
  </tr>
</table>

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
        btnImages[i][j].src = '${root}/<bean:message key="img.btn"/>' + i + '_' + j + '.gif';
    }

}

    document.images['mbtn0'].src = '${root}/<bean:message key="img.btn"/>' + '0_0.gif';
    document.images['mbtn1'].src = '${root}/<bean:message key="img.btn"/>' + '1_0.gif';
    document.images['mbtn2'].src = '${root}/<bean:message key="img.btn"/>' + '2_0.gif';
    
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
</body>
</html>