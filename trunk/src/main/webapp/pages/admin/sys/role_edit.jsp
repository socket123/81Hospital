<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.validate.min.js"></script>
<div class="pageheader notab">
    <h1 class="pagetitle">权限管理--新增/修改角色</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<div class="subcontent">
         <form id="form1" class="stdform">
         	<p>
             	<label>角色名称</label>
                 <span class="field">
                 	<input type="text" name="roleName" class="smallinput" value="${role.roleName }"/>
                 	<input type="hidden" name="id" value="${role.id }"/>
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
	validateForm();

	$(".back").on("click",function(){
		location.href = url +'admin/role/list';
	});
	$(".submit").on("click",function(){
		if($("#form1").valid()){
			$.ajax({
				url: url +'admin/role/merge',
				data:$('#form1').serialize(),
				dataType: "json",
				type : "post",
				success: function(data) {
					if(data > 0){
						alert("保存成功");
						location.href = url +'admin/role/list';
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
			roleName: {
				required: true,
				maxlength:20
			}
		},
		messages: {
			roleName: {
				required: "角色名称不能为空",
				maxlength:"请不要超过20个字符"
			}
		}
	});
}
</script>