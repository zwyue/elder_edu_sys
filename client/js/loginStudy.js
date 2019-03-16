

    //aiax请求数据
    function ajax_get(){
        $("#loginBtn").click(function () {
            var userName = $("#user_name").val();
            var passWord = $("#password").val();
            $.ajax({
                url: "api/index/login",
                type: 'POST',
                dataType: 'json',
                async: false,
                data: {
                    'tnumber': userName,
                    'password': passWord
                },
                success: function (data) {
                    if (data.code == 1) {
                        window.location = "common/comm_left.html";
                    } else {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg(data.msg);
                        });
                    }
                },
                error: function(error) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        var msg = error.responseText.replace(/^\s*|\s*$/g,"");
                        msg = msg==""?"系统错误":msg;
                        layer.msg(msg);
                    });
                }
            })
        })
    };
    ajax_get();

    //登录和忘记密码切换
    // $(".forgetPass").click(function () {
    //     $("#login").css("display","none");
    //     $("#forgetPass").css("display","block");
    // })

    // $(".fhLogin").click(function () {
    //     $("#forgetPass").css("display","none");
    //     $("#login").css("display","block");
    // })