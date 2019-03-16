
//layui form 表单
layui.use('form', function(){
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});

// 异动档案弹窗
   function ydbutton() {
   	
   		//查询异动档案
		var sfzh = $('#certNumber').val();         //身份证号
		('sfzh:'+sfzh);
		$.ajax({
			type: "get",
			async: false,
			url: "api/specialmanager/querylist",
			data:{
	        	idcard:sfzh
	       	},
			success: function(data){			
				var ydHtml ="";             
	            $.each(data.data, function (i, item){
	                ydHtml += '<tr>';
	                ydHtml += '<td>' + (i+1) + '</td><td>' + item.content + '</td><td>' + item.filtime + '</td>';
	                ydHtml += '</tr>';
	            })
	            $('#ydContent').html(ydHtml);							
			},
			error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
			}
		});
     layui.use('layer', function(){
        var layer = layui.layer;
        layer.open({
            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type: 1,
            skin: '',
            title: "异动档案",
            area: ['50%', '50%'],
            content: $("#popSearchRoleTest").html()
        })
     });
     
   };

// 弹窗选择老师
   function xzls() {
     layui.use('layer', function(){
        var layer = layui.layer;
        layer.open({
            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type: 1,
            skin: '',
            title: "授课老师选择",
            area: ['50%', '50%'],
            content: $("#popSearchRoleTest").html()
        })
     });
   };

//下拉框选中解禁
     // var option=$("#test option:selected");
     // alert(option.val());
     //  $("#test2").attr("disabled","disabled");
     //  $("#test3").attr("disabled","disabled");
     //  $("#test").click(function () {
     //      var objS = document.getElementById("test");
     //      var grade = objS.options[objS.selectedIndex].value;
     //      $("#test2").removeAttr("disabled");
     //      $("#test3").removeAttr("disabled");
     //  })
