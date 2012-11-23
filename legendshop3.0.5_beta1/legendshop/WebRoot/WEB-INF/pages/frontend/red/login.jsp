<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<script type="text/javascript" language="javascript" src="<ls:templateResource item='/common/js/randomimage.js'/>"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/default/css/indexJpgForm.css'/>" />
<!--顶部menu end-->
<!--nav-->
 <div class="nav">
   <div class="wrap">
   <p class="dengl">
   <span class="yongh">用户登录</span>
   <span class="zhuce">还没有注册，现在就 <a href="<ls:url address='/reg'/>" style="color:#FFF"><b>注册</b></a></span>
   </p>
   </div> 
 </div>
<!--nav end-->

<div class="w"> 

    <div class="login_left wrap">
      <div class="news_wrap">
         
         <div class="news_bor">
         <div class="loginwrap">
        <div class="left">
           <div class="lgtit">用户登录</div>
           <div class="formtable">
           <form name="loginform" action="${pageContext.request.contextPath}/j_spring_security_check" method="POST"  onsubmit="return checkRandNum();" >
           	<input type="hidden" id="rand" name="rand"/>
			<input type="hidden" id="cannonull" name="cannonull" value='<fmt:message key="randomimage.errors.required"/>'/>
			<input type="hidden" id="charactors4" name="charactors4" value='<ls:i18n key="randomimage.charactors.required" length="4"/>'/>
			<input type="hidden" id="errorImage" name="errorImage" value='<fmt:message key="error.image.validation"/>'/>
			<input type="hidden" id="returnUrl" name="returnUrl" value="${param.returnUrl}"/>
           <table border="0" cellpadding="0" cellspacing="0">
            <tbody>
              <c:if test="${param.error}">
	              <tr><th>&nbsp;</th><td><label class="error"><fmt:message key="error.password.noright"/></label></td></tr>
             </c:if>
              <tr><th width="158">用户名</th>
	              <td width="462">
	              <input id="username" type='text' name="j_username"  style="width:300px; height:25px;" class="inputstyle" tabindex="1" value='<lb:lastLogingUser/>'/>
	              </td>
              </tr>
              <tr><th>密码</th><td>
              <input id="pwd" type='password' name='j_password'  style="width:300px; height:25px;" autocomplete="off" class="inputstyle" tabindex="2" onload="this.value=''"/>
              </td></tr>
              <lb:userValidationImage>
					<tr>
						<th><fmt:message key="validation.code"/></th>
						<td align="left"><input type="text" id="randNum" name="randNum"  class="inputstyle"  maxlength="4"  style="width: 50px;height:22px;" tabindex="3" >
						<img id="randImage" name="randImage" src="<ls:templateResource item='/captcha.svl'/>"  style="vertical-align: middle;"/>
 				        &nbsp;<a href="javascript:void(0)" onclick="javascript:changeRandImg('${pageContext.request.contextPath}')" style="font-weight: bold;"><fmt:message key="change.random2"/></a>
						</td>
					</tr>	
			  </lb:userValidationImage>	
              <tr><th>&nbsp;</th><td>
              <table>
                <tr>
                    <td>
                    	<input name="submit" type="image" src="<ls:templateResource item='/common/red/images/dl_11.gif'/>"  width="80" height="35"  tabindex="4" />
					</td>
                  </tr>
                </table>
                </td>
               </tr>             
            </tbody>          
          </table>
          </form>
          </div>        
        </div>         
        <div class="right">
          <p><span>还不是legend网户？</span><br />
            现在免费注册成为legend用户，便能立刻享受便宜又放心的购物乐趣。<br /><br /><a href="#"><img src="../images/dl_07.gif" width="76" height="30" /></a></p>
        </div>        
        <div class="clear"></div>
     </div>   
        </div>      
      </div>                                  
    </div>
    <!----左边end---->
    
   <div class="clear"></div>
</div>
	<script type="text/javascript">	
	window.onload = function(){
	   if(window.top.location.href!=location.href)    
		{
    		window.top.location.href=location.href;    
		}

		var userName = document.getElementById("username").value;
		if(userName == null || userName =='' ){
			document.getElementById("username").focus();
		}else{
		    document.getElementById("pwd").focus();
		}
    }
	</script>
<!----red两栏end---->
