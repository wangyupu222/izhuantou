<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-邀请好友</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>

<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<script type="text/javascript" src="https://img3.job1001.com/js/ZeroClipboard/jquery.zclip.min.js"></script>
<script src="/js/paging.js"></script>
<style >
.mytzcon{min-height:45px;}
.invitationtop{
    position: relative;
}
#zclip-ZeroClipboardMovie_1{
	left: 709px !important;	
    opacity: 0;
}
/* #zclip-ZeroClipboardMovie_1:hover{
opacity: 1;
background: url(/p2p/web<%=pathUrl %>/images/link_icon_h.png);
} */
.shouru_img a{
    margin: 6px 33px 6px 40px !important;
}
.rowbox05.value td{
    border-bottom: 1px solid #eaeaea;
}
.shouru_img{
position: relative;
}
</style>
</head>
<body>
<% try{ %>
<hoontag:TagAction handleClassName="isLogin" />
<hoontag:TagAction handleClassName="pageInvitefriends" />
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<div class="main minhh">
	<div class="con personcon">
    	<div class="smallnav">
    	<!-- <a >当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a >我的特权</a><span>&gt;</span><a >邀请好友</a> -->
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft invitecon">        
        	<!-- <div class="hbtab">
            	<a class="active" href="Invitefriends.jsp">邀请好友</a>	
            </div> -->
            <div class="my_loantop"><a class="active">邀请好友</a><span class="L1">&nbsp;</span></div>
            
            <div class="invitent hide"><img src="<%=pathUrl %>/images/invient.jpg"></div>
            <div class="invitenttit">邀请方式</div>
            <div id="member_value" style="display:none;"><%=session.getAttribute("memberOID")%></div>
            <div class="invitation">
            	<div class="invitationtop">邀请码：<span class="yqid" id="copy_value"><%=session.getAttribute("userMobile")%></span><!-- <a  class="clickcopy copy_btn">点击可复制粘贴</a> --><!--<a href="Invite_record.jsp">全部邀请记录</a>--></div>
                <div class="shouru">
                	<div class="shouru_img">
                	<div class="bdsharebuttonbox">
    <a href="#" class="bds_weixin"  style="width: 75px;height: 113px;background: url('<%=pathUrl %>/images/wechat.png');background-repeat: no-repeat;background-position: center;" data-cmd="weixin" title="分享到微信"></a>
    <a href="#" class="bds_qzone" style="width: 75px;height: 113px;background: url('<%=pathUrl %>/images/qzone.png');background-repeat: no-repeat;background-position: center;" data-cmd="qzone" title="分享到QQ空间"></a>
    <a href="#" class="bds_tsina" style="width: 75px;height: 113px;background: url('<%=pathUrl %>/images/sina_weibo.png');background-repeat: no-repeat;background-position: center;" data-cmd="tsina" title="分享到新浪微博"></a>
    <a href="#" class="bds_sqq" style="width: 75px;height: 113px;background: url('<%=pathUrl %>/images/qqchat.png');background-repeat: no-repeat;background-position: center;" data-cmd="sqq" title="分享到qq好友"></a>
	<a href="#" class="bds_link" style="width: 75px;height: 113px; display: inline-block;background: url('<%=pathUrl %>/images/link_icon.png');background-repeat: no-repeat;background-position: center;"  title="点击复制链接"></a>
                     </div>
                     <div class="InciteLink hide">https://www.izhuantou.com/p2p/web/Invite_FriendsStart.jsp?ssl=<%=session.getAttribute("memberOID")%></div>
                	</div>
                    <div class="clear"></div>
                </div>
            </div>
             <div class="invitenttit">邀请记录</div>
             <div class="invitation">
            <div class="loanrecord">
            	<table class="mytzcon inv" border="0" cellspacing="0" cellpadding="0">
            	<thead>
                	<tr class="rowbox05 tit"><td style="width:12%;">序号</td><td style="width:18%;">注册码</td><td style="width:13%;">姓名</td><td style="width:19%;">注册时间</td><td style="width:19%;">出借时间</td><td style="width:19%;">出借金额</td></tr>
				</thead>
				<tbody>
				
				</tbody>
                </table>
                <!--分页-->
            	<div id="page" class="page_div"></div>
                <!--分页结束-->
              
            </div>
            </div>
            
            
            <div class="wxts hide">
            	<div class="wxtstit"><img src="<%=pathUrl %>/images/light.png"><span>温馨提示</span></div>
                <div class="clear"></div>
                <div class="wxtscon">特别提醒：
                <br/>请勿使用他人身份证进行实名认证并且充值，否则将导致无法提现
				<br/>用户须知：
				<br/>1.平台只支持绑定1张银行卡（不支持绑定信用卡），用户今后在快捷充值与提现时，可以使用这张银行卡
                <br/>2.请用户尽量填写较大的银行(如农行、工行、建行、中国银行等)，避免填写那些比较少见的银行（如农村信用社、平安银行、恒丰银行等）。 否则提现资金很容易会被退票。
                <br/>3. 如果选择其它银行，请在选择其它银行后填写该银行的名称。
                <br/>4. 请您填写完整联系方式，以便遇到问题时，工作人员可以及时联系到您。
                </div>
            </div>
            
            <!--<div class="invitation">
            	<div class="invitationtop"><span>邀请规则</span></div>
                <div class="shouru">
                	<div class="rules_tit">推荐好友奖励规则</div>
                    <div class="rules_con">1.推荐好友奖励规则推荐好友奖励规则推荐好友奖励规则推荐好友奖励规则推<br>1.推荐好友奖励规则推荐好友奖励规则推荐好友奖励规则推荐好友奖励规则推<br>1.推荐好友奖励规则推荐好友奖励规则推荐好友奖励规则推荐好友奖励规则推</div>
                    <div class="clear"></div>
                </div>
            </div>-->
            
            
      		
            
            
            
            
            <div class="clear"></div>
        </div>
        
    </div>
    </div>
</div>
<!-----------------中间部分结束----------------> 
<!--bottom common-->

<%@ include file="footer.jsp" %>

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script language=javascript type=text/javascript> 
$(document).ready(function(){
    if ( window.clipboardData ) {
        /* $('.copy_btn').click(function() {
            window.clipboardData.setData("Text", $(this).prev('span').text());
            alert('复制成功！');
        }); */
        $('.bds_link').click(function() {
            window.clipboardData.setData("Text", $(".InciteLink").text());
            alert('复制成功！');
        });
    } else {
        /* $(".copy_btn").zclip({
            path:'http://img3.job1001.com/js/ZeroClipboard/ZeroClipboard.swf',
            copy:function(){return $(this).prev('span').text();},
            afterCopy:function(){alert('复制成功！');}
        }); */
        $(".bds_link").zclip({
            path:'<%=pathUrl %>/image/ZeroClipboard.swf',
            copy:function(){return $(".InciteLink").text();},
            afterCopy:function(){alert('复制成功！');}
        });
    }
});
</script>
<script>
var oHeight=$("#member_value").text();
    window._bd_share_config={
        "common":{
            "bdPopTitle":"您的自定义pop窗口标题aa",
            "bdSnsKey":{},
            "bdText":"上砖头，有赚头~",
            "bdMini":"2",
            "bdMiniList":false,
            /* "bdUrl":"http://www.izhuantou.com/p2p/web/Invite_FriendsStart.jsp?ssl="+oHeight, */ 
            "bdUrl":"https://www.izhuantou.com/p2p/web/Invite_FriendsStart.jsp?ssl="+oHeight,
            "bdPic":"https://www.izhuantou.com/p2p/web<%=pathUrl %>/images/logof.png", /* 此处填写要分享图片地址 */
            "bdStyle":"0",
            "bdSize":"16"
        },
        "share":{}
    };
    with(document)0[
            (getElementsByTagName('head')[0]||body).
            appendChild(createElement('script')).
            src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)
            ];
</script>
<!--<script src="/js/tabmenu.js"></script>-->
<script>
$(function(){
	$.ajax({
        type: "post",//请求方式
        url: "/portal/mytq/invitation",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        data:{
        	currentPage:1
        },
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        	console.log(result);
        var dataItems=result.dataValue.data;
        if(result.dataValue.totalNumber<=10){
        	$("#page").remove();
        }
        
        for(var i=0;i<dataItems.length;i++){
        	var str=' <tr class="rowbox05 value"><td style="width:12%;">'+(i+1)+'</td><td style="width:18%;">'+dataItems[i].name+'</td><td style="width:13%;">'+dataItems[i].nameCN+'</td><td style="width:19%;">'+dataItems[i].zcsj+'</td><td style="width:19%;">'+dataItems[i].addDateTime+'</td><td style="width:19%;">'+dataItems[i].money+'</td></tr>';
        	$(".mytzcon tbody").append(str);
        }
        $("#page").paging({
			totalPage: result.dataValue.totalPage,
			totalSize: result.dataValue.totalNumber,
			callback: function(num) {
				$.ajax({
                    type: "post",//请求方式
                    url: "/portal/mytq/invitation",//地址，就是json文件的请求路径
                    dataType: "json",//数据类型可以为 text xml json  script  jsonp
                    data:{
                    	currentPage:num
                    },
                    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                    	console.log(result);
                    	$(".mytzcon tbody tr").remove();
                    	for(var i=0;i<dataItems.length;i++){
                        	var str=' <tr class="rowbox05 value"><td style="width:12%;">'+((num-1)*10+(i+1))+'</td><td style="width:18%;">'+dataItems[i].name+'</td><td style="width:13%;">'+dataItems[i].nameCN+'</td><td style="width:19%;">'+dataItems[i].zcsj+'</td><td style="width:19%;">'+dataItems[i].addDateTime+'</td><td style="width:19%;">'+dataItems[i].money+'</td></tr>';
                        	$(".mytzcon tbody").append(str);
                        }
                    	
                    }    			            	            	
                	});
			}
		})
        
        
        },
		error:function(result){
    	}
    });
})
</script>
</html>
