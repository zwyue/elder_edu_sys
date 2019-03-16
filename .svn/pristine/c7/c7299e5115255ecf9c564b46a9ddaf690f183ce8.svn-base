
    //定义一个全局变量接收返回的data
    var listData,secondData,threeData;
    $(function(){
        //加载一级分类
        loadFirst();

        //加载一级分类的方法
        function loadFirst(){
            //一级分类的父级代号
//          var id;
            $.ajax({
                async: false, //同步
                url: "api/common/catelist",
                // data: {
                //     id: id  //后台需要的：传给后台的
                // },
                type: "get",
                dataType: "json",
                success: function (data){
                    layui.use(['layer', 'form'], function(){
                        var form = layui.form;
                        //接收返回的data
                        listData = data.data;
                        var i = 0;
                        $.each(listData, function (i, result){
//                          var ids=listData[i];
//                          var type=listData[i];
                            $("#classCategory").append('<option onclick="loadSecond(' + result.id + ')" value="' + result.id + '">' + result.category + '</option>');
                        });
                        //更新 lay-filter="classCategory" 所在容器内的全部 select 状态
                        form.render('select', 'classCategory');
                        //找到渲染后的dd
                        var dlChild = $('#classCategory').siblings("div.layui-form-select").find('dl').children();
                        //找到渲染后的所有option（是个数组）
				        var optionSecond = $('#classCategory option');
                        //遍历dd,给每个dd添加onclick事件
                        for (var i = 1; i < dlChild.length; i++){
                        	//拿到option的val值
							var a = $(optionSecond[i]).val();
                            $(dlChild[i]).attr('onclick', 'loadSecond(' + a + ')');
                        }
                    });
                },
                error: function(error){
                    layui.use('layer', function (){
                        var layer = layui.layer;
                        layer.msg(error.responseText);
                    });
				}
            });
        };
    });

    //加载二级分类的方法
    function loadSecond(id){
        $.ajax({
            async: false,
            url: "api/common/prolist",
            data: {
                 cateid: id //后台需要的：传给后台的
            },
            type: "get",
            dataType: "json",
            success: function (data){
                //清空二级分类下的option
                $("#classMajor").empty();
                //加上‘请选择’
				$('#classMajor').append('<option value="">请选择</option>');
                
                layui.use(['layer', 'form'], function (){
                    var form = layui.form;
                    //接收返回的data
                    secondData = data.data;
                    for (var i = 0; i < secondData.length; i++){
                        $("#classMajor").append('<option onclick="loadThree(' + secondData[i].majornumber + ')" value="' + secondData[i].majornumber + '">' + secondData[i].majorname + '</option>');
                    }
                    //更新 lay-filter="classMajor" 所在容器内的全部 select 状态
                    form.render('select', 'classMajor');
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


    //获得,并回显到父窗口
    var callbackdata = function () {

        var selectText = $("#classMajor option:selected").text();
        var selectVal = $("#classMajor option:selected").val();
        var data = {
            username: selectText,
            usernameid: selectVal,
        };
        return data;
    }