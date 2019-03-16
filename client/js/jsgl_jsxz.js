	//角色新增
	$('#addRoles').click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
				nodes = treeObj.getCheckedNodes(true),
				powerId = "",
				powerName = "";
			for(var i = 0; i < nodes.length; i++){
				powerId   += nodes[i].id+ ',';
				powerName += nodes[i].name+ ',';
				("powerId:" + powerId + "powerName:" + powerName); //获取选中节点的值
			}
		var roleName = $('#rolename').val();
		var decription = $('#decription').val();
		if(roleName!=''&&decription!=''&&powerId!=''&&powerName!='' ){
			//拿到所有数据后新增
			$.ajax({
				url: 'api/role/create',
				type: 'get',
				data: {
					rolename: roleName,
					description: decription,
					powerid: powerId,
					powername: powerName
				},
				dataType: 'json',
				async: false,
				success:function(){
					layui.use('layer', function(){
						var layer = layui.layer;
						layer.msg('添加成功！');
					});
					setTimeout(function(){
						window.location = "jsgl_jsbdqx.html";
					}, 800)
				},
                error: function(error) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg(error.responseText);
                    });
                }
			})	
		}else{
			layui.use('layer', function(){
				var layer = layui.layer;
				layer.msg('所有信息不能为空');
			});
		}
				
	});