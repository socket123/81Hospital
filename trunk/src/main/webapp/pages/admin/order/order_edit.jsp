<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.validate.min.js"></script>
<div class="pageheader notab">
    <h1 class="pagetitle">科室物资--申请</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<div class="subcontent">
         <form id="form1" class="stdform">
         	<%--<p>--%>
             	<%--<label>部门名称</label>--%>
                 <%--<span class="field">--%>
                 	<%--<input type="text" name="departmentName" class="smallinput" value="${Order.departmentName }"/>--%>

                     <%--&lt;%&ndash;内部申请&ndash;%&gt;--%>
                 <%--</span>--%>
             <%--</p>--%>


             <p>
                 <label>部门选择</label>
                 <span class="field">
                 	<select name="departmentId"  id="departmentId" class="departmentId">

                        <option value="">请选择</option>
                        <c:forEach varStatus="vars" var="departmentList" items="${departmentList}">
                            <option value="${departmentList.id}"  <c:if test="${departmentList.id == Order.departmentId}">
                                        selected
                            </c:if> >${departmentList.departmentName}</option>

                        </c:forEach>

                    </select>
                 </span>
             </p>
             <p>
                 <label>种类选择</label>
                 <span class="field">
                 	<select name="typeId"  id="typeId" class="typeId">

                        <option value="">请选择</option>
                        <c:forEach varStatus="vars" var="types" items="${types}">
                            <option value="${types.id}"  <c:if test="${types.id == Order.typeId}">
                                selected
                            </c:if> >${types.typeName}</option>

                        </c:forEach>

                    </select>
                 </span>
             </p>

             <p>
                 <label>订单数量</label>
                 <span class="field">

                 	<input type="number" name="number" class="number" value="${Order.number }"/>

                 </span>
             </p>

             <p>
                 <label>订单备注</label>
                 <span class="field">

                     <textarea type="text" name="orderDes" class="orderDes" >
                         ${Order.orderDes }
                     </textarea>

                 </span>
             </p>
                <input type="hidden" name="id" value="${Order.id }"/>
                <input type="hidden" name="orderType" value="1"/>
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
		location.href = url +'admin/order/list';
	});
	$(".submit").on("click",function(){
		if($("#form1").valid()){
			$.ajax({
				url: url +'admin/order/merge',
				data:$('#form1').serialize(),
				dataType: "json",
				type : "post",
				success: function(data) {
					if(data > 0){
						alert("保存成功");
						location.href = url +'admin/order/list';
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
            },
            departmentId: {
                required: true

            },
            typeId: {
                required: true

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
            departmentId: {
                required: "选择不能为空",

            },
            typeId: {
                required: "选择不能为空",

            },
		}
	});
}
</script>