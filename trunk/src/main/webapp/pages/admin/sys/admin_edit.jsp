<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.validate.min.js"></script>
<style>
.stdform label span{color: red;margin-right: 5px;}
</style>
<div class="pageheader notab">
    <h1 class="pagetitle">管理员管理--新增/修改管理员</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<div class="subcontent">
         <form id="form1" class="stdform">
         	<p>
             	<label><span>*</span>姓名</label>
                 <span class="field">
                 	<input type="text" name="username" class="smallinput" value="${admin.username }"/>
                 	<input type="hidden" name="id" value="${admin.id }"/>
                 </span>
             </p>
             <p>
             	<label><span>*</span>账号</label>
                 <span class="field">
                 	<input type="text" name="account" <c:if test="${!empty(admin) }">readonly='readonly' </c:if>class="smallinput" value="${admin.account }"/>
                 	一经保存不可以修改
                 </span>
             </p>
             <p>
             	<label><span>*</span>密码</label>
                 <span class="field">
                 	系统默认密码为：ntgame888
                 </span>
             </p>
             <p>
             	<label><span>*</span>角色</label>
                 <span class="field">
                 	<select name="roleId" id="roleId" <c:if test="${admin.roleId != '' && admin.roleId != null}">disabled='disabled'</c:if> >
                 		<option value="">==请选择角色==</option>
                 		<c:forEach items="${roleList }" var="list">
	                 		<option value="${list.id }">${list.roleName }</option>
                 		</c:forEach>
                 	</select>
                 	<c:if test="${admin.roleId != '' && admin.roleId != null}">
                 		<input type="hidden" name="roleId" value="${admin.roleId }"/>
                 	</c:if>
                 </span>
             </p>
             <p class="stdformbutton">
             	<button type="button" class="submit radius2">保存</button>
             	<button type="button" class="back radius2">返回</button>
             </p>
         </form>
    </div><!--subcontent-->
</div>
<script>
$(document).ready(function(){});
	$("#roleId").val("${admin.roleId }");

	validateForm();

	$(".back").on("click",function(){
		location.href = url+'admin/manager/list';
	});
	$(".submit").on("click",function(){
		if($("#form1").valid()){
			$.ajax({
				url: url+"admin/manager/merge",
				data:$('#form1').serialize(),
				dataType: "json",
				type : "post",
				success: function(data) {
					if(data > 0){
						alert("保存成功");
						location.href = url+'admin/manager/list';
					}else{
						alert("保存失败");return;
					}
				}
			});
		}
		//alert(123213)
	});
	///// FORM VALIDATION /////
	

/*验证*/
function validateForm() {
	$("#form1").validate({
		rules: {
			userName: {
				required: true,
				maxlength:20
			},
			account: {
				required: true,
				maxlength:20
			},
			roleId: {
				required: true
			}
		},
		messages: {
			userName: {
				required: "姓名不能为空",
				maxlength:"请不要超过20个字符"
			},
			account: {
				required: "账号不能为空",
				maxlength:"请不要超过20个字符"
			},
			roleId: {
				required: "角色不能为空"
			}
		}
	});
}
</script>