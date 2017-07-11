<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/custom/manager.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jqPaginator.js"></script>
 <div class="pageheader notab">
    <h1 class="pagetitle">物资预判统计</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<!--contenttitle-->
	<form id="searchForm" >
	<div class="dataTables_wrapper" id="dyntable2_wrapper">
		<input type="hidden" class="status_biao" name="status_biao" id="status_biao" value="${status_biao}">

		<a class="btn btn_orange btn_search radius50" onclick="hiede()"><span>表格</span></a>
		<a class="btn btn_orange btn_search radius50" onclick="shows()"><span>图表</span></a>
		<%--<div id="dyntable2_length" class="dataTables_length">--%>
			<%--<label><a class="btn btn2 btn_blue btn_bulb" onclick="add('order')"><span>新增</span></a></label>--%>
		<%--</div>--%>

				<%--<div class="dataTables_filter" id="dyntable2_filter">--%>
					<%--<label>科室:--%>
						<%--<select name="id" id="id">--%>
							<%--<option value="">==全部==</option>--%>
							<%--<c:forEach items="${departMent}" var="departMent" varStatus="vars">--%>
							<%--<option value="${departMent.id}" <c:if test="${department == departMent.id}" > selected</c:if>   >${departMent.departmentName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</label>--%>
					<%--<a class="btn btn_orange btn_search radius50" onclick="searchFormListDepartment('order','ListDepartment')"><span>搜索</span></a>--%>
				<%--</div>--%>
		<div class="disnoe">
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable" id="dyntable2">
			<thead>
				<tr>
					<th class="head1">种类名称</th>
					<th class="head1">预判物资数量</th>

					<%--<th class="head1">状态</th>--%>
					<%--<th class="head1">种类</th>--%>
					<%--<th class="head1">数量</th>--%>
					<%--<th class="head1">申请时间</th>--%>
					<%--<th class="head1">时间</th>--%>
					<%--<th class="head1">操作</th>--%>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="list">
				<tr class="gradeX">
					<c:if test="${ empty page.list }">
						<td>暂无数据</td>
						<td>暂无数据</td>
					</c:if>
					<c:if test="${ ! empty page.list }">
						<td>${list.typeName }</td>
						<td>${list.numbers }</td>
					</c:if>

					<%--<td>--%>

					<%--</td>--%>
					<%--<td>--%>
						<%--<c:if test="${list.orderType == 0 }">--%>
							<%--医院内部采购--%>
						<%--</c:if>--%>
						<%--<c:if test="${list.orderType == 1 }">--%>
							<%--医院外部采购--%>
						<%--</c:if>--%>
					<%--</td>--%>
					<%--<td>${list.department_name }</td>--%>
					<%--<td>${list.type_name }</td>--%>
					<%--<td>${list.number }</td>--%>
					<%--<td><fmt:formatDate value="${list.time}" pattern="yyyy-mm-dd HH:MM:ss"></fmt:formatDate></td>--%>
					<%--<td><fmt:formatDate value="${list.createTime }" pattern="yyyy-mm-dd HH:MM:ss"></fmt:formatDate></td>--%>
					<%--<td class="center">--%>
					<%--<input type="hidden" name="id" value="${list.id }">--%>

							<%--<a class="btn btn_flag" onclick="edit('order','${list.id }')"><span>编辑</span></a>--%>

							<%--<a class="btn btn_trash" onclick="del('order','${list.id }')"><span>删除</span></a>--%>

					<%--</td>--%>
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
		<%--<div class="dataTables_info" id="dyntable2_info">--%>
			<%--共${page.total }条记录--%>
		<%--</div>--%>

		<%--<div class="dataTables_paginate paging_full_numbers" id="dyntable2_paginate">--%>
			<%--<span class="first paginate_button paginate_button_disabled" id="dyntable2_first">首页</span>--%>
			<%--<span class="previous paginate_button paginate_button_disabled" id="dyntable2_previous">下一页</span>--%>
			<%--<span>--%>
				<%--<c:forEach items="${page.navigatePageNumbers }" var="list">--%>
					<%--<span class="<c:if test="${list == page.pageNumber}">paginate_active</c:if>--%>
					<%--<c:if test="${list != page.pageNumber}">paginate_button</c:if>">${list }</span>--%>
				<%--</c:forEach>--%>
			<%--</span>--%>
			<%--<span class="next paginate_button" id="dyntable2_next">上一页</span>--%>
			<%--<span class="last paginate_button" id="dyntable2_last">尾页</span>--%>
		<%--</div>--%>
	</div>
	</form>
	<div id="main" style="width: 100%;height:400px;"></div>
</div>


<script> 
$('#page-jq').jqPaginator({
    totalPages: parseInt('${page.pages }'),
    visiblePages: 5,
    currentPage: parseInt('${page.pageNum }'),
    onPageChange:function(num,type){
    	if(type == 'change'){
            ListDepartment('order','currentIndex='+num+'&'+$('#searchForm').serialize(),'List_Anticipation');
    	}
    }
});



var myChart = echarts.init(document.getElementById('main'));

var option = {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true,
        x: 40,
        x2: 100,
        y2: 150// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
    },
    xAxis : [
        {
            type : 'category',
            data : [
                <c:forEach items="${page.list }" var="list">
                "${list.typeName}",
                </c:forEach>
            ],
            axisTick: {
                alignWithLabel: true
            },
            axisLabel :{
                interval:0,
                rotate: 10,
            },
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'数量',
            type:'bar',
            barWidth: '60%',
            data:[

                <c:forEach items="${page.list }" var="list">
                ${list.numbers},
                </c:forEach>
            ]
        }
    ]
};
myChart.setOption(option);

</script>