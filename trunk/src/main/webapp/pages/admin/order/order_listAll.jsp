<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/custom/manager.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jqPaginator.js"></script>

 <div class="pageheader notab">
    <h1 class="pagetitle">医院物资统计</h1>

    <span class="pagedesc"></span>

</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<!--contenttitle-->
	<form id="searchForm" >
	<div class="dataTables_wrapper" id="dyntable2_wrapper" >
		<input type="hidden" class="status_biao" name="status_biao" id="status_biao" value="${status_biao}">

		<a class="btn btn_orange btn_search radius50" onclick="hiede()"><span>表格</span></a>
		<a class="btn btn_orange btn_search radius50" onclick="shows()"><span>图表</span></a>
		<a class="btn btn_orange btn_search radius50" onclick="excels('order','listAll')"><span>导出</span></a>
		<a class="btn btn_orange btn_search radius50" onclick="searchFormListDepartment('order','listAll')"><span>搜索</span></a>



		<div class="dataTables_filter" id="dyntable2_filter" style="width: 30%;">
			<label>种类: <input type="text" name="name" class="name" style="width: 200px;margin: -7px 0 0px 0px;" value="${name}">&nbsp;&nbsp;</label>
		</div>
	<div class="disnoe">
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable " id="dyntable2">
			<thead>
				<tr>
					<th class="head1">种类名称</th>
					<th class="head1">数量统计</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="list">
				<tr class="gradeX">
					<td>${list.type_name }</td>
					<td>${list.numbers }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pull-right ">
			<ul class="pagination pagination-sm no-margin">
				<li><span class="pa-span">每页<i> ${page.limit } </i>条记录，共<i> ${page.total } </i>条记录</span></li>
			</ul>
			<ul id="page-jq" class="pagination pagination-sm no-margin"></ul>
		</div>
</div>
	</div>
	</form>

	<div id="main" style="width: 100%;height:400px;"></div>

</div>
<script> 
$('#page-jq').jqPaginator({
    totalPages: parseInt('${page.pages }'),
    visiblePages: 5,
    currentPage: parseInt('${page.pageNumber }'),
    onPageChange:function(num,type){
    	if(type == 'change'){
            ListDepartment('order','currentIndex='+num+'&'+$('#searchForm').serialize(),'listAll');
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
               "${list.type_name}",
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