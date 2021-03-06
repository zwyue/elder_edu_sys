
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
    $(function () {
        var username=$("#user",parent.parent.document).text();
        var teacher=username.substr(3);
        $("#fdTeacher").val(teacher);
    })
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
                            //联系方式和学号
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
                            //取得弹窗学生姓名
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
                            //联系方式和学号
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
            },
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
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
            },
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
            }
        })
    }


    //新增
    function btnXz() {
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
        //隐藏域
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
                url: "api/specialmanager/save",
                async: false,
                data: {
                    sname:stuName,
                    sid:stuNameId,
                    sex:stuSex,
                    types:stuResult,
                    classname:tbClass,
                    classid:tbClassId,
                    content:mainResult,
                    times:time,
                    address:area,
                    tname:fdTeacher,
                    question:question,
                    options:massage,
                    phone:linkType,
                    stunumber:stuNumber,
                    idcard:idcard
                },
                dataType: 'json',
                xhrFields: {withCredentials: true},
                success: function (data) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('新增成功!', {area: ['150px', '50px']});
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