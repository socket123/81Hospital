<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.validate.min.js"></script>
<style>
    .stdform label span{color: red;margin-right: 5px;}
</style>
<div class="pageheader notab">
    <h1 class="pagetitle">用户管理--修改用户</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
    <div class="subcontent">
        <form id="form1" class="stdform">
            <p>
                <label><span>*</span>姓名</label>
                <span class="field">
                 	<input type="text" name="userName" class="smallinput" value="${record.userName }"/>
                 	<input type="hidden" name="id" value="${record.id }"/>
                 </span>
            </p>
            <p>
                <label>性别</label>
                <span class="field">
                 	<select name="sex" id="sex">
                        <option value="">---请选择---</option>
                 		<option value="1">男</option>
                 		<option value="0">女</option>
                 	</select>
                 </span>
            </p>
            <p>
                <label><span>*</span>手机号</label>
                <span class="field">
                 	<input type="text" name="phone" class="smallinput" value="${record.phone }"/>
                 </span>
            </p>
            <p>
                <label>微信号</label>
                <span class="field">
                 	<input type="text" name="wxNumber" class="smallinput" value="${record.wxNumber }"/>
                 </span>
            </p>
            <p>
                <label>QQ号</label>
                <span class="field">
                 	<input type="text" name="qqNumber" class="smallinput" value="${record.qqNumber }"/>
                 </span>
            </p>
            <p>
                <label>房卡数量</label>
                <span class="field">
                 	<input type="text" name="cardNum" class="smallinput" value="${record.cardNum }"/>
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
        location.href = url+'admin/user/list';
    });
    $(".submit").on("click",function(){
        if($("#form1").valid()){
            $.ajax({
                url: url+"admin/user/merge",
                data:$('#form1').serialize(),
                dataType: "json",
                type : "post",
                success: function(data) {
                    if(data > 0){
                        alert("保存成功");
                        location.href = url+'admin/user/list';
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
                phone: {
                    required: true
                }
            },
            messages: {
                userName: {
                    required: "姓名不能为空",
                    maxlength:"请不要超过20个字符"
                },
                phone: {
                    required: "手机号不能为空"
                }
            }
        });
    }
</script>