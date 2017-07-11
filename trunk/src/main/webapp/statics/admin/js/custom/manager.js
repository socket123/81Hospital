function add(type){
	location.href = url+'admin/'+type+'/mergepage';
}

function edit(type,id){
	location.href = url+'admin/'+type+'/mergepage?id='+id;
}


/***
 * 修改
 * @param type
 * @param id
 * @param urls
 */
function editUrls(type,id,urls){
    location.href = url+'admin/'+type+'/'+urls+'?id='+id;
}

/**
 *增加
 * @param type
 */
function addUrls(type,urls){
    location.href = url+'admin/'+type+'/'+urls+'';
}

/**
 * 删除
 * @param type
 * @param id
 */
function delUrls(type,id,urls,lists){
    if(confirm("确定删除此条记录么？删除后将不能恢复！")){
        $.post(url+"admin/"+type+"/"+urls+"",{id:id},function(data){
            // alert(data);
            if(data > 0){
                alert("删除成功！");
                location.href = url+'admin/'+type+'/'+lists+'';
            }else{
                alert("操作失败！");
                return;
            }
        });
    }
}

function searchForm(type){
    // alert($("#searchForm").serialize());
    // console.log($("input[name='departmentName']").val());
    // var param  = [];
    // param=
	location.href = encodeURI(url+'admin/'+type+'/list?'+$("#searchForm").serialize());
}

$(function () {
    biaoge_queding()
});

function biaoge_queding() {
    if($(".status_biao").val() == 1){
        hiede()
    }else if($(".status_biao").val() == 2){
        shows()
	}
}

function hiede() {
	$(".disnoe").show(100);
    $("#main").hide(100);
    $(".status_biao").val(1);
}



function shows() {
    $(".disnoe").hide(100);
    $("#main").show(100);
    $(".status_biao").val(2);
}

// 导出
function excels(type,parem) {
	var html=[];
	$(".head1").each(function () {
		var thisz = $(this);

        html.push(""+thisz.text()+"");

    })
    $.post(url+"admin/"+type+"/List_Excel",{pames:parem,htmls:html.join(',')},function(data){

			console.log(data);
            location.href = url+''+data+'';

});
}

function searchFormListDepartment(type,urls) {

    location.href =encodeURI( url + 'admin/' + type + '/'+urls+'?' + $("#searchForm").serialize());
}
/**
 *  下拉框
 * @param type
 * @param urls
 */
function gradeChange(type,urls) {
    // alert($("#searchForm").serialize());
    // console.log($("input[name='departmentName']").val());
    // var param  = [];
    // param=
    searchFormListDepartment(type,urls)
}


function del(type,id){
	if(confirm("确定删除此条记录么？删除后将不能恢复！")){
		$.post(url+"admin/"+type+"/del",{id:id},function(data){
			// alert(data);
			if(data > 0){
				alert("删除成功！");
				location.href = url+'admin/'+type+'/list';
			}else{
				alert("操作失败！");
				return;
			}
		});
	}
}

function resetP(type,param,urls){
	location.href = url+'admin/'+type+'/list?'+param;
}

function ListPolice(type,param,urls){
	location.href = url+'admin/'+type+'/ListPolice?'+param;
}

function ListDepartment(type,param,urls){
	location.href = url+'admin/'+type+'/'+urls+'?'+param;
}

function reset(type,param){
	location.href = url+'admin/'+type+'/list';
}

