
    //from表单select 通用
    layui.use('form', function(){
        var form = layui.form;
    });

    //判断登录身份
    function xzclass() {
        $.ajax({
            type: 'get',
            url: "api/workplan/judgeheader",
            async: false,
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: function (data) {
                if (data.data == 0) {
                    //身份为校长-新增班级弹窗
                    layer.open({
                        title: '请选择',
                        type: 2,
                        area: ['500px', '400px'],
                        fixed: false, //不固定
                        maxmin: true, //开启最大化最小化按钮
                        content: 'bzrgl_bjgzjh_xz_alert.html',
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata();
                            $("#classname").val(res.username);
                            $("#classnameId").val(res.usernameid);
                            var bjClass=$("#classname").val();
                            if(bjClass=='请选择'){
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
                    });
                }
                else if(data.data==1){
                    //身份为班主任-新增班级弹窗
                    layer.open({
                        title: '请选择',
                        type: 2,
                        area: ['500px', '400px'],
                        fixed: false, //不固定
                        maxmin: true, //开启最大化最小化按钮
                        content: 'bzrgl_bjgzjh_xz_alert2.html',
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata2();
                            $("#classname").val(res.username);
                            $("#classnameId").val(res.usernameid);
                            var bjClass=$("#classname").val();
                            if(bjClass=='请选择'){
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
                    });

                }
            },
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
			}
        });
    };


    //新增
    function btnXz() {
        var className = document.getElementById("classname").value;
        var classNameId = document.getElementById("classnameId").value;
        var title = document.getElementById("title").value;
        var content = document.getElementById("content").value;
        if (className === ''  || title === ''|| content === '') {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('文本框不能为空！', {time: 1000});
            })
        }else if(className=="请选择"){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('请重新选择班级', {area: ['200px','180px']});
            });
            return false;
        }else {
            $.ajax({
                type: 'post',
                url: "api/workplan/save",
                async: false,
                data: {
                    classid:classNameId,
                    classname:className,
                    title:title,
                    content:content
                },
                dataType: 'json',
                xhrFields: {withCredentials: true},
                success: function (data) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('新增成功!', {area: ['150px', '50px']});
                    });
                    setTimeout(function () {
                        window.location.href = "bzrgl_bjgzjh.html";
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