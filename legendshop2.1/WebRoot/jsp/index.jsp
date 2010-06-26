<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="bingosoft.jcf.sql.ConfigCode"%>
<%@include file='/common/jsp/taglib.jsp'%>
<%@ include file="top.jsp"%>
<script type="text/javascript" src="${root}/common/js/funcs.js"></script>
<div id="bodyer">
<table width="950px" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px">
  <tr>
    <td valign="top" align="left">
      <%@ include file="notice.jsp"%>
      <%@ include file="topnews.jsp"%>
     </td>
  <td width="530px" valign="top">
   <div class="flashNews" style="width: 530px">
		<div id="Switch_1"><a id="imglink1" target="_blank" ><img id="img1" width="530px" height="290px" onmouseover="pauseSwitch();" onmouseout="goonSwitch();"/></a></div>
        <div id="Switch_2" style="display:none;"><a id="imglink2" target="_blank"><img id="img2"  width="530px" height="290px" onmouseover="pauseSwitch();"  onmouseout="goonSwitch();"/></a></div>
        <div id="Switch_3" style="display:none;"><a id="imglink3" target="_blank"><img id="img3" width="530px" height="290px" onmouseover="pauseSwitch();"  onmouseout="goonSwitch();"/></a></div>
        <div id="Switch_4" style="display:none;"><a id="imglink4" target="_blank"><img id="img4" width="530px" height="290px" onmouseover="pauseSwitch();"  onmouseout="goonSwitch();"/></a></div>
        <div id="Switch_5" style="display:none;"><a id="imglink5" target="_blank"><img id="img5" width="530px" height="290px" onmouseover="pauseSwitch();"  onmouseout="goonSwitch();"/></a></div>
        <div id="Switch_6" style="display:none;"><a id="imglink6" target="_blank"><img id="img6" width="530px" height="290px" onmouseover="pauseSwitch();"  onmouseout="goonSwitch();"/></a></div>
        <div id="Switch_7" style="display:none;"><a id="imglink7" target="_blank"><img id="img7" width="530px" height="290px" onmouseover="pauseSwitch();"  onmouseout="goonSwitch();"/></a></div>
        <div id="Switch_8" style="display:none;"><a id="imglink8" target="_blank"><img id="img8" width="530px" height="290px"  onmouseover="pauseSwitch();"  onmouseout="goonSwitch();"/></a></div>
        <div id="SwitchTitle" onmouseover="pauseSwitch();"  onmouseout="goonSwitch();"></div>
		<ul id="SwitchNav" style="margin: 0px"></ul>
		<div class="bg" onmouseover="pauseSwitch();"  onmouseout="goonSwitch();"></div>
    </div>
    </td>
    <td valign="top" width="205px"><%@ include file="newproducts.jsp"%></td>
  </tr>
</table>

<table width="950px" cellspacing="0" cellpadding="0" align="center"  style="margin-bottom: 5px">
  <tr> 
    <td valign="top" align="center"> 
      <%@ include file="recommend.jsp"%>
    </td>
  </tr>
</table>
<table width="950px" cellspacing="0" cellpadding="0" align="center" >
  <tr> 
    <td valign="top" align="center"> 
      <%@ include file="copy.jsp"%>
    </td>
  </tr>
</table>
</div>
<script type="text/javascript">
<!--
var showad = '${not empty COUPLET}' == 'true';
var Toppx = 100;         
var AdDivW = 110;       
var AdDivH = 300;       
var PageWidth = 980;   
var MinScreenW = 1024;  
if(showad){
	var ClosebuttonHtml = '<div align="right" style="position: absolute;top:0px;right:0px;margin:2px;padding:0px;z-index:2000;"><a href="javascript:void(0)" onclick="hidead()" style="color:black;text-decoration:none;font-size:12px;"><img src="${root}/img/close.gif"/></a></div>'
	var AdContentHtml = '<div align="center" style="color:green;font-size:23pt;font-family:黑体;"><a href="${COUPLET.linkUrl}" target="_blank"><img src="${PHOTO_PATH}/${COUPLET.picUrl}" width="110px" border="0"></a></div>';
	document.write('<div id="coupletLeftDiv"  style="position: absolute;border: 1px solid #CCCCCC; background-color:#EEEEE2;z-index:1000;width:'+AdDivW+'px;top:-1000px;word-break:break-all;display:none;">'+ClosebuttonHtml+'<div>'+AdContentHtml+'</div></div>');
	document.write('<div id="coupletRightDiv" style="position: absolute;border: 1px solid #CCCCCC; background-color:#EEEEE2;z-index:1000;width:'+AdDivW+'px;top:-1000px;word-break:break-all;display:none;">'+ClosebuttonHtml+'<div>'+AdContentHtml+'</div></div>');
}
function scallWin(){
    if(!showad){return;}
    if (window.screen.width<=MinScreenW){
        showad = false;
        document.getElementById("coupletLeftDiv").style.display="none";
        document.getElementById("coupletRightDiv").style.display="none";
        return;
    }
    
    var Borderpx = ((window.screen.width-PageWidth)/2-AdDivW)/2;
    var x,y;
    if(document.body.scrollTop){
           x=document.body.scrollLeft;
           y=document.body.scrollTop;
    }else{
           x=document.documentElement.scrollLeft;
           y=document.documentElement.scrollTop;
    }

    document.getElementById("coupletLeftDiv").style.display="block";
    document.getElementById("coupletLeftDiv").style.top=y+Toppx+"px";
    document.getElementById("coupletLeftDiv").style.left=x+Borderpx+"px";

    document.getElementById("coupletRightDiv").style.display="block";
    document.getElementById("coupletRightDiv").style.top=y+Toppx+"px";
    document.getElementById("coupletRightDiv").style.left=x+document.body.clientWidth-document.getElementById("coupletRightDiv").offsetWidth-Borderpx+"px";

}

function hidead()
{
    showad = false;
    document.getElementById("coupletLeftDiv").style.display="none";
    document.getElementById("coupletRightDiv").style.display="none";
}

   //window.onscroll=scallWin;
    window.onresize=scallWin;
    window.onload=scallWin;

        //indexJpg
        var Switcher = new Array();
        var MaxScreen = '${MaxScreen}' ;
        var dataArray = eval('${indexJSON}');
        if(dataArray!="undefined" || dataArray != null){
        for(var i in dataArray){
                     MaxScreen = dataArray.length;
                     var ii = parseInt(i);
                     var data = dataArray[i] ;
                     Switcher[ii+1] = Array();
                     Switcher[ii+1]['title'] = data.title ;
                     Switcher[ii+1]['stitle'] = data.stitle ;
                     Switcher[ii+1]['link'] = data.titleLink ;
                     var imgorder = "img"+(ii+1);
                     var imgs = document.getElementById(imgorder);
                     imgs.src = '${PHOTO_PATH}' + '/' + data.img;
                     imgs.alt = data.alt;
                     var linkorder = "imglink"+(ii+1); 
                     var links = document.getElementById(linkorder);
                     links.href = data.link;
            }
        }
                  if(MaxScreen == 0){
                     Switcher[1] = Array();
                     Switcher[1]['title'] = 'LegendShop - 网上购物平台';
                     Switcher[1]['stitle'] = '基于J2EE的MVC架构的高性能、独立可配置的多用户多商家的网购平台' ;
                     Switcher[1]['link'] = '${DOMAIN_NAME}' ;
                     var imgorder = "img1";
                     var imgs = document.getElementById(imgorder);
                     imgs.src = '${root}/img/common.jpg';
                     imgs.alt = '基于J2EE的MVC架构的高性能、独立可配置的多用户多商家的网购平台';
                
                     var linkorder = "imglink1";
                     var links = document.getElementById(linkorder);
                     links.href = '${DOMAIN_NAME}';
                     MaxScreen = 1;
                    
           }
            switchPic(1);
            refreshSwitchTimer = setTimeout('reSwitchPic()', 6000);
            
</script>
</body>
</html>