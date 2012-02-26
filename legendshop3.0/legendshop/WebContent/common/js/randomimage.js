function checkRandNum (){
		var inputVal = document.getElementById('randNum');
		var randVal = document.getElementById('rand').value;
	if(inputVal.value==null||inputVal.value==''){
		 	alert(document.getElementById("cannonull").value) ; 
		 	inputVal.focus() ;
		    return false;
		 }
	 if(inputVal.value.length!=4){
	 	alert(document.getElementById("charactors4").value) ;
	 	inputVal.focus() ;
		return false;
	 }
	 if(inputVal.value!= randVal){
	 	alert(document.getElementById("errorImage").value) ;
	 	inputVal.focus() ;
		return false;
	 }
	 return true;
}

    function changeRandImg(path){
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
    

    
    