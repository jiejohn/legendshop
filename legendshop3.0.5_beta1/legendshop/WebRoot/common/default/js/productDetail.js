function isNumber(oNum)
   {
	  if(!oNum) return false;
	  var strP=/^\d+(\.\d+)?$/;
	  if(!strP.test(oNum)) return false;
	  try{
	  if(parseFloat(oNum)!=oNum) return false;
	  }
	  catch(ex)
	  {
	   return false;
	  }
	  return true;
   }
   
   function contains(entry, value){
      for(var i = 0; i <entry.length; i++){
      if(entry[i] == value){
      	return false;
       }
      }
   	return true;
   }
   
	function RemoveArray(array,attachId)
	{
	    for(var i=0,n=0;i<array.length;i++)
	    {
	        if(array[i]!=attachId)
	        {
	            array[n++]=array[i];
	        }
	    }
	    array.length -= 1;
	}