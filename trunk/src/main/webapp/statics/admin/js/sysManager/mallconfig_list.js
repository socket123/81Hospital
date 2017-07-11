$(document).ready(function(){
/********************************list列表界面用************************************************/
	$(".add").on("click",function(){
		var typeName = $("#typeName").val();
		location.href = url+'admin/config/mergepage?typeName='+typeName;
	});
	
	$(".edit").on("click",function(){
		var id = $(this).parent().find("input[name='id']").val();
		var typeName = $("#typeName").val();
		location.href = url+'admin/config/mergepage?id='+id+"&typeName="+typeName;
	});
	
	$(".btn_trash").on("click",function(){
		if(confirm("确定删除此条记录么？删除后将不能恢复！")){
			var id = $(this).parent().find("input[name='id']").val();
			$.post(url+'admin/config/del',{id:id},function(data){
				if(data > 0){
					layer.alert("删除成功！");
					var typeName = $("#typeName").val();
					location.href = url+'admin/config/list?typeName='+typeName;
				}else if(data == -2){
					layer.alert("该菜单存在下级菜单，请勿删除！");
					return;
				}else{
					layer.alert("操作失败！");
					return;
				}
			});
		}
	});
	
	
	
	/********************************edit界面用************************************************/
	//validateForm();
	
	$(".back").on("click",function(){
		var typeName = $("#typeName").val();
		location.href = url+'admin/config/list?typeName='+typeName;
	});
	
	$(".submit").on("click",function(){
		if($("#form1").valid()){
			$.ajax({
				url: url+'admin/config/merge',
				data:$('#form1').serialize(),
				dataType: "json",
				type : "post",
				success: function(data) {
					if(data > 0){
						layer.alert("保存成功");
						var typeName = $("#typeName").val();
						location.href = url+'admin/config/list?typeName='+typeName;
					}else{
						layer.alert("保存失败");return;
					}
				}
			});
		}
		//layer.alert(123213)
	});
	///// FORM VALIDATION /////
});

/*验证*/
function validateForm() {
	$("#form1").validate({
		rules: {
			typeName: {
				required: true,
				maxlength:20
			},
			listOrder: "required",
		},
		messages: {
			menuName: {
				required: "配置项名称不能为空",
				maxlength:"请不要超过20个字符"
			},
			listOrder: "排序不能为空",
		}
	});	
}

function imgDel(){
	var img = $("#imgIdTxt").val();
	$.post(url+"file/del",{img:img},function(data){
		if(data == "success"){
			$("#imgId").attr("src",url+"statics/admin/images/default.png");
			$("#imgIdTxt").val("");
		}else{
			layer.layer.alert(data);
		}
	});
}
function imgClick(imgId){
	new AjaxUpload(imgId, {
        action: url+"file/upload",
		data : {
			'timestamp' : (new Date()).getTime(),
			'token'     : '',
			'key' : "文件将被覆盖"
		},
		onSubmit : function(file, ext){
			if (ext && /^(jpg|jpeg|gif|png|JPG|JPEG|GIF|PNG)$/.test(ext)){
				/* Setting data */
				var pic = $("#imgIdTxt").val();
				this.setData({
					'img': pic
				});					
				$('#example2 .text').text('正在上传...' + file);	
			} else {			
				layer.alert('错误: 只能上传允许的图片格式，如JPG|PNG|GIF');
				return false;				
			}		
		},
		onComplete : function(file,data){
			if (file == null) {
				layer.alert('你没有上传任何文件或者选择了取消！');
				return;
			}
			var reg = /<pre.+?>(.+)<\/pre>/g;  
			var result = data.match(reg);  
			data = RegExp.$1;
			var flag = eval('(' + data + ')');
			if(flag.code != "success"){
				layer.alert(flag.url);
			}else{
				$("#"+imgId).attr("src",url+"file/showpic?pic="+flag.url);
				$("#imgIdTxt").val(flag.url);
			}
			//$("#P_PIC"+ii).val(msg);
		}		
		});
}
