	/******************ztree*******************/
	var zTreeObj;
	// zTree 的参数配置
	var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onClick: onClick
		}
	};
	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		if (!check) alert("请选择最小分类...");
		return check;
	}
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#proTypeName");
		cityObj.attr("value", v);
		$("#proType").val(treeNode.id);
	}
	function showMenu() {
		var cityObj = $("#proTypeName");
		var cityOffset = $("#proTypeName").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "proTypeName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	
	$(document).ready(function(){
		zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		$('input:checkbox, input:radio, select.uniformselect, input:file').uniform();
		validateForm();
		$("#stagesNum").val(stagesNum);
	});
	

	$(".back").on("click",function(){
		location.href = '../sysproduct/list';
	});
	
	$("#oneType").on("change",function(){
		var id = $(this).val();
		$.post(url+"admin/protype/bypid",{pid:id},function(data){
			var sid = "";
			$("#twoType").html("");
			var html = "";
			for(var i = 0 ; i < data.length; i++){
				html += "<option value='"+data[i].id+"'>"+data[i].typeName+"</option>"
			}
			if(data.length > 0){
				$("#twoType").removeClass("hide");
				sid = data[0].id;
			}else{
				$("#twoType").addClass("hide");
			}
			$("#twoType").append(html);
			//三级
			threeType(sid);
		});
	});
	
	$("#twoType").on("change",function(){
		var id = $(this).val();
		threeType(id);
	});
	
	function threeType(id){
		$.post(url+"admin/protype/bypid",{pid:id},function(data){
			$("#threeType").html("");
			var html = "";
			for(var i = 0 ; i < data.length; i++){
				html += "<option value='"+data[i].id+"'>"+data[i].typeName+"</option>"
			}
			if(data.length > 0){
				$("#threeType").removeClass("hide");
			}else{
				$("#threeType").addClass("hide");
			}
			$("#threeType").append(html);
		});
	}
	
	$(".submit").on("click",function(){
		if($("#form1").valid()){
			$.ajax({
				url: url+'admin/sysproduct/merge',
				data:$('#form1').serialize(),
				dataType: "json",
				type : "post",
				success: function(data) {
					if(data > 0){
						alert("保存成功");
						location.href = url+'admin/sysproduct/list?state='+state;
					}else if(data == -2){
						alert("您是超级管理员，不能保存产品");return;
					}else{
						alert("保存失败");return;
					}
				}
			});
		}
	});
	///// FORM VALIDATION /////
	

/*验证*/
function validateForm() {
	$("#form1").validate({
		rules: {
			proTypeName:"required",
			proName: {
				required: true,
				maxlength:50
			},
			price: {
				required: true,
				number:true,
				min:0.01
			},
			originalPrice: {
				required: true,
				number:true,
				min:0.01
			},
			memberPrice: {
				required: true,
				number:true,
				min:0.01
			},
			stagesNum: {
				required: true
			},
			descript: {
				maxlength:200
			}
		},
		messages: {
			proTypeName:"请选择产品分类",
			proName: {
				required:"请输入产品名称",
				maxlength:"请不要超过50个字符"
			},
			price: {
				required: "请输入产品价格",
				number:"请输入合法的产品价格",
				min:"请输入合法的产品价格"
			},
			originalPrice: {
				required: "请输入产品原价",
				number:"请输入合法的产品原价",
				min:"请输入合法的产品原价"
			},
			memberPrice: {
				required: "请输入会员价格",
				number:"请输入合法的会员价格",
				min:"请输入合法的会员价格"
			},
			stagesNum: {
				required: "请选择分期期数"
			},
			descript: {
				maxlength:"请不要超过200个字符"
			}
		}
	});
}
	
	
function imgDel(){
	var img = $("#imgIdTxt").val();
	$.post(url+"file/del",{img:img},function(data){
		if(data == "success"){
			$("#imgId").attr("src",url+"statics/admin/images/default.png");
			$("#imgIdTxt").val("");
		}else{
			layer.alert(data);
		}
	});
}
function imgClick(imgId){
	new AjaxUpload(imgId, {
        action: url+"file/upload",
		data : {
			'timestamp' : (new Date()).getTime(),
			'token'     : '',
			'key' : "文件将被覆盖"
		},
		onSubmit : function(file, ext){
			if (ext && /^(jpg|jpeg|gif|png|JPG|JPEG|GIF|PNG)$/.test(ext)){
				/* Setting data */
				var pic = $("#imgIdTxt").val();
				this.setData({
					'img': pic
				});					
				$('#example2 .text').text('正在上传...' + file);	
			} else {			
				alert('错误: 只能上传允许的图片格式，如JPG|PNG|GIF');
				return false;				
			}		
		},
		onComplete : function(file,data){
			if (file == null) {
				alert('你没有上传任何文件或者选择了取消！');
				return;
			}
			var reg = /<pre.+?>(.+)<\/pre>/g;  
			var result = data.match(reg);  
			data = RegExp.$1;
			var flag = eval('(' + data + ')');
			if(flag.code != "success"){
				alert(flag.url);
			}else{
				$("#"+imgId).attr("src",url+"file/showpic?pic="+flag.url);
				$("#imgIdTxt").val(flag.url);
			}
			//$("#P_PIC"+ii).val(msg);
		}		
		});
}