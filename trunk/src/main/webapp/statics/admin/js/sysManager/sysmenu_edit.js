validateForm();

$(".back").on("click",function(){
	location.href = url+'admin/sysmenu/list';
});

$("#ppid").on("change",function(){
	var pid = $(this).val();
	var thisid = $("#id").val();
	if(pid == thisid){
		alert("请勿选择自己为上级菜单");
		$(this).val("");
	}
});

$("#level").on("change",function(){
	var level = $(this).val();
	var content = "<option value=''>==请选择上级菜单==</option>";
	if(level == 1){
		content = "<option value='0'>顶级菜单</option>";
	}
	level = level-1;
	$.post(url+"admin/sysmenu/levellist",{level:level},function(data){
		for(var i = 0; i < data.length;i++){
			content += "<option value='"+data[i].id+"'>"+data[i].menuName+"</option>";
		}
		$("#ppid").html(content);
	});
});
$(".submit").on("click",function(){
	if($("#form1").valid()){
		$.ajax({
			url: url+'admin/sysmenu/merge',
			data:$('#form1').serialize(),
			dataType: "json",
			type : "post",
			success: function(data) {
				if(data > 0){
					alert("保存成功");
					location.href = url+'admin/sysmenu/list';
				}else{
					alert("保存失败");return;
				}
			}
		});
	}
	//alert(123213)
});
///// FORM VALIDATION /////


/*验证*/
function validateForm() {
	$("#form1").validate({
		rules: {
			menuName: {
				required: true,
				maxlength:10
			},
			pid: "required",
			level: "required"
		},
		messages: {
			menuName: {
				required: "菜单名称不能为空",
				maxlength:"请不要超过10个字符"
			},
			pid: "上级菜单不能为空",
			level:"级别不能为空"
		}
	});
}