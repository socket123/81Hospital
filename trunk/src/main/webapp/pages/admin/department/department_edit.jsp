<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.validate.min.js"></script>
<div class="pageheader notab">
    <h1 class="pagetitle">部门管理--新增/修改部门</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<div class="subcontent">
         <form id="form1" class="stdform">
         	<p>
             	<label>部门名称</label>
                 <span class="field">
                 	<input type="text" name="departmentName" class="smallinput" value="${department.departmentName }"/>
                 	<input type="hidden" name="id" value="${department.id }"/>
                 </span>
             </p>
             <p>
                 <label>部门简介</label>
                 <span class="field">
                 	<input type="text" name="departmentIntroduce" class="smallinput" value="${department.departmentIntroduce }"/>

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
		location.href = url +'admin/department/list';
	});
	$(".submit").on("click",function(){
		if($("#form1").valid()){
			$.ajax({
				url: url +'admin/department/merge',
				data:$('#form1').serialize(),
				dataType: "json",
				type : "post",
				success: function(data) {
					if(data > 0){
						alert("保存成功");
						location.href = url +'admin/department/list';
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
            departmentName: {
				required: true,
				maxlength:20
			},
            departmentIntroduce: {
                required: true,
                maxlength:20
            }
		},
		messages: {
            departmentName: {
				required: "名称不能为空",
				maxlength:"请不要超过20个字符"
			},
            departmentIntroduce: {
                required: "名称不能为空",
                maxlength:"请不要超过20个字符"
            },
		}
	});
}
</script>