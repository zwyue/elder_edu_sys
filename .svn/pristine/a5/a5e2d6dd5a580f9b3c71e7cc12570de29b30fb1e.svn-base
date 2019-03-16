

    $(function () {
        //加载一级分类
        FillCate();

        //加载一级分类类别方法
        function FillCate() {
            $.ajax({
                type: "get",
                url: "api/roster/identity",
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
                            var text = data.data[i].duties;
                            $("#test1").append('<option value="' + index + '">' + text + '</option>');
                        });
                        form.render();
                    });
                },
                error: function(error) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg(error.responseText);
                    });
                }
            });
        };
    })


    //获得职务,并回显到父窗口
    var callbackdata = function () {

        var selectText=$("#test1 option:selected").text();
        var selectTextVal=$("#test1 option:selected").val();
        var data = {
            zhiwu: selectText,
            zhiwuVal: selectTextVal
        };
        return data;
    }