

    $(function () {
        //加载一级分类
        FillCate();

        //加载一级分类类别方法
        function FillCate() {
            $.ajax({
                type: "get",
                url: "api/teachmanager/catelist",
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
                            var text = data.data[i].category;
                            $("#test1").append('<option onclick="loadfirst(' + result.id + ')" value="' + index + '">' + text + '</option>');
                        });
                        form.render('select', 'test1');
                        //找到渲染后的dd
                        var dlChild = $('#test1').siblings("div.layui-form-select").find('dl').children();
                        //遍历dd,给每个dd添加onclick事件
                        for (var i = 1; i < dlChild.length; i++) {
                            $(dlChild[i]).attr('onclick', 'loadfirst(' + i + ')');
                        }
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

    //加载二级分类
    function loadfirst(id){
        $.ajax({
            type: "get",
            url: "api/teachmanager/prolist",
            data: {
                cateid: id
            },
            dataType: "json",
            async: false,
            xhrFields: {withCredentials: true},
            success: function (data) {
                //清空二级分类下的option
                $("#test2").empty();
                //from表单select 通用
                layui.use(['layer', 'form'], function () {
                    var form = layui.form;
                    var i = 0;
                    $.each(data.data, function (i, result) {
                        var index = data.data[i].id;
                        var text = data.data[i].majorname;
                        $("#test2").append('<option onclick="loadSecond(' + result.id + ')" value="' + index + '">' + text + '</option>');
                    });
                    form.render('select', 'test2');
                    //找到渲染后的dd
                    var dlChildSecond = $('#test2').siblings("div.layui-form-select").find('dl').children();
                    //找到渲染后的所有option（是个数组）
                    var optionSecond = $('#test2 option');
                    //遍历dd,给每个dd添加onclick事件
                    for (var i = 0; i < dlChildSecond.length; i++) {
                        var a = $(optionSecond[i]).val();
                        $(dlChildSecond[i]).attr('onclick', 'loadSecond(' + a + ')');
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

    //老师
    function loadSecond(id) {
        $.ajax({
            type: "get",
            url: "api/teachmanager/teacherlist",
            data: {
                majorid: id
            },
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
                        var text = data.data[i].tname;
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

    var callbackdata = function () {

        var selectText3=$("#test3 option:selected").text();
        var selectVal3=$("#test3 option:selected").val();
        var data = {
            username: selectText3,
            usernameid:selectVal3
        };
        return data;
    }