<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/custom/manager.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jqPaginator.js"></script>

<script type="text/javascript" src="${url }/assets/js/charts/echarts.min.js"></script>
 <div class="pageheader notab">
    <h1 class="pagetitle">物资损耗分析图表数据</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
    <form id="searchForm" >
        <div class="dataTables_wrapper" id="dyntable2_wrapper" style="height: 45px;">
            <%--<a class="btn btn_orange btn_search radius50" onclick="searchFormListDepartment('order','ListDepartment')"><span>搜索</span></a>--%>
            <div class="dataTables_filter" id="dyntable2_filter" style="width: 30%;">
                <%--<label>种类: <input type="text" name="name" class="name" style="width: 100px;margin: -7px 0 0px 0px;" value="${name}">&nbsp;&nbsp;</label>--%>
                <label>种类:
                    <select name="id" id="id" onchange="gradeChange('order','List_echarts')" style="margin: -7px 0 0 0;">
                        <%--<option value="">==全部==</option>--%>
                        <c:forEach items="${typeList}" var="typeList" varStatus="vars">
                            <option value="${typeList.id}" <c:if test="${type.id == typeList.id}" > selected</c:if>   >${typeList.typeName}</option>
                        </c:forEach>
                    </select>
                </label>

            </div>
        </div>

    </form>


	<!--contenttitle-->
	<div id="main" style="width: 100%;height:400px;"></div>
</div>
<script>


    var myChart = echarts.init(document.getElementById('main'));





    var serclist = {};

    <c:forEach items="${typelistss}" varStatus="typeListss"  var="typeList">

        var data = [
            <c:forEach items="${map[typeList.id]}" varStatus="vars"  var="maps" >
            [${vars.index+1}, ${maps}],

            </c:forEach>
        ];
        console.log(data);
        // See https://github.com/ecomfe/echarts-stat
        var myRegression = ecStat.regression('linear', data);
        myRegression.points.sort(function(a, b) {
            return a[0] - b[0];
        });
        serclist["scatter${typeListss.index}"]  = {
            name: '${typeList.typeName}',
            type: 'scatter',
            label: {
                emphasis: {
                    show: true,
                    position: 'left',
                    textStyle: {
                        color: 'blue',
                        fontSize: 16
                    }
                }
            },
            data: data
        };
        serclist["line${typeListss.index}"] = {
            name: 'line',
            type: 'line',
            showSymbol: false,
            data: myRegression.points,
            markPoint: {
                itemStyle: {
                    normal: {
                        color: 'transparent'
                    }
                },
                label: {
                    normal: {
                        show: true,
                        position: 'left',
                        formatter: myRegression.expression,
                        textStyle: {
                            color: '#333',
                            fontSize: 14
                        }
                    }
                },
                data: [{
                    coord: myRegression.points[myRegression.points.length - 1]
                }]
            }
        };

    </c:forEach>

    var serice =  [
        <c:forEach items="${typelistss}" varStatus="typeListss"  var="typeList">
            serclist["scatter${typeListss.index}"],
            serclist["line${typeListss.index}"],
        </c:forEach>
    ];


   var option = {
        title: {
            text: '12个月损耗图',
//            subtext: 'By ecStat.regression',
//            sublink: 'https://github.com/ecomfe/echarts-stat',
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        xAxis: {
            type: 'value',
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            },
        },
        yAxis: {
            type: 'value',
            min: 1.5,
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            },
        },
        series:[]
    };

    option.series = serice;




    myChart.setOption(option);
</script>