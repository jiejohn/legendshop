<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<html:html>
<head>
  		<script type='text/javascript' src='${root}/dwr/interface/CommonService.js'></script>
		<script type='text/javascript' src='${root}/dwr/interface/optionService.js'></script>
		<script type='text/javascript' src='${root}/dwr/engine.js'></script>
		<script type='text/javascript' src='${root}/dwr/util.js'></script>
		<LINK title=Style href="${root}/common/css/back_style.css" type=text/css rel=stylesheet>
		<script type="text/javascript" language="javascript" src="${root}/common/js/linked-select.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
  function confirmDelete()
	{
	    return con = confirm("确定要删除吗？");
	}
	
  function deleteHw(hwId,hwName) {
	  if(confirm('确定删除产品 '+hwName+' ?')){
        CommonService.deleteHw(hwId, function(retData){
	       if(retData == null ){
	          alert("成功删除该纪录！") ;
	          window.location.reload() ;
	       }else{
	          alert("删除失败！") ;
	       }
	       
	    }) ;
    }
}

  function hwOnline(hwId,hwName) {
	  if(confirm('确定产品 '+hwName+' 上线?')){
        CommonService.hwOnline(hwId, function(retData){
        
	       if(retData == null ){
	          alert("成功上线！") ;
	          window.location.reload() ;
	       }else{
	          alert("上线失败！") ;
	       }
	       
	    }) ;
    }
}

  function hwOffline(hwId,hwName) {
	  if(confirm('确定产品 '+hwName+' 下线?')){
        CommonService.hwOffline(hwId, function(retData){
        
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
			
	       function initStatus(commendValue,statusValue){
			DWRUtil.setValues({commend:commendValue});
			DWRUtil.setValues({status:statusValue});
			}		
			
			
		window.onload=function(){
		 var sortId = '${hw.sortId}';
		 var nsortId = '${hw.nsortId}';
		 var subNsortId = '${hw.subNsortId}';
		 var commend = '${hw.commend}';
		 var status = '${hw.status}';
		 if(sortId!=null ||sortId!=''){
		 	initSelect(sortId,nsortId,subNsortId);
		 }
		 initStatus(commend,status);
		}
		
		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
//-->
</script>
<title>分类列表</title>
</head>
<body>
<%
			Integer offset = (Integer)request.getAttribute("offset");
	%>
<br>
<form action="${root}/admin/hwList.do" id="form1">
<input type="hidden" id="curPageNO" name="curPageNO" value="${hw.curPageNO}"/>
			<div align="center">
		    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
              用户名：
            <input type="text" name="userName" maxlength="50" value="${hw.userName}" size="15"/>
            </auth:auth>
			商品类型：
			<select id="sortId" name="sortId" onChange="changeNsort(this.value)">
			<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
				<c:if test="${hw.userName != null && hw.userName !=''}">
				  <option:optionGroup type="select" required="false" cache="fasle"
	                beanName="Sort" beanId="sortId" beanDisp="sortName" defaultDisp="-- 一级分类 --"
	                hql="select t.sortId, t.sortName from Sort t where t.userName = ?" param="${hw.userName}" />
				</c:if>
	            <c:if test="${hw.userName == null}">
                    <option value="">-- 一级分类 --</option>
	            </c:if>
			</auth:auth>
			<auth:auth ifNotGranted="F_VIEW_ALL_DATA">
			<c:if test="${userName != null && userName !=''}">
	            <option:optionGroup type="select" required="false" cache="fasle"
	                defaultDisp="-- 一级分类 --" 
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
			名称：
			<input type="text" name="hwName" id="hwName" maxlength="50" value="${hw.hwName}" size="15"/>
			推荐：
			<select id="commend" name="commend">
					<option value="">请选择</option>	
			    	<option value="True">是</option>	
	      			<option value="False" >否</option>
			</select>
			状态：
			<select id="status" name="status">
					<option value="">请选择</option>	
			    	<option value="1">上线</option>	
	      			<option value="0" >下线</option>
			</select>		
			<html:submit value="搜索" property="Submit" />
		   &nbsp; <html:link page="/admin/hw/saveHw.jsp">创建产品</html:link>
			</div>

<table width="100%" border="0" align="center" class="tableBorder">
  <tr>
    <th height="29" colspan="17" scope="col">类型列表</th>
  </tr>
  <c:if test="${list != null }">
  <tr>
    <td align="center" class="forumRow" width="5%">顺序</td>
    <td align="center" class="forumRow" width="6%">一级分类</td>
    <td align="center" class="forumRow" width="6%">二级分类</td>
    <td align="center" class="forumRow" width="6%">三级分类</td>
    <td align="center" class="forumRow" width="12%">商品名称</td>
    <td align="center" class="forumRow">商品原价</td>
    <td align="center" class="forumRow">商品现价</td>
    <td align="center" class="forumRow">查看数</td>
    <td align="center" class="forumRow">订购数</td>
    <td align="center" class="forumRow">上线时间</td>
    <td align="center" class="forumRow">图片</td>
    <td align="center" class="forumRow">推荐</td>
    <td align="center" class="forumRow" width="4%">状态</td>
    <td align="center" class="forumRow">修改时间</td>
    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
     <td align="center" class="forumRow">用户名</td>
     </auth:auth>
    <td align="center" class="forumRow" width="10%">操作&nbsp;</td>
  </tr>
  <c:forEach items="${requestScope.list}" var="hw">
  <tr>
    <td align="center" class="forumRow"><%=offset++%></td>
    <td align="center" class="forumRow">${hw.sortName}</td>
    <td align="center" class="forumRow">${hw.nsortName}</td>
    <td align="center" class="forumRow">${hw.subNsortName}</td>
    <td align="center" class="forumRow">
    <a href= "${root}/views/${hw.hwId}/${hw.sortId}" target="_blank"> 
    ${hw.hwName}</a></td>
    <td align="center" class="forumRow">${hw.hwPrice}</td>
    <td align="center" class="forumRow">${hw.hwCash}</td>
    <td align="center" class="forumRow">${hw.hwViews}</td>
    <td align="center" class="forumRow">${hw.hwBuys}</td>
    <td align="center" class="forumRow">${hw.hwDate}</td>
    <td align="center" class="forumRow">
    <a href="${PHOTO_PATH}/${hw.hwPic}" target="_blank">
     <img src="${root}/img/filter_search.png"></a>
    </td>
    <td align="center" class="forumRow">
        <c:choose>
    	<c:when test="${hw.commend == 'True'}"><font color="red">是</font></c:when>
    	<c:otherwise>否</c:otherwise>
    </c:choose>
    </td>
    <td align="center" class="forumRow">
    <c:choose>
    	<c:when test="${hw.status == 1}"><font color="red">上线</font></c:when>
    	<c:otherwise>下线</c:otherwise>
    </c:choose>
    </td>
    <td align="center" class="forumRow">${hw.modifyDate}</td>
    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
     <td align="center" class="forumRow">${hw.userName}</td>
     </auth:auth>
    <td align="center" class="forumRow">
     <a href= "${root}/admin/updateHw.do?hwId=${hw.hwId}"> 修改</a>
     <auth:auth ifAnyGranted="F_OPERATOR">
  	<c:choose>
  		<c:when test="${hw.status == 1}">
  			<a href='javascript:hwOffline("${hw.hwId}","${hw.hwName}")'> 下线</a>
  		</c:when>
  		<c:otherwise>
  			<a href='javascript:hwOnline("${hw.hwId}","${hw.hwName}")'> 上线</a>
  		</c:otherwise>
  	</c:choose>
     <a href='javascript:deleteHw("${hw.hwId}","${hw.hwName}")'>删除</a>
     </auth:auth>
    </td>
    </tr>
  </c:forEach>
  </c:if>
</table>
</form>
	<div align="center">
	<logic:present name="toolBar">
		<bean:write name="toolBar" filter="false"/>
	</logic:present>
	</div>
</body>
</html:html>

