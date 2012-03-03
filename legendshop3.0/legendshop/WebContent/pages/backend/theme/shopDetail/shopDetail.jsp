<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<html>
    <head>
        <title>创建商城</title>
        <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/CommonService.js'></script>
        <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/optionService.js'></script>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/indexJpgForm.css" />
        <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/linked-select.js"></script>
        <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
             sitename: {
				required: true,
				minlength: 2
			},
            maddr: "required",
            briefDesc: "required"
        },
        messages: {
             maddr: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
             msn: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
            mname: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
           code: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
            ymname: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
           ymaddr: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
                minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="2"/></fmt:message>'
            }, 
		   briefDesc: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
            sitename: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
                minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="2"/></fmt:message>'
            }
        }
    });
 
      $("#col1 tr").each(function(i){
      if(i>0){
         if(i%2 == 0){
             $(this).addClass('even');
         }else{    
              $(this).addClass('odd'); 
         }
    }
     });   
         $("#col1 th").addClass('sortable'); 
         $("#colorStyle").get(0).value  ='${bean.colorStyle}';
});

	//更改商城状态
	function auditShop(userId,shopId,status) {
	       var str ="上线";
	       if(status == 0){
	       	str = "下线";
	       }else if(status == -1){
	       	str = "审核中";
	       }else if(status == -2){
	       	str = "拒绝";
	       }else if(status == -3){
	       	str = "关闭";
	       }
		  if(confirm('确认状态变更为 ' + str +  " ?")){
	        CommonService.auditShop('${sessionScope.SPRING_SECURITY_LAST_USERNAME}',userId,shopId,status, function(retData){
	        if(retData == null){
	        	alert('成功') ;
	        	window.location.reload();
	        }else{
	         alert('失败') ;	
	       	 }      
		    }) ;
	    }
	}
	
	function changeCity(provinceid) {
  		DWREngine.setAsync(false);
		if(provinceid!=null &&provinceid!=''){
				var sql ="select cityid id, city name from ls_cities where provinceid = '"+provinceid+"'";
		        changeLinkedOptions1("cityid", false, sql,"-- 城市 --");
	        }
	        DWREngine.setAsync(true); 
        }
        
    function changeAreas(cityid) {
        DWREngine.setAsync(false);
        if(cityid!=null && cityid!=''){
                var sql ="select areaid as id, area as name from ls_areas where cityid = '"+cityid+"'";
                changeLinkedOptions1("areaid", false, sql,"-- 地区 --");
            }
            DWREngine.setAsync(true); 
        }
        
     function initSelect(provinceidValue,cityidValue,areasidValue){
			DWRUtil.setValues({provinceid:provinceidValue});
			changeCity(provinceidValue);
			DWRUtil.setValues({cityid:cityidValue});
			changeAreas(cityidValue);
			DWRUtil.setValues({areaid:areasidValue});
		}
		
	   window.onload=function(){
		 	initSelect('${bean.provinceid}','${bean.cityid}','${bean.areaid}');
		}
</script>

</head>
    <body class="bodymargin">
        <form action="${pageContext.request.contextPath}/admin/shopDetail/save${applicationScope.WEB_SUFFIX}" method="post" id="form1" enctype="multipart/form-data">
            <input id="userId" name="userId" value="${bean.userId == null ? param.userId:bean.userId}" type="hidden">
             <input id="id" name="id" value="${id}" type="hidden">
             <input id="shopId" name="shopId" value="${bean.shopId}" type="hidden">

                <table class="${tableclass}" style="width: 100%">
			    <thead>
			    	<tr><td><a href="${pageContext.request.contextPath}/admin/index${applicationScope.WEB_SUFFIX}" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/shopDetail/query${applicationScope.WEB_SUFFIX}">商城管理</a> &raquo; 创建商城</td></tr>
			    </thead>
			    </table>
            <table style="width: 100%" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建商城
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td width="150px">
          <div align="right">用户名:</div>
       </td>
        <td>
           <p><input type="text" name="storeName" id="storeName" value="${bean.storeName == null ? param.userName : bean.storeName}" size="50" readonly="readonly"/>
           <a href="${pageContext.request.contextPath}/myaccount${applicationScope.WEB_SUFFIX}" target="_blank">我的账号</a></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">网店名称: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="sitename" id="sitename" value="${bean.sitename}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">网店地址: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="maddr" id="maddr" value="${bean.maddr}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">银行汇款帐号:</div>
       </td>
        <td>
           <p><input type="text" name="msn" id="msn" value="${bean.msn}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">收款人姓名:</div>
       </td>
        <td>
           <p><input type="text" name="mname" id="mname" value="${bean.mname}" size="50" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">邮政编码:</div>
       </td>
        <td>
           <p><input type="text" name="code" id="code" value="${bean.code}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">汇款地址:</div>
       </td>
        <td>
           <p><input type="text" name="ymaddr" id="ymaddr" value="${bean.ymaddr}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">邮递接收人: </div>
       </td>
        <td>
           <p><input type="text" name="ymname" id="ymname" value="${bean.ymname}" size="50"/></p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">界面风格: <font color="ff0000">*</font></div>
       </td>
        <td><!-- see BusinessService getColorTyle -->
           	  <select id="colorStyle" name="colorStyle">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="COLOR_STYLE" selectedValue="${bean.colorStyle}"/>
	            </select>
        </td>
      </tr>    
      <c:if test="${applicationScope.LANGUAGE_MODE == 'userChoice'}"> 
      <tr>
        <td>
          <div align="right">语言选项: <font color="ff0000">*</font></div>
       </td>
        <td>
              <select id="langStyle" name="langStyle">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="LANG_STYLE" selectedValue="${bean.langStyle}"/>
	            </select>
        </td>
      </tr>
      </c:if>
      <tr>
        <td>
          <div align="right">地区选项: <font color="ff0000">*</font></div>
        </td>
        <td>
   			省份
			<select id="provinceid" name="provinceid" onChange="changeCity(this.value)">
	            <option:optionGroup type="select" required="false" cache="fasle"
	                defaultDisp="-- 省份 --" 
	                sql="select t.provinceid, t.province from ls_provinces t"/>
			</select>&nbsp;
			城市 <select id="cityid" name="cityid" onChange="changeAreas(this.value)">
				<option value="">-- 城市 --</option>
			</select>&nbsp;
			地区 <select id="areaid" name="areaid">
	            <option value="">-- 地区 --</option><br>
	        </select>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">简要描述: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="briefDesc" id="briefDesc" value="${bean.briefDesc}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">商城服务(出现在商品说明中): </div>
       </td>
        <td>
           <p>
              <textarea  name="detailDesc" id="detailDesc">${bean.detailDesc}</textarea>
           </p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">状态设置: <font color="ff0000">*</font></div>
       </td>
        <td>
        <!-- for管理员 -->
        <auth:auth ifAllGranted="F_VIEW_ALL_DATA,F_OPERATOR">
                <font color="red">当前状态：
        		<option:optionGroup type="label" required="true" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${bean.status}"/></font>&nbsp;&nbsp;
        <c:choose>
        	<c:when test="${bean.status == -1 }">
        	         <a href='javascript:auditShop("${bean.userId}","${bean.shopId}",1)'>同意申请</a>
        	         <a href='javascript:auditShop("${bean.userId}","${bean.shopId}",-2)'>拒绝申请</a>
        	</c:when>
        	<c:otherwise>
        	         <a href='javascript:auditShop("${bean.userId}","${bean.shopId}",1)'>上线</a>
        	         <a href='javascript:auditShop("${bean.userId}","${bean.shopId}",0)'>下线</a>
        	         <a href='javascript:auditShop("${bean.userId}","${bean.shopId}",-3)'>违规关闭（用户将不能登录后台）</a>
        	</c:otherwise>
        </c:choose>
        </auth:auth>
        
        <!-- for user 已经审核成功的 -->
        <c:if test="${bean.status != -1 && bean.status != -3 }">
        <auth:auth ifNotGranted="F_VIEW_ALL_DATA">
                <select id="status" name="status">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${bean.status}" exclude="-1,-2,-3"/>
	            </select>
        </auth:auth>
        </c:if>
        <!-- 还没有审核 -->
        <c:if test="${bean.status == -1 || bean.status == -3}">
         <auth:auth ifNotGranted="F_VIEW_ALL_DATA">
        <font color="red">
        		<option:optionGroup type="label" required="true" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${bean.status}"/></font>
	     </auth:auth>
        </c:if>
        
        </td>
      </tr>  
      
      <c:if test="${bean.addtime!=null}">
     <tr>
        <td>
          <div align="right">修改时间: <font color="ff0000">*</font></div>
       </td>
        <td><fmt:formatDate value="${bean.modifyTime}" pattern="yyyy-MM-dd HH:mm"/></td>
      </tr>
      </c:if>
      <c:if test="${bean.addtime!=null}">
     <tr>
        <td>
          <div align="right">创建时间: <font color="ff0000">*</font></div>
       </td>
        <td><fmt:formatDate value="${bean.addtime}" pattern="yyyy-MM-dd HH:mm"/></td>
      </tr>
      </c:if>
      <tr>
      	<td><div align="right">商城图片</div></td>
      	<td><p><input type="file" name="file" id="file"/><br>
			<c:if test="${bean.shopPic != null}">
              <img src="${pageContext.request.contextPath}/photoserver/photo/${bean.shopPic}" height="150" width="200"/> &nbsp;&nbsp;&nbsp;
             </c:if>
						</p></td>
      </tr>
      <tr>
        <td>
          <div align="right">验证信息: </div>
       </td>
        <td>
           <p>
           <c:if test="${bean.type != null}">
              网店类型：
             <option:optionGroup type="label" required="false" cache="true" beanName="SHOP_TYPE" selectedValue="${bean.type}"/><br><br>
            </c:if>
            <c:if test="${bean.idCardNum != null}">
              身份证： ${bean.idCardNum}<br><br>
            </c:if>
            <c:if test="${bean.idCardPic != null}">
              身份证照片： <img src="${pageContext.request.contextPath}/photoserver/photo/${bean.idCardPic }" height="150" width="200"/> &nbsp;&nbsp;&nbsp;
              </c:if>
              <c:if test="${bean.trafficPic != null}">
             营业执照照片： <img src="${pageContext.request.contextPath}/photoserver/photo/${bean.trafficPic }" height="150" width="200"/><br>
             </c:if>
           </p>
        </td>
      </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                        <auth:auth ifAnyGranted="F_OPERATOR">
                            <input type="submit" value="提交" />
                        </auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${pageContext.request.contextPath}/admin/shopDetail/query${applicationScope.WEB_SUFFIX}'" />
                        </div>
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>

