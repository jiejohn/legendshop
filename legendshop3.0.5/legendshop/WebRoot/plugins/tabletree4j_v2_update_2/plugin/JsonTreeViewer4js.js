
JsonTreeViewer4js = function(){


		var typeofMap=new Core4j.util.HashMap();
		typeofMap.put('number','<span style=\'color:#0000ff;valueObj\'>number</span>');
		typeofMap.put('string','<span style=\'color:#0000ff;valueObj\'>string</span>');
		typeofMap.put('boolean','<span style=\'color:#0000ff;valueObj\'>boolean</span>');
		typeofMap.put('function','<span style=\'color:#0000ff;valueObj\'>function</span>');
		typeofMap.put('object','<span style=\'color:#0000ff;valueObj\'>object</span>');
		typeofMap.put('undefined','<span style=\'color:#0000ff;valueObj\'>undefined</span>');

	/*
	 * @param {Object} tabletreeObj
	 */
	this.initTableTree4jPlugin = function(tabletreeObj){



		tabletreeObj.buildJsonTreeViewer = function(configObject){
			tabletreeObj.treeMode = "menu";
			tabletreeObj.selectMode = "single";
			tabletreeObj.themeName="arrow";
			tabletreeObj.addEvent(tabletreeObj.eventsType.ExpandNodeEvents,jsonViewerExpandNodeEvent);
			tabletreeObj.columns = [{
				isNodeClick: true,
				width: '100%',
				dataIndex: 'nodeName'
			}];
			var nodes = [];
			tabletreeObj.nodeslength=0;
			var jsonparam = configObject.jsonparam;//can be string or object
			tabletreeObj.onJsonparamErrorEvent = configObject.onJsonparamErrorEvent;
			try {
				if (typeof jsonparam == 'string') {
					jsonparam = eval('('+jsonparam+')');
				}
				if (typeof jsonparam != 'object') {
					if (tabletreeObj.onJsonparamErrorEvent != null) {
						tabletreeObj.onJsonparamErrorEvent("The value of configObject.jsonparam must be a json string or json Object.", tabletreeObj);
					}
				}
				else {
					nodes[0] = new Core4j.toolbox.TableTreeNode({
						id: 'root',
						pid: null,
						dataObject: {
							nodeName: 'JsonTreeViewer'
						},
						isOpen: true
					});
					var nodeName="{}";
					var isArray=false;
					if (Core4j.checkIsArray(jsonparam)) {
						//nodeName="["+jsonparam.length+"]";
						nodeName="[]";
						isArray=true;
					}
					nodeName='<span style=\'color:#0000ff;valueObj\'>'+nodeName+'</span>';

					nodes[nodes.length] = new Core4j.toolbox.TableTreeNode({
						isLeaf:false,
						id: nodes.length,
						pid: nodes[0].id,
						dataObject: {
							nodeName: nodeName
						},
						// userObject objTypeof keyname isarray jsonvalue
						userObject:{objTypeof:typeof(jsonparam),jsonvalue:jsonparam,keyname:"",isarray:isArray}
					});
					tabletreeObj.nodeslength=nodes.length;
					tabletreeObj.rebuildTreeByNodes(nodes, false);
				}

			}
			catch (errMsg) {
				if (tabletreeObj.onJsonparamErrorEvent != null) {
					tabletreeObj.onJsonparamErrorEvent(errMsg, tabletreeObj);
				}
			}
		}
	}




	var jsonViewerExpandNodeEvent=function(node,tabletreeObj){
		if (node.isLoad == false) {
			tabletreeObj.startLoadingNode(node);
			var userObject=node.userObject;
			var jsonvalue=userObject.jsonvalue;
			if(userObject.objTypeof=='object'){

				var childNodes=[];

				if(userObject.isarray){
					for (var i = 0, n = jsonvalue.length; i < n; i++) {
						var tmp = jsonvalue[i];
						tabletreeObj.nodeslength++;
						childNodes[childNodes.length] = parseValueAsNode(tmp,key,tabletreeObj.nodeslength);
					}
				}else{
					for (var key in jsonvalue) {
						var tmp = jsonvalue[key];
						tabletreeObj.nodeslength++;
						childNodes[childNodes.length] = parseValueAsNode(tmp,key,tabletreeObj.nodeslength);
					}
				}
				tabletreeObj.loadingAddNodes(childNodes,node.id);
			}
			tabletreeObj.endLoadingNode(node);
		}

		/*if(node!=null){
			for (var i = 0,n=node.childs.length;i<n;i++){
				var tmpnode=node.childs[i];
				tabletreeObj.toggleNode(tmpnode,true);
			};
		}*/

	}

	var parseValueAsNode = function(valueObj,key,nid){
		var nodeName = '{}';
		var isArray = false;
		var isobj = true;
		var tmpkey="";

		if(key==null){
			key="";
		}else{
			tmpkey=" "+key;
		}

		if(valueObj==null){
			nodeName ="{} " + key + ":";
		}else{
			if (typeof valueObj == 'object') {
				if (Core4j.checkIsArray(valueObj)) {
					nodeName = "[]";
					isArray = true;
				}
				nodeName='<span style=\'color:#0000ff;valueObj\'>'+nodeName+'</span>'+tmpkey;
			}else {
				isobj = false;
				nodeName = typeofMap.get(typeof valueObj) + " " + key + ":" + Core4j.util.htmlEncode(valueObj+"");
				//nodeName = typeofMap.get(typeof valueObj) + " " + key + ":" + valueObj;
			}
		}
		var node = new Core4j.toolbox.TableTreeNode({
			isLeaf: !isobj,
			id:nid,
			dataObject: {
				nodeName: nodeName
			},
			// userObject objTypeof keyname isarray jsonvalueObj
			userObject: {
				objTypeof: typeof(valueObj),
				jsonvalue: valueObj,
				keyname: key,
				isarray: isArray
			}
		});
		return node;
	}
}

