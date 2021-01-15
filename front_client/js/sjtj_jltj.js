   //JavaScript代码区域
   layui.use('element', function(){
      var element = layui.element;
   });

    //折线图
   // 基于准备好的dom，初始化echarts实例
   var myChart = echarts.init(document.getElementById('main'));
   // 指定图表的配置项和数据
   myChart.setOption({
       title: {
           text: '天津老年大学奖励统计',
           x: 'center',
           textStyle:{
               fontSize:"24",
           },
           padding:20,
       },
       grid:{
           top:'13%',
           width:"60%",
           height:"60%",
           left:"20%",
           containLabel: true,
       },
       legend: {
           data:['数量'],
       },
       xAxis: {
           data: [],
           name:"时间（年）",
       },
       yAxis: {
           name:"数量（个）"
       },
       color:'#ffac48',
       series: [{
           name: '数目',
           type: 'bar',
           data: [],
           barWidth: '35%',
        }],
       textStyle:{
           fontSize:18,
       },
   });

   myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

   var years=[];    //年份数组（实际用来盛放X轴坐标值）
   var nums1=[];    //数目数组（实际用来盛放Y坐标值）

   $.ajax({
       url:"api/statistics/student/base/rewardstatistics",
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
               nums1.push(DataList[i].total);    //挨个取出'数目'数组
           }

           myChart.hideLoading();    //隐藏加载动画
           myChart.setOption({        //加载数据图表
               xAxis: {
                   data: years
               },
               series: [
                   // 根据名字对应到相应的系列
                   {
                       name: '数目',
                       data: nums1
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