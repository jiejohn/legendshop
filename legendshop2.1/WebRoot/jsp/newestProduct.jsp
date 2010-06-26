<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%session.setAttribute("root", request.getContextPath());%>
<%@include file='/common/jsp/taglib.jsp'%>
<div class="rollBox">
     <div class="LeftBotton" onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()" onmouseout="ISL_StopUp()"></div>
     <div class="Cont" id="ISL_Cont">
      <div class="ScrCont">
       <div id="List1">
       
        <!-- 图片列表 begin -->   
         <c:forEach items="${requestScope.newestList}" var="hw" varStatus="status"> 
         <div class="pic">
            <a href="${root}/views/${hw.hwId}"><img src="${SMALL_PHOTO_PATH}/${hw.hwPic}" width="112" height="90" alt="${hw.hwName}"/></a>  
          
          <font color="red" style="font-weight: bold;"><fmt:formatNumber type="currency" pattern="￥0" value="${hw.hwCash}"/></font><br>
          <font color="#006699"><a href="${root}/views/${hw.hwId}" target="_blank">${hw.hwName}</a></font>
           </div>    
        
        </c:forEach>
        <!-- 图片列表 end -->
        
       </div>
       <div id="List2"></div>
      </div>
     </div>
     <div class="RightBotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()" onmouseout="ISL_StopDown()"></div>
    </div>
<style type="text/css">
<!--
.rollBox{width:580px;overflow:hidden;padding:4px 0 4px 0px;}
.rollBox .LeftBotton{height:52px;width:19px;background:url(${root}/jsp/img/left_right.gif) no-repeat 11px 0;overflow:hidden;float:left;display:inline;margin:25px 0 0 0;cursor:pointer;}
.rollBox .RightBotton{height:52px;width:19px;background:url(${root}/jsp/img/left_right.gif) no-repeat -8px 0;overflow:hidden;float:left;display:inline;margin:25px 0 0 0;cursor:pointer;}
.rollBox .Cont{width:539px;overflow:hidden;float:left;}
.rollBox .ScrCont{width:10000000px;}
.rollBox .Cont .pic{width:135px;float:left;text-align:center;}
.rollBox .Cont .pic img{padding:4px;background:#fff;border:1px solid #ccc;display:block;margin:0 auto;}
.rollBox .Cont .pic p{line-height:26px;color:#505050;}
.rollBox .Cont a:link,.rollBox .Cont a:visited{color:#626466;text-decoration:none;}
.rollBox .Cont a:hover{color:#f00;text-decoration:underline;}
.rollBox #List1,.rollBox #List2{float:left;}
-->
</style>
<script language="javascript" type="text/javascript">
<!--//--><![CDATA[//><!--
//图片滚动列表 mengjia 070816
var Speed = 10; //速度(毫秒)
var Space = 5; //每次移动(px)
var PageWidth = 135; //翻页宽度
var fill = 0; //整体移位
var MoveLock = false;
var MoveTimeObj;
var Comp = 0;
var AutoPlayObj = null;
GetObj("List2").innerHTML = GetObj("List1").innerHTML;
GetObj('ISL_Cont').scrollLeft = fill;
GetObj("ISL_Cont").onmouseover = function(){clearInterval(AutoPlayObj);}
GetObj("ISL_Cont").onmouseout = function(){AutoPlay();}
AutoPlay();
function GetObj(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval('document.all.'+objName)}}
function AutoPlay(){ //自动滚动
 clearInterval(AutoPlayObj);
 AutoPlayObj = setInterval('ISL_GoDown();ISL_StopDown();',5000); //间隔时间
}
function ISL_GoUp(){ //上翻开始
 if(MoveLock) return;
 clearInterval(AutoPlayObj);
 MoveLock = true;
 MoveTimeObj = setInterval('ISL_ScrUp();',Speed);
}
function ISL_StopUp(){ //上翻停止
 clearInterval(MoveTimeObj);
 if(GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0){
  Comp = fill - (GetObj('ISL_Cont').scrollLeft % PageWidth);
  CompScr();
 }else{
  MoveLock = false;
 }
 AutoPlay();
}
function ISL_ScrUp(){ //上翻动作
 if(GetObj('ISL_Cont').scrollLeft <= 0){GetObj('ISL_Cont').scrollLeft = GetObj('ISL_Cont').scrollLeft + GetObj('List1').offsetWidth}
 GetObj('ISL_Cont').scrollLeft -= Space ;
}
function ISL_GoDown(){ //下翻
 clearInterval(MoveTimeObj);
 if(MoveLock) return;
 clearInterval(AutoPlayObj);
 MoveLock = true;
 ISL_ScrDown();
 MoveTimeObj = setInterval('ISL_ScrDown()',Speed);
}
function ISL_StopDown(){ //下翻停止
 clearInterval(MoveTimeObj);
 if(GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0 ){
  Comp = PageWidth - GetObj('ISL_Cont').scrollLeft % PageWidth + fill;
  CompScr();
 }else{
  MoveLock = false;
 }
 AutoPlay();
}
function ISL_ScrDown(){ //下翻动作
 if(GetObj('ISL_Cont').scrollLeft >= GetObj('List1').scrollWidth){GetObj('ISL_Cont').scrollLeft = GetObj('ISL_Cont').scrollLeft - GetObj('List1').scrollWidth;}
 GetObj('ISL_Cont').scrollLeft += Space ;
}
function CompScr(){
 var num;
 if(Comp == 0){MoveLock = false;return;}
 if(Comp < 0){ //上翻
  if(Comp < -Space){
   Comp += Space;
   num = Space;
  }else{
   num = -Comp;
   Comp = 0;
  }
  GetObj('ISL_Cont').scrollLeft -= num;
  setTimeout('CompScr()',Speed);
 }else{ //下翻
  if(Comp > Space){
   Comp -= Space;
   num = Space;
  }else{
   num = Comp;
   Comp = 0;
  }
  GetObj('ISL_Cont').scrollLeft += num;
  setTimeout('CompScr()',Speed);
 }
}
//--><!]]>
</script>
