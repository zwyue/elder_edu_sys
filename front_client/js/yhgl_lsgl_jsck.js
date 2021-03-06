		layui.use('form', function(){
			//预先加载form表单模块
		    var form = layui.form;										
	    });
	    //获取浏览地址id
    	$.getUrlParam = function (name){
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
    	var id=$.getUrlParam('id');
        $(function(){
        	//查询到id，并把表格页面的值带到修改页面
            $.ajax({
                type: 'GET',
                url: "api/teacher/detail?id="+id,
                async: false,
                data:{
                    id:id
                },
                dataType:'json',
//              xhrFields: {withCredentials: true},
                success: function (data) {
                	//将后台返回的数据回填到表单里
                    $("#name").val(data.data.tname);
                    $("#idCard").val(data.data.sfzh);
                    $("#sex").val(data.data.sex);
                    $("#nation").val(data.data.nation);
                    $("#phone").val(data.data.phone);
                    $("#birthday").val(data.data.birthdate);
                    $("#nation").val(data.data.nation);
                    $("#address").val(data.data.sfzaddress);
                    $("#workUnit").val(data.data.workunit);
                    //$("#classCategory").append('<option value="' + data.data.cateId + '">' + data.data.cateName + '</option>');
 					//$("#classMajor").val('<option  value="' + data.data.majorid + '">' + data.data.majorname + '</option>');
 					$("#classMajor").val(data.data.majorname);
 					$("#classes").append('<option  value="' + data.data.classid + '">' + data.data.classname + '</option>');
                },
                error: function(error) {
	                layui.use('layer', function () {
	                    var layer = layui.layer;
	                    layer.msg(error.responseText);
	                });
				}
            });   
    });
            
     //一键离职
     $('#quitBtn').click(function(){
     	("id："+id);
		
		layui.use('layer', function () {
			layer.confirm('确认要一键离职吗？', function (index) {
				$.ajax({
					type: "get",
					async: false,
					url: "api/teacher/delete",
					data:{
						id:id
					},
					success: function (data) {
						if(data.code==1){
							layer.msg('离职成功!', {icon:1, time:1000});
							window.location.href = 'yhgl_lsgl.html';
						}
						if(data.code==0){
							layer.msg('离职失败!', {icon:2, time:1000});
						}
					},
                    error: function(error) {
						layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg(error.responseText);
                        });
                    }
				});
				layer.close(index);
			});
		});
     });     