

    layui.use('form',function(){
        var form = layui.form;
        //定义星期数据
        var weekArr = [
            {value: '1', name: '周一'},
            {value: '2', name: '周二'},
            {value: '3', name: '周三'},
            {value: '4', name: '周四'},
            {value: '5', name: '周五'},
            {value: '6', name: '周六'},
            {value: '7', name: '周天'},
        ];
        $.each(weekArr, function () {
            $('<option value="' + this.value + '">' + this.name + '</option>').appendTo('#week');
        });
        //更新 lay-filter="weekTime"所在容器内的全部 select 状态
        form.render('select', 'weekTime');
        //找到渲染后的dd
        var dlChild = $('#week').siblings("div.layui-form-select").find('dl').children();
        //遍历dd,给每个dd添加onclick事件
        for (var i = 1; i < dlChild.length; i++) {
            $(dlChild[i]).attr('onclick', 'queryTime(' + i + ')');
        }
    });

    //根据选择的星期查询那一天的时间段
    $.ajax({
        type: 'post',
        async: false,
        url: 'api/schedule-class/pre/list',
        dataType: 'json',
        success: function (data) {
            layui.use('form', function () {
                var form = layui.form;
                var i = 0;
                $.each(data.data.list, function (i, result) {
                    $("#time").append('<option value="' + result.id + '">' + result.time + '</option>');
                });
                //更新 lay-filter="weekTime"所在容器内的全部 select 状态
                form.render('select', 'weekTime');
            })
        },
        error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
			}  
    })


    //保存自动排课信息
    $('#saveClass').click(function () {
        var weekId = $('#week option:selected').val();   //weekId
        var timeId = $('#time option:selected').val();   //时间段id
//		var timeText   = $('#time option:selected').text();
//		var classroomTypeId = $('#classroomType option:selected').val();  //教室类别id
//		var classroomTypeText = $('#classroomType option:selected').text();
//		var classroomId =  $('#classroom option:selected').val();
        var classroomText = $('#classroom option:selected').text();       //教室名称
//		var classCategoryId = $('#classCategory option:selected').val();  //班级类别id
//		var classCategoryText = $('#classCategory option:selected').text();
//		var classMajorId = $('#classMajor option:selected').val();    //班级专业id
//		var classMajorText = $('#classMajor option:selected').text();
        var classesId = $('#classes option:selected').val();    //班级id
        var classesText = $('#classes option:selected').text();
        //点击保存
        $.ajax({
            type: 'post',
            async: false,
            dataType: 'json',
            data: {
                week: weekId,   					 //weekid
                courseid: timeId,                 //时间段id
                classroom: classroomText,         //教室名称
                classid: classesId,       		 //班级id
                classname: classesText    		 //班级名称
            },
            url: 'api/schedule-class/schedule-course',
            success: function (data) {
                //判断教室是否被占用
                if (data.code == 1) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('保存排课信息成功');
                    });
                    setTimeout(function () {
                        window.location.reload();  //刷新本页面
                    }, 800);
                } else {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg(data.msg);
                    });
                }
            },
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
            }
        })
    })
