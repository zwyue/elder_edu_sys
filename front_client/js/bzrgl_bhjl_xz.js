
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
    var week=['第1周','第2周','第3周','第4周','第5周','第6周','第7周','第8周',
        '第9周','第10周','第11周','第12周','第13周','第14周','第15周','第16周',
        '第17周','第18周','第19周','第20周','第21周'];
    var i=0;
    for(var i=0; i<week.length;i++) {
        $("#Week").append('<option>'+week[i]+'</option>')
    }

    //判断登录身份
    function xzclass() {
        $.ajax({
            type: 'get',
            url: "api/common/judge",
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
                        content: 'bzrgl_tsxyjl_xz_tuiban_alert.html',
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata();
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
                    //身份为班主任-新增班级弹窗
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

                            $("#classname").val(res.username);
                            $("#classnameId").val(res.usernameid);
                            //最后关闭弹出层
                            layer.close(index);
                        }
                        , btn2: function (index, layero) {

                        }
                    });

                }
            },
            error: function () {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请求身份失败！', {area: ['150px', '50px']});
                });
            }
        });
    };


    //新增
    function btnXz() {
        var className = document.getElementById("classname").value;
        var classNameId = document.getElementById("classnameId").value;
        var time = document.getElementById("time").value;
        var title = document.getElementById("title").value;
        var week =$("#Week option:selected").text();
        var remark = document.getElementById("remark").value;
        if (className === ''  || title === ''|| time === '') {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('文本框不能为空！', {time: 1000});
            })
        }else if(week=="请选择"){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('周次选择不正确', {area: ['200px','180px']});
            });
            return false;
        }else {
            $.ajax({
                type: 'post',
                url: "api/meetrecord/save",
                async: false,
                data: {
                    classid:classNameId,
                    classname:className,
                    weeksort:week,
                    content:title,
                    meettime:time,
                    remark:remark
                },
                dataType: 'json',
                xhrFields: {withCredentials: true},
                success: function (data) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('新增成功!', {area: ['150px', '50px']});
                    });
                    setTimeout(function () {
                        window.location.href = "bzrgl_bhjl.html";
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