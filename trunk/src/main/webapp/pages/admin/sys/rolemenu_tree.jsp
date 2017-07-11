<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<style>
.dyntable2_left {width: 500px;border: 1px solid #ddd; position: absolute;overflow-x:hide;margin-top: 20px;}
.radius2 {
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    border: 1px solid #f0882c;
    background: #fb9337;
    color: #fff;
    cursor: pointer;
    padding: 7px 10px;
    font-weight: bold;
}
</style>
 <div class="pageheader notab">
    <h1 class="pagetitle">角色菜单调整</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<a class="save radius2">保存</a>
	<a class="cancel radius2" href="${url }admin/role/list">取消</a>
	<input type="hidden" id="roleId" value="${roleId}">
	<div id="dyntable2_left" class="dyntable2_left">
		<div><ul id="treeDemo" class="ztree"></ul></div>
	</div>
</div>
<script type="text/javascript">
var adminUrl = '${url}admin/';
//zTree 的数据属性
var zNodes = ${treeJson};
var roleId = "${roleId}";
</script>
<script type="text/javascript" src="${url }statics/admin/js/ztree/ztree.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/ztree/jquery.ztree.excheck.js"></script>
<link rel="stylesheet" media="screen" href="${url }statics/admin/js/ztree/zTreeStyle/zTreeStyle.css"/>
<script> 
//初始化
$(document).ready(function(){
	/******************ztree*******************/
	var zTreeObj;
	// zTree 的参数配置
	var setting = {
		data: {
			simpleData: {
				enable:true
			}
		},
		check: {
			enable: true
		}
	};
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	zTreeObj.expandAll(true);
	
	$(".save").on("click",function(){
		var roleId = $("#roleId").val();
		var menuIds = getSelectedIds();
		if(!menuIds){
			layer.msg("请选择菜单");return;
		}
		
		$.post(adminUrl+"role/saveRoleMenu",{roleId:roleId,menuIds:menuIds},function(data){
			if(data > 0){
				layer.alert("分配菜单成功！");
			}else{
				layer.alert("分配菜单失败，请重新分配！");
			}
		})
	});
});

//获取选择的多个选框ID
function getSelectedIds() {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    checkCount = zTree.getCheckedNodes(true);

    var classpurview = "";
    for(var i=0;i<checkCount.length;i++) {
    	if(i == 0){
    		classpurview += checkCount[i].id
    	}else{
        	classpurview += "," + checkCount[i].id
    	}
    }
    return classpurview;
}
</script>
