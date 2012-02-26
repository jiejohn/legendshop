<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html>
<head>
<title>menu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/theme/skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/theme/skin/css/menu.css" type="text/css" />
<script language='javascript'>var curopenItem = '1';</script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/theme/skin/js/frame/menu.js"></script>
<base target="main" />
</head>
<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">
	<!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>用户管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
          <!-- 
            <li>
              <div class='items'>
                <div class='fllct'><a href='userMessage.jsp' target='main'>登录用户管理</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/system/userDetail/query${applicationScope.WEB_SUFFIX}' target='main'><img src='skin/images/frame/gtk-sadd.png' alt='登录用户管理' title='登录用户管理'/></a> </div>
              </div>
            </li>
              -->
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/system/userDetail/query${applicationScope.WEB_SUFFIX}'  target='main'>用户信息管理</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/system/userDetail/query${applicationScope.WEB_SUFFIX}'  target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='用户信息管理' title='用户信息管理'/></a> </div>
              </div>
            </li>
           <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/loginHistory/query${applicationScope.WEB_SUFFIX}' target='main'>用户登录历史</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/loginHistory/query${applicationScope.WEB_SUFFIX}' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='用户登录历史' title='用户登录历史'/></a> </div>
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/loginHistory/sum${applicationScope.WEB_SUFFIX}'  target='main'>登录历史统计</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/loginHistory/sum${applicationScope.WEB_SUFFIX}'  target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='登录历史统计' title='登录历史统计'/></a> </div>
              </div>
            </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      <auth:auth ifAllGranted="F_VIEW_ALL_DATA">
       <dl class='bitem'>
        <dt onClick='showHide("items1_3")'><b>权限管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_3'>
          <ul class='sitemu'>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/member/user/usersList${applicationScope.WEB_SUFFIX}' target='main'>用户管理</a></div>
                <div class='flrct'><a href='${pageContext.request.contextPath}/member/user/usersList${applicationScope.WEB_SUFFIX}' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='用户管理' title='用户管理'/></a> </div>
              </div>
            </li>
             <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/member/right/findAllRole${applicationScope.WEB_SUFFIX}' target='main'>角色管理</a></div>
                <div class='flrct'><a href='${pageContext.request.contextPath}/member/right/findAllRole${applicationScope.WEB_SUFFIX}' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='角色管理' title='角色管理'/></a> </div>
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/member/right/listFunction${applicationScope.WEB_SUFFIX}' target='main'>权限管理</a></div>
                <div class='flrct'><a href='${pageContext.request.contextPath}/member/right/listFunction${applicationScope.WEB_SUFFIX}' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='权限管理' title='权限管理'/></a> </div>
              </div>
            </li>
         </ul>
        </dd>
      </dl> 
      </auth:auth> 
	  </td>
  </tr>
</table>
</body>
</html>