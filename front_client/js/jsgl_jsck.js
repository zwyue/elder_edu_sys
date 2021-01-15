$(function(){
    	//获取浏览地址id
    	 $.getUrlParam = function (name){
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
    	var id=$.getUrlParam('id');
    	
    	ztree = $.fn.zTree.getZTreeObj("treeDemo");  
    	//查询到id，并把表格页面的值带到修改页面
        $.ajax({
            type: 'GET',
            url: "api/role/detail?roleId="+id,
            async: false,
            data:{
                roleId:id
            },
            dataType:'json',
//              xhrFields: {withCredentials: true},
            success: function (data){
            	//将后台返回的数据回填到表单里
                $("#rolename").val(data.data.role.rolename);
                $("#decription").val(data.data.role.description);
				for(var i= 0;i<data.data.powers.length;i++){
					var powerId   = data.data.powers[i].id;
                	var powerName = data.data.powers[i].name;
            		var nodeId = ztree.getNodeByParam("id", powerId, null); //获取带来的powerid
            			nodeId.checked = true;
            			ztree.selectNode(nodeId,true,true);   
				}
				ztree.refresh();
			},
            error: function (error) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg(error.responseText);
                    });
                }
        	});
       	})