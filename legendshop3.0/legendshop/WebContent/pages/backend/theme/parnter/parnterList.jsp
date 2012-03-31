<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
    <style type="text/css" media="all">
       @import url("<ls:templateResource item='/css/screen.css'/>");
    </style>
    <title>Parnter列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/parnter/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<td>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/parnter/query'/>">Parnter</a>
			    	</td>
		    	</tr>
		    </thead>
	    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城名称&nbsp;<input type="text" name="userName" maxlength="50" value="${parnter.userName}" />
            </auth:auth>
            <input type="submit" value="搜索"/>
            <input type="button" value="创建Parnter" onclick='window.location="<ls:url address='/admin/parnter/load'/>"'/>
            <br>
    </form>
    <div align="center">
          <%@ include file="/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/parnter/query" id="item" export="false" class="${tableclass}" style="width:100%">
	             <display:column title="PartnerId" property="partnerId"></display:column>
      <display:column title="PartnerName" property="partnerName"></display:column>
      <display:column title="Password" property="password"></display:column>
      <display:column title="Title" property="title"></display:column>
      <display:column title="Homepage" property="homepage"></display:column>
      <display:column title="UserId" property="userId"></display:column>
      <display:column title="UserName" property="userName"></display:column>
      <display:column title="ShopId" property="shopId"></display:column>
      <display:column title="BankName" property="bankName"></display:column>
      <display:column title="BankNo" property="bankNo"></display:column>
      <display:column title="BankUser" property="bankUser"></display:column>
      <display:column title="Location" property="location"></display:column>
      <display:column title="Contact" property="contact"></display:column>
      <display:column title="Image" property="image"></display:column>
      <display:column title="Image1" property="image1"></display:column>
      <display:column title="Image2" property="image2"></display:column>
      <display:column title="Phone" property="phone"></display:column>
      <display:column title="Address" property="address"></display:column>
      <display:column title="Other" property="other"></display:column>
      <display:column title="Mobile" property="mobile"></display:column>
      <display:column title="Open" property="open"></display:column>
      <display:column title="Status" property="status"></display:column>
      <display:column title="Display" property="display"></display:column>
      <display:column title="CommentGood" property="commentGood"></display:column>
      <display:column title="CommentNone" property="commentNone"></display:column>
      <display:column title="CommentBad" property="commentBad"></display:column>
      <display:column title="ModifyTime" property="modifyTime"></display:column>
      <display:column title="CreateTime" property="createTime"></display:column>

	    <display:column title="Action" media="html">
		      <a href="<ls:url address='/admin/parnter/load/${item.partnerId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.partnerId}")' title="删除">
		      		<img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/>">
		      </a>
	      </display:column>
	    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
        <script language="JavaScript" type="text/javascript">
			<!--
			  function deleteById(id) {
			      if(confirm("  确定删除 ?")){
			            window.location = "<ls:url address='/admin/parnter/delete/" + id + "'/>";
			        }
			    }
			
			        function pager(curPageNO){
			            document.getElementById("curPageNO").value=curPageNO;
			            document.getElementById("form1").submit();
			        }
			//-->
		</script>
</body>
</html>


