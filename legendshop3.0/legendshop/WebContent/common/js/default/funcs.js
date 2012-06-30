
var CurScreen ;
var refreshSwitchTimer;

function switchPic(screen) {
	if (screen > MaxScreen) {
		screen = 1 ;
	}
	
	for (i=1;i<=MaxScreen;i++) {
		document.getElementById("Switch_"+i).style.display = "none" ;
	}
	document.getElementById("Switch_"+screen).style.display = "block" ;
	showSwitchNav(screen);
	showSwitchTitle(screen);
	//Effect.Appear("Switch_"+screen);
			
	//switchLittlePic(screen);
	//showSwitchTitles(screen);
	CurScreen = screen  ;
}
function showSwitchNav(screen) {
	var NavStr = "" ;
	for (i=1;i<=MaxScreen;i++) {
		if (i == screen) {
			NavStr += '<li onmouseover="pauseSwitch();" onmouseout="goonSwitch();"><a href="javascript://" target="_self" class="sel">'+i+'</a></li>' ;
		}
		else {
			NavStr += '<li onmouseover="pauseSwitch();" onmouseout="goonSwitch();" onclick="goManSwitch('+i+');"><a href="javascript://" target="_self">'+i+'</a></li>' ;
		}
		
	}
	document.getElementById("SwitchNav").innerHTML = NavStr ;
}
function showSwitchTitle(screen) {
	var titlestr = "" ;
	titlestr = '<h3><a href="'+Switcher[screen]['link']+'" target="_blank">'+Switcher[screen]['title']+'</a></h3><p><a href="'+Switcher[screen]['link']+'" target="_blank">'+Switcher[screen]['stitle']+'</a></p>' ;
	document.getElementById("SwitchTitle").innerHTML = titlestr ;
}
function reSwitchPic() {
	refreshSwitchTimer = null;
	switchPic(CurScreen+1);
	refreshSwitchTimer = setTimeout('reSwitchPic()', 6000);
}

function pauseSwitch() {
    if(refreshSwitchTimer != null){
       clearTimeout(refreshSwitchTimer);
    }
}

function goonSwitch() {
    if(refreshSwitchTimer != null){
       clearTimeout(refreshSwitchTimer);
    }
    refreshSwitchTimer = setTimeout('reSwitchPic();', 6000);
}

function goManSwitch(index) {
    if(refreshSwitchTimer != null){
       clearTimeout(refreshSwitchTimer);
    }
	CurScreen = index - 1 ;
	reSwitchPic();
}
