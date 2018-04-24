/**
 * Created by Administrator on 2017/5/5.
 */
// JavaScript Document
//图标划动态
var funcData={
        //ICO触效
        PingyiIcoFun : function (o,n){
            $(o).hover(function(){
                $(this).find('.FunPingyiIco i').stop().animate({'top':n});
            },function(){
                $(this).find('.FunPingyiIco i').stop().animate({'top':'0px'});
            })
        },
        navID:{
            index:0,
            chujie:1,
            jiekuan:2,
            zhiyin:3,
            anbao:4,
            //about:5,
            classroom:5,
            zhanghu:6
        },
        //导航
        navContainerFunc : function (n){
            if(typeof(n)!='undefined'){ $('.nav-main li').removeClass('on').eq(n).addClass('on') }
            $('.nav-main li').last().css('margin-right','0px');
            var oldOn=$('.nav-main li.on').addClass('oldOn'),allLi=$('.nav-main li'),setOut=null,setIn=null;
            funcData.navMainS($('.nav-main'));
            $(document).delegate('.nav-main li','mouseenter',function(){
                //clearTimeout(setOut);
                allLi.removeClass('on');
                $(this).addClass('on');
                var $t=$(this);
                setIn=setTimeout(function(){
                    $t.find('.nav-main-zList').stop().fadeIn(200).find('a').stop().slideDown(200,function(){ $(this).removeAttr('style') });
                },200)
            }).delegate('.nav-main dt','mouseenter',function(){
                clearTimeout(setOut);
            }).delegate('.nav-main li','mouseleave',function(){
                clearTimeout(setIn);
                //clearTimeout(setOut);
                var $t=$(this);
                setOut=setTimeout(function(){
                    $t.not('.oldOn').removeClass('on');
                    $t.find('.nav-main-zList').fadeOut(200).find('a').slideUp(200);
                    oldOn.addClass('on');
                },200)
            })
        },
        //进度条
        Schedule01Func:function(p){
            var _this=this;
            this.am=function(o,t){
                if(typeof(o)!='number' && typeof(o)!='undefined'){
                    o.each(function(){
                        var n=$(this).find('input').val(),l=500+parseInt((n/10+1)*100),s=$('span',this),d=$('div',this),i=$('i',this),a=$(this).is('[sd-txt-id]')?$('.'+$(this).attr('sd-txt-id')):false;
                        $(this).animate({jindu:n},{step:function(n,fx){
                            s.stop().css({'width':n+'%'},l);
                            d.stop().css({'left':n+'%'},l);
                            i.html(parseInt(n)+'<em>%</em>');
                            a&&(a.html(parseInt(n)+'<em>%</em>'));
                        },duration:parseInt(n)+1000});
                    })
                }else{
                    var t=t?t:$('.StyJindu01'),n=o?o : t.find('input').val();
                    t.find('p span').animate({width:n+'%'});
                }
            }
            if(typeof(p)!='undefined'){  this.am(p); };
        },
        //链接整理
        aFunc:function(){
            $('a.disabled').attr('href','javascript:;')
        },
        //选项卡兼划显层
        tagFunc:function(){
            $('[tag-func-id][tags]').each(function(index, element) {
                var datas=$('[tag-func-id='+$(this).attr('tag-func-id')+'][cards]'),tag=$(this).attr('tags'),card=datas.attr('cards'),this_tag=$(this);
                if(this_tag.find(tag+'.on').length==1){
                    var i=this_tag.find(tag).index(this_tag.find(tag+'.on'));
                    datas.find(card).eq(i).hide().show();
                }else{
                    if(!(this_tag.attr('auto-one')=='false')){
                        this_tag.find(tag).eq(0).addClass('on');
                        datas.find(card).eq(0).show();
                    }
                }
                if(this_tag.find(tag+'.on').length==1&&this_tag.find(tag+'.on').is('[tag-event-call-back]')){ eval(this_tag.find(tag+'.on').attr('tag-event-call-back')) };
                if($(this).is('[tag-event]')){
                    //$(this).find(tag).click(function(index, element){
                    $(this).delegate(tag,'click',function(){
                        var $t=$(this),indexs=this_tag.find(tag).index($(this));
                        if($(this).closest('[tags]').attr('out-hide')=='true' && $t.hasClass('on') ){
                            $t.removeClass('on');
                            datas.find(card).eq(indexs).hide();
                        }else{
                            $t.addClass('on').siblings(tag+'.on').removeClass('on');
                            datas.find(card).eq(indexs).show().siblings(card).hide();
                            if($(this).is('[tag-event-call-back]')){ eval($(this).attr('tag-event-call-back')) };
                        }
                    })
                }else{
                    var key=null,outKey=null;
                    //$(this).find(tag).mouseenter(function(index, element){
                    $(this).delegate(tag,'mouseenter',function(){
                        clearTimeout(key);
                        clearTimeout(outKey);
                        var $t=$(this),indexs=this_tag.find(tag).index($(this));
                        key=setTimeout(function(){
                            $t.addClass('on').siblings(tag+'.on').removeClass('on');
                            datas.find(card).eq(indexs).show().siblings(card).hide();
                        },200)
                    })
                    if($(this).attr('out-hide')=='true'){
                        //$(this).find(tag).mouseleave(function(index, element) {
                        $(this).delegate(tag,'mouseleave',function(){
                            if(datas.attr('out-show')=='true'){ return false };
                            clearTimeout(key);
                            var $t=$(this),indexs=this_tag.find(tag).index($(this));
                            outKey=setTimeout(function(){
                                $t.removeClass('on');
                                datas.find(card).eq(indexs).hide();
                            },200)
                        });
                        if(datas.attr('in-show')=='true'){
                            //datas.find(card).mouseenter(function(e) {
                            datas.delegate(card,'mouseenter',function(){
                                clearTimeout(outKey);
                            }).delegate(card,'mouseleave',function(index, element){
                                if(datas.attr('out-show')=='true'){ return false };
                                var $t=$(this),indexs=datas.find(card).index($(this));
                                outKey=setTimeout(function(){
                                    $t.hide();
                                    this_tag.find(tag).eq(indexs).removeClass('on');
                                },200)
                            });
                        }
                    }
                }
            });
        },
        //单行公告
        adTxt:function(){
            var c,_=Function;
            with(o=document.getElementById("ink_con")){ innerHTML+=innerHTML; onmouseover=_("c=1"); onmouseout=_("c=0");}
            (F=_("if(#%18||!c)#++,#%=o.scrollHeight>>1;setTimeout(F,#%18?10:1500);".replace(/#/g,"o.scrollTop")))();
        },
        //首页伙伴
        sLeft:function (){
            var btn=$('.IDpartner a'),w=1152;
            if(btn.eq(0).hasClass('IDsLeftBtn')){ return false; }
            btn.addClass('IDsLeftBtn');
            function anList(ob,a){
                if(a<=0){ return false;}
                var $f=ob.find('li:first');
                $f.animate({'marginLeft':'-'+($f.outerWidth(true)-25)+'px'},200,function(){
                    $(this).appendTo(ob).css('margin-left','0px');
                    anList(ob,a-1);
                })
            }
            function anRlist(ob,nw){
                ob.find('li:first').css('margin-left','0px');
                var $l=ob.find('li:last'),m=$l.outerWidth(true);
                if(nw+m>=w){ return false;}
                $l.css('margin-left','-'+m+'px').prependTo(ob).animate({'marginLeft':'-20px'},200,function(){
                    anRlist(ob,nw+m);
                })
            }
            btn.first().click(function(){
                var ob=$(this).siblings('div').find('ul');
                anRlist(ob,0);
            })
            btn.last().click(function(){
                var ob=$(this).siblings('div').find('ul'),list=$(this).siblings('div').find('li'),ow=0;
                for(var i=0; i<list.length;i++){
                    ow=ow+list.eq(i).outerWidth(true);
                    if(ow>w){
                        anList(ob,i);
                        break;
                    }
                }
            })
        },
        newsFunc:function(o){
            var o=o?o:$('.newsListMian');
            function sA(o,h){
                if(!o.hasClass('add_IDstop')){
                    $(o).find('a:first').animate({'marginTop':'-'+h+'px'},1000,function(){
                        $(this).appendTo(o).css('margin-top','0px');
                        setTimeout(function(){ sA(o,h); },3000);
                    });
                }else{
                    setTimeout(function(){ sA(o,h); },3000);
                }
            }
            $('.newsListMian').each(function(index, element) {
                var h=$(this).find('a:first').outerHeight(true);
                sA($(this),h);
            }).mouseenter(function(){ $(this).addClass('add_IDstop'); }).mouseleave(function(e) { $(this).removeClass('add_IDstop'); });
        },
        Titles:function(){ //title美化 用法：<div titles="提示信息内容"></div>
            function htmlFunc(o){
                var tCssName = o.attr('ls-title-cssname') ? o.attr('ls-title-cssname') : '';
                var tClass = o.attr('ls-titleclass') ? o.attr('ls-titleclass') : 'ls_JIAO_l';
                var tMar = o.attr('ls-titlemar') ? o.attr('ls-titlemar') : '4px 0px 8px 16px';
                var htmls='<div class="ls_FUNCtitles '+tCssName+'" style="margin:'+tMar+';">'+
                    '<div class="ls_IDn0">'+
                    '<div class="ls_IDn1">'+
                    '<i class="ls_JIAO '+tClass+'"></i>'+
                    '<i class="ls_IDn1_n0"></i>'+
                    '<i class="ls_IDn1_n1"></i>'+
                    '<i class="ls_IDn1_n2"></i>'+
                    '<i class="ls_IDn1_n3"></i>'+
                    '<div class="ls_IDn1_n4"></div>'+
                    '<div class="ls_TITLESIDnr">'+$(o).attr('ls-titles')+
                    '</div>'+
                    '<div class="ls_IDn1_n5"><div></div></div>'+
                    '</div>'+
                    '</div>'+
                    '</div>';
                return htmls;
            }
            $(function(){
                $(document).delegate('[ls-titles]','mouseenter',function(){
                    var t=0,l=0,c=$(this).attr('ls-titleclass'),isMr=$(this).attr('position-auto');
                    if($('.ls_FUNCtitles').length>0){$('.ls_FUNCtitles').remove()};
                    $('body').append(htmlFunc($(this)));
                    //isMr&&($('.ls_FUNCtitles').css({'width':'auto'}));
                    if($(this).is('[ls-css-name]')){$('.ls_FUNCtitles').addClass($(this).attr('ls-css-name'));}
                    if(c.search('ls_JIAO_b')!==-1&&isMr){
                        t=$(this).offset().top-15-$('.ls_FUNCtitles').height();
                        l=$(this).offset().left-($('.ls_FUNCtitles').width()-$(this).width())*0.5-2;
                        $('.ls_FUNCtitles').css('margin','0px');
                    }else if(c.search('ls_JIAO_t')!==-1&&isMr){
                        t=$(this).offset().top+15+$(this).height();
                        l=$(this).offset().left-($('.ls_FUNCtitles').width()-$(this).width())*0.5-2;
                        $('.ls_FUNCtitles').css('margin','0px');
                    }else if(c.search('ls_JIAO_l')!==-1&&isMr){
                        t=$(this).offset().top+($(this).height()-$('.ls_FUNCtitles').height())*0.5;
                        l=$(this).offset().left+$(this).width()+10;
                        $('.ls_FUNCtitles').css('margin','0px');
                    }else if(c.search('ls_JIAO_r')!==-1&&isMr){
                        t=$(this).offset().top+($(this).height()-$('.ls_FUNCtitles').height())*0.5;
                        l=$(this).offset().left-$('.ls_FUNCtitles').width()-10;
                        $('.ls_FUNCtitles').css('margin','0px');
                    }else{
                        t=$(this).offset().top+($(this).height()-$('.ls_FUNCtitles').height())*0.5;
                        l=$(this).offset().left+$(this).width();
                    }
                    $('.ls_FUNCtitles').css({'top':t+'px','left':l+'px'});
                    if($(window).width()-$('.ls_FUNCtitles').offset().left-$('.ls_FUNCtitles').width()<0){
                        $('.ls_FUNCtitles').css('left',parseInt($('.ls_FUNCtitles').css('left'))-$('.ls_FUNCtitles').width()-$(this).width()+'px').find('.ls_JIAO').attr('class','ls_JIAO ls_JIAO_r');
                    }
                }).delegate('[ls-titles],.ls_FUNCtitles','mouseleave',function(){
                    $('.ls_FUNCtitles').fadeOut(300,function(){$(this).remove();});
                }).delegate('.ls_FUNCtitles','mouseenter',function(){
                    $(this).stop().fadeIn(function(){
                        $(this).animate({opacity:1});
                    });
                })
            })
        },
        laterFunc:{
            bgLater:function(z,o,c,b,fn,data){
                var data=arguments[arguments.length-1];
                (!(typeof data=='object'&&data.lock))&&$("html,body").addClass("oYH");
                if($('.IDbgLaterOnly').length>0){ $('.IDbgLaterOnly').remove(); }
                var d=b?b:$('.TancengLaterDOMs:visible').last(),z = z ? z : d.css('z-index') , o = o ? o : 0.6 , c = c ? c : '#000000';
                d.before('<div class="IDbgLaterOnly shadow" style="background-color:'+c+';opacity:'+o+';filter:Alpha(opacity='+(o*100)+');z-index:'+(z?z:0)+';"></div>');
                $('.IDbgLaterOnly').click(function(e) {
                    if(d.hasClass('IDbgLaterOnlyClose')){
                        if(d.find('.IDlaterTitle').length>0){
                            d.find('.IDlaterTitle').next('.laterCloseBtn').click();
                        }else{
                            $(this).stop().fadeOut(200);
                            (!data.lock)&&$("html,body").removeClass("oYH");
                            fn&&fn();
                        }
                    };
                });
            },
            setPt:function(ob){
                $(ob).css({'marginTop':-($(ob).outerHeight())*0.5+'px','marginLeft':-($(ob).outerWidth())*0.5+'px'})
            },
            open:function(data,cs){
                if(!data){ return false; }
                var dataType=typeof(data),_t=this,html,width=(cs&&cs.width) ? cs.width : 420,height=(cs&&cs.height) ? cs.height : 'auto',z=(cs&&cs.zindex) ? cs.zindex : ($('.TancengLaterDOMs:visible').length>0 ? $('.TancengLaterDOMs:visible').last().css('z-index') : 1000),cssname=(cs&&cs.cssname) ? cs.cssname : '',BlankClose=(cs&&cs.BlankClose)?'IDbgLaterOnlyClose':'';
                if(dataType=='string'){
                    if( data.search(/^(http(s)?\:\/\/)/)==0 ){
                        var height=height=='auto'?300:height,title=(cs&&cs.title) ? cs.title : '',IFname=(cs&&cs.name) ? cs.name : 'ifnametanceng';
                        html=$('<div class="TancengLaterDOMs '+BlankClose+' instruct_tck '+cssname+'" style="width:'+(width-80)+'px;height:'+(height=='auto'?'auto':((height-80)+'px'))+';z-index:'+z+';"><h2 class="IDlaterTitle">'+title+'</h2><a class="pjs_icon circle_close laterCloseBtn" href="javascript:;"></a><div class="ins_tck_content cl" style="margin:16px -40px -35px;"><iframe width="100%" height="'+(height-61)+'" frameborder="0" scrolling="auto" src="" name="'+IFname+'"></iframe></div></div>');
                    }else{
                        var title=(cs&&cs.title) ? cs.title : '',mT=title==''?' ':' style="margin-top:16px;" ';
                        html=$('<div class="TancengLaterDOMs '+BlankClose+' instruct_tck '+cssname+'" style="width:'+(width-80)+'px;height:'+(height=='auto'?'auto':((height-80)+'px'))+';z-index:'+z+';"><h2 class="IDlaterTitle">'+title+'</h2><a class="pjs_icon circle_close laterCloseBtn" href="javascript:;"></a><div class="ins_tck_content cl" '+mT+'>'+data+'</div></div>');
                    }
                }else if(dataType=='object'){
                    var title=(cs&&cs.title) ? cs.title : '';
                    html=$('<div class="TancengLaterDOMs '+BlankClose+' instruct_tck '+cssname+'" style="width:'+(width-80)+'px;height:'+(height=='auto'?'auto':((height-80)+'px'))+';z-index:'+z+';"><h2 class="IDlaterTitle">'+title+'</h2><a class="pjs_icon circle_close laterCloseBtn" href="javascript:;"></a><div class="ins_tck_content cl" '+mT+'>'+data.html()+'</div></div>');
                }
                html.appendTo('body');
                if($('body').find('.TancengLaterDOMs:visible iframe').length>0){
                    $('body').find('.TancengLaterDOMs:visible iframe').last()[0].src=data;
                }
                _t.setPt(html);
                html.delegate('.laterCloseBtn','click',function(){
                    funcData.laterFunc.close(cs);
                })
                this.bgLater(z);
                cs&&cs.openedFn&&eval(cs.openedFn);
            },
            close:function(cs){
                var _z=this,o=$('.IDbgLaterOnly'),obj=o.next();
                cs&&cs.obj&&(obj=cs.obj,o=cs.obj.prev('.IDbgLaterOnly'));
                if(cs && cs.before){ cs.before(); }
                obj.fadeOut(200,function(){
                    $(this).remove();
                    o.remove();
                    if($('.TancengLaterDOMs:visible').length>0){ _z.bgLater(); };
                });
                $("html,body").removeClass("oYH");
                if(cs && cs.after){ cs.after(); }
            },
            closeF:function(cs){ //iframe内部点击关闭弹层方法  onclick="function(){ funcData.laterFunc.closeF(); }";
                if(cs && cs.before){ cs.before(); }
                window.parent.funcData.laterFunc.close();
                if(cs && cs.after){ cs.after(); }
            }
        },
        alertRe:function(txt,obj){
            //funcData.alertRe('您确认提交借款申请？',{img:'image/chenggong_image01.jpg',title:false,btn:'<a href="javascript:;" class="btn btn-lan dIbI laterCloseBtn"><i class="ico2 ICOfS01_40 ICOp"></i>确认</a><a href="javascript:;" class="btn btn-lan dIbI laterCloseBtn"><i class="ico2 ICOfS01_52 ICOp"></i>取消</a>'});
            var ob={title:'<i class="ico2 ICOtanhao ICOp"></i>提示',tageName:'p',img:false,btn:false,timeout:false,style:'',after:function(){},before:function(){}},ins_tck_content='ins_tck_contentP01',img='',cssname='',btn='';
            obj&&($.extend(ob,obj));
            ob.img&&(ins_tck_content='ins_tck_contentP02',img='<img src="'+ob.img+'" />');
            ob.cssname&&(cssname=cssname+' '+ob.cssname);
            (!ob.title)&&(cssname=cssname+' noTitle');
            ob.btn&&(btn='<div class="ins_tck_Btns tAcen">'+ob.btn+'</div>');
            funcData.laterFunc.open('<div class="'+ins_tck_content+'">'+img+'<'+ob.tageName+' style="'+ob.style+'">'+txt+'</'+ob.tageName+'></div>'+btn,{title:ob.title,cssname:'TCcommonFty p0I'+cssname,after:ob.after,before:ob.before});
            (ob.timeout)&&(setTimeout(function(){
                $('.TancengLaterDOMs:visible').children('.laterCloseBtn').click();
            },ob.timeout));
        },
        CheckBoxSty:function(o,k){ //CheckBox/radio 美化readonly
            var o=o?o:$('[type=radio],[type=checkbox]'),k=k;
            o.filter(':checked').closest('label').addClass('on');
            o.click(function(){
                if($(this).is('[readonly]')){ return false; };
                if($(this).is(':checked')){
                    if($(this).attr('type')=='radio'||k){
                        $('[name='+$(this).attr('name')+']').closest('label').removeClass('on');
                    }
                    $(this).closest('label').addClass('on');
                }else{
                    $(this).closest('label').removeClass('on');
                }
            })
        },
        CheckReadonly:function(){ //radio / checkbox 设置为只读不可更改
            var box=$('[type=radio][readonly],[type=checkbox][readonly]');
            box.filter(':checked').addClass('IDcheckedLock');
            box.click(function(e) {
                box.removeAttr('checked').filter('.IDcheckedLock').attr('checked','checked');
            });
        },
        verifica:{
            //表单验证
            regArr:{
                t:'\\~\\`\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\-\\=\\{\\}\\|\\[\\]\\\\\:\\"\\;\\<\\>\\?\\,\\.\\/',
                Y:'',
                Yinput:[/.+/g,'不能为空'],
                userName:[/^1[3-9]\d{9}$/g,'不能为空'],
                zs_name:[/^.+$/g,'不能为空'],
                Mobile:[/^(\+\d{2}(\s)?)?1[3-9]\d{9}$/g,'手机号码不正确'],
                password:[/^(?![\d]+$)(?![A-z]+$)[\dA-z\~\`\!\@\#\$\%\^\&\*\(\)\_\+\-\=\{\}\|\[\]\\\:\"\;\<\>\?\,\.\/\']{6,20}$/g,'密码须为6-20位英文字母、数字和符号两种以上组合'],
                password1:[/^(?![\d]+$)(?![A-z]+$)[\dA-z\~\`\!\@\#\$\%\^\&\*\(\)\_\+\-\=\{\}\|\[\]\\\:\"\;\<\>\?\,\.\/\']{6,20}$/g,'密码须为6-20位英文字母、数字和符号两种以上组合'],
                jiaoyiPass:[/^(?![\d]+$)(?![A-z]+$)[\dA-z]{8,20}$/g,'必须为不少于8位的数字和字母'],
                l6_20:[/^.{6,20}$/g,'密码须保证在 6 到 20 位字符'],
                yqm:[/^(([0-9]{7})|(1[3-9]\d{9}))$/g,'邀请码须为7位数字或手机号码'],
                IDcard:[/^(([1-9][0-9]{7}(([0][1-9])|([1][0-2]))(([0][1-9])|([12][0-9])|([3][01]))[0-9]{3})|([1-9][0-9]{5}[1-9][0-9]{3}(([0][1-9])|([1][0-2]))(([0][1-9])|([12][0-9])|([3][01]))[0-9]{3}[0-9X]))$/g,'身份证号不正确'],
                yh_card:[/^\d+$/g,'银行卡号不正确'],
                Email:[/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/g,'邮箱号码不正确'],
                floatNum:[/^(\d+(\.\d{1,2})?)$/g,'只能为数字，允许两位小数'],
                FloatZ:[/^(\d+(\.\d{1,2})?)$/g,'只能为数字，允许两位小数'],
                FloatZ100:[/^((\d{1,2}(\.\d{1,2})?)|(100(\.00)?))$/g,'需为100以内数字，允许两位小数'],
                Int:[/^\d{1,}$/g,'只能为整数'],
                telormob:[/^(\d{3,4}(\-|\s)?\d{6,8})|((\+\d{2}(\s)?)?1[3-9]\d{9})$/g,'联系电话不正确,需为“区号 - 电话”或“手机号”'],
                MorE:[/^((1[3-9]\d{9})|((\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})))$/g,'邮箱号码不正确'],
                M1000_00:[/^(\d{1,8}(\.\d{1,2})?)$/,'数值必须小于1亿'],
                sel:['请填选该项信息']
            },
            reging:function(e,kn1){
                //验证单元
                var regdata=$(e).attr('data-regdata').split(","),I=0,lock=0;
                if(regdata[0]=='Y'){ lock=1;I=1; }
                switch(true){
                    case $(e).is('input[type=radio],input[type=checkbox]'):
                        var name=$(e).attr('name'),l=$('[name='+name+']:checked').length,RadioO=$('[name='+name+'][data-regdata]');
                        switch(true){
                            case lock==0:
                                //非必填
                                RadioO.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                                RadioO.closest('label').find('*').add($(e).closest('label')).removeClass('IDfalseBorderS');
                                break;
                            case l>0:
                                //正确
                                RadioO.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                                RadioO.closest('label').find('*').add($(e).closest('label')).removeClass('IDfalseBorderS');
                                if(RadioO.is('data-true-call-back')){ eval(RadioO.attr('data-true-call-back')) }; //验证通过回调
                                break;
                            default:
                                //错误
                                if(!RadioO.next().hasClass('LsjIDtagAfters')){
                                    RadioO.after('<div class="LsjIDtagAfters" style="display:inline-block;"></div>');
                                    $('.LsjIDtagAfters').parent().css('position','relative');
                                };
                                RadioO.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                                var wrInfo = RadioO.is('[data-info-'+regdata[1]+']') ? RadioO.attr('data-info-'+regdata[1]) : this.regArr[regdata[1]];
                                RadioO.next('.LsjIDtagAfters').prepend('<code class="LsjVerificaFalseS">'+wrInfo+'</code>');
                                var l=RadioO.offset().left+5-RadioO.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').offset().left,t=RadioO.offset().top-RadioO.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').offset().top,h=RadioO.closest('label').height();
                                RadioO.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').css({'marginLeft':3+'px','marginTop':h+'px'});
                                RadioO.closest('label').find('*').add(RadioO.closest('label')).addClass('IDfalseBorderS');
                                if(kn1){ if($(e).is(':hidden')){ $(document).scrollTop($(e).closest(':visible').offset().top-10); }else{ $(document).scrollTop($(e).offset().top-10); } }
                                return false;
                        }
                        break;
                    case $(e).is('input:not([type=radio],[type=checkbox]),textarea'):
                        if(lock==1 || ($(e).val()!='' && $(e).is(':visible'))){
                            for(I;I<regdata.length;I++){
                                if(regdata[I] in this.regArr){
                                    if(typeof(this.regArr[regdata[I]])=='function'){
                                        this.regArr[regdata[I]]=this.regArr[regdata[I]]();
                                    };
                                    if($.trim($(e).val()).search(this.regArr[regdata[I]][0])==-1){
                                        if(!$(e).next().hasClass('LsjIDtagAfters')){
                                            $(e).after('<div class="LsjIDtagAfters" style="display:inline-block;"></div>');
                                            $('.LsjIDtagAfters').parent().css('position','relative');
                                        };
                                        $(e).next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                                        var wrInfo = $(e).is('[data-info-'+regdata[I]+']') ? $(e).attr('data-info-'+regdata[I]) : this.regArr[regdata[I]][1];
                                        $(e).next('.LsjIDtagAfters').prepend('<code class="LsjVerificaFalseS">'+wrInfo+'</code>');
                                        var l=$(e).offset().left+5-$(e).next('.LsjIDtagAfters').find('.LsjVerificaFalseS').offset().left,t=$(e).offset().top-$(e).next('.LsjIDtagAfters').find('.LsjVerificaFalseS').offset().top,h=$(e).closest('label').height();
                                        $(e).next('.LsjIDtagAfters').find('.LsjVerificaFalseS').css({'marginLeft':3+'px','marginTop':h+'px'});
                                        $(e).closest('label').find('*').add($(e).closest('label')).addClass('IDfalseBorderS');
                                        if(kn1){ if($(e).is(':hidden')){ $(document).scrollTop($(e).closest(':visible').offset().top-10); }else{ $(document).scrollTop($(e).offset().top-10); } }
                                        if($(e).is('[data-false-call-back]')){ eval($(e).attr('data-false-call-back')); }; //验证通过回调
                                        return false;
                                        break;
                                    }else{
                                        $(e).next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                                        $(e).closest('label').find('*').add($(e).closest('label')).removeClass('IDfalseBorderS');
                                        if($(e).is('[data-true-call-back]')&&I==regdata.length-1){ eval($(e).attr('data-true-call-back')); }; //验证通过回调
                                    }
                                }
                            }
                        }else{
                            $(e).next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                            $(e).closest('label').find('*').add($(e).closest('label')).removeClass('IDfalseBorderS');
                        }
                        break;
                    case $(e).is('select'):
                        var sel_key=$(e).find('option').first().val() , selE= $(e).next().hasClass('SelectBoxAFuncREL') ? $(e).next() : $(e);
                        if($(e).is('[sel-default]')){ sel_key==$(e).attr('sel-default') };
                        if(lock==1){
                            if($(e).find(':selected').val()==sel_key){
                                if(!selE.next().hasClass('LsjIDtagAfters')){
                                    selE.after('<div class="LsjIDtagAfters" style="display:inline-block;"></div>');
                                    $('.LsjIDtagAfters').parent().css('position','relative');
                                };
                                selE.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                                var wrInfo = $(e).is('[data-info-'+regdata[1]+']') ? $(e).attr('data-info-'+regdata[1]) : this.regArr[regdata[1]];
                                selE.next('.LsjIDtagAfters').prepend('<code class="LsjVerificaFalseS">'+wrInfo+'</code>');
                                var l=selE.offset().left+5-selE.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').offset().left,t=selE.offset().top-selE.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').offset().top,h=selE.closest('label').height();
                                selE.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').css({'marginLeft':3+'px','marginTop':h+'px'});
                                selE.closest('label').find('*').add(selE.closest('label')).addClass('IDfalseBorderS');
                                if(kn1){ if($(e).is(':hidden')){ $(document).scrollTop($(e).closest(':visible').offset().top-10); }else{ $(document).scrollTop($(e).offset().top-10); } }
                                return false;
                            }else{
                                selE.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                                selE.closest('label').find('*').add(selE.closest('label')).removeClass('IDfalseBorderS');
                                if(selE.is('data-true-call-back')){ eval(selE.attr('data-true-call-back')) }; //验证通过回调
                            }
                        }else{
                            selE.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                            selE.closest('label').find('*').add(selE.closest('label')).removeClass('IDfalseBorderS');
                        }
                        break;
                }
                return true;
            },
            regINIT:function(e){
                //功能绑定
                var oneObj = e ? $(e) : $('body'),_t=funcData;
                oneObj.delegate('input[data-regdata]:not([type=radio],[type=checkbox],[type=button],[type=image]),textarea','blur',function(){
                    if($(this).val()!=''){ _t.verifica.reging($(this)); };
                })
                oneObj.delegate('select[data-regdata]','change',function(){
                    _t.verifica.reging($(this));
                })
                oneObj.delegate('[type=radio],[type=checkbox]','click',function(){
                    if($('[name='+$(this).attr('name')+'][data-regdata]').length!=0){
                        _t.verifica.reging($('[name='+$(this).attr('name')+'][data-regdata]'));
                    }
                })
            },
            submitReg:function(e,sT){
                //表单提交验证结果
                var oneObj = e ? $(e) : $('body'),CursorKey=true,_t=funcData,sT=typeof(sT)!='undefined'?sT:1;
                oneObj.find('[data-regdata]:enabled').each(function(){
                    if(!_t.verifica.reging($(this),sT)){ CursorKey=false; }
                })
                return CursorKey;
            },
            MustReg:function(e,k){
                //规则填写变更
                if(k==1){
                    e.each(function(){
                        var O=$(this).is('[data-regdata]') ? $(this) : $(this).find('[data-regdata]');
                        O.each(function(index, element) {
                            var regda=$(this).attr('data-regdata').split(",");
                            if(regda[0]!='Y'){
                                $(this).attr('data-regdata','Y,'+$(this).attr('data-regdata'));
                            };
                        });
                    })
                }else{
                    e.each(function(){
                        var O=$(this).is('[data-regdata]') ? $(this) : $(this).find('[data-regdata]');
                        O.each(function(index, element) {
                            var regda=$(this).attr('data-regdata').split(",");
                            if(regda[0]=='Y'){
                                $(this).attr('data-regdata',$(this).attr('data-regdata').replace(/^Y\,/,""));
                            };
                        });
                    })
                }
            },
            resetReg:function(e){
                //还原验证项
                var e=$(e).is('[data-regdata]') ? $(e) : $(e).find('[data-regdata]');
                e.each(function(){
                    var selE=$(this);
                    selE.next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                    selE.closest('label').find('*').add(selE.closest('label')).removeClass('IDfalseBorderS');
                })
            },
            errInfo:function(e,info,kn1){
                if(!$(e).next().hasClass('LsjIDtagAfters')){
                    $(e).after('<div class="LsjIDtagAfters" style="display:inline-block;"></div>');
                    $('.LsjIDtagAfters').parent().css('position','relative');
                };
                $(e).next('.LsjIDtagAfters').find('.LsjVerificaFalseS').remove();
                var wrInfo =info;
                $(e).next('.LsjIDtagAfters').prepend('<code class="LsjVerificaFalseS">'+wrInfo+'</code>');
                var l=$(e).offset().left+5-$(e).next('.LsjIDtagAfters').find('.LsjVerificaFalseS').offset().left,t=$(e).offset().top-$(e).next('.LsjIDtagAfters').find('.LsjVerificaFalseS').offset().top,h=$(e).closest('label').height();
                $(e).next('.LsjIDtagAfters').find('.LsjVerificaFalseS').css({'marginLeft':3+'px','marginTop':h+'px'});
                $(e).closest('label').find('*').add($(e).closest('label')).addClass('IDfalseBorderS');
                if(kn1){ if($(e).is(':hidden')){ $(document).scrollTop($(e).closest(':visible').offset().top-10); }else{ $(document).scrollTop($(e).offset().top-10); } }
                //if($(e).is('[data-false-call-back]')){ eval($(e).attr('data-false-call-back')); }; //验证通过回调
            }
        },
        eChange:{
            //输入内容控制
            COM:function(e,c){
                var e=e ? ($(e).is('[txtype]')?$(e):$(e).find('[txtype]')) : $('[txtype]');
                var objThis=this,domInput=$('<input type="text" />')[0],bind_name = ('oninput' in domInput) ? 'input' :  ( !$.support.leadingWhitespace ? 'keyup blur' : (('onpropertychange' in domInput) ? 'propertychange' : 'keyup blur') );
                bind_name=c?c:bind_name;
                e.bind(bind_name,function(){
                    var regs,wz=$(this).is(':focus')&&funcData.getLocation($(this)[0]);
                    if($(this).attr('txtype') in objThis.regData){
                        regs=objThis.regData[$(this).attr('txtype')];
                    }else{
                        regs=eval($(this).attr('txtype'));
                    }
                    if($(this).is('[input-func-before]')){ eval($(this).attr('input-func-before')); }
                    if(typeof(regs)==='function'){
                        var matchArr=regs($(this).val());
                    }else{
                        var matchArr=$(this).val().match(regs);
                        matchArr=(matchArr ? matchArr : '')[0];
                    }
                    $(this).val(matchArr);
                    if($(this).is('[input-func-after]')){ eval($(this).attr('input-func-after')); }
                    $(this).is(':focus')&&funcData.setLocation($(this)[0],wz);
                })
            },
            regData:{
                'NaZH':/[0-9a-zA-Z\u3400-\u9fbb\ue815-\ue864\uf900-\ufa2d\ufa30-\ufa6a\-\#\s]*/, //中英文数字-# 可数字字符开头
                'aZNH':/([a-zA-Z\u3400-\u9fbb\ue815-\ue864\uf900-\ufa2d\ufa30-\ufa6a]+[1-9a-zA-Z\u3400-\u9fbb\ue815-\ue864\uf900-\ufa2d\ufa30-\ufa6a\-\#\s]*)?/, //中英文数字-# 非数字字符开头
                'aZH_s':/[a-zA-Z\u3400-\u9fbb\ue815-\ue864\uf900-\ufa2d\ufa30-\ufa6a\s]*/, //中英文
                'no_limit':/([0-9a-zA-Z\u3400-\u9fbb\ue815-\ue864\uf900-\ufa2d\ufa30-\ufa6a][0-9a-zA-Z\D\n\r]{0,})?/, //任意字符
                'no_limit_15':/([0-9a-zA-Z\u3400-\u9fbb\ue815-\ue864\uf900-\ufa2d\ufa30-\ufa6a][0-9a-zA-Z\D\n\r]{0,14})?/, //15个任意字符
                'no_limit_200':/([0-9a-zA-Z\u3400-\u9fbb\ue815-\ue864\uf900-\ufa2d\ufa30-\ufa6a][0-9a-zA-Z\D\n\r]{0,199})?/, //200个任意字符
                'no_limit_100':/([0-9a-zA-Z\u3400-\u9fbb\ue815-\ue864\uf900-\ufa2d\ufa30-\ufa6a][0-9a-zA-Z\D\n\r]{0,99})?/, //100个任意字符
                'Nmax50W':/(500000|(^[1-4][0-9]{0,5})|(^[1-9]([0-9]{0,4})))/, //50万以内的数字
                'tel_area':/\d{0,4}/, //电话区号
                'tel_code':/\d{0,8}/, //电话号码
                'tel_move':/(1([3-9]\d{0,9})?)?/, //手机号码
                'Kik':/[0-9a-zA-Z\_\-\*\.]*/, //微信号码
                'Int':/([0-9]+)?/, //小数
                'FloatZ':/([0-9]+(\.)?[0-9]{0,2})?/, //小数
                'FloatZ100':/(100|([0-9]{1,2}(\.[0-9]{0,2})?))?/, //100以内小数
                'FloatZ5W':/(50000|([1-4]?[0-9]{0,4}(\.[0-9]{0,2})?))?/,
                'FloatZ100W':/[1-9]?\d{0,6}(\.\d{0,2})?/,
                'FloatZ5W':/50000|[1-4]?[0-9]{0,4}(\.\d{0,2})?/,
                'FloatZ1000W':/[1-9]?\d{0,7}(\.\d{0,2})?/,
                'IntZ':/[1-9]\d*/,
                'IntZ31':/([4-9])|([1-2][0-9]?)|(3[01]?)/,
                'IDcard':/\d{0,17}[xX0-9]?/,
                'aZ09':/[a-zA-Z0-9]{0,20}/,
                'M1000_00':/\d{0,8}(\.\d{0,2})?/,
                'W100_00':/1000000|(\d{0,6}(\.\d{0,2})?)/,
                'lenFor':function(str){ var byteLen=0,len=str.length,txt='',n=400; if(str){ for(var i=0; i<len; i++){ if(n>byteLen){ if(str.charCodeAt(i)>255){ byteLen += 2; txt=txt+str[i]; } else{ byteLen++; txt=txt+str[i]; };}else{ break; }; }; return txt; } else{ return ''; };}
            }
        },
        getLocation:function(elm) {
            //获取光标位置
            if(elm.createTextRange&&document.selection) { // IE
                var range = document.selection.createRange();
                range.setEndPoint('StartToStart', elm.createTextRange());
                return range.text.length;
            } else if(typeof elm.selectionStart == 'number') { // Firefox
                return elm.selectionStart;
            }
        },
        setLocation:function(elm, n) {
            //设置光标位置
            if(n > elm.value.length){ n = elm.value.length; };
            if(elm.createTextRange) {   // IE
                var textRange = elm.createTextRange();
                textRange.moveStart('character', n);
                textRange.collapse();
                textRange.select();
            } else if(elm.setSelectionRange) { // Firefox
                elm.setSelectionRange(n, n);
                elm.focus();
            }
        },
        getSelect:function(t){
            //获取选中的内容 取不到则返回false
            var select_field = t,word='';
            if (document.selection) {
                var sel = document.selection.createRange();
                if (sel.text.length > 0) {
                    word = sel.text;
                }
            }    /*ie浏览器*/
            else if (select_field.selectionStart || select_field.selectionStart == '0') {
                var startP = select_field.selectionStart;
                var endP = select_field.selectionEnd;
                if (startP != endP) {
                    word = select_field.value.substring(startP,endP);
                }
            }   /*标准浏览器*/
            if(word==''){ return false;}else{ return word; }
        },
        SecondsCD:function (o,s,max_s) {
            //秒倒计时
            var wait=s?s:0,max_s=max_s?max_s:15;
            if (wait == 0||o.is('[stop-cd]')) {
                o.removeAttr('stop-cd').removeClass('on').find('a').show().siblings('span').hide();
                wait = max_s;
            } else {
                //funcData.verifica.resetReg(o.siblings('label'));
                o.addClass('on').find('a').hide().siblings('span,em').show();
                o.find('span').html(wait+'秒后重新获取');
                wait--;
                setTimeout(function() {
                    funcData.SecondsCD(o,wait,max_s);
                },1000)
            }
        },
        regMainH:function (minH,x){
            //注册类页面高度计算
            var minH=minH-x;
            var h=$(window).height()-x;
            if(h<minH){
                $('.ylogin_cont .ylog_bom').addClass('LnoFixed');
                h=minH;
            }else{
                $('.ylogin_cont .ylog_bom').removeClass('LnoFixed');
            }
            return h;
        },
        passwordLevel:function (t){
            //密码等级
            var v=$(t).val(),i=0,o=$(t).siblings('.ICOpassword_level');
            if(v.length<6){ return false; }
            if(v.search(/[0-9]/)!=-1){i++;}
            if(v.search(/[A-z]/)!=-1){i++;}
            if(i>1){
                if(v.length>16){i++;}
            }
            if(i==0){
                o.removeClass('ID1','ID2','ID3').addClass('ID0');
            } else if(i==1){
                o.removeClass('ID0','ID2','ID3').addClass('ID1');
            } else if(i==2){
                o.removeClass('ID0','ID1','ID3').addClass('ID2');
            } else if(i<=3){
                o.removeClass('ID0','ID1','ID2').addClass('ID3');
            }

        },
        addSelect:function (ob){
            //补css
            var ob=ob?$(ob):$('[add-select]');
            ob.each(function(){
                var ch=$(ob).attr('add-select');
                $(this).children(ch+':first').addClass('CH-First');
                $(this).children(ch+':last').addClass('CH-Last');
                $(this).children(ch+':even').addClass('CH-Even');
                $(this).children(ch+':odd').addClass('CH-Odd');
            })
        },
        CountdownFunc : function (ts,fn){
            //参数：  ts:停止时间、fn:{ step:渲染函数 ， end:结束回调函数 }
            this.Mdate= +(new Date(ts));//'2015-12-12 17:21:49' //加号的作用是把日期转成时间戳
            this.fn=fn;
            this.key=0;
            this.add0=function(n){
                var n=n;
                if(n<10){
                    n='0'+n;
                }
                return n;
            };
            this.setFunc=function(){
                //计算当前时间 到目标时间的时间差
                var timeS=timeSkey=parseInt((this.Mdate-(+new Date()))/1000);
                //下面是计算天数、小时数、分钟数、秒数
                var T,H,M,S;
                T=parseInt(timeS/86400); timeS=timeS%86400;
                H=this.add0(parseInt(timeS/3600)); timeS=timeS%3600;
                M=this.add0(parseInt(timeS/60)); timeS=timeS%60;
                S=this.add0(parseInt(timeS));
                this.fn.step({t:T,h:H,m:M,s:S});

                if(!timeSkey||timeSkey<=0){
                    clearInterval(this.start);
                    if(this.key==1){
                        this.fn.end && this.fn.end();
                    }
                    this.key=0;
                    return false;
                }
            }
            //定义对象私有计时变量
            this.start=undefined;
            //开始倒计时;
            this.open=function(){
                var timeS=parseInt((this.Mdate-(+new Date()))/1000);
                if(timeS&&timeS>0){
                    this.start=setInterval($.proxy(this.setFunc, this), 1000);
                    this.key=1;
                }
            };
            //备用：临时停止倒计时
            this.stop=function(){
                clearInterval(this.start);
                this.key=0;
            }
        },
        FocusPicSeeBox:function(){
            //焦点图 图片查看小组件
            ;(function(){
                function G(s){ return document.getElementById(s);
                }

                function getStyle(obj, attr){
                    if(obj.currentStyle){
                        return obj.currentStyle[attr];
                    }else{
                        return getComputedStyle(obj, false)[attr];
                    }
                }

                function Animate(obj, json){
                    if(obj.timer){
                        clearInterval(obj.timer);
                    }
                    obj.timer = setInterval(function(){
                        for(var attr in json){
                            var iCur = parseInt(getStyle(obj, attr));
                            iCur = iCur ? iCur : 0;
                            var iSpeed = (json[attr] - iCur) / 5;
                            iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed);
                            obj.style[attr] = iCur + iSpeed + 'px';
                            if(iCur == json[attr]){
                                clearInterval(obj.timer);
                            }
                        }
                    }, 30);
                }

                var oPic = G("picBox");
                var oList = G("listBox");

                var oPrev = G("prev");
                var oNext = G("next");
                var oPrevTop = G("prevTop");
                var oNextTop = G("nextTop");

                var oPicLi = oPic.getElementsByTagName("li");
                var oListLi = oList.getElementsByTagName("li");
                var len1 = oPicLi.length;
                var len2 = oListLi.length;

                var oPicUl = oPic.getElementsByTagName("ul")[0];
                var oListUl = oList.getElementsByTagName("ul")[0];
                var w1 = oPicLi[0].offsetWidth;
                var w2 = oListLi[0].offsetHeight+16;

                oPicUl.style.width = w1 * len1 + "px";
                oListUl.style.height = w2 * len2 + "px";

                var index = 0;

                var num = 4;
                var num2 = Math.ceil(num / 2);

                function Change(){

                    Animate(oPicUl, {left: - index * w1});

                    if(index < num2){
                        Animate(oListUl, {top: 0});
                    }else if(index + num2 <= len2){
                        Animate(oListUl, {top: - (index - num2 + 1) * w2});
                    }else{
                        Animate(oListUl, {top: - (len2 - num) * w2});
                    }

                    for (var i = 0; i < len2; i++) {
                        oListLi[i].className = "";
                        if(i == index){
                            oListLi[i].className = "on";
                        }
                    }
                }

                oNextTop.onclick = oNext.onclick = function(){
                    index ++;
                    index = index == len2 ? 0 : index;
                    Change();
                }

                oPrevTop.onclick = oPrev.onclick = function(){
                    index --;
                    index = index == -1 ? len2 -1 : index;
                    Change();
                }

                for (var i = 0; i < len2; i++) {
                    oListLi[i].index = i;
                    oListLi[i].onclick = function(){
                        index = this.index;
                        Change();
                    }
                }

            })();
        },
        oneClick : function(){
            //防按钮重复点击 需要给按钮添加属性 one-click 恢复方法 按钮.reClick()
            if($('.on-oneClickTrue').length>0){ return false; }
            $('body').addClass('on-oneClickTrue');
            $(document).delegate('[one-click=true]','click',function(){
                var d=$(this).clone(),dCss=$(this).is('[one-click-css]')?$(this).attr('one-click-css'):'btnDisabled';
                d.removeAttr('one-click').removeAttr('onclick').addClass(dCss);
                $(this).after(d).hide();
            })
            $.fn.reClick=function(e){
                var e=typeof(e)=='undefined'?0:e;
                if(e>20){ return false; }
                var th=$(this);
                if(th.is(':hidden')){
                    th.show().next('.btnDisabled').remove();
                }else{
                    setTimeout(function(){th.reClick(e+1);},10);
                }
            }
            $.fn.delClick=function(){
                var d=$(this).clone(),dCss=$(this).is('[one-click-css]')?$(this).attr('one-click-css'):'btnDisabled';
                d.removeAttr('one-click').removeAttr('onclick').addClass(dCss);
                $(this).after(d).hide();
            }
        },
        toEvent:function(){if(document.all){return window.event;};var func=this.toEvent.caller;while(func!=null){ var arg0=func.arguments[0];if(arg0){if(arg0.constructor==Event){return arg0;};};func=func.caller;};return null; },
        //导航游标
        navMainS:function(box,re){ if(box.find('.on').length==0){ return false; }; if(re){ var dom=$(box).find('.oldOn'),w=dom.outerWidth(),l=dom.offset().left-$(box).offset().left; $(box).find('li.IDyoubiaoN01').css({'left':l+'px','width':w+'px'}); return false; } $(box).css('position','relative'); var youbiao=$('<li class="IDyoubiaoN01" style="position:absolute;bottom:0;left:0;background:#2577E3;height:3px;overflow:hidden;margin:0;padding:0;z-index:0"></li>'),onDom=$(box).children('.on'),onL=onDom.offset().left-$(box).offset().left; youbiao.css({'left':onL+'px','width':onDom.outerWidth()+'px'}); $(box).append(youbiao); var lastLi=$(box).find('li.IDyoubiaoN01'),set=null; $(box).find('li:not(:last)').mouseenter(function(){ clearTimeout(set); var w=$(this).outerWidth(),l=$(this).offset().left-$(box).offset().left; lastLi.stop().animate({'left':l+'px','width':w+'px'},{easing:'easeOutQuint',duration: 300,complete:function(){}}); }).mouseleave(function(){ var dom=$(box).find('.oldOn'),w=dom.outerWidth(),l=dom.offset().left-$(box).offset().left; set=setTimeout(function(){ lastLi.stop().animate({'left':l+'px','width':w+'px'},{easing:'easeOutQuint',duration: 300,complete:function(){}}); },500) })},
        //保障信息图标介绍信息定位
        baozhangList:function(listOld,hit){
            /*if($(list).length==0){ return false;};
             var hit=hit?hit:'.BaoZhangIcoList';
             $(document).delegate(hit+' i','mouseenter',function(){
             var onList=$(list).children(':eq('+($(this).index())+')'), EmLw = ($(this).width()/2)-5;
             if($(this).is('[val]')){ onList.find('p').html($(this).attr('val'))}
             if($(this).is('[css-list]')){ $(list).addClass($(this).attr('css-list')); };
             $(list).removeAttr('style').show().css({left:$(this).closest(hit).offset().left+'px',top:($(this).closest(hit).offset().top+30)+'px'}).children(':eq('+($(this).index())+')').show().siblings().hide();
             onList.find('em').css('left',($(this).offset().left-onList.offset().left)+EmLw+'px');
             if($(list).offset().left+$(list).outerWidth()>$(window).width()-10){
             $(list).width($(window).width()-$(list).offset().left-10);
             }
             }).delegate('.BaoZhangIcoList i','mouseleave',function(){
             if($(this).is('[css-list]')){ $(list).hide().removeClass($(this).attr('css-list')); }else{ $(list).hide(); };
             })*/

            var hit=hit?hit:'.BaoZhangIcoList';
            var list=$('<ul class="bz_tan BaoZhangContent hide_noI bz_tan_ID01"><li style="display:block;"><em class="bz_img"></em><h3></h3><p>可使用加息券或代金券</p></li></ul>').hide().appendTo('body');
            var IcoData={
                Ico_y:'XXXX银行 无条件承兑',
                Ico_y0:'深圳鼎中商业保理有限公司到期回购',
                Ico_y1:'可使用加息券或代金券',
                Ico_y2:'国企背景企业保兑',
                Ico_y3:['票据银行保管：','借款人将商业承兑汇票作为质押物，由金盈所对借款方提交的商业承兑汇票进行检验，通过招商银行进行保管，保证票据真实、有效，出借人通过平台标的出借，到期后由借款人支付借款本金和收益。'],
                Ico_y4:'深圳鼎中商业保理有限公司到期回购',
                Ico_y5:['银行存管：','金盈所与恒丰银行达成资金存管合作。恒丰银行按照法律法规规定和合同约定，履行网络借贷资金专用账户的开立与销户、资金保管、资金清算、账务核对、信息披露等职责的业务。银行资金存管确保用户网络借贷资金和金盈所自有资金分账管理，安全保管客户交易结算资金。'],
                Ico_y6:['质保专款：','金盈所从每笔借款服务费中提取20%，存入恒丰银行专户，作为质保服务专款。专户资金由恒丰银行进行存管，当逾期发生时，使用质保服务专款垫付，防范出借风险，保障资金安全。为保证平台履行能力，金盈所将公开披露并公示该项资金的金额。'],
                Ico_y7:['合法签章：','金盈所携手CFCA，对用户的网络出借提供数字证书及合法电子印章服务。电子印章是传统印章的印迹和数字证书相结合的产物。通过电子印章，出借人与金盈所签署的每一份电子版合同或协议都将经过技术加密，法律效力等同于手写签名或盖章的纸质合同或协议，从而保证了出借人的利益。'],
                Ico_y8:['40道风控：','金盈所拥有成熟、严谨的风险控制评估管理体系，采用与银行等大型金融机构一致的风控审查标准，通过环环相扣的八大模块、四十道流程，全方位把控项目风险，保障出借人的资金安全。']
            }
            $(document).delegate(hit+' i','mouseenter',function(){
                var onList=$(list).children('li'), EmLw = ($(this).width()/2)-5,key=$(this).attr('class').match(/Ico_y\d*/g);
                if(key){key=key[0]}else{return false;}
                if($(this).is('[val]')){ onList.find('p').html($(this).attr('val')).prev('h3').hide(); }else{
                    if(typeof(IcoData[key])=='string'){
                        onList.find('p').html(IcoData[key]).prev('h3').hide();
                    }else{
                        onList.find('p').html(IcoData[key][1]).prev('h3').html(IcoData[key][0]).show()
                    }
                }
                if($(this).is('[css-list]')){ $(list).addClass($(this).attr('css-list')); };
                var hit_left=$(this).closest(hit).offset().left;
                $(list).removeAttr('style').css({left:hit_left+'px',top:($(this).closest(hit).offset().top+30)+'px'}).show();
                var ofL=$(list).offset().left,ouW=$(list).outerWidth(),winW=$(window).width(),newW=ouW>500?500:ouW+10 , bz_img_left=($(this).offset().left-onList.offset().left)+EmLw;
                if(newW<bz_img_left+25){
                    $(list).css('left',hit_left+(bz_img_left+25-newW)+'px');
                    onList.find('em').css({'left':newW-25+'px','marginLeft':'0px'});
                }else{
                    onList.find('em').css({'left':bz_img_left+'px','marginLeft':'0px'});
                }
                $(list).width(newW);
                if(ofL+ouW>winW-10){
                    var cha=ofL+ouW-winW+20;
                    $(list).css('margin-left','-'+cha+'px').find('em.bz_img').css('margin-left',cha+'px');
                }
            }).delegate('.BaoZhangIcoList i','mouseleave',function(){
                if($(this).is('[css-list]')){ $(list).hide().removeClass($(this).attr('css-list')); }else{ $(list).hide(); };
            })
        },
        dvType:function(){
            alert(navigator.userAgent)
            if (/android/i.test(navigator.userAgent)){ return 'android'; }
            if (/ipad|iphone|mac/i.test(navigator.userAgent)){ return 'ios'; }
            return false;
        },
        //登录弹层
        openLogin:function(c,u){
            var d=+new Date();
            $.ajax({ url: u+"?d="+d, dataType: 'html', success: function(data){
                funcData.laterFunc.open($(data).filter('#IDloginBOX'),{'cssname':'IDtcBlank '+c,width:1280,'openedFn':'documentReady()'});
            }});
        },
        //登录成功Top处理
        loginSuCallback:function(m){
            $('#welcome').html('欢迎您  <a href="/jinyingsuo-online/account/toAccount">'+m+'</a>');
            $('#notLogin').addClass('hide');$('#login').removeClass('hide');
        },
        //注册弹层
        openReg:function(c,u){
            var d=+new Date();
            $.ajax({ url: u+"?d="+d, dataType: 'html', success: function(data){
                funcData.laterFunc.open($(data).filter('#IDregBOX'),{'cssname':'IDtcBlank '+c,width:1280,'openedFn':'documentReady()'});
            }});
        },
        //加载锁定（参数0）/解锁（参数1）
        loadFn:function(){
            if(arguments[0]==1){
                var s=$(document).scrollTop(),WinH =parseInt($(window).height()/2)-30+s,DocH = $(document).height();
                var imgU="data:image/gif;base64,R0lGODlhHwAfANUhAOTk5Ly8vOjo6KqqqqKioszMzLCwsNTU1Nzc3KioqK6urqCgoNLS0ri4uPb29tjY2Obm5sbGxnBwcP///0hISJKSklZWVvj4+DIyMjY2NmhoaEZGRsTExPr6+lBQUCYmJpqamv///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH/C05FVFNDQVBFMi4wAwEAAAAh/wtYTVAgRGF0YVhNUDw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NzVCM0Y0MjI5MjE2MTFFM0ExMzc4MDY0NERCQTY5QTYiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NzVCM0Y0MjM5MjE2MTFFM0ExMzc4MDY0NERCQTY5QTYiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo3NUIzRjQyMDkyMTYxMUUzQTEzNzgwNjQ0REJBNjlBNiIgc3RSZWY6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";
                $('body').append('<div id="tipbgtipbg" style="background:#ddd url('+imgU+') no-repeat center '+WinH+'px;left: 0px; opacity: 0.6; position: absolute; top: 0px; width: 100%; z-index: 3000; height:'+DocH+'px; display: block;"></div>');
                $(window).scroll(function(){
                    if($('#tipbgtipbg').length>0){
                        $(window).scrollTop(s);
                    }
                });
            }else{
                $("#tipbgtipbg").remove();
            }
        },
        addInfo:function(data){
            var data=$.extend({cssname:'',text:'',time:1000},data);
            var box=$('<div class="autoInfoBox '+data.cssname+' on">'+data.text+'</div>');
            box.appendTo('body');
            setTimeout(function(){
                box.removeClass('on');
                setTimeout(function(){ box.addClass('on'); setTimeout(function(){box.remove();},200) },data.time)
            },100)
        },
        //弥补数组indexOf兼容
        arrInfo:function(){ if(!Array.indexOf){ Array.prototype.indexOf = function(obj){ for(var i=0; i<this.length; i++){ if(this[i]==obj){ return i; }; }; return -1; }; }; },
        baseFunc : function(data){
            var t=this;
            $(function(){
                try{ t.PingyiIcoFun($('.tel-wap-in a,.IDuserbar a'),'-20px'); }catch(err){ }; //图标划动
                try{ t.navContainerFunc(); }catch(err){ }; //导航
                try{ $("[placeholder]").placeholder();}catch(err){ }; //导航
                try{ funcData.tagFunc(); }catch(err){ }; //选项卡
                try{ funcData.oneClick(); }catch(err){ };  //屏蔽按钮频繁点击
                try{ window.newSchedule01=new funcData.Schedule01Func($('.StySchedule01'));  }catch(err){ };  //进度条
                $(window).scroll(function(){
                    try{
                        if($(window).scrollTop()<40){
                            $('.IDnav').css('top',(40-$(window).scrollTop())+'px').removeClass('IDnavSml');
                            funcData.navMainS($('.nav-main'),true);
                        }else{
                            $('.IDnav').css('top','0px').addClass('IDnavSml');
                            funcData.navMainS($('.nav-main'),true);
                        }
                        $('.IDnav').css('left',(0-$(window).scrollLeft())+'px');
                    }catch(err){ };
                }).scroll();
                try{ funcData.navMainS($('.nav-main')); }catch(err){ };  //导航游标
            })
        },
        keySubmit:function(obj){ //回车提交
            $(obj).keydown(function(e){
                if(e.keyCode==13){
                    submitForm(); //调用提交函数
                };
            });
        },
        DoScroll:function(object){ //文字列向上滚动（确定无调用地方后再删除）
            var Parent = object,First = Parent.find('li:first'),Height = First.height();
            First.animate({marginTop:-Height+'px'},600,function(){ First.css('marginTop',0).appendTo(Parent);});
        },
        NewDoScroll:function(name,object){ //文字列向上滚动
            var obj = { MinLength:1, AnimateTime:500, IntervalTime:1800}, Newobj = $.extend({},obj,object), ScrollLiNum = $('li',name).length,
                doscrollFunc = function(n){
                    var First = n.find('li:first'), Height = First.height();
                    First.animate({marginTop:-Height+'px'},Newobj.AnimateTime,function(){ First.css('marginTop',0).appendTo(n);});
                };
            cSetDoScroll = function(){ SetDoScroll = setInterval(function(){doscrollFunc(name);},Newobj.IntervalTime);}; cSetDoScroll();
            ScrollLiNum<=Newobj.MinLength&&clearInterval(SetDoScroll);
            name.mouseenter(function(){ clearInterval(SetDoScroll);}).mouseleave(function(){ cSetDoScroll();});
        },
        RightNav:function(par,Top){
            // 各栏hover事件
            var Tim = 500, _window = $(window);
            // 处理当前默认显示项
            par.each(function(i){
                if($(par[i]).hasClass("active")){
                    $(par[i]).find(".IDtispop").stop().animate({ opacity:"show", right:"59px"},Tim/2);
                    _window.scroll(function(){
                        _window.scrollTop()>50&&$(par[i]).removeClass("active").find(".IDtispop").stop().animate({ opacity:"hide", right:"70px"},0);
                    });
                };
            });
            // 鼠标触发弹出操作
            par.mouseenter(function(){
                $(this).addClass("active").find(".IDtispop").stop().animate({ opacity:"show", right:"59px"},Tim/2)
                    .end().siblings().removeClass("active").find(".IDtispop").stop().animate({ opacity:"hide", right:"70px"},0);
            }).mouseleave(function(){
                $(this).removeClass("active").find(".IDtispop").stop().animate({ opacity:"hide", right:"70px"},0);
                $(".feedback_alert").length==1&&$(".feedback_alert").text(""); //反馈意见提示层清空操作
            });
            // 返回顶部按钮消失/出现
            Top.hide();
            _window.scroll(function(){
                if(_window.scrollTop()>100){ Top.fadeIn(Tim);}else{ Top.fadeOut(Tim);}
            });
            // 返回顶部事件
            Top.click(function(){
                $('body,html').animate({scrollTop:0},Tim*2);
                return false;
            });
        },
        indexFunc : function(){
            $(function(){
                /*banner*/
                /*$('#slider').nivoSlider({
                 effect: 'fade',   // 效果
                 animSpeed: 500,        // 动画速度
                 pauseTime: 3000,       // 暂停时间
                 slices: 10           // 分为10列
                 });
                 // 设置小圆点偏移值，使居中
                 var  sliderOffset = $(".nivo-controlNav").width()*(-0.5)-16;
                 $(".nivo-controlNav").css("marginLeft",sliderOffset);*/
                //重置禁用按钮链接
                try{ funcData.aFunc(); }catch(err){ };
                //信息列表格式化
                try{
                    $('.IDjingpiaoSY li:eq(0),.IDjingpiaoSY li:eq(3),.IDjingpiaoSY li:eq(6)').addClass('first_child');
                    $('.IDjingpiaoSY li:eq(2),.IDjingpiaoSY li:eq(5),.IDjingpiaoSY li:eq(8)').addClass('last_child');
                }catch(err){ };
                //进度条即时更新
                //newSchedule01.am($(input).parent('.StySchedule01'))
                //合作伙伴
                funcData.sLeft();
                //保障图标List
                funcData.baozhangList('.BaoZhangContent');
                //公告
                //funcData.newsFunc();
            })
        }
    }

/*placeholder*/
/* class="PlaceholderSpanBox"
 解决boxSing,paddingLeft问题
 将提示置于文本框上面,用过click,和focusout关联
 */
    ;(function($){var isSupportPlaceholder=(function(){var attr="placeholder";var input=document.createElement("input");return attr in input})();var isIE=(function(){return window.attachEvent})();var isIE7=(function(){return(navigator.userAgent.indexOf("MSIE 7")!=-1)})();function holderToggle(e){if($(e).val()==''){$(e).siblings('.PlaceholderSpanBox').show()}else{$(e).siblings('.PlaceholderSpanBox').hide()}};$.placeholder=function(elem){var $elem=elem,$eP=$elem.parent();var info={};info.width=$elem.is('input')?$elem.outerWidth():$elem.outerWidth()-30;info.height=$elem.outerHeight();info.lineHeight=$elem.is('input')?(Math.max((parseInt($elem.css("lineHeight"),10)||0),info.height)+"px"):(parseInt($elem.css("lineHeight"),10)+"px");info.fontSize=$elem.css("fontSize");info.textIndent=$elem.css("textIndent");info.left=parseInt($elem.offset().left-$eP.offset().left-parseInt($eP.css('border-left-width'))+parseInt($elem.css('margin-left')))+'px';info.top=parseInt($elem.offset().top-$eP.offset().top-parseInt($eP.css('border-top-width'))+parseInt($elem.css('margin-top')))+'px';info.paddingTop=parseInt($elem.css("paddingTop"),10)-2+"px";info.paddingLeft=parseInt($elem.css("paddingLeft"),10)-2+"px";info.position="absolute";info.fontFamily=$elem.css("font-family");info.color="#A9A9A9";info.zIndex=$elem.css("zIndex");info.whiteSpace='normal';if(isIE7){info.float="left";info.display="inline"}$elem.css({"backgroundColor":"transparent",position:"relative"});var placeText=$elem.attr("placeholder");$elem.attr("placeholder","");var $holder=$('<div class="PlaceholderSpanBox">'+placeText+'</div>');$holder.css(info);$elem.before($holder);holderToggle($elem);$holder.attr('unselectable','on').css({'-moz-user-select':'-moz-none','-moz-user-select':'none','-o-user-select':'none','-khtml-user-select':'none','-webkit-user-select':'none','-ms-user-select':'none','user-select':'none'}).bind('selectstart',function(){return false})};$.fn.placeholder=function(){if(!isIE){return};$elem=this;if($elem.length<=0){return};if($elem.siblings('.PlaceholderSpanBox').length>0){return false}$elem.each(function(){$.placeholder($(this))});$elem.siblings('.PlaceholderSpanBox').bind('mousedown',function(){$(this).siblings(':input').focus();});if('oninput'in $elem[0]){$elem.bind('input keyup blur',function(){holderToggle($(this))})}else{$elem.bind('propertychange',function(){holderToggle($(this))})}};$.fn.placeholderPs=function(){if(this.find('.PlaceholderSpanBox:visible').length>0){this.find('.PlaceholderSpanBox:visible').each(function(index,element){var $elem=$(this).siblings(':input'),$eP=$elem.parent(),infoLeft=parseInt($elem.offset().left-$eP.offset().left-parseInt($eP.css('border-left-width'))+parseInt($elem.css('margin-left')))+'px',infoTop=parseInt($elem.offset().top-$eP.offset().top-parseInt($eP.css('border-top-width'))+parseInt($elem.css('margin-top')))+'px';$(this).css({'left':infoLeft,'top':infoTop})})}}})(jQuery);
/* placeholder end */
/*滚动图*/
;(function(factory){if(typeof define==='function'&&define.amd){define(['jquery'],factory)}else{factory(jQuery)}}(function($){$.cxScroll=function(){var obj;var settings;var callback;var scroller={dom:{},api:{},lockState:false};var isElement=function(o){if(o&&(typeof HTMLElement==='function'||typeof HTMLElement==='object')&&o instanceof HTMLElement){return true}else{return(o&&o.nodeType&&o.nodeType===1)?true:false}};var isJquery=function(o){return(o&&o.length&&(typeof jQuery==='function'||typeof jQuery==='object')&&o instanceof jQuery)?true:false};for(var i=0,l=arguments.length;i<l;i++){if(isJquery(arguments[i])){obj=arguments[i]}else if(isElement(arguments[i])){obj=$(arguments[i])}else if(typeof arguments[i]==='function'){callback=arguments[i]}else if(typeof arguments[i]==='object'){settings=arguments[i]}};if(!obj.length){return};scroller.init=function(){var self=this;self.dom.el=obj;self.settings=$.extend({},$.cxScroll.defaults,settings);self.build();self.api={play:function(){self.settings.auto=true;self.play()},stop:function(){self.settings.auto=false;self.stop()},prev:function(speed){speed=parseInt(speed,10);if(typeof speed!=='number'||speed<0){speed=self.settings.speed};self.goto(self.prevVal,speed)},next:function(speed){speed=parseInt(speed,10);if(typeof speed!=='number'||speed<0){speed=self.settings.speed};self.goto(self.nextVal,speed)}};if(typeof callback==='function'){callback(self.api)}};scroller.build=function(){var self=this;self.dom.box=self.dom.el.find('.box');self.dom.list=self.dom.box.find('.list');self.dom.items=self.dom.list.find('li');self.itemSum=self.dom.items.length;if(self.itemSum<=1){return};self.dom.prevBtn=self.dom.el.find('.prev');self.dom.nextBtn=self.dom.el.find('.next');self.itemWidth=self.dom.items.outerWidth();self.itemHeight=self.dom.items.outerHeight();if(self.settings.direction==='left'||self.settings.direction==='right'){if(self.itemWidth*self.itemSum<=self.dom.box.outerWidth()){return};self.prevVal='left';self.nextVal='right';self.moveVal=self.itemWidth}else{if(self.itemHeight*self.itemSum<=self.dom.box.outerHeight()){return};self.prevVal='top';self.nextVal='bottom';self.moveVal=self.itemHeight};self.dom.list.append(self.dom.list.html());if(self.settings.prevBtn&&!self.dom.prevBtn.length){self.dom.prevBtn=$('<a></a>',{'class':'prev'}).prependTo(self.dom.el)};if(self.settings.nextBtn&&!self.dom.nextBtn.length){self.dom.nextBtn=$('<a></a>',{'class':'next'}).prependTo(self.dom.el)};if(self.settings.nextBtn&&self.dom.prevBtn.length){self.dom.nextBtn.bind('click',function(){if(!self.lockState){self.goto(self.nextVal,self.settings.accel)}})};if(self.settings.prevBtn&&self.dom.prevBtn.length){self.dom.prevBtn.bind('click',function(){if(!self.lockState){self.goto(self.prevVal,self.settings.accel)}})};if(self.settings.hoverLock){self.dom.box.on('mouseenter',function(){self.stop()});self.dom.box.on('mouseleave',function(){self.play()})};self.play()};scroller.play=function(){var self=this;if(!self.settings.auto){return};self.stop();self.run=setTimeout(function(){self.goto()},self.settings.time)};scroller.reset=function(){return this};scroller.stop=function(){if(typeof(this.run)!=='undefined'){clearTimeout(this.run)}};scroller.goto=function(d,t){var self=this;var _max;var _dis;var _speed=t||self.settings.speed;if(typeof d!=='string'){d=self.settings.direction};self.stop();self.lockState=true;switch(d){case'left':case'top':_max=0;if(d==='left'){if(parseInt(self.dom.box.scrollLeft(),10)===0){self.dom.box.scrollLeft(self.itemSum*self.moveVal)};_dis=self.dom.box.scrollLeft()-(self.moveVal*self.settings.step);if(_dis%self.itemWidth>0){_dis-=(_dis%self.itemWidth)-self.itemWidth};if(_dis<_max){_dis=_max};self.dom.box.animate({'scrollLeft':_dis},_speed,self.settings.easing,function(){if(parseInt(self.dom.box.scrollLeft(),10)<=_max){self.dom.box.scrollLeft(0)}})}else{if(parseInt(self.dom.box.scrollTop(),10)===0){self.dom.box.scrollTop(self.itemSum*self.moveVal)};_dis=self.dom.box.scrollTop()-(self.moveVal*self.settings.step);if(_dis%self.itemHeight>0){_dis-=(_dis%self.itemHeight)-self.itemHeight};if(_dis<_max){_dis=_max};self.dom.box.animate({'scrollTop':_dis},_speed,self.settings.easing,function(){if(parseInt(self.dom.box.scrollTop(),10)<=_max){self.dom.box.scrollTop(0)}})};break;case'right':case'bottom':_max=self.itemSum*self.moveVal;if(d==='right'){_dis=self.dom.box.scrollLeft()+(self.moveVal*self.settings.step);if(_dis%self.itemWidth>0){_dis-=(_dis%self.itemWidth)};if(_dis>_max){_dis=_max};self.dom.box.animate({'scrollLeft':_dis},_speed,self.settings.easing,function(){if(parseInt(self.dom.box.scrollLeft(),10)>=_max){self.dom.box.scrollLeft(0)}})}else{_dis=self.dom.box.scrollTop()+(self.moveVal*self.settings.step);if(_dis%self.itemHeight>0){_dis-=(_dis%self.itemHeight)};if(_dis>_max){_dis=_max};self.dom.box.animate({'scrollTop':_dis},_speed,self.settings.easing,function(){if(parseInt(self.dom.box.scrollTop(),10)>=_max){self.dom.box.scrollTop(0)}})};break;default:return};self.dom.box.queue(function(){self.lockState=false;self.play();$(this).dequeue()})};scroller.init()};$.cxScroll.defaults={direction:'right',easing:'swing',step:1,accel:200,speed:800,time:4000,auto:true,hoverLock:true,prevBtn:true,nextBtn:true};$.fn.cxScroll=function(settings,callback){this.each(function(i){$.cxScroll(this,settings,callback)});$.fn.slReset=function(fn,time){var dom=this.children(':not(a)'),list=dom.children().children(),time=time||300;if(dom.siblings('a').length==0){fn&&fn();return;};dom.animate({scrollLeft:'0px'},time,function(){list.eq(list.length/2-1).nextAll().add(dom.siblings('a')).remove();fn&&fn()})};return this}}));
/*环形图*/
$.fn.extend({roundInit:function(n,s){var r=1000;if(this.find('ul.IDroundInitUL').length==0){this.css({'position':'relative'});var $box=$('<ul class="IDroundInitUL"><li class="ID01"></li><li class="ID02"></li><li class="ID03"></li><li class="ID04"></li></ul>');$box.css({'position':'absolute','left':'50%','top':'50%','width':'0px','height':'0px'});$box.find('li').css({'position':'absolute','width':'0px','height':'0px','border':(r*0.5)+'px solid #f00'});var lt=$box.find('li.ID01'),rt=$box.find('li.ID02'),rb=$box.find('li.ID03'),lb=$box.find('li.ID04');lt.css({'left':'auto','top':'auto','bottom':'0px','right':'0px','borderTop':'none','borderRightColor':'transparent'}).css({'borderBottomWidth':'0px','borderRightWidth':'500px'});rt.css({'left':'0px','top':'auto','bottom':'0px','right':'auto','borderRight':'none','borderBottomColor':'transparent'}).css({'borderLeftWidth':'0px','borderBottomWidth':'500px'});rb.css({'left':'0px','top':'0px','bottom':'auto','right':'auto','borderBottom':'none','borderLeftColor':'transparent'}).css({'borderTopWidth':'0px','borderLeftWidth':'500px'});lb.css({'left':'auto','top':'0px','bottom':'auto','right':'0px','borderLeft':'none','borderTopColor':'transparent'}).css({'borderRightWidth':'0px','borderTopWidth':'500px'});this.append($box).attr('round-scale',0)};if(s){this.roundAm(n)}else if(typeof(n)=='number'){this.roundTo(n)}},roundTo:function(n){var $box=this,lt=$box.find('li.ID01'),rt=$box.find('li.ID02'),rb=$box.find('li.ID03'),lb=$box.find('li.ID04'),liAr=[[rt,'borderLeftWidth','borderBottomWidth'],[rb,'borderTopWidth','borderLeftWidth'],[lb,'borderRightWidth','borderTopWidth'],[lt,'borderBottomWidth','borderRightWidth']],step=0;for(var i=0;i<liAr.length;i++){if(n<=step+12.5){var d0=n-step>=0?40*(n-step):0;liAr[i][0].css(liAr[i][1],d0+'px');liAr[i][0].css(liAr[i][2],'500px')}else if(n<=step+25){liAr[i][0].css(liAr[i][1],'500px');liAr[i][0].css(liAr[i][2],(500-40*(n-step-12.5))+'px')}else{liAr[i][0].css(liAr[i][1],'500px');liAr[i][0].css(liAr[i][2],'0px')};step+=25};this.attr('round-scale',n)},roundAm:function(n,m){var th=this,s=m?n:parseFloat(th.attr('round-scale')),m=m?m:n;setTimeout(function(){if(m>s){if(s+0.5>=m){th.roundTo(m);return false};th.roundTo(s+0.5);th.roundAm(s+0.5,m)}else if(m<s){if(s-0.5<=m){th.roundTo(m);return false};th.roundTo(s-0.5);th.roundAm(s-0.5,m)}},0)}});
//SELECT美化
$.fn.extend({
    setInit:function(data){
        var t=this,sed=t.find(':selected'),sed=sed.length>0?sed:t.find('option').first(),new_t=(data&&data.title)?data.title:'<span sel-id-val="value" sel-id-text="html" value="<$Val$>"><$Text$></span>',new_ob=$( new_t.replace('<$Val$>',sed.val()).replace('<$Text$>',sed.html()) ),setBox=(data&&data.setBox)?data.setBox:$(document),cssList=t.attr('set-cssList')?t.attr('set-cssList'):'setBoxStyle';
        $(t).delegate('option','click',function(e) {
            $(this).parent().is('[fn-change-before]')&&eval(_thisS.attr('fn-change-before'));
            $(this).siblings(':selected').removeAttr('selected').removeProp('selected').end().removeAttr('selected').removeProp('selected').parent().val($(this).val()).change();
            var _thisS=$(this).parent('select'),_thisP=_thisS.prev('b');
            var vOb=_thisP.is('[sel-id-val]')?_thisP:_thisP.find('[sel-id-val]'),tOb=_thisP.is('[sel-id-text]')?_thisP:_thisP.find('[sel-id-text]');
            vOb.attr('sel-id-val')=='html'?(vOb.attr('value',$(this).html())):(vOb.attr('value',$(this).val()));
            tOb.attr('sel-id-text')=='value'?(tOb.html($(this).val())):(tOb.html($(this).html()));
            _thisS.is('[fn-change-after]')&&eval(_thisS.attr('fn-change-after'));
            $(this).is('[fn-change-after]')&&eval($(this).attr('fn-change-after'));
        });
        new_ob.addClass(cssList).on('click',function(event){
            event.stopPropagation();
            var _thisS=$(this).next('select'); _thisS.is('[fn-open-before]')&&eval(_thisS.attr('fn-open-before'));
            $('.setListBoxBase').remove();$('.OpenSetListBox').removeClass('OpenSetListBox');$(this).addClass('OpenSetListBox');
            //_thisS.attr('scroll-pos',_thisS.isFixed())
            var listT='<ul class="setListBoxBase '+cssList+'List">',top=$(this).offset().top+$(this).outerHeight(),left=$(this).offset().left,zI=_thisS.is('[zindex]')?_thisS.attr('zindex'):_thisS.css('z-index');
            $($(this).next('select').html()).filter('option').each(function(index, element) { var t=$(this),v=t.val(),c=t.attr('class'),h=$(this).html(); c=c?' class="'+t.attr('class')+'"':''; listT+='<li value="'+v+'"'+c+'>'+h+'</li>'; });
            listT=$( listT+'</ul>' );
            listT.css({'position':'absolute','left':left+'px','top':top+'px','zIndex':zI}).find('li').click(function(e) { var _thisP=$('.OpenSetListBox'),_thisS=_thisP.next('select'); _thisS.find('option:eq('+$(this).index()+')').click(); });
            listT.children().eq(_thisS.find(':selected').index()).addClass('on');
            listT.children().mouseenter(function(e) { $(this).addClass('on').siblings().removeClass('on'); });
            listT.appendTo('body');
            $(this).setListSize();
        })
        $(document).on('click',function(e) { setTimeout(function(){$('.setListBoxBase').remove(); $('.OpenSetListBox').removeClass('OpenSetListBox'); },10); });
        new_ob.insertBefore(t.hide());
        $(window).scroll(function(e) { var t=$('.OpenSetListBox'); if(t.length>0){ var top=t.offset().top+t.outerHeight(),left=t.offset().left; $('.setListBoxBase').css({'left':left+'px','top':top+'px'}); }; });
    },
    setListSize:function(){ var t=$(this),li=$('.setListBoxBase'),tW=t.innerWidth(),liW=li.innerWidth(); (liW<=tW)?(li.width(t.innerWidth())):(li.width('auto')); },
    isFixed:function(){
        var t=$(this),key=false;  if(t.attr('scroll-pos')=='false'){return false;};
        for(var o=$(this).next().offsetParent();!o.is('html');o=o.offsetParent()){ if(o.css('position')=='absolute'||o.css('position')=='fixed'){ key=o; break; }; };
        return key;
    }
});
/*JQ动画缓冲插件*/
jQuery.easing.jswing=jQuery.easing.swing;jQuery.extend(jQuery.easing,{def:"easeOutQuad",swing:function(e,f,a,h,g){return jQuery.easing[jQuery.easing.def](e,f,a,h,g)},easeInQuad:function(e,f,a,h,g){return h*(f/=g)*f+a},easeOutQuad:function(e,f,a,h,g){return -h*(f/=g)*(f-2)+a},easeInOutQuad:function(e,f,a,h,g){if((f/=g/2)<1){return h/2*f*f+a}return -h/2*((--f)*(f-2)-1)+a},easeInCubic:function(e,f,a,h,g){return h*(f/=g)*f*f+a},easeOutCubic:function(e,f,a,h,g){return h*((f=f/g-1)*f*f+1)+a},easeInOutCubic:function(e,f,a,h,g){if((f/=g/2)<1){return h/2*f*f*f+a}return h/2*((f-=2)*f*f+2)+a},easeInQuart:function(e,f,a,h,g){return h*(f/=g)*f*f*f+a},easeOutQuart:function(e,f,a,h,g){return -h*((f=f/g-1)*f*f*f-1)+a},easeInOutQuart:function(e,f,a,h,g){if((f/=g/2)<1){return h/2*f*f*f*f+a}return -h/2*((f-=2)*f*f*f-2)+a},easeInQuint:function(e,f,a,h,g){return h*(f/=g)*f*f*f*f+a},easeOutQuint:function(e,f,a,h,g){return h*((f=f/g-1)*f*f*f*f+1)+a},easeInOutQuint:function(e,f,a,h,g){if((f/=g/2)<1){return h/2*f*f*f*f*f+a}return h/2*((f-=2)*f*f*f*f+2)+a},easeInSine:function(e,f,a,h,g){return -h*Math.cos(f/g*(Math.PI/2))+h+a},easeOutSine:function(e,f,a,h,g){return h*Math.sin(f/g*(Math.PI/2))+a},easeInOutSine:function(e,f,a,h,g){return -h/2*(Math.cos(Math.PI*f/g)-1)+a},easeInExpo:function(e,f,a,h,g){return(f==0)?a:h*Math.pow(2,10*(f/g-1))+a},easeOutExpo:function(e,f,a,h,g){return(f==g)?a+h:h*(-Math.pow(2,-10*f/g)+1)+a},easeInOutExpo:function(e,f,a,h,g){if(f==0){return a}if(f==g){return a+h}if((f/=g/2)<1){return h/2*Math.pow(2,10*(f-1))+a}return h/2*(-Math.pow(2,-10*--f)+2)+a},easeInCirc:function(e,f,a,h,g){return -h*(Math.sqrt(1-(f/=g)*f)-1)+a},easeOutCirc:function(e,f,a,h,g){return h*Math.sqrt(1-(f=f/g-1)*f)+a},easeInOutCirc:function(e,f,a,h,g){if((f/=g/2)<1){return -h/2*(Math.sqrt(1-f*f)-1)+a}return h/2*(Math.sqrt(1-(f-=2)*f)+1)+a},easeInElastic:function(f,h,e,l,k){var i=1.70158;var j=0;var g=l;if(h==0){return e}if((h/=k)==1){return e+l}if(!j){j=k*0.3}if(g<Math.abs(l)){g=l;var i=j/4}else{var i=j/(2*Math.PI)*Math.asin(l/g)}return -(g*Math.pow(2,10*(h-=1))*Math.sin((h*k-i)*(2*Math.PI)/j))+e},easeOutElastic:function(f,h,e,l,k){var i=1.70158;var j=0;var g=l;if(h==0){return e}if((h/=k)==1){return e+l}if(!j){j=k*0.3}if(g<Math.abs(l)){g=l;var i=j/4}else{var i=j/(2*Math.PI)*Math.asin(l/g)}return g*Math.pow(2,-10*h)*Math.sin((h*k-i)*(2*Math.PI)/j)+l+e},easeInOutElastic:function(f,h,e,l,k){var i=1.70158;var j=0;var g=l;if(h==0){return e}if((h/=k/2)==2){return e+l}if(!j){j=k*(0.3*1.5)}if(g<Math.abs(l)){g=l;var i=j/4}else{var i=j/(2*Math.PI)*Math.asin(l/g)}if(h<1){return -0.5*(g*Math.pow(2,10*(h-=1))*Math.sin((h*k-i)*(2*Math.PI)/j))+e}return g*Math.pow(2,-10*(h-=1))*Math.sin((h*k-i)*(2*Math.PI)/j)*0.5+l+e},easeInBack:function(e,f,a,i,h,g){if(g==undefined){g=1.70158}return i*(f/=h)*f*((g+1)*f-g)+a},easeOutBack:function(e,f,a,i,h,g){if(g==undefined){g=1.70158}return i*((f=f/h-1)*f*((g+1)*f+g)+1)+a},easeInOutBack:function(e,f,a,i,h,g){if(g==undefined){g=1.70158}if((f/=h/2)<1){return i/2*(f*f*(((g*=(1.525))+1)*f-g))+a}return i/2*((f-=2)*f*(((g*=(1.525))+1)*f+g)+2)+a},easeInBounce:function(e,f,a,h,g){return h-jQuery.easing.easeOutBounce(e,g-f,0,h,g)+a},easeOutBounce:function(e,f,a,h,g){if((f/=g)<(1/2.75)){return h*(7.5625*f*f)+a}else{if(f<(2/2.75)){return h*(7.5625*(f-=(1.5/2.75))*f+0.75)+a}else{if(f<(2.5/2.75)){return h*(7.5625*(f-=(2.25/2.75))*f+0.9375)+a}else{return h*(7.5625*(f-=(2.625/2.75))*f+0.984375)+a}}}},easeInOutBounce:function(e,f,a,h,g){if(f<g/2){return jQuery.easing.easeInBounce(e,f*2,0,h,g)*0.5+a}return jQuery.easing.easeOutBounce(e,f*2-g,0,h,g)*0.5+h*0.5+a}});
//判断flash是否已安装
function checkFlashPlayer(){var hasFlashPlayer=0;var flashPlayerVersion=0;if(document.all){var shockWaveFlash=new ActiveXObject('ShockwaveFlash.ShockwaveFlash');if(shockWaveFlash){hasFlashPlayer=1;flashPlayerVersion=parseInt(shockWaveFlash.GetVariable("$version").split(" ")[1].split(",")[0])}}else if(navigator.plugins&&navigator.plugins.length>0){var shockWaveFlash=navigator.plugins["Shockwave Flash"];if(shockWaveFlash){hasFlashPlayer=1;var descriptionInfo=shockWaveFlash.description.split(" ");for(var i=0;i<descriptionInfo.length;++i){if(isNaN(parseInt(descriptionInfo[i]))){continue}flashPlayerVersion=parseInt(descriptionInfo[i])}}}return{hasFlashPlayer:hasFlashPlayer,flashPlayerVersion:flashPlayerVersion}};
function hasFlash(){if(checkFlashPlayer().hasFlashPlayer){if(checkFlashPlayer().flashPlayerVersion<=10){if(confirm("您的Flash Player版本过低，立即升级Flash Player版本？")){window.location.href="http://get.adobe.com/cn/flashplayer/"}}else{return checkFlashPlayer().flashPlayerVersion}}else{if(confirm("您没有安装Flash Player，立即安装？")){window.location.href="http://get.adobe.com/cn/flashplayer/"}}};
/*数据加载引擎初稿*/
//<dl set-text-type="for"><dt>环比变化率：<-$hb$->%</dt><dd><span><b><-$date$-></b><i></i></span><em><-$val$-></em></dd></dl>
//$('[set-text-type]').setTextFn(data,{callback:stat_box_cValBox});
$.fn.extend({
    setTextFn:function(data,option){
        var addOb=null,hasList=this.next().is('[set-text-done]');
        switch(this.attr('set-text-type')){
            case 'for' :
                if(option&&(!option.del)){addOb=hasList?this.nextAll('[set-text-done]').last():this;}else{ this.nextAll('[set-text-done]').remove(); addOb=this; }
                var txt=this.show().prop("outerHTML"),newTxt='',sL=txt.search('&lt;')>-1?'&lt;':'<',sR=txt.search('&gt;')>-1?'&gt;':'>';
                this.hide();
                for( var i=0; i<data.length; i++){
                    newTxt+=txt.replace('set-text-type','set-text-done');
                    for( var k in data[i]){ newTxt=newTxt.replace(sL+'-$'+k+'$-'+sR,data[i][k]); }
                }
                addOb.after(newTxt);
                break;
        }
        option&&option.callback&&option.callback(this);
    }
});
//创建多行字符串的方法
function heredoc(fn) { return fn.toString().split('\n').slice(1,-1).join('\n') + '\n';}
//复选label联动绑定
function checkForFn(){
    $('[for]').each(function(index, element) {
        $('#'+$(this).attr('for')).is(':checked')&&($(this).addClass('checked'));
        $('a',this).click(function(e){ e.stopPropagation();});
    });
    $(document).delegate('[for]','click',function(){
        $(this).toggleClass('checked');
        $('#'+$(this).attr('for')).blur();
    })
}