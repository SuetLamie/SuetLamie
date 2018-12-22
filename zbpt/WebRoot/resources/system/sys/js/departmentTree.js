function SysDepartmentTree(role){
	var t=this;
	this.loadTreeData= function(){
		$("#tree").treeview({
			data: [{
				text: CommUtil.sysInfo.loginUser.org_name,
				id: CommUtil.sysInfo.loginUser.org_id,
				state: {
					checked: false,
					expanded: false
					},
				nodes: []		
			}],
			//showCheckbox: true,
			onNodeSelected:function(event, node)
			{
				var options = $("#tb_dept").bootstrapTable('getOptions');
				role.second = node.id;
				$("#tb_dept").bootstrapTable('refresh',{aa:node.id,pageNumber : 1});
			},
			onNodeExpanded: function(event, node) {
				if(node.nodes.length == 0) {
					$.ajax({
						type: "Post",
						url: "system/sys/departmentController/getDepartmentTreeNode.action",
						data: {
							node: node.id
						},
						dataType: "json",
						success: function(result) {
							$('#tree').treeview(
								'addChildNode', [node.id, result]);
						}			
					});
				}
			}
		});
	}

	
	
	
	this.init = function() {
		$('#tree').hide();
	}
	
}
















