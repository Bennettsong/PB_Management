	
$(document).ready(function() {

$("#msglx_list").change(function(){
	if( $(this).val()=='1')
		   $("#part").hidden();
	  else
		   $("#part").show();
	});


		var ue = UE.getEditor('editor');
		function setContent(isAppendTo) {
			UE.getEditor('editor').setContent('', isAppendTo);
			//alert(arr.join("\n"));
		}

		function saveNotifitation() {
		    //��������ťʱ�༭�����Ѿ�ʧȥ�˽��㣬���ֱ����getText������õ����ݣ�����Ҫ��ѡ������Ȼ��ȡ������
		    //var range = UE.getEditor('editor').selection.getRange();
			//range.select();
			var message='';
			message = UE.getEditor('editor').getContent();
		    //alert(message);
		   
			$.ajax({
				type : 'POST',
				dataType : 'text',
				url : 'saveNotification.do',//��̨�޸�
				data:{
					data:message
				},
				async : false,
				cache : false,
				error : function(request) {
					bootbox.alert({
						message : "����ʧ��",
						size : 'small'
					});
				},
				success : function(em) {
					bootbox.alert({
						message : "�����ɹ�",
						size : 'small'
					});
				}
			});
		}
		function addMessage() {
		    //��������ťʱ�༭�����Ѿ�ʧȥ�˽��㣬���ֱ����getText������õ����ݣ�����Ҫ��ѡ������Ȼ��ȡ������
		    //var range = UE.getEditor('editor').selection.getRange();
			//range.select();
			var message='';
			message = UE.getEditor('editor').getContentTxt();
		    //alert(message);
		    var title="����";
		    var userid=1245041;
		    
			$.ajax({
				type : 'POST',
				dataType : 'text',
				url : 'saveMessage.do',//��̨�޸�
				data:{
					content:message,
					title:title,
					userid:userid
				},
				async : false,
				cache : false,
				error : function(request) {
					bootbox.alert({
						message : "����ʧ��",
						size : 'small'
					});
				},
				success : function(em) {
					bootbox.alert({
						message : "�����ɹ�",
						size : 'small'
					});
				}
			});
		}
	});
		