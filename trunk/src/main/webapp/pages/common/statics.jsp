<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="url" value="${pageContext.request.contextPath}/" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="${url }statics/admin/css/style.default.css" type="text/css" />
<link rel="stylesheet" href="${url }statics/admin/css/styles.css" type="text/css" />

<script type="text/javascript" src="${url }statics/jquery.js"></script>

<script type="text/javascript" src="${url }/assets/js/charts/echarts.min.js"></script>
<script type="text/javascript" src="${url }/assets/js/charts/ecStat.js"></script>
<script type="text/javascript" src="${url }/assets/js/charts/echarts.min.js"></script>


<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="${url }statics/layer/layer.js"></script>
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

<script type="text/javascript">
	var url = '${url}';
	
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
	
	function linkajaxAppend(url,clazz){
		$.ajax({
			type: 'POST',
		    url: url,
		    dataType: "html",
		    success: function(data){
		    	$("."+clazz).append(data);
		    }
		});
	}
</script>	
