<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>团购</title>
<link type="text/css" href="<ls:templateResource item='/common/red/css/legend.css'/>" rel="stylesheet"/>
</head>

<body>
<%@ include file="top.jsp" %>

<!----地址---->
 <div class="w addr">
   <span><a href="#">首页</a></span>&gt;<span>团购</span> 
 </div>
<!----地址end---->
<!----两栏---->
 <div class="w"> 
    <!----右边---->
    <div class="t_right">
      <div class="sidebox">
        <div class="boxtit">
           <span><a href="#">更多 &gt;&gt;</a></span><b>客户服务</b>
         </div>
         <div class="phone">
           <p class="pimg"><img src="${pageContext.request.contextPath}/img/group/phone.png" width="75" height="56" /></p>
           <div class="pword">
             <p class="phonenumber">400-100-1234</p>
             <p class="phonetime">周一至周日 9:00-20:00</p>
           </div>
           <div class="clear"></div>
        </div>
         <div class="word">
           <p><span><img src="${pageContext.request.contextPath}/img/group/dot.gif" width="7" height="7" /></span>
           <span>10天无条件付款+先行赔付</span></p>
           <p><span><img src="${pageContext.request.contextPath}/img/group/dot.gif" width="7" height="7" /></span>
           <span>10天无条件付款+先行赔付</span></p>
         </div>
         <div class="clear"></div>
      </div>
      
      <div class="sidebox mar12">
        <div class="boxtit">
           <span><a href="#">更多 &gt;&gt;</a></span><b>常见问题</b>
        </div>
         <ul class="lista listimg1">
            <li><a href="#">如何付款，安全么？</a> </li>
            <li><a href="#">如果参加团购人数不足，怎么办？</a> </li>
            <li><a href="#">今天的团购看起来不错，怎么购买？</a> </li> 
            <li><a href="#">如何付款，安全么？</a> </li> 
            <li><a href="#">如果参加团购人数不足，怎么办？</a> </li>
            <li><a href="#">今天的团购看起来不错，怎么购买？</a> </li>  
            <li><a href="#">如果参加团购人数不足，怎么办？</a> </li>
         </ul>         
      </div>
      
      <div class="sidebox mar12" style="border:1px solid #fff;"></div>
       
       
    </div>
<!----右边end---->
    
    <!----左边---->
    <div class="t_wrapbig">
    <div class="t_wrap">
    
       <p class="w_title">常见问题</p>
      <div class="along_q">
       
      </div>
     
      <div class="question">
        <span>如何付款？安全吗？</span>
      <img src="${pageContext.request.contextPath}/img/group/sanjiao.gif" width="16" height="14" />
      </div>
      <div class="answer">每天提供多单精品消费，为您精选餐厅、酒吧、KTV、SPA、美发店、瑜伽馆等特色商家，只要凑够最低团购人数就能享受无敌折扣。详情请看美团是什么。 
      </div>
      
       <div class="question">
        <span>如何付款？安全吗？</span>
      <img src="${pageContext.request.contextPath}/img/group/sanjiaod.gif" width="16" height="14" />
      </div>
      
      <div class="question">
        <span>如何付款？安全吗？</span>
      <img src="${pageContext.request.contextPath}/img/group/sanjiaod.gif" width="16" height="14" />
      </div>
      
      <div class="question">
        <span>如何付款？安全吗？</span>
      <img src="${pageContext.request.contextPath}/img/group/sanjiaod.gif" width="16" height="14" />
      </div>
     
             
          
   <div class="linel"></div> 
         
         <div class="clear"></div>
         
         <div class="fanye">
          <span class="ysel">1</span>
          <span class="nsel"><a href="#">2</a></span>
          <span class="nsel">3</span>
          <span class="nsel">4</span>
          <span class="nsel">5</span>
          <span class="nsel">6</span>
          <span class="nsel">下一页</span>
          <span class="nsel">尾页</span>
         
         </div>     
     
    </div></div>
    <!----左边end---->
    
   <div class="clear"></div>
</div>
<!----两栏end---->

<%@ include file="foot.jsp" %>
 
</body>
</html>
