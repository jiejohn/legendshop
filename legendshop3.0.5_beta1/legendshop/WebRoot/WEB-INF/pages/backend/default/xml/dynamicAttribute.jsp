<%@page pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<html>
	<head>
		<title>商品动态属性</title>
        <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
       <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
       <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
    <%
    Integer type = (Integer)request.getAttribute("DYNAMIC_TYPE");
    String userName = (String)session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
    Object[] params = new Object[]{type.shortValue(),userName};
    request.setAttribute("params",params);
    %>
    
        <style type="text/css">
        	.modelType{border: 1px solid ; margin: 3px;padding: 1px;background-color: #FFFFF6;}
        	.modelType:hover{background:#CCCCBB;} 
        	.textOptionType { padding: 3px ;margin-left:68px ; }
        	.selectType { padding: 3px ;margin-left:68px ; }
        	.selectOption { padding: 3px ;margin-left:68px ; }
        	.CheckBoxType { padding: 3px ;margin-left:68px ; }
        	.radioType { padding: 3px ;margin-left:68px ; }
        	.selectOption {padding: 3px ;margin-left:100px ;}
        </style>
	</head>
	<body>

 <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理 &raquo; <a href="<ls:url address='/admin/product/query'/>">商品管理</a> &raquo; <a href="${pageContext.request.contextPath}/admin/product/load">创建商品</a>
    	 <c:if test="${prod.name != null}"> &raquo; <a href= "${pageContext.request.contextPath}/views/${prod.prodId}" target="_blank">${prod.name}</a></c:if>
    	</td></tr>
   	</thead>
    <tr class="odd">
      <td><div align="left">
        <jsp:include page="/admin/product/createsetp">
    		<jsp:param name="step" value="3"/>
    	</jsp:include>
      </div></td>
    </tr>
   
    </table>
    <table style="width: 100%; border: 0px">
			<tr>
				<td width="300px">选择模板
				<!-- 
					<select id="modelType" name="modelType">
					   <option value="Text">参数</option>
					   <option value="Select">下拉列表</option>
					   <option value="Radio">单选框</option>
					   <option value="CheckBox">多选框</option>
					</select>
					 -->
					<select id="tempId" name="tempId">
					<option:optionGroup type="select" required="false" cache="fasle"
	                defaultDisp="-- 属性模板 --" 
	                hql="select t.id, t.name from DynamicTemp t where t.type =? and t.userName = ?" params="${params}"/>
					</select>
				<input type='button' value='加载' onclick="applyTemp()">
				<input type='button' value='修改' onclick="modifyTemp()">
				<br><br>
				模板名称 <input type="text" value="" id="tempName" name="tempName" size="14" maxlength="50"> 
				<a href="javascript:addTemp('${DYNAMIC_TYPE}')" title="根据现有配置，创建商品属性模板"><img alt="创建商品属性模板" src="${pageContext.request.contextPath}/common/default/images/grid_add.png"></a>
				<a href="javascript:deleteTemp('${DYNAMIC_TYPE}')" title="删除商品属性模板"><img alt="删除商品属性模板" src="<ls:templateResource item='/common/default/images/grid_delete.png'/>"></a>
				<br><br><br><br>
				<c:choose>
					<c:when test="${DYNAMIC_TYPE == 1}">创建动态属性： <br>
					<!-- 
					<input type="button" value="下拉列表" onclick="addRowRecord('Select')">	
					 -->
					<input type="button" value="单选框" onclick="addRowRecord('Radio')">	
					<input type="button" value="多选框" onclick="addRowRecord('CheckBox')">	
					</c:when>
					<c:otherwise>创建动态参数： <br><input type="button" value="参数" onclick="addRowRecord('Text')">	</c:otherwise>
				</c:choose>
				<br><br>
				<input  type="button" value="保存设置" onclick="javascript:save()"/>
				</td>
				<td width="650px"><div class="box" id="target" style="border: 1px"></div></td>
			</tr>
		</table>
   <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1.<img alt="创建商品属性模板" src="${pageContext.request.contextPath}/common/default/images/grid_add.png">&nbsp;以“模板名称”为名称，以当前的属性配置作为内容，创建商品属性模板，在属性内容编辑器中则用于创建一个动态属性<br>
   2.<img alt="删除商品属性模板" src="<ls:templateResource item='/common/default/images/grid_delete.png'/>">&nbsp;删除选择模板中选定的商品属性模板,在属性内容编辑器中则用于删除属性或者属性下的选项<br>
   </td><tr></table> 
		
		

	<script language="javascript"><!--


	function load() {
		var table = document.getElementById("target");
		var dataArray = eval('${imgFileJSON}');
		if(dataArray!=null && dataArray.length >0){
		 for(var i = 0; i < dataArray.length; i++){
            	addModel(table,dataArray[i]);
                }
            }
	}

	function save() {
		var temp = getTemplate();
         //alert(temp);
         var type = '${DYNAMIC_TYPE}';
	    CommonService.saveDynamic('${prod.prodId}','${sessionScope.SPRING_SECURITY_LAST_USERNAME}', type, temp, function(retData){
	           if(retData){
	           		if(type != null && type ==1){
	           		 alert("保存商品动态属性成功");
	           		}else{
	           		 alert("保存商品动态参数成功");
	           		}
		           }else{
		        	    alert("保存失败");
			          }
	         }) ;
	}
	
	function getTemplate() {
		var temp = [];
        var target = document.getElementById("target");
        for(var i = 0 ; i<target.childNodes.length; i++){
            var jModel = {id:"",type:"",items:[]};
            var model = target.childNodes[i];
            var type = model.getAttribute("type");
            var jItems = [];
            if(type == "Text"){
            	var jItem = {key:"",value:""};
                for(var item = model.childNodes[0].firstChild; item; item = item.nextSibling){
                          if(item.name == "textInput"){
                            jModel.id = item.value;
                            jItem.key = item.value;
                            }
                         if(item.name == "textValue"){
                	    	jItem.value= item.value;
                    	    }
                }
         
                   jItems.push(jItem);
            }else{
                for(var items = model.childNodes[0].nextSibling; items; items = items.nextSibling){
                   var jItem = {key:"",value:""};
                   for(var item = items.firstChild; item; item = item.nextSibling){
                	    //alert(item.nodeType +" ,nodeValue = " +item.nodeValue +" ,value = " +item.value+" , name = " +item.name);
                	    if(item.name == "textInput"){
                	    	jItem.key= item.value;
                    	    }
                   }
                   jItems.push(jItem);
                  }
                 
                 for(var item2 = model.childNodes[0].firstChild; item2; item2 = item2.nextSibling){
                          if(item2.name == "textInput"){
                            jModel.id = item2.value;
                            }
                	}
                }
            jModel.items = jItems;
            jModel.type = type;
            
            temp.push(jModel);
        }
        return temp;
	}

	function addModel(table,model){
		switch(model.type){
	        case 'Select':addSelect(table,model);
	        break;
	        case 'Text':addText(table,model);
	        break;
	        case 'Radio':addRadio(table,model);
	        break;
	        case 'CheckBox':addCheckBox(table,model);
	        break;
	        default:alert('Other');
      }
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

	function addText(table,model){
	   var text;
	   var value;
		if(model){
			text = "参数：<input id='textInput' name ='textInput' type='text' value='" + model.id + "'/>";
			 if(model!=null && model.items !=null){
			 var itemValue = model.items.length > 0 ?  model.items[0].value : "";
			 value = "&nbsp;&nbsp;值：<input id='textValue' name ='textValue' type='text' value='" + itemValue + "'/>";
			 }
		}else{
			text = "参数：<input id='textInput' name ='textInput' type='text'/>";
			value = "&nbsp;&nbsp;值：<input id='textValue' name ='textValue' type='text' />";
		}
        
        //var del = "<input id='textButton' name ='textButton' type='button' value='删除参数框' onclick='del(this.parentNode)'/>";
        var del = "<a href='#' onclick='del(this.parentNode)'><img src='<ls:templateResource item='/common/default/images/grid_delete.png'/>' title='删除'/></a>";
        var up = "&nbsp;&nbsp;<a href='#' onclick='moveUp(this.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/up.gif' title='上移'/></a>";
        
        var down = "&nbsp;<a href='#' onclick='moveDown(this.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/down.gif' title='下移'/></a>";
        var e = element("div", {"class": "textOptionType","className": "textOptionType"}, [text,value,del, up, down]);
        table.appendChild(element("div",{"class": "modelType","className": "modelType","type": "Text"},[e]));
	}

	function addSelect(table, model){
	 var text;
	if(model){
		text = "下拉列表：<input id='textInput' name ='textInput' type='text' value='" + model.id + "'/>";
	}else{
		text = "下拉列表：<input id='textInput' name ='textInput' type='text'/>";
	}
        var add = "<a href='#' onclick='addSelectOption(this.parentNode.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/grid_add.png'/></a>";
       // var del = "<input id='textButton' name ='textButton' type='button' value='删除选择框' onclick='del(this.parentNode)'/>";
        var del = "<a href='#' onclick='del(this.parentNode)'><img src='<ls:templateResource item='/common/default/images/grid_delete.png'/>' title='删除'/></a>";
        
        var up = "&nbsp;&nbsp;<a href='#' onclick='moveUp(this.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/up.gif' title='上移'/></a>";
        
        var down = "&nbsp;<a href='#' onclick='moveDown(this.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/down.gif' title='下移'/></a>";
        
        var e = element("div", {"class": "selectType","className": "selectType"}, [text,add,del, up, down]);
        
        var e1 = element("div",{"class": "modelType","className": "modelType","type": "Select"},[e]);
        
        if(model!=null && model.items !=null){
		for(var i = 0; i< model.items.length; i++){
			e1.appendChild(getSelectOption(model.items[i]));
			}	
		}
        
        table.appendChild(e1);
	   }

    function addSelectOption(obj, item){
    		var text;
    		if(item){
    		  text = "选项&nbsp;<input id='textInput' name ='textInput' type='text' value='"+ item.value + "'/>";
    		}else{
    		  text = "选项&nbsp;<input id='textInput' name ='textInput' type='text'/>";
    		}
	        
	        var del = "<input id='textButton' name ='textButton' type='image' src='<ls:templateResource item='/common/default/images/grid_delete.png'/>' title='删除' onclick='del(this)'/>";
	        var e = element("div", {"class": "selectOption","className": "selectOption"}, [text,del]);
	        obj.appendChild(e);
        }
        
        function getSelectOption(item){
    		var text;
    		if(item){
    		  text = "选项&nbsp;<input id='textInput' name ='textInput' type='text' value='"+ item.key + "'/>";
    		}else{
    		  text = "选项&nbsp;<input id='textInput' name ='textInput' type='text'/>";
    		}
	        
	        var del = "<input id='textButton' name ='textButton' type='image' src='<ls:templateResource item='/common/default/images/grid_delete.png'/>' title='删除' onclick='del(this)'/>";
	        var e = element("div", {"class": "selectOption","className": "selectOption"}, [text,del]);
	        return e;
        }

	function addCheckBox(table,model){
		 var text;
		if(model){
		  text = "多选框：<input id='textInput' name ='textInput' type='text' value='" + model.id + "'/>";
		}else{
		  text = "多选框：<input id='textInput' name ='textInput' type='text'/>";
		}
         var add = "<a href='#' onclick='addSelectOption(this.parentNode.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/grid_add.png'/></a>";
         var del = "<a href='#' onclick='del(this.parentNode)'><img src='<ls:templateResource item='/common/default/images/grid_delete.png'/>' title='删除'/></a>";
         var up = "&nbsp;&nbsp;<a href='#' onclick='moveUp(this.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/up.gif' title='上移'/></a>";
         var down = "&nbsp;<a href='#' onclick='moveDown(this.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/down.gif' title='下移'/></a>"; 
         var e = element("div", {"class": "CheckBoxType","className": "CheckBoxType"}, [text,add,del,up,down]);
         var e1 = element("div",{"class": "modelType","className": "modelType","type": "CheckBox"},[e]);
         
	        if(model!=null && model.items !=null){
			for(var i = 0; i< model.items.length; i++){
				e1.appendChild(getSelectOption(model.items[i]));
				}	
			}         
         
        table.appendChild(e1);
	    }

	function addRadio(table,model){
		var text;
		if(model){
		  text = "单选框：<input id='textInput' name ='textInput' type='text' value='" + model.id + "'/>";
		}else{
		  text = "单选框：<input id='textInput' name ='textInput' type='text'/>";
		}
        var add = "<a href='#' onclick='addSelectOption(this.parentNode.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/grid_add.png'/></a>";
        //var del = "<input id='textButton' name ='textButton' type='button' value='删除单选框' onclick='del(this.parentNode)'/>";
         var del = "<a href='#' onclick='del(this.parentNode)'><img src='<ls:templateResource item='/common/default/images/grid_delete.png'/>' title='删除'/></a>";  
         var up = "&nbsp;&nbsp;<a href='#' onclick='moveUp(this.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/up.gif' title='上移'/></a>";
         var down = "&nbsp;<a href='#' onclick='moveDown(this.parentNode)'><img src='${pageContext.request.contextPath}/common/default/images/down.gif' title='下移'/></a>";
         
        var e = element("div", {"class": "radioType","className": "radioType"}, [text,add,del,up,down]);
        var e1 = element("div",{"class": "modelType","className": "modelType","type": "Radio"},[e]);
        
        if(model!=null && model.items !=null){
		for(var i = 0; i< model.items.length; i++){
			e1.appendChild(getSelectOption(model.items[i]));
			}	
		}
        
        table.appendChild(e1);
	 }

    function del(obj){
        var child = obj.parentNode;
        child.parentNode.removeChild(child);
      }
      
    function addRowRecord(attributeType){
        var table = document.getElementById("target");
        add(table,attributeType);
    }
      

    function addRow(){
        var table = document.getElementById("target");
        var modelType = document.getElementById("modelType");
        add(table,modelType.options[modelType.selectedIndex].value);
    }
    
    function addTemp(type){
    	var temp = getTemplate();
    	var tempName = document.getElementById("tempName").value;
	    CommonService.saveDynamicTemp(tempName,'${sessionScope.SPRING_SECURITY_LAST_USERNAME}', type ,temp, function(retData){
	           if(retData){
	        	     alert("保存成功");
	        	     window.location.reload();
		           }else{
		        	    alert("操作失败,检查名称是否重复");
			          }
	         }) ;
    }
    
    function modifyTemp(){
    
    	var tempId = document.getElementById("tempId").value;
        var temp = getTemplate();
    	
    	if(tempId == null || tempId == '' || temp == null || temp == ''){
    		alert("请先加载属性模板");
    		return;
    	}
    	CommonService.updateDynamicTemp(tempId,'${sessionScope.SPRING_SECURITY_LAST_USERNAME}', '${DYNAMIC_TYPE}',temp, function(retData){
	           if(retData){
	        	     alert("更新成功");
		           }
	         }) ;
    }
    
    function deleteTemp(type){
   		 var tempId = document.getElementById("tempId");
    	CommonService.deleteDynamicTemp(tempId.options[tempId.selectedIndex].value,'${sessionScope.SPRING_SECURITY_LAST_USERNAME}',  function(retData){
	           if(retData){
	        	     alert("操作成功");
	        	     window.location.reload();
		           }else{
		        	    alert("操作失败,检查名称是否重复");
			          }
	         }) ;
    }
    
   function applyTemp(){
   	    var table = document.getElementById("target");
   	    var tempId = document.getElementById("tempId").value;
   	    table.innerHTML = "";
		CommonService.loadDynamicAttributeFromTemp(tempId,'${sessionScope.SPRING_SECURITY_LAST_USERNAME}', function(retData){
            for(var i = 0; i < retData.length; i++){
            	addModel(table,retData[i]);
                }
         }) ;
   
   }
   
  function moveUp(e) {
     var obj = e.parentNode;
     var toPos = obj.previousSibling;
     while (toPos && toPos.nodeType != 1){
       toPos = toPos.previousSibling;
      }
     // alert(toPos.tagName +" = "+ obj.tagName+ "  "+ toPos.id +" = " + obj.id);
     if (toPos && toPos.tagName == obj.tagName && toPos.id == obj.id) {
      obj.parentNode.removeChild(obj);
      toPos.parentNode.insertBefore(obj, toPos);
     }else{
       return;
     }
    }
    
     function redrawOrder(){
        order = 1;
        var rules = document.getElementsByName("rules");
        if(rules!=null){
            for(var i=0;i<rules.length;i++){
               rules[i].firstChild.nodeValue = order +". Routing Rule Type ";
               if(order % 2 != 0){
               rules[i].setAttribute("class","rules");
               rules[i].setAttribute("className","rules");
           }else{
               rules[i].setAttribute("class","ruleseven");
               rules[i].setAttribute("className","ruleseven");
           }
           order ++;
            }
        }
      }
    
    function moveDown(e) {
      var obj = e.parentNode; 
      var toPos = obj.nextSibling;
      while (toPos && toPos.nodeType != 1){
           toPos = toPos.nextSibling;
      }
      //alert(addroutingRule.innerHTML);
      //alert(toPos.innerHTML);
      if (toPos && toPos.tagName == obj.tagName && toPos.id == obj.id) {
           toPos.parentNode.removeChild(toPos);
           obj.parentNode.insertBefore(toPos, obj);
     }else{
           return;
     }
     //redrawOrder();
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
--></script>
	</body>
</html>
