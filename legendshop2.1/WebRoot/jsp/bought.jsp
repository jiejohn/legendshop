<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
<tr> 
    <td colspan="2">
    <table width="100%" cellspacing="0" border="0" cellpadding="0">
        <tr> 
          <td bgcolor="#ECECEC">
		    <div align="center">
              <center> 
            <table width="500" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
              <tr>
                <td width="30%" height="25"> <div align="center"><bean:message key="product.name"/>(<bean:message key="product.name.delete"/>)</div></td>
                <td width="12%"> <div align="center"><bean:message key="product.price"/></div></td>
                <td width="15%"> <div align="center"><bean:message key="product.number"/></div></td>
                <td width="15%"> <div align="center"><bean:message key="dateStr"/></div></td>
                <td width="18%"> <div align="center"><bean:message key="price.total"/></div></td>
              </tr>
              <c:forEach items="${requestScope.baskets}" var="basket">
              <tr> 
                <td height="25"> 
                	<div align="center">
                	   <a target="_blank" href="${root}/views/${basket.hwId}" title="detail"><font color="#FF0000">${basket.hwName}</font></a>
                	   <a href="${root}/clear.do?basketId=${basket.basketId}" title="delete"><font color="#FF0000">(X)</font></a>
                	</div>
                </td>
                <td height="25"> <div align="center"> 
                    ${basket.hwCash}</div></td>
                <td width="65" height="25"> <div align="center">${basket.basketCount} 
                  </div></td>
                <td height="25"> <div align="center">${basket.basketDate}
                  </div></td>
                
                <td height="25"> <div align="center"> 
                  <fmt:formatNumber type="currency" value="${basket.total}"/>
                  &nbsp;
                  </div></td>
 		</c:forEach>
              <tr> 
             
                <td height="25" colspan="4"><div align="center"><bean:message key="price.total"/>:</div></td>
                <td height="25"><div align="center"><fmt:formatNumber type="currency" value="${requestScope.totalcash}"/>
                  &nbsp;</div></td>
            </table>

              </center>
          </div>
     </td>
        </tr>
      </table>
      </td>
  </tr>