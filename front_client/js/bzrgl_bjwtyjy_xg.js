
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
            url: "api/classsuggest/query",
            async: false,
            data: {
                id: id
            },
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: function (data) {
                $("#classname").val(data.data.classname);
                $("#classnameId").val(data.data.classid);
                $("#suggestTime").val(data.data.suggesttime);
                $("#title").val(data.data.title);
                $("#content").val(data.data.content);
                $("#reply").val(data.data.reply);
            },
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
			}
        });
    });

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

    //新增选择班级弹窗
    function xgClassName() {
        $.ajax({
            type: 'get',
            url: "api/common/judge",
            async: false,
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: function (data) {
                if (data.data == 0) {
                    //身份为校长-选择班级弹窗
                    layer.open({
                        title: '请选择',
                        type: 2,
                        area: ['500px', '400px'],
                        fixed: false, //不固定
                        maxmin: true, //开启最大化最小化按钮
                        content: 'bzrgl_tsxyjl_xz_tuiban_alert.html',
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata();
                            //打印返回的值，看是否有我们想返回的值。
                            (res);
                            $("#classname").val(res.username);
                            $("#classnameId").val(res.usernameid);
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
                        title: '请选择',
                        type: 2,
                        area: ['500px', '400px'],
                        fixed: false, //不固定
                        maxmin: true, //开启最大化最小化按钮
                        content: 'bzrgl_tsxyjl_xz_tuiban_alert2.html',
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata();
                            //打印返回的值，看是否有我们想返回的值。
                            (res);
                            $("#classname").val(res.username);
                            $("#classnameId").val(res.usernameid);
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

    //修改
    function btnXg() {
        var className = document.getElementById("classname").value;
        var classNameId = document.getElementById("classnameId").value;
        var time = document.getElementById("suggestTime").value;
        var title = document.getElementById("title").value;
        var content = document.getElementById("content").value;
        var reply = document.getElementById("reply").value;
        $.ajax({
            type: 'post',
            url: "api/classsuggest/update",
            async: false,
            data: {
                id:id,
                classid:classNameId,
                classname:className,
                suggesttime:time,
                title:title,
                content:content,
                reply:reply
            },
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: function (data) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('修改成功!', {area: ['150px', '50px']});
                });
                setTimeout(function () {
                    window.location.href = "bzrgl_bjwtyjy.html";
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