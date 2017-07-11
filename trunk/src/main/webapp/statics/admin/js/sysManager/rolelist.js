$(".add").on("click",function(){
	linkajax('role/mergepage','centercontent');
});

$(".btn_help").on("click",function(){
	var id = $(this).parent().find("input[name='id']").val();
	linkajax('sysmenu/editPage?id='+id,'centercontent');
});

$(".edit").on("click",function(){
	var id = $(this).parent().find("input[name='id']").val();
	linkajax('role/mergepage?id='+id,'centercontent');
});

$(".btn_trash").on("click",function(){
	if(confirm("确定删除此条记录么？删除后将不能恢复！")){
		var id = $(this).parent().find("input[name='id']").val();
		$.post("role/del",{id:id},function(data){
			if(data > 0){
				alert("删除成功！");
				linkajax('role/list','centercontent');
			}else{
				alert("操作失败！");
				return;
			}
		});
	}
});