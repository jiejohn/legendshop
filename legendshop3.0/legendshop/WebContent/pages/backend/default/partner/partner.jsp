<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<html>
    <head>
        <title>创建Parnter</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/css/indexJpgForm.css'/>" />
        <style type="text/css" media="all">
          @import url(<ls:templateResource item='/css/screen.css'/>);
        </style>
        <script language="javascript">
		    $.validator.setDefaults({
		    });

		    $(document).ready(function() {
		        jQuery("#form1").validate({
		            rules: {
		            	name: {
		                    required: true,
		                },
		            	url: {
		                    required: true,
		                }
		            },
		            messages: {
		            	name: {
		                    required: "not null",
		                },
		            	url: {
		                    required: "not null",
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
		    });
</script>
</head>
    <body>    
	<table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr>
		    	<td>
			    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
			    	<a href="<ls:url address='/admin/partner/query'/>">供应商管理</a>
		    	</td>
	    	</tr>
	    </thead>
    </table>
        <form action="<ls:url address='/admin/partner/save'/>" method="post" id="form1">
            <input id="partnerId" name="partnerId" value="${partner.partnerId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
							<c:choose>
			                	<c:when test="${not empty partner}">修改供应商</c:when>
			                	<c:otherwise>创建供应商</c:otherwise>
			                </c:choose>				
                            </div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">供应商登录名: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="partnerName" id="partnerName" value="${partner.partnerName}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">登录密码: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="password" id="password" value="${partner.password}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">供应商名称: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="title" id="title" value="${partner.title}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">供应商主页: </div>
       </td>
        <td>
           <p><input type="text" name="homepage" id="homepage" value="${partner.homepage}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">银行名称: </div>
       </td>
        <td>
           <p><input type="text" name="bankName" id="bankName" value="${partner.bankName}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">银行账号: </div>
       </td>
        <td>
           <p><input type="text" name="bankNo" id="bankNo" value="${partner.bankNo}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">银行用户名: </div>
       </td>
        <td>
           <p><input type="text" name="bankUser" id="bankUser" value="${partner.bankUser}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户所在位置: </div>
       </td>
        <td>
           <p><input type="text" name="location" id="location" value="${partner.location}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">联系人: </div>
       </td>
        <td>
           <p><input type="text" name="contact" id="contact" value="${partner.contact}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户图片1: </div>
       </td>
        <td>
           <p><input type="text" name="image" id="image" value="${partner.image}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户图片2: </div>
       </td>
        <td>
           <p><input type="text" name="image1" id="image1" value="${partner.image1}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户图片3: </div>
       </td>
        <td>
           <p><input type="text" name="image2" id="image2" value="${partner.image2}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">联系电话: </div>
       </td>
        <td>
           <p><input type="text" name="phone" id="phone" value="${partner.phone}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户地址: </div>
       </td>
        <td>
           <p><input type="text" name="address" id="address" value="${partner.address}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户其他信息: </div>
       </td>
        <td>
           <p><input type="text" name="other" id="other" value="${partner.other}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户手机号码: </div>
       </td>
        <td>
           <p><input type="text" name="mobile" id="mobile" value="${partner.mobile}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">是否展示相关信息: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p>           
				<select id="open" name="open">
				  <option:optionGroup type="select" required="true" cache="true"  beanName="YES_NO" selectedValue="${partner.open}"/>
	            </select>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户状态: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p>
				<select id="status" name="status">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="ENABLED" selectedValue="${partner.status}"/>
	            </select>
	        </p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">是否显示首页: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p>
				<select id="display" name="display">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${partner.display}"/>
	            </select>
	        </p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评论满意数量: </div>
       </td>
        <td>
           <p><input disabled="disabled" type="text" name="commentGood" id="commentGood" value="${partner.commentGood}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评论一般数量: </div>
       </td>
        <td>
           <p><input disabled="disabled" type="text" name="commentNone" id="commentNone" value="${partner.commentNone}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评论失望数量: </div>
       </td>
        <td>
           <p><input disabled="disabled" type="text" name="commentBad" id="commentBad" value="${partner.commentBad}" /></p>
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/partner/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


