	var setting = {
				view: {
					addHoverDom: null, //设置鼠标移到节点上，在后面显示一个按钮，默认值为null,使用时需配合removeHoverDom一起使用
					removeHoverDom: null,
					selectedMulti: false, //设置是否允许同时显示多个节点
					showIcon: true, //设置是否显示节点图标
					showLine: true, //设置是否显示节点与节点之间的连线
					showTitle: true //设置是否显示节点的title提示信息
				},
				check: {
					enable: true //设置zTree节点上是否显示单选框或复选框
						,
					chkStyle: 'radio' //勾选框类型为radio 默认为checkbox
						,
					radioType: "level" //设置radio的分组范围，level在每一级节点范围内当做一个分组.all表示在整棵树范围内当做一个分组。
				},
				data: {
					simpleData: {
						enable: true //使用简单数据模式
					}
				},
				edit: {
					enable: true // 设置zTree进入编辑状态
				},
		};
		var zNodes = []; //定义一个全局空数组
		
		//获取权限管理下拉列表
		$.ajax({
			url: "api/power/powers",
			type: 'get',
			dataType: 'json',
			async: false,
			success: function(data){				
				for(var i = 0; i < data.data.length; i++){
					var obj = {
						id: data.data[i].id,
						pId: data.data[i].pId,
						name: data.data[i].name,
						open: data.data[i].open,
					};
					zNodes.push(obj);
				};								
				function setCheck(){
					setting.check.chkStyle = $("#r1").attr("checked") ? "checkbox" : "radio";
					setting.check.enable = (!$("#disablechk").attr("checked"));
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				};
				$(document).ready(function(){
					$.fn.zTree.init($("#treeDemo"), setting, zNodes); //初始化  $("#treeDemo")为zTree的DOM容器 ；setting为zTree 的配置数据；zNodes为zTree的节点数据
					setCheck();
					$("#r1").bind("change", setCheck);
					$("#r2").bind("change", setCheck);
					$("#disablechk").bind("change", setCheck);
				});
	
				var newCount = 1;
				function addHoverDom(treeId, treeNode){
					var sObj = $("#" + treeNode.tId + "_span");
					if(treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
					var addStr = "<span class='button add' id='addBtn_" + treeNode.tId +
						"' title='add node' onfocus='this.blur();'></span>";
					sObj.after(addStr);
					var btn = $("#addBtn_" + treeNode.tId);
					if(btn) btn.bind("click", function() {
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						zTree.addNodes(treeNode, {
							id: (100 + newCount),
							pId: treeNode.id,
							name: "new node" + (newCount++)
						});
						return false;
					});
				};
	
				function removeHoverDom(treeId, treeNode){
					$("#addBtn_" + treeNode.tId).unbind().remove();
				};
			},
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
            }
		});