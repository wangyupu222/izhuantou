$(window).load(function() {
	$.ajax({
        type: "get",//请求方式
        url: "/portal/index/DDTou",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        	console.log(result.length);
        if(result.length>=2){
        	var DDyb01=$(".DDLen02 .share_right_box1 .DDyb");
    		var DDdjs01=$(".DDLen02 .share_right_box1 .DDdjs");
    		var DDyb02=$(".DDLen02 .share_right_box2 .DDyb");
    		var DDdjs02=$(".DDLen02 .share_right_box2 .DDdjs");
        	if(result[0].szds==1 && result[0].dsTime>=0){	
        		DDyb01.remove();
        		DDdjs01.css("display","block");
        		DDdjs01.find("#Djs_DD .bigtit").text(result[0].biddingName);
        		DDdjs01.find("#Djs_DD .row1").find("em").eq(0).text(result[0].rate);
        		DDdjs01.find("#Djs_DD .row1").find("em").eq(1).text(result[0].productTerm);
        		DDdjs01.find("#Djs_DD .row1").find("#Countdown").text(result[0].sj);
        		
        		DDdjs01.find("#Yb_DD .bigtit").text(result[0].newBiddingAmount);
        		DDdjs01.find("#Yb_DD .row1").find("em").eq(0).text(result[0].rate);
        		DDdjs01.find("#Yb_DD .row1").find("em").eq(1).text(result[0].productTerm);
        		DDdjs01.find("#Yb_DD .row1").find("em").eq(2).text(result[0].startBidAmount);
        		DDdjs01.find("#Yb_DD .row1").find("em").eq(3).text(result[0].sy);
        		DDdjs01.find("#Yb_DD .jindupercent").find("em").text(result[0].process);
        		DDdjs01.find("#Yb_DD .lijitza").find("a").attr("href","/portal/detial/Diantzdetails?OID="+result[0].oid);
      	          var tidDD1 = setInterval(function () {
      	          var oTimebox = $(".DDWarp.DDLen02 .share_right_box1 #Djs_DD #Countdown");
      	          var syTime = oTimebox.text();
      	          var totalSec = getTotalSecond(syTime) - 1;
      	          //alert(syTime);
      	          if (totalSec >= 0) {
      	              oTimebox.text(getNewSyTime(totalSec));
      	          } else {
      	              clearInterval(tidDD1);
      	              // window.location.reload();
      	              $(".DDWarp.DDLen02 .share_right_box1 #Djs_DD").css("display","none").siblings(".DDWarp.DDLen02 .share_right_box1 #Yb_DD").css("display","block");
      	              $(".DDWarp.DDLen02 .share_right_box1 #Yb_DD .lijitza a").css("background","#55b0fd");
      	          }
      	      	}, 1000);
        	}else{
        		DDdjs01.remove();
        		DDyb01.css("display","block");
        		DDyb01.find(".bigtit").text(result[0].newBiddingAmount);
        		DDyb01.find(".row1").find("em").eq(0).text(result[0].rate);
        		DDyb01.find(".row1").find("em").eq(1).text(result[0].productTerm);
        		DDyb01.find(".row1").find("em").eq(2).text(result[0].startBidAmount);
        		DDyb01.find(".row1").find("em").eq(3).text(result[0].sy);
        		DDyb01.find(".jindupercent").find("em").text(result[0].process);
        		var str="";
        		if(result[0].productStatus==10){
        			str='<a style="background-color:#ababab;" href="/portal/detial/Diantzdetails?OID='+result[0].oid+'">立即出借</a>';
        		}else if(result[0].productStatus==2 || result[0].productStatus==3 || result[0].productStatus==5){
        			str='<a style="background-color:#ababab;" href="/portal/detial/Diantzdetails?OID='+result[0].oid+'">已满标</a>';			
        		}else{
        			str='<a href="/portal/detial/Diantzdetails?OID='+result[0].oid+'">立即出借</a>';
        		}
        		DDyb01.find(".lijitza").append(str);
        	}
        	
        	if(result[1].szds==1 && result[1].dsTime>=0){	
        		DDyb02.remove();
        		DDdjs02.css("display","block");
        		DDdjs02.find("#Djs_DD .bigtit").text(result[1].biddingName);
        		DDdjs02.find("#Djs_DD .row1").find("em").eq(0).text(result[1].rate);
        		DDdjs02.find("#Djs_DD .row1").find("em").eq(1).text(result[1].productTerm);
        		DDdjs02.find("#Djs_DD .row1").find("#Countdown").text(result[1].sj);
        		
        		DDdjs02.find("#Yb_DD .bigtit").text(result[1].newBiddingAmount);
        		DDdjs02.find("#Yb_DD .row1").find("em").eq(0).text(result[1].rate);
        		DDdjs02.find("#Yb_DD .row1").find("em").eq(1).text(result[1].productTerm);
        		DDdjs02.find("#Yb_DD .row1").find("em").eq(2).text(result[1].startBidAmount);
        		DDdjs02.find("#Yb_DD .row1").find("em").eq(3).text(result[1].sy);
        		DDdjs02.find("#Yb_DD .jindupercent").find("em").text(result[1].process);
        		DDdjs02.find("#Yb_DD .lijitza").find("a").attr("href","/portal/detial/Diantzdetails?OID="+result[1].oid)
      	          var tidDD2 = setInterval(function () {
      	          var oTimebox = $(".DDWarp.DDLen02 .share_right_box2 #Djs_DD #Countdown");
      	          var syTime = oTimebox.text();
      	          var totalSec = getTotalSecond(syTime) - 1;
      	          //alert(syTime);
      	          if (totalSec >= 0) {
      	              oTimebox.text(getNewSyTime(totalSec));
      	          } else {
      	              clearInterval(tidDD2);
      	              // window.location.reload();
      	              $(".DDWarp.DDLen02 .share_right_box2 #Djs_DD").css("display","none").siblings(".DDWarp.DDLen02 .share_right_box2 #Yb_DD").css("display","block");
      	              $(".DDWarp.DDLen02 .share_right_box2 #Yb_DD .lijitza a").css("background","#55b0fd");
      	          }
      	      	}, 1000);
        	}else{
        		DDdjs02.remove();
        		DDyb02.css("display","block");
        		DDyb02.find(".bigtit").text(result[1].newBiddingAmount);
        		DDyb02.find(".row1").find("em").eq(0).text(result[1].rate);
        		DDyb02.find(".row1").find("em").eq(1).text(result[1].productTerm);
        		DDyb02.find(".row1").find("em").eq(2).text(result[1].startBidAmount);
        		DDyb02.find(".row1").find("em").eq(3).text(result[1].sy);
        		DDyb02.find(".jindupercent").find("em").text(result[1].process);
        		var str="";
        		if(result[1].productStatus==10){
        			str='<a style="background-color:#ababab;" href="/portal/detial/Diantzdetails?OID='+result[1].oid+'">立即出借</a>';
        		}else if(result[1].productStatus==2 || result[1].productStatus==3 || result[1].productStatus==5){
        			str='<a style="background-color:#ababab;" href="/portal/detial/Diantzdetails?OID='+result[1].oid+'">已满标</a>';			
        		}else{
        			str='<a href="/portal/detial/Diantzdetails?OID='+result[1].oid+'">立即出借</a>';
        		}
        		DDyb02.find(".lijitza").append(str);
        	}
        	
        	
        	$(".DDLen02").css("display","block").siblings(".DDLen01").remove();
        }else if(result.length==1){
        	var DDybOne01=$(".DDLen01 .DDyb");
    		var DDdjsOne01=$(".DDLen01 .DDdjs");
    		if(result[0].szds==1 && result[0].dsTime>=0){	
    			DDybOne01.remove();
    			DDdjsOne01.css("display","block");
    			DDdjsOne01.find("#Djs_DD .bigtit").text(result[0].biddingName);
        		DDdjsOne01.find("#Djs_DD .row1").find("em").eq(0).text(result[0].rate);
        		DDdjsOne01.find("#Djs_DD .row1").find("em").eq(1).text(result[0].productTerm);
        		DDdjsOne01.find("#Djs_DD .row1").find("#Countdown").text(result[0].sj);
        		
        		DDdjsOne01.find("#Yb_DD .bigtit").text(result[0].newBiddingAmount);
        		DDdjsOne01.find("#Yb_DD .row1").find("em").eq(0).text(result[0].rate);
        		DDdjsOne01.find("#Yb_DD .row1").find("em").eq(1).text(result[0].productTerm);
        		DDdjsOne01.find("#Yb_DD .row1").find("em").eq(2).text(result[0].startBidAmount);
        		DDdjsOne01.find("#Yb_DD .row1").find("em").eq(3).text(result[0].sy);
        		DDdjsOne01.find("#Yb_DD .jindupercent").find("em").text(result[0].process);
        		DDdjsOne01.find("#Yb_DD .lijitza").find("a").attr("href","/portal/detial/Diantzdetails?OID="+result[0].oid);
        		var tid = setInterval(function () {
                    var oTimebox = $(".DDWarp.DDdjs #Djs_DD #Countdown");
                    var syTime = oTimebox.text();
                    var totalSec = getTotalSecond(syTime) - 1;
                    //alert(syTime);
                    if (totalSec >= 0) {
                        oTimebox.text(getNewSyTime(totalSec));
                    } else {
                        clearInterval(tid);
                        // window.location.reload();
                        $(".DDWarp.DDdjs #Djs_DD").css("display","none").siblings(".DDWarp.DDdjs #Yb_DD").css("display","block");
                        $(".DDWarp.DDdjs #Yb_DD .lijitza a").css("background","#55b0fd");
                    }
                	}, 1000);
        	}else{
        		DDdjsOne01.remove();
        		DDybOne01.css("display","block");
        		DDybOne01.find(".bigtit").text(result[0].newBiddingAmount);
        		DDybOne01.find(".row1").find("em").eq(0).text(result[0].rate);
        		DDybOne01.find(".row1").find("em").eq(1).text(result[0].productTerm);
        		DDybOne01.find(".row1").find("em").eq(2).text(result[0].startBidAmount);
        		DDybOne01.find(".row1").find("em").eq(3).text(result[0].sy);
        		DDybOne01.find(".jindupercent").find("em").text(result[0].process);
        		var str="";
        		if(result[0].productStatus==10){
        			str='<a style="background-color:#ababab;" href="/portal/detial/Diantzdetails?OID='+result[0].oid+'">立即出借</a>';
        		}else if(result[0].productStatus==2 || result[0].productStatus==3 || result[0].productStatus==5){
        			str='<a style="background-color:#ababab;" href="/portal/detial/Diantzdetails?OID='+result[0].oid+'">已满标</a>';			
        		}else{
        			str='<a href="/portal/detial/Diantzdetails?OID='+result[0].oid+'">立即出借</a>';
        		}
        		DDybOne01.find(".lijitza").append(str);
        	}
    		$(".DDLen01").css("display","block").siblings(".DDLen02").remove();
        }else if(result.length<1){
        	$("#DDfinedatacon").remove();
        	
        }
        },
		error:function(result){
			console.log("发生错误 ");
    	}
    });
	
	/* 环环投  */
	$.ajax({
        type: "get",//请求方式
        url: "/portal/index/HHTou",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){
        var HHdjs01=$(".finedataHH01 .HHdjs");
        var HHyb01=$(".finedataHH01 .HHyb");
        var HHdjs02=$(".finedataHH02 .HHdjs");
        var HHyb02=$(".finedataHH02 .HHyb");
        var HHdjs03=$(".finedataHH03 .HHdjs");
        var HHyb03=$(".finedataHH03 .HHyb");
        var HHdjs04=$(".finedataHH04 .HHdjs");
        var HHyb04=$(".finedataHH04 .HHyb");
        var strImg='<img src="/image/Addyxjh.png">';
        if(result.length>=1){
        	if(result[0].szds==1 && result[0].dsTime>0){
        		HHyb01.remove();
        		HHdjs01.css("display","block");
        		if(result[0].jxother!=0){
        			HHdjs01.find("#Djs_wrap .borderimg").append(strImg);        			
        			HHdjs01.find("#Djs_wrap .hhdata.row01 span").append('<span class="AddInterest">+'+result[0].jxother+'%</span>');
        			HHdjs01.find("#Yb_wrap .borderimg").append(strImg);        			
        			HHdjs01.find("#Yb_wrap .hhdata.row01 span").append('<span class="AddInterest">+'+result[0].jxother+'%</span>');
        		}
        			HHdjs01.find("#Djs_wrap .finetitle").text(result[0].packageName);
        			HHdjs01.find("#Djs_wrap .hhdata.row01 em").text(result[0].rate);
        			HHdjs01.find("#Djs_wrap .HHbtn em.ktjeHH").text(result[0].sykt);
        			HHdjs01.find("#Djs_wrap .HHbtn em.productStatusHH").text(result[0].sykt);
        			HHdjs01.find("#Djs_wrap .Djs_Date").text(result[0].sj);
        			
        			HHdjs01.find("#Yb_wrap .finetitle").text(result[0].newPackageName);
        			HHdjs01.find("#Yb_wrap .hhdata.row01 em").text(result[0].rate);
        			HHdjs01.find("#Yb_wrap span.cjqx").text(result[0].productTerm+"个月");
        			HHdjs01.find("#Yb_wrap span.hkfs").text(result[0].repaymentType);
        			HHdjs01.find("#Yb_wrap .HHbtn em.ktjeHH").text(result[0].sykt);
        			HHdjs01.find("#Yb_wrap .HHbtn em.productStatusHH").text(result[0].sykt);
        			var str="";
        			if(result[0].productStatus==10){
        				if(result[0].sykt>0){
        					str='<a class="hhbtnBox tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[0].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[0].oid +'">预约出借</a>';
        				}
        			}else{
        				if(result[0].sykt>0){
        					str='<a class="hhbtnBox " href="/portal/detial/HHtzdetails?OID='+ result[0].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue " href="/portal/detial/HHtzdetails?OID='+ result[0].oid +'">预约出借</a>';
        				}
        			}       		          
        		          HHdjs01.find("#Yb_wrap .row3").append(str);
        		          var tidHH1 = setInterval(function () {
        		          var oTimebox = $(".finedataHH01 #Countdown");
        		          var syTime = oTimebox.text();
        		          var totalSec = getTotalSecond(syTime) - 1;
        		          //alert(totalSec);
        		          if (totalSec >= 0) {
        		              oTimebox.text(getNewSyTime(totalSec));
        		          } else {
        		              clearInterval(tidHH1);
        		              // window.location.reload();
        		              $(".finedataHH01 #Djs_wrap").css("display","none").siblings(".finedataHH01 #Yb_wrap").css("display","block");
        		              $(".finedataHH01 #Yb_wrap .cell.row3 a").removeClass("tingbiao");
        		          }
        		      	}, 1000);
        	}else{
        		HHdjs01.remove();
        		HHyb01.css("display","block");
        		if(result[0].jxother!=0){
        			HHyb01.find(".borderimg").append(strImg);        			
        			HHyb01.find(".hhdata.row01 span").append('<span class="AddInterest">+'+result[0].jxother+'%</span>');
        		}	
        			HHyb01.find(".finetitle").text(result[0].newPackageName);
        			HHyb01.find(".hhdata.row01 em").text(result[0].rate);
        			HHyb01.find(".hhdata_btm span.cjqx").text(result[0].productTerm+"个月");
        			HHyb01.find(".hhdata_btm span.hkfs").text(result[0].repaymentType);
        			HHyb01.find(".HHbtn em.ktjeHH").text(result[0].sykt);
        			HHyb01.find(".HHbtn em.productStatusHH").text(result[0].sykt);
        			var str="";
        			if(result[0].productStatus==10){
        				if(result[0].sykt>0){
        					str='<a class="hhbtnBox tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[0].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[0].oid +'">预约出借</a>';
        				}
        			}else{
        				if(result[0].sykt>0){
        					str='<a class="hhbtnBox " href="/portal/detial/HHtzdetails?OID='+ result[0].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue " href="/portal/detial/HHtzdetails?OID='+ result[0].oid +'">预约出借</a>';
        				}
        			}
        			HHyb01.find(".cell.row3").append(str);
        	}
        	
        }else{
    		HHdjs01.remove();
    		HHyb01.remove();
        	$(".finedataHH01 .recommendBox02").removeClass("hide");
        }
        if(result.length>=2){
        	if(result[1].szds==1 && result[1].dsTime>0){
        		HHyb02.remove();
        		HHdjs02.css("display","block");
        		if(result[1].jxother!=0){
        			HHdjs02.find("#Djs_wrap .borderimg").append(strImg);        			
        			HHdjs02.find("#Djs_wrap .hhdata.row01 span").append('<span class="AddInterest">+'+result[1].jxother+'%</span>');
        			HHdjs02.find("#Yb_wrap .borderimg").append(strImg);        			
        			HHdjs02.find("#Yb_wrap .hhdata.row01 span").append('<span class="AddInterest">+'+result[1].jxother+'%</span>');
        		}
        			HHdjs02.find("#Djs_wrap .finetitle").text(result[1].packageName);
        			HHdjs02.find("#Djs_wrap .hhdata.row01 em").text(result[1].rate);
        			HHdjs02.find("#Djs_wrap .HHbtn em.ktjeHH").text(result[1].sykt);
        			HHdjs02.find("#Djs_wrap .HHbtn em.productStatusHH").text(result[1].sykt);
        			HHdjs02.find("#Djs_wrap .Djs_Date").text(result[1].sj);
        			
        			HHdjs02.find("#Yb_wrap .finetitle").text(result[1].newPackageName);
        			HHdjs02.find("#Yb_wrap .hhdata.row01 em").text(result[1].rate);
        			HHdjs02.find("#Yb_wrap span.cjqx").text(result[1].productTerm+"个月");
        			HHdjs02.find("#Yb_wrap span.hkfs").text(result[1].repaymentType);
        			HHdjs02.find("#Yb_wrap .HHbtn em.ktjeHH").text(result[1].sykt);
        			HHdjs02.find("#Yb_wrap .HHbtn em.productStatusHH").text(result[1].sykt);
        			var str="";
        			if(result[1].productStatus==10){
        				if(result[1].sykt>0){
        					str='<a class="hhbtnBox tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[1].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[1].oid +'">预约出借</a>';
        				}
        			}else{
        				if(result[1].sykt>0){
        					str='<a class="hhbtnBox " href="/portal/detial/HHtzdetails?OID='+ result[1].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue " href="/portal/detial/HHtzdetails?OID='+ result[1].oid +'">预约出借</a>';
        				}
        			}
        			HHdjs02.find("#Yb_wrap .row3").append(str);
        			var tidHH2 = setInterval(function () {
  		              var oTimebox = $(".finedataHH02 #Countdown");
  		              var syTime = oTimebox.text();
  		              var totalSec = getTotalSecond(syTime) - 1;
  		              //alert(totalSec);
  		              if (totalSec >= 0) {
  		                  oTimebox.text(getNewSyTime(totalSec));
  		              } else {
  		                  clearInterval(tidHH2);
  		                  // window.location.reload();
  		                  $(".finedataHH02 #Djs_wrap").css("display","none").siblings(".finedataHH02 #Yb_wrap").css("display","block");
  		                  $(".finedataHH02 #Yb_wrap .cell.row3 a").removeClass("tingbiao");
  		              }
  		          	}, 1000);
        	}else{
        		HHdjs02.remove();
        		HHyb02.css("display","block");
        		if(result[1].jxother!=0){
        			HHyb02.find(".borderimg").append(strImg);        			
        			HHyb02.find(".hhdata.row01 span").append('<span class="AddInterest">+'+result[1].jxother+'%</span>');
        		}	
        			HHyb02.find(".finetitle").text(result[1].newPackageName);
        			HHyb02.find(".hhdata.row01 em").text(result[1].rate);
        			HHyb02.find(".hhdata_btm span.cjqx").text(result[1].productTerm+"个月");
        			HHyb02.find(".hhdata_btm span.hkfs").text(result[1].repaymentType);
        			HHyb02.find(".HHbtn em.ktjeHH").text(result[1].sykt);
        			HHyb02.find(".HHbtn em.productStatusHH").text(result[1].sykt);
        			var str="";
        			if(result[1].productStatus==10){
        				if(result[1].sykt>0){
        					str='<a class="hhbtnBox tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[1].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[1].oid +'">预约出借</a>';
        				}
        			}else{
        				if(result[1].sykt>0){
        					str='<a class="hhbtnBox " href="/portal/detial/HHtzdetails?OID='+ result[1].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue " href="/portal/detial/HHtzdetails?OID='+ result[1].oid +'">预约出借</a>';
        				}
        			}
        			HHyb02.find(".cell.row3").append(str);
        	}
        	
        }else{
    		HHdjs02.remove();
    		HHyb02.remove();
        	$(".finedataHH02 .recommendBox02").removeClass("hide");
        }
        if(result.length>=3){
        	if(result[2].szds==1 && result[2].dsTime>0){
        		HHyb04.remove();
        		HHdjs03.css("display","block");
        		if(result[2].jxother!=0){
        			HHdjs03.find("#Djs_wrap .borderimg").append(strImg);        			
        			HHdjs03.find("#Djs_wrap .hhdata.row01 span").append('<span class="AddInterest">+'+result[2].jxother+'%</span>');
        			HHdjs03.find("#Yb_wrap .borderimg").append(strImg);        			
        			HHdjs03.find("#Yb_wrap .hhdata.row01 span").append('<span class="AddInterest">+'+result[2].jxother+'%</span>');
        		}
        			HHdjs03.find("#Djs_wrap .finetitle").text(result[2].packageName);
        			HHdjs03.find("#Djs_wrap .hhdata.row01 em").text(result[2].rate);
        			HHdjs03.find("#Djs_wrap .HHbtn em.ktjeHH").text(result[2].sykt);
        			HHdjs03.find("#Djs_wrap .HHbtn em.productStatusHH").text(result[2].sykt);
        			HHdjs03.find("#Djs_wrap .Djs_Date").text(result[2].sj);
        			
        			HHdjs03.find("#Yb_wrap .finetitle").text(result[2].newPackageName);
        			HHdjs03.find("#Yb_wrap .hhdata.row01 em").text(result[2].rate);
        			HHdjs03.find("#Yb_wrap span.cjqx").text(result[2].productTerm+"个月");
        			HHdjs03.find("#Yb_wrap span.hkfs").text(result[2].repaymentType);
        			HHdjs03.find("#Yb_wrap .HHbtn em.ktjeHH").text(result[2].sykt);
        			HHdjs03.find("#Yb_wrap .HHbtn em.productStatusHH").text(result[2].sykt);
        			var str="";
        			if(result[2].productStatus==10){
        				if(result[2].sykt>0){
        					str='<a class="hhbtnBox tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[2].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[2].oid +'">预约出借</a>';
        				}
        			}else{
        				if(result[2].sykt>0){
        					str='<a class="hhbtnBox " href="/portal/detial/HHtzdetails?OID='+ result[2].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue " href="/portal/detial/HHtzdetails?OID='+ result[2].oid +'">预约出借</a>';
        				}
        			}
        			HHdjs03.find("#Yb_wrap .row3").append(str);       		          
        		          var tidHH3 = setInterval(function () {
        		              var oTimebox = $(".finedataHH03 #Countdown");
        		              var syTime = oTimebox.text();
        		              var totalSec = getTotalSecond(syTime) - 1;
        		              //alert(totalSec);
        		              if (totalSec >= 0) {
        		                  oTimebox.text(getNewSyTime(totalSec));
        		              } else {
        		                  clearInterval(tidHH3);
        		                  // window.location.reload();
        		                  $(".finedataHH03 #Djs_wrap").css("display","none").siblings(".finedataHH03 #Yb_wrap").css("display","block");
        		                  $(".finedataHH03 #Yb_wrap .cell.row3 a").removeClass("tingbiao");
        		              }
        		          	}, 1000);
        	}else{
        		HHdjs03.remove();
        		HHyb03.css("display","block");
        		if(result[2].jxother!=0){
        			HHyb03.find(".borderimg").append(strImg);        			
        			HHyb03.find(".hhdata.row01 span").append('<span class="AddInterest">+'+result[2].jxother+'%</span>');
        		}	
        			HHyb03.find(".finetitle").text(result[2].newPackageName);
        			HHyb03.find(".hhdata.row01 em").text(result[2].rate);
        			HHyb03.find(".hhdata_btm span.cjqx").text(result[2].productTerm+"个月");
        			HHyb03.find(".hhdata_btm span.hkfs").text(result[2].repaymentType);
        			HHyb03.find(".HHbtn em.ktjeHH").text(result[2].sykt);
        			HHyb03.find(".HHbtn em.productStatusHH").text(result[2].sykt);
        			var str="";
        			if(result[2].productStatus==10){
        				if(result[2].sykt>0){
        					str='<a class="hhbtnBox tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[2].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[2].oid +'">预约出借</a>';
        				}
        			}else{
        				if(result[2].sykt>0){
        					str='<a class="hhbtnBox " href="/portal/detial/HHtzdetails?OID='+ result[2].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue " href="/portal/detial/HHtzdetails?OID='+ result[2].oid +'">预约出借</a>';
        				}
        			}
        			HHyb03.find(".cell.row3").append(str);
        	}
        	
        }else{
        		HHdjs03.remove();
        		HHyb03.remove();
            	$(".finedataHH03 .recommendBox02").removeClass("hide");
        }
        if(result.length>=4){
        	if(result[3].szds==1 && result[3].dsTime>0){
        		HHyb04.remove();
        		HHdjs04.css("display","block");
        		if(result[3].jxother!=0){
        			HHdjs04.find("#Djs_wrap .borderimg").append(strImg);        			
        			HHdjs04.find("#Djs_wrap .hhdata.row01 span").append('<span class="AddInterest">+'+result[3].jxother+'%</span>');
        			HHdjs04.find("#Yb_wrap .borderimg").append(strImg);        			
        			HHdjs04.find("#Yb_wrap .hhdata.row01 span").append('<span class="AddInterest">+'+result[3].jxother+'%</span>');
        		}
        			HHdjs04.find("#Djs_wrap .finetitle").text(result[3].packageName);
        			HHdjs04.find("#Djs_wrap .hhdata.row01 em").text(result[3].rate);
        			HHdjs04.find("#Djs_wrap .HHbtn em.ktjeHH").text(result[3].sykt);
        			HHdjs04.find("#Djs_wrap .HHbtn em.productStatusHH").text(result[3].sykt);
        			HHdjs04.find("#Djs_wrap .Djs_Date").text(result[3].sj);
        			
        			HHdjs04.find("#Yb_wrap .finetitle").text(result[3].newPackageName);
        			HHdjs04.find("#Yb_wrap .hhdata.row01 em").text(result[3].rate);
        			HHdjs04.find("#Yb_wrap span.cjqx").text(result[3].productTerm+"个月");
        			HHdjs04.find("#Yb_wrap span.hkfs").text(result[3].repaymentType);
        			HHdjs04.find("#Yb_wrap .HHbtn em.ktjeHH").text(result[3].sykt);
        			HHdjs04.find("#Yb_wrap .HHbtn em.productStatusHH").text(result[3].sykt);
        			var str="";
        			if(result[3].productStatus==10){
        				if(result[3].sykt>0){
        					str='<a class="hhbtnBox tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[3].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[3].oid +'">预约出借</a>';
        				}
        			}else{
        				if(result[3].sykt>0){
        					str='<a class="hhbtnBox " href="/portal/detial/HHtzdetails?OID='+ result[3].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue " href="/portal/detial/HHtzdetails?OID='+ result[3].oid +'">预约出借</a>';
        				}
        			}
        			HHdjs04.find("#Yb_wrap .row3").append(str);       		          
        			var tidHH4 = setInterval(function () {
        		          var oTimebox = $(".finedataHH04 #Countdown");
        		          var syTime = oTimebox.text();
        		          var totalSec = getTotalSecond(syTime) - 1;
        		          //alert(totalSec);
        		          if (totalSec >= 0) {
        		              oTimebox.text(getNewSyTime(totalSec));
        		          } else {
        		              clearInterval(tidHH4);
        		              // window.location.reload();
        		              $(".finedataHH04 #Djs_wrap").css("display","none").siblings(".finedataHH04 #Yb_wrap").css("display","block");
        		              $(".finedataHH04 #Yb_wrap .cell.row3 a").removeClass("tingbiao");
        		          }
        		      	}, 1000);
        	}else{
        		HHdjs04.remove();
        		HHyb04.css("display","block");
        		if(result[3].jxother!=0){
        			HHyb04.find(".borderimg").append(strImg);        			
        			HHyb04.find(".hhdata.row01 span").append('<span class="AddInterest">+'+result[3].jxother+'%</span>');
        		}	
        			HHyb04.find(".finetitle").text(result[3].newPackageName);
        			HHyb04.find(".hhdata.row01 em").text(result[3].rate);
        			HHyb04.find(".hhdata_btm span.cjqx").text(result[3].productTerm+"个月");
        			HHyb04.find(".hhdata_btm span.hkfs").text(result[3].repaymentType);
        			HHyb04.find(".HHbtn em.ktjeHH").text(result[3].sykt);
        			HHyb03.find(".HHbtn em.productStatusHH").text(result[3].sykt);
        			var str="";
        			if(result[3].productStatus==10){
        				if(result[3].sykt>0){
        					str='<a class="hhbtnBox tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[3].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue tingbiao" href="/portal/detial/HHtzdetails?OID='+ result[3].oid +'">预约出借</a>';
        				}
        			}else{
        				if(result[3].sykt>0){
        					str='<a class="hhbtnBox " href="/portal/detial/HHtzdetails?OID='+ result[3].oid +'">立即出借</a>';
        				}else{
        					str='<a class="hhbtnBox yuyue " href="/portal/detial/HHtzdetails?OID='+ result[3].oid +'">预约出借</a>';
        				}
        			}
        			HHyb04.find(".cell.row3").append(str);
        	}
        	
        }else{
        	HHdjs04.remove();
    		HHyb04.remove();
        	$(".finedataHH04 .recommendBox02").removeClass("hide");
        }
        },
		error:function(result){
			console.log("发生错误 ");
    	}
    });
});