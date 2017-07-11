$(document).ready(function(){
$(".add").on("click",function(){
	location.href = url+'admin/manager/mergepage';
});

$(".edit").on("click",function(){
	var id = $(this).parent().find("input[name='id']").val();
	location.href = url+'admin/manager/mergepage?id='+id;
});

$(".btn_trash").on("click",function(){
	if(confirm("确定删除此条记录么？删除后将不能恢复！")){
		var id = $(this).parent().find("input[name='id']").val();
		$.post(url+"admin/manager/del",{id:id},function(data){
			if(data > 0){
				alert("删除成功！");
				location.href = url+'admin/manager/list';
			}else{
				alert("操作失败！");
				return;
			}
		});
	}
});
});