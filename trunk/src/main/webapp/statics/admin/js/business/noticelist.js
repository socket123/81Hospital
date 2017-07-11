/**
 * Created by LXX on 2017-06-17.
 */
$(document).ready(function(){

    $(".btn_search").on("click",function(){
        var noticeTitle = $("input[name='noticeTitle']").val();
        var noticeType = $("#noticeType").val();
        if(noticeTitle!=""){
            noticeTitle='noticeTitle='+noticeTitle;
        }
        if(noticeType!=""){
            noticeType='noticeType='+noticeType;
        }
        location.href = url+'admin/notice/list?'+noticeTitle+noticeType;
    });

    $(".add").on("click",function(){
        location.href = url+'admin/notice/mergepage';
    });

    $(".edit").on("click",function(){
        var id = $(this).parent().find("input[name='id']").val();
        location.href = url+'admin/notice/mergepage?id='+id;
    });

    $(".btn_trash").on("click",function(){
        if(confirm("确定删除此条记录么？删除后将不能恢复！")){
            var id = $(this).parent().find("input[name='id']").val();
            $.post(url+"admin/notice/del",{id:id},function(data){
                if(data > 0){
                    alert("删除成功！");
                    location.href = url+'admin/notice/list';
                }else{
                    alert("操作失败！");
                    return;
                }
            });
        }
    });
});
