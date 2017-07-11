<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/ztree/ztree.js"></script>
<link rel="stylesheet" media="screen" href="${url }statics/admin/js/ztree/zTreeStyle/zTreeStyle.css"/>

<style>
.dataTables_wrapper {margin-left: 200px;border: 1px solid #ddd;}
.dataTables_wrapper input {width: 160px;}
.dyntable2_left {width: 200px;
    border: 1px solid #ddd;
    position: absolute;overflow-x:hide }
</style>
<script type="text/javascript">
//zTree 的数据属性
var zNodes = ${treeJson};
</script>
 <div class="pageheader notab">
    <h1 class="pagetitle">系统菜单管理</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<div id="dyntable2_left" class="dyntable2_left">
		<div><ul id="treeDemo" class="ztree"></ul></div>
	</div>
	<!--contenttitle-->
	<div class="dataTables_wrapper" id="dyntable2_wrapper">
		<div id="dyntable2_length" class="dataTables_length">
			<label><a class="btn btn2 btn_blue btn_bulb add"><span>新增</span></a></label>
		</div>
		<div class="dataTables_filter" id="dyntable2_filter">
			<label>菜单名称: <input type="text" id="menuName" value="${menuName }">&nbsp;&nbsp;</label>
			<input type="hidden" id="pid" value="${pid }">
			<a class="btn btn_orange btn_search radius50"><span>搜索</span></a>
		</div>
	
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable" id="dyntable2">
			<thead>
				<tr>
					<th class="head1">ID</th>
					<th class="head1">菜单名称</th>
					<th class="head1">级别</th>
					<th class="head1">菜单URL</th>
					<th class="head1">排序</th>
					<th class="head1">菜单样式</th>
					<th class="head1">创建时间</th>
					<th class="head1">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr class="gradeX">
					<td colspan="8">暂无数据</td>
				</tr>
			</tbody>
		</table>
		<script type="text/javascript" src="${url }statics/admin/js/sysManager/sysmenu_list.js"></script>
	</div>
</div>
