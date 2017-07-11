validateForm();

$(".back").on("click",function(){
	location.href = url+'admin/store/list';
});

$(".submit").on("click",function(){
	if($("#form1").valid()){
		$.ajax({
			url: url+"admin/store/merge",
			data:$('#form1').serialize(),
			dataType: "json",
			type : "post",
			success: function(data) {
				if(data > 0){
					alert("保存成功");
					location.href = url+'admin/store/list';
				}else{
					alert("保存失败");return;
				}
			}
		});
	}
	//alert(123213)
});

/*验证*/
///// FORM VALIDATION /////
function validateForm() {
	$("#form1").validate({
		rules: {
			storeName: {
				required: true,
				maxlength:20
			},
			storePhone: {
				required: true,
				maxlength:11,
				minlength:11
			},
			qsPrice:"required",
			province: "required",
			city: "required",
			storeAddress: {
				required: true,
				maxlength:100
			}
		},
		messages: {
			storeName: {
				required: "请输入店铺名称",
				maxlength:"请不要超过20个字符"
			},
			storePhone: {
				required: "请输入店铺联系电话",
				maxlength:"请输入合法的联系电话",
				minlength:"请输入合法的联系电话"
			},
			qsPrice:"请输入起送价格",
			province: "请选择省",
			city: "请选择市",
			storeAddress: {
				required: "请输入店铺详细地址",
				maxlength:"请不要超过100个字符"
			}
		}
	});
}
	
$("#province").on("change",function(){
	var cid = $(this).val();
	var name = $(this).find("option:selected").text();
	$(".province").html(name);
	$.post(url+"area/list",{pid:cid},
		function(data){
			$("#city").html("<option value=''>==市==</option>");
			for (var i = 0; i < data.length; i++) {
				$("#city").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
			}
			$("#region").html("<option value=''>==区县==</option>");
		}
	);
});
$("#city").on("change",function(){
	var cid = $(this).val();
	var name = $(this).find("option:selected").text();
	$(".city").html(name);
	$.post(url+"area/list",{pid:cid},
		function(data){
			$("#region").html("<option value=''>==区县==</option>");
			for (var i = 0; i < data.length; i++) {
				$("#region").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
			}
		}
	);
});


function imgDel(){
	var img = $("#imgIdTxt").val();
	$.post(url+"file/del",{img:img},function(data){
		if(data == "success"){
			$("#imgId").attr("src",url+"statics/admin/images/default.png");
			$("#imgIdTxt").val("");
		}else{
			layer.alert(data);
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
				alert('错误: 只能上传允许的图片格式，如JPG|PNG|GIF');
				return false;				
			}		
		},
		onComplete : function(file,data){
			if (file == null) {
				alert('你没有上传任何文件或者选择了取消！');
				return;
			}
			var reg = /<pre.+?>(.+)<\/pre>/g;  
			var result = data.match(reg);  
			data = RegExp.$1;
			var flag = eval('(' + data + ')');
			if(flag.code != "success"){
				alert(flag.url);
			}else{
				$("#"+imgId).attr("src",url+"file/showpic?pic="+flag.url);
				$("#imgIdTxt").val(flag.url);
			}
			//$("#P_PIC"+ii).val(msg);
		}		
		});
}