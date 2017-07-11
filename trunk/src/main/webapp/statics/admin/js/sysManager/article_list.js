$(document).ready(function(){
	$(".add").on("click",function(){
		location.href = '../article/mergepage';
	});
	$(".edit").on("click",function(){
		var id = $(this).parent().find("input[name='id']").val();
		location.href = '../article/mergepage?id='+id;
	});
	
	$(".btn_search").on("click",function(){
		//alert($("#dyntable2_filter").serialize());
		linkajax('../article/list?'+$("#dyntable2_filter").serialize(),'dataTables_wrapper');
	});
	$(".btn_reset").on("click",function(){
		linkajax('../article/list','dataTables_wrapper');
	});
	
	
	$(".btn_trash").on("click",function(){
		if(confirm("确定删除此条记录么？删除后将不能恢复！")){
			var id = $(this).parent().find("input[name='id']").val();
			$.post("../article/del",{id:id},function(data){
				if(data > 0){
					alert("删除成功！");
					linkajax('../article/list','dataTables_wrapper');
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
					zTree.expandNode(treeNode);
					return false;
				} else {
					linkajax(url+"admin/article/listByTypeId?typeId="+treeNode.id,"dataTables_wrapper");
					return true;
				}
			}
		}
	};
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
})


