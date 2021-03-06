//初始化
$(document).ready(function(){
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
				linkajax(adminUrl+"articleType/mergepage?id="+treeNode.id,"dataTables_wrapper");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					return true;
				}
			}
		}
	};
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	zTreeObj.expandAll(true);
});

$(".add").on("click",function(){
	linkajax(adminUrl+'articleType/mergepage','dataTables_length');
});

$(".edit").on("click",function(){
	var id = $(this).parent().find("input[name='id']").val();
	linkajax(adminUrl+'articleType/mergepage?id='+id,'dataTables_length');
});

$(".btn_search").on("click",function(){
	alert($("#dyntable2_filter").serialize());return;
	linkajax(adminUrl+'articleType/list?'+$("#dyntable2_filter").serialize(),'dataTables_length');
});