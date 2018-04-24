// JavaScript Document
(function(ns){  
        function Scroll(element){
              
            var content = document.createElement("div");
            var container = document.createElement("div");
            var _this =this;
            var cssText = "position: absolute; visibility:hidden; left:0; white-space:nowrap;";
            this.element = element; 
            this.contentWidth = 0;
            this.stop = false;
              
            content.innerHTML = element.innerHTML;
              
            //获取元素真实宽度
            content.style.cssText = cssText;
            element.appendChild(content);
            this.contentWidth = content.offsetWidth;
              
            content.style.cssText= "float:left;";
            container.style.cssText = "width: " + (this.contentWidth*1000) + "px; overflow:hidden";
            container.appendChild(content);
            container.appendChild(content.cloneNode(true));
            element.innerHTML = "";
            element.appendChild(container);
              
            container.onmouseover = function(e){
                clearInterval(_this.timer);
				
				/*$(function(){
				$(".tuyo").mouseover(function(){
				$(this).children('div.lanbk').animate({'height':'150px'},500);
					});
				});*/
		 
            };
              
            container.onmouseout = function(e){
                _this.timer = setInterval(function(){ 
                    _this.run();
                },80);          
  		/*$(function(){
		$('div.lanbk').css('height','0px');
		$(".tuyo").mouseout(function(){
		$(this).children('div.lanbk').animate({'height':'0px'},500);
			});
		});*/
                  
            };
            _this.timer = setInterval(function(){ 
                _this.run();
            }, 80);
        }
          
        Scroll.prototype = {
              
            run: function(){
                  
                var _this = this;
                var element = _this.element;
                  
                element.scrollLeft = element.scrollLeft + 1;
                  
                if(element.scrollLeft >=  this.contentWidth ) {
                        element.scrollLeft = 0;
                }
            }
        };
    ns.Scroll = Scroll; 
}(window));