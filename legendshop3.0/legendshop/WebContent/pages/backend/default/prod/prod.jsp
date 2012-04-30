<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<html>
<head>
<title>创建商品类型</title>

		<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
		<script src="<ls:templateResource item='/plugins/My97DatePicker/WdatePicker.js'/>" type="text/javascript"></script>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
		<script src="<ls:templateResource item='/common/js/linked-select.js'/>" type="text/javascript"></script>
<script language="javascript">
	window.onload=function(){
		 var sortId = '${prod.sortId}';
		 var nsortId = '${prod.nsortId}';
		 var subNsortId = '${prod.subNsortId}';
		 var brandId = '${prod.brandId}';
		 if(sortId!=null ||sortId!=''){
		 	initSelect(sortId,nsortId,subNsortId,brandId);
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
		var carriage = document.getElementById("carriage");
	    if((carriage.value != null || carriage.value!='') && isNaN(carriage.value)){
			alert("商品运费必须为数字!");
			carriage.focus();
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
<form action="<ls:url address='/admin/product/save'/>"  id="saveProdForm" name="saveProdForm" method="post" enctype="multipart/form-data" onsubmit="return checkform()">
    <input type="hidden" value="next" id="nextAction" name="nextAction"/>
    <input type="hidden" id="prodId" name="prodId" value="${prod.prodId}">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="<ls:url address='/admin/product/query'/>">商品管理</a> &raquo; <a href="${pageContext.request.contextPath}/admin/product/load${applicationScope.WEB_SUFFIX}">创建商品</a>
    		<c:if test="${prod.name != null}">&raquo; <a href= "${pageContext.request.contextPath}/views/${prod.prodId}${applicationScope.WEB_SUFFIX}" target="_blank">${prod.name}</a></c:if>
    	</td></tr>
    </thead>
    <tr class="odd"><td>
    	<jsp:include page="/admin/product/createsetp${applicationScope.WEB_SUFFIX}">
    		<jsp:param name="step" value="1"/>
    	</jsp:include>
    	</td></tr>
    </table>
  <table class="${tableclass}" style="width: 100%;" id="col1">
   <thead>
    <tr>
      <td colspan="3">
      <div align="center">
		<b>商品详细信息</b>   
      </div></td>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td width="120px"><div align="center">选择商品类型<font color="#ff0000">*</font></div></td>
      <td width="48%">
      <div>
		<select id="sortId" name="sortId" onChange="changeNsort(this.value)">
            <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                <c:if test="${prod.userName != null}">
                  <option:optionGroup type="select" required="false" cache="fasle"
                    beanName="Sort" beanId="sortId" beanDisp="sortName" defaultDisp="--商品大类--" 
                    hql="select t.sortId, t.sortName from Sort t where t.sortType = 'P' and t.userName = ?" param="${prod.userName}" />
                </c:if>
                <c:if test="${prod.userName == null}">
                    <option value="">-- 一级类型 --</option>
                </c:if>
            </auth:auth>
            <auth:auth ifNotGranted="F_VIEW_ALL_DATA">
            <c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null && sessionScope.SPRING_SECURITY_LAST_USERNAME !=''}">
            <option:optionGroup type="select" required="false" cache="fasle"
                defaultDisp="- 一级类型 -" 
                hql="select t.sortId, t.sortName from Sort t where t.sortType = 'P' and  t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}"/>
             </c:if>
            </auth:auth>
		</select>    
		<select id="nsortId" name="nsortId" onChange="changeSubNsort(this.value)">
            <option value="">-- 二级类型 --</option>
        </select>  
         <select id="subNsortId" name="subNsortId">
            <option value="">-- 三级类型 --</option>
        </select> 
        &nbsp;品牌
        <select id="brandId" name="brandId">
        	   <option:optionGroup type="select" required="false" cache="fasle"
                    hql="select t.brandId, t.brandName from Brand t where t.userName = ?" defaultDisp="--商品品牌--" 
                    param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" />
        </select>
        </div><div style="margin-top: 3px">
        还没有商品类型？请先&nbsp;<a href="${pageContext.request.contextPath}/admin/sort/load${applicationScope.WEB_SUFFIX}">创建类型</a>&nbsp;<a href="${pageContext.request.contextPath}/admin/brand/load${applicationScope.WEB_SUFFIX}">创建品牌</a>
		</div></td>
      <td width="25%" rowspan="9" align="center" valign="middle" style="margin: 0px">
      	<c:choose>
      		<c:when test="${prod.pic!=null}"><a href="${pageContext.request.contextPath}/photoserver/photo/${prod.pic}" target="_blank">
      		<img src="${pageContext.request.contextPath}/photoserver/photo/${prod.pic}" height="235px" width="320px"/></a>
      		</c:when>
      		<c:otherwise>请上传商品图片</c:otherwise>
      	</c:choose>
      	
      	</td>
    </tr>
    
    <tr>
      <td width="120px"><div align="center">商品名称<font color="#ff0000">*</font></div></td>
      <td>
			<input type="text" name="name" id="name" size="50" value="${prod.name}" maxlength="120"/></td>
    </tr>
    <tr>
      <td width="120px"><div align="center">商品型号</div></td>
      <td>
			<input type="text" name="modelId" id="modelId" size="50" value="${prod.modelId}" maxlength="50"/></td>
    </tr>
    <tr>
      <td width="120px"><div align="center">关键字</div></td>
      <td>
			<input type="text" name="keyWord" id="keyWord" size="50" value="${prod.keyWord}" maxlength="100"/></td>
    </tr> 
     <tr>
      <td width="120px"><div align="center">价格</div></td>
      <td>
			原价&nbsp;<input type="text" name="price" id="price" size="10" value="${prod.price}" maxlength="15" />&nbsp;
            现价&nbsp;<input type="text" name="cash" id="cash" size="10" value="${prod.cash}" maxlength="15"/>&nbsp;
           运费&nbsp;<input type="text" name="carriage" id="carriage" size="10" value="${prod.carriage}" maxlength="15"/>
		</td>
    </tr>    
   <tr>
      <td width="120px"><div align="center">库存量</div></td>
      <td>
			<input type="text" name="stocks" id="stocks" size="15" value="${prod.stocks}" maxlength="15"/>      </td>
    </tr>
    <tr>
      <td width="120px"><div align="center">类型</div></td>
      <td>
		       <select id="prodType" name="prodType" style="display: none">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="PRODUCT_TYPE" selectedValue="${prod.prodType}"/>
	            </select>&nbsp;

	        是否推荐到首页<select id="commend" name="commend">
      		<c:choose>
      			<c:when test="${prod.commend == 'Y'}">
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
			<input readonly="readonly" name="startDate" id="startDate" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${prod.startDate}" pattern="yyyy-MM-dd"/>' />
			&nbsp;
			结束时间
			<input readonly="readonly"  name="endDate" id="endDate" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${prod.endDate}" pattern="yyyy-MM-dd"/>' />
	  </td>
    </tr> 
    <tr>
      <td width="120px"><div align="center">商品图片<font color="#ff0000">*</font></div></td>
      <td><input type="file" name="file" id="file" size="30"/>
      	<input type="hidden" name="pic" id="pic" size="30" value="${prod.pic}"/>图片分辨率640×470,大小不能超过1M</td>
    </tr>
     <tr>
      <td width="120px"><div align="center">简要描述</div></td>
      <td colspan="2"><textarea style="width:100%;height:70" name="brief" id="brief" >${prod.brief}</textarea></td>
    </tr>    
    <auth:auth ifAnyGranted="F_OPERATOR">
      <tr>
      <td width="14%"><div align="center">详细描述</div></td>
      <td colspan="2">
			<FCK:editor instanceName="content" height="600px" basePath="/plugins/fckeditor">
                <jsp:attribute name="value">${prod.content}</jsp:attribute>
            </FCK:editor>  
	 </td>
    </tr>
    </auth:auth>
    <tr>
      <td colspan="3"><div align="center">
        <auth:auth ifAnyGranted="F_OPERATOR"> 
            <input type="button" value="下一步" onclick="javascript:submitNext()"/>
            <input type="button" value="完成" onclick="javascript:submitFinish()"/>
        </auth:auth>  
        <input type="reset" value="重置"/>
		<input type="button" value="返回" onclick='window.location="<ls:url address='/admin/product/query'/>"'/>
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
   </td><tr></table> 
</form>
		</body>
</html>