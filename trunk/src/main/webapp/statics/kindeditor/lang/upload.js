
var 	editor ;
	 	editor = KindEditor.create('textarea[name="content"]', {
		resizeType : 0,
		uploadJson : 'kindeditor/jsp/upload_json.jsp',
		fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		items : [ 'undo', 'redo', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist','link', 'unlink','fullscreen','|'],
		afterChange: function (e) { this.sync() } 
		
	});
	KindEditor('select[name=model]').change(function() {

			if (this.value == 'default') {
				editor.remove();
				editor = null;
				editor = KindEditor.create('textarea[name="content"]', {
				resizeType : 0,
				uploadJson : 'kindeditor/jsp/upload_json.jsp',
				fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
				allowPreviewEmoticons : false,
				allowImageUpload : true,
				items:[  '|', 'undo', 'redo','preview', 'print', 'copy', 'paste',    'plainpaste', 'wordpaste', 
					'|', 'justifyleft', 'justifycenter', 'justifyright',
       	 	'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
       	 	'superscript','emoticons', 'baidumap',  'fullscreen', '|','/',
      	 	'|', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
        	'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
			 'table', 'hr',  'link', 'unlink', '|'
				],
				afterChange: function (e) { this.sync() } 
				});

			}else{
				editor.remove();
				editor = null;				
	 			editor = KindEditor.create('textarea[name="content"]', {
				resizeType : 0,
				uploadJson : 'kindeditor/jsp/upload_json.jsp',
				fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
				allowPreviewEmoticons : false,
				allowImageUpload : true,
				items : [ 'undo', 'redo', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist','link', 'unlink','fullscreen','|'],
				afterChange: function (e) { this.sync() } 
		
				});
			} 
			
		});
