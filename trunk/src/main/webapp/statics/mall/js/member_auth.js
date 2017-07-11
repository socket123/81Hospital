$(document).ready(function(){
	$(".sex span").on("click",function(){
		$(".sex span").removeClass("on");
		$(this).addClass("on");
		var sex = $(this).attr("id");
		$("#sex").val(sex);
	});
	$("#memType").on("change",function(){
		var memType = $(this).val();
		if(memType == 1){
			$("#price").text("￥5.0元");
			$("#authPrice").val("5");
		}else if(memType == 2){
			$("#price").text("￥20.0元");
			$("#authPrice").val("20");
		}else{
			$("#price").text("￥150.0元");
			$("#authPrice").val("150");
		}
	});
	
	validateForm();
	
	$(".save").on("click",function(){
		if($("#addForm").valid()){
			$.ajax({
				url: url+"profile/auth",
				data:$('#addForm').serialize(),
				dataType: "json",
				type : "post",
				success: function(data) {
					alert("保存成功");
					location.href = url+'profile/index';
				}
			});
		}
	});
});


/*验证*/
///// FORM VALIDATION /////
function validateForm() {
	$("#addForm").validate({
        debug: true, //调试模式取消submit的默认提交功能   
        //errorClass: "label.error", //默认为错误的样式类为：error   
        focusInvalid: true, //当为false时，验证无效时，没有焦点响应  
        onkeyup: false,
        rules:{
        	userName:{
        		required:true,
        		maxlength:20
            },
            telephone:{
            	required:true,
            	maxlength:11
            },
            address:{
        		required:true,
        		maxlength:50
            },
            sex:{
            	required:true
            }
        },
        messages:{
        	userName:{
        		required:"请填写您的姓名",
        		maxlength:"请输入长度不超过20的字符串",
            },
            telephone:{
            	required:"请填写能够联系到您的手机号",
            	maxlength:"请输入正确的手机号"
            },
            address:{
        		required:"请填写详细地址",
        		maxlength:"请输入长度不超过20的字符串",
            },
            sex:{
            	required:"请填写详细地址",
            	maxlength:"请输入长度不超过20的字符串",
            },
        }
    });
}