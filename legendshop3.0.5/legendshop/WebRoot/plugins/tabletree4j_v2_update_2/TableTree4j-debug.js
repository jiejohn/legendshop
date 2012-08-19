/**
 * TABLETREE4J VESION 2 Javascript
 * @author LK
 * @email lannerk@qq.com
 * @date 2010-5-5
 * @releaseDate 2010-6-20 12:59
 * @version 2.0
 * @license apache licence V2.0
 */
Core4j.packageRegister("Core4j.toolbox");

Core4j.toolbox.TableTree4j = function(configObject){

	if(configObject==null){
		configObject={};
	}
	//private vars
	var nodeMap=new Core4j.util.HashMap();
	var tabletree=this;
	var nodeClickColumnIndex=0;


	//public vars
	this.version="2.0.1.31";

	this.id=configObject.id||'tabletree-'+(document.getElementsByTagName('table').length+1)+'-'+new Date().getTime();
	this.tableObject=null;
	this.rootNodes=[];

	this.sortColumn=null;
	this.sortColumnIndex=null;
	this.isSortColumnReversal=configObject.isSortColumnReversal;//||false;
	if(this.isSortColumnReversal==null){this.isSortColumnReversal=false;}
	this.scriptRootPath=configObject.scriptRootPath;
	if(this.scriptRootPath==null){
		this.scriptRootPath=Core4j.Domhelper.getResoureRoot("script","TableTree4j.js");
	}
	if(this.scriptRootPath==null){
		this.scriptRootPath=Core4j.Domhelper.getResoureRoot("script","TableTree4j-debug.js");
	}
	this.themeName=configObject.themeName||'default';
	this.cookiesSplitString=configObject.cookiesSplitString||'#T#';

	//public config var
	this.treeMode=configObject.treeMode||'menu';//'menu' ,'gird'
	//the tree show in the id of element
	this.renderTo=configObject.renderTo;
	//columns config,config the columns and use render function..
	//[{isNodeClick:,dataIndex:,width:,renderFunction:,canSort:,isDefalutSort:,[canSearch:]}]
	this.columns=configObject.columns||[];
	this.headers=configObject.headers||[];
	this.footers=configObject.footers||[];
	this.cookieTime=configObject.cookieTime||30*24*60*60*1000;





	var floatRight=configObject.floatRight;//||false;
	if(floatRight==null){floatRight=false;}
	var useCookie=configObject.useCookie;//||false;
	if(useCookie==null){useCookie=false;}
	var useIcon=configObject.useIcon;//||true;
	if(useIcon==null){useIcon=true;}
	var useLine=configObject.useLine;//||true;
	if(useLine==null){useLine=true;}
	this.useOrder=configObject.useOrder;//||true;
	if(this.useOrder==null){this.useOrder=true;}

	var useLineStr=useLine?"":"-no";
	var useIconStr=useIcon?"":"-no";
	var isFlowRightStr=floatRight?"-fr":"";
	//this.isReloadNode=configObject.isReloadNode||false;
	var selectMode=configObject.selectMode||'muti';'single','none';
	var selectNodes=[];


	//Evetns
	this.onExpandNodeEvents=configObject.onExpandNodeEvents||[];
	this.onCollapseNodeEvents=configObject.onCollapseNodeEvents||[];
	this.onBeforeAddNodeEvents=configObject.onBeforeAddNodeEvents||[];
	this.onAfterAddNodeEvents=configObject.onAfterAddNodeEvents||[];
	this.onBeforeRemoveNodeEvents=configObject.onBeforeRemoveNodeEvents||[];
	this.onAfterRemoveNodeEvents=configObject.onAfterRemoveNodeEvents||[];
	this.onBeforeRemoveNodeChildsEvents=configObject.onBeforeRemoveNodeChildsEvents||[];
	this.onAfterRemoveNodeChildsEvents=configObject.onAfterRemoveNodeChildsEvents||[];
	this.onBuildTreeAddNodeEvents=configObject.onBuildTreeAddNodeEvents||[];
	this.onBeforeBuildEvents=configObject.onBeforeBuildEvents||[];
	this.onAfterBuildEvents=configObject.onAfterBuildEvents||[];
	this.onBeforeLoadingAddNodesEvents=configObject.onBeforeLoadingAddNodesEvents||[];
	this.onAfterLoadingAddNodesEvents=configObject.onAfterLoadingAddNodesEvents||[];
	//onSelectNodeEvent(selects,allselectNodes,tabletreeObj);
	this.onSelectNodeEvents=configObject.onSelectNodeEvents||[];

	this.eventsType={
		ExpandNodeEvents:"ExpandNodeEvents",
		CollapseNodeEvents:"CollapseNodeEvents",
		BeforeAddNodeEvents:"BeforeAddNodeEvents",
		AfterAddNodeEvents:"AfterAddNodeEvents",
		BeforeRemoveNodeEvents:"BeforeRemoveNodeEvents",
		AfterRemoveNodeEvents:"AfterRemoveNodeEvents",
		BeforeRemoveNodeChildsEvents:"BeforeRemoveNodeChildsEvents",
		AfterRemoveNodeChildsEvents:"AfterRemoveNodeChildsEvents",
		BuildTreeAddNodeEvents:"BuildTreeAddNodeEvents",
		BeforeBuildEvents:"BeforeBuildEvents",
		AfterBuildEvents:"AfterBuildEvents",
		BeforeLoadingAddNodesEvents:"BeforeLoadingAddNodesEvents",
		AfterLoadingAddNodesEvents:"AfterLoadingAddNodesEvents",
		SelectNodeEvents:"SelectNodeEvents"
	};



	//Plugins
	this.plugins=configObject.plugins||[];

	//init canSort:,isDefalutSort:}
	this.headers.reverse();
	this.footers.reverse();

	for(var i=0,n=this.columns.length;i<n;i++){
		var tmp=this.columns[i];
		if ((tmp.canSort != false || tmp.canSort == 'false')&&(tmp.isDefalutSort == true || tmp.isDefalutSort == 'true')) {
			this.sortColumn=tmp;
			this.sortColumnIndex=i;
		}
		if(tmp.isNodeClick!=null&&(tmp.isNodeClick==true||tmp.isNodeClick=='true')){
			nodeClickColumnIndex=i;
		}
	}

		// init the plugins
		if(this.plugins!=null&&this.plugins.length>0){
			for (var i = 0, n = this.plugins.length; i < n; i++) {
				var pluginObject = this.plugins[i];
				pluginObject.initTableTree4jPlugin(this);
			}
		}


	/**remove event from the tree
	 * @param enentsType the type of events array
	 * all types:this.eventsType
	 */
	//API
	this.removeEvents=function(enentsType){
		if(eval("this.on"+enentsType)!=null){
			eval("this.on"+enentsType+"=[]");
		}
	};
	//API
	this.addEvent=function(enentType,eventFun){
		eval("this.on"+enentType+"[this.on"+enentType+".length]=eventFun");
	};
	//API
	this.removeEventByIndex=function(enentType,index){
		eval("this.on"+enentType+".removeItemByIdex(index)");
	};
	//make the Float Right css id
	/*this.makeThemeFloatRightCssId=function(){
		return this.id+"_floatRight_themecss";
	}*/
/*
 * 	var selectMode=configObject.selectMode||'muti';'single','none';
	var selectNodes=[];
 * */
	//API
	this.setSelectMode=function(mode){
		if(selectMode!=mode){
			this.clearSelects();
			selectMode=mode;
		}
	};
	//API
	this.getSelectMode=function(){
		return selectMode;
	};
	//API
	this.addselectNodes=function(nodes){
		if(selectMode=='muti'){
			for(var i=0,n=nodes.length;i<n;i++){
				var tmpNode=nodes[i];
				var selectnodeindex=selectNodes.indexOf(tmpNode);
				if (selectnodeindex == -1) {
					this.highLightNode(tmpNode);
					selectNodes[selectNodes.length] = tmpNode;
					//!!!fire onSelectNodeEvent
					if (this.onSelectNodeEvents != null) {
						for (var i = 0, n = this.onSelectNodeEvents.length; i < n; i++) {
							var onSelectNodeEvent = this.onSelectNodeEvents[i];
							if (onSelectNodeEvent != null && typeof(onSelectNodeEvent) == 'function') {
								onSelectNodeEvent(nodes,selectNodes,this);
							}
						}
					}
				}
			}
		}
	};
	//API
	this.setSelectNode=function(node){
		if(node!=null&&selectMode!='none'&&node.canSelect){
			var selectnodeindex=selectNodes.indexOf(node);
			if(selectnodeindex==-1||selectNodes.length>1){

				if(selectMode=='single'||!Core4j.Domhelper.isCtrlKey()){
					//alert("asdasd");
					this.clearSelects();
				}
					this.highLightNode(node);
					selectNodes[selectNodes.length]=node;
					//!!!fire onSelectNodeEvent
					if (this.onSelectNodeEvents != null) {
						for (var i = 0, n = this.onSelectNodeEvents.length; i < n; i++) {
							var onSelectNodeEvent = this.onSelectNodeEvents[i];
							if (onSelectNodeEvent != null && typeof(onSelectNodeEvent) == 'function') {
								onSelectNodeEvent([node],selectNodes,this);
							}
						}
					}
			}
		}
	};
	//API
	this.disSelectNode=function(node){

		if(node!=null){
			var selectnodeindex=selectNodes.indexOf(node);
			if(selectnodeindex!=-1){
				this.disHighLightNode(node);
				selectNodes.removeItemByIdex(selectnodeindex);
			}
		}
	};
	//API
	this.getSelectNodes=function(){
		return selectNodes;
	};
	//API
	this.clearSelects=function(){
		var n=selectNodes.length;
		for(var i=0,n=selectNodes.length;i<n;i++){
			var tmpNode=selectNodes[i];
			this.disHighLightNode(tmpNode);
		}
		selectNodes.clearAllItems();
	};

	this.highLightNode=function(node){
		if(node==null){return null;}
		var elobj=node.rowObj;
		/*if(this.treeMode=='menu'){
			//alert(this.makeValueDivId(node.id,null,true));
			elobj=document.getElementById(this.makeValueDivId(node.id,null,true));
			//Core4j.Domhelper.addClass(node.iconDivObj,this.makeHighLightClassName("highlight-bg-color"));
			//alert(elobj);
		}else{
			elobj=node.rowObj;
		}*/
		if(elobj!=null){
				Core4j.Domhelper.addClass(elobj,this.makeHighLightClassName("highlight-bg-color"));
		}
	};
	this.disHighLightNode=function(node){
		if(node==null){return null;}
		var elobj=node.rowObj;
		/*if(this.treeMode=='menu'){
			elobj=document.getElementById(this.makeValueDivId(node.id,null,true));
			Core4j.Domhelper.removeClass(node.iconDivObj,this.makeHighLightClassName("highlight-bg-color"));
		}else{
			elobj=node.rowObj;
		}*/
		if(elobj!=null){
			Core4j.Domhelper.removeClass(elobj,this.makeHighLightClassName("highlight-bg-color"));
		}
	};

	this.makeHighLightClassName=function(clsName){
		return this.themeName+"-"+clsName+"-row";
		/*if(this.treeMode=='menu'){
			return this.themeName+"-"+clsName+"-div";
		}else{
			return this.themeName+"-"+clsName+"-row";
		}*/
	};

	this.makeLineClassName=function(clsName){
		return this.themeName+"-"+clsName+useLineStr+isFlowRightStr;
	};
	this.makeIconDivClassName=function(clsName){
		return this.themeName+"-"+clsName+useIconStr+isFlowRightStr;
	};
	this.makeIconClassName=function(clsName){
		return this.themeName+"-"+clsName+isFlowRightStr;
	};

	this.makebaseClassName=function(clsName){
		return this.themeName+"-"+clsName+isFlowRightStr;
	};

	this.makeContentClassName=function(clsName){
		return this.themeName+"-"+clsName+isFlowRightStr;
	};

	this.makeClearfixClassName=function(clsName){
		return this.themeName+"-"+clsName;
	};

	//make the theme css id
	this.makeThemeCssId=function(){
		return this.themeName+"_tabletree4j_theme";
	};

	//make the row id
	this.makeRowId=function(nodeid){
		return this.id+"_"+nodeid;
	};
	//make the value div id
	this.makeValueDivId=function(nodeid,index,isNodeClickValue){
		if(isNodeClickValue==true){
			return this.id+"_"+nodeid+"_nodeclick_vl";
		}else{
			return this.id+"_"+nodeid+"_"+index+"_vl";
		}

	};

	//make the margin div id
	this.makeMarginDivId=function(nodeid){
		return this.id+"_"+nodeid+"_mg";
	};

	//make the click div id
	this.makeClickDivId=function(nodeid){
		return this.id+"_"+nodeid+"_clk";
	};

	//make marginItemReplaceClickDiv id
	this.makeMarginItemReplaceClickDivId=function(nodeid){
		return this.id+"_"+nodeid+"_mg_clk";
	};

	//make the click div id
	this.makeIconDivId=function(nodeid){
		return this.id+"_"+nodeid+"_ico";
	};

	//make the cookie name
	this.makeCookieName=function(){
		return this.id+"_openid";
	};

	this.addThemeCss=function(){
		var themeCssEl=document.getElementById(this.makeThemeCssId());
		if(themeCssEl==null){
			themeCssEl=Core4j.Domhelper.createAndInsertElementBeforeIndexTagNode("link",{
			attributeNames:['id','rel','href','type'],
			valueObject:{id:this.makeThemeCssId(),rel:'StyleSheet',href:this.getThemeRoot()+"css/theme.css",type:'text/css'}
			},document.getElementsByTagName("head")[0],1);
		}
	};

	/*this.changeFloatRightCss=function(){
		var themeFloatRighCssEl=document.getElementById(this.makeThemeFloatRightCssId());
		if(themeFloatRighCssEl==null&&floatRight==true){
			themeFloatRighCssEl=Core4j.Domhelper.createElement("link",{
			attributeNames:['id','rel','href','type'],
			valueObject:{id:this.makeThemeFloatRightCssId(),rel:'StyleSheet',href:this.scriptRootPath+"theme/theme-floatright.css",type:'text/css'}
			},document.getElementsByTagName("head")[0]);
			//document.body.appendChild(themeFloatRighCssEl);
		}
		if(themeFloatRighCssEl!=null&&floatRight==false){
			themeFloatRighCssEl.parentNode.removeChild(themeFloatRighCssEl);
		}
	}*/

	/**
	 * change style of tabletree
	 * @param theme name
	 */
	//API
	this.changeTheme=function(themeName){
		var temecssObj=document.getElementById(this.makeThemeCssId());
		if(themeName!=null&&this.themeName!=themeName){
			this.themeName=themeName;
			this.addThemeCss();
			this.reFreshTableTreeView();
		}

	};

	//API
	this.setUseCookie=function(isUse){
		if(useCookie!=usUse){
			useCookie=usUse;
			Core4j.removeCookie(this.makeCookieName());
			if(useCookie){
				//TODO
			}
		}
	};
	//API
	this.isUseCookie=function(){
		return useCookie;
	};

	//api setting
	//API
	this.setUseIcon=function(isUse){
		if (isUse != null && useIcon != isUse) {
			useIcon = isUse;
			useIconStr=useIcon?"":"-no";
			this.flowToReConfigNodesDivs(this.rootNodes);
			//this.reFreshTableTreeView();
		}
	};
	this.isUseIcon=function(){
		return useIcon;
	};
	//api setting
	//API
	this.setUseLine=function(isUse){
		if(isUse!=null&&useLine!=isUse){
			useLine=isUse;
			useLineStr=useLine?"":"-no";
			this.flowToReConfigNodesDivs(this.rootNodes);
			//this.reFreshTableTreeView();
		}
	};
	//API
	this.isUseLine = function(){
		return useLine;
	};
	//api setting
	//API
	this.setFloatRight=function(isFloatRight){
		if(isFloatRight!=null&&floatRight!=isFloatRight){
			floatRight=isFloatRight;
			isFlowRightStr=floatRight?"-fr":"";
			this.reFreshTableTreeView();
		}
	};
	//API
	this.isFloatRight=function(){
		return floatRight;
	};
	//API
	this.getThemeRoot=function(){
		return this.scriptRootPath+"theme/"+this.themeName+"/";
	};

	/*this.configNodeByDefaultValues=function(node){
		node.rowObj=null;
		node.marginDivObj=null;
		node.iconDivObj=null;
		node.clickDivObj=null;
		node.pNode=null;
		node.childs=[];
		node.level=0;
		node.visible="none";
		node.isRoot=false;
		node.isLastNode=false;
	}*/

	//API
	this.sortByColumnIndex=function(index){
		var canSort = this.columns[index].canSort;
		if(index<this.columns.length&&this.columns[index]!=null&&(canSort != false || canSort == 'false')){
			if(this.sortColumnIndex==index){
				this.isSortColumnReversal=!this.isSortColumnReversal;
			}else{
				this.sortColumnIndex=index;
				this.isSortColumnReversal=false;
			}

			this.sortColumn=this.columns[index];
			this.reFreshTableTreeView();
		}
	};
	//API
	this.sortByNodeOrder=function(){
		if(this.useOrder!=true||this.sortColumn!=null){
			this.sortColumn=null;
			this.sortColumnIndex=null;
			this.useOrder=true;
			this.reFreshTableTreeView();
		}
	};

	/*this.unSortNodes=function(){
		if(this.useOrder==true||this.sortColumn!=null){
			this.sortColumn=null;
			this.sortColumnIndex=null;
			this.useOrder=false;
			this.reFreshTableTreeView();
		}
	}*/

	this.makeRender=function(){
		var parentNode=null;
		// append tabletree to renderEl
		if(this.renderTo==null){
			parentNode=document.body;
		}else{
			var renderToObject=document.getElementById(this.renderTo);
			if(renderToObject==null){
				renderToObject=Core4j.Domhelper.createElement('div',{
				attributeNames:['id'],
				valueObject:{id:this.renderTo}
				},document.body);
			}
			parentNode=renderToObject;
		}
		return parentNode;
	};

	//API
	this.reFreshTableTreeView=function(){
		var parentNode=this.makeRender();

		Core4j.Domhelper.removeElement(this.tableObject);
		//var tmptreemode=this.treeMode;
		this.tableObject=Core4j.Domhelper.createElement('table',{
			attributeNames:['id','classStyle'],
			valueObject:{id:this.id,classStyle:'tabletree4j-'+this.treeMode}
		},parentNode);

		this.buildHeaders();

		this.initTreebuildRowsByNodes(this.rootNodes,true);

		this.buildFooters();

		this.clearSelects();
	};
	//API
	this.rebuildTreeByNodes=function(nodes,isJsondata){
		this.rootNodes.clearAllItems();
		selectNodes.clearAllItems();
		Core4j.removeCookie(this.makeCookieName());
		nodeMap.clear();
		Core4j.Domhelper.removeElement(this.tableObject);
		this.build(nodes,isJsondata);
	};




	/**build a tabletree by nodes
	 * @param nodes array,if null,will be build a empty tabletree
	 */
	//API
	this.build=function(nodes,isJsondata){



		//create table,add css

		//!!!fire onBeforeBuildEvent
		if(this.onBeforeBuildEvents!=null){
			for(var i=0,n=this.onBeforeBuildEvents.length;i<n;i++){
				var onBeforeBuildEvent=this.onBeforeBuildEvents[i];
				if(onBeforeBuildEvent!=null&&typeof(onBeforeBuildEvent)=='function'){
					onBeforeBuildEvent(this);
				}
			}
		}


		//add theme css
		this.addThemeCss();

		//TODO
		//this.changeFloatRightCss();

		var parentNode=this.makeRender();


		this.tableObject=Core4j.Domhelper.createElement('table',{
			attributeNames:['id','classStyle'],
			valueObject:{id:this.id,classStyle:'tabletree4j-'+this.treeMode}
		},parentNode);

		this.buildHeaders();

		//init nodes
		if(nodes!=null&&nodes.length>0){
			var tmpNode=null;

			for(var i=0,n=nodes.length;i<n;i++){
				tmpNode=nodes[i];
				if (tmpNode.id != null) {
					if(isJsondata!=null&&isJsondata==true){
						this.configDefaultValueToTableTreeNodeBoject(tmpNode);
					}
					/*if(isRebuild){
						this.configNodeByDefaultValues(tmpNode);
					}*/
					nodeMap.put(tmpNode.id, tmpNode);
				}
				/*if(tmpNode.pid==null){
					this.rootNodes[this.rootNodes.length]=tmpNode;
				}*/
			}
			for(var i=0,n=nodes.length;i<n;i++){
				tmpNode=nodes[i];
				if (tmpNode.id != null) {
					this.initNodeAsTree(tmpNode);
				}
			}
			this.initTreebuildRowsByNodes(this.rootNodes);
		}
		this.buildFooters();
		this.toggleNodesExpandByCookie();
		//!!!fire onAfterBuildEvent
		if(this.onBeforeBuildEvents!=null){
			for(var i=0,n=this.onBeforeBuildEvents.length;i<n;i++){
				var onBeforeBuildEvent=this.onBeforeBuildEvents[i];
				if(onBeforeBuildEvent!=null&&typeof(onBeforeBuildEvent)=='function'){
					onBeforeBuildEvent(this);
				}
			}
		}

	};

	this.sortNodes=function(nodes){
			if(this.useOrder==true&&this.sortColumn==null){
				nodes.sort(this.sortNodesOrderFunction);
			}else{
				if(this.sortColumn!=null){
				var sc=	this.sortColumn;

				var sortColumFun=function(node1,node2){
					var var1=null;
					var var2=null;

					if(node1!=null&&node1.dataObject!=null){
						var1=eval("node1.dataObject."+sc.dataIndex);
					}
					if(node2!=null&&node2.dataObject!=null){
						var2=eval("node2.dataObject."+sc.dataIndex);
					}

					if(typeof(var1)=='string'){
						return var1.localeCompare(var2);
					}else{
						return var2>var2?1:(var2<var2?-1:0);
					}
				};
				nodes.sort(sortColumFun);

				if(this.isSortColumnReversal){
					nodes.reverse();
				}

				}
			}
	};

	this.initTreebuildRowsByNodes=function(nodes,isReFreshTableTreeView){
		//alert(nodes.length);
		if(nodes!=null&&nodes.length>0){

			if(isReFreshTableTreeView){
				for (var i = 0, n = nodes.length; i < n; i++) {
					nodes[i].isLastNode=false;
				}
			}

			this.sortNodes(nodes);

			//set last node
			nodes[nodes.length-1].isLastNode=true;

			for (var i = 0, n = nodes.length; i < n; i++) {
				var tmpNode=nodes[i];
				this.initNodeInfo(tmpNode);
				this.addRowToTableObject(tmpNode);
				this.initTreebuildRowsByNodes(tmpNode.childs,isReFreshTableTreeView);
			}
		}
	};


	this.flowToReConfigNodesDivs=function(nodes){
		if(nodes!=null&&nodes.length>0){
			for (var i = 0, n = nodes.length; i < n; i++) {
				var tmpNode=nodes[i];
				this.configNodeMarginDiv(tmpNode);
				this.configNodeIconDiv(tmpNode);
				this.configNodeClickDiv(tmpNode);
				this.flowToReConfigNodesDivs(tmpNode.childs);
			}
		}
	};


	/**when add a node ,may be rebulid anthoer nodes margin*/
	this.configNodeMarginDiv=function(node){
		//var marginDiv=document.getElementById(this.makeMarginDivId(node.id));
		var marginDiv=node.marginDivObj;
		if(marginDiv==null){
			marginDiv=document.getElementById(this.makeMarginDivId(node.id));
		}
		if(marginDiv!=null){
			//clear all
			Core4j.Domhelper.removeAllElement(marginDiv);

			Core4j.Domhelper.setClass(marginDiv,this.makebaseClassName("tt-node-margin")+" "+this.makeClearfixClassName("tt-node-clearfix"));

				//margin
			var looptimes=node.level;
			if(looptimes>0){
				var tmpPnods=[];
				var pnode=node.pNode;
				while (looptimes > 0 && pnode != null) {
					tmpPnods[tmpPnods.length] = pnode;
					looptimes = pnode.level;
					pnode = pnode.pNode;
				}
				//caeate margin item
				var p1=tmpPnods.length;
				while(p1>0){
					p1--;
					var tmpPnode=tmpPnods[p1];
					var marginItemClassName=" "+this.makeClearfixClassName("tt-node-clearfix");
					if (tmpPnode.isLastNode) {
						marginItemClassName=this.makebaseClassName("tt-node-margin-empty")+marginItemClassName;
					}else{
						marginItemClassName=this.makeLineClassName("tt-node-margin-line")+marginItemClassName;
					}
					var marginItemDiv=Core4j.Domhelper.createElement('div',{
						attributeNames:['classStyle'],
						valueObject:{classStyle:marginItemClassName}
						},marginDiv);
				}
			}
				//margin end
		}
	};

	this.configNodeIconDiv=function(node){
		var iconDiv=node.iconDivObj;
		if(iconDiv==null){
			iconDiv=document.getElementById(this.makeIconDivId(node.id));
		}
		if (iconDiv != null) {

			Core4j.Domhelper.setClass(iconDiv,this.makeIconDivClassName("tt-node-icon")+" "+this.makeClearfixClassName("tt-node-clearfix"));

			var nodeIconImg=null;
			var nodeIconClassName=null;
			if(node.isLeaf==true){
				nodeIconImg=node.icon;
				nodeIconClassName=this.makeIconClassName("tt-node-icon-leaf");
			}else{
				if(node.isOpen||(node.isLoad&&node.childs.length==0)){
					nodeIconImg=node.iconOpen;
					nodeIconClassName=this.makeIconClassName("tt-node-icon-folderOpen");
				}else{
					nodeIconImg=node.icon;
					nodeIconClassName=this.makeIconClassName("tt-node-icon-folder");
				}
			}
			Core4j.Domhelper.addClass(iconDiv,nodeIconClassName);
			if(nodeIconImg!=null&&nodeIconImg!=''){
				iconDiv.style.backgroundImage ="url("+nodeIconImg+")";
			}
		}
	};

	//API
	//add 2010.6.18 18:13
	this.setNodeAsUnload=function(nodeId){

		var node=this.getNodeById(nodeId);

		if(node!=null){
			if(node.isLeaf==false){
				this.toggleNode(node,false);
				this.removeNodeChilds(node.id);
				//alert(node.childs.length);
			}else{
				node.isLeaf=false;
				this.configNodeIconDiv(node);
			}
			node.isOpen=false;
			node.isLoad=false;
			this.configNodeMarginDiv(node);
			this.configNodeClickDiv(node);
		}

	};

	this.configNodeClickDiv=function(node){
		var clickDiv=node.clickDivObj;
		if(clickDiv==null){
			clickDiv=document.getElementById(this.makeClickDivId(node.id));
		}
		if (clickDiv != null) {

			Core4j.Domhelper.setClass(clickDiv,this.makebaseClassName("tt-node-click")+" "+this.makeClearfixClassName("tt-node-clearfix"));

			var nodeClickClassName=null;
			if (node.isLeaf == true) {
				clickDiv.style.display="none";
			}else {
				clickDiv.style.display="";
				if(node.isLastNode){
					if(node.isOpen){
						nodeClickClassName=this.makeLineClassName("tt-node-click-last-open");;
					}else{
						nodeClickClassName=this.makeLineClassName("tt-node-click-last-close");
					}
				}else{
					if(node.isOpen){
						nodeClickClassName=this.makeLineClassName("tt-node-click-open");
					}else{
						nodeClickClassName=this.makeLineClassName("tt-node-click-close");
					}
				}
				if(node.isLoad&&node.childs.length==0){
					clickDiv.style.display="none";
				}
			}

			if(clickDiv.style.display!="none"){
				Core4j.Domhelper.addClass(clickDiv,nodeClickClassName);
			}else{
				this.marginItemReplaceClickDiv(node);
			}
		}
	};

	this.marginItemReplaceClickDiv=function(node){
		var marginDiv=node.marginDivObj;
		if(marginDiv==null){
			marginDiv=document.getElementById(this.makeMarginDivId(node.id));
		}
		if (marginDiv != null) {
			///caeate margin item
			var marginItemClassName=" "+this.makeClearfixClassName("tt-node-clearfix");
			if (node.isLastNode) {
				marginItemClassName=this.makeLineClassName("tt-node-margin-joinbottom")+marginItemClassName;
			}else{
				marginItemClassName=this.makeLineClassName("tt-node-margin-join")+marginItemClassName;
			}
			var marginItemDiv=Core4j.Domhelper.createElement('div',{
				attributeNames:['id','classStyle'],
				valueObject:{id:this.makeMarginItemReplaceClickDivId(node.id),classStyle:marginItemClassName}
			},marginDiv);
		}
	};

	this.removeMarginItemReplaceClickDiv = function(node){
		var eleobj=document.getElementById(this.makeMarginItemReplaceClickDivId(node.id));
		Core4j.Domhelper.removeElement(eleobj);
	};

	// cookie operation
	this.saveNodeCooikeByToggleNode=function(node){
		if(useCookie==true){
			if(node.isOpen){
				this.addExpandNodeIdToCookie(node.id);
			}else{
				this.removeNodeIdFromCookie(node.id);
			}
		}
		//alert(Core4j.getCookie(this.makeCookieName()));
	};



	this.addExpandNodeIdToCookie=function(nodeId){
		var openids=Core4j.getCookie(this.makeCookieName());
		var flag=this.cookiesSplitString;
		if(openids==null||openids==""){
			Core4j.setCookie(this.makeCookieName(),flag+nodeId+flag,this.cookieTime);
		}else{
			if(openids.indexOf(flag+nodeId+flag)==-1){
				openids=openids+nodeId+flag;
				Core4j.setCookie(this.makeCookieName(),openids,this.cookieTime);
			}
		}
	};

	this.removeNodeIdFromCookie=function(nodeId){
		var openids=Core4j.getCookie(this.makeCookieName());
		var flag=this.cookiesSplitString;
		if(openids!=null&&openids!=""){
			if(openids.indexOf(flag+nodeId+flag)!=-1){
				openids=openids.replace(flag+nodeId+flag,flag);
				Core4j.setCookie(this.makeCookieName(),openids,this.cookieTime);

				//delete chlids id cookie value
				var opidArray=openids.split(flag);
				var node=null;
				for (var i = 0, n = opidArray.length; i < n; i++) {
					var tmpid = opidArray[i];
					if (tmpid != null && tmpid != "") {
						node=this.getNodeById(tmpid);
						if (node != null) {
							if(node.pNode!=null&&node.pNode.id==nodeId){
								this.removeNodeIdFromCookie(node.id);
							}
						}
					}
				}
				///////////////////////////////////


			}
		}
	};

	this.getCookieExpandNodes=function(isOrderByLevel){
		var openids=Core4j.getCookie(this.makeCookieName());
		var flag=this.cookiesSplitString;
		var expandNodes=[];
		if(openids!=null&&openids!=""){
			var opidArray=openids.split(flag);
			var node=null;
			for(var i=0,n=opidArray.length;i<n;i++){
				var tmpid=opidArray[i];
				if(tmpid!=null&&tmpid!=""){
					node=this.getNodeById(tmpid);
					if(node==null||node.isLeaf==true){
						this.removeNodeIdFromCookie(tmpid);
					}else{
						expandNodes[expandNodes.length]=node;
					}
				}
			}
		}
		if(isOrderByLevel==true){
			expandNodes.sort(this.sortNodesLevelFunction);
		}


		return expandNodes;
	};

	this.sortNodesLevelFunction=function(a,b){
		var t1=a.level;
		var t2=b.level;
		if(t1==null||t1==""){
			t1=0;
		}
		if(t2==null||t2==""){
			t2=0;
		}
		if(parseInt(t1)>parseInt(t2)){
			return 1;
		}else{
			return -1;
		}
	};

	this.toggleNodesExpandByCookie=function(){
		if (useCookie == true) {
			var nodes = this.getCookieExpandNodes(true);
			for (var i = 0, n = nodes.length; i < n; i++) {
				this.toggleNode(nodes[i], true);
			}
		}
	};
	// cookie operation end

	/**when start loading a node ,must call this method first*/
	//API
	this.startLoadingNode=function(node,iconUrl,isHideLoadingIcon){
		if(node!=null&&node.isOpen&&node.isLoad==false){
				//show loading
				node.isLoading=true;
				if(!isHideLoadingIcon){
					if(iconUrl!=null){
						node.iconDivObj.style.backgroundImage ="url("+iconUrl+")";
					}else{//use theme icon
						node.iconDivObj.style.backgroundImage ="url("+this.getThemeRoot()+"img/loading.gif"+")";
					}
					node.iconDivObj.style.display="block";
				}
			}
	};

	/**when end loading a node ,must call this method first*/
	//API
	this.endLoadingNode=function(node){
		if(node!=null){
				node.isLoading=false;
				node.isLoad=true;
				///// hide loading
				if(Core4j.browser.msie){
					node.iconDivObj.removeAttribute("style");
				}else{
					node.iconDivObj.setAttribute("style","");
				}

				if(node.iconOpen!=null&&node.iconOpen!=''){
					node.iconDivObj.style.backgroundImage ="url("+node.iconOpen+")";
				}
				/////

				if(node.isLoad&&node.childs.length==0){
					node.clickDivObj.style.display="none";
					///caeate margin item
					this.marginItemReplaceClickDiv(node);
					//this.removeMarginItemReplaceClickDiv(node);
					//node.marginDivObj.appendChild(marginItemDiv);
					this.removeNodeIdFromCookie(node.id);
				}
		}
	};

	//API
	this.toggleNodeById=function(nodeId,isExpandNode){
		this.toggleNode(this.getNodeById(nodeId),isExpandNode);
	};

	/**click the node*/
	//API
	this.toggleNode=function(node,isExpandNode){

		if(node==null){
			return null;
		}
		//var rowObject=document.getElementById(this.makeRowId(node.id));
		var rowObject=node.rowObj;
		//alert(node.isLoading);
		if(node.visible!='none'&&rowObject!=null&&node.isLoading==false){

			if(isExpandNode!=null&&isExpandNode==node.isOpen){
				return null;
			}

			var isExpand=false;
			if(node.isOpen==false){
				isExpand=true;
				node.isOpen=true;
			}else{
				node.isOpen=false;
			}

			//cookie operation
			this.saveNodeCooikeByToggleNode(node);

			var iconDiv=node.iconDivObj;
			var clickDiv=node.clickDivObj;
			//change icon and click icon
			if (isExpand) {
				Core4j.Domhelper.changeClass(iconDiv,this.makeIconClassName("tt-node-icon-folder"),this.makeIconClassName("tt-node-icon-folderOpen"));
				if(node.iconOpen!=null&&node.iconOpen!=''){
						iconDiv.style.backgroundImage ="url("+node.iconOpen+")";
				}

				if (node.isLastNode) {
					Core4j.Domhelper.changeClass(clickDiv,this.makeLineClassName("tt-node-click-last-close"),this.makeLineClassName("tt-node-click-last-open"));
					//clickDiv.className="tt-node-click tt-node-clearfix tt-node-click-last-open";
				}else{
					Core4j.Domhelper.changeClass(clickDiv,this.makeLineClassName("tt-node-click-close"),this.makeLineClassName("tt-node-click-open"));
					//clickDiv.className="tt-node-click t-node-clearfix tt-node-click-open";
				}

			}else{
				Core4j.Domhelper.changeClass(iconDiv,this.makeIconClassName("tt-node-icon-folderOpen"),this.makeIconClassName("tt-node-icon-folder"));
				if(node.icon!=null&&node.icon!=''){
					iconDiv.style.backgroundImage ="url("+node.icon+")";
				}
				if (node.isLastNode) {
					Core4j.Domhelper.changeClass(clickDiv,this.makeLineClassName("tt-node-click-last-open"),this.makeLineClassName("tt-node-click-last-close"));
				}else{
					Core4j.Domhelper.changeClass(clickDiv,this.makeLineClassName("tt-node-click-open"),this.makeLineClassName("tt-node-click-close"));
				}
			}

			//var initd2=new Date();
			this.flowDisplayOrNotChildNodes(node.childs);
			//alert("flow use time:"+(new Date()-initd2));

			//EVENTS tabletree
			if(isExpand){
				/*if(this.onExpandNodeEvent!=null){
					this.onExpandNodeEvent(node,this);
				}*/
				//!!!fire onExpandNodeEvent
				if(this.onExpandNodeEvents!=null){
					for(var i=0,n=this.onExpandNodeEvents.length;i<n;i++){
						var onExpandNodeEvent=this.onExpandNodeEvents[i];
						if(onExpandNodeEvent!=null&&typeof(onExpandNodeEvent)=='function'){
							onExpandNodeEvent(node,this);
						}
					}
				}

			}else{
				/*if(this.onCollapseNodeEvent!=null){
					this.onCollapseNodeEvent(node,this);
				}*/
				//!!!fire onCollapseNodeEvent
					if(this.onCollapseNodeEvents!=null){
					for(var i=0,n=this.onCollapseNodeEvents.length;i<n;i++){
						var onCollapseNodeEvent=this.onCollapseNodeEvents[i];
						if(onCollapseNodeEvent!=null&&typeof(onCollapseNodeEvent)=='function'){
							onCollapseNodeEvent(node,this);
						}
					}
				}

			}


		}
	};
	//tmpchild.visible
	this.flowDisplayOrNotChildNodes=function(nodes){
		if(nodes!=null&&nodes.length>0){
			var n=nodes.length;
			for(var i=0;i<n;i++){
				var tmpchild=nodes[i];
				//var rowObject=document.getElementById(this.makeRowId(tmpchild.id));
				var rowObject=tmpchild.rowObj;

				var pNode=tmpchild.pNode;
				if(pNode.isOpen&&pNode.visible!='none'){//show
					tmpchild.visible="";
				}else{//hide
					tmpchild.visible="none";
				}
				rowObject.style.display=tmpchild.visible;

				if(tmpchild.isOpen&&tmpchild.isLeaf==false){
					this.flowDisplayOrNotChildNodes(tmpchild.childs);
				}
			}
		}
	};


	this.addRowToTableObject=function(node,index){

		var rowObject=null;
		if(index==null){
			index=this.tableObject.rows.length;
		}
		rowObject=this.tableObject.insertRow(index);
		node.rowObj=rowObject;
		rowObject.setAttribute("id",this.makeRowId(node.id));
		rowObject.style.display=node.visible;

		Core4j.Domhelper.addEventToEl(rowObject,Core4j.Domhelper.ElEventType.CLICK,function(){
			tabletree.setSelectNode(node);
		});

		//alert(node.dataObject.name+"["+node.visible+"/"+node.id+"]");
		//[{isNodeClick:,header:,dataIndex:,width:,renderFunction:}]

		for(var i=0,n=this.columns.length;i<n;i++){

			var tmpColum=this.columns[i];
			var value=null;
			var width=null;
			var renderFunction=null;
			var isNodeClick=false;
			if(nodeClickColumnIndex==i){
				isNodeClick=true;
			}

			if(node.dataObject!=null){
				value=eval("node.dataObject."+tmpColum.dataIndex);
				width=tmpColum.width;
				renderFunction=tmpColum.renderFunction;

			}
			var cellObject=rowObject.insertCell(rowObject.cells.length);
			if(width!=null){
				cellObject.setAttribute("width",width);
			}

			var contentValueClassName=this.makeContentClassName("tt-node-content");

			//init node click
			if(isNodeClick==true){

				contentValueClassName=this.makeContentClassName("tt-node-click-content");

				//margin div
				var marginDiv=Core4j.Domhelper.createElement('div',{
				attributeNames:['id'],
				valueObject:{id:this.makeMarginDivId(node.id)}
				},cellObject);
				//end margin div

				//click div
				var clickDiv=Core4j.Domhelper.createElement('div',{
				attributeNames:['id'],
				valueObject:{id:this.makeClickDivId(node.id)}
				},cellObject);
				//end click div

				//icon div
				var iconDiv=Core4j.Domhelper.createElement('div',{
				attributeNames:['id'],
				valueObject:{id:this.makeIconDivId(node.id)}
				},cellObject);
				//end icon div

				/*Core4j.Domhelper.addEventToEl(iconDiv,Core4j.Domhelper.ElEventType.CLICK,function(){
				tabletree.setSelectNode(node);
				});*/

				node.marginDivObj=marginDiv;
				node.iconDivObj=iconDiv;
				node.clickDivObj=clickDiv;

				//init
				//margin div
				this.configNodeMarginDiv(node);
				//margin div end

				//icon div
				this.configNodeIconDiv(node);
				//icon div end

				//click div
				this.configNodeClickDiv(node);
				//click div end
/*
				node.toggleEventfun=function(){
					tabletree.toggleNode(node);
				}
*/
				//add event to click node
				Core4j.Domhelper.addEventToEl(clickDiv,Core4j.Domhelper.ElEventType.CLICK,function(event){
					if(event.stopPropagation){
						event.stopPropagation();
					}else{
						event.cancelBubble=true;
					}
					tabletree.toggleNode(node);
				});
			}

			var dateValueEl=Core4j.Domhelper.createElement('div',{
				attributeNames:['id','classStyle'],
				valueObject:{id:this.makeValueDivId(node.id,i,isNodeClick),classStyle:contentValueClassName}
			},cellObject);

			/*if(isNodeClick){
				Core4j.Domhelper.addEventToEl(dateValueEl,Core4j.Domhelper.ElEventType.CLICK,function(){
				tabletree.setSelectNode(node);
				});
			}*/


			var showvalue=null;
			if(renderFunction!=null&&typeof(renderFunction)=='function'){
				showvalue=renderFunction({
					dataValue:value,
					node:node,
					tabletreeObj:this,
					rowObj:rowObject,
					rowIndex:index,
					container:dateValueEl,
					columnIndex:i
				});
			}else{
				showvalue=value;
			}
			if (showvalue != null) {
				if (typeof(showvalue) == 'object') {
					if (Core4j.checkIsArray(showvalue)) {
						for (var q = 0, w = showvalue.length; q < w; q++) {
							var tmpobj = showvalue[q];
							if (typeof(tmpobj) == 'object') {
								dateValueEl.appendChild(tmpobj);
							}
						}
					}
					else {
						dateValueEl.appendChild(showvalue);
					}
				}
				else {
					dateValueEl.innerHTML = showvalue;
				}
			}
			/*if(this.onBuildTreeAddNodeEvent!=null){
				this.onBuildTreeAddNodeEvent(node,this);
			}*/
			//!!!fire onBuildTreeAddNodeEvent
			if(this.onBuildTreeAddNodeEvents!=null){
				for(var t1=0,n1=this.onBuildTreeAddNodeEvents.length;t1<n1;t1++){
					var onBuildTreeAddNodeEvent=this.onBuildTreeAddNodeEvents[t1];
					if(onBuildTreeAddNodeEvent!=null&&typeof(onBuildTreeAddNodeEvent)=='function'){
						onBuildTreeAddNodeEvent(node,this);
					}
				}
			}
		}
	};


	this.initNodeAsTree = function(node,paramPNode){
			if(node.pid==null){
				this.rootNodes[this.rootNodes.length]=node;
			}else{
				var pNode=paramPNode||this.getNodeById(node.pid);
				if(pNode!=null){
					node.pNode=pNode;
					pNode.childs[pNode.childs.length]=node;
					pNode.isLeaf=false;
					pNode.isLoad=true;
				}
			}
	};

	this.initNodeInfo=function(node){
			var pNode=node.pNode;
			if(pNode==null){
				node.level=0;
				node.isRoot=true;
				node.visible="";
			}else{
				node.level=pNode.level+1;
				if(pNode.isOpen){
					node.visible=pNode.visible;
				}
				node.isRoot=false;

			}
			//alert("[init]"+node.dataObject.name+"["+node.visible+"/"+node.id+"]")
			/*if(node.isReLoad==null){
				node.isReLoad=this.isReloadNode;
			}*/
	};



	this.sortNodesOrderFunction=function(a,b){
		var t1=a.order;
		var t2=b.order;
		if(t1==null||t1==""){
			t1=0;
		}
		if(t2==null||t2==""){
			t2=0;
		}
		if(parseInt(t1)>parseInt(t2)){
			return 1;
		}else{
			return -1;
		}
	};



	this.flowToReConfigNodesMarginAndClickDiv=function(nodes){
		if (nodes != null && nodes.length > 0) {
			for (var i = 0, n = nodes.length; i < n; i++) {
				var tmpNode=nodes[i];
				this.configNodeMarginDiv(tmpNode);
				this.configNodeClickDiv(tmpNode);
				this.flowToReConfigNodesMarginAndClickDiv(tmpNode.childs);
			}
		}
	};

	this.flowToCountRowIndexByPrewNode=function(prewNode){
		var index=prewNode.rowObj.rowIndex;

		while(prewNode.childs.length>0){
			var lastchild=prewNode.childs[prewNode.childs.length-1];
			index=lastchild.rowObj.rowIndex;
			prewNode=lastchild;
		}
		return index;
	};

	//if the childs of pnode is empty ,you can use this method to addnodes
	//API
	this.loadingAddNodes=function(nodes,pNodeid){
		if(pNodeid!=null&&nodes!=null&&nodes.length>0){
			var pnode=this.getNodeById(pNodeid);
			var pchilds=pnode.childs||[];
			if(pnode!=null&&pchilds.length==0){
				var pnodeIsLeaf=pnode.isLeaf;
				var tmpNode=null;
				for (var i = 0, n = nodes.length; i < n; i++) {
					tmpNode = nodes[i];
					if (tmpNode.id != null&&nodeMap.hasKey(tmpNode.id)==false) {
						this.configDefaultValueToTableTreeNodeBoject(tmpNode);
						tmpNode.pid=pNodeid;
						this.initNodeAsTree(tmpNode,pnode);
						this.initNodeInfo(tmpNode);
						nodeMap.put(tmpNode.id, tmpNode);
					}
				}
				var childs=pnode.childs;

				if(childs!=null&&childs.length>0){
					//!!!fire onBeforeLoadingAddNodesEvent
					if(this.onBeforeLoadingAddNodesEvents!=null){
						for(var i=0,n=this.onBeforeLoadingAddNodesEvents.length;i<n;i++){
							var onBeforeLoadingAddNodesEvent=this.onBeforeLoadingAddNodesEvents[i];
							if(onBeforeLoadingAddNodesEvent!=null&&typeof(onBeforeLoadingAddNodesEvent)=='function'){
								onBeforeLoadingAddNodesEvent(childs,pnode,this);
							}
						}
					}

					this.sortNodes(childs);
					childs[childs.length-1].isLastNode=true;

					var insertRowIndex=pnode.rowObj.rowIndex;
					if(pnodeIsLeaf){
						this.removeMarginItemReplaceClickDiv(pnode);
						this.configNodeIconDiv(pnode);
						this.configNodeClickDiv(pnode);
					}else{
						//this.removeMarginItemReplaceClickDiv(pNode);
						this.removeMarginItemReplaceClickDiv(pnode);
						this.configNodeClickDiv(pnode);
					}


					for (var i = 0, n = childs.length; i < n; i++) {
						insertRowIndex=insertRowIndex+1;
						this.addRowToTableObject(childs[i],insertRowIndex);
					}

					//!!!fire onAfterLoadingAddNodesEvent
					if(this.onAfterLoadingAddNodesEvents!=null){
						for(var i=0,n=this.onAfterLoadingAddNodesEvents.length;i<n;i++){
							var onAfterLoadingAddNodesEvent=this.onAfterLoadingAddNodesEvents[i];
							if(onAfterLoadingAddNodesEvent!=null&&typeof(onBeforeLoadingAddNodesEvent)=='function'){
								onAfterLoadingAddNodesEvent(childs,pnode,this);
							}
						}
					}
				}
			}
		}
	};


	/**add a node to the tree
	 * @param tableTreeNode the TableTreeNode/json object
	 * @pNodeid if pNodeid is not null and pnode isexist,tableTreeNode.pid=pNodeid,else use the tableTreeNode.pid as default
	 */
	//API
	this.addNode=function(tableTreeNode,pNodeid){
		if(tableTreeNode!=null&&tableTreeNode.id!=null&&!nodeMap.hasKey(tableTreeNode.id)){
			/*if(this.onBeforeAddNodeEvent!=null){
				var canAdd=this.onBeforeAddNodeEvent(tableTreeNode,this);
				if(canAdd==false){
					return false;
				}
			}*/

			this.configDefaultValueToTableTreeNodeBoject(tableTreeNode);

			//!!!fire onBeforeAddNodeEvent
			if(this.onBeforeAddNodeEvents!=null){
				for(var i=0,n=this.onBeforeAddNodeEvents.length;i<n;i++){
					var onBeforeAddNodeEvent=this.onBeforeAddNodeEvents[i];
					if(onBeforeAddNodeEvent!=null&&typeof(onBeforeAddNodeEvent)=='function'){
						onBeforeAddNodeEvent(tableTreeNode,this);
					}
				}
			}
			nodeMap.put(tableTreeNode.id,tableTreeNode);


			var pid=pNodeid;
			var pNode=null;
			//var isRoot=false;
			var pNodechilds=null;
			var lastchild=null;

			if(pid!=null&&nodeMap.hasKey(pid)==true){
				tableTreeNode.pid=pid;
			}
			if(tableTreeNode.pid!=null){
				pNode=this.getNodeById(pid);
			}


			var insertRowIndex=0;

			if(pNode==null){
				pNodechilds=this.rootNodes;
				if(pNodechilds!=null&&pNodechilds.length>0){
					lastchild=pNodechilds[pNodechilds.length-1];
				}
				tableTreeNode.pid=null;
				this.initNodeAsTree(tableTreeNode);
				this.initNodeInfo(tableTreeNode);
			}else{
				//reconfig pnode
				pNodechilds=pNode.childs;
				if(pNodechilds!=null&&pNodechilds.length>0){
					lastchild=pNodechilds[pNodechilds.length-1];
				}

				var pnodeIsLeaf=pNode.isLeaf;
				/*if(!pnodeIsLeaf){
					this.toggleNode(pNode,true);
				}*/
				this.initNodeAsTree(tableTreeNode,pNode);
				this.initNodeInfo(tableTreeNode);
				if(pnodeIsLeaf){
					this.removeMarginItemReplaceClickDiv(pNode);
					this.configNodeIconDiv(pNode);
					this.configNodeClickDiv(pNode);
				}else{
					//this.removeMarginItemReplaceClickDiv(pNode);
					this.removeMarginItemReplaceClickDiv(pNode);
					this.configNodeClickDiv(pNode);
				}
				insertRowIndex=pNode.rowObj.rowIndex;
			}
			var iswantReconfigLastchild=false;
			//add row

			if(lastchild!=null){
				this.sortNodes(pNodechilds);
				var tmplastchild=pNodechilds[pNodechilds.length-1];
				var prewNode=null;
				if(tableTreeNode==tmplastchild){
					tableTreeNode.isLastNode=true;
					iswantReconfigLastchild=true;
					prewNode=pNodechilds[pNodechilds.length-2]
				}else{
					prewNode=pNodechilds[pNodechilds.indexOf(tableTreeNode)-1];
				}
				if(prewNode!=null){
					insertRowIndex=this.flowToCountRowIndexByPrewNode(prewNode);
				}

			}else{
				tableTreeNode.isLastNode=true;
			}

			this.addRowToTableObject(tableTreeNode,insertRowIndex+1);


			//reconfig last child
			if(iswantReconfigLastchild){
				lastchild.isLastNode=false;
				this.removeMarginItemReplaceClickDiv(lastchild);
				this.configNodeClickDiv(lastchild);
				this.flowToReConfigNodesMarginAndClickDiv(lastchild.childs);
			}




			if(this.onAfterAddNodeEvents!=null){
				for(var i=0,n=this.onAfterAddNodeEvents.length;i<n;i++){
					var onAfterAddNodeEvent=this.onAfterAddNodeEvents[i];
					if(onAfterAddNodeEvent!=null&&typeof(onAfterAddNodeEvent)=='function'){
						onAfterAddNodeEvent(tableTreeNode,this);
					}
				}
			}
			/*if(this.tabletreeEventListerners!=null){
				for(var i=0,n=this.tabletreeEventListerners.length;i<n;i++){
					var tteventlistener=this.tabletreeEventListerners[i];
					if(tteventlistener!=null&&typeof(tteventlistener.onAfterAddNodeEvent)=='function'){
						tteventlistener.onAfterAddNodeEvent(node,this);
					}
				}
			}*/
			return true;
		}else{
			return false;
		}
	};
	//first ,find all tr,save to a array,and table remove the rows,reconfig the pnode and prew node
	//second,insert the tr array before the pNode tr,reconfig....[ table_obj.insertBefore(tr_obj,tr_move_obj);]
	//API
	this.moveNodeToNode=function(fromid,toid){

	};

	/**remove a node from the tree
	 * @param id the id of TableTreeNode object
	 */
	//API
	this.removeNode=function(id){
		var node=this.getNodeById(id);
		if(node!=null){
		/*if(this.onBeforeRemoveNodeEvent!=null){
			var canRemove=this.onBeforeRemoveNodeEvent(node,this);
			if(canRemove==false){
				return false;
			}
		}*/
			//!!!fire onBeforeRemoveNodeEvent
			if(this.onBeforeRemoveNodeEvents!=null){
				for(var i=0,n=this.onBeforeRemoveNodeEvents.length;i<n;i++){
					var onBeforeRemoveNodeEvent=this.onBeforeRemoveNodeEvents[i];
					if(onBeforeRemoveNodeEvent!=null&&typeof(onBeforeRemoveNodeEvent)=='function'){
						onBeforeRemoveNodeEvent(node,this);
					}
				}
			}
			//TODO

			var pNode=node.pNode;
			var childs=null;
			if(pNode==null){
				childs=this.rootNodes;
			}else{
				childs=pNode.childs;
			}

			var lastNode=null;
			if(childs!=null&&childs.length>0){
				lastNode=childs[childs.length-1];

			}
			//alert(childs.length);
			this.flowToRemoveNodeAndChilds(node);
			//alert(childs.length);
			if(pNode!=null&&pNode.childs.length==0){
				this.configNodeMarginDiv(pNode);
				this.configNodeClickDiv(pNode);
			}

			if(childs!=null&&childs.length>0){
				if(lastNode!=childs[childs.length-1]){
					//alert(lastNode.id);
					lastNode=childs[childs.length-1];
					lastNode.isLastNode=true;
					this.configNodeMarginDiv(lastNode);
					this.configNodeClickDiv(lastNode);
					this.flowToReConfigNodesMarginAndClickDiv(lastNode.childs);
				}
			}




			//!!!fire onAfterRemoveNodeEvent
			if(this.onAfterRemoveNodeEvents!=null){
				for(var i=0,n=this.onAfterRemoveNodeEvents.length;i<n;i++){
					var onAfterRemoveNodeEvent=this.onAfterRemoveNodeEvents[i];
					if(onAfterRemoveNodeEvent!=null&&typeof(onAfterRemoveNodeEvent)=='function'){
						onAfterRemoveNodeEvent(node,this);
					}
				}
			}

			return true;
		}else{
			return false;
		}

	};


	this.flowToRemoveNodeAndChilds=function(node){
		if(node!=null){
			var childs=node.childs;
			if(childs!=null&&childs.length>0){
				while(childs.length>0){
					var tmpNode=childs[childs.length-1];
					this.flowToRemoveNodeAndChilds(tmpNode);
				}
			}
			nodeMap.remove(node.id);
			this.disSelectNode(node);
			if(node.pNode==null){
				this.rootNodes.removeItem(node);
			}else{
				node.pNode.childs.removeItem(node);
			}
			//Core4j.Domhelper.removeEventFromEl(node.rowObj,Core4j.Domhelper.ElEventType.CLICK);
			this.tableObject.deleteRow(node.rowObj.rowIndex);

			//alert(node.rowObj);
			//this.tableObject.insertBefore(node.rowObj,this.tableObject.rows[1]);
			//this.tableObject.insertRow(node.rowObj,this.tableObject.rows.length);
			//this.tableObject.appendChild(node.rowObj);

		}
	};

	//api
	this.removeNodeChilds=function(nodeId){
		//alert(nodeId);
		var node = this.getNodeById(nodeId);
		if (node != null&&node.childs!=null) {
			//!!!fire onBeforeRemoveNodeEvent
			if(this.onBeforeRemoveNodeChildsEvents!=null){
				for(var i=0,n=this.onBeforeRemoveNodeChildsEvents.length;i<n;i++){
					var onBeforeRemoveNodeChildsEvent=this.onBeforeRemoveNodeChildsEvents[i];
					if(onBeforeRemoveNodeChildsEvent!=null&&typeof(onBeforeRemoveNodeChildsEvent)=='function'){
						onBeforeRemoveNodeChildsEvent(node,this);
					}
				}
			}

			var childs = node.childs;
			if (childs != null && childs.length > 0) {
				while (childs.length > 0) {
					var tmpNode = childs[childs.length - 1];
					this.flowToRemoveNodeAndChilds(tmpNode);
				}
			}


			//!!!fire onAfterRemoveNodeEvent
			if(this.onAfterRemoveNodeChildsEvents!=null){
				for(var i=0,n=this.onAfterRemoveNodeChildsEvents.length;i<n;i++){
					var onAfterRemoveNodeChildsEvent=this.onAfterRemoveNodeChildsEvents[i];
					if(onAfterRemoveNodeChildsEvent!=null&&typeof(onAfterRemoveNodeChildsEvent)=='function'){
						onAfterRemoveNodeChildsEvent(node,this);
					}
				}
			}

			this.configNodeClickDiv(node);
		}
	};


	/**get a node from tree
	 * @param id the id of TableTreeNode object
	 */
	//API
	this.getNodeById=function(id){
		return nodeMap.get(id);
	};


	//for json object
	this.configDefaultValueToTableTreeNodeBoject=function(node){
		if(node.isAutoLeaf==null){
			node.isAutoLeaf=true;
			if(node.isLeaf!=null&&node.isLeaf==false){
				node.isAutoLeaf=false;
			}
		}
		if(node.isLeaf==null){node.isLeaf=true;}
		if(node.isLoad==null){node.isLoad=false;}
		if(node.isOpen==null){node.isOpen=false;}
		if(node.childs==null){node.childs=[];}
		if(node.level==null){node.level=0;}
		if(node.visible==null){node.visible="none";}
		if(node.isRoot==null){node.isRoot=false;}
		if(node.isLastNode==null){node.isLastNode=false;}
		if(node.isLoading==null){node.isLoading=false;}
		if(node.canSelect==null){node.canSelect=true;}
	};

/* 	//[{dataIndex:,width:,renderFunction:,tdAttributes:,trAttributeValues:}]
	this.columns=configObject.columns||[];
	this.id=configObject.id;
	this.trAttributes=configObject.trAttributes;
	this.trAttributeValueObject=configObject.trAttributeValueObject;
	this.dataObject=configObject.dataObject;
	this.rowObj=null;
*/


	//API
	this.addHeader=function(tabletreeHF){
		this.addHFRowToTableObject(tabletreeHF,true);
		this.headers[this.headers.length]=tabletreeHF;
	};
	//API
	this.addFooter=function(tabletreeHF){
		this.addHFRowToTableObject(tabletreeHF,false);
		this.footers[this.footers.length]=tabletreeHF;
	};
	//API
	this.removeHeader=function(index,tabletreeHF){
		this.removeTabletreeHF(index,tabletreeHF,true);
	};
	//API
	this.removeFooter=function(index,tabletreeHF){
		this.removeTabletreeHF(index,tabletreeHF,false);
	};

	//API
	this.removeAllHeader=function(){
		this.removeAllTabletreeHF(true);
	};
	//API
	this.removeAllFooter=function(){
		this.removeAllTabletreeHF(false);
	};

	this.buildHeaders=function(){
		for(var i=0,n=this.headers.length;i<n;i++){
				var header=this.headers[i];
				if(header!=null){
					this.addHFRowToTableObject(header,true);
				}
		}
	};

	this.buildFooters=function(){
		for(var i=0,n=this.footers.length;i<n;i++){
				var footer=this.footers[i];
				if(footer!=null){
					this.addHFRowToTableObject(footer,false);
				}
		}
	};

	this.addHFRowToTableObject=function(tabletreeHF,isHeader){
		var index=0;
		if(isHeader==false){
			index=this.tableObject.rows.length;
		}
		var rowObject=this.tableObject.insertRow(index);
		tabletreeHF.rowObj=rowObject;

		/*
		 * 	this.trAttributeNames=configObject.trAttributeNames;
			this.trAttributeValueObject=configObject.trAttributeValueObject;
		 * */

		var attributeNames=tabletreeHF.trAttributeNames;
		var valueObject=tabletreeHF.trAttributeValueObject;
		Core4j.Domhelper.setElAttributes(rowObject,attributeNames,valueObject);

		//[{dataIndex:,width:,renderFunction:,tdAttributes:,tdAttributeValueObject:}]
		var columns=tabletreeHF.columns;
		for (var i = 0, n = columns.length; i < n; i++) {
			var tmpColum=columns[i];
			var cellObject=rowObject.insertCell(rowObject.cells.length);
			//[{dataIndex:,renderFunction:,tdAttributeNames:,tdAttributeValueObject:}]
        	Core4j.Domhelper.setElAttributes(cellObject,tmpColum.tdAttributeNames,tmpColum.tdAttributeValueObject);

			var renderFunction=tmpColum.renderFunction;
			var value=null;
			var dataObject=tabletreeHF.dataObject;
			if(dataObject!=null){
				value=eval("dataObject."+tmpColum.dataIndex);
			}


			var showvalue=null;
			if(renderFunction!=null&&typeof(renderFunction)=='function'){
				showvalue=renderFunction({
					dataValue:value,
					tabletreeHF:tabletreeHF,
					tabletreeObj:this,
					columnIndex:i
				});
			}else{
				showvalue=value;
			}
			if (showvalue != null) {
				if (typeof(showvalue) == 'object') {
					if (Core4j.checkIsArray(showvalue)) {
						for (var q = 0, w = showvalue.length; q < w; q++) {
							var tmpobj = showvalue[q];
							if (typeof(tmpobj) == 'object') {
								cellObject.appendChild(tmpobj);
							}
						}
					}
					else {
						cellObject.appendChild(showvalue);
					}
				}
				else {
					cellObject.innerHTML = showvalue;
				}
			}

			//Core4j.Domhelper.renderEl(cellObject,renderFunction,value);
		}

	};

	this.removeHFRowFromTableObject=function(tabletreeHF){
		var trObj=tabletreeHF.rowObj;
		if(trObj!=null){
			Core4j.Domhelper.removeElement(trObj);
		}
	};

	this.removeTabletreeHF=function(index,tabletreeHF,isHeader,isRemoveFromArray){
		var hfs=this.headers;
		if(isHeader==false){
			hfs=this.footers;
		}
		if(hfs!=null){
			var tmptabletreeHF=null;
			if(index!=null&&index<hfs.length){
				tmptabletreeHF=hfs[index];
				this.removeHFRowFromTableObject(tmptabletreeHF);
				if(isRemoveFromArray!=false){
					hfs.removeItemByIdex(index);
				}
			}else{
				if(tabletreeHF!=null){
					tmptabletreeHF=tabletreeHF;
					this.removeHFRowFromTableObject(tmptabletreeHF);
					if(isRemoveFromArray!=false){
						hfs.removeItem(tabletreeHF);
					}
				}
			}
		}

	};

	this.removeAllTabletreeHF = function(isHeader){
		var hfs=this.headers;
		if(isHeader==false){
			hfs=this.footers;
		}
		if (hfs != null) {
			for (var i = 0, n = hfs.length; i < n; i++) {
				this.removeTabletreeHF(i,null,true,false);
			}
		}
		if(isHeader==false){
			this.footers=[];
		}else{
			this.headers=[];
		}
	};


};

Core4j.toolbox.TableTreeNode=function(configObject){

	this.id=configObject.id;
	this.pid=configObject.pid;
	this.icon=configObject.icon;
	this.iconOpen=configObject.iconOpen;
	this.iconLoading=configObject.iconLoading;
	this.userObject=configObject.userObject;
	this.dataObject=configObject.dataObject;
	this.order=configObject.order;
	this.isLeaf=configObject.isLeaf;//||true;
	if(this.isLeaf==null){this.isLeaf=true;}
	this.isLoad=configObject.isLoad;//||false;
	if(this.isLoad==null){this.isLoad=false;}
	this.isOpen=configObject.isOpen;//||false;
	if(this.isOpen==null){this.isOpen=false;}
	this.isLoading=false;
	this.canSelect=configObject.canSelect;//||true;
	if(this.canSelect==null){this.canSelect=true;}

//when delete child node ,if the childs length the pnode is 0,and isAutoLeaf is true
//the node icon will be change to leaf and this.isLeaf=true
	this.isAutoLeaf=true;
	if(configObject.isLeaf!=null&&configObject.isLeaf==false){
		this.isAutoLeaf=false;
	}
	this.rowObj=null;
	this.marginDivObj=null;
	this.iconDivObj=null;
	this.clickDivObj=null;

	this.pNode=null;
	this.childs=[];
	this.level=0;
	this.visible="none";
	this.isRoot=false;
	this.isLastNode=false;
};

/**
 * TableTree Header Or Footer Object
 * @param {Object} configObject
 */
Core4j.toolbox.TableTreeHF = function(configObject){
	//[{dataIndex:,renderFunction:,tdAttributeNames:,tdAttributeValueObject:}]
	this.columns=configObject.columns||[];
	this.trAttributeNames=configObject.trAttributeNames;
	this.trAttributeValueObject=configObject.trAttributeValueObject;
	this.dataObject=configObject.dataObject;
	this.rowObj=null;
};