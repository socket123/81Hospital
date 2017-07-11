<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/custom/manager.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jqPaginator.js"></script>
 <div class="pageheader notab">
    <h1 class="pagetitle">种类管理</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<!--contenttitle-->
	<form id="searchForm" >
	<div class="dataTables_wrapper" id="dyntable2_wrapper">

		<div id="dyntable2_length" class="dataTables_length">
			<label><a class="btn btn2 btn_blue btn_bulb" onclick="add('type')"><span>新增</span></a></label>
		</div>

				<%--<div class="dataTables_filter" id="dyntable2_filter">--%>
					<%--<label>部门名称: <input type="text" name="departmentName" value="${departmentName }"></label>--%>
					<%--<a class="btn btn_orange btn_search radius50" onclick="searchForm('department')"><span>搜索</span></a>--%>
				<%--</div>--%>

		<table cellpadding="0" cellspacing="0" border="0" class="stdtable" id="dyntable2">
			<thead>
				<tr>
					<th class="head1">ID</th>
					<th class="head1">种类名称</th>
					<th class="head1">种类简介</th>
					<th class="head1">时间</th>
					<th class="head1">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="list">
				<tr class="gradeX">
					<td>${list.id }</td>
					<td>${list.typeName }</td>
					<td>${list.typeDes }</td>
					<td><fmt:formatDate value="${list.createTime }" pattern="yyyy-mm-dd HH:MM:ss"></fmt:formatDate></td>
					<td class="center">
					<input type="hidden" name="id" value="${list.id }">

							<a class="btn btn_flag" onclick="edit('type','${list.id }')"><span>编辑</span></a>

							<a class="btn btn_trash" onclick="del('type','${list.id }')"><span>删除</span></a>

					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pull-right">
			<ul class="pagination pagination-sm no-margin">
				<li><span class="pa-span">每页<i> ${page.pageSize } </i>条记录，共<i> ${page.total } </i>条记录</span></li>
			</ul>
			<ul id="page-jq" class="pagination pagination-sm no-margin"></ul>
		</div>
	</div>
	</form>
</div>
<script> 
$('#page-jq').jqPaginator({
    totalPages: parseInt('${page.pages }'),
    visiblePages: 5,
    currentPage: parseInt('${page.pageNum }'),
    onPageChange:function(num,type){
    	if(type == 'change'){
    		resetP('type','currentIndex='+num+'&'+$('#searchForm').serialize());
    	}
    }
});
</script>