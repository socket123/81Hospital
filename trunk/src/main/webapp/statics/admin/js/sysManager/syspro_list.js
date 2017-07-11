$(document).ready(function(){
	$(".add").on("click",function(){
		location.href = url+'admin/sysproduct/mergepage?state='+state;
	});
	$(".edit").on("click",function(){
		var id = $(this).parent().find("input[name='id']").val();
		location.href = url+'admin/sysproduct/mergepage?id='+id;
	});
	
	$(".btn_search").on("click",function(){
		//alert($("#dyntable2_filter").serialize());
		var typeId = $("#typeId").val();
		linkajax(url+'admin/sysproduct/listByTypeId?typeId='+typeId+"&"+$("#dyntable2_filter").serialize(),'dataTables_wrapper');
	});
	$(".btn_reset").on("click",function(){
		var typeId = $("#typeId").val();
		linkajax(url+'admin/sysproduct/listByTypeId?typeId='+typeId,'dataTables_wrapper');
	});
	
	
	$(".btn_trash").on("click",function(){
		if(confirm("确定删除此条记录么？删除后将不能恢复！")){
			var id = $(this).parent().find("input[name='id']").val();
			$.post(url+'admin/sysproduct/del',{id:id},function(data){
				if(data > 0){
					alert("删除成功！");
					var typeId = $("#typeId").val();
					linkajax(url+'admin/sysproduct/listByTypeId?typeId='+typeId,'dataTables_wrapper');
				}else if(data == -2){
					alert("该菜单存在下级菜单，请勿删除！");
					return;
				}else{ 
					alert("操作失败！");
					return;
				}
			});
		}
	});
	
	/******************ztree*******************/
	var zTreeObj;
	// zTree 的参数配置
	var setting = {
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//				alert(state);
				if (treeNode.isParent) {
					//zTree.expandNode(treeNode);
					return false;
				} else {
					linkajax(url+"admin/sysproduct/listByTypeId?typeId="+treeNode.id+"&state="+state,"dataTables_wrapper");
					return true;
				}
			}
		}
	};
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
})


