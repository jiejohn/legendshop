<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
   <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/indexJpgForm.css" />
    <title>商品图片列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/imgFile/save" id="form1" method="post" enctype="multipart/form-data">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="<ls:url address='/admin/product/query'/>">商品管理</a> &raquo; <a href="${pageContext.request.contextPath}/admin/product/load">创建商品</a>
    	  <c:if test="${prod.name != null}">&raquo; <a href= "${pageContext.request.contextPath}/views/${prod.prodId}" target="_blank">${prod.name}</a></c:if>
    	</td></tr>
    </thead>
    <tr class="odd">
      <td><div align="left">
		<jsp:include page="/admin/product/createsetp">
    		<jsp:param name="step" value="2"/>
    	</jsp:include>
      </div></td>
    </tr>
    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
           商品图片<font color="ff0000">*</font> <input type="file" name="file" id="file" size="30"/>      
           <input type="hidden" name="productId" id="productId" value="${productId}"/><input type="submit" value="提交">
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/imgFile/query" id="item"
         export="false" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="文件"><a href="<ls:photo item='${item.filePath}'/>" target="_blank">
      <img src="<ls:photo item='${item.filePath}'/>" height="110px" width="150px"/></a>
      </display:column>
      <display:column title="文件类型" property="fileType"></display:column>
      <display:column title="文件大小" property="fileSize"></display:column>
      <display:column title="上传时间"><fmt:formatDate value="${item.upoadTime}" pattern="yyyy-MM-dd HH:mm" /></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
     	 <display:column title="商城名称" property="userName"></display:column>
      </auth:auth>
      <display:column title="操作" media="html" style="width:50px">
      <auth:auth ifAnyGranted="F_OPERATOR">
       <c:choose>
  		<c:when test="${item.status == 1}">
  			<a href='javascript:imgFileOffline("${item.fileId}")' ><img alt="下线"  src="<ls:templateResource item='/common/default/images/blue_down.png'/> "></a>
  		</c:when>
  		<c:otherwise>
  			<a href='javascript:imgFileOnline("${item.fileId}")'><font color="red"><img alt="上线" src="<ls:templateResource item='/common/default/images/yellow_up.png'/> "></font></a>
  		</c:otherwise>
  	  </c:choose>
      	<a href='javascript:deleteById("${item.fileId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
         <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 点击选择，再点击提交则为商品增加一个图片，大小不能超过2M<br>
   3. 支持上传的图片格式有<font color="red">.jpg,.gif,.png,.jpeg</font>,其余文件格式不支持<br>
   2. 图片处于上线状态则会在商品的详细信息页面出现，处于下线状态则不会在页面上显示<br>
   3. 图片删除则不会再显示<br>
   </td><tr></table> 
   
   <script language="JavaScript" type="text/javascript">
<!--
  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/imgFile/delete/"+id;
        }
    }

        function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
        
  function imgFileOnline(prodId) {
	  if(confirm('确定商品图片上线?')){
        CommonService.imgFileOnline(prodId, function(retData){      
	       if(retData == null ){
	          alert("成功上线！") ;
	          window.location.reload() ;
	       }else{
	          alert("上线失败！") ;
	       }
	       
	    }) ;
    }
}

  function imgFileOffline(fileId) {
	  if(confirm('确定商品图片下线?')){
        CommonService.imgFileOffline(fileId, function(retData){   
	       if(retData == null ){
	          alert("成功下线！") ;
	          window.location.reload() ;
	       }else{
	          alert("下线失败！") ;
	       }
	       
	    }) ;
    }
}

$.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            file: "required"
        },
        messages: {
            file: {
                required: "请上传商品图片"
            }
        }
    });
 
});

highlightTableRows("item"); 
//-->
</script>
   
</body>
</html>

