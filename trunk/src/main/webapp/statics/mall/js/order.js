$(document).ready(function(){
	var id = $("#addressId").val();
	if(id){
		$(".choice-null").addClass("ng-hide");
		$(".choice-address").removeClass("ng-hide");
	}
	
	var cart = "${cart}";
	if(null == cart || cart == '[]'){
		alert("购物车中没有数据，请继续逛逛！");
		location.href = url+"mall/home"; 
	}
	
	$(".ui-padding-block").on("click",function(){
		location.href = url + "mall/order/address";
	});
	
	$(".odr").on("click",function(){
		$(".remark").toggle();
	});
	
	$(".btn-confirm").on("click",function(){
		var payType = $("#payType").val();
		var remark = $("#remark").val();
		var addressId = $("#addressId").val();
		if(!addressId){
			alert("请选择送货地址！");return;
		}
		if(!payType){
			alert("请选择支付方式！");return;
		}
		$.post(url+"mall/order/saveorder",{addressId:addressId,payType:payType,remark:remark},function(data){
			if(data > 0){
				if(payType == 1){
					if(confirm("下单成功！是否继续逛逛？")){
						location.href = url + "mall/index";
					}else{
						location.href = url + "profile/index";
					}
				}else{
					alert("微信支付！");
				}
			}else{
				alert("购物车是空的，请继续逛逛");
				location.href = url + "mall/index";
			}
		});
	});
});