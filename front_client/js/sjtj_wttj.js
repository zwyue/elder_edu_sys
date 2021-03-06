

    //JavaScript代码区域
    layui.use('element', function () {
         var element = layui.element;
    });

   //统计图
   // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    //异步加载的配置项和数据显示图表
    myChart.setOption({
        title: {
            text: '天津老年大学问题统计',
            x: 'center',
            textStyle: {
                fontSize: "24",
            },
            padding: 20,
        },
        textStyle: {
            fontSize: 18,
        },
        grid: {
            top: '13%',
            width: "60%",
            height: "60%",
            left: "20%",
            containLabel: true,
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            orient: 'vertical',
            data: ['退班', '转班', '休学','开除'],
            x: 'right'
        },
        calculable: true,
        xAxis: {
            type: 'category',
            name: '时间（年）',
            data: []
        },
        yAxis: [{
            name:"人数（人）"
        }],
        series: [
            {
                name: '退班',
                color: '#ff9227',
                type: 'bar',
                data: [],
                barWidth: '18%',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
            },
            {
                name: '转班',
                color: '#ffd927',
                type: 'bar',
                data: [],
                barWidth: '18%',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
            },
            {
                name: '休学',
                color: '#ff5614',
                type: 'bar',
                data: [],
                barWidth: '18%',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
            },
            {
                name: '开除',
                color: '#facd62',
                type: 'bar',
                data: [],
                barWidth: '18%',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
            }
        ],
        animation:false,
    })

    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

    var years=[];    //年份数组（实际用来盛放X轴坐标值）
    var nums1=[];    //退学数组（实际用来盛放Y坐标值）
    var nums2=[];    //休学数组
    var nums3=[];    //退班数组
    var nums4=[];    //开除数组
    $.ajax({
        url:"api/statistics/student/base/problemstatistics",
        type:'POST',
        dataType:'json',
        async:true,
        xhrFields: {withCredentials: true},
        success:function(data){
            var i;
            var DataList = data.data.statisticInfo;
            for (var i = 0; i < DataList.length; i++) {
                years.push(DataList[i].year);    //挨个取出年份并填入年份数组
            }
            for (var i = 0; i < DataList.length; i++) {
                nums1.push(DataList[i].exit);    //挨个取出'退班'数组
            }
            for (var i = 0; i < DataList.length; i++) {
                nums2.push(DataList[i].conversion);    //挨个取出'转班'数组
            }
            for (var i = 0; i < DataList.length; i++) {
                nums3.push(DataList[i].suspension);    //挨个取出'休学'数组
            }
            for (var i = 0; i < DataList.length; i++) {
                nums4.push(DataList[i].expel);    //挨个取出'开除'数组
            }
            myChart.hideLoading();    //隐藏加载动画
            myChart.setOption({        //加载数据图表
                xAxis: {
                    data: years
                },
                series: [
                    // 根据名字对应到相应的系列
                    {
                        name: '退班',
                        data: nums1,
                    },
                    {
                        name: '转班',
                        data: nums2,
                    },
                    {
                        name: '休学',
                        data: nums3,
                    },
                    {
                        name: '开除',
                        data: nums4,
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
    // var postDownLoadFile = function (options) {
    //     var config = $.extend(true, {method: 'post'}, options);
    //     var $iframe = $('<iframe id="down-file-iframe" />');
    //     var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
    //     $form.attr('action', config.url);
    //     //图片
    //     $form.append('<input type="hidden" name="base64Info" value="' + config.data + '" />');
    //
    //     $iframe.append($form);
    //     $(document.body).append($iframe);
    //     $form[0].submit();
    //     $iframe.remove();
    // }

    var postDownLoadFile = function (options) {
        var config = $.extend(true, {method: 'post'}, options);
        var $div = $('<div id="down-file-iframe"></div>');
        var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
        $form.attr('action', config.url);
        //图片
        $form.append('<input type="hidden" name="base64Info" value="' + config.data + '" />');

        $div.append($form);
        $(document.body).append($div);
        $form[0].submit();
        $div.remove();
    }

    //触发下载功能
    function dxExport(){
        var picBase64Info = myChart.getDataURL();
        //$("#base").val(picBase64Info);
        postDownLoadFile({
            url:"api/statistics/student/base/wordoutput",
            data:picBase64Info,
            method:'post'
        });
        // $.ajax({
        //     type: 'POST',
        //     url: "api/statistics/student/base/wordoutput",
        //     async: false,
        //     data: $('#form1').serialize(),
        //     // dataType: 'json',
        //     xhrFields: {withCredentials: true},
        //     success:function (data) {
        //         layui.use('layer', function(){
        //             var layer = layui.layer;
        //             layer.msg('导出成功！',{area:['150px','50px']});
        //         });
        //     },
        //     error:function () {
        //         layui.use('layer', function(){
        //             var layer = layui.layer;
        //             layer.msg('导出错误！',{area:['150px','50px']});
        //         });
        //     }
        // })
    }


