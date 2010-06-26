<%@page pageEncoding="UTF-8" language="java"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<html>
	<head>
		<title>产品动态属性</title>
		<script type='text/javascript' src='${root}/dwr/interface/CommonService.js'></script>
        <script type='text/javascript' src='${root}/dwr/engine.js'></script>
        <script type='text/javascript' src='${root}/dwr/util.js'></script>
	</head>
	<body>
		<table id="table">
			<tr>
				<td>
					<select id="modelType" name="modelType">
					   <option value="Text">文本框</option>
					   <option value="Select">选择框</option>
					   <option value="Radio">单选框</option>
					   <option value="CheckBox">多选框</option>
					</select>
				</td>
				<td colspan="2">
					<input type='button' value='确定' onclick="addRow()">
				</td>
			</tr>
			<tr>
			 <td colspan="3">
			     <input type="radio" name="g1" value="r1"/>
			     <input type="radio" name="g1" value="r2"/>
			     <input type="radio" name="g2" value="r11"/>
			     <input type="radio" name="g2" value="r21"/>
			 </td>
			</tr>
		</table>
		<div class="box" id="target"></div>
		<input  type="button" value="提交" onclick="javascript:save()"/>
	</body>
	<script language="javascript">


	function load() {
		var table = document.getElementById("target");
		CommonService.loadDynamic("hwId", function(retData){
            for(var i = 0; i < retData.length; i++){
            	addModel(table,retData[i]);
                }
         }) ;
	}

	function save() {
		var temp = [];
        var target = document.getElementById("target");
        for(var i = 0 ; i<target.childNodes.length; i++){
            var jModel = {id:"",type:"",items:[]};
            var model = target.childNodes[i];
            var type = model.getAttribute("type");
            var jItems = [];
            if(type == "Text"){
                var jItem = {nullEnabled:false,key:"",value:""};
                for(var item = model.childNodes[0].firstChild; item; item = item.nextSibling){
                          if(item.name == "textInput"){
                            jItem.key= item.value;
                            }
                            if(item.name == "checkbox"){
                            jItem.nullEnabled= item.checked;
                            }
                }
                jItems.push(jItem);
            }else{
                for(var items = model.childNodes[0].nextSibling; items; items = items.nextSibling){
                   var jItem = {nullEnabled:false,key:"",value:""};
                   for(var item = items.firstChild; item; item = item.nextSibling){
                	    //alert(item.nodeType +" ,nodeValue = " +item.nodeValue +" ,value = " +item.value+" , name = " +item.name);
                	    if(item.name == "textInput"){
                	    	jItem.key= item.value;
                    	    }
                   }
                   jItems.push(jItem);
                  }
                }
                jModel.items = jItems;
            jModel.id = model.childNodes[0].firstChild.value;
            jModel.type = type;
            
            temp.push(jModel);
        }
       //alert(temp);
	    CommonService.saveDynamic(temp, function(retData){
	           if(retData){
	        	   // alert("success");
		           }else{
		        	    alert("failed");
			          }
	         }) ;
	}

	function addModel(table,model){
		add(table,model.type);
	}

	function add(table,type){
        switch(type){
        case 'Select':addSelect(table);
        break;
        case 'Text':addText(table);
        break;
        case 'Radio':addRadio(table);
        break;
        case 'CheckBox':addCheckBox(table);
        break;
        default:alert('Other');
      }
	}

	function addText(table){
        var text = "<input id='textInput' name ='textInput' type='text'/>";
        var nullEnabled = "<input type='checkbox' name ='checkbox' title='允许为空'/>";
        var del = "<input id='textButton' name ='textButton' type='button' value='删除文本框' onclick='del(this.parentNode)'/>";
        var e = element("div", {"class": "textType","className": "textType"}, [text,nullEnabled,del]);
        table.appendChild(element("div",{"class": "modelType","className": "modelType","type": "Text"},[e]));
	}

	function addSelect(table){
        var text = "<input id='textInput' name ='textInput' type='text'/>";
        var add = "<a href='#' onclick='addSelectOption(this.parentNode.parentNode)'><img src='${root}/img/grid_add.png'/></a>";
        var del = "<input id='textButton' name ='textButton' type='button' value='删除选择框' onclick='del(this.parentNode)'/>";
        var e = element("div", {"class": "selectType","className": "selectType"}, [text,add,del]);
        table.appendChild(element("div",{"class": "modelType","className": "modelType","type": "Select"},[e]));
	    }

    function addSelectOption(obj){
	        var text = "<input id='textInput' name ='textInput' type='text'/>";
	        var del = "<input id='textButton' name ='textButton' type='image' src='${root}/img/grid_delete.png' title='删除' onclick='del(this)'/>";
	        var e = element("div", {"class": "selectOption","className": "selectOption"}, [text,del]);
	        obj.appendChild(e);
        }

	function addCheckBox(table){
        var text = "<input id='textInput' name ='textInput' type='text'/>";
        var add = "<a href='#' onclick='addCheckBoxOption(this.parentNode.parentNode)'><img src='${root}/img/grid_add.png'/></a>";
        var del = "<input id='textButton' name ='textButton' type='button' value='删除多选框' onclick='del(this.parentNode)'/>";
        var e = element("div", {"class": "CheckBoxType","className": "CheckBoxType"}, [text,add,del]);
        table.appendChild(element("div",{"class": "modelType","classlName": "modeType","type": "CheckBox"},[e]));
	    }

    function addCheckBoxOption(obj){
        var text = "<input id='textInput' name ='textInput' type='text'/>";
        var del = "<input id='textButton' name ='textButton' type='image' src='${root}/img/grid_delete.png' title='删除' onclick='del(this)'/>";
        var e = element("div", {"class": "checkBoxOption","className": "checkBoxOption"}, [text,del]);
        obj.appendChild(e);
    }

	function addRadio(table){
        var text = "<input id='textInput' name ='textInput' type='text'/>";
        var add = "<a href='#' onclick='addSelectOption(this.parentNode.parentNode)'><img src='${root}/img/grid_add.png'/></a>";
        var del = "<input id='textButton' name ='textButton' type='button' value='删除单选框' onclick='del(this.parentNode)'/>";
        var e = element("div", {"class": "radioType","className": "radioType"}, [text,add,del]);
        table.appendChild(element("div",{"class": "modelType","className": "modelType","type": "Radio"},[e]));
	    }

    function del(obj){
        var child = obj.parentNode;
        child.parentNode.removeChild(child);
      }

    function addRow(){
        var table = document.getElementById("target");
        var modelType = document.getElementById("modelType");
        add(table,modelType.options[modelType.selectedIndex].value);
    }

	function element(tagName, attributes, children) {
		var e = document.createElement(tagName);
		for ( var a in attributes) {
			e.setAttribute(a, attributes[a]);
		}
		if (children)
			for ( var i = 0; i < children.length; i++)
				if (typeof (children[i]) == "string") {
					e.innerHTML += children[i];
				} else {
					e.appendChild(children[i]);
				}
		return e;
	}

    function isElement(obj){
        if(window.DOMParser){//Firefox
            if(obj instanceof Element){
                return true;
            }else{
                return false;
            }
        }else{
            return true;//IE
        }
     }
    load();
</script>
</html>
