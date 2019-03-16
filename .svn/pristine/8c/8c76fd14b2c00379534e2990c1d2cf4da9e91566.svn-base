//修改方法
	//获取浏览地址id
	 $.getUrlParam = function (name){
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
	var id=$.getUrlParam('id');
	
	ztree = $.fn.zTree.getZTreeObj("treeDemo");  
	$('#editRoles').click(function(){
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

		//拿到所有数据后新增
		$.ajax({
			 url: "api/role/update?id="+id,
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
					layer.msg('修改成功！');
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
	});