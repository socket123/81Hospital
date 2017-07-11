///// LOGIN FORM SUBMIT /////
function login(){
	var username = jQuery('#username').val();
	var password = jQuery('#password').val();
	
	if(username == '' && password == '') {
		jQuery('.nousername').fadeIn();
		jQuery('.nopassword').hide();
		return false;	
	}
	if(username != '' && password == '') {
		jQuery('.nopassword').fadeIn().find('.userlogged h4, .userlogged a span').text(username);
		jQuery('.nousername,.username').hide();
		return false;
	}
	jQuery.post(url+"back/loginajax",{username:username,password:password},function(data){
		if(data == 200){
			location.href = url + "admin/index";
		}else{
			jQuery('.nopassword').fadeIn().find('.userlogged h4, .userlogged a span').text(username);
			jQuery('.nousername,.username').hide();
		}
		return false;
	});
}

jQuery(document).ready(function(){
	jQuery('#login').click(function(){
		login();
	});
	///// TRANSFORM CHECKBOX /////							
	jQuery('input:checkbox').uniform();
	
	///// ADD PLACEHOLDER /////
	jQuery('#username').attr('placeholder','用户名');
	jQuery('#password').attr('placeholder','密码');
});

document.onkeydown=function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if(e && e.keyCode==13){ // enter 键
		login();
	}
}; 

