function link(url){
	$.ajax({
        url : url,
        type : "get",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data : {
        },
        cache:false,
        traditional : true,
        beforeSend : function(a, b, c) {
        	layout_open();
        },
        success : function(data, statusText, jqXHR) {
        	setRightData(data);
        },
        error : function(a, b, c) {
        	layer.alert("系统异常，请联系管理员！");
        },complete: function(msg) {
        	layer.close(default_load);
        }
    });
}

function linkByDom(dom,url){
	$.ajax({
        url : url,
        type : "get",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data : {
        },
        traditional : true,
        beforeSend : function(a, b, c) {
        	
        },
        cache:false,
        success : function(data, statusText, jqXHR) {
        	$('#'+dom).html(data);
        },
        error : function(a, b, c) {
        	layer.alert("系统异常，请联系管理员！");
        }
    });
}

var default_load;
function layout_open(){
	default_load = layer.msg('正在加载...', {icon: 1,time: 6000,
		shade: [0.4, '#E0DEDC']}); 
}

function setRightData(data){
	$("#rightContent").html(data);
}

//获取选择的多个选框ID
function getSelectedIds() {
	var ids = "";
	$("input[name='checkbox']").each(function() {
		if (this.checked == true) {
			var val=$(this).val();
			if(val.indexOf("_")!=-1){
				var id=val.split("_");
				if(id[1]=="N"){
					ids+=id[0];
					ids+=",";
				}	
			}else{
				ids+=val;
				ids+=",";
			}
		}
	});
	return ids;
}

//获取选择的单个选框ID
function getSelectedId() {
	var ids = getSelectedIds();
	var id = ids.split(",");
	if (id.length > 2) {
		alert("只可选择一条记录进行操作！");
		return "";
	}
	if (id.length < 2) {
		alert("请选择一条记录进行操作！");
		return "";
	}
	return id[0];
}