$(".add").on("click",function(){
	linkajax('storeProType/mergepage','centercontent');
});

$(".edit").on("click",function(){
	var id = $(this).parent().find("input[name='id']").val();
	linkajax('storeProType/mergepage?id='+id,'centercontent');
});

$(".btn_trash").on("click",function(){
	if(confirm("确定删除此条记录么？删除后将不能恢复！")){
		var id = $(this).parent().find("input[name='id']").val();
		$.post("storeProType/del",{id:id},function(data){
			if(data > 0){
				alert("删除成功！");
				linkajax('storeProType/list','centercontent');
			}else{
				alert("操作失败！");
				return;
			}
		});
	}
});

$(".btn_search").on("click",function(){
	alert($("#dyntable2_filter").serialize());return;
	linkajax('storeProType/list?'+$("#dyntable2_filter").serialize(),'centercontent');
});