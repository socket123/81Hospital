$(document).ready(function(){
	$("#addForm").validate({
        debug: true, //调试模式取消submit的默认提交功能   
        //errorClass: "label.error", //默认为错误的样式类为：error   
        focusInvalid: true, //当为false时，验证无效时，没有焦点响应  
        onkeyup: false,   
        submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
            form.submit();   //提交表单   
        },   
        rules:{
        	userName:{
        		required:true,
        		maxlength:20
            },
            provice:{
            	required:true
            },
            city:{
            	required:true
            },
            address:{
        		required:true,
        		maxlength:50
            },
            phone:{
            	required:true,
            	maxlength:11
            }
        },
        messages:{
        	userName:{
        		required:"请填写您的姓名",
        		maxlength:"请输入长度不超过20的字符串",
            },
            provice:{
            	required:"请选择省",
            },
            city:{
            	required:"请选择市",
            },
            region:{
            	required:"请选择区",
            },
            address:{
        		required:"请填写详细地址",
        		maxlength:"请输入长度不超过20的字符串",
            },
            phone:{
            	required:"请填写能够联系到您的手机号",
            	maxlength:"请输入正确的手机号"
            }                                   
        }
    });
	
	
	$("#provice").on("change",function(){
		var cid = $(this).val();
		$.post(url+"area/list",{pid:cid},
			function(data){
				$("#city").html("<option value=''>==市==</option>");
				for (var i = 0; i < data.length; i++) {
					$("#city").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
				}
				$("#region").html("<option value=''>==区==</option>");
			}
		);
	});
	$("#city").on("change",function(){
		var cid = $(this).val();
		$.post(url+"area/list",{pid:cid},
			function(data){
				$("#region").html("<option value=''>==区==</option>");
				for (var i = 0; i < data.length; i++) {
					$("#region").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
				}
			}
		);
	});
});
