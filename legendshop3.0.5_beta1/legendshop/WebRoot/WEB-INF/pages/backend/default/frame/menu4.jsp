<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
<head>
<title>menu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/theme/skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/theme/skin/css/menu.css" type="text/css" />
<script language='javascript'>var curopenItem = '1';</script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/theme/skin/js/frame/menu.js"></script>
</head>
<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">
     <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>订单管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
           <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/order/processing' target='main'>未处理订单</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/order/processing' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='未处理订单' title='未处理订单'/></a> </div>
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/order/processed' target='main'>已成交订单</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/order/processed' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='已成交订单' title='已成交订单'/></a> </div>
              </div>
            </li>
          </ul>
        </dd>
      </dl> 
       
     </td>
  </tr>
</table>
</body>
</html>