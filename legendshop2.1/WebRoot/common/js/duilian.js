var showad = true;
var Toppx = 60;         //上端位置
var AdDivW = 100;       //宽度
var AdDivH = 300;       //高度
var PageWidth = 800;    //页面多少宽度象素下正好不出现左右滚动条
var MinScreenW = 1024;  //显示广告的最小屏幕宽度象素
var ClosebuttonHtml = '<div align="right" style="position: absolute;top:0px;right:0px;margin:2px;padding:2px;z-index:2000;"><a href="javascript:;" onclick="hidead()" style="color:red;text-decoration:none;font-size:12px;">关闭</a></div>'
var AdContentHtml = '<div align="center" style="color:green;font-size:23pt;font-family:黑体;"> <a href="http://www.lanrentuku.com/" target="_blank"><img src="images/duilian.jpg" width="100" height="300" border="0"></a></div>';
document.write ('<div id="Javascript.LeftDiv" style="position: absolute;border: 1px solid #336699;background-color:#EEEEE2;z-index:1000;width:'+AdDivW+'px;height:'+AdDivH+'px;top:-1000px;word-break:break-all;display:none;">'+ClosebuttonHtml+'<div>'+AdContentHtml+'</div></div>');
document.write ('<div id="Javascript.RightDiv" style="position: absolute;border: 1px solid #336699;background-color:#EEEEE2;z-index:1000;width:'+AdDivW+'px;height:'+AdDivH+'px;top:-1000px;word-break:break-all;display:none;">'+ClosebuttonHtml+'<div>'+AdContentHtml+'</div></div>');
function scall(){
if(!showad){return;}
if (window.screen.width<MinScreenW){
alert("临时提示：\n\n显示器分辨率宽度小于"+MinScreenW+",不显示广告");
showad = false;
document.getElementById("Javascript.LeftDiv").style.display="none";
document.getElementById("Javascript.RightDiv").style.display="none";
return;
}
var Borderpx = ((window.screen.width-PageWidth)/2-AdDivW)/2;
document.getElementById("Javascript.LeftDiv").style.display="";
document.getElementById("Javascript.LeftDiv").style.top=document.body.scrollTop+Toppx;
document.getElementById("Javascript.LeftDiv").style.left=document.body.scrollLeft+Borderpx;
document.getElementById("Javascript.RightDiv").style.display="";
document.getElementById("Javascript.RightDiv").style.top=document.body.scrollTop+Toppx;
document.getElementById("Javascript.RightDiv").style.left=document.body.scrollLeft+document.body.clientWidth-document.getElementById("Javascript.RightDiv").offsetWidth-Borderpx;
}
function hidead()
{
showad = false;
document.getElementById("Javascript.LeftDiv").style.display="none";
document.getElementById("Javascript.RightDiv").style.display="none";
}
window.onscroll=scall;
window.onresize=scall;
window.onload=scall;