
		//获取浏览地址id
    	 $.getUrlParam = function (name){
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }

        //选择专业弹窗
        function xgjob(){
            layer.open({
                title: '选择专业',
                type: 2,
                area: ['500px', '400px'],
                fixed: false, //不固定
                maxmin: true, //开启最大化最小化按钮
                content: 'yhgl_lsgl_xg_alert.html',
                btn: ['确定', '取消'],
                yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                    //当点击‘确定’按钮的时候，获取弹出层返回的值
                    var res = window["layui-layer-iframe" + index].callbackdata();
                    //打印返回的值，看是否有我们想返回的值。
                    $("#classMajor").val(res.username);
                    $("#classMajorId").val(res.usernameid);

                    //最后关闭弹出层
                    layer.close(index);
                }
                , btn2: function (index, layero) {

                }
            });
		}

        //手机号输入框失去焦点时判断手机号是否正确
        var myreg1 = /^[1][3,4,5,7,8,9][0-9]{9}$/;
        $("input[name='phone']").blur(function () {
            var phoneNew = $("input[name='phone']").val();
            var myreg = /^[1][3,4,5,7,8,9][0-9]{9}$/;
            if (myreg.test(phoneNew)) {
                console.log("手机号码填写正确")
            } else {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('手机号输入错误');
                });
            }
        });

        //验证姓名
        var regName1 =/^[\u4e00-\u9fa5]{2,4}$/;
        $("input[name='name']").blur(function () {
            var teachName = $("input[name='name']").val();
            var regName =/^[\u4e00-\u9fa5]{2,4}$/;
            if(!regName.test(teachName)){
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('真实姓名填写有误');
                });
                return false;
            }
        })

        //验证身份证号
        var idcardReg1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
        $("input[name='card']").blur(function () {
            var teachCard = $("input[name='card']").val();
            var idcardReg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
            if(!idcardReg.test(teachCard)){
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('身份证输入不合法');
                });
                return false;
            }
        })
        	
		//修改
        $("#changeBtn").click(function(){
        	var id=$.getUrlParam('id');

        	var teacherName=$("#name").val();  //姓名
            var teacherCard=$("#idCard").val();//身份证
            var teacherSex=$("#sex").val();
            var teacherNation=$("#nation").val();
            var teacherBirthday=$("#birthday").val();

			var address    = $('#address').val();
			var phone      = $('#phone').val(); //手机号
			var workUnit   = $('#workUnit').val();
			var classMajorId = $('#classMajorId').val();
			var classMajor = $('#classMajor').val();
            if (classMajor == '请选择') {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请将内容填写完整!');
                });
            }else if(!myreg1.test(phone) || !regName1.test(teacherName) || !idcardReg1.test(teacherCard)){
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请输入正确的姓名,手机号,身份证号!',{area:["270px","80px"]});
                });
            }else if (teacherName != '' && teacherSex != '' && teacherCard != '' && teacherNation != '' && address != '' && phone != '' && workUnit != '' && teacherBirthday != '') {
                $.ajax({
                    type: 'post',
                    async: false,
                    url: "api/teacher/update?id=" + id,
//              xhrFields: {withCredentials: true}, 
                    data: {
                        tname: teacherName,
                        sfzh: teacherCard,
                        sex: teacherSex,
                        nation: teacherNation,
                        birthdate: teacherBirthday,
                        sfzaddress: address,
                        phone: phone,
                        workunit: workUnit,
                        majorid: classMajorId,
                        majorname: classMajor,
                    },
                    success: function (data) {
                        if (data.code == 1) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg('修改成功', {area: ["170px", "50px"]});
                            });
                            setTimeout(function () {
                                window.location = "yhgl_lsgl.html";
                            }, 800)
                        } else {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg('已修改但修改失败！', {area: ["200px", "50px"]});
                            });
                        }
                    },
                    error: function (error) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg(error.responseText);
                        });
                    }
                })
            }else {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('所有信息不能为空!');
                });
            }
        })