var dwrChildSelectId;
var dwrChildSelectIsRequired;
var callAfterCallBack;
var defaultDisp;

function changeLinkedOptions(childSelectId, isReiquired, sql, callAfter) {
	dwrChildSelectId = childSelectId;
    dwrChildSelectIsRequired = isReiquired;
    callAfterCallBack = callAfter;
    defaultDisp = ' - 请选择 - ';
    optionService.getLinkedOptionsBySql(sql, fillSelect);
}

function changeLinkedOptions1(childSelectId, isReiquired, sql, defaultDisplay, callAfter) {
	dwrChildSelectId = childSelectId;
    dwrChildSelectIsRequired = isReiquired;
    callAfterCallBack = callAfter;
    defaultDisp = defaultDisplay;
    optionService.getLinkedOptionsBySql(sql, fillSelect);
}

function changeLinkedOptions2(sql, callAfter) {
    optionService.getLinkedOptionsBySql(sql, callAfter);
}

function fillSelect(data) {
	dwr.util.removeAllOptions(dwrChildSelectId);
	if(dwrChildSelectIsRequired == false || !data || data.length == 0) {
		dwr.util.addOptions(dwrChildSelectId, [{ value:'', disp : defaultDisp }], "value", "disp");
	}
	dwr.util.addOptions(dwrChildSelectId, data);
	//dwr.util.addOptions(dwrChildSelectId,[{name:"first",value:"1"},{name:"second",value:"2"},{name:"third",value:"3"}],"value","name") ;
	if(callAfterCallBack) {
		callAfterCallBack();
	}
}

