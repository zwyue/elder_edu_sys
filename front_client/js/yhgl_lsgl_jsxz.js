	layui.use(['layer', 'form'], function (){
        var form = layui.form;
        //自定义验证规则  
        form.verify({
            name: function (value) {
                if (value.length < 2) {
                    return '请输入至少2位的用户名';
                }
            },
            phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！']
        }); 
	});
		
	//手机号输入框失去焦点时判断手机号是否正确
	testPhone();
	function testPhone(){
		var myreg1 = /^[1][3,4,5,7,8,9][0-9]{9}$/;
	    $("input[name='phone']").blur(function (){
	        var phoneNew = $("input[name='phone']").val();
	        var myreg = /^[1][3,4,5,7,8,9][0-9]{9}$/;
	        if (myreg.test(phoneNew)){
	            console.log("手机号码填写正确")
	        }else {
	            layui.use('layer', function (){
	                var layer = layui.layer;
	                layer.msg('手机号输入错误');
	            });
	        }
	    });
	}


    //验证姓名
    testName();
    function testName(){
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
    };
  
	//验证身份证号
	testIdCard();
	function testIdCard(){
		var idcardReg1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	    $("input[name='card']").blur(function () {
	        var teachCard = $("input[name='card']").val();
	        var idcardReg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	        if(!idcardReg.test(teachCard)){
	            layui.use('layer', function (){
	                var layer = layui.layer;
	                layer.msg('身份证输入不合法');
	            });
	            return false;
	        }
	    })
	};
   
		
    //保存新增教师
	$('#addBtn').click(function(){
        var tname = $('#personName').val(); //教师姓名
        var sex = $('#gender').val();
        var sfzh = $('#certNumber').val(); //教师身份证号
        var nation = $('#nation').val();
        var address = $('#address').val();
        var phone = $('#phone').val();     //手机号
        var workUnit = $('#workUnit').val();
        var birthdate = $('#birthday').val();
        var classCategoryId = $('#classCategory option:selected').val();
        var classCategory = $('#classCategory option:selected').text();
        var classMajorId = $('#classMajor option:selected').val();
        var classMajor = $('#classMajor option:selected').text();
        var classesId = $('#classes option:selected').val();
        var classes = $('#classes option:selected').text();
        if (classCategory == '请选择' || classMajor == '请选择') {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('请将内容填写完整!');
            });
        }else if(!myreg1.test(phone) || !regName1.test(tname) || !idcardReg1.test(sfzh)){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('请输入正确的姓名,手机号,身份证号!',{area:["270px","80px"]});
            });
        }else if (tname != '' && sex != '' && sfzh != '' && nation != '' && address != '' && phone != '' && workUnit != '' && birthdate != '') {
            $.ajax({
                type: "post",
                async: false,
                url: "api/teacher/enter",
                data: {
                    tname: tname,
                    sex: sex,
                    sfzh: sfzh,
                    nation: nation,
                    sfzaddress: address,
                    phone: phone,
                    workunit: workUnit,
                    birthdate: birthdate
                    //专业分类ID
                    , cateId: classCategoryId
//		        	//专业分类名称
                    , cateName: classCategory
//		        	//专业ID
                    , majorid: classMajorId
//		        	//专业名称
                    , majorname: classMajor
//		        	//班级ID
//		        	classid:classesId,
//		        	//班级名称
//		        	classname:classes,
                },
                success: function (data) {
                    if (data.code == 1) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg('新增成功');
                        });
                        setTimeout(function () {
                            window.location = "yhgl_lsgl.html";
                        }, 800)
                    } else {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg('该教师已存在！');
                        });
                    }
                },
                error: function () {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg("新增失败");
                    });
                }
            });
        }else {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('所有信息不能为空!');
            });
        }
	});
      