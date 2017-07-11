<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/statics.jsp" %>
<script type="text/javascript" src="${url }statics/admin/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/custom/ajaxupload.js"></script>
<script type="text/javascript" src="${url }statics/admin/js/sysManager/mallconfig_list.js"></script>
<style>
.stdform label span{color: red;margin-right: 5px;}
</style>
<div class="pageheader notab">
    <h1 class="pagetitle">新增/修改</h1>
    <span class="pagedesc"></span>
</div><!--pageheader-->
<div id="contentwrapper" class="contentwrapper">
	<div class="subcontent">
         <form id="form1" class="stdform">
         	<input type="hidden" name="typeName" id="typeName" value="${typeName }"/>
         	<input type="hidden" name="id" value="${record.id }"/>
             <p>
             	<label>图片</label>
                 <span class="field">
                 	<c:if test="${empty record.content }">
                 		<img src='${url}statics/admin/images/default.png' id='imgId' style='height: 100px; width: 100px' onclick="imgClick('imgId')"/>
                 	</c:if>
                 	<c:if test="${!empty record.content }">
                 		<img src='${url }file/showpic?pic=${record.content}' style='height: 100px; width: 100px' id='imgId' onclick="imgClick('imgId')"/>
                 		<img src="${url}statics/admin/images/cha.png" style="height: 20px; width: 20px" onclick="imgDel()"/>
                 	</c:if>
                 	<input type="hidden" name="content" id="imgIdTxt" value="${record.content }"/>
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
</div>
