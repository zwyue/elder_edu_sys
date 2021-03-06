
    //获取浏览地址id
    (function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        }
    })(jQuery);
    var id = $.getUrlParam('id');
    // 查询到id，并把表格页面的值带到修改页面
    $(function () {
        $.ajax({
            type: 'GET',
            url: "api/specialmanager/query",
            async: false,
            data: {
                id: id
            },
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: function (data) {
                $("#stuName").val(data.data.sname);
                $("#stuNameValue").val(data.data.sid);
                $("#linkType").val(data.data.phone);
                $("#xuehao").val(data.data.stunumber);
                $("#stuSex").val(data.data.sex);
                $("#stuResult").val(data.data.types);
                $("#tbClass").val(data.data.classname);
                $("#tbClassId").val(data.data.classid);
                $("#mainResult").val(data.data.content);
                $("#time").val(data.data.filtime);
                $("#area").val(data.data.address);
                $("#question").val(data.data.question);
                $("#fdMassage").val(data.data.options);
                $("#idCard").val(data.data.idcard);
            },
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
            }
        });
    });
    $(function () {
        var username = $("#user", parent.parent.document).text();
        var teacher = username.substr(3);
        $("#fdTeacher").val(teacher);
    })
    //from表单select 通用
    layui.use('form', function(){
        var form = layui.form;
    });
    //时间控件
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        lay('.test-item').each(function(){
            laydate.render({
                elem:this
                ,type: 'datetime'
            });
        });

    });

    //新增选择学生弹窗
    function xzstudent() {
        $.ajax({
            type: 'get',
            url: "api/common/judge",
            async: false,
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: function (data) {
                if (data.data == 0) {
                    //身份为校长-学生姓名弹窗
                    layer.open({
                        title: '选择学生',
                        type: 2,
                        area: ['500px', '400px'],
                        fixed: false, //不固定
                        maxmin: true, //开启最大化最小化按钮
                        content: 'bzrgl_tsxyjl_xz_name_alert.html',
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata();
                            $("#stuName").val(res.username);
                            $("#stuNameValue").val(res.usernameid);
                            //判断选择学生姓名是否正确
                            var studentName=$("#stuName").val();
                            if(studentName=='请选择'){
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.alert('请选择学生', {area: ['200px','180px']});
                                });
                                return false;
                            }
                            //联系方式和学号,身份证
                            $("#linkType").val(res.link);
                            $("#xuehao").val(res.xuehao);
                            $("#idCard").val(res.idCard);
                            //最后关闭弹出层
                            layer.close(index);
                        }
                        , btn2: function (index, layero) {

                        }
                    });
                }
                else if(data.data==1){
                    //身份为班主任-新增学生姓名弹窗
                    layer.open({
                        title: '选择学生',
                        type: 2,
                        area: ['500px', '400px'],
                        fixed: false, //不固定
                        maxmin: true, //开启最大化最小化按钮
                        content: 'bzrgl_tsxyjl_xz_name_alert2.html',
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata();

                            $("#stuName").val(res.username);
                            $("#stuNameValue").val(res.usernameid);
                            //判断选择学生姓名是否正确
                            var studentName=$("#stuName").val();
                            if(studentName=='请选择'){
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.alert('请选择学生', {area: ['200px','180px']});
                                });
                                return false;
                            }
                            //联系方式和学号,身份证
                            $("#linkType").val(res.link);
                            $("#xuehao").val(res.xuehao);
                            $("#idCard").val(res.idCard);
                            //最后关闭弹出层
                            layer.close(index);
                        }
                        , btn2: function (index, layero) {

                        }
                    });
                }
            }
        })
    }
    //选择退班班级弹窗
    function tbclass() {
        $.ajax({
            type: 'get',
            url: "api/common/judge",
            async: false,
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: function (data) {
                if (data.data == 0) {
                    //身份为校长-退班班级弹窗
                    layer.open({
                        title: '选择班级',
                        type: 2,
                        area: ['500px', '400px'],
                        fixed: false, //不固定
                        maxmin: true, //开启最大化最小化按钮
                        content: 'bzrgl_tsxyjl_xz_tuiban_alert.html',
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata();

                            $("#tbClass").val(res.username);
                            $("#tbClassId").val(res.usernameid);
                            //判断选择学生姓名是否正确
                            var studentClass=$("#tbClass").val();
                            if(studentClass=='请选择'){
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.alert('请选择班级', {area: ['200px','180px']});
                                });
                                return false;
                            }
                            //最后关闭弹出层
                            layer.close(index);
                        }
                        , btn2: function (index, layero) {

                        }
                    })
                }
                else if (data.data == 1) {
                    //身份为班主任-退班班级弹窗
                    layer.open({
                        title: '选择班级',
                        type: 2,
                        area: ['500px', '400px'],
                        fixed: false, //不固定
                        maxmin: true, //开启最大化最小化按钮
                        content: 'bzrgl_tsxyjl_xz_tuiban_alert2.html',
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata();

                            $("#tbClass").val(res.username);
                            $("#tbClassId").val(res.usernameid);
                            //判断选择学生姓名是否正确
                            var studentClass=$("#tbClass").val();
                            if(studentClass=='请选择'){
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.alert('请选择班级', {area: ['200px','180px']});
                                });
                                return false;
                            }
                            //最后关闭弹出层
                            layer.close(index);
                        }
                        , btn2: function (index, layero) {

                        }
                    })
                }
            }
        })
    }


    //修改
    function btnXg() {
        var stuName = document.getElementById("stuName").value;
        var stuNameId = document.getElementById("stuNameValue").value;
        var stuSex = $("#stuSex option:selected").val();
        var stuResult = $("#stuResult option:selected").val();
        var tbClass = document.getElementById("tbClass").value;
        var tbClassId = document.getElementById("tbClassId").value;
        var mainResult = document.getElementById("mainResult").value;

        var time = document.getElementById("time").value;
        var area = document.getElementById("area").value;
        var fdTeacher = document.getElementById("fdTeacher").value;
        var question = document.getElementById("question").value;
        var massage = document.getElementById("fdMassage").value;

        var linkType = document.getElementById("linkType").value;
        var stuNumber = document.getElementById("xuehao").value;
        var idcard = document.getElementById("idCard").value;
        if (stuName === ''  || stuSex === ''|| stuResult === '' || tbClass === '') {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('文本框不能为空！', {time: 1000});
            })
        }else if(stuResult=="" || stuName=="请选择" || tbClass==""){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('请将内容填写完整', {area: ['200px','180px']});
            });
            return false;
        }else {
            $.ajax({
                type: 'post',
                url: "api/specialmanager/update",
                async: false,
                data: {
                    id: id,
                    sname: stuName,
                    sid: stuNameId,
                    sex: stuSex,
                    types: stuResult,
                    classname: tbClass,
                    classid: tbClassId,
                    content: mainResult,
                    times: time,
                    address: area,
                    tname: fdTeacher,
                    question: question,
                    options: massage,
                    phone: linkType,
                    stunumber: stuNumber,
                    idcard: idcard
                },
                dataType: 'json',
                xhrFields: {withCredentials: true},
                success: function (data) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('修改成功!', {area: ['150px', '50px']});
                    });
                    setTimeout(function () {
                        window.location.href = "bzrgl_tsxyjl.html";
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