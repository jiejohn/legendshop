/**
 * Core4j Javascript
 * @author LK
 * @email lannerk@qq.com
 * @date 2010-5-5
 * @releaseDate 2010-6-20 12:59
 * @license apache licence V2.0
 */

/** Core4j class */
var Core4j = {};

/**
 * Object browser in class Core4j
 */
var _tmp_ua_lc_core4j = navigator.userAgent.toLowerCase();

Core4j.browser = {
    userAgent: navigator.userAgent,
    version: (_tmp_ua_lc_core4j.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1],
    safari: /webkit/.test(_tmp_ua_lc_core4j),
    opera: /opera/.test(_tmp_ua_lc_core4j),
    msie: /msie/.test(_tmp_ua_lc_core4j) && !/opera/.test(_tmp_ua_lc_core4j),
    mozilla: /mozilla/.test(_tmp_ua_lc_core4j) &&
    !/(compatible|webkit)/.test(_tmp_ua_lc_core4j)
};



/**
 * toString method in browser object
 */
Core4j.browser.toString = function(){
    var str = "unknow";
    if (Core4j.browser.safari) {
        str = "safari";
    }
    if (Core4j.browser.opera) {
        str = "opera";
    }
    if (Core4j.browser.msie) {
        str = "msie";
    }
    if (Core4j.browser.mozilla) {
        str = "mozilla";
    }
    return str + "/" + Core4j.browser.version;
};

/**
 * package namespace register method param:String package path
 * exp:'com.devision.js'
 */
Core4j.packageRegister = function(packagepath){
    var packageArray = packagepath.split('.');
    var str_eval = "";
    var str_package = "";
    var packageArray_length = packageArray.length;
    for (var i = 0; i < packageArray_length; i++) {
        if (i != 0) {
            str_package += ".";
        }
        str_package += packageArray[i];
        str_eval += "if (typeof(" + str_package + ") == 'undefined') " +
        str_package +
        " = new Object();"
    }
    if (str_eval != "") {
        eval(str_eval);
    }
};

Core4j.packageRegister("Core4j.util");

/**
 * hash map
 */
Core4j.util.HashMap = function(){
    this.length = 0;
    this.container = {};
    //this.keySet=[];

    this.put = function(key, value){
        try {
            key = "_" + key.toString();
            if (this.container[key] == null) {
                this.container[key] = value;
                this.length++;
                //this.keySet[length]=key;
            }
        }
        catch (e) {
            return false;
        }
        return true;
    };

    this.get = function(key){
        var value = null;
        try {
            key = "_" + key.toString();
            if (this.container[key])
                value = this.container[key];
        }
        catch (e) {
            return null;
        }
        return value
    };

    this.remove = function(key){
        key = "_" + key.toString();
        if (this.container[key] != null) {
            this.container[key] = null;
            this.length--;
        }
    };

    this.clear = function(){
        this.container = {};
        this.length = 0;
    };

    this.hasKey = function(key){
        try {
            key = "_" + key.toString();
            if (this.container[key]) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (e) {
            return false;
        }
    };

    this.hasValue = function(value){
        try {
            for (var p in this.container) {
                if (this.container[p] === value)
                    return true;
            }
            return false;
        }
        catch (e) {
            return e;
        }
    };

    this.isEmpty = function(){
        if (this.length == 0) {
            return true;
        }
        else {
            return false;
        }
    };
};



//cookies
Core4j.setCookie = function(name, value, timeSet){
    var time = timeSet;
    if (time == null || time == "") {
        time = 30 * 24 * 60 * 60 * 1000;
    }
    var exp = new Date();
    exp.setTime(exp.getTime() + time);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
};

Core4j.getCookie = function(cookieName){
    var cookieString = document.cookie;
    //var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    var start = cookieString.indexOf(cookieName + '=');
    if (start == -1) {
        return null;
    }
    start += cookieName.length + 1;
    var end = cookieString.indexOf(';', start);
    if (end == -1){
		 return unescape(cookieString.substring(start));
	}
    return unescape(cookieString.substring(start, end));
};

Core4j.removeCookie = function(name){
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = Core4j.getCookie(name);
    if (cval != null){
		 document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
	}
};

Core4j.removeAllCookie = function(){
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cookieString = document.cookie;
    var cookiesArray = cookieString.split("; ");
    if (cookiesArray.length != 0 && cookiesArray[0] != "") {
        for (var i = 0; i < cookiesArray.length; i++) {
            document.cookie = cookiesArray[i] + ";expires=" + exp.toGMTString();
        }
    }
};

Core4j.packageRegister("Core4j.Domhelper");

Core4j.Domhelper.ElEventType = {
    CLICK: 'click'
};

Core4j.Domhelper.addEventToEl = function(elObj, eventType, eventFunc){
	if (elObj != null && eventType != null) {
		if (elObj.attachEvent) {
			elObj.attachEvent("on" + eventType, eventFunc);
		}
		else {
			elObj.addEventListener(eventType, eventFunc, false);
		}
	}


};

Core4j.Domhelper.removeEventFromEl = function(elObj, eventType, eventFunc){
	if (elObj != null && eventType != null) {
		if (elObj.detachEvent) {
			alert("ie remove["+"on" + eventType+"]");
			elObj.detachEvent("on" + eventType, eventFunc);
		}
		else {
			alert("remove ["+"on" + eventType+"]");
			elObj.removeEventListener(eventType, eventFunc, false);
		}
	}
};


Core4j.Domhelper.getResoureRoot = function(elTag, fileName){
    var els = document.getElementsByTagName(elTag);
    for (var i = 0, n = els.length; i < n; i++) {
        var tmp = els[i];
        var path = tmp.getAttribute('src');
        if (path == null || path == '') {
            path = tmp.getAttribute('href');
        }
        if ((path != null && path != '')) {
            path = path.toLowerCase();
            var index = path.indexOf(fileName.toLowerCase());
            if (index != -1) {
                return path.substring(0, index);
            }
        }
    }
    return null;
};




Core4j.Domhelper.getElAttributeValue = function(elObj, attributeName){
    if (!elObj.tagName && typeof elObj.length != "undefined") {
        elObj = elObj[0];
    }
    if (!elObj) {
        return null;
    }
    if (attributeName == "for") {
        return elObj.htmlFor;
    }
    if (attributeName == "class" || attributeName == "className") {
        return elObj.className;
    }
    return elObj.getAttribute(attributeName) || elObj[attributeName];

};

Core4j.Domhelper.setElAttributeValue=function(elobj,attributeName,value){
	if(value!=null){
	    if (attributeName == "class" || attributeName == "className" || attributeName == "classStyle") {
	    	elobj.className=value;
	    }else if(attributeName=="style"&&Core4j.browser.msie){
	    	elobj.style.cssText=value;
	    }else{
	    	elobj.setAttribute(attributeName,value);
	    }
	}
};

Core4j.Domhelper.setElAttributes=function(elobj,attributeNames,valueObject){
    if (attributeNames != null && attributeNames.length > 0&&valueObject!=null) {
        for (var i = 0, n = attributeNames.length; i < n; i++) {
            var name = attributeNames[i];
            var value = eval("valueObject." + name);
            if (value != null) {
            	Core4j.Domhelper.setElAttributeValue(elobj,name,value);
                /*if (name == 'classStyle' || name == 'className' || name == 'class') {
                    //name='className';
                    el.className = value;
                }
                else {
                    el.setAttribute(name, value);
                }*/
            }
        }
    }
};

Core4j.Domhelper.renderEl=function(elobj,renderFunction,value){
	var showvalue=null;
	if(renderFunction!=null&&typeof(renderFunction)=='function'){
		showvalue=renderFunction(value,elobj);
	}else{
		showvalue=value;
	}
	if (showvalue != null) {
		if (typeof(showvalue) == 'object') {
			if (Core4j.checkIsArray(showvalue)) {
				for (var q = 0, w = showvalue.length; q < w; q++) {
					var tmpobj = showvalue[q];
					if (typeof(tmpobj) == 'object') {
						elobj.appendChild(tmpobj);
					}
				}
			}
			else {
				elobj.appendChild(showvalue);
			}
		}
		else {
			elobj.innerHTML = showvalue;
		}
	}
};

//in the valueObject ,the attribute name 'class' is a keyword in ie,so change to 'classStyle' or 'className'
Core4j.Domhelper.createElement = function(elTag, attributeConfigObject,parentNode){
    var el = document.createElement(elTag);
	if(parentNode!=null){
		parentNode.appendChild(el);
	}
    if (attributeConfigObject != null) {
        var attributeNames = attributeConfigObject.attributeNames;
        var valueObject = attributeConfigObject.valueObject;
        Core4j.Domhelper.setElAttributes(el,attributeNames,valueObject);
    }

    return el;
};

Core4j.Domhelper.createAndInsertElementBeforeElement=function(elTag, attributeConfigObject,befele){
    var el = document.createElement(elTag);
	if(befele!=null){
		befele.parentNode.insertBefore(el,befele);
	}
    if (attributeConfigObject != null) {
        var attributeNames = attributeConfigObject.attributeNames;
        var valueObject = attributeConfigObject.valueObject;
        Core4j.Domhelper.setElAttributes(el,attributeNames,valueObject);
    }

    return el;
};


Core4j.Domhelper.createAndInsertElementBeforeIndexTagNode=function(elTag, attributeConfigObject,parentNode,beforeindex){
	if(parentNode!=null){
	    var el = document.createElement(elTag);
	    var childs=parentNode.childNodes;
	    if(childs.length==0){
	    	parentNode.appendChild(el);
	    }else{
	    	//alert(childs.length);
	    	if(beforeindex>(childs.length-1)){
	    		parentNode.appendChild(el);
	    	}else{
		    	var bfNode=null;
		    	var bindex=0;
			    for(var i=0,n=childs.length;i<n;i++){
			    	if(childs[i].tagName!=null){
			    		if(beforeindex<=bindex){
			    			beforeindex=i;
			    			break;
			    		}
			    		bindex++;
			    	}
			    }
		    	bfNode=childs[beforeindex];
		    	parentNode.insertBefore(el,bfNode);
	    	}
	    }
	    if (attributeConfigObject != null) {
	        var attributeNames = attributeConfigObject.attributeNames;
	        var valueObject = attributeConfigObject.valueObject;
	        Core4j.Domhelper.setElAttributes(el,attributeNames,valueObject);
	    }
	    return el;
	}
};

Core4j.Domhelper.removeElement = function(eleObject){
    if (eleObject != null && eleObject.parentNode != null) {
        eleObject.parentNode.removeChild(eleObject);
    }
};

Core4j.Domhelper.removeAllElement = function(eleObject){
    if (eleObject != null && eleObject.parentNode != null) {
        eleObject.innerHTML="";
    }
};

/**insert a row to a table
 * @param index the row index
 * @param rowFunction the function init the row(TR)
 */
Core4j.Domhelper.insertRow = function(tableObject, index, rowFunction){
    var rowObject = null;
    if (index != null) {
        rowObject = tableObject.insertRow(index);
    }
    else {
        rowObject = tableObject.insertRow(rowObject.rows.length);
    }
    rowFunction(rowObject);
};

/**insert a cell to a row
 * @param index the cell index
 * @param rowObject the row object
 * @param cellFunction the function init the cell(TD)
 */
Core4j.Domhelper.insertCell = function(index, rowObject, cellFunction){
    var cellObject = null;
    if (index != null) {
        cellObject = rowObject.insertCell(index);
    }
    else {
        cellObject = rowObject.insertCell(rowObject.cells.length);
    }
    cellFunction(cellObject);
};

Core4j.Domhelper.hasClass = function(ele, cls){
    //var className=ele.getAttribute("class");
    var className = ele.className;
    if (className == null) {
        return true;
    }
    else {
        return className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
    }

};

Core4j.Domhelper.setClass = function(ele, cls){
    ele.className=cls;
};

Core4j.Domhelper.addClass = function(ele, cls){
    //var className=ele.getAttribute("class");
    var className = ele.className;
    if (!this.hasClass(ele, cls)) {
        if (className == null) {
            className = "";
        }
        //ele.setAttribute("class",className+" "+cls);
        ele.className = className + " " + cls;
    }

};
Core4j.Domhelper.removeClass = function(ele, cls){
    if (Core4j.Domhelper.hasClass(ele, cls)) {
        var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
        //var className=ele.getAttribute("class");
        var className = ele.className;
        if (className != null) {
            className = className.replace(reg, ' ');
            //ele.setAttribute("class",className);
            ele.className = className;
        }
    }
};
Core4j.Domhelper.toggleClass = function(ele, cls){
    if (hasClass(ele, cls)) {
        Core4j.Domhelper.removeClass(ele, cls);
    }
    else {
        Core4j.Domhelper.addClass(ele, cls);
    }

};
Core4j.Domhelper.changeClass = function(ele, oldcls, newcls){
    Core4j.Domhelper.removeClass(ele, oldcls);
    Core4j.Domhelper.addClass(ele, newcls);
};

Core4j.isNull = function(obj){
    if (obj == null || obj == undefined) {
        return true;
    }
    return false;
};

//add index of to array if the browser is unsouport
if(!Array.indexOf){
	Array.prototype.indexOf = function(obj){
		var length=this.length;
		for(var i=0; i<length; i++){
			if(this[i]===obj){
				return i;
			}
		}
		return -1;
	}
};

Array.prototype.clearAllItems = function(){
    this.length = 0;
};
Array.prototype.insertItemAtIndex = function(index, obj){
    this.splice(index, 0, obj);
};
Array.prototype.removeItemByIdex = function(index){
    this.splice(index, 1);
};
Array.prototype.removeItem = function(obj){
    var index = this.indexOf(obj);
    if (index >= 0) {
        this.removeItemByIdex(index);
    }
};
Array.prototype.IS_CORE4J_OBJECT_ARRAY=true;




/*Core4j.KEYCODES={
	CTRL:17,
	ENTER:13,
	SHIFT:16,
	BACKSPACE:8,
	ALT:18,
	CAPSLOCK:20,
	TAB:9
}*/

Core4j.Domhelper.getEvent=function(){
 if (window.event!=null) {
 	return window.event;
 }
 func=Core4j.Domhelper.getEvent.caller;
        while(func!=null){
            var arg0=func.arguments[0];
            if(arg0){if((arg0.constructor==Event || arg0.constructor ==MouseEvent) || (typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation)){return arg0;}            }
            func=func.caller;
        }
       return null;
};
Core4j.Domhelper.isCtrlKey=function(){
	var event=Core4j.Domhelper.getEvent();
	if(event!=null){
		return event.ctrlKey;
	}
	return false;
};

Core4j.cloneValueObject = function(valueObject){
	var objClone;
	if (valueObject.constructor == Object) {
		objClone = new valueObject.constructor();
	}
	else {
		objClone = new valueObject.constructor(valueObject.valueOf());
	}
	for (var key in valueObject) {
		if (objClone[key] != valueObject[key]) {
			if (typeof(valueObject[key]) == 'object') {
				objClone[key] = Core4j.cloneValueObject(valueObject[key]);
			}
			else {
				objClone[key] = valueObject[key];
			}
		}
	}
	objClone.toString = valueObject.toString;
	objClone.valueOf = valueObject.valueOf;
	return objClone;
};

Core4j.checkIsArray=function(obj){
        if(typeof obj == 'object' && typeof obj.sort == 'function' && typeof obj.length == 'number' &&(obj.IS_CORE4J_OBJECT_ARRAY==true)){
            return true;
        }else{
            return false;
        }
};


Core4j.util.jsonToString=function(jsonObj){
	if(typeof jsonObj=='object'){
		/*if(true){

		}*/
	}
};

Core4j.util.htmlEncode = function(text){
  return text.replace(/&/g, '&amp;').replace(/"/g, '&quot;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
};

String.prototype.replaceAllStr  = function(s1,s2){
	return this.replace(new RegExp(s1,"gm"),s2);
};