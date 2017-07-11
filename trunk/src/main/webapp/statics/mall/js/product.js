$(document).ready(function(){
	//设置初始值
	var qsPrice = $("#qsPrice").val();
	var st_qsPrice = $("#st_qsPrice").val();
	var storeId = $("#storeId").val();
	
	//计算剩余起送价格
	if(st_qsPrice <= 0){
		$(".mcart-note").addClass("ng-hide");
		$(".mcart-checkout").removeClass("ng-hide");
	}else{
		$(".mcart-note").removeClass("ng-hide");
		$(".mcart-checkout").addClass("ng-hide");
	}
	
	//分类ajax点击事件
	$(".restaurant-menu-item ").on("click",function() {
		$(this).parent().find(".restaurant-menu-item").removeClass("active");
		$(this).addClass("active");
		var typeId = $(this).find("input[name='typeId']").val();
		var url1 = url+"mall/product/listajax?storeId="+storeId+"&typeId="+typeId;
		linkajax(url1,"restaurant-food");
	});
	
	//详细信息点击事件
	$(".restaurant-food-name,.restaurant-food-description").on("click",function() {
		var proId = $(this).parent().find("input[name=proId]").val();
		location.href = url+"mall/product/info?proId="+proId+"&qsPrice="+qsPrice;
	});
	
	/**
	 * 加入购物车点击事件
	 */
	$(".mdish-cartcontrol-add").on("click",function() {
		var _t = $(this);
		var proState = $("#proState").val();
		$.post(url+"mall/order/bystate",{state:proState},function(data){
			if(data > 0){
				if(confirm("您的购物车中存在其他商品，是否清空购物车？")){
					$.post(url+"mall/order/clearCart",{proId:null},function(data){});
					addCart(_t);
				}
			}else{
				addCart(_t);
			}
		});
	});
	
	function addCart(_t){
		//开始修改购物车
		
		var stateNum = $("#stateNum").val();
		var proId = $("#proId").val();
		console.log(proId);
		$.post(url+"mall/order/updateCart",{proId:proId,flag:1,stateNum:stateNum},function(data){
			if(data.thisNum > 0){
				_t.parent().find(".mdish-cartcontrol-minus").removeClass("ng-hide");
				_t.parent().find(".restaurant-food-originprice").removeClass("ng-hide");
				_t.parent().find(".restaurant-food-originprice").html(data.thisNum);
				
				$(".ui-quantity").html(data.totalNum);//设置总数
				$(".mcart-price").html("¥"+data.totalPrice);//设置总价
				//设置起送价格
				var tempt = qsPrice-data.totalPrice;
				if(tempt <= 0){
					$(".mcart-note").addClass("ng-hide");
					$(".mcart-checkout").removeClass("ng-hide");
				}
				$(".haichaPrice").html("¥"+parseFloat(tempt).toFixed(2));
			}else{
				alert("网络异常，请刷新页面重试");
			}
		});
	}
	
	/**
	 * 减少购物车点击事件
	 */
	$(".mdish-cartcontrol-minus").on("click",function() {
		var _t = $(this);
		var proId = _t.parent().find("input[name=proId]").val();
		$.post(url+"mall/order/updateCart",{proId:proId,flag:0},function(data){
			if(data.thisNum > 0){
				_t.parent().find(".restaurant-food-originprice").html(data.thisNum);//设置当前数量
				$(".ui-quantity").html(data.totalNum);//设置总数
				$(".mcart-price").html("¥"+data.totalPrice);//设置总价
				//设置起送价格
				var tempt = qsPrice-data.totalPrice;
				if(tempt <= 0){
					$(".mcart-note").addClass("ng-hide");
					$(".mcart-checkout").removeClass("ng-hide");
				}else{
					$(".mcart-note").removeClass("ng-hide");
					$(".mcart-checkout").addClass("ng-hide");
				}
				$(".haichaPrice").html("¥"+parseFloat(tempt).toFixed(2));
			}else if(data.thisNum == 0){
				_t.addClass("ng-hide");
				_t.parent().find(".restaurant-food-originprice").addClass("ng-hide");
				$(".ui-quantity").html(data.totalNum);
				$(".haichaPrice").html(qsPrice);
				$(".mcart-price").html("¥"+data.totalPrice);
			}else{
				alert("系统异常");
			}
		});
	});
});

