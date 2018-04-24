/**
 * Created by pc on 2016/11/25.
 */
$(function(){
  /*  $(".team_top").mouseover(function(){
        $(".team_top_img1").css({"z-index":"2","opacity":"1"}).siblings().css({"opacity":"0","z-index":"1"})
    });
    $(".team_top").mouseout(function(){
        $(".team_top_img1").css({"z-index":"1","opacity":"0"}).siblings().css({"opacity":"1","z-index":"2"})
    })*/
   /* $(".team_top").hover(function(){
        $(this).find(".team_top_img1").css({"z-index":"2","opacity":"1"}).siblings().css({"opacity":"0","z-index":"1"})
    },function(){
        $(this).find(".team_top_img1").css({"z-index":"1","opacity":"0"}).siblings().css({"opacity":"1","z-index":"2"})
    })*/
    /*$(".team_people_img").hover(function(){
        $(this).find(".team_top_img1").css({"z-index":"2","opacity":"1"}).siblings().css({"opacity":"0","z-index":"1"})
    },function(){
        $(this).find(".team_top_img1").css({"z-index":"1","opacity":"0"}).siblings().css({"opacity":"1","z-index":"2"})
    })
    $(".team_bottom_box").hover(function(){
        $(this).find(".team_bottom_box_img2").css({"z-index":"2","opacity":"1"}).siblings().css({"opacity":"0","z-index":"1"})
    },function(){
        $(this).find(".team_bottom_box_img2").css({"z-index":"1","opacity":"0"}).siblings().css({"opacity":"1","z-index":"2"})
    })*/

    //   任务切换
    var n=0;
    var osTop;
    $(".team_people1 .team_right").click(function(){
        n++;
        if (n>3){
            n=0;

        }
        $(".team_people1 .team_wrap_box").eq(n).css({"display":"block","z-index":"2"}).siblings(".team_wrap_box").css({"dispaly":"none","z-index":"1"});
    })
    $(".team_people1 .team_left").click(function(){
        n--;
        if (n<0){
            n=3;
        }
        $(".team_people1 .team_wrap_box").eq(n).css({"display":"block","z-index":"2"}).siblings(".team_wrap_box").css({"dispaly":"none","z-index":"1"});
    })


    $(".team_people1 .team_bottom_box").click(function(){
        n= $(this).index();
        /*$(".team_people1 .team_wrap_top").stop().slideDown(500,"backOut").parent().parent().siblings().find(".team_people").find(".team_wrap_top").slideUp(500,"backIn");*/
        $(".team_people1 .team_wrap_box").eq(n).css({"display":"block","z-index":"2"}).siblings(".team_wrap_box").css({"display":"none","z-index":"1"});
    })

//team_people2
    var n2=0;
    $(".team_people2 .team_right").click(function(){
        n2++;
        if (n2>9){
            n2=0;
            /*$(".team_people2 .team_wrap_top").stop().slideUp(500,"backIn");
            $(".team_people3 .team_wrap_top").stop().slideDown(500,"backOut");*/
            //var a=osTop+=0;
           /* osTop=$(document).scrollTop();
            $("body,html").animate({scrollTop:osTop+300},1000);*/
        }
        $(".team_people2 .team_wrap_box").eq(n2).css({"display":"block","z-index":"2"}).siblings(".team_wrap_box").css({"dispaly":"none","z-index":"1"});
    })
    $(".team_people2 .team_left").click(function(){
        n2--;
        if (n2<0){
            n2=9;
            /*$(".team_people2 .team_wrap_top").stop().slideUp(500,"backIn");
            $(".team_people1 .team_wrap_top").stop().slideDown(500,"backOut");
            osTop=$(document).scrollTop();
            $("body,html").animate({scrollTop:osTop-300},1000);*/
        }
        $(".team_people2 .team_wrap_box").eq(n2).css({"display":"block","z-index":"2"}).siblings(".team_wrap_box").css({"dispaly":"none","z-index":"1"});
    })


    $(".team_people2 .team_bottom_box").click(function(){
        n2= $(this).index();
        /*$(".team_people2 .team_wrap_top").stop().slideDown(500,"backOut").parent().parent().siblings().find(".team_people").find(".team_wrap_top").slideUp(500,"backIn");*/
        $(".team_people2 .team_wrap_box").eq(n2).css({"display":"block","z-index":"2"}).siblings(".team_wrap_box").css({"display":"none","z-index":"1"});
    })

    
})