
// var listData;
    $(function () {
        //加载一级分类
        loadSecond();

        //加载一级分类-班级
        function loadSecond() {
            $.ajax({
                type: "get",
                url: "api/common/getClass",
                dataType: "json",
                async: false,
                xhrFields: {withCredentials: true},
                success: function (data) {
                    //from表单select 通用
                    layui.use(['layer', 'form'], function () {
                        var form = layui.form;
                        var i = 0;
                        $.each(data.data, function (i, result) {
                            var index = data.data[i].id;
                            var text = data.data[i].classname;
                            $("#test3").append('<option onclick="loadFinal(' + result.id + ')" value="' + index + '">' + text + '</option>');
                        });
                        form.render('select', 'test3');

                        //找到渲染后的dd
                        var dlChildSecond = $('#test3').siblings().children(":last").children();
                        //找到渲染后的所有option（是个数组）
                        var optionSecond = $('#test3 option');
                        //遍历dd,给每个dd添加onclick事件
                        for (var i = 1; i < dlChildSecond.length; i++){
                             var a = $(optionSecond[i]).val();
                             $(dlChildSecond[i]).attr('onclick', 'loadFinal(' + a + ')');
                        }
                    })
                },
                error: function(error) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg(error.responseText);
                    });
                }
            });
        }
    })

    //加载二级分类-学生
    function loadFinal(id) {
        $.ajax({
            type: "get",
            url: "api/common/studentlist",
            data: {
                classid: id
            },
            dataType: "json",
            async: false,
            xhrFields: {withCredentials: true},
            success: function (data) {
                //清空三级分类下的option
                $("#test4").empty();
                //from表单select 通用
                layui.use(['layer', 'form'], function () {
                    var form = layui.form;
                    var i = 0;
                    $.each(data.data, function (i, result) {
                        var index = data.data[i].id;
                        var text = data.data[i].stuname;
                        $("#test4").append('<option value="' + index + '">' + text + '</option>');
                        $("#linkType").val(data.data[i].phone);
                        $("#xuehao").val(data.data[i].stunumber);
                        $("#idCard").val(data.data[i].sfzh);
                    });
                    form.render('select', 'test4');
                })
            },
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
            }
        });
    }


    //获得四级学生,并回显到父窗口
    var callbackdata = function () {

        var selectText4=$("#test4 option:selected").text();
        var selectVal4=$("#test4 option:selected").val();
        var linkType=$("#linkType").val();
        var XH=$("#xuehao").val();
        var idcard=$("#idCard").val();
        var data = {
            username: selectText4,
            usernameid:selectVal4,
            link:linkType,
            xuehao:XH,
            idCard:idcard
        };
        return data;
    }