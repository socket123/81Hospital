/**
 * Created by LXX on 2017-06-17.
 */
$(document).ready(function(){

    $(".btn_search").on("click",function(){
        var declareType = $("#declareType").val();
        if(declareType!=""){
            declareType='declareType='+declareType;
        }
        location.href = url+'admin/declare/list?'+declareType;
    });

    $(".add").on("click",function(){
        location.href = url+'admin/declare/mergepage';
    });

    $(".edit").on("click",function(){
        var id = $(this).parent().find("input[name='id']").val();
        location.href = url+'admin/declare/mergepage?id='+id;
    });

    $(".btn_trash").on("click",function(){
        if(confirm("确定删除此条记录么？删除后将不能恢复！")){
            var id = $(this).parent().find("input[name='id']").val();
            $.post(url+"admin/notice/del",{id:id},function(data){
                if(data > 0){
                    alert("删除成功！");
                    location.href = url+'admin/declare/list';
                }else{
                    alert("操作失败！");
                    return;
                }
            });
        }
    });
});
