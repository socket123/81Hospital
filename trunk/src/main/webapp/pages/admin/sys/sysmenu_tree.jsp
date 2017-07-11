<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/sysManager/sysmenu_list.js"></script>
<link rel="stylesheet" href="${url }statics/admin/js/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${url }statics/admin/js/ztree/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="${url }statics/admin/js/ztree/jquery.ztree.excheck.js"></script>
<style>
	.dataTables_wrapper {border: solid #ccc 1px;height: 100%;}
	.leftDiv {
		float: left;
	    width: 200px;
	    border-right: 1px;
	    display: block;
	    background-color: #d5d5d5;
	    height: 100%;
    }
	.rightDiv {
		float: left;
    }
</style>
 <div class="pageheader notab">
    <h1 class="pagetitle">角色菜单调整</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<!--contenttitle-->
	<div class="dataTables_wrapper" id="dyntable2_wrapper">
		<div class="leftDiv"></div>
		<div class="rightDiv"></div>
	</div>
</div>