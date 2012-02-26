<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<html:html>
<head>
<LINK title=Style href="${pageContext.request.contextPath}/common/css/back_style.css" type=text/css rel=stylesheet>
<title>创建用户</title>
</head>
<br>
<br>
<html:form action="/member/user/saveUser${applicationScope.WEB_SUFFIX}" >
  <table width="489" height="129"  align="center" bordercolor="#0099FF" class="tableBorder">
    <tr>
      <th colspan="2">创建用户</th>
    </tr>
    <tr>
      <td width="217" class="forumRow"><div align="center">用户名<font color="#ff0000">*</font></div></td>
      <td width="262" class="forumRow"><input type="text" name="user.name" value=""></td>
    </tr>
    <tr>
      <td class="forumRow"><div align="center">密码<font color="#ff0000">*</font></div></td>
      <td class="forumRow"><input type="password" name="user.password" value=""></td>
    </tr>
	    <tr>
      <td class="forumRow"><div align="center">确认密码<font color="#ff0000">*</font></div></td>
      <td class="forumRow"><input type="password" name="user.passwordag" value=""></td>
    </tr>
        <tr>
      <td class="forumRow"><div align="center">状态</div></td>
      <td class="forumRow">
        <p>
          <label>
            <input type="radio" name="user.enabled" value="1" checked="checked"/>
            有效</label> &nbsp; &nbsp; 
          <label>
            <input type="radio" name="user.enabled" value="0" />
            无效</label>
          <br />
        </p>     </td>
    </tr>
	<tr>
      <td class="forumRow"><div align="center">注释</div></td>
      <td class="forumRow"><input type="text" name="user.note" value=""></td>
    </tr>
    <tr>
      <td colspan="2" class="forumRow"><div align="center">        
        <input type="submit" value="添加"/>
        <html:reset  value="重置"/>
		          
      </div></td>
    </tr>
  </table>
  <html:javascript formName="usersForm"/>
</html:form>
		<table  width="200" align="center">
			<tr>
				<td>
					<p align="center">
						<a href="usersList${applicationScope.WEB_SUFFIX}" title="GoBack">返回</a>
					</p>
				</td>
			</tr>
		</table>
</html:html>