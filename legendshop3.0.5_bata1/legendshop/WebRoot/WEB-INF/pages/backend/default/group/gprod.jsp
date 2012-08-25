<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<html>
<head>
<title>创建团购商品类型</title>

		<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
		<script src="<ls:templateResource item='/plugins/My97DatePicker/WdatePicker.js'/>" type="text/javascript"></script>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
		<script src="<ls:templateResource item='/common/js/linked-select.js'/>" type="text/javascript"></script>
<script language="javascript">
	window.onload=function(){
		 var sortId = '${prod.product.sortId}';
		 var nsortId = '${prod.product.nsortId}';
		 var subNsortId = '${prod.product.subNsortId}';
		 var brandId = '${prod.product.brandId}';
		 if(sortId!=null ||sortId!=''){
		 	initSelect(sortId,nsortId,subNsortId,brandId);
		 }
		}
 
  function changeNsort(sortId) {
  		dwr.engine.setAsync(false);
		if(sortId!=null &&sortId!=''){
				var sql ="select nsort_id id, nsort_name name from ls_nsort where sort_id = '"+sortId+"' and parent_nsort_id is null";
		        changeLinkedOptions1("nsortId", false, sql,"-- 二级类型 --");
	        }
	        dwr.engine.setAsync(true); 
        }
        
       function changeSubNsort(nsortId) {
        dwr.engine.setAsync(false);
        if(nsortId!=null &&nsortId!=''){
                var sql ="select nsort_id id, nsort_name name from ls_nsort where parent_nsort_id = '"+nsortId+"'";
                changeLinkedOptions1("subNsortId", false, sql,"-- 三级类型 --"); 
            }
            dwr.engine.setAsync(true); 
        }
        
        
   function initSelect(sortIdValue,nsortIdValue,subNsortIdValue,brandIdValue){
			//设置商品类型当前值
            dwr.util.setValues({sortId:sortIdValue});
            changeNsort(sortIdValue);
            dwr.util.setValues({nsortId:nsortIdValue});
            changeSubNsort(nsortIdValue);
            dwr.util.setValues({subNsortId:subNsortIdValue});
             dwr.util.setValues({brandId:brandIdValue});
			}	
			
     
    jQuery(document).ready(function() {
	      jQuery("#col1 tr").each(function(i){
	      if(i>0){
	         if(i%2 == 0){
	             jQuery(this).addClass('even');
	         }else{    
	              jQuery(this).addClass('odd'); 
	         }   
	    }
	     });   
	      jQuery("#col1 th").addClass('sortable'); 
	});
	
	function checkform() {
	var content = document.getElementById("content");
      var sortId = document.getElementById("sortId");
	  if(sortId.value == null || sortId.value == '')
		{
			alert("请选择类型名称!");
			sortId.focus();
			return false;
		}
		var name = document.getElementById("name");
		if(name.value==null||name.value=='')
		{
			alert("请输入商品名称!");
			name.focus();
			return false;
		}

		var price = document.getElementById("price");
		if((price.value != null || price.value!='') && isNaN(price.value)){
			alert("商品原价必须为数字!");
			price.focus();
			return false;
		}
		var cash = document.getElementById("cash");
        if((cash.value != null || cash.value!='') && isNaN(cash.value)){
			alert("商品价格必须为数字!");
			cash.focus();
			return false;
		}
		
		var stocks = document.getElementById("stocks");
	        if((stocks.value != null || stocks.value!='') && isNaN(stocks.value)){
			alert("库存量必须为数字!");
			cash.focus();
			return false;
		}	
	  var file = document.getElementById("file");
	  if(file.value==null||file.value=='')
		{
		var pic = document.getElementById("pic");
		if(pic.value==null||pic.value==''){
			alert("请上传商品图片!");
			file.focus();
			return false;
		}
		}			
		return true ;
  }
  
  function submitNext(){
  	var saveProdForm = document.getElementById("saveProdForm");
  	if(checkform()){
  		saveProdForm.submit();
  	}
  }
  
  function submitFinish(){
    	var saveProdForm = document.getElementById("saveProdForm");
    	document.getElementById("nextAction").value = "success";
  		  	if(checkform()){
  		saveProdForm.submit();
  	}
  }
  
</script>
</head>
<body>
<form action="<ls:url address='/admin/group/product/save'/>"  id="saveProdForm" name="saveProdForm" method="post" enctype="multipart/form-data" onsubmit="return checkform()">
    <input type="hidden" value="next" id="nextAction" name="nextAction"/>
    <input type="hidden" id="prodId" name="product.prodId" value="${prod.product.prodId}">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="<ls:url address='/admin/group/product/query'/>">团购管理</a>
    	 &raquo; <a href="<ls:url address='/admin/group/product/load'/>">创建团购</a>
    		<c:if test="${prod.product.name != null}">&raquo; <a href= "<ls:url address='/views/${prod.product.prodId}'/>" target="_blank">${prod.product.name}</a></c:if>
    	</td></tr>
    </thead>
    </table>
  <table class="${tableclass}" style="width: 100%;" id="col1">
   <thead>
    <tr>
      <td colspan="3">
      <div align="center">
		<b>团购详细信息</b>   
      </div></td>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td width="120px"><div align="center">选择团购类型<font color="#ff0000">*</font></div></td>
      <td width="48%">
      <div>
<!-- 		<select id="sortId" name="product.sortId" onChange="changeNsort(this.value)"> -->
		<select id="sortId" name="product.sortId" >
            <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                <c:if test="${prod.product.userName != null}">
                  <option:optionGroup type="select" required="false" cache="fasle"
                    beanName="Sort" beanId="sortId" beanDisp="sortName" defaultDisp="--商品大类--" 
                    hql="select t.sortId, t.sortName from Sort t where t.sortType = 'G' and t.userName = ?" param="${prod.product.userName}" />
                </c:if>
                <c:if test="${prod.product.userName == null}">
                    <option value="">-- 一级类型 --</option>
                </c:if>
            </auth:auth>
            <auth:auth ifNotGranted="F_VIEW_ALL_DATA">
            <c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null && sessionScope.SPRING_SECURITY_LAST_USERNAME !=''}">
            <option:optionGroup type="select" required="false" cache="fasle"
                defaultDisp="- 一级类型 -" 
                hql="select t.sortId, t.sortName from Sort t where  t.sortType = 'G' and t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}"/>
             </c:if>
            </auth:auth>
		</select>    
<%-- 		<select id="nsortId" name="product.nsortId" onChange="changeSubNsort(this.value)">
            <option value="">-- 二级类型 --</option>
        </select>  
         <select id="subNsortId" name="product.subNsortId">
            <option value="">-- 三级类型 --</option>
        </select>  --%>
        &nbsp;品牌
        <select id="brandId" name="product.brandId">
        	   <option:optionGroup type="select" required="false" cache="fasle"
                    hql="select t.brandId, t.brandName from Brand t where t.userName = ?" defaultDisp="--商品品牌--" 
                    param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}"  selectedValue="${prod.product.brandId}"/>
        </select>
        </div><div style="margin-top: 3px">
        还没有商品类型？请先&nbsp;<a href="${pageContext.request.contextPath}/admin/sort/load">创建类型</a>&nbsp;<a href="${pageContext.request.contextPath}/admin/brand/load">创建品牌</a>
		</div></td>
      <td width="25%" rowspan="9" align="center" valign="middle" style="margin: 0px">
      	<c:choose>
      		<c:when test="${prod.product.pic!=null}"><a href="${pageContext.request.contextPath}/photoserver/photo/${prod.product.pic}" target="_blank">
      		<img src="${pageContext.request.contextPath}/photoserver/photo/${prod.product.pic}" height="235px" width="320px"/></a>
      		</c:when>
      		<c:otherwise>请上传商品图片</c:otherwise>
      	</c:choose>
      	
      	</td>
    </tr>
    
    <tr>
      <td width="120px"><div align="center">商品名称<font color="#ff0000">*</font></div></td>
      <td>
			<input type="text" name="product.name" id="name" size="50" value="${prod.product.name}" maxlength="120"/></td>
    </tr>
    <tr>
      <td width="120px"><div align="center">商品型号</div></td>
      <td>
			<input type="text" name="product.modelId" id="modelId" size="50" value="${prod.product.modelId}" maxlength="50"/></td>
    </tr>
    <tr>
      <td width="120px"><div align="center">关键字</div></td>
      <td>
			<input type="text" name="product.keyWord" id="keyWord" size="50" value="${prod.product.keyWord}" maxlength="100"/></td>
    </tr> 
     <tr>
      <td width="120px"><div align="center">数量限制</div></td>
      <td>
			最低数量&nbsp;<input type="text" name="salesMin" id="salesMin" size="10" value="${prod.salesMin}" maxlength="15" />&nbsp;
            最高数量&nbsp;<input type="text" name="salesMax" id="salesMax" size="10" value="${prod.salesMax}" maxlength="15"/>&nbsp;
            每人限购&nbsp;<input type="text" name="maxBuys" id="maxBuys" size="10" value="${prod.maxBuys}" maxlength="15"/>
		</td>
    </tr> 
    <tr>
      <td width="120px"><div align="center">价格</div></td>
      <td>
			原价&nbsp;<input type="text" name="product.price" id="price" size="10" value="${prod.product.price}" maxlength="15" />&nbsp;
            现价&nbsp;<input type="text" name="product.cash" id="cash" size="10" value="${prod.product.cash}" maxlength="15"/>&nbsp;
           虚拟购买&nbsp;<input type="text" name="visualBuys" id="visualBuys" size="10" value="${prod.visualBuys}" maxlength="15"/>
		</td>
    </tr>   
   <tr>
      <td width="120px"><div align="center">库存量</div></td>
      <td>
			<input type="text" name="product.stocks" id="stocks" size="15" value="${prod.product.stocks}" maxlength="15"/>      </td>
    </tr>
    <tr>
      <td width="120px"><div align="center">类型</div></td>
      <td>
		       <select id="prodType" name="product.prodType" style="display: none">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="PRODUCT_TYPE" selectedValue="${prod.product.prodType}"/>
	            </select>&nbsp;

	        是否推荐到首页<select id="commend" name="product.commend">
      		<c:choose>
      			<c:when test="${prod.product.commend == 'Y'}">
      				<option value="Y" selected="selected">是</option>	
	      			<option value="N" >否</option>
      			</c:when>
      			<c:otherwise>
      			 	<option value="N" selected="selected">否</option>
      				<option value="Y">是</option>	
      			</c:otherwise>
      		</c:choose>
      		</select>       
       </td>
    </tr> 
    <tr>
      <td width="120px"><div align="center">有效时间</div></td>
      <td>
			开始时间
			<input readonly="readonly" name="product.startDate" id="startDate" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${prod.product.startDate}" pattern="yyyy-MM-dd"/>' />
			&nbsp;结束时间
			<input readonly="readonly"  name="product.endDate" id="endDate" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${prod.product.endDate}" pattern="yyyy-MM-dd"/>' />
			&nbsp;券有效期
			<input readonly="readonly"  name="couponEndTime" id="couponEndTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${prod.couponEndTime}" pattern="yyyy-MM-dd"/>' />
	  </td>
    </tr> 
    <tr>
      <td width="120px"><div align="center">商品图片<font color="#ff0000">*</font></div></td>
      <td><input type="file" name="product.file" id="file" size="30"/>
      	<input type="hidden" name="product.pic" id="pic" size="30" value="${prod.product.pic}"/>图片分辨率640×470,大小不能超过1M</td>
    </tr>
   <tr>
      <td width="120px"><div align="center">限制条件</div></td>
      <td colspan="2">
			    <select id="successCause" name="successCause">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="GROUP_SUCCESS_CAUSE" selectedValue="${prod.successCause}"/>
		        </select>
		        <select id="buyCondition" name="buyCondition">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="GROUP_BUY_CONDITION" selectedValue="${prod.buyCondition}"/>
		        </select>
	 </td>
    </tr>  
     <tr>
      <td width="120px"><div align="center">商户</div></td>
      <td colspan="2">
			    <select id="partnerId" name="partnerId">	                
	                <option:optionGroup type="select" required="false" cache="fasle"
                     defaultDisp="--请选择商户--"  
                    hql="select t.partnerId, t.partnerName from Partner t where t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" selectedValue="${prod.partnerId}"/>
		        </select>
	 </td>
    </tr> 
     <tr>
      <td width="120px"><div align="center">简要描述</div></td>
      <td colspan="2"><textarea style="width:100%;height:70" name="product.brief" id="brief" >${prod.product.brief}</textarea></td>
    </tr>    
    <auth:auth ifAnyGranted="F_OPERATOR">
      <tr>
      <td width="14%"><div align="center">详细描述</div></td>
      <td colspan="2">
			<FCK:editor instanceName="product.content" height="600px" basePath="/plugins/fckeditor">
                <jsp:attribute name="value">${prod.product.content}</jsp:attribute>
            </FCK:editor>  
	 </td>
    </tr>
    </auth:auth>
    <tr>
      <td colspan="3"><div align="center">
        <auth:auth ifAnyGranted="F_OPERATOR"> 
            <input type="button" value="完成" onclick="javascript:submitFinish()"/>
        </auth:auth>  
        <input type="reset" value="重置"/>
		<input type="button" value="返回" onclick='window.location="<ls:url address='/admin/group/product/query'/>"'/>
      </div></td>
    </tr>
    </tbody>
  </table>
   <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 带星号<font color="#ff0000">*</font>的属性务必要填写，如果不填写则不在页面展示。如价格和库存量等。<br>
   2. 商品类型：首先选择了一级类型，自动关联对应的二级类型，选择了二级类型，自动关联对应的三级类型<br>
   3. 是否推荐到首页如果选择是，则会在首页的精品推荐中出现<br>
   4. 商品在开始时间和时间结束之内有效，如果不填写则长期有效<br>
   5. 详细描述可以上传图片，编辑html元素
   6. 最低数量必须大于0，最高数量/每日限购：0 表示没最高上限 （产品数|人数 由成团条件决定）
   </td><tr></table> 
</form>
		</body>
</html>