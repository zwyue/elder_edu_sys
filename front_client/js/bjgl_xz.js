
	//新增班级
	$('#addClass').click(function(){
		var className = $("input[name='classname']").val();                //班级名称
		var headTeacherId = $('#headTeacher option:selected').val();       //班主任id
		var headTeacherName = $('#headTeacher option:selected').text();    //班主任姓名
		var classCategoryId = $('#classCategory option:selected').val();   
		var classCategoryName = $('#classCategory option:selected').text(); //班级类别
		var classMajorId   =$('#majorname option:selected').val();
		var classMajorName   =$('#majorname option:selected').text();    //班级专业
		var plansize  = $('input[name=plansize]').val();    //班级人数
		var teacherId  = $('#classes option:selected').val();
		var teacherName  = $('#classes option:selected').text();    //授课老师
			
		if(className =='' || plansize=='' || teacherName == '' || classCategoryName=='' || classMajorName==''){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('请完善信息', {area: ['200px']});
            });
            return false;
		}else if(headTeacherName=="请选择" || classMajorName=="请选择" || teacherName=="请选择"){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('请将内容填写完整', {area: ['200px']});
            });
            return false;
		}else{
			$.ajax({
			async: false, //同步
			url: "api/classes/newclass",
			type: "POST",
			data:{
				classname:className,
				headmaster:headTeacherId,
				headmastername:headTeacherName,
				cateid:classCategoryId,
				catename:classCategoryName,
				majorid:classMajorId,
				majorname:classMajorName,
				plansize:plansize,
				tid:teacherId,
				tname:teacherName
			},
			dataType: "json",
			success:function(data){
				layui.use('layer', function(){
				   var layer = layui.layer;					  
				   layer.msg('添加班级成功');
				});   
				setTimeout(function(){
					window.location="bjgl.html"
				},800);
			},
			error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
			}
		})
		}
	})
