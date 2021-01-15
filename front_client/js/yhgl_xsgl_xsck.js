
	//获取浏览地址id
	 $.getUrlParam = function (name){
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
	var id=$.getUrlParam('id');
	
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
				error: function(error) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg(error.responseText);
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
//				data:{
//		        	stuname:stuname, 
//		        	sex:sex,
//		        	sfzh:sfzh,
//		        	nation:nation,
//		        	address:address,
//	        		sfzaddress:sfzaddress,
//		        	phone:phone,
//		        	age:stuAge, 
//		        	carnumber:carnumber,
//		        	emergency:emergency,
//		        	iscard:studentCard,  //0是需要学生证，1是不需要学生证
//		        	fee:fee,
//		        	entertime:entertime,
//		        	graduatetime:graduatetime,
//					classid: classId.join(','),   
//					classname:className.join(',')
//		       },
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
				formSelects.disabled('select15');//禁用多选
				
				
								
			},
			error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
			}
		});
	});	
