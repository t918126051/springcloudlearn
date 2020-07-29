layui.use(['element', 'jquery','layer','table','form','excel'], function () {
    let element = layui.element, $ = layui.jquery;
    let layer = layui.layer;
    let table = layui.table;
    let form = layui.form;
    let excel=layui.excel;
    let recordNum = -1; //放大显示操作标记
    let fieldStatis;
    let statisIds;
    let fieldNames;
    form.on('select(getfieldName)',function (data) {debugger
        let tableNameCn=$("#tableNameCn").val();
        fieldStatis=$("#getfieldName").val();
        statisIds=$("#statisId").val();
        fieldNames=data.value;
        let statisId=statisIds;
        let fieldName=fieldNames;
        if(fieldName!==""){
            /**
             *柱状图
             */
            Histogram(statisId,fieldName,fieldStatis,tableNameCn);

            /**
             * 折线图
             */
            BrokenLineBiagram(statisId,fieldName,fieldStatis,tableNameCn);

            /**
             * 饼状图
             */
            LineBiagram(statisId,fieldName,fieldStatis,tableNameCn);

            /**
             * 统计表
             */
            TableData(statisId,fieldName,fieldStatis,tableNameCn);
        }
    })
    form.on('select(getstatis)',function (data) {
        let value=data.value;
        if(value=="柱状图"){
            $(".zshortDiv1").css("display", "block");
            $(".bshortDiv1").css("display", "none");
            $(".zxshortDiv1").css("display", "none");
            $(".tabshortDiv1").css("display","none");
            $(".SMap").css("display","none");
        }
        if(value=="饼状图"){
            $(".bshortDiv1").css("display", "block");
            $(".zshortDiv1").css("display", "none");
            $(".zxshortDiv1").css("display", "none");
            $(".tabshortDiv1").css("display","none");
            $(".SMap").css("display","none");

        }
        if(value=="折线图"){
            $(".zxshortDiv1").css("display", "block");
            $(".bshortDiv1").css("display", "none");
            $(".zshortDiv1").css("display", "none");
            $(".tabshortDiv1").css("display","none");
            $(".SMap").css("display","none");
        }
        if(value=="统计表"){
            $(".tabshortDiv1").css("display","block");
            $(".zxshortDiv1").css("display", "none");
            $(".bshortDiv1").css("display", "none");
            $(".zshortDiv1").css("display", "none");
            $(".SMap").css("display","none");
        }

  })

    let statisId;
    let fieldName;
    $(function () {

        statisId=$("#statisId").val();
        let tableNameCn=$("#tableNameCn").val();
        fieldName="0";
        /**
         * 查询统计类型
         */
       // getPtype(statisId);
        $.ajax({
            url:"/table/display/ptypes",
            type: "post",
            async: false,
            data:{
                "statisId":statisId,
            },
            success:function (res) {
                $.each(res.data, function(index, item) {
                    $('#getfieldName').append(new Option(item.fieldName,item.fieldName));

                });
                    fieldStatis=res.data[0].fieldName;
                layui.form.render("select");
            }
        })

        /**
         * 折线图
         */
        BrokenLineBiagram(statisId,fieldName,fieldStatis,tableNameCn);

        /**
         *柱状图
         */
        Histogram(statisId,fieldName,fieldStatis,tableNameCn);

        /**
         * 饼状图
         */
        LineBiagram(statisId,fieldName,fieldStatis,tableNameCn);

        /**
         * 统计表
         */
        TableData(statisId,fieldName,fieldStatis,tableNameCn);


    })

//图表三柱状图
function Histogram(statisId,fieldName,fieldStatis,tableNameCn) {
    $.ajax({
        url:"/table/display/chart",
        type: "post",
        data: {
            "statisId":statisId,
            "fieldName":fieldName
        },
        dataType: "json",
        success: function (result) {
            debugger;
            var data = result.data;
            //TODO 控制箱数据补齐后，此两个变量需要添加到相应位置。
            var arr_rg = [];//位置1
            var arr_sum = [];//位置2
            //循环赋值
            for (var i = 0; i < data.length; i++) {
                arr_rg[i] = data[i].scaleName;
                arr_sum[i] = data[i].sum;
            }
            if(data.length==0){
                for(let i=0;i<=6;i++){
                    arr_rg[i] = "名称"+i;
                    arr_sum[i] = 0;
                }
            }
            //基于准备好的dom，初始化echarts实例
            var myChart3 = echarts.init(document.getElementById('Histogram'), 'themeOne');
            var oldWidth = $('.picChart').width();
            var oldHeight = $('.picChart').height();
            option = {
                title: {
                    text: tableNameCn+"--"+fieldStatis+"统计",
                    x:'center',//水平安放位置，默认为'left'，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
                    top:10,//垂直安放位置，默认为top，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）
                    backgroundColor: 'rgba(0,0,0,0)',//标题背景颜色，默认'rgba(0,0,0,0)'透明
                },
                toolbox: {
                    top: '3%',
                    right: '2%',
                    iconStyle: {
                        normal: {
                            textPosition: 'left'
                        },
                        emphasis: {
                            textPosition: 'top'
                        }
                    },
                    show: true,

                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType:{show: true,type: ['line', 'bar','pie'],},
                        restore:  { show: true, },
                        saveAsImage: {show: true},
                        myTool: {
                            show: true,
                            x:'right',
                            y: 'top',
                            title: '放大',
                            textStyle: {
                                color: '#3300FF'
                            },
                            icon: 'image:///images/full_screen.jpg',
                            onclick: function () {
                                if (recordNum < 0) {
                                    recordNum = 1;
                                    var mylayer3 = layer.open({
                                        type: 1,
                                        title: "<b style='color:#F7FAF1'>全市控制箱按行政区域统计</b>",
                                        skin: 'title-style',
                                        area: ['100%', '100%'],
                                        shade: 0,
                                        success: function () {
                                            myChart3.resize({
                                                width: $('.picChart').width(),
                                                height: $('.picChart').height()
                                            });
                                            var opt = myChart3.getOption();
                                            opt.toolbox[0].feature.myTool.show = false;
                                            myChart3.setOption(opt);
                                        },
                                        cancel: function () {
                                            //右上角关闭回调
                                            layer.close(mylayer3);
                                            //刷新当前页面，即重新加载
						                      window.location.reload();
                                            recordNum = -1;
                                            myChart3.resize({
                                                width: oldWidth,
                                                height: oldHeight
                                            });
                                            var opt = myChart3.getOption();
                                            opt.toolbox[0].feature.myTool.show = true;
                                            myChart3.setOption(opt);
                                        },
                                        content: $('.picChart') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                                    });
                                    myChart3.resize();
                                } else {
                                    //右上角关闭回调
                                    layer.close(mylayer3);
                                    recordNum = -1;
                                    //重新加载
                                    myChart3.resize({
                                        width: oldWidth,
                                        height: oldHeight
                                    });
                                    var opt = myChart3.getOption();
                                    opt.toolbox[0].feature.myTool.show = true;
                                    myChart3.setOption(opt);
                                }
                            }
                        }


                    }
                },
                tooltip: {},
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '6%',
                    containLabel: true

                },
                dataZoom: [{
                    type: 'inside'
                }],
                legend: {
                    data: arr_rg,
                    top: '2%',
                    right: '4%',
                },
                xAxis: {
                    name: "("+fieldStatis+")",// 给X轴加单位
                    type: 'category',
                    data: arr_rg,//,//位置1
                    axisLabel: {
                        color: '#2898e5',
                        fontWeight: 'bold',
                        interval: 0,
                        rotate: 40,
                        showMaxLabel: true,
                        formatter: function (name) {
                            if (recordNum > 0) {
                                return name;
                            } else {
                                return (name.length > 5 ? (name.slice(0, 5) + "...") : name);
                            }
                        }
                    },
                    axisTick: {
                        alignWithLabel: true,
                        lineStyle: { color: '#2898e5' }
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#019bf8',
                            fontWeight: 'bold'
                        },
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: ['rgb(1,155,246,0.3)'],  //网格线
                            width: 1,
                        }
                    },
                },
                yAxis: {
                    type: 'value',
                    axisLabel: {
                        color: '#019bf8',
                        fontWeight: 'bold',
                    },
                    axisLine: {
                            lineStyle: {
                                color: '#2898e5',
                                fontWeight: 'bold'
                            },
                    },
                    axisTick: {
                        alignWithLabel: true,
                        lineStyle: { color: '#2898e5' }
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: ['rgb(1,155,246,0.3)'],  //网格线
                            width: 1,
                        }
                    },

                },
                series: [{
                    symbol: 'circle',  //实心点
                    symbolSize: 6,     //实心点的大小
                    smooth: false,      //折现平滑
                    name: '共计数量',
                    type: 'bar',
                    barWidth:25,
                    data: arr_sum,//[30, 30, 25, 40, 20, 10, 20, 30, 40] //位置2
                    markPoint: {
                        symbolSize: 65,
                        data: [
                            {type: 'max', arr_sum: '最大值'},
                            {type: 'min', arr_sum: '最小值'}
                        ],
                        itemStyle:{
                            color: '#4587E7',
                            borderColor: '#000',
                            borderWidth: 0,
                            borderType: 'solid',
                        }
                    },
                    markLine: {
                        name:'平均值',
                        data: [
                            {type: 'average', arr_sum: '平均值'},
                            [{
                                symbol: 'none',
                                x: '10%',
                                yAxis: 'max'
                            }, {
                                symbol: 'circle',
                                label: {
                                    normal: {
                                        position: 'start',
                                        formatter: '最大值',
                                        color:'#6380A1'
                                    }
                                },
                                type: 'max',
                                name: '最高点'
                            }],
                            [{
                                symbol: 'none',
                                x: '10%',
                                xAxis: 'min'
                            }, {
                                symbol: 'circle',
                                label: {
                                    normal: {
                                        position: 'start',
                                        formatter: '最小值',
                                        color:'#6380A1'
                                    }
                                },
                                type: 'min',
                                name: '最低点'
                            }]
                        ]
                    }
                    ,itemStyle: {
                        emphasis: {
                            barBorderRadius: 7
                        },
                        normal: {
                            barBorderRadius: 7,
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#6380A1'},
                                    {offset: 1, color: '#6BE6C1'}

                                ]
                            ),
                            label: {
                                show: true,
                                position: 'top',
                                padding: 4,
                                backgroundColor: '#EEE',
                                borderRadius: 50,
                                textStyle: {
                                    color: '#3300FF'
                                }
                            }
                        },
                    },
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgb(52,214,145)'  //渐变上边颜色
                            }, {
                                offset: 1,
                                color: 'transparent'      //渐变下边颜色
                            }])
                        }
                    },
                }]
            };
            //使用刚指定的配置项和数据显示图表。
            myChart3.setOption(option);
        },
    });
}


    //图表三饼状图
    function LineBiagram(statisId,fieldName,fieldStatis,tableNameCn) {
        var dom = document.getElementById("LineBiagram");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            title: {
                text: tableNameCn+"--"+fieldStatis+"统计",
                x:'center',//水平安放位置，默认为'left'，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
                top:10,//垂直安放位置，默认为top，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）
                backgroundColor: 'rgba(0,0,0,0)',//标题背景颜色，默认'rgba(0,0,0,0)'透明
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                //x : 'left',
                top: 100,
                right:50,
                bottom:40,
                data:[]
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {
                        show: true, readOnly: false,
                    },
                    // magicType : {
                    //     show: true,
                    //     type: ['line', 'bar'],
                    //     option: {
                    //         funnel: {
                    //             x: '25%',
                    //             width: '50%',
                    //             funnelAlign: 'left',
                    //             max: 1548
                    //         }
                    //     }
                    // },

                    restore : {show: true},
                    saveAsImage : {show: true},
                    myTool: {
                        show: true,
                        x:'right',
                        y: 'top',
                        title: '放大',
                        textStyle: {
                            color: '#3300FF'
                        },
                        icon: 'image:///images/full_screen.jpg',
                        onclick: function () {
                            if (recordNum < 0) {
                                recordNum = 1;
                                var mylayer3 = layer.open({
                                    type: 1,
                                    title: "<b style='color:#F7FAF1'>全市控制箱按行政区域统计</b>",
                                    skin: 'title-style',
                                    area: ['100%', '100%'],
                                    shade: 0,
                                    success: function () {
                                        myChart.resize({
                                            width: 1446,
                                            height: 598
                                        });
                                        var opt = myChart.getOption();
                                        opt.toolbox[0].feature.myTool.show = false;
                                        myChart.setOption(opt);
                                    },
                                    cancel: function () {
                                        //右上角关闭回调
                                        layer.close(mylayer3);
                                        //刷新当前页面，即重新加载
                                        window.location.reload();
                                        recordNum = -1;
                                        myChart.resize({
                                            width: 1446,
                                            height: 598
                                        });
                                        var opt = myChart.getOption();
                                        opt.toolbox[0].feature.myTool.show = true;
                                        myChart.setOption(opt);
                                    },
                                    content: $('.LinepicChart') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                                });
                                myChart.resize();
                            } else {
                                //右上角关闭回调
                                layer.close(mylayer3);
                                recordNum = -1;
                                //重新加载
                                myChart.resize({
                                    width: 1446,
                                    height: 598
                                });
                                var opt = myChart.getOption();
                                opt.toolbox[0].feature.myTool.show = true;
                                myChart.setOption(opt);
                            }
                        }
                    },

                    iconStyle:{
                        normal:{
                            color:'#ff4a6a',//设置颜色
                        }
                    }
                }
            },
            calculable : true,
            series : [
                {
                    name:'',
                    type:'pie',
                    radius : '65%',
                    center: ['45%', '50%'],
                    data:[]
                }
            ]
        };
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
        $.ajax({
            url:"/table/display/chart",
            type: "post",
            data: {
                "statisId":statisId,
                "fieldName":fieldName
            },
            dataType: "json",
            success: function(result) {
                var data = result.data;
                var arr_rg = [];//位置1
                var arr_sum = [];//位置2
                //循环赋值
                for (var i = 0; i < data.length; i++) {
                    arr_rg[i] = data[i].scaleName;
                    arr_sum[i] = data[i].sum;
                }
                var names=[];//定义两个数组
                var nums=[];
                for (var i = 0; i < data.length; i++) {
                    names.push(arr_rg[i]);
                    var obj = new Object();
                    obj.name = arr_rg[i];
                    obj.value = arr_sum[i];
                    nums.push(obj);
                }
                myChart.resize({
                   // width: $('.picChart').width(),
                    //height: $('.picChart').height()
                    width:1446,
                    height:598,
                });
                myChart.setOption({ //加载数据图表
                    legend: {
                        data: names
                    },
                    series: {
                        // 根据名字对应到相应的系列
                        name: ['名称  数量'],
                        data: nums
                    }
                });
            },
        })

    }


    //图表三折线图
    function BrokenLineBiagram(statisId,fieldName,fieldStatis,tableNameCn) {
        var dom = document.getElementById("BrokenLineBiagram");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            color: ['#3398DB'],
            title: {
                text: tableNameCn+"--"+fieldStatis+"统计",
                x:'center',//水平安放位置，默认为'left'，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
                top:10,//垂直安放位置，默认为top，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）
                backgroundColor: 'rgba(0,0,0,0)',//标题背景颜色，默认'rgba(0,0,0,0)'透明
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ["图例1", "图例2", "图例3"],
                icon: "circle",   //  这个字段控制形状  类型包括 circle，rect ，roundRect，triangle，
                //   diamond，pin，arrow，none
                itemWidth: 10,  // 设置宽度
                itemHeight: 10, // 设置高度
                itemGap: 40, // 设置间距
                orient: 'vertical',  //vertical是竖着显示 ，默认是横着
                left: '70%',   //距离左边70%，也可用left,center,right
                y: 'center',   //上下居中显示，也可以用百分比
                textStyle:{    //图例文字设置
                fontSize: 18,//字体大小
                color: '#ffffff'//字体颜色
        }

            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '6%',
                containLabel: true
            },
            toolbox: {
                show: true,
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar','pie']},

                    restore: {
                        show: true,
                    },
                    saveAsImage: {show: true},
                    myTool: {
                        show: true,
                        x:'right',
                        y: 'top',
                        title: '放大',
                        textStyle: {
                            color: '#3300FF'
                        },
                        icon: 'image:///images/full_screen.jpg',
                        onclick: function () {
                            if (recordNum < 0) {
                                recordNum = 1;
                                var mylayer3 = layer.open({
                                    type: 1,
                                    title: "<b style='color:#F7FAF1'>全市控制箱按行政区域统计</b>",
                                    skin: 'title-style',
                                    area: ['100%', '100%'],
                                    shade: 0,
                                    success: function () {
                                        myChart.resize({
                                            width: 1446,
                                            height: 598
                                        });
                                        var opt = myChart.getOption();
                                        opt.toolbox[0].feature.myTool.show = false;
                                        myChart.setOption(opt);
                                    },
                                    cancel: function () {
                                        //右上角关闭回调
                                        layer.close(mylayer3);
                                        //刷新当前页面，即重新加载
                                        window.location.reload();
                                        recordNum = -1;
                                        myChart.resize({
                                            width: 1446,
                                            height: 598
                                        });
                                        var opt = myChart.getOption();
                                        opt.toolbox[0].feature.myTool.show = true;
                                        myChart.setOption(opt);
                                    },
                                    content: $('.BrokenpicChart') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                                });
                                myChart.resize();
                            } else {
                                //右上角关闭回调
                                layer.close(mylayer3);
                                recordNum = -1;
                                //重新加载
                                myChart.resize({
                                    width: 1446,
                                    height: 598
                                });
                                var opt = myChart.getOption();
                                opt.toolbox[0].feature.myTool.show = true;
                                myChart.setOption(opt);
                            }
                        }
                    },
                },

            },
            xAxis: {
                name: "("+fieldStatis+")",// 给X轴加单位
                type: 'category',
                boundaryGap: false,
                data: [],
                axisLine: {
                    lineStyle: {
                        color: '#2898e5',
                    },
                },
                axisLabel: {
                    show: true,
                    interval: 'auto',
                    rotate: 40,
                    textStyle: {
                        color: '#019bf8',
                    },
                    showMaxLabel: true,
                    formatter: function (value, index) {
                        if(value.length>6){
                            return value.substr(0,5)+'...'
                        }else{
                            return value
                        }
                    }

                },
                axisTick: {
                    lineStyle: { color: '#2898e5' }
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: ['rgb(1,155,246,0.3)'],  //网格线
                        width: 1,
                    }
                },
            },
            yAxis: {
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color: '#2898e5',
                    },
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#019bf8'
                    }
                },
                axisTick: {
                    lineStyle: { color: '#2898e5' }
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: ['rgb(1,155,246,0.3)'],  //网格线
                        width: 1,
                    }
                },
            },
            series: [
                {
                    //折现的平滑度
                    symbol: 'circle',  //实心点
                    symbolSize: 6,     //实心点的大小
                    smooth: false,      //折现平滑

                    type:'line',
                    data:[],
                    barWidth: '20%',
                    // itemStyle: {
                    //     color: new echarts.graphic.LinearGradient(
                    //         0, 0, 0, 1,
                    //         [
                    //             {offset: 0, color: '#83bff6'},
                    //             {offset: 0.5, color: '#188df0'},
                    //             {offset: 1, color: '#188df0'}
                    //         ]
                    //     )
                    // },
                    markPoint: {
                        color:'#188df0',
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        name:'平均值',
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    },
                    //折现阴影变色
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgb(52,214,145)'  //渐变上边颜色
                            }, {
                                offset: 1,
                                color: 'transparent'      //渐变下边颜色
                            }])
                        }
                    },
                   //折线上方显示文字：
                    label: {
                        normal: {
                            show: true,
                            position: 'top',  //头上显示数据
                            color: 'rgb(0,255,255)'  //显示文字颜色
                        }
                    },
                }
            ]
        };

        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
        $.ajax({
            url:"/table/display/chart",
            type: "post",
            data: {
                "statisId":statisId,
                "fieldName":fieldName
            },
            dataType: "json",
            success: function(result) {

                var data = result.data;
                //行政区域
                // var arr_rg = [];
                // //各行政区域路灯总数
                // var arr_sum = [];
                var arr_rg = [];//位置1
                var arr_sum = [];//位置2
                //循环赋值
                for (var i = 0; i < data.length; i++) {
                    arr_rg[i] = data[i].scaleName;
                    arr_sum[i] = data[i].sum;
                }
                var names=[];//定义两个数组
                var nums=[];
                for (var i = 0; i < data.length; i++) {
                    names.push(arr_rg[i]);
                    nums.push(arr_sum[i]);
                }
                myChart.resize({
                    // width: $('.picChart').width(),
                    // height: $('.picChart').height()
                    width:1446,
                    height:598,
                });
                myChart.setOption({ //加载数据图表
                    legend: {
                        data: names
                    },
                    xAxis: {
                        data:names
                    },
                    series: {
                        data: nums,
                    }
                });
            },
        })

    }

    //统计表
    function TableData(statisId,fieldName,fieldStatis,tableNameCn) {
        table.render({
            elem: '#table_sta',
            url:"/table/display/chart1/"+statisId+"/"+fieldName,
            align: 'center',
            totalRow: true,
            page: true,
            even: true,
            toolbar: '#toolbar_sta',
            cols: [
                [ //表头
                    {field: '', width: 700,colspan:2, title:tableNameCn+"--"+fieldStatis+"统计" , align: 'center', sort: true},
                ],[
                    {field: 'scaleName', width: 700, title: fieldStatis, sort: true},
                    {field: 'sum', width: 700, title: '数量(个)', sort: true},
                    ]
            ],
            response: {
                statusCode: 200 //规定成功的状态码，默认：0
            },
        })
    }

    /**
     * 触发execl按钮，导出数据
     */
    table.on('toolbar(table_sta)',function (obj) {
        debugger
        switch(obj.event){
            case 'export_execl':
                let statisId = $("#statisId").val();
                let tableNameCn = $("#tableNameCn").val();
                // let fieldStatis=$("#getfieldName").val();
                if (fieldStatis !== "") {
                    fieldName = fieldStatis

                } else {
                    fieldName = "0";
                }
                $.ajax({
                    url: "/table/display/chart1/" + statisId + "/" + fieldName,
                    type: "post",
                    data: {},
                    dataType: "json",
                    success(res) {
                        debugger
                        var data = res.data;
                        console.log(res);
                        // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                        data = excel.filterExportData(data, [
                            'scaleName',
                            'sum'
                        ]);
                        // 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
                        data.unshift({scaleName: fieldStatis, sum: "数量(个)"});

                        var timestart = Date.now();
                        excel.exportExcel(data, tableNameCn + '--'+'按' + fieldStatis  + '统计' + '.xlsx', 'xlsx');
                    }
                    , error() {
                        layer.alert('获取数据失败，请检查是否部署在本地服务器环境下');
                    }
                });
                break;
        }
    })


/**
 * 查询统计类型
 * @param statisId
 */
function getPtype(statisId){
        $.ajax({
            url:"/table/display/ptypes",
            type: "post",
            async: false,
            data:{
                "statisId":statisId,
            },
            success:function (res) {
                $.each(res.data, function(index, item) {
                    $('#getfieldName').append(new Option(item.fieldName,item.fieldName));
                });
                layui.form.render("select");
            }
        })
    }
});