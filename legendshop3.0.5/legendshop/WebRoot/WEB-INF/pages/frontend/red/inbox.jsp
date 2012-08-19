<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<!----地址---->
 <div class="w addr">
   <span><a href="#">首页</a></span>&gt;<span><a href="#">会员中心</a></span>&gt;<span><a href="#">我的消息</a></span>&gt;<span>收件箱</span>
 </div>
<!----地址end---->


<!----两栏---->
 <div class="w" style="padding-top:10px;"> 
    <!-----------leftm-------------->
	<c:import url="usercenter_left.jsp" />	
    <!-----------leftm end-------------->
    
    <!-----------right_con-------------->
     <div class="right_con">
     
         <div class="pagetab ">
                 <ul>           
                    <li class="on"><span><a href="#">收件箱</a></span></li>                  
                   <li><span><a href="new_mes.html">发消息</a></span></li>      
                 </ul>       
         </div>
     
         <div class="mailtool">
         
              <div class="fl mailbtn">
                 <input name="" type="button" value="删除" /> <input name="" type="button" value="清空" /> <input name="" type="button" value="导出" />      
              </div>
              <div class="fr pagef">
                  <a href="#">上一页</a> <a href="#">下一页</a> <input name="" type="text" /> /6
              </div>
              <div class="clear"></div>
          </div>
     
         <!--user info--> 
         <!--user info end--> 
        
        
        <!-------------订单---------------->
         <div id="recommend" class="m10 recommend" style="display: block;">
                
                
                <table width="100%" cellspacing="0" cellpadding="0" class="buytable mailtab">
                    <tbody><tr>
                        <th width="30"><input name="" type="checkbox" value="" /></th>
                        <th width="30"></th>
                        <th width="120">发件人</th>
                        <th >标题</th>
                        <th width="100">发送时间</th>                        
                    </tr>
                    
                     <tr>
                          <td><input name="" type="checkbox" value="" /></td>
                          <td><img src="../images/mes_icon.jpg" width="16" height="11" /></td>
                          <td><a href="#">lgllliu</a></td>
                          <td><a href="mailinfo.html">阿德法撒旦法的司法的司法的</a></td>
                          <td>2012-02-13</td>
                          
                      </tr>
                      <tr>
                          <td><input name="" type="checkbox" value="" /></td>
                          <td><img src="../images/mes_icon.jpg" width="16" height="11" /></td>
                          <td><a href="#">lgllliu</a></td>
                          <td><a href="mailinfo.html">阿德法撒旦法的司法的司法的</a></td>
                          <td>2012-02-13</td>
                          
                      </tr>
                      <tr>
                          <td><input name="" type="checkbox" value="" /></td>
                          <td><img src="../images/mes_icon.jpg" width="16" height="11" /></td>
                          <td><a href="#">lgllliu</a></td>
                          <td><a href="mailinfo.html">阿德法撒旦法的司法的司法的</a></td>
                          <td>2012-02-13</td>
                          
                      </tr>
                      <tr>
                          <td><input name="" type="checkbox" value="" /></td>
                          <td> </td>
                          <td><a href="#">lgllliu</a></td>
                          <td><a href="mailinfo.html">阿德法撒旦法的司法的司法的</a></td>
                          <td>2012-02-13</td>
                          
                      </tr>
                      <tr>
                          <td><input name="" type="checkbox" value="" /></td>
                          <td> </td>
                          <td><a href="#">lgllliu</a></td>
                          <td><a href="mailinfo.html">阿德法撒旦法的司法的司法的</a></td>
                          <td>2012-02-13</td>
                          
                      </tr>
                      <tr>
                          <td><input name="" type="checkbox" value="" /></td>
                          <td> </td>
                          <td><a href="#">lgllliu</a></td>
                          <td><a href="mailinfo.html">阿德法撒旦法的司法的司法的</a></td>
                          <td>2012-02-13</td>
                          
                      </tr>
                      <tr>
                          <td><input name="" type="checkbox" value="" /></td>
                          <td> </td>
                          <td><a href="#">lgllliu</a></td>
                          <td><a href="mailinfo.html">阿德法撒旦法的司法的司法的</a></td>
                          <td>2012-02-13</td>
                          
                      </tr>
                      <tr>
                          <td><input name="" type="checkbox" value="" /></td>
                          <td> </td>
                          <td><a href="#">lgllliu</a></td>
                          <td><a href="mailinfo.html">阿德法撒旦法的司法的司法的</a></td>
                          <td>2012-02-13</td>
                          
                      </tr>
                      <tr>
                          <td><input name="" type="checkbox" value="" /></td>
                          <td> </td>
                          <td><a href="#">lgllliu</a></td>
                          <td><a href="mailinfo.html">阿德法撒旦法的司法的司法的</a></td>
                          <td>2012-02-13</td>
                          
                      </tr>
                      <tr>
                          <td><input name="" type="checkbox" value="" /></td>
                          <td> </td>
                          <td><a href="#">lgllliu</a></td>
                          <td><a href="mailinfo.html">阿德法撒旦法的司法的司法的</a></td>
                          <td>2012-02-13</td>                          
                      </tr>
                         
                          
                </tbody></table>				
				              		
	     </div>
        <!-------------订单end---------------->       
       
     </div>
    <!-----------right_con end-------------->
    
    
    <div class="clear"></div>
 </div>
<!----两栏end---->