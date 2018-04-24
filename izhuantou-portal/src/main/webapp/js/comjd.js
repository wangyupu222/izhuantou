
	
//原型进度条
window.onload = function(){
			var i = 88;
			var object = document.getElementById('box');
			
					function loadImg (data){
					
					var i = 0;
					setInterval(function(){
						i++
						if(i>data){
							i=data
						}
						var imgLeft = -(i*95)+'px';
						object.style.backgroundPosition = imgLeft+'\t'+'0px';
						object.innerHTML = i+'%';
					},10)
					
			}
			
		
			loadImg(i);
			if(i == 100){
						object.style.color='#679d28';
					}else{object.style.color='#ff7c2d';}
			
					}
						
