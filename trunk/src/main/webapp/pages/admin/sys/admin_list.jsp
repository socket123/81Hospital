<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/sysManager/adminlist.js"></script>
 <div class="pageheader notab">
    <h1 class="pagetitle">管理员账号管理</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<!--contenttitle-->
	<div class="dataTables_wrapper" id="dyntable2_wrapper">
	
		<div id="dyntable2_length" class="dataTables_length">
			<label><a class="btn btn2 btn_blue btn_bulb add"><span>新增</span></a></label>
		</div>
		<div class="dataTables_filter" id="dyntable2_filter">
			<label>姓名: <input type="text" name="username" value="${admin.username }">&nbsp;&nbsp;</label>
			<label>账号: <input type="text" name="account" value="${admin.account }">&nbsp;&nbsp;</label>
			<label>角色: 
				<select name="roleId" id="roleId">
					<option value="">==全部==</option>
				</select>
			</label>
			<a class="btn btn_orange btn_search radius50"><span>搜索</span></a>
		</div>
	
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable" id="dyntable2">
			<thead>
				<tr>
					<th class="head1">ID</th>
					<th class="head1">昵称</th>
					<th class="head1">账号</th>
					<th class="head1">角色</th>
					<th class="head1">创建时间</th>
					<th class="head1">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="list">
				<tr class="gradeX">
					<td>${list.id }</td>
					<td>${list.username }</td>
					<td>${list.account }</td>
					<td>${list.roleName }</td>
					<td>${list.createTime }</td>
					<td class="center">
						<c:if test="${list.roleId != -1}">
							<input type="hidden" name="id" value="${list.id }">
							<a class="btn btn_flag edit"><span>编辑</span></a>
							<a class="btn btn_trash"><span>删除</span></a>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="dataTables_info" id="dyntable2_info">
			共${page.total }条记录
		</div>
		
		<div class="dataTables_paginate paging_full_numbers" id="dyntable2_paginate">
			<span class="first paginate_button paginate_button_disabled" id="dyntable2_first">首页</span>
			<span class="previous paginate_button paginate_button_disabled" id="dyntable2_previous">下一页</span>
			<span>
				<c:forEach items="${page.navigatePageNumbers }" var="list">
					<span class="<c:if test="${list == page.pageNumber}">paginate_active</c:if>
					<c:if test="${list != page.pageNumber}">paginate_button</c:if>">${list }</span>
				</c:forEach>
			</span>
			<span class="next paginate_button" id="dyntable2_next">上一页</span>
			<span class="last paginate_button" id="dyntable2_last">尾页</span>
		</div>
	</div>
</div>