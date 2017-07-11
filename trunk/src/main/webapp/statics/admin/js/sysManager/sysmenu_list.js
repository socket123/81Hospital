$(document).ready(function(){
	$(".add").on("click",function(){
		linkajax(url+'admin/sysmenu/mergepage','dataTables_wrapper');
	});
	
	$(".edit").on("click",function(){
		var id = $(this).parent().find("input[name='id']").val();
		linkajax(url+'admin/sysmenu/mergepage?id='+id,'dataTables_wrapper');
	});
	
	$(".sonmenu").on("click",function(){
		var id = $(this).parent().find("input[name='id']").val();
		linkajax(url+'admin/sysmenu/list?pid='+id,'dataTables_wrapper');
	});
	
	$(".backmenu").on("click",function(){
		var id = $("#pid").val();
		linkajax(url+'admin/sysmenu/pidlist?pid='+id,'dataTables_wrapper');
	});
	
	$(".btn_trash").on("click",function(){
		if(confirm("确定删除此条记录么？删除后将不能恢复！")){
			var id = $(this).parent().find("input[name='id']").val();
			$.post("sysmenu/del",{id:id},function(data){
				if(data > 0){
					alert("删除成功！");
					linkajax(url+'admin/sysmenu/list','dataTables_wrapper');
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
	//初始化
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
				linkajax(url+"admin/sysmenu/mergepage?id="+treeNode.id,"dataTables_wrapper");
				//$(".dataTables_wrapper").html("<iframe src='"+url+"admin/sysmenu/mergepage?id="+treeNode.id+"' width='100%'></iframe>");
				if (treeNode.isParent) {
					//zTree.expandNode(treeNode);
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



