
//  $(document).ready(function(){	
		var termName = [];//定义学期姓名
		var termPeople = [];//定义学期人数
        //JavaScript代码区域
	    layui.use('element', function(){
	       var element = layui.element;
	    });
	    
	   // 基于准备好的dom，初始化echarts实例
    var	myChart = echarts.init(document.getElementById('count'));
		
		//异步加载的配置项和数据显示图表
	    myChart.setOption({
	    	title: {
		        text: '天津老年大学总人数',
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
		        data:['销量']
		    },
	        calculable: true,
	        xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: [],
		    },
	        yAxis:{
		        type: 'value',
		        name:'人数（个）',
		    },
	        series: [{
		        data: [],
		        type: 'line',
		        areaStyle: {},
		        itemStyle:{
		            color:'#31ffff',
		        },
		    }],
	    });
		
		
  		myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
  		
		
    	$.ajax({
    		type:'post',
    		async:true,
    		dataType:'json',
    		url:'api/statistics/student/total',
    		success:function(data){
    			('请求成功');
    			var listData = data.data.statisticInfo;
	            for (var i = 0; i < listData.length; i++){
	                termName.push(listData[i].termname);    //挨个取出学期名字回填到学期姓名数组
	            }
	            for (var i = 0; i < listData.length; i++){
	                termPeople.push(listData[i].total);    //挨个取出学期名字回填到学期姓名数组
	            }
	            myChart.hideLoading();    //隐藏加载动画
	            option ={
	            	xAxis: {
	                    data: termName
	                },
	                series: [
	                    // 根据名字对应到相应的系列
	                    {
	                        data: termPeople
	                    }
	                ],
	                animation: false
	            }
			  //使用刚指定的配置项和数据显示图表。
		      myChart.setOption(option);
    		},
    		error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
                myChart.hideLoading();
			}  		
    	})
// });
	/*弹窗选择班级查询*/
    var callBackData;  //定义全局变量接收返回的班级id
  	//选择班级弹窗
	$('input[name=classname]').click(function(){
	
		layui.use('layer', function(){
            var layer = layui.layer;
			layer.open({
			  type: 2, 
			  title:'选择班级',
			  area: ['600px', '500px'],
			  content: ['sjfx_xyzrs_xzbj.html','no'], //这里content是一个普通的String
			  btn: ['确定', '取消'],
			  yes:function(index,layero){						
		        	//当点击‘确定’按钮的时候，获取弹出层返回的值
				     callBackData = window["layui-layer-iframe" + index].callbackdata();
				    //打印返回的值，看是否有我们想返回的值。
				    (callBackData);
//					    $('#teacher').id(callBackData.teacherId);
//					if(callBackData.classCategoryId==''){
//						
//					}
				   //如果有班级也有专业id的时候，就接收班级id,若没有班级id，只接收专业id
				   if(callBackData.classesId==''){
				   		var termNameMajor = [];//定义学期姓名
						var termPeopleMajor = [];//定义学期人数
				   	 	$('input[name=classname]').attr('id',callBackData.classMajorId);
				   		$('input[name=classname]').val(callBackData.classMajorName);
//				   		myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
				   		//查询班级回填课程表数据
				  		$.ajax({
					  		type:'post',
					  		async:true,
					  		url:'api/statistics/student/total?majorId='+callBackData.classMajorId,
							dataType:'json', 		
					  		success:function(data){
				    			('请求成功');
				    			var listData = data.data.statisticInfo;
					            for (var i = 0; i < listData.length; i++){
					                termNameMajor.push(listData[i].termname);    //挨个取出学期名字回填到学期姓名数组
					            }
					            for (var i = 0; i < listData.length; i++){
					                termPeopleMajor.push(listData[i].total);    //挨个取出学期名字回填到学期姓名数组
					            }
					            myChart.hideLoading();    //隐藏加载动画
					            myChart.setOption({        //加载数据图表
				                xAxis: {
				                    data: termNameMajor
				                },
				                series: [
				                    // 根据名字对应到相应的系列
				                    {
				                        data: termPeopleMajor
				                    }
				                ],
				                  animation: false
				            });
								  // 使用刚指定的配置项和数据显示图表。
				//				  myChart.setOption(option);
				    			
				    		},
					  		error: function(error) {
				                layui.use('layer', function () {
				                    var layer = layui.layer;
				                    layer.msg(error.responseText);
				                });
				                myChart.hideLoading();
							}  		  		
					  	})
				   }else{
				   		var termNameClass = [];//定义学期姓名
						var termPeopleClass = [];//定义学期人数	
					   $('input[name=classname]').attr('id',callBackData.classesId);
					   $('input[name=classname]').val(callBackData.classesName);
					   myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
					   $.ajax({
					  		type:'post',
					  		async:true,
					  		url:'api/statistics/student/total?classId='+callBackData.classesId,
							dataType:'json', 		
					  		success:function(data){	
				  				('请求成功');
				    			var listData = data.data.statisticInfo;
					            for (var i = 0; i < listData.length; i++){
					                termNameClass.push(listData[i].termname);    //挨个取出学期名字回填到学期姓名数组
					            }
					            for (var i = 0; i < listData.length; i++){
					                termPeopleClass.push(listData[i].total);    //挨个取出学期名字回填到学期姓名数组
					            }
					            myChart.hideLoading();    //隐藏加载动画
					            myChart.setOption({        //加载数据图表
				                xAxis: {
				                    data: termNameClass
				                },
				                series: [
				                    // 根据名字对应到相应的系列
				                    {
				                        data: termPeopleClass
				                    }
				                ],
				                animation: false
				            });
								  // 使用刚指定的配置项和数据显示图表。
				//				  myChart.setOption(option);
				    			
				    		},  								  	
					  		error: function(error) {
				                layui.use('layer', function () {
				                    var layer = layui.layer;
				                    layer.msg(error.responseText);
				                });
				                myChart.hideLoading();
							}    		
					  	})
				   }
				    //最后关闭弹出层
                	layer.close(index);
			  }
			});
		});		
	});
//	var classId = $('input[name=classname]').val();   //获取班级id
	/*弹窗选择班级查询end*/
