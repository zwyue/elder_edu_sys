
    var week=['第1周','第2周','第3周','第4周','第5周','第6周','第7周','第8周',
        '第9周','第10周','第11周','第12周','第13周','第14周','第15周','第16周',
        '第17周','第18周','第19周','第20周','第21周'];
    var i=0;
    for(var i=0; i<week.length;i++){
        $("#Weekly").append('<option value='+week[i]+'>'+week[i]+'</option>')
    };
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
            url: "api/meetrecord/query",
            async: false,
            data: {
                id: id
            },
            dataType: 'json',
            xhrFields: {withCredentials: true},
            success: function (data) {
                //下拉框回显
                var num = data.data.weeksort;   //获取后台选中的
                var numbers = $("#Weekly").find("option"); //获取select下拉框的所有值
                for (var j = 1; j < numbers.length; j++) {
                    if ($(numbers[j]).val() == num) {
                        $(numbers[j]).attr("selected", "selected");
                    }
                }
                // $('#Weekly option:selected').checked(data.data.weeksort);
                $("#classname").val(data.data.classname);
                $("#classnameId").val(data.data.classid);
                $("#title").val(data.data.content);
                $("#remark").val(data.data.remark);
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

    //修改选择班级弹窗
    function xgClass() {
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
        var title = document.getElementById("title").value;
        var week =$("#Weekly option:selected").text();
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
                url: "api/meetrecord/update",
                async: false,
                data: {
                    id: id,
                    classid: classNameId,
                    classname: className,
                    weeksort: week,
                    content: title,
                    remark: remark
                },
                dataType: 'json',
                xhrFields: {withCredentials: true},
                success: function (data) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('修改成功!', {area: ['150px', '50px']});
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