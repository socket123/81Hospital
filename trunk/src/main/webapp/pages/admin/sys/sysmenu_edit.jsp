<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/sysManager/sysmenu_edit.js"></script>
<style>
.stdform label span{color: red;margin-right: 5px;}
</style>

	<div class="subcontent">
         <form id="form1" class="stdform">
         	<p>
             	<label><span>*</span>菜单名称</label>
                 <span class="field">
                 	<input type="text" name="menuName" class="smallinput" value="${record.menuName }"/>
                 	<input type="hidden" name="id" id="id" value="${record.id }"/>
                 </span>
             </p>
             <p>
             	<label>菜单URL</label>
                 <span class="field">
                 	<input type="text" name="menuUrl" class="smallinput" value="${record.menuUrl }"/>
                 	顶级菜单可以不填写URL
                 </span>
             </p>
             <p>
             	<label><span>*</span>菜单级别</label>
                 <span class="field">
                 	<select name="menuLevel" id="level">
                 		<option value="1">顶级</option>
                 		<option value="2">2级</option>
                 		<option value="3">3级</option>
                 	</select>
                 </span>
             </p>
             <p>
             	<label><span>*</span>上级菜单</label>
                 <span class="field">
                 	<select name="pid" id="ppid">
                 		<option value="">==请选择上级菜单==</option>
                 		<c:forEach items="${memuList }" var="list">
	                 		<option value="${list.id }">${list.menuName }</option>
                 		</c:forEach>
                 	</select>
                 </span>
             </p>
             <p>
             	<label>样式</label>
                 <span class="field">
                 	<input type="text" name="icoClass" class="smallinput" value="${record.icoClass }"/>
                 </span>
             </p>
             <p>
             	<label>排序</label>
                 <span class="field">
                 	<input type="text" name="listOrder" class="smallinput" value="${record.listOrder }"/>
                 </span>
             </p>
             <p class="stdformbutton">
             	<button type="button" class="submit radius2">保存</button>
             	<button type="button" class="back radius2">返回</button>
             </p>
         </form>
    </div><!--subcontent-->

<script>
	$("#level").val("${record.menuLevel }");
	$("#ppid").val("${record.pid }");
	if("${record.menuLevel }" == "1"){
		$("#ppid").html("<option value='0'>顶级菜单</option>");
	}
</script>