$(document).ready(function(){
	$(".restaurant-tab").on("click",function(){
		var flag = $(this).attr('id');
		location.href = url + "profile/myorder?flag="+flag;
	});
	
	//分类ajax点击事件
	$(".restaurant-menu-item").on("click",function() {
		$(this).parent().find(".restaurant-menu-item").removeClass("active");
		$(this).addClass("active");
		var status = $(this).attr('id');
		var flag = $("#flag").val();
		$("#current_status").val(status);
		var url1 = url+"profile/myorderBystatus?status="+status+"&flag="+flag;
		linkajax(url1,"restaurant-food");
	});
	
	//详细信息点击事件
	$(".restaurant-food-name,.restaurant-food-description").on("click",function() {
		var orderId = $(this).parent().find("input[name=orderId]").val();
		location.href = url+"profile/orderinfo?orderId="+orderId;
	});
	
});

function updateStatus(orderId,status){
	var msg = "";
	if(status == 0){
		msg = "是否删除此订单？";
	}else if(status == 4){
		msg = "我已收到货";
	}else if(status == 5){
		msg = "你确认取消此订单？";
	}
	var current_status = $("#current_status").val();
	if(confirm(msg)){
		$.post(url+"order/updatestauts",{orderId:orderId,status:status},function(data){
			if(data > 0){
				var flag = $("#flag").val();
				var tourl = url+"profile/myorderBystatus?flag="+flag+"&status="+current_status;
				//alert(tourl);return;
				linkajax(tourl,"restaurant-food");
			}
		});
	}
}

function toevaluate(orderId){
	location.href = url+"profile/evalpage?orderId="+orderId;
}
