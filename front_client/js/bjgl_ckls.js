
//	var a=0;
	var listDataClass;  //定义全局变量接收班级一级分类

//班级专业联动
$(function () {
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer;
        var form = layui.form;

        //班主任查询
        loadHeadTeacher();

        function loadHeadTeacher(){
            $.ajax({
                async: false, //同步
//				url: "api/teacher/queryteacherbyroleid?roleId=23",
                url: "api/teacher/queryteacherbyroleids",
                type: "get",
                dataType: "json",
                success: function (data) {
                    var i = 0;
                    $.each(data.data, function (i, result) {
                        $("#headTeacher").append('<option value="' + result.id + '">' + result.tname + '</option>');
                    });
                    // 更新 lay-filter="headTeacher" 所在容器内的全部 select 状态
                    form.render('select', 'headTeacher');
                },
                error: function (error) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg(error.responseText);
                        layer.msg(error.responseText,{area: ['150px', '50px']});
                        layer.msg(error.responseText, {area: ['150px', '50px']});
                    });
                }
            })
        };

        //班级专业联动查询
        //加载一级分类
        loadFirst();

        //加载一级分类的方法
        function loadFirst(){
            $.ajax({
                async: false, //同步
                url: "api/classes/catelist",
                type: "get",
                dataType: "json",
                xhrFields: {withCredentials: true},
                success: function (data) {
                    //接收返回的data
                    listDataClass = data.data.catelist;
                    var i = 0;
                    $.each(listDataClass, function (i, result) {
                        var ids = listDataClass[i];
                        var categorylist = listDataClass[i];
                        $("#classCategory").append('<option onclick="loadSecond(' + ids.id + ')" value="' + ids.id + '">' + categorylist.category + '</option>');
                    });
                    //更新 lay-filter="classCategory" 所在容器内的全部 select 状态
                    form.render('select', 'classCategory');
                    //找到渲染后的dd
                    var dlChild = $('#classCategory').siblings("div.layui-form-select").find('dl').children();
                    //遍历dd,给每个dd添加onclick事件
                    for (var j = 1; j < dlChild.length; j++) {
                        var index = listDataClass[j - 1].id;
                        $(dlChild[j]).attr('onclick', 'loadSecond(' + index + ')');
                    }
                },
                error: function (error) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg(error.responseText);
                        layer.msg(error.responseText,{area: ['150px', '50px']});
                        layer.msg(error.responseText, {area: ['150px', '50px']});
                    });
                }
            });
        };
    });
});

    //查询二级分类的方法
	function loadSecond(id){			
		$.ajax({
			async: false,
			url:"api/classes/prolist",
			data:{
                cateid: id //后台需要的：传给后台的
			},
			type: "get",
			dataType: "json",
			success: function(data){	
				//清空二级分类下的option
				$("#majorname").empty();
				//加上‘请选择’
				$('#majorname').append('<option value="">请选择</option>');
                layui.use(['layer', 'form'], function(){
                    var form = layui.form;
                    //接收返回的data
                    secondData =data.data.prolist;
                    for(var i = 0;i<secondData.length;i++){
                        $("#majorname").append('<option  value="' + secondData[i].majornumber + '">' + secondData[i].majorname + '</option>');
                    }
                    //更新 lay-filter="classMajor" 所在容器内的全部 select 状态
                    form.render('select','majorname');
                    //找到渲染后二级分类的dd（是个数组）
                    var dlChildSecond = $('#majorname').siblings("div.layui-form-select").find('dl').children();
                    //找到渲染后的所有option（是个数组）
                    var optionSecond = $('#majorname option');
                    //遍历dd,给每个dd添加onclick事件
                    for(var i = 0;i<dlChildSecond.length;i++){
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
	function loadThree(majornumber){
        $.ajax({
            async: false,
            url: "api/common/getTealist",
            data: {
                proid: majornumber  //后台需要的：传给后台的
            },
            type: "get",
            dataType: "json",
            success: function (data) {
                //清空三级分类下的option
                $("#classes").empty();
                //加上‘请选择’
				$('#classes').append('<option value="">请选择</option>');
                layui.use(['layer', 'form'], function () {
                    var form = layui.form;
                    //接收返回的data
                    threeData = data.data;
                    for (var i = 0; i < threeData.length; i++) {
                        $("#classes").append('<option value="' + threeData[i].id + '">' + threeData[i].tname + '</option>');
                    }
                    form.render('select', 'classes');
                });
            },
            error: function () {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请求三级分类错误');
                });
            }
        });
    };

//授课老师弹窗
// 	$('input[name=tname]').click(function(){
// 		layui.use('layer', function(){
//             var layer = layui.layer;
// 			layer.open({
// 			  type: 2,
// 			  title:'授课老师',
// 			  area: ['600px', '500px'],
// 			  content: ['bjgl_skls.html','no'], //这里content是一个普通的String
// 			  btn: ['确定', '取消'],
// 			  yes:function(index,layero){
// 		        	//当点击‘确定’按钮的时候，获取弹出层返回的值
// 				    var callBackData = window["layui-layer-iframe" + index].callbackdata();
// 				    //打印返回的值，看是否有我们想返回的值。
// //					    $('#teacher').id(callBackData.teacherId);
// 				   $('input[name=tname]').attr('id',callBackData.teacherId);
// 				   $('input[name=tname]').val(callBackData.teacherName);
// 				    //最后关闭弹出层
//                 	layer.close(index);
// 			  }
// 			});
// 		});
// 	});
