<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@include file='/pages/common/common.jsp'%>
<%@include file='/pages/common/taglib.jsp'%>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/CommonService.js'></script>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
		<script language="javascript" src='${pageContext.request.contextPath}/common/js/ScrachParam.js'></script>
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/json.js"></script>
	<%
	  String id = request.getParameter("id") ;
	  request.setAttribute("nsortId",id) ;
   %>
<html>
	<head>
		<title>增加品牌</title>
		<link href="${pageContext.request.contextPath}/common/style/global_base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">		
        function onEnter(e){
			var   ev   =   window.event||   e; 
			if(ev.keyCode == 13){
				loadDataByName();
			}
		}  
       function saveBrand(obj){
          obj.disabled = "disabled" ;
          var idMap = [];
          var nameMap = [];
          for(i=0;i<document.getElementById("Select2").options.length;i++){
            var brandId = document.getElementById("Select2").options[i].value;
            var brandName = document.getElementById("Select2").options[i].text;
            idMap.push(brandId) ;
           nameMap.push(brandName);
          } 
          var idJson = JSON.stringify(idMap);
          var nameJson = JSON.stringify(nameMap);
          CommonService.saveBrandItem(idJson,nameJson,'${nsortId}','${sessionScope.SPRING_SECURITY_LAST_USERNAME}',function(data){
              if(data==null){
                 alert("操作成功");
                 window.close();               
              }else{
                 alert("操作失败: "+data) ;
              }
              obj.disabled = false ;
          }) ;
       }

	function loadDataByName(){
		 	var name = "%"+document.getElementById("selectname").value+"%";
		 	 CommonService.getUsableBrandByName('${nsortId}', '${sessionScope.SPRING_SECURITY_LAST_USERNAME}', name,function(dataArray){
                try{
                dwr.util.removeAllOptions("Select1");
                 for(var i=0;i<dataArray.length ;i++){
                     var data = dataArray[i] ;
 					 dwr.util.addOptions("Select1", [{ value:data.key, disp : data.value }], "value", "disp");
                 }
               }catch(e){}
           }) ;
		 }

       function loadData(){
          //alert("loadData " + '${nsortId}' +" " + '${sessionScope.SPRING_SECURITY_LAST_USERNAME}');
	       //未有选择
           CommonService.getUsableBrand('${nsortId}','${sessionScope.SPRING_SECURITY_LAST_USERNAME}',function(dataArray){
               try{
                 for(var i=0;i<dataArray.length ;i++){        
                     var data = dataArray[i] ;                                   
					 dwr.util.addOptions("Select1", [{ value:data.key, disp : data.value }], "value", "disp");
                 }
               }catch(e){}
           });
           
           //已经选择的
           CommonService.getUsedBrand('${nsortId}','${sessionScope.SPRING_SECURITY_LAST_USERNAME}',function(dataArray){
                try{
                 for(var i=0;i<dataArray.length ;i++){
                     var data = dataArray[i] ;
	                 dwr.util.addOptions("Select2", [{ value:data.key, disp : data.value }], "value", "disp");
                 }
               }catch(e){}
           }) ;
       }
       
       
       function toRight(leftObj,rightObj){
            for(var i=0;i<leftObj.options.length;i++){
				if(leftObj.options[i].selected){
				   var e = leftObj.options[i];
				   //是否存在右边框
				   var len = 0 ;
				   try{
				       len = rightObj.options.length ;
				   }catch(e){}
				   
				   var bool = false ;
				   for(var j=0 ;j<len;j++){
				       var temp = rightObj.options[j] ;
				       if(e.text==temp.text&&e.value==temp.value){
				          //存在相关数据则返回
				          bool = true ;
				       }
				   }
				   leftObj.remove(i);
				   i=i-1;
				   if(bool) continue ;
				   
				   rightObj.options.add(new Option(e.text, e.value));
				   
				}
			}
       }
       function toLeft(leftObj,rightObj){
           for(var i=0;i<leftObj.options.length;i++){
               if(leftObj.options[i].selected){
					var e = leftObj.options[i];
					
					//是否存在右边框
				    var len = 0 ;
				    try{
				       len = rightObj.options.length ;
				    }catch(e){}
				   
				    var bool = false ;
				    for(var j=0 ;j<len;j++){
				       var temp = rightObj.options[j] ;
				       if(e.text==temp.text&&e.value==temp.value){
				          //存在相关数据则返回
				          bool = true ;
				       }
				    }
				    leftObj.remove(i);
					i=i-1;
					if(bool){
					    continue ;
					}
					
					rightObj.options.add(new Option(e.text, e.value));
			    }
	       }
           //toRight(rightObj,leftObj) ;
       }
       function toRightAll(leftObj,rightObj){
            for(var i=0;i<leftObj.options.length;i++){
				var e = leftObj.options[i];
				
				//是否存在右边框
			    var len = 0 ;
			    try{
			       len = rightObj.options.length ;
			    }catch(e){}
			   
			    var bool = false ;
			    for(var j=0 ;j<len;j++){
			       var temp = rightObj.options[j] ;
			       if(e.text==temp.text&&e.value==temp.value){
			          //存在相关数据则返回
			          bool = true ;
			       }
			    }
				if(bool) continue ;
				
				rightObj.options.add(new Option(e.text, e.value));
				leftObj.remove(i);
				i=i-1;
			}
       }
       function toLeftAll(leftObj,rightObj){
             for(var i=0;i<leftObj.options.length;i++){
				var e = leftObj.options[i];
				
				//是否存在右边框
			    var len = 0 ;
			    try{
			       len = rightObj.options.length ;
			    }catch(e){}
			   
			    var bool = false ;
			    for(var j=0 ;j<len;j++){
			       var temp = rightObj.options[j] ;
			       if(e.text==temp.text&&e.value==temp.value){
			          //存在相关数据则返回
			          bool = true ;
			       }
			    }
				leftObj.remove(i);
				i=i-1;
				if(bool){
				    continue ;
				}
				
				rightObj.options.add(new Option(e.text, e.value));
			}
           //toRightAll(rightObj,leftObj) ;
       }
       
       </script>     
		
	</head>
	<body scroll="no" onLoad="loadData()">
		<table width="100%" height="100%"  cellpadding="1" cellspacing="1">
			<tr>
				<td><br>
				&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/plugins/theme/skin/images/frame/menusearch.gif">商品品牌										
				<input name="selectname" type="text" id="selectname" size="15" onKeyDown="javascript:onEnter(event)" >
                <input name="search" type="button" id="search" onClick="loadDataByName()" value="查询" class="s"></td>
			</tr>
				<tr>
<td>

								<fieldset>
									<legend>选择</legend>
									<table width="100%" cellpadding="5" cellspacing="0">
										<tr>
											<td width="45%" class="fim_l">
												<table width="*" cellpadding="0" cellspacing="0" class="">
													<tr>
														<td>
														</td>
														<td>
															&nbsp;可选择商品品牌</td>
													</tr>
												</table>
											</td>
											<td width="2%">
											</td>
											<td width="45%">
												<table width="*" cellpadding="0" cellspacing="0">
													<tr>
														<td>
														</td>
														<td>
															&nbsp;已选择商品品牌</td>
													</tr>
												</table>
											</td>
											
										</tr>
										<tr>
											<td valign="top" rowspan="2">
											   <table width="100%" height="100%">
											       <tr>
											        <td  width="100%" colspan="2" height="*">
											        <select id="Select1" multiple ondblclick="toRight(Select1,Select2)" name="Select1" 
													      style="width: 100%; height:220px">														
												      </select>
											        </td>
											      </tr>
											   </table>
											    
											</td>
											<td class="" rowspan="2">
												<input type="button" value=">>" title="全部添加"  onClick="toRightAll(Select1,Select2)" id="Button3" name="Button1"
													 class="s"><br>
												<input type="button" value=">" title="添加" onClick="toRight(Select1,Select2)" id="Button4" name="Button2"  class="s"><br>
												<input type="button" value="<" title="删除" onClick="toLeft(Select2,Select1)" id="Button11" name="Button3"  class="s"><br>
												<input type="button" value="<<" title="全部删除" onClick="toLeftAll(Select2,Select1)"  id="Button12" name="Button4"
													 class="s"><br>
											</td>
											<td class="fim_m">
												<select id="Select2" multiple ondblclick="toLeft(Select2,Select1)" class="com_textarea"
													style="width: 100%; height: 220px">
												</select>
											</td>
											
										</tr>
										
									</table>
								</fieldset>
							</td>
			</tr>
			<tr>
										<td align="center">
											<input type="button" class="s" onClick="saveBrand(this)" value="确定" id="Button1"
												name="Button1"> <input type="button" class="s" onclick="window.close();" value="取消" id="Button2"
												name="Button2"><br><br> 
										</td>
			</tr>
		</table>
	</body>
</html>
