	//获取浏览地址id
	 $.getUrlParam = function (name){
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
	var id=$.getUrlParam('id');


    //手机号输入框失去焦点时判断手机号是否正确
    var myregPhone=/^[1][3,4,5,7,8,9][0-9]{9}$/;
    $("input[name='phone']").blur(function(){
        var phoneNew =$("input[name='phone']").val();
        var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
        if (!myreg.test(phoneNew)){
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('手机号输入错误');
            });
        }
    });
    //手机号输入框失去焦点时判断手机号是否正确
    var myregPhone1=/^[1][3,4,5,7,8,9][0-9]{9}$/;
    $("input[name='emergency']").blur(function(){
        var emergencyNew =$("input[name='emergency']").val();
        var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
        if (myreg.test(emergencyNew)){
            console.log("手机号码填写正确")
        } else {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('家庭联系号码输入错误');
            });
        }
    });

    //验证姓名
    var regName1 =/^[\u4e00-\u9fa5]{2,4}$/;
    $("input[name='stuname']").blur(function () {
        var teachName = $("input[name='stuname']").val();
        var regName = /^[\u4e00-\u9fa5]{2,4}$/;
        if (!regName.test(teachName)) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('真实姓名填写有误');
            });
            return false;
        }
    })

    //验证身份证号
    var idcardReg1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    $("input[name='sfzh']").blur(function () {
        var teachCard = $("input[name='sfzh']").val();
        var idcardReg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
        if (!idcardReg.test(teachCard)) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('身份证输入不合法');
            });
            return false;
        }
    })

    //验证车牌号
    $("input[name='carnumber']").blur(function(){
        var carNumberNew =$("input[name='carnumber']").val();
        var carnumReg =/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
        if (carnumReg.test(carNumberNew)){
            console.log('车牌号填写正确；')
        } else {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('车牌号输入错误');
            });
        }
    });

	
    //时间控件
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        lay('#entertime').each(function(){
            laydate.render({
                elem:this
                ,type: 'datetime'
            });
        });
        lay('#graduatetime').each(function(){
            laydate.render({
                elem:this
                ,type: 'datetime'
            });
        });

    });
	
		/*多选查询 end*/
	var  listData ,childData,lastChildData;  //定义全局变量接收一级数据和二级数据
	 
	 	$.ajax({
				async: false, //同步
				url: "api/classes/queryclscatetoacquirecls",
				type: "get",
				dataType: "json", 
				success: function(data){
					var  arr =[] ; //定义一个空数组来接收返回的数据					
					listData = data.data;            //拿到一级数据
					for(var i = 0;i<listData.length;i++){
						var obj = {};  //定义一个空对象来接收返回的数据
						//拿到一级的name和value
						obj.name  = listData[i].name;
						obj.value = listData[i].value;	
						obj.children = [];   //定义一级数据的children
						arr.push(obj);       //将一级数据回填到数组里
						
						childData = listData[i].children;  //遍历二级数据						
						//遍历拿到二级数据
						for(var j=0;j<childData.length;j++){
							//拿到二级的name和value
							var objChild = {};
							objChild.name = childData[j].name;
							objChild.value = childData[j].value;
							objChild.children = [];       //定义二级数据的children
							obj.children.push(objChild);  //将二级数据回填到一级数组的children里
							
							lastChildData = childData[j].children; //遍历三级数据
							//遍历拿到三级数据
							for(var num=0;num<lastChildData.length;num++){
								//拿到三级的name和value
								var objLastChild = {};
								objLastChild.name = lastChildData[num].name;
								objLastChild.value = lastChildData[num].value;
								objLastChild.children = [];       //定义三级数据的children
								objChild.children.push(objLastChild);  //将二级数据回填到一级数组的children里
							}							
						}						
					}
					layui.formSelects.data('select15', 'local', {
						arr,
						linkage: true   //可多选
					})
				},
				error: function() {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg("请求失败");
                    });
				}				
			});
	/*多选查询 end*/
	layui.use(['layer', 'form'], function(){
		var layer = layui.layer; 
        var form  = layui.form;  
		//如果已经绑定班级了，先查询
		var formSelects = layui.formSelects;  //加载模块
		var returnId;//定义一全局变了接收返回的班级id

		$.ajax({
			type: "get",
			async: false,
			url: "api/student/all-detail?id="+id,
			dataType:'json',
			success: function(data){
				//后台返回的基本信息
				var returnBaseInfo =  data.data.baseInfo;
				//后台返回的报名信息
				var returnEnterInfo =  data.data.enterInfo;
				//后台返回的班级id
				returnId  =  data.data.value;			
				//将后台返回的值回显到页面
				$("input[name='stuname']").val(returnBaseInfo.stuname);
				$("input[name='sex']").val(returnBaseInfo.sex);
				$("input[name='sfzh']").val(returnBaseInfo.sfzh);
				$("input[name='nation']").val(returnBaseInfo.nation);
				$("input[name='address']").val(returnBaseInfo.address);
				$("input[name='sfzaddress']").val(returnBaseInfo.sfzaddress);
				$("input[name='phone']").val(returnBaseInfo.phone);
				$("input[name='age']").val(returnBaseInfo.age);
				$("input[name='carnumber']").val(returnBaseInfo.carnumber);
				$("input[name='fee']").val(returnEnterInfo.fee);
				$("input[name='emergency']").val(returnBaseInfo.emergency);
				$("input[name='entertime']").val(returnBaseInfo.entertime);
				$("input[name='graduatetime']").val(returnBaseInfo.graduatetime);			
				//学生证
				 var stuOption=document.getElementById("studentCard").options;
		        for(var i=0;i<stuOption.length;i++){
		        	var stuId = stuOption[i].value;
		        	if(stuId == returnBaseInfo.status){
		        		$('#week').find("option[value = '"+stuId+"']").attr('selected','selected');
		        	}
		        }
		        form.render('select','studentCard');
				//将返回的班级名称和value值回显到下拉框
				formSelects.value('select15',returnId); 
//				formSelects.disabled('select15');//禁用多选	
			},
			error: function() {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg("请求失败");
                });
			}
		});
	});	
	
	/*修改*/
	layui.use(['layer', 'form'], function(){
            var form = layui.form;

        $('#changeStu').click(function () {
            var classId, className;
            var classids = [], classnames = [];  //定义全局变量接收选中班级的id和name
            var stuname = $('#personName').val();         //学生姓名
            var sex = $('#gender').val();             //性别
            var sfzh = $('#certNumber').val();         //身份证号
            var nation = $('#nation').val();             //民族
            var address = $('#nowAddress').val();         //身份证地址
            var sfzaddress = $('#address').val();         //身份证地址
            var phone = $('#phone').val();              //联系方式
            var carnumber = $('#carNumber').val();          //汽车车牌号
            var emergency = $('#emergency').val();	        //家庭联系方式
            var fee = $('#fee').val();                //学费
            var entertime = $('#entertime').val();          //入学时间
            var graduatetime = $('#graduatetime').val();       //毕业时间
            var studentCard = $('#studentCard option:selected').val();           //学生证val
            var stuAge = $('#age').val();
            //获取.xm-select-title的第一个孩子，再找第一个孩子的第一个孩子.xm-select-label，再找那个孩子下的所有span元素，获取span元素的value;
            //遍历拿到各自的id
            $('.xm-select-title').children(":first").children(":first").find("span").each(function (i) {
                classids[i] = $(this).attr('value');
            });
            //遍历拿到各自的名称
            $('.xm-select-title').children(":first").children(":first").find('font').each(function (i) {
                classnames[i] = $(this).text();
            });
            //将遍历得到的value值赋给classIds
            classId = classids;
            className = classnames;

            if (stuname == '' || sex == '' || sfzh == '' || nation == '' || address == '' || sfzaddress == '' || phone == '' || emergency == '' || studentCard == '' || entertime == '' || graduatetime == '' || classId == '' || className == '') {
                layer.msg('除车牌号和学费，其他所有都是必填项');
            } else if (!myregPhone.test(phone) || !myregPhone1.test(emergency) || !regName1.test(stuname) || !idcardReg1.test(sfzh)) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请输入正确的姓名,手机号,身份证号!',{area:["270px","80px"]});
                });
            } else if (entertime < graduatetime) {
                $.ajax({
                    type: "post",
                    async: false,
                    url: "api/student/update?id=" + id,
                    dataType: 'json',
                    data: {
                        stuname: stuname,
                        sex: sex,
                        sfzh: sfzh,
                        nation: nation,
                        address: address,
                        sfzaddress: sfzaddress,
                        phone: phone,
                        age: stuAge,
                        carnumber: carnumber,
                        emergency: emergency,
                        iscard: studentCard,  //0是需要学生证，1是不需要学生证
                        fee: fee,
                        entertime: entertime,
                        graduatetime: graduatetime,
                        classid: classId.join(','),
                        classname: className.join(',')
                    },
                    success: function (data) {
                        if (data.code == 1) {
                            layer.msg('修改成功');
                            setTimeout(function () {
                                window.location = "yhgl_xsgl.html";
                            }, 800)
                        } else {
                            layer.msg('修改失败：' + data.msg);
                        }
                    },
                    error: function () {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg("请求失败");
                        });
                    }
                });
            } else {
                layer.msg('入学时间应小于毕业时间!');
            }
        });
    });
	/*修改 end*/