<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/statics.jsp" %>
<style>
    .stdform label span{color: red;margin-right: 5px;}
</style>
<div class="pageheader notab">
    <h1 class="pagetitle">反馈管理--查看反馈</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
    <div class="subcontent">
        <form id="form1" class="stdform">
            <p>
                <label>反馈人</label>
                <span class="field">
                 	<input type="text" name="userName" class="smallinput" value="${record.userName }" disabled="disabled" readonly="readonly"/>
                 	<input type="hidden" name="id" value="${record.id }"/>
                 </span>
            </p>
            <p>
                <label>手机号</label>
                <span class="field">
                 	<input type="text" name="phone" class="smallinput" value="${record.phone }" disabled="disabled" readonly="readonly"/>
                 </span>
            </p>
            <p>
                <label>微信号</label>
                <span class="field">
                 	<input type="text" name="wxNumber" class="smallinput" value="${record.wxNumber }" disabled="disabled" readonly="readonly"/>
                 </span>
            </p>
            <p>
                <label>QQ号</label>
                <span class="field">
                 	<input type="text" name="qqNumber" class="smallinput" value="${record.qqNumber }" disabled="disabled" readonly="readonly"/>
                 </span>
            </p>
            <p>
                <label>反馈内容</label>
                <span class="field">
                    <textarea name="qqNumber" disabled="disabled" readonly="readonly">${record.content }</textarea>
                 </span>
            </p>

            <p class="stdformbutton">
                <button type="button" class="back radius2">返回</button>
            </p>
        </form>
    </div><!--subcontent-->
</div>
<script>
    $(document).ready(function(){});
    $(".back").on("click",function(){
        location.href = url+'admin/feedBack/list';
    });
</script>