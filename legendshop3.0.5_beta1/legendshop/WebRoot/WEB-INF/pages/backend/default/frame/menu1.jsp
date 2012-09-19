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
        <dt onClick='showHide("items1_2")'><b>首页管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_2'>
          <ul class='sitemu'>
           <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/dashboard' target='main'>首页管理</a></div>
                <div class='flrct'><a href='${pageContext.request.contextPath}/admin/dashboard' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='首页管理' title='首页管理'/></a> </div>
              </div>
            </li>
          </ul>
        </dd>
      </dl> 
    
	<!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>商品管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
             <li>
              <div class='items'>
                <div class='fllct'><a href='<ls:url address='/admin/product/query'/>'   target='main'>商品管理</a></div>
                <div class='flrct'> <a href='<ls:url address='/admin/product/query'/>'  target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='商品管理' title='商品管理'/></a> </div>
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/sort/query'  target='main'>类型管理</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/sort/query'  target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='商品类型管理' title='商品类型管理'/></a> </div>
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/brand/query' target='main'>品牌管理</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/brand/query' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='商品品牌管理' title='商品品牌管理'/></a> </div>
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/userComment/query?status=0' target='main'>消息管理</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/userComment/query?status=0' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='消息管理' title='消息管理'/></a> </div>
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/indexjpg/query'  target='main' title="主页上的轮换图片广告管理">图片管理</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/indexjpg/query'  target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='主页图片管理' title='主页图片管理'/></a> </div>
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='<ls:url address='/admin/hotsearch/query'/>' target='main'>热门商品</a></div>
                <div class='flrct'> <a href='<ls:url address='/admin/hotsearch/query'/>' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='热门商品' title='热门商品'/></a> </div>
              </div>
            </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/productcomment/query' target='main'>评论管理</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/productcommentadmin/productcomment/query' target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='评论管理' title='评论管理'/></a> </div>
              </div>
            </li>
         </ul>
        </dd>
      </dl>
       
 <dl class='bitem'>
        <dt onClick='showHide("items1_3")'><b>团购管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_3'>
          <ul class='sitemu'>
           <li>
              <div class='items'>
                <div class='fllct'><a href='${pageContext.request.contextPath}/admin/group/product/query'   target='main'>团购管理</a></div>
                <div class='flrct'> <a href='${pageContext.request.contextPath}/admin/group/product/query'  target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='团购管理' title='团购管理'/></a> </div>
              </div>
            </li>
          </ul>
        </dd>
      </dl> 
        
        
           
     <dl class='bitem'>
        <dt onClick='showHide("items1_4")'><b>订单管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_4'>
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