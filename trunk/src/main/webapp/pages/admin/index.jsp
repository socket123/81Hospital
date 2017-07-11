<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="url" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>vSmart医院辅助决策系统</title>
<link rel="stylesheet" href="${url }statics/admin/css/style.default.css" type="text/css" />
<script type="text/javascript" src="${url }statics/jquery.js"></script>
<script type="text/javascript">
	var url = '${url}';
</script>
<script type="text/javascript" src="${url }statics/admin/js/custom/general.js"></script>
<!--[if lte IE 8]>
	<script language="javascript" type="text/javascript" src="${url }statics/admin/js/plugins/excanvas.min.js"></script>
<![endif]-->
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="${url }statics/admin/css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="${url }statics/admin/css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="${url }statics/admin/js/plugins/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="withvernav">
<div class="bodywrapper">
    <div class="topheader">
        <div class="left">
            <h1 class="logo">vSmart.<span>Admin</span></h1>
            <span class="slogan">医院辅助决策系统</span>
            
            <div class="search">
            	<form action="" method="post">
                	<input type="text" name="keyword" id="keyword" value="请输入" />
                    <button class="submitbutton"></button>
                </form>
            </div><!--search-->
            
            <br clear="all" />
            
        </div><!--left-->
        
        <div class="right">
        	<!--<div class="notification">
                <a class="count" href="ajax/notifications.html"><span>9</span></a>
        	</div>-->
            <div class="userinfo">
            	<img src="${url }statics/admin/images/thumbs/avatar.png" alt="" />
                <span>${admin.username }</span>
            </div><!--userinfo-->
            
            <div class="userinfodrop">
            	<div class="avatar">
                	<a href=""><img src="${url }statics/admin/images/thumbs/avatarbig.png" alt="" /></a>
                    <div class="changetheme">
                    	切换主题: <br />
                    	<a class="default"></a>
                        <a class="blueline"></a>
                        <a class="greenline"></a>
                        <a class="contrast"></a>
                        <a class="custombg"></a>
                    </div>
                </div><!--avatar-->
                <div class="userdata">
                	<h4>${admin.username }</h4>
                    <span class="email">${admin.account }</span>
                    <ul>
                    	<li><a href="editprofile.html">编辑资料</a></li>
                        <li><a href="accountsettings.html">账号设置</a></li>
                        <li><a href="help.html">帮助</a></li>
                        <li><a href="${url }back/logout">退出</a></li>
                    </ul>
                </div><!--userdata-->
            </div><!--userinfodrop-->
        </div><!--right-->
    </div><!--topheader-->
    
    
    <div class="header">
    	<ul class="headermenu">
    		<!-- 
    		<li class="current"><a><span class="icon icon-flatscreen"></span>首页</a></li>
    		 -->
    		<c:forEach items="${oneMenu }" var="list" varStatus="n"> 
        		<li class="<c:if test="${n.index == 0 }">current</c:if> onemenu" id="${list.id }">
        			<a href="javascript:;">
        				<span class="icon ${list.ico_class }"></span>
        				${list.menu_name }
        			</a>
        		</li>
        	</c:forEach>
        </ul>
    </div><!--header-->
    <!--leftmenu-->
    <div class="vernav2 iconmenu">
	</div>
	
    <div class="centercontent">
		<iframe name="right" id="rightMain" src="" frameborder="false" scrolling="auto" style="border:none;" width="100%" height="470" allowtransparency="true"></iframe>
	</div><!-- centercontent -->
    
    
</div><!--bodywrapper-->
<script>
	//jQuery.noConflict(); 
	(function($) {
		var pid = $(".onemenu").eq(0).attr("id");
		linkajax(url+"admin/getmenu?pid="+pid,'vernav2');
		
		$(".headermenu li").on("click",function(){
			var pid = $(this).attr("id");
			$(".headermenu").find("li").removeClass("current");
			$(this).addClass("current");
			linkajax(url+"admin/getmenu?pid="+pid,'vernav2');
		});
	})(jQuery);
	
	function linkajax(url,clazz){
		$.ajax({
			type: 'POST',
		    url: url,
		    dataType: "html",
		    success: function(data){
		    	$("."+clazz).html(data);
		    }
		});
	}
</script>
</body>
</html>
