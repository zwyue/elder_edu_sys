	//定义一个全局变量接收返回的data
	var listData,secondData,threeData;
    $(function(){
		//加载一级分类
		loadFirst();
		//加载一级分类的方法
		function loadFirst(){
			//一级分类的父级代号
			var id;
			$.ajax({
				async: false, //同步
				url: "api/common/catelist",
				data: {
					id: id  //后台需要的：传给后台的
				},
				type: "get",
				dataType: "json",
				//解决跨域问题
//         		xhrFields: {withCredentials: true},  
				success: function(data){
                    layui.use(['layer', 'form'], function () {
                        var form = layui.form;
                        //接收返回的data
                        listData = data.data;
                        var i = 0;
                        $.each(listData, function (i, result) {
                            var index = data.data[i].id;
                            var text = data.data[i].category;
                            $("#classCategory").append('<option onclick="loadSecond(' + result.id + ')" value="' + index + '">' + text + '</option>');
                            //$("#classCategory").append('<option onclick="loadSecond(' + result.id + ')" value="' + result.id + '">' + result.category + '</option>');
                        });
                        //更新 lay-filter="classCategory" 所在容器内的全部 select 状态
                        form.render('select', 'classCategory');
                        //找到渲染后的dd
                        var dlChild = $('#classCategory').siblings("div.layui-form-select").find('dl').children();
                        //找到渲染后的所有option（是个数组）
				        var optionFirst = $('#classCategory option');
                        //遍历dd,给每个dd添加onclick事件
                        for (var i = 1; i < dlChild.length; i++) {
                        	var b= $(optionFirst[i]).val();
                            $(dlChild[i]).attr('onclick', 'loadSecond(' + b + ')');
                        }
                    });
				},
				error: function(error){
	                layui.use('layer', function (){
	                    var layer = layui.layer;
	                    layer.msg(error.responseText);
	                });
				}				
			});
		};
	}); 
	
	//加载二级分类的方法
	function loadSecond(id){			
			$.ajax({
				async: false,
				url:"api/common/prolist",
				data:{
                    cateid: id //后台需要的：传给后台的
				},
				type: "get",
				dataType: "json",
				//解决跨域问题
//         		xhrFields: {withCredentials: true},  
				success: function(data){	
					//清空二级分类下的option
					$("#classMajor").empty();
					//加上‘请选择’
					$('#classMajor').append('<option value="">请选择</option>');
					
					layui.use(['layer', 'form'], function(){
                        var form = layui.form;                           
						//接收返回的data
						secondData =data.data;
                        $.each(data.data, function (i, result) {
                            var index = data.data[i].id;
                            var text = data.data[i].majorname;
                            $("#classMajor").append('<option onclick="loadThree(' + result.id + ')" value="' + index + '">' + text + '</option>');
							//$("#classMajor").append('<option onclick="loadThree('+ secondData[i].majornumber+')" value="' + secondData[i].majornumber + '">' + secondData[i].majorname + '</option>');
						})
						//更新 lay-filter="classMajor" 所在容器内的全部 select 状态
						form.render('select','classMajor');
						//找到渲染后二级分类的dd（是个数组）
				        var dlChildSecond = $('#classMajor').siblings("div.layui-form-select").find('dl').children();
				        //找到渲染后的所有option（是个数组）
				        var optionSecond = $('#classMajor option');
				        //遍历dd,给每个dd添加onclick事件
				        for(var i = 1;i<dlChildSecond.length;i++){
							//拿到option的val值
							var a = $(optionSecond[i]).val();
							//将option的value值赋给loadThree()函数
							$(dlChildSecond[i]).attr('onclick','loadThree('+a+')');
				        }
					});
				},
				error: function(error) {
	                layui.use('layer', function () {
	                    var layer = layui.layer;
	                    layer.msg(error.responseText);
	                });
				}	
			});
	};
		//加载三级分类的方法
		function loadThree(id){
			$.ajax({
				async: false,
				url: "api/common/classlist",
				data: {
                    proid: id  //后台需要的：传给后台的
				},
				type: "get",
				dataType: "json",
				//解决跨域问题
//         		xhrFields: {withCredentials: true},  
           		success:function(data){
           			//清空三级分类下的option
					$("#classes").empty();
					
					$('#classes').append('<option value="">请选择</option>');
					layui.use(['layer', 'form'],function(){
                        var form = layui.form;                           
						//接收返回的data
						// threeData =data.data;
                        $.each(data.data, function (i, result) {
                            var index = data.data[i].id;
                            var text = data.data[i].classname;
                            $("#classes").append('<option value="' + index + '">' + text + '</option>');
                        });
						// for(var i = 0;i<threeData.length;i++){
						// 	$("#classes").append('<option value="' + threeData[i].id + '">' + threeData[i].classname + '</option>');
						// }
						form.render('select','classes');
					});
           		},
				error: function(error) {
	                layui.use('layer', function () {
	                    var layer = layui.layer;
	                    layer.msg(error.responseText);
	                });
				}	
			});
		};	