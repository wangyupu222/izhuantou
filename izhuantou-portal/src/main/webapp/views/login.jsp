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
    <script src="/js/com.js"></script>
    <!-- <script src="/js/resubmit.js"></script> -->
    <style>
        .inpbox.yzm .close_btn{
            right:115px;
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
                <img src="/image/close_icon.png" class="urog-regclose close_btn" data-close="phone"/></div>
            <div class="inpbox pwd urog-form-role"><input type="password" id="password" name="password" autocomplete='off' placeholder='请输入密码' required data-rule-password='true'  minlength='0' maxlength='20' onfocus="this.placeholder=''" onblur="this.placeholder='请输入密码'" value='<%= password %>'    />
                <img src="/image/close_icon.png" class="urog-regclose close_btn" data-close="password"/></div>
                <div class="inpbox yzm"><input type="text" id="yzm" name="yzm" autocomplete='off' placeholder='请输入验证码' required  minlength='4' maxlength='4' onfocus="this.placeholder=''" onblur="this.placeholder='请输入验证码'"  /><a href="#"><img width="100" height="36" src="checkcode" onclick="this.setAttribute('src','checkcode?x='+Math.random());"  class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!"></a>
        	</div>
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
            }else if (!oLogin_yzm.val()) {
                str += "请输入验证码";
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
