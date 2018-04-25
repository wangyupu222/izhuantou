<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>砖头网-一周年庆典，感谢一路有你</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link href="/css/normalize.css" rel="stylesheet" type="text/css">
<link href="/css/ZTYWrap/FirstAnniversary.css?v=117" rel="stylesheet" type="text/css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js?ver=114"></script>
</head>
<style>
.footerbg {
 border-top: none;
 margin-top: 0px;
}
</style>
<body>
<% try{ %>
<%@ include file="../header.jsp" %>
<div class="addbox" style="height:79px;display:none;"></div> 
<div class="AnniversaryWrap">
    <div class="AnniversaryMain">
        <div class="firstTit" style="width:100%;">
            <img src="/im/irstAnniversary/firstTit.png">
        </div>
        <div class="welfareBox01" style="width:100%;">
            <img class="Tit01" src="<%=pathUrl %>/image/FirstAnniversary/welfareTit01.png?v=114">
            <img class="TableImg01" src="..<%=pathUrl %>/image/FirstAnniversary/welfareImg01.png?v=114">
        </div>
        <div class="welfareBox02" style="width:100%;">
            <img class="welfareTit02" src="<%=pathUrl %>/image/FirstAnniversary/welfareTit02.png?v=114">
            <img class="welfareImg02" src="<%=pathUrl %>/image/FirstAnniversary/welfareImg02.png?v=114">
        </div>
        <div class="welfareBox04" style="width:100%;">
            <img class="welfareTit04" src="<%=pathUrl %>/image/FirstAnniversary/welfareTit03.png?v=114">
            <img class="welfareImg04" src="<%=pathUrl %>/image/FirstAnniversary/welfareImg03.png?v=114">
        </div>
        <div class="welfareBox03" style="width:100%;">
            <img class="welfareTit03" src="<%=pathUrl %>/image/FirstAnniversary/welfareTit04.png">
            <img class="welfareImg03" src="<%=pathUrl %>/image/FirstAnniversary/welfareImg04.png">
        </div>


    </div>

</div>

<!--bottom common-->
<%@ include file="../footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>

<script>
    $(function(){
        var u = navigator.userAgent;
        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
            $("body").css("min-width","1160px");
        } else if (u.indexOf('iPhone') > -1) {//苹果手机
            $("body").css("min-width","1160px");
        } else if (u.indexOf('Windows Phone') > -1) {//winphone手机
            $("body").css("min-width","1160px");
        }
        
        $(window).scroll(function(){	       								
      		 if(getScrollTop()>80){
      			     $(".addbox").css("display","block");
      			}
      		else{
      			$(".addbox").css("display","none");
      			}		
      	});
      	if($(window).scrollTop() > 80){
      		$(".addbox").css("display","block");
      	}else{
      		$(".addbox").css("display","none");
      	}
        var isMobile = /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i.test(navigator.userAgent);
        if(!isMobile){
            var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

            function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

            var Progress = function () {
                function Progress() {
                    var param = arguments.length <= 0 || arguments[0] === undefined ? {} : arguments[0];

                    _classCallCheck(this, Progress);

                    this.timestamp = null;
                    this.duration = param.duration || Progress.CONST.DURATION;
                    this.progress = 0;
                    this.delta = 0;
                    this.progress = 0;
                    this.isLoop = !!param.isLoop;

                    this.reset();
                }

                Progress.prototype.reset = function reset() {
                    this.timestamp = null;
                };

                Progress.prototype.start = function start(now) {
                    this.timestamp = now;
                };

                Progress.prototype.tick = function tick(now) {
                    if (this.timestamp) {
                        this.delta = now - this.timestamp;
                        this.progress = Math.min(this.delta / this.duration, 1);

                        if (this.progress >= 1 && this.isLoop) {
                            this.start(now);
                        }

                        return this.progress;
                    } else {
                        return 0;
                    }
                };

                _createClass(Progress, null, [{
                    key: "CONST",
                    get: function get() {
                        return {
                            DURATION: 1000
                        };
                    }
                }]);

                return Progress;
            }();

            var Confetti = function () {
                function Confetti(param) {
                    _classCallCheck(this, Confetti);

                    this.parent = param.elm || document.body;
                    this.canvas = document.createElement("canvas");
                    this.ctx = this.canvas.getContext("2d");
                    this.width = param.width || this.parent.offsetWidth;
                    this.height = param.height || this.parent.offsetHeight;
                    this.length = param.length || Confetti.CONST.PAPER_LENGTH;
                    this.yRange = param.yRange || this.height * 2;
                    this.progress = new Progress({
                        duration: param.duration,
                        isLoop: true
                    });
                    this.rotationRange = typeof param.rotationLength === "number" ? param.rotationRange : 10;
                    this.speedRange = typeof param.speedRange === "number" ? param.speedRange : 10;
                    this.sprites = [];

                    this.canvas.style.cssText = ["display: block", "position: absolute", "top: 0", "left: 0", "pointer-events: none"].join(";");

                    this.render = this.render.bind(this);

                    this.build();

                    this.parent.append(this.canvas);
                    this.progress.start(performance.now());

                    requestAnimationFrame(this.render);
                }

                Confetti.prototype.build = function build() {
                    for (var i = 0; i < this.length; ++i) {
                        var canvas = document.createElement("canvas"),
                            ctx = canvas.getContext("2d");

                        canvas.width = Confetti.CONST.SPRITE_WIDTH;
                        canvas.height = Confetti.CONST.SPRITE_HEIGHT;

                        canvas.position = {
                            initX: Math.random() * this.width,
                            initY: -canvas.height - Math.random() * this.yRange
                        };

                        canvas.rotation = this.rotationRange / 2 - Math.random() * this.rotationRange;
                        canvas.speed = this.speedRange / 2 + Math.random() * (this.speedRange / 2);

                        ctx.save();
                        ctx.fillStyle = Confetti.CONST.COLORS[Math.random() * Confetti.CONST.COLORS.length | 0];
                        ctx.fillRect(0, 0, canvas.width, canvas.height);
                        ctx.restore();

                        this.sprites.push(canvas);
                    }
                };

                Confetti.prototype.render = function render(now) {
                    var progress = this.progress.tick(now);

                    this.canvas.width = this.width;
                    this.canvas.height = this.height;

                    for (var i = 0; i < this.length; ++i) {
                        this.ctx.save();
                        this.ctx.translate(this.sprites[i].position.initX + this.sprites[i].rotation * Confetti.CONST.ROTATION_RATE * progress, this.sprites[i].position.initY + progress * (this.height + this.yRange));
                        this.ctx.rotate(this.sprites[i].rotation);
                        this.ctx.drawImage(this.sprites[i], -Confetti.CONST.SPRITE_WIDTH * Math.abs(Math.sin(progress * Math.PI * 2 * this.sprites[i].speed)) / 2, -Confetti.CONST.SPRITE_HEIGHT / 2, Confetti.CONST.SPRITE_WIDTH * Math.abs(Math.sin(progress * Math.PI * 2 * this.sprites[i].speed)), Confetti.CONST.SPRITE_HEIGHT);
                        this.ctx.restore();
                    }

                    requestAnimationFrame(this.render);
                };

                _createClass(Confetti, null, [{
                    key: "CONST",
                    get: function get() {
                        return {
                            SPRITE_WIDTH: 9,
                            SPRITE_HEIGHT: 16,
                            PAPER_LENGTH: 100,
                            DURATION: 8000,
                            ROTATION_RATE: 50,
                            COLORS: ["#EF5350", "#EC407A", "#AB47BC", "#7E57C2", "#5C6BC0", "#42A5F5", "#29B6F6", "#26C6DA", "#26A69A", "#66BB6A", "#9CCC65", "#D4E157", "#FFEE58", "#FFCA28", "#FFA726", "#FF7043", "#8D6E63", "#BDBDBD", "#78909C"]
                        };
                    }
                }]);

                return Confetti;
            }();

            (function () {
                var DURATION = 8000,
                    LENGTH = 50;

                new Confetti({
                    width: window.innerWidth,
                    height: window.innerHeight,
                    length: LENGTH,
                    duration: DURATION
                });

                setTimeout(function () {
                    new Confetti({
                        width: window.innerWidth,
                        height: window.innerHeight,
                        length: LENGTH,
                        duration: DURATION
                    });
                }, DURATION / 2);
            })();
        }else{
        	//$(".firstTit img,.welfareImg01,.welfareImg02,.welfareTit02,.welfareTit01").css("max-width","initial");
        }

    })
</script>
</body>

</html>