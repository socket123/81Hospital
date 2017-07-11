$(".add").on("click",function(){
	linkajax('sysorder/mergepage','centercontent');
});

$(".edit").on("click",function(){
	var id = $(this).parent().find("input[name='id']").val();
	linkajax('sysorder/mergepage?id='+id,'centercontent');
});

$(".btn_search").on("click",function(){
	//alert($("#dyntable2_filter").serialize());
	linkajax('sysorder/list?'+$("#dyntable2_filter").serialize(),'centercontent');
});
$(".btn_reset").on("click",function(){
	linkajax('sysorder/list','centercontent');
});


$(".btn_trash").on("click",function(){
	if(confirm("确定删除此条记录么？删除后将不能恢复！")){
		var id = $(this).parent().find("input[name='id']").val();
		$.post("sysorder/del",{id:id},function(data){
			if(data > 0){
				alert("删除成功！");
				linkajax('sysorder/list','centercontent');
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