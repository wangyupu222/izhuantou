    



    var isRun=false;
	function validataForm(oClass){
		if(isRun ==true){ 
		    //已经
		    return false;
			//alert("不能重复提交！");
		}else{
			//未提交
			isRun ==true;
			document.getElementsByClassName(oClass)[0].disabled=true;
			  // alert("正常提交中！");
			//setTimeout("text()",5000);
			return true;
			 
		} 
		
	}
	
	function validataForm_id(oId){
		if(isRun ==true){ 
		    //已经
		    return false;
			//alert("不能重复提交！");
		}else{
			//未提交
			isRun ==true;
			document.getElementById(oId).disabled=true;
			//alert("正常提交中！");
			return true;
			 
		} 
		
	}