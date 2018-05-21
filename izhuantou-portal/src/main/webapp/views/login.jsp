<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/css/style-common.css">
    <link rel="stylesheet" type="text/css" href="/css/login.css?v=116">
    <Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
    <script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
    <script src="/js/drag.js" type="text/javascript"></script>
    <script src="/js/com.js"></script>
    <!-- <script src="/js/resubmit.js"></script> -->
    <style>
        .inpbox.yzm .close_btn{
            right:115px;
        }
         #drag{ 
    position: relative;
    background-color: #e8e8e8;
    width: 300px;
    height: 44px;
    line-height: 44px;
    text-align: center;
    -moz-user-select: none;
    -webkit-user-select: none;
    user-select: none;
    -o-user-select:none;
    -ms-user-select:none;
    border-radius: 5px;
}
#drag .handler{
    position: absolute;
    top: 0px;
    left: 0px;
    width: 40px;
    height: 42px;
    border: 1px solid #ccc;
    cursor: move;
    border-radius: 5px;
    
}
.handler_bg{
    background: #fff url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ZDhlNWY5My05NmI0LTRlNWQtOGFjYi03ZTY4OGYyMTU2ZTYiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NTEyNTVEMURGMkVFMTFFNEI5NDBCMjQ2M0ExMDQ1OUYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NTEyNTVEMUNGMkVFMTFFNEI5NDBCMjQ2M0ExMDQ1OUYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo2MTc5NzNmZS02OTQxLTQyOTYtYTIwNi02NDI2YTNkOWU5YmUiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NGQ4ZTVmOTMtOTZiNC00ZTVkLThhY2ItN2U2ODhmMjE1NmU2Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+YiRG4AAAALFJREFUeNpi/P//PwMlgImBQkA9A+bOnfsIiBOxKcInh+yCaCDuByoswaIOpxwjciACFegBqZ1AvBSIS5OTk/8TkmNEjwWgQiUgtQuIjwAxUF3yX3xyGIEIFLwHpKyAWB+I1xGSwxULIGf9A7mQkBwTlhBXAFLHgPgqEAcTkmNCU6AL9d8WII4HOvk3ITkWJAXWUMlOoGQHmsE45ViQ2KuBuASoYC4Wf+OUYxz6mQkgwAAN9mIrUReCXgAAAABJRU5ErkJggg==") no-repeat center;
}
.handler_ok_bg{
    background: #fff url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ZDhlNWY5My05NmI0LTRlNWQtOGFjYi03ZTY4OGYyMTU2ZTYiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NDlBRDI3NjVGMkQ2MTFFNEI5NDBCMjQ2M0ExMDQ1OUYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NDlBRDI3NjRGMkQ2MTFFNEI5NDBCMjQ2M0ExMDQ1OUYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDphNWEzMWNhMC1hYmViLTQxNWEtYTEwZS04Y2U5NzRlN2Q4YTEiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NGQ4ZTVmOTMtOTZiNC00ZTVkLThhY2ItN2U2ODhmMjE1NmU2Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+k+sHwwAAASZJREFUeNpi/P//PwMyKD8uZw+kUoDYEYgloMIvgHg/EM/ptHx0EFk9I8wAoEZ+IDUPiIMY8IN1QJwENOgj3ACo5gNAbMBAHLgAxA4gQ5igAnNJ0MwAVTsX7IKyY7L2UNuJAf+AmAmJ78AEDTBiwGYg5gbifCSxFCZoaBMCy4A4GOjnH0D6DpK4IxNSVIHAfSDOAeLraJrjgJp/AwPbHMhejiQnwYRmUzNQ4VQgDQqXK0ia/0I17wJiPmQNTNBEAgMlQIWiQA2vgWw7QppBekGxsAjIiEUSBNnsBDWEAY9mEFgMMgBk00E0iZtA7AHEctDQ58MRuA6wlLgGFMoMpIG1QFeGwAIxGZo8GUhIysmwQGSAZgwHaEZhICIzOaBkJkqyM0CAAQDGx279Jf50AAAAAABJRU5ErkJggg==") no-repeat center;
}
#drag .drag_bg{
    background-color: #7ac23c;
    height: 44px;
    width: 0px;
    border-top-left-radius:5px;
    border-bottom-left-radius:5px;
}
#drag .drag_text{
    position: absolute;
    top: 0px;
    width: 300px;
    font-size:12px;
    color:#9c9c9c;
    -moz-user-select: none;
    -webkit-user-select: none;
    user-select: none;
    -o-user-select:none;
    -ms-user-select:none; 
}

#drag .drag_text[data-nc-lang="_startTEXT"] {
    background: -webkit-gradient(linear,left top,right top,color-stop(0,#4d4d4d),color-stop(.4,#4d4d4d),color-stop(.5,#fff),color-stop(.6,#4d4d4d),color-stop(1,#4d4d4d));
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    -webkit-animation: slidetounlock 3s infinite;
    -webkit-text-size-adjust: none;
}
@keyframes slidetounlock  {
0% {
    background-position: -200px 0;
    }
100% {
    background-position: 200px 0;
    }
}
    </style>

</head>
<body>
<!--common header-->
<%@ include file="header.jsp" %>
<!--  Cookie中获取手机号和密码-->
 <%  
        String userMobile = "";  
        String password = "";  
        Cookie[] c = request.getCookies();  
        if (c != null) {  
            for (int i = 0; i < c.length; i++) {  
                if ("userMobile".equals(c[i].getName())) {  
                	userMobile = c[i].getValue();  
                } else if ("password".equals(c[i].getName())) {  
                    password = c[i].getValue();  
                }  
            }  
        } else {  
        	userMobile = " ";  
            password = " ";  
        }  
    %>  
<div class="addbox" style="height:79px;display:none;"></div>
<!--中间部分开始-->
<div class="con logincon">
    <div class="login_right">
        <form action="/portal/user/toLogin" method="post" id="FormLogin" name="FormLogin">
            <input type="hidden" id="loginsign" name="loginsign" value="login"/>
            <div class="right_top"><span>登录砖头网</span></div>
            <div class="inpbox yhm urog-form-role urog-phone"><input type="text" id="phone" name="name" placeholder='请输入手机号' required data-rule-mobile='true'  onfocus="this.placeholder=''" onblur="this.placeholder='请输入手机号'"  value = '<%= userMobile %>' />
                <img src="<%=pathUrl %>/image/close_icon.png" class="urog-regclose close_btn" data-close="phone"/></div>
            <div class="inpbox pwd urog-form-role"><input type="password" id="password" name="password" autocomplete='off' placeholder='请输入密码' required data-rule-password='true'  minlength='0' maxlength='20' onfocus="this.placeholder=''" onblur="this.placeholder='请输入密码'" value='<%= password %>'    />
                <img src="<%=pathUrl %>/image/close_icon.png" class="urog-regclose close_btn" data-close="password"/></div>
                <div class="inpbox yhm urog-form-role"><div id="drag"></div><input style="opacity: 0;height:1px;visibility: hidden;" type="text" name="yzmIsFlag" id="yzmIsFlag" required data-rule-yzm='true'></div>
            <div class="reademe"><label class="checklabel"><input type="checkbox" id="isRememberMe" name="isRememberMe" value="ok" /><label for="isRememberMe">记住我</label>
            </label><a href="forgetpwd01.jsp">忘记密码？</a></div>
            <div align="center" class="loginErro"><hoontag:Message name="loginErro"/></div>
            <div id="login_val" class="login_validate"> ${msg}</div>
            <div  class="dlbtn" ><button id="login" name="login" value="登录" type="submit" class="dlbtn">登录</button>
                <span class="loadingBtn" style="display:none;">正在登录中<img src="<%=pathUrl %>/images/loading_dian.gif"></span>
            </div>
            <div class="reademe01">还没有账号？<a href="register.jsp">立即注册</a></div>
        </form>
    </div>
    <div class="login_left"><a href="ZTYMain/FirstAnniversary_ZTY.jsp"><img src="<%=pathUrl %>/images/convention_login_left_new.jpg?v=137"></a></div>
</div>
<!--bottom common-->
<%@ include file="footer.jsp" %>
</body>
<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script>
$('#drag').drag();
    $(function(){
        //jquery.validate

        $(window).scroll(function(){
            if(getScrollTop()>50){
                $(".header").addClass("fixed");
                $(".addbox").css("display","block");
            }
            else{
                $(".header").removeClass("fixed");
                $(".addbox").css("display","none");
            }
        });
        if($(window).scrollTop() > 80){
            $(".addbox").css("display","block");
        }else{
            $(".addbox").css("display","none");
        }


        //    登录验证
        var oLogin_ph = $(".yhm input");
        var oLogin_paw = $(".pwd input");
        var oLogin_val = $("#login_val");
        var oLogin_yzm = $(".yzm input");
        var yzmIsFlag = $("#yzmIsFlag");
        
        $(".dlbtn").click(function () {
            var isright = true;
            var str = "";
            if (!oLogin_ph.val()) {
                str += "请输入手机号！";
                isright = false;
            } else if (/^1(3|4|5|7|8)\d{9}$/.test(oLogin_ph.val()) == false) {
                str += "请输入11位手机号！";
                isright = false;
            } else if (!oLogin_paw.val()) {
                str += "请输入密码！";
                isright = false;
            } else if(/^[0-9a-zA-Z]*$/.test(oLogin_paw.val()) == false){
                str += "密码只能是数字和字母";
                isright = false;
            }else if (/^yzmok$/.test(yzmIsFlag.val()) == false) {
                str += "请按住滑块，拖动到最右边";
                isright = false;
            }

            if (isright == true) {
                oLogin_val[0].innerHTML = "";


            } else {
                oLogin_val[0].innerHTML = str;
            }
        })

        $("#FormLogin").validate({
            submitHandler: function(form) {
                //验证通过后 的js代码写在这里
                validataForm_id("login");
                form.submit();          	
                //return validataForm_id("login");
                $(".loadingBtn").css("display","block");
                $(".login_validate").text("");
            }
        }) 
        //登录保留值
        
        /* $("#login").click(function(){
        	$("#FormLogin").validate({
                submitHandler: function(form) {
                    //验证通过后 的js代码写在这里
                    //validataForm_id("login");
                     $.ajax({
                        url : "/portal/user/toLogin",
                        type : "post",
                        dataType : "json",
                        data: {
                           // loginsign: $("#loginsign").val(),
                            isRememberMe:$("#isRememberMe").val(),
                            name: $("#phone").val(),
                            password: $("#password").val(),
                            yzm: $("#yzm").val()
                        },
                        success : function(result) {
                        	if(result.status==1){
                        		window.location.href = "/portal/user/indexUI?"+Math.random(); 
            	        	}else{
            	        		//window.location.href = "/portal/user/loginUI";
            	        		$(".loginErro").show();
            	        		$(".loginErro").text(result.message);
            	        		$(".loadingBtn").css("display","none")
            	        	}
                        },
                        error:function(result){
                        	alert("异常!");
                        }
                    }); 
                	
                    
                    //return validataForm_id("login");
                    $(".loadingBtn").css("display","block");
                    $(".login_validate").text("");
                }
            })
        }) */


        $("#phone,#password,#yzm").focus(function(){
            $(".loginErro").css("display","none");
        })
    })





    //配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
    $.validator.setDefaults({
        errorElement:'span'
    });

    //配置通用的默认提示语
    $.extend($.validator.messages, {
        required: '',//必填
        equalTo: "",//请再次输入相同的值
        maxlength: $.validator.format(""),//Please enter no more than {0} characters.
        minlength: $.validator.format(""),//Please enter at least {0} characters.
    });




    //手机验证规则
    jQuery.validator.addMethod("mobile", function (value, element) {
        var mobile = /^1[3|4|5|7|8]\d{9}$/;
        return this.optional(element) || (mobile.test(value));
    }, "");//手机格式不对

    //密码验证规则
    jQuery.validator.addMethod("password", function (value, element) {
        var password = /^[0-9a-zA-Z]*$/;
        return this.optional(element) || (password.test(value));
    }, "");//密码只能是数字和字母
    
    jQuery.validator.addMethod("yzm", function (value, element) {
        var yzm = /^yzmok$/;
    	return this.optional(element) || (yzm.test(value));
    }, "");


    /* //密码验证规则
     jQuery.validator.addMethod("password", function (value, element) {
        var password = /^\w*(?=\w*\d)(?=\w*[a-zA-Z])\w*$/;
        return this.optional(element) || (password.test(value));
    }, "");//密码只能是数字和字母  */



</script>
<script>
    $(function() {

        $(".urog-form-role").click(function(event){
            //得到点击事件的dom结构
            var click_node = getEventTrigger(event);
            //得到点击close图片的data-close
            //data-close的值是input框的id
            var clickName = click_node.getAttribute("data-close");
            //得到点击事件的属性（INPUT）
            var clickInput = click_node.nodeName;
            //判断点击的是close图片，则清空相应id输入框的值，并隐藏该close图片
            if(clickName=='phone'|| clickName=='password'){
                $("#"+clickName).val(null);
                $("#"+clickName).siblings(".urog-regclose").css("display","none");
            }
            //判断点击事件的元素是input框
            if(clickInput=="INPUT"){
                //获取INPUT的id
                var next_closeId = click_node.id;
                //alert(next_closeId);
                //绑定获取焦点和输入值-函数
                $("#"+next_closeId).bind("focus keyup",function(){
                    //判断当前输入框的值不为空时
                    if($("#"+next_closeId).val()!=''){
                        $(".urog-regclose").css("display","none");
                        //当前输入框的下一个同袍节点样式显示
                        $("#"+next_closeId).siblings(".urog-regclose").css({"display":"block","opacity":"1"});
                    }else{
                        $(".urog-regclose").css("display","none");
                    }

                })
            }
        })
        $(".urog-form-role input").blur(function(){
            //alert("aa");
            $(".urog-regclose").css("opacity","0");
        })
        //得到点击事件的dom结构
        function getEventTrigger(event)
        {
            //ie 火狐 event兼容
            //参考资料： http://www.cnblogs.com/quanhai/archive/2010/04/20/1716149.html
            event = event? event: window.event
            var x = event.srcElement ? event.srcElement:event.target;
            return x;
        }
    })
</script>
