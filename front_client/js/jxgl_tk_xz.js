
    //from表单select 通用
    layui.use('form', function(){
        var form = layui.form;
    });

    //时间控件
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        lay('.test-item').each(function () {
            laydate.render({
                elem: this
                , type: 'datetime'
            });
        });
    });

    //倒课-新增弹窗
    function qjrpeople() {
        layer.open({
            title: '请选择',
            type: 2,
            area: ['500px', '400px'],
            fixed: false, //不固定
            maxmin: true, //开启最大化最小化按钮
            content: 'jxgl_tk_xz_alert.html',
            btn: ['确定', '取消'],
            yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                //当点击‘确定’按钮的时候，获取弹出层返回的值
                var res = window["layui-layer-iframe" + index].callbackdata();
                //打印返回的值，看是否有我们想返回的值。
                $("#leaveName").val(res.username);
                $("#leaveNameId").val(res.usernameid);
                //判断选择停课人是否正确
                var teacherName=$("#leaveName").val();
                if(teacherName=='请选择'){
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('请选择老师', {area: ['200px','180px']});
                    });
                    return false;
                }
                //最后关闭弹出层
                layer.close(index);
            }
            , btn2: function (index, layero) {

            }
        });
    };

    //倒课-补课教室弹窗
    function MakeupClassRoom() {
        layer.open({
            title: '请选择',
            type: 2,
            area: ['500px', '400px'],
            fixed: false, //不固定
            maxmin: true, //开启最大化最小化按钮
            content: 'jxgl_qj_xz_classrome_alert.html',
            btn: ['确定', '取消'],
            yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                //当点击‘确定’按钮的时候，获取弹出层返回的值
                var res = window["layui-layer-iframe" + index].callbackdata();
                //打印返回的值，看是否有我们想返回的值。
                $("#MakeupClass").val(res.classRoom);
                $("#MakeupClassId").val(res.classRoomId);
                //判断选择新增教室是否正确
                var MakeupClassRoom = $("#MakeupClass").val();
                if (MakeupClassRoom == '请选择') {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.alert('请选择倒课教室', {area: ['200px', '180px']});
                    });
                    return false;
                }
                //最后关闭弹出层
                layer.close(index);
            }
            , btn2: function (index, layero) {

            }
        });
    };

    //获得倒课班级下拉列表
    $(function () {
        $.ajax({
            type: "get",
            url: "api/teachmanager/classlist",
            dataType: "json",
            async: false,
            xhrFields: {withCredentials: true},
            success: function (data) {
                var i = 0;
                $.each(data.data, function (i, result) {
                    var index = data.data[i].id;
                    var text = data.data[i].classname;
                    $("#leaveClass").append('<option value="' + index + '">' + text + '</option>');
                })
            },
            error: function(error) {
	                layui.use('layer', function () {
	                    var layer = layui.layer;
	                    layer.msg(error.responseText);
	                });	
            	}
        });

        var week = ['第1周', '第2周', '第3周', '第4周', '第5周', '第6周', '第7周', '第8周',
            '第9周', '第10周', '第11周', '第12周', '第13周', '第14周', '第15周', '第16周',
            '第17周', '第18周', '第19周', '第20周', '第21周'];
        var i = 0;
        for (var i = 0; i < week.length; i++) {
            $("#qjWeek").append('<option>' + week[i] + '</option>')
        }

    })

    //新增
    function btnXz() {
        var qjLeaveName = document.getElementById("leaveName").value;
        var qjLeaveNameId = document.getElementById("leaveNameId").value;
        var qjLeaveDate = document.getElementById("testblue1").value;
        var qjendDate = document.getElementById("testblue2").value;
        var qjLeaveClass = $("#leaveClass option:selected").text();
        var qjLeaveClassId = $("#leaveClass option:selected").val();
        var qjMakeupDate = document.getElementById("testblue3").value;
        var qjMakeupDate2 = document.getElementById("testblue4").value;
        var qjMakeupClass = document.getElementById("MakeupClass").value;
        var qjMakeupClassId = document.getElementById("MakeupClassId").value;
        var qjWeek = $("#qjWeek option:selected").text();
        var qjSuggest = document.getElementById("suggest").value;
        if (qjLeaveName === '' || qjLeaveDate === '' || qjLeaveClass === '' || qjMakeupDate === '' || qjMakeupClass === '') {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('文本框不能为空！', {time: 1000});
            })
        }else if(qjLeaveName==="请选择" || qjLeaveClass==="请选择" || qjWeek==="请选择" || qjMakeupClass==="请选择"){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('请将内容填写完整', {area: ['200px','180px']});
            });
            return false;
        }else {
            $.ajax({
                type: 'post',
                url: "api/teachmanager/save",
                async: false,
                data: {
                    type: 1,
                    tname: qjLeaveName,
                    tid: qjLeaveNameId,
                    starttime: qjLeaveDate,
                    endtime: qjendDate,
                    classname: qjLeaveClass,
                    classid: qjLeaveClassId,
                    bkkssj: qjMakeupDate,
                    bkjssj: qjMakeupDate2,
                    roomname: qjMakeupClass,
                    roomid:qjMakeupClassId,
                    weeks: qjWeek,
                    issure: qjSuggest
                },
                dataType: 'json',
                xhrFields: {withCredentials: true},
                success: function (data) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('新增成功!', {area: ['150px', '50px']});
                    });
                    setTimeout(function () {
                        window.location.href = "jxgl_tk.html";
                    }, 2000)
                },
                error: function(error) {
	                layui.use('layer', function () {
	                    var layer = layui.layer;
	                    layer.msg(error.responseText);
	                });	
            	}
            });
        }
    }