

    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });


    //饼图
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    myChart.setOption({
        title: {
            text: '天津老年大学性别统计',
            x: 'center',
            y: '5%',
            textStyle: {
                fontSize: "24",
            },
            padding: 20,
        },
        grid: {
            top: '13%',
            width: "60%",
            height: "60%",
            left: "20%",
            containLabel: true,
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            y: "15%",
            x: '5%',
            data: ['女性','男性'],
            pageTextStyle: {
                fontsize: 18,
                fontStyle: '微软雅黑',
            }
        },
        series: [
            {
                type: 'pie',
                radius: '50%',
                data: [
                    {value:[], name: '女性', itemStyle: {color: '#fc9a49',},},
                    {value:[], name: '男性', itemStyle: {color: '#f2796c',},},
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },
                textStyle: {
                    fontSize: 18,
                },
            }
        ]
    });

    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

    var nums1=[];    //女性数组
    var nums2=[];    //男性数组
    $.ajax({
        url:"api/statistics/student/base/sexrate",
        type:'POST',
        dataType:'json',
        async:true,
        xhrFields: {withCredentials: true},
        success:function(data){
            var i;
            var DataList = data.data.statisticInfo;
            for (var i = 0; i < DataList.length; i++) {
                nums1.push(DataList[i].female);    //挨个取出'女性'数组
            }
            for (var i = 0; i < DataList.length; i++) {
                nums2.push(DataList[i].male);    //挨个取出'男性'数组
            }

            myChart.hideLoading();    //隐藏加载动画
            myChart.setOption({       //加载数据图表
                legend: {
                    orient: 'vertical',
                    y: "15%",
                    x: '5%',
                    data: ['女性','男性'],
                },
                series: [
                    // 根据名字对应到相应的系列
                    {
                        name: '性别统计',
                        data: [{
                            value:nums1,
                            name:'女性',
                            itemStyle: {color: '#fc9a49',},
                        },{
                            value:nums2,
                            name:'男性',
                            itemStyle: {color: '#f2796c',},
                        }]
                    },
                ]
            });
        },
        error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
                myChart.hideLoading();
			}  
    })

    //导出
    //创建form提交图片数据
    var postDownLoadFile = function (options) {
        var config = $.extend(true, { method: 'post' }, options);
        var $iframe = $('<iframe id="down-file-iframe" />');
        var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
        $form.attr('action', config.url);
        //图片
        $form.append('<input type="hidden" name="base64Info" value="' + config.data + '" />');

        $iframe.append($form);
        $(document.body).append($iframe);
        $form[0].submit();
        $iframe.remove();
    }
    //触发下载功能
    function Expart() {
        // 获取base64图片
        var picBase64Info = myChart.getDataURL();
        postDownLoadFile({
            url: "api/statistics/student/base/wordoutput",
            data: picBase64Info,
            method: 'post'
        });
    }
