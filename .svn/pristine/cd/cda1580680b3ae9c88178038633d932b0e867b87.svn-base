
	//定义全局变量后台接收
	var cateInfoData;      //班级专业
	var headmastersData;   //班主任
	var classData;         //班级其他信息
	$(function(){
    	//获取浏览地址id
    	 $.getUrlParam = function (name){
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
    	var id=$.getUrlParam('id');
 		
 		layui.use([ 'layer','form'], function(){
            var layer = layui.layer;
            var form = layui.form;
            
    	//查询到id，并把表格页面的值带到修改页面
        $.ajax({
            type: 'GET',
            url: "api/classes/query?id="+id,
            async: true,
            dataType:'json',
            success: function (data){
            	cateInfoData = data.data.cateInfo;        //班级专业
            	headmastersData = data.data.headmasters;  //班主任姓名
            	classInfoData   = data.data.classInfo;    //班级其他信息
            	//将后台返回的数据回填到表单里
                $("input[name='classname']").val(classInfoData.classname);  
                $("input[name='plansize']").val(classInfoData.plansize); 
                $("#classes").text(classInfoData.tname);   //授课老师
                //班主任下拉框回显(查询回显接口里应包含下拉列表select类的接口,使其下拉列表类id与选中id相等)
                var i = 0;
                $.each(headmastersData, function(i,result){
                    var index = headmastersData[i].id;    
                    var headmaster = headmastersData[i].tname;
                    $("#headTeacher").append('<option value="'+index+'">'+headmaster+'</option>');
                    if (index == classInfoData.headmaster){
                        $("#headTeacher").val(index);
                    }
                    //更新 lay-filter="classCategory" 所在容器内的全部 select 状态
                    form.render('select','headTeacher');
               });
				//班级分类下拉框回显(查询回显接口里应包含下拉列表select类的接口,使其下拉列表类id与选中id相等)
                var j = 0;
                $.each(cateInfoData, function(j,result){
                    var index = cateInfoData[j].id;    //
                    var category = cateInfoData[j].category;
                    $("#classCategory").append('<option  onclick="loadSecond(' + index + ')" value="'+index+'">'+category+'</option>');
                    if (index == classInfoData.cateid){
                        $("#classCategory").val(index); 
                    }
                    //更新 lay-filter="classCategory" 所在容器内的全部 select 状态
                    form.render('select','classCategory');
                   
                    //找到渲染后的dd
                    var dlChild = $('#classCategory').siblings("div.layui-form-select").find('dl').children();
                    //遍历dd,给每个dd添加onclick事件
                    for (var i = 0; i < dlChild.length; i++){     
                    	var indexData = cateInfoData[i].id;
                        $(dlChild[i]).attr('onclick', 'loadSecond(' + indexData + ')');
                    }
                });
                $("#majorname").append('<option value="' + classInfoData.majorid + '">' + classInfoData.majorname + '</option>');
                form.render('select','majorname');
                //授课老师回显
                $("#classes").append('<option value="' + classInfoData.tid + '">' + classInfoData.tname + '</option>');
                form.render('select','classes');
			},
            error: function() {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请求一级分类错误');
                });
			}
        });
    })
	
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
            error: function() {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请求二级分类错误');
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
	
// 	//授课老师弹窗
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
// 				    (callBackData);
// //					    $('#teacher').id(callBackData.teacherId);
// 				   $('input[name=tname]').attr('id',callBackData.teacherId);
// 				   $('input[name=tname]').val(callBackData.teacherName);
// 				    //最后关闭弹出层
//                 	layer.close(index);
// 			  }
// 			});
// 		});
// 	});