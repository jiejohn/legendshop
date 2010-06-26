<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.done.util.SessionUserManagement"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<html>
<head>
<title>创建产品类型</title>
<%
   if(session.getAttribute("userName") == null){
       session.setAttribute("userName", SessionUserManagement.getUsername(request.getSession()));
   }
%>
		<LINK title=Style href="${root}/common/css/back_style.css" type=text/css rel=stylesheet>
		<script type='text/javascript' src='${root}/dwr/interface/optionService.js'></script>
		<script type='text/javascript' src='${root}/dwr/engine.js'></script>
		<script type='text/javascript' src='${root}/dwr/util.js'></script>
		<script type="text/javascript" language="javascript" src="${root}/common/js/linked-select.js"></script>
<script language="javascript">
	window.onload=function(){
		 var sortId = '${hw.sortId}';
		 var nsortId = '${hw.nsortId}';
		 var subNsortId = '${hw.subNsortId}';
		 if(sortId!=null ||sortId!=''){
		 	initSelect(sortId,nsortId,subNsortId);
		 }
		}
 
  function changeNsort(sortId) {
  		DWREngine.setAsync(false);
		if(sortId!=null &&sortId!=''){
				var sql ="select nsort_id id, nsort_name name from nsort where sort_id = '"+sortId+"' and parent_nsort_id is null";
		        changeLinkedOptions1("nsortId", false, sql,"-- 二级分类 --");
	        }
	        DWREngine.setAsync(true); 
        }
        
       function changeSubNsort(nsortId) {
        DWREngine.setAsync(false);
        if(nsortId!=null &&nsortId!=''){
                var sql ="select nsort_id id, nsort_name name from nsort where parent_nsort_id = '"+nsortId+"'";
                changeLinkedOptions1("subNsortId", false, sql,"-- 三级分类 --"); 
            }
            DWREngine.setAsync(true); 
        }
        
        
   function initSelect(sortIdValue,nsortIdValue,subNsortIdValue){
			//设置商品分类当前值
            DWRUtil.setValues({sortId:sortIdValue});
            changeNsort(sortIdValue);
            DWRUtil.setValues({nsortId:nsortIdValue});
            changeSubNsort(nsortIdValue);
            DWRUtil.setValues({subNsortId:subNsortIdValue});
			}	
			
   function checkform() {
   //hwCash hwPic hwBrief hwContent
	  if($("sortId").value==null||$("sortId").value=='')
		{
			alert("请选择类型名称!");
			$("sortId").focus();
			return false;
		}
		if($("hwName").value==null||$("hwName").value=='')
		{
			alert("请输入产品名称!");
			$("hwName").focus();
			return false;
		}
		
        if(($("hwCash").value != null || $("hwCash").value!='') && isNaN($("hwCash").value)){
			alert("商品现价必须为数字!");
			$("hwCash").focus();
			return false;
		}	
	  if($("hwPic").value==null||$("hwPic").value=='')
		{
		if($("hwPicName").value==null||$("hwPicName").value==''){
			alert("请上传类型图片!");
			$("hwPic").focus();
			return false;
		}
		}
		if($("hwBrief").value==null||$("hwBrief").value=='')
		{
			alert("请输入商品简要描述!");
			$("hwBrief").focus();
			return false;
		}
			
		return true ;
  }
  

</script>
</head>
<body>
<form action="${root}/admin/saveHw.do"  method="post" enctype="multipart/form-data" onsubmit="return checkform()">
	<input type="hidden" id="hwId" name="hwId" value="${hw.hwId}">
  <table width="90%" height="129" border="0" align="center" bordercolor="#0099FF" class="tableBorder">
    <tr>
      <th colspan="3">创建产品</th>
    </tr>
    <tr>
      <td width="14%" class="forumRow"><div align="center">选择产品类型：<font color="#ff0000">*</font></div></td>
      <td width="48%" class="forumRow">
		<select id="sortId" name="sortId" onChange="changeNsort(this.value)">
            <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                <c:if test="${hw.userName != null}">
                  <option:optionGroup type="select" required="false" cache="fasle"
                    beanName="Sort" beanId="sortId" beanDisp="sortName" defaultDisp="--商品大类--" 
                    hql="select t.sortId, t.sortName from Sort t where t.userName = ?" param="${hw.userName}" />
                </c:if>
                <c:if test="${hw.userName == null}">
                    <option value="">-- 一级分类 --</option>
                </c:if>
            </auth:auth>
            <auth:auth ifNotGranted="F_VIEW_ALL_DATA">
            <c:if test="${userName != null && userName !=''}">
            <option:optionGroup type="select" required="false" cache="fasle"
                defaultDisp="- 一级分类 -" 
                hql="select t.sortId, t.sortName from Sort t where t.userName = ?" param="${userName}"/>
             </c:if>
            </auth:auth>
		</select>    
		<select id="nsortId" name="nsortId" onChange="changeSubNsort(this.value)">
            <option value="">-- 二级分类 --</option>
        </select>  
         <select id="subNsortId" name="subNsortId">
            <option value="">-- 三级分类 --</option>
        </select> 
		</td>
      <td width="38%" rowspan="6" class="forumRow">
	   <c:if test="${hw.hwPic!=null}">
      	<a href="${PHOTO_PATH}/${hw.hwPic}" target="_blank">
      	<img src="${PHOTO_PATH}/${hw.hwPic}" height="235" width="320"/></a></c:if></td>
    </tr>
    
    <tr>
      <td width="14%" class="forumRow"><div align="center">商品名称：<font color="#ff0000">*</font></div></td>
      <td width="48%" class="forumRow">
			<input type="text" name="hwName" id="hwName" size="30" value="${hw.hwName}" maxlength="50"/>      </td>
    </tr>
     <tr>
      <td width="14%" class="forumRow"><div align="center">商品原价：</div></td>
      <td width="48%" class="forumRow">
			<input type="text" name="hwPrice" id="hwPrice" size="30" value="${hw.hwPrice}" maxlength="15"/>      </td>
    </tr>   
    <tr>
      <td width="14%" class="forumRow"><div align="center">商品现价：</div></td>
      <td width="48%" class="forumRow">
			<input type="text" name="hwCash" id="hwCash" size="30" value="${hw.hwCash}" maxlength="15"/>      </td>
    </tr> 
    <tr>
      <td width="14%" class="forumRow"><div align="center">是否推荐到首页：</div></td>
      <td width="48%" class="forumRow">
      		<select id="commend" name="commend">
      		<c:choose>
      			<c:when test="${hw.commend == 'True'}">
      				<option value="True" selected="selected">是</option>	
	      			<option value="False" >否</option>
      			</c:when>
      			<c:otherwise>
      			 	<option value="False" selected="selected">否</option>
      				<option value="True">是</option>	
      			</c:otherwise>
      		</c:choose>
      		</select>      </td>
    </tr>
    <tr>
      <td width="14%" class="forumRow"><div align="center">产品图片<font color="#ff0000">*</font></div></td>
      <td class="forumRow"><input type="file" name="hwPic" id="hwPic" size="30"/>
      	<input type="hidden" name="hwPicName" id="hwPicName" size="30" value="${hw.hwPic}"/>图片分辨率640×470,大小不能超过1M</td>
    </tr> 
     <tr>
      <td width="14%" class="forumRow"><div align="center">简要描述：<font color="#ff0000">*</font></div></td>
      <td colspan="2" class="forumRow">
			<textarea rows="10" cols="58"  name="hwBrief" id="hwBrief" >${hw.hwBrief}</textarea>      </td>
    </tr>    
    <auth:auth ifAnyGranted="F_OPERATOR"> 
      <tr>
      <td width="14%" class="forumRow"><div align="center">详细描述：</div></td>
      <td colspan="2" class="forumRow">
			<FCK:editor instanceName="hwContent" height="400px">
                <jsp:attribute name="value">${hw.hwContent}</jsp:attribute>
            </FCK:editor>  
	 </td>
    </tr>
    </auth:auth>
    <tr>
      <td colspan="3" class="forumRow"><div align="center">
        <auth:auth ifAnyGranted="F_OPERATOR"> 
            <html:submit value="提交"/>
        </auth:auth>  
        <html:reset  value="重置"/>
		          
      </div></td>
    </tr>
  </table>
</form>
		<table border="0" width="200" align="center">
			<tr>
				<td>
					<p align="center">
						<a href="${root}/admin/hwList.do" title="GoBack">返回</a>
					</p>
				</td>
			</tr>
		</table>
		</body>
</html>