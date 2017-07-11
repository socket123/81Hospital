<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="url" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>登录页面_WechatAdmin后台管理系统</title>
<script type="text/javascript">
	var url = '${url}';
</script>
<link rel="stylesheet" href="${url }statics/admin/css/style.default.css" type="text/css" />
<script type="text/javascript" src="${url }statics/jquery.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/custom/general.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/custom/index.js"></script>
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="${url }statics/admin/css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="${url }statics/admin/css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="${url }statics/admin/js/plugins/css3-mediaqueries.js"></script>
<![endif]-->

    <script id="-mob-share" src="http://f1.webshare.mob.com/code/mob-share.js?appkey=1ed3efb81c580"></script>
</head>

<body class="loginpage">
	<div class="loginbox">
    	<div class="loginboxinner">
            <div class="logo">
            	<h1 class="logo">Wechat.<span>Admin</span></h1>
				<span class="slogan">后台管理系统</span>
            </div><!--logo-->
            <br clear="all" /><br />
            <div class="nousername">
				<div class="loginmsg">用户名不正确.</div>
            </div><!--nousername-->
            <div class="nopassword">
				<div class="loginmsg">密码不正确.</div>
                <div class="loginf">
                    <div class="thumb"><img src="${url }statics/admin/images/thumbs/avatar1.png" /></div>
                    <div class="userlogged">
                        <h4></h4>
                        <a href="${url }admin/index"><span></span>是否正确?</a> 
                    </div>
                </div><!--loginf-->
            </div><!--nopassword-->
               <div class="username">
               	<div class="usernameinner">
                   	<input type="text" name="username" id="username" />
                   </div>
               </div>
               <div class="password">
               	<div class="passwordinner">
                   	<input type="password" name="password" id="password" />
                   </div>
               </div>
               <button id="login">登录</button>
               <div class="keep"><input type="checkbox" />记住密码</div>
        </div><!--loginboxinner-->
    </div><!--loginbox-->
</body>
</html>
