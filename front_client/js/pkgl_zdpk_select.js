
    $(function () {
        first();

        //查询教室类别并回显
        function first() {
            $.ajax({
                type: 'get',
                async: false,
                url: 'api/classroom/typelist',
                dataType: 'json',
                success: function (data) {
                    layui.use('form', function () {
                        var form = layui.form;
                        var i = 0;
                        $.each(data.data.list, function (i, result) {
                            $("#classroomType").append('<option onclick="queryClass(' + result.id + ')" value="' + result.id + '">' + result.catename + '</option>');
                        });
                        //更新 lay-filter="classroomType"所在容器内的全部 select 状态
                        form.render('select', 'classroomType');
                        //找到渲染后的dd
                        var dlChild = $('#classroomType').siblings("div.layui-form-select").find('dl').children();
                        //找到渲染后的所有option（是个数组）
				        var optionFirst = $('#classroomType option');
                        //遍历dd,给每个dd添加onclick事件
                        for (var i = 1; i < dlChild.length; i++){
                        	var b= $(optionFirst[i]).val();
                            $(dlChild[i]).attr('onclick', 'queryClass(' + b + ')');
                        }
                    })
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

    //根据教室类别查班级
    function queryClass(id) {
        $.ajax({
            type: 'get',
            async: false,
            url: 'api/classroom/roomlist',
            dataType: 'json',
            data: {
                cateid: id   //带到后台的教室类别id
            },
            success: function (data) {
                //清空二级分类下的option
                $("#classroom").empty();
                layui.use('form', function () {
                    var form = layui.form;
                    var i = 0;
                    $.each(data.data, function (i, result) {
                        $("#classroom").append('<option value="' + result.id + '">' + result.classroom + '</option>');
                    });
                    //更新 lay-filter="classroomType"所在容器内的全部 select 状态
                    form.render('select', 'classroom');
                })
            },
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
			}  
        })
    };