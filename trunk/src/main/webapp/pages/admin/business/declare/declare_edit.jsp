<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.validate.min.js"></script>
<style>
    .stdform label span{color: red;margin-right: 5px;}
</style>
<div class="pageheader notab">
    <h1 class="pagetitle">玩法管理--新增/修改玩法</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
    <div class="subcontent">
        <form id="form1" class="stdform">
            <p>
                <label>类型</label>
                <span class="field">
                    <input type="hidden" name="id" value="${record.id }"/>
                 	<select name="declareType" id="declareType">
                        <option value="">---请选择---</option>
                        <option value="1">南通长牌</option>
                        <option value="2">启东长牌</option>
                        <option value="3">启东麻将</option>
                        <option value="4">斗地主</option>
                 	</select>
                 </span>
            </p>
            <p>
                <label><span>*</span>内容</label>
                <span class="field">
                    <textarea name="declareContent">${record.declareContent }</textarea>
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

    $("#declareType").val("${record.declareType }");

    validateForm();

    $(".back").on("click",function(){
        location.href = url+'admin/declare/list';
    });
    $(".submit").on("click",function(){
        if($("#form1").valid()){
            $.ajax({
                url: url+"admin/declare/merge",
                data:$('#form1').serialize(),
                dataType: "json",
                type : "post",
                success: function(data) {
                    if(data > 0){
                        alert("保存成功");
                        location.href = url+'admin/declare/list';
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
                declareType: {
                    required: true
                }
            },
            messages: {
                declareType: {
                    required: "类型不能为空"
                }
            }
        });
    }
</script>