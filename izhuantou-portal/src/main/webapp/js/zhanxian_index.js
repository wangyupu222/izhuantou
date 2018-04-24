/**
 * Created by pc on 2016/11/14.
 */
$(function(){
    /*滚动图片*/


})
var dataAll = [
    [
        {value:'46',itemStyle:{normal:{color:'#79abec'},emphasis:{color:'#93bbef'}}},
        {value:'54',itemStyle:{normal:{color:'#a7cf9b'},emphasis:{color:'#a7cf9b'}}}
    ],
    [
        {value:'15',itemStyle:{normal:{color:'#79abec'},emphasis:{color:'#93bbef'}}},
        {value:'33',itemStyle:{normal:{color:'#a7cf9b'},emphasis:{color:'#a7cf9b'}}},
        {value:'17',itemStyle:{normal:{color:'#edbd49'},emphasis:{color:'#edbd49'}}},
        {value:'35',itemStyle:{normal:{color:'#c594d9'},emphasis:{color:'#c594d9'}}}
    ],
    [
        {value:'59',itemStyle:{normal:{color:'#93bbef'},emphasis:{color:'#93bbef'}}},
        {value:'41',itemStyle:{normal:{color:'#a7cf9b'},emphasis:{color:'#a7cf9b'}}},
    ],
    [
        {value:'0',itemStyle:{normal:{color:'#93bbef'},emphasis:{color:'#93bbef'}}},
        {value:'100',itemStyle:{normal:{color:'#a7cf9b'},emphasis:{color:'#a7cf9b'}}},
    ]
];
option = {
    title: {
        //text: 'Anscombe\'s quartet',
        x: 'center',
        y: 0
    },
    grid: [
        {x: '5%', y: '10%', width: '38%', height: '300px'},
        {x2: '2%', y: '10%', width: '48%', height: '300px;'},

    ],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter: function (params) {
            var tar = params[0];
            return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value+'%';
        },
        /*trigger: 'item',
         formatter: "{a}:{d}%"*/
    },
    xAxis: [
        {
            gridIndex: 0,
            data:['男性占比','女性占比'],
            axisLabel: {
                show: true,
                textStyle: {
                    fontSize:'14'
                }
            }},
        {
            gridIndex: 1,
            data:['18-25','26-35','36-45','45岁以上'],
            axisLabel: {
                show: true,
                textStyle: {
                    fontSize:'14'
                }
            }
        }
    ],
    yAxis: [
        {gridIndex: 0, type : 'value', max: 100,axisLabel : {formatter : '{value} %'}},
        {gridIndex: 1, type : 'value', max: 100,axisLabel : {formatter : '{value} %'}},
    ],

    series: [
        {
            name: '百分比值',
            type: 'bar',
            barWidth:'70',
            label: {
                normal: {
                    show: true,
                    position: 'top',
                    formatter:'{c} %',
                    textStyle:{
                        fontSize:18

                    }

                }
            },
            xAxisIndex: [0],
            yAxisIndex: [0],
            data: dataAll[0]
        },
        {
            name: '百分比值',
            type: 'bar',
            barWidth:'70',
            label: {
                normal: {
                    show: true,
                    position: 'top',
                    formatter:'{c} %',
                    textStyle:{
                        fontSize:18

                    }

                }
            },
            xAxisIndex: [1],
            yAxisIndex: [1],
            data: dataAll[1]
        },
        {
            name: '访问来源',
            type: 'pie',
            radius :120,
            center: ['14%', '82%'],
            xAxisIndex: [2],
            yAxisIndex: [2],
            data: dataAll[2],
            label: {
                normal: {
                    show: true,
                    position: 'inside',
                    formatter:'{c} %',
                    textStyle:{
                        fontSize:24

                    }
                }
            }
        },
        {
            name: '访问来源',
            type: 'pie',
            radius :120,
            center: ['67%', '82%'],
            xAxisIndex: [3],
            yAxisIndex: [3],
            data: dataAll[3],
            label: {
                normal: {
                    show: true,
                    position: 'inside',
                    formatter:'{c} %',
                    textStyle:{
                        fontSize:24

                    }
                }
            }
        }
    ]
};

function timeDataSet(){
    var Da=new Date(),y=Da.getFullYear(),m=Da.getMonth()+1,d=Da.getDate()-1,timeData=[],mList=",1,3,5,7,8,10,12,";
    for(var i=0; i<30;i++){
        if(d==0){
            m=m==1?12:m-1;
            d=(mList.search(','+m+',')>=0)?31:(m!=2?30:(y%4==0?29:28));
            timeData.push(m+'-'+d+'');
            d--;
        }else{
            timeData.push(m+'-'+d+'');
            d--;
        }
    }
    return timeData.reverse();
}

var timeData =[];
option1 = {
    tooltip: {
        trigger: 'axis',
        formatter: function (params) {
            return '日期：'+params[0].name + '<br/>'
                + params[0].seriesName.substring(0,2) + ' : ' + params[0].value + params[0].seriesName.substring(2,params[0].seriesName.length) + '<br/>';
        },
        axisPointer: {
            animation: false
        }
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            axisLine: {onZero: true},
            data: timeData
        }
    ],
    yAxis : [
        {
            //name : '流量(m^3/s)',
            type : 'value',
            //min:100,
            max : 500
        }
    ],
    series : [
        {
            name:'金额万元',
            type:'line',
            symbolSize: 8,
            symbol:'path://#79abec',
            hoverAnimation: false,
            itemStyle:{
                normal:{
                    lineStyle:{
                        color:'#79abec'
                    }
                }
            },
            data:[
                //10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,12
            ]
        }
    ]
};
function SetOption(ob,oData){
    var w=window;
    w[ob.myChart].clear();
    w[ob.option].xAxis[0].data=oData.xData || timeDataSet();
    w[ob.option].series[0].data=oData.data;
    var max=0,min=10000000000000,ar=oData.data;
    for(var i=0;i<ar.length;i++){
        max=max<parseFloat(ar[i])?parseFloat(ar[i]):max;
        min=min>parseFloat(ar[i])?parseFloat(ar[i]):min;
    }
    var chazhi=max-min,
        is100=(parseInt(max/100)>=1),
        is10=(parseInt(max/10)>=1),
        is1=(parseInt(max)>=1),
        is01=(parseInt(max*10)>=1),stepNum=is100?100:is10?10:is1?1:0.1;
    var stepTo=max+chazhi/5,stepSt=0;
    stepNum=stepNum/max>0.2?stepNum/10:stepNum;
    stepTo=stepTo%stepNum==0?(stepTo+stepNum):(parseInt(stepTo/stepNum)+1)*stepNum;
    stepTo=parseInt(stepTo)+1;
    stepSt=0;//((min==0)||(parseInt(min/stepNum)-1<0))?0:min%stepNum==0?(min-stepNum):(parseInt(min/stepNum)-1)*stepNum;
    if(stepTo<5){ w[ob.option].yAxis[0].splitNumber=stepTo; }
    w[ob.option].yAxis[0].max=stepTo;
    w[ob.option].yAxis[0].min=stepSt;
    w[ob.option].series[0].name=oData.name+oData.dw;
    $('.DA_wz1 span').eq(0).html('('+oData.dw+')');
    $('.DA_wz1 div').html(oData.title);
    w[ob.myChart] = echarts.init(document.getElementById(ob.id));
    w[ob.myChart].setOption(w[ob.option]);
}
//SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{data:[500,1020,100,700,1000,652,748,105,990,332,449,752,737,769,125,444,315,954,102,481,963,150,842,371,951,458,752,211,654,454,524]})
$(function(){
    myChart1 = echarts.init(document.getElementById('myzhu'));
    myChart1.setOption(option);
    myChart2 = echarts.init(document.getElementById('myDA_nr'));
    //myChart2.setOption(option1);
    $('.IDzhishuType').change(function(){
        var v=parseInt($(this).val());
        switch(v){
            case 1:
                SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{title:'促成出借金额趋势图',name:'金额',dw:'万元',data:eval('[225.65, 235.45, 454.43, 581.82, 359.45, 659.31, 387.33, 216.97, 174.69, 839.57, 802.71, 609.01, 375.17, 592.00, 206.39, 210.17, 407.20, 1038.13, 874.52, 370.48, 539.66, 279.11, 187.21, 874.22, 922.60, 674.38, 515.07, 702.95, 224.99, 263.67]')});
                break;
            case 2:
                SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{title:'出借人数趋势图',name:'人数',dw:'人',data:eval('[189, 181, 197, 240, 207, 355, 333, 305, 237, 399, 236, 312, 284, 343, 155, 267, 293, 237, 400, 284, 310, 385, 217, 425, 303, 298, 136, 259, 108, 126]')});
                break;
            case 3:
                SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{title:'出借笔数趋势图',name:'笔数',dw:'笔',data:eval('[203, 194, 222, 279, 233, 395, 351, 314, 243, 421, 262, 348, 308, 375, 172, 278, 327, 264, 417, 309, 452, 406, 228, 507, 348, 311, 167, 305, 113, 135]')});
                break;
            case 4:
                SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{title:'平均利率趋势图',name:'利率',dw:'%',data:eval('[4, 5, 5, 6, 4, 6, 6, 4, 5, 7, 4, 6, 4, 5, 4, 6, 5, 7, 5, 5, 6, 4, 4, 5, 5, 6, 6, 5, 5, 5]')});
                break;
            case 5:
                SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{title:'标的数量趋势图',name:'个数',dw:'个',data:eval('[3, 8, 7, 7, 4, 10, 12, 3, 4, 5, 4, 10, 5, 11, 3, 4, 6, 7, 10, 9, 5, 4, 3, 22, 10, 6, 9, 13, 3, 5]')});
                break;
        }
    }).change();
    $('.IDzhishuType').setInit({title:'<b><em sel-id-val="true" sel-id-text="true" value="<$Val$>"><$Text$></em><i class="DA_tu"></i></b>'});
})

