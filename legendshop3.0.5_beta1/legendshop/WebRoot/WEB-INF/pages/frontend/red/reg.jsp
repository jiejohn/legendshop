<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<!--nav-->
 <div class="nav">
   <div class="wrap">
   <p class="dengl">
   <span class="yongh">注册新用户</span>
   <span class="zhuce">我已经注册，现在就 <a href="<ls:url address='/p/login'/>" style="color:#FFF"><b>登录</b></a></span>
   </p>
   </div>
 </div>
<!--nav end-->
<div class="w"> 
    <div class="login_left wrap">
      <div class="news_wrap">
         <div class="news_bor">
         <div class="loginwrap">
        <div class="leftr">
           <div class="lgtit">
           
      <div class="pagetablg">
         <ul>
           <li class="onlg">个人用户</li>
           <li>企业用户</li>       
         </ul>       
       </div>
           
           </div>
           <div class="formtable" style="text-align:center;">
           
           <table style="margin:0 auto; display: none;">
            <tbody>
              <tr><th width="150">用户名：</th><td><input type="text" value="gln13571357"   class="inputstyle" /></td><td class="hint">用户名已存在</td></tr>
              <tr><th>设置密码：</th><td><input type="password"   class="inputstyle" /></td><td class="hint">&nbsp;</td></tr>
             
              <tr><th>确认密码：</th><td><input type="password"   class="inputstyle" /></td><td class="hint">&nbsp;</td></tr>
              <tr><th>邮箱：</th><td><input type="text"   class="inputstyle" /></td><td class="hint">&nbsp;</td></tr>
              <tr><th>推荐人用户名：</th><td><input type="text"   class="inputstyle" /></td><td class="hint">&nbsp;</td></tr>
              <tr><th>验证码：</th><td><input type="text"   class="inputstyle" style="width:80px;" /> <img src="../image/img/JDVerification.gif" width="100" height="26" /></td><td class="hint">&nbsp;</td></tr>
            </tbody>
          
          </table>          
          
          <!--企业用户-->
            
          <table style="margin:0 auto;">
            <tbody>
            <tr>
              <th width="150"><strong>帐户信息：</strong></th><td>&nbsp;</td><td class="hint">&nbsp;</td></tr>
              <tr><th width="150">用户名：</th><td><input type="text" value="gln13571357"   class="inputstyle" /></td><td class="hint">用户名已存在</td></tr>
              <tr><th>设置密码：</th><td><input type="password"   class="inputstyle" /></td><td class="hint">&nbsp;</td></tr>
             
              <tr><th>确认密码：</th><td><input type="password"   class="inputstyle" /></td><td class="hint">&nbsp;</td></tr>
              <tr><th>邮箱：</th><td><input type="text"   class="inputstyle" /></td><td class="hint">&nbsp;</td></tr>
              <tr><th>推荐人用户名：</th><td><input type="text"   class="inputstyle" /></td><td class="hint">&nbsp;</td></tr>
              <tr><th>验证码：</th><td><input type="text"   class="inputstyle" style="width:80px;" /> <img src="../image/img/JDVerification.gif" width="100" height="26" /></td><td class="hint">&nbsp;</td></tr>
             
             
            </tbody>
          
          </table>
          
          <table style="margin:0 auto;">
            <tbody>
           <tr><th height="73">&nbsp;</th><td>
           <input name="submit" type="image" src="<ls:templateResource item='/common/red/images/btntong_10.gif'/>"   width="173" height="45"  />
           </td></tr>
              <tr>
                <th colspan="3"><textarea class="lgtext">网站用户注册协议
本协议是您与legendshop网站（简称“本站”，网址：www.legendshop.com）所有者（以下简称为“legendshop”）之间就legendshop网站服务等相关事宜所订立的契约，请您仔细阅读本注册协议，您点击“同意以下协议，提交</textarea></th></tr>
            </tbody>
          
          </table>
           
           </div>
        
        </div>     
        
        <div class="clear"></div>
     </div>   
        </div>
      </div>
    </div>
    <!----左边end---->
    
   <div class="clear"></div>
</div>
<!----red两栏end---->

