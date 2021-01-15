

    $(function () {
        //加载一级分类
        loadSecond();

        //班级
        function loadSecond() {
            $.ajax({
                type: "get",
                url: "api/common/getClass",
                dataType: "json",
                async: false,
                xhrFields: {withCredentials: true},
                success: function (data) {
                    //清空三级分类下的option
                    $("#test3").empty();
                    //from表单select 通用
                    layui.use(['layer', 'form'], function () {
                        var form = layui.form;
                        var i = 0;
                        $.each(data.data, function (i, result) {
                            var index = data.data[i].id;
                            var text = data.data[i].classname;
                            $("#test3").append('<option value="' + index + '">' + text + '</option>');
                        });
                        form.render('select', 'test3');
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



    var callbackdata = function () {

        var selectText3=$("#test3 option:selected").text();
        var selectVal3=$("#test3 option:selected").val();
        var data = {
            username: selectText3,
            usernameid:selectVal3
        };
        return data;
    }