function checkRandNum (){
	var inputVal = document.getElementById('randNum');
	//如果找不到对象则表示不用验证
	if(inputVal == null){
		return true;
	}
	
	if(inputVal.value==null||inputVal.value==''){
		 	alert(document.getElementById("cannonull").value) ; 
		 	inputVal.focus() ;
		    return false; //验证失败
		 }
	 if(inputVal.value.length!=4){
	 	alert(document.getElementById("charactors4").value) ;
	 	inputVal.focus() ;
		return false; //验证失败
	 }

	 return true;
}

function validateRandNum (path){
	var checkResult = checkRandNum();
	if(!checkResult){
		 //验证失败，返回
		return checkResult;
	}
	var randNum = document.getElementById("randNum").value;
	var result = true;
	dwr.engine.setAsync(false);
	 CommonService.validateRandImg(randNum,function(retData){
		 if(!retData){
			 	result = retData;
			 	alert(document.getElementById("errorImage").value) ;
			 	document.getElementById("randNum").value="";
			 	document.getElementById('randNum').focus() ;
			 	changeRandImg(path);
		 }
	 });
	 dwr.engine.setAsync(true);
	 return result;
}

    function changeRandImg(path){
        var obj = document.getElementById("randImage") ;
        obj.src = path + "/captcha.svl?d=" + new Date();
     }
     
    function changeRandImg_bak(path){
       var obj = document.getElementById("randImage") ;
         CommonService.changeRandImg(function(retData){
	       if(retData != null ){
	            var imagePath = "/servlet/ImageServlet?rand="+retData["randStr"];
	            if(path !=null && path != "undefined" && path != ''){
	            	imagePath = path + imagePath;
	            }
 				obj.src =imagePath;
 				document.getElementById("rand").value = retData["rand"];
 				document.getElementById("randNum").value = "";
	       }
	    }) ;
	  
    }
    
    