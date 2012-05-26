<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<%@page import="com.legendshop.core.constant.ParameterEnum"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
     <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
     <script src="<ls:templateResource item='/css/alternative.js'/>" type="text/javascript"></script>

	<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
    <script type="text/javascript" src="<ls:templateResource item='/common/js/linked-select.js'/>"></script>
		
		

<title>类型列表</title>
</head>
<body class="bodymargin">
<%
			Integer offset = (Integer)request.getAttribute("offset");
	%>
<form action="<ls:url address='/admin/group/product/query'/>" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th style="font-weight: normal;"><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 团购管理 &raquo; <a href="<ls:url address='/admin/group/product/query'/>">团购管理</a></th></tr>
    </thead>
    <tbody>
    	<tr><td>
    	    <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}"/>
			<div align="left" style="padding: 5px;">
		    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
               商城名称&nbsp;
            <input type="text" name="userName" maxlength="50" value="${prod.userName}" size="15"/>
            </auth:auth>
			<auth:auth ifNotGranted="F_VIEW_ALL_DATA">
<!-- 			商品类型&nbsp;<select id="sortId" name="product.sortId" onChange="changeNsort(this.value)"> -->
			商品类型&nbsp;<select id="sortId" name="product.sortId">
			<c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null && sessionScope.SPRING_SECURITY_LAST_USERNAME !=''}">
	            <option:optionGroup type="select" required="false" cache="fasle"
	                defaultDisp="-- 一级类型 --" 
	                hql="select t.sortId, t.sortName from Sort t where t.sortType = 'G' and t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}"/>
             </c:if>
		</select><%-- 
		<select id="nsortId" name="product.nsortId" onChange="changeSubNsort(this.value)">
			<option value="">-- 二级类型 --</option>
		</select>  
		 <select id="subNsortId" name="product.subNsortId">
            <option value="">-- 三级类型 --</option>
        </select> --%>
          &nbsp;品牌
        <select id="brandId" name="product.brandId">
        	   <option:optionGroup type="select" required="false" cache="fasle"
                    hql="select t.brandId, t.brandName from Brand t where t.userName = ?" defaultDisp="--商品品牌--" 
                    param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" />
        </select>    
        </auth:auth>  
        </div><div align="left" style="padding: 5px">
			商品名称
			<input type="text" name="product.name" id="name" maxlength="50" value="${prod.name}" size="32"/>
			状态
			<select id="status" name="product.status">
					<option value="">请选择</option>	
			    	<option value="1">上线</option>
	      			<option value="0" >下线</option>
			</select>		
			<input type="submit" value="搜索"/>
		   <input type="button" value="创建团购" onclick='window.location="<ls:url address='/admin/group/product/load'/>"'/>
			</div>
    	</td></tr>
    </tbody>
    </table>
	</form>	
	
	
	<div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/product/query${applicationScope.WEB_SUFFIX}" id="item"
         export="true"  class="${tableclass}" style="width:100%" sort="external">
      <display:column style="width:70px" title="<input type='checkbox'  id='checkbox' name='checkbox' onClick='javascript:selAll()' />顺序"><input type="checkbox" name="strArray" value="${item.prodId}" arg="${item.name}"/><%=offset++%></display:column>
      <display:column title="商品名称" sortable="true" sortName="name">
      	<a href= "${pageContext.request.contextPath}/views/${item.prodId}${applicationScope.WEB_SUFFIX}" target="_blank"> 
   		 ${item.name}</a>
      </display:column>
      <display:column title="原价" property="price" sortable="true" sortName="price"></display:column>
      <display:column title="现价" property="cash" sortable="true" sortName="cash"></display:column>
      <%
      	if(PropertiesUtil.getObject(ParameterEnum.VISIT_HW_LOG_ENABLE, Boolean.class)){
      %>
      <display:column title="查看数" property="views" sortable="true" sortName="views"></display:column>
      <%
      	}
      %>
      <display:column title="订购数" property="buys" sortable="true" sortName="buys"></display:column>
      <display:column title="库存" property="stocks" sortable="true" sortName="stocks"></display:column>
      <display:column title="实际库存" property="actualStocks" sortable="true" sortName="actualStocks"></display:column>
     <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
     <display:column title="商城名称" property="userName" sortable="true" sortName="userName"></display:column>
     </auth:auth>
     
      <auth:auth ifAnyGranted="F_OPERATOR">
	      <display:column title="操作" media="html" style="width: 80px;">
		  	<c:choose>
		  		<c:when test="${item.status == 1}">
		  			<a href='javascript:productTurnOff("${item.prodId}","${item.name}")'>下线</a>
		  		</c:when>
		  		<c:otherwise>
		  			<a href='javascript:productTurnOn("${item.prodId}","${item.name}")'><font color="red">上线</font></a>
		  		</c:otherwise>
		  	</c:choose>
		  	 <a href="<ls:url address='/admin/group/product/load/${item.prodId}'/>" title="修改"><img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/> "></a>
		     <a href='javascript:confirmDelete("${item.prodId}","${item.name}")' title="删除"><img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/> "></a>
	      </display:column>
      </auth:auth>
    </display:table>
    <input type="button" value="刪除" style="float: left" onclick="return deleteAction();"/> 
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
	<table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 建立商品的五个步骤：1.商品详细信息 2.商品相关说明图片 3.动态属性 4.动态参数  5.相关商品<br>
   1. 商品可以挂在一级，二级，三级类型上<br>
   2. 商品有一个对应的品牌，品牌对应三级类型<br>
   3. 商品状态，在开始时间和结束时间之内有效，失效后包括下线状态将不会在前台显示，推荐状态为是则在首页精品推荐中出现<br>
   4. 价格、运费、库存量为正数，不填写则不在前台显示，如果填写了库存量为0则无法订购<br>
   5. 库存量在用户下单时会减少，实际库存量在订单成交时减少<br>
   6. <img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/> "> 修改按钮<br>
   7. <img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/> "> 删除按钮<br>
   </td><tr></table> 
   
   <script language="JavaScript" type="text/javascript">
<!--

 function deleteAction()
{
	//获取选择的记录集合
	selAry = document.getElementsByName("strArray");
	if(!checkSelect(selAry)){
	 window.alert('删除时至少选中一条记录！');
	 return false;
	}
	if(confirm("确定要删除吗？")){
	for(i=0;i<selAry.length;i++){
	  if(selAry[i].checked){
		var prodId = selAry[i].value;
		var name = selAry[i].getAttribute("arg");
			deleteProduct(prodId,name);
		 }
		}
	}
	return true;
}

  function confirmDelete(prodId,name)
	{
	    if(confirm("确定要删除 "+name+" 吗？")){
	   		deleteProduct(prodId,name);
	   }
	}
	
  function deleteProduct(prodId,name) {
        CommonService.deleteProduct(prodId, function(retData){
	       if(retData == null ){
	          window.location.reload() ;
	       }else{
	          alert("删除 " + name + " 失败！") ;
	       }
	       
	    }) ;
}

  function productTurnOn(prodId,name) {
	  if(confirm('确定商品 '+name+' 上线?')){
        CommonService.productTurnOn(prodId, function(retData){
        
	       if(retData == null ){
	          alert("成功上线！") ;
	          window.location.reload() ;
	       }else{
	          alert("上线失败！") ;
	       }
	       
	    }) ;
    }
}

  function productTurnOff(prodId,name) {
	  if(confirm('确定商品 '+name+' 下线?')){
        CommonService.productTurnOff(prodId, function(retData){
        
	       if(retData == null ){
	          alert("成功下线！") ;
	          window.location.reload() ;
	       }else{
	          alert("下线失败！") ;
	       }
	       
	    }) ;
    }
}

  function changeNsort(sortId) {
  		DWREngine.setAsync(false);
		if(sortId!=null &&sortId!=''){
				var sql ="select nsort_id id, nsort_name name from ls_nsort where sort_id = '"+sortId+"' and parent_nsort_id is null";
		        changeLinkedOptions1("nsortId", false, sql,"-- 二级类型 --");
	        }
	        DWREngine.setAsync(true); 
        }
        
       function changeSubNsort(nsortId) {
        DWREngine.setAsync(false);
        if(nsortId!=null &&nsortId!=''){
                var sql ="select nsort_id id, nsort_name name from ls_nsort where parent_nsort_id = '"+nsortId+"'";
                changeLinkedOptions1("subNsortId", false, sql,"-- 三级类型 --");
                
            }
            DWREngine.setAsync(true); 
        }
        
       function initSelect(sortIdValue,nsortIdValue,subNsortIdValue,brandIdValue){
			//设置商品类型当前值
			DWRUtil.setValues({sortId:sortIdValue});
			changeNsort(sortIdValue);
			DWRUtil.setValues({nsortId:nsortIdValue});
			changeSubNsort(nsortIdValue);
			DWRUtil.setValues({subNsortId:subNsortIdValue});
			DWRUtil.setValues({brandId:brandIdValue});
			}
			
	       function initStatus(commendValue,statusValue){
			DWRUtil.setValues({commend:commendValue});
			DWRUtil.setValues({status:statusValue});
			}		
			
			
		window.onload=function(){
		 var sortId = '${prod.sortId}';
		 var nsortId = '${prod.nsortId}';
		 var subNsortId = '${prod.subNsortId}';
		 var commend = '${prod.commend}';
		 var status = '${prod.status}';
		 var brandId = '${prod.brandId}';
		 if(sortId!=null ||sortId!=''){
		 	initSelect(sortId,nsortId,subNsortId,brandId);
		 }
		 initStatus(commend,status);
		}
		
		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
		
		    highlightTableRows("item");  
//-->
</script>
</body>
</html>

