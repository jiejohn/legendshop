<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<html>
<head>
<%@include file='/common/jsp/common.jsp'%>
    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
<title>
 ${sub.subNumber} <bean:message key="product.subscribed.list"/>
</title>
</head> 
<body topmargin="0">
<table border="0" width="500" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td class="titlebg" height="28">
            <div align="center">
            <font color=white>
            	<b><bean:message key="your.subNember"/>:${sub.subNumber}<b>
            </font> 
            </div>
          </td>
        </tr>
        <tr class="bodybg">
          <td colspan="2">
            <table border="1px" cellspacing="0" cellpadding="0" width="100%" class="bodybg" style="border-collapse: collapse;">
              <tr> 
                <td width="45%" height="28"><bean:message key="dateStr"/>：<fmt:formatDate value="${sub.subDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <td width="55%" height="28"><bean:message key="User"/>：${sub.userName}</td>
              </tr>
              <tr> 
                <td height="28"><bean:message key="orderer"/>：${sub.orderName}</td>
                <td height="28"><bean:message key="address"/>：${sub.subAdds}</td>
              </tr>
              <tr> 
                <td height="28" height="28"><bean:message key="address"/>：${sub.subTel}</td>
                <td height="28" height="28">E-mail：${sub.subMail}</td>
              </tr>
              <tr> 
                <td  colspan="2">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="20%" height="28"><bean:message key="other.info"/>：</td>
                      <td width="80%" height="28">${sub.other}</td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
<table border="0" width="500" cellspacing="0" cellpadding="0" align="center" >
  <tr> 
    <td align="center" colspan="2" height="5">
    </td>
  </tr>

  <tr> 
    <td height="28" align="center" class="titlebg" >
    <div>
   <font color="white"><b><bean:message key="product.subscribed.list"/></b></font>
   </div>
    </td>
  </tr>

<tr> 
    <td colspan="2">
    <table width="100%" cellspacing="0" border="0" cellpadding="0">
        <tr class="bodybg"> 
          <td>

            <table width="100%" border="1" cellpadding="0" cellspacing="0" class="bodybg" style="border-collapse: collapse;">
              <tr>
                <td width="30%" height="25"> <div align="center"><bean:message key="product.name"/></div></td>
                <td width="12%"> <div align="center"><bean:message key="product.price"/></div></td>
                <td width="15%"> <div align="center"><bean:message key="product.number"/></div></td>
                <td width="15%"> <div align="center"><bean:message key="dateStr"/></div></td>
                <td width="18%"> <div align="center"><bean:message key="price.total"/></div></td>
              </tr>
              <c:forEach items="${requestScope.baskets}" var="basket">
              <tr> 
                <td height="25"> <div align="center"><a target="_blank" href="${root}/views/${basket.hwId}"><font color="#FF0000">${basket.hwName}</font></a></div></td>
                <td height="25"> <div align="center"> 
                    ${basket.hwCash}</div></td>
                <td width="65" height="25"> <div align="center">${basket.basketCount} 
                  </div></td>
                <td height="25"> <div align="center">${basket.basketDate}
                  </div></td>
                
                <td height="25"> <div align="center"> 
                  <fmt:formatNumber type="currency" value="${basket.total}"/> </div></td>
 		</c:forEach>
              <tr> 
             
                <td height="28" colspan="4"><div align="center"><bean:message key="price.total"/>:</div></td>
                <td height="28"><div align="center"><fmt:formatNumber type="currency" value="${requestScope.totalcash}"/>
                  </div></td>
            </table>
     </td>
        </tr>
      </table>
      </td>
  </tr>
</table>
</body>
</html>