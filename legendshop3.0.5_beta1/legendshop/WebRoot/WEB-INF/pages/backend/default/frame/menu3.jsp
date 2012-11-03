<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
<head>
<title>menu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/theme/skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/theme/skin/css/menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language='javascript'>var curopenItem = '1';</script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/theme/skin/js/frame/menu.js"></script>
</head>
<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">
	<!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>系统管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/system/lucene/query' target='main'>重建索引</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/system/lucene/query' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='重建Lucene索引' title='重建Lucene索引'/></a> </div>
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/system/systemParameter/query/sy' target='main'>系统配置</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/system/systemParameter/query/sy' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='系统配置' title='系统配置'/></a> </div>
              </div>
            </li>

            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/system/systemParameter/query/sh' target='main'>商城配置</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/system/systemParameter/query/sh' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='商城配置' title='商城配置'/></a> </div>
              </div>
            </li>
            
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/system/systemParameter/query/ma' target='main'>邮件配置</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/system/systemParameter/query/ma' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='邮件配置' title='邮件配置'/></a> </div>
              </div>
            </li>
            
           <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/system/systemParameter/query/lo' target='main'>日志配置</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/system/systemParameter/query/lo' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='日志配置' title='日志配置'/></a> </div>
              </div>
            </li>
            
           <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/system/plugin/query' target='main'>插件列表</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/system/plugin/query' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='插件列表' title='插件列表'/></a> </div>
              </div>
            </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
        <dl class='bitem'>
        <dt onClick='showHide("items1_2")'><b>日志管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_2'>
          <ul class='sitemu'>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/event/query' target='main'>系统日志</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/event/query' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='系统日志' title='系统日志'/></a> </div>
              </div>
            </li>
          </ul>
        </dd>
      </dl>
      
         <dl class='bitem'>
        <dt onClick='showHide("items1_3")'><b>缓存管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_2'>
          <ul class='sitemu'>
           <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/system/cache/query' target='main'>清空缓存</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/system/cache/query' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='清空缓存' title='清空缓存'/></a> </div>
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