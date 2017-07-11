<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/sysManager/mallconfig_list.js"></script>
 <div class="pageheader notab">
    <h1 class="pagetitle">商城配置</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<!--contenttitle-->
	<div class="dataTables_wrapper" id="dyntable2_wrapper">
	
		<div id="dyntable2_length" class="dataTables_length">
			<label><a class="btn btn2 btn_blue btn_bulb add"><span>新增</span></a></label>
		</div>
		<div class="dataTables_filter" id="dyntable2_filter">
			&nbsp;
			<input type="hidden" id="typeName" value="${typeName }"/>
		</div>
	
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable" id="dyntable2">
			<thead>
				<tr>
					<th class="head1">排序</th>
					<th class="head1">配置位</th>
					<th class="head1">内容</th>
					<th class="head1">最后修改时间</th>
					<th class="head1">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty(list) }">
					<tr class="gradeX">
					<td colspan="8">暂无数据</td>
					</tr>
				</c:if>			
				<c:forEach items="${list }" var="list">
				<tr class="gradeX">
					<td>${list.listOrder }</td>
					<td>
						<c:if test="${list.typeName == 'index'}">首页轮播大图</c:if>
						<c:if test="${list.typeName != 'index'}">${list.typeName}</c:if>
					</td>
					<td>
						<c:if test="${empty list.content }">
	                 		<img src='${url}statics/admin/images/default.png' style='height: 50px; width: 50px'/>
	                 	</c:if>
	                 	<c:if test="${!empty list.content }">
	                 		<img src='${url }file/showpic?pic=${list.content}' style='height: 50px; width: 50px'/>
	                 	</c:if>
					</td>
					<td>${list.lastTime }</td>
					<td class="center">
						<input type="hidden" name="id" value="${list.id }">
						<a class="btn btn_flag edit"><span>编辑</span></a>
						<a class="btn btn_trash"><span>删除</span></a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>