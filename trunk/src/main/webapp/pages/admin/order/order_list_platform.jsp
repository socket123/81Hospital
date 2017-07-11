<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }/statics/admin/js/custom/manager.js"></script>
<script type="text/javascript" src="${url }/statics/admin/js/plugins/jqPaginator.js"></script>
<script type="text/javascript" src="${url }/statics/admin/js/custom/bootstrap.js"></script>
<%--<script type="text/javascript" src="${url }/statics/admin/js/custom/"></script>--%>
<link rel="stylesheet" href="${url }/statics/admin/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="${url }/statics/admin/css/bootstrap.min.css" type="text/css" />
<script type="text/javascript" src="${url }/assets/js/charts/echarts.min.js"></script>
 <div class="pageheader notab">
    <h1 class="pagetitle">平台总体情况</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
        <div id="contentwrapper" class="contentwrapper">


            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span6">
                        <div class="span12">
                            <h3>
                                物资消耗排名
                            </h3>
                        </div>

                        <div class="span12">
                                <c:forEach items="${listall}" var="listall" varStatus="vars" begin="0" end="2">
                                    <div class="talkbubble">
                                        <p class="zhongl">
                                            <span>种类：</span>
                                            <span>${listall.type_name}</span>
                                            <%--<span class="badge badge-danger" style="background-color:#f89406!important" >${vars.index+1}</span>--%>
                                        </p>
                                        <p class="zhongl">
                                            <span>数量：</span>
                                            <span>${listall.numbers}</span>
                                        </p>
                                    </div>
                                </c:forEach>
                        </div>
                        <div class="span12">
                            <h3>
                               物资库存
                            </h3>
                        </div>
                        <div class="span12">
                            <form id="searchForm" >
                                <div class="dataTables_wrapper" id="dyntable2_wrapper" >
                                    <input type="hidden" class="status_biao" name="status_biao" id="status_biao" value="${status_biao}">
                                    <button class="btn" type="button" onclick="hiede()" ><span class="">表格</span></button>
                                    <button class="btn" type="button" onclick="shows()" ><span class="">图表</span></button>
                                    <%--<a class="btn btn_orange btn_search radius50" onclick="hiede()"><span>表格</span></a>--%>
                                    <%--<a class="btn btn_orange btn_search radius50" onclick="shows()"><span>图表</span></a>--%>
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

                    </div>
                    <div class="span6">
                        <div id="mains" style="width: 100%;height:400px;"></div>
                    </div>
                </div>
            </div>
        </div>
<script>
    $('#page-jq').jqPaginator({
        totalPages: parseInt('${page.pages }'),
        visiblePages: 5,
        currentPage: parseInt('${page.pageNumber }'),
        onPageChange:function(num,type){
            if(type == 'change'){
                ListDepartment('order','currentIndex='+num+'&'+$('#searchForm').serialize(),'List_platform');
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





    var myCharts = echarts.init(document.getElementById('mains'));

    var options = {
        title: {
            text: '库存报警',
//            subtext: '数据来自网络'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['2011年']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: [
                <c:forEach items="${pager_procli}" varStatus="vars" var="pager_procli">
                    <c:if test="${pager_procli.status ==1}">
                '${pager_procli.type_name}',
                    </c:if>

                </c:forEach>

            ],
        },
        series: [
            {
                name: '数量',
                type: 'bar',
                data: [
                    <c:forEach items="${pager_procli}" varStatus="vars" var="pager_procli">
                            <c:if test="${pager_procli.status == 1}">
                                      ${pager_procli.numbers},
                            </c:if>
                    </c:forEach>


                     ]
            },

        ]
    };
    myCharts.setOption(options);
</script>