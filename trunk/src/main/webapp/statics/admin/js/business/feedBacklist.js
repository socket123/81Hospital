/**
 * Created by LXX on 2017-06-17.
 */
$(document).ready(function(){

    $(".btn_search").on("click",function(){
        var userId = $("input[name='userId']").val();
        if(userId!=""){
            userId='userId='+userId;
        }
        location.href = url+'admin/feedBack/list?'+userId;
    });

    $(".edit").on("click",function(){
        var id = $(this).parent().find("input[name='id']").val();
        location.href = url+'admin/feedBack/mergepage?id='+id;
    });

});
