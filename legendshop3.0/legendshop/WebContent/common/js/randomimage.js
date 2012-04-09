function checkRandNum (){
	var inputVal = document.getElementById('randNum');
	if(inputVal.value==null||inputVal.value==''){
		 	alert(document.getElementById("cannonull").value) ; 
		 	inputVal.focus() ;
		    return false;
		 }
	 if(inputVal.value.length!=6){
	 	alert(document.getElementById("charactors4").value) ;
	 	inputVal.focus() ;
		return false;
	 }

	 return true;
}

function validateRandNum (path){
	var checkResult = checkRandNum();
	if(!checkResult){
		return checkResult;
	}
	var randNum = document.getElementById("randNum").value;
	var result = true;
	DWREngine.setAsync(false);
	 CommonService.validateRandImg(randNum,function(retData){
		 if(!retData){
			 	result = retData;
			 	alert(document.getElementById("errorImage").value) ;
			 	document.getElementById("randNum").value="";
			 	document.getElementById('randNum').focus() ;
			 	changeRandImg(path);
		 }
	 });
	 DWREngine.setAsync(true);
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
    
    