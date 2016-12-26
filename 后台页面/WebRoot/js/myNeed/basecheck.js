$(document).ready(function() {
	
	var Spage = $('#basecheck').DataTable(
			{
				"aLengthMenu" : [ 2, 10, 20, 30 ], // ��ָ̬����ҳ��ÿҳ��ʾ�ļ�¼����
				"lengthChange" : true, // �Ƿ����øı�ÿҳ��ʾ���������ݵĿؼ�
				"bSort" : true,
				"ordering":true,
				"serverSide" : true,
				"iDisplayLength": 2,				
				"dom" : 'tipr<"bottom"l>',
				"ajax" : {
					"url" : "",
					"type" : "POST"
				},
				"aoColumns" : [ 
				{
					"mData" : "id",					
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "2%",
				}, { // aoColumns������ʱ������������ָ���У������г������С�
					"mData" : "name",
					"orderable" : true, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%"
				}, { // aoColumns������ʱ������������ָ���У������г������С�
					"mData" : "type",
					"orderable" : true, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%"
				},{
					"mData" : "applydp",
					"orderable" : true, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "landarea",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%"
				}, 
				
				{
					"mData" : "constructionarea",					
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%"
				},
				{
					"mData" : "land_address",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"visible":false,
					"sWidth" : "6%"
				},
				{
					"mData" : "username",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%"
				}, {
					"mData" : "phone",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "major",
					"visible":false,
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "undertake",
					"visible":false,
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				},
				{
					"mData" : "material_path",
					"visible":false,
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				},
				 {
					"mData" : "userid",
					"visible":false,
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "id",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",
					"render":function(data,type, row){
						return data="<button type='button' class='btn btn-warning btn-xs' value='"+data
									+ "' id='scanDetail'>�鿴</button>";
					}

				}

				],
				"columnDefs" : [ {
					"orderable" : false, // ��������
					"targets" : [ 0 ], // ָ������
					"data" : "id",
					"render" : function(data, type, row) {
						
						return '<input type="checkbox" value="'
								+ data
								+ '" name="idname" />';
					}
				} ],
				"language" : {
					"lengthMenu" : "ÿҳ _MENU_ ����¼",
					"zeroRecords" : "û���ҵ���¼",
					"info" : "�� _PAGE_ ҳ ( �ܹ� _PAGES_ ҳ )",
					"infoEmpty" : "�޼�¼",
					"infoFiltered" : "(�� _MAX_ ����¼����)",
					"sSearch" : "ģ����ѯ��",
					"oPaginate" : {
						"sFirst" : "��ҳ",
						"sPrevious" : " ��һҳ ",
						"sNext" : " ��һҳ ",
						"sLast" : " βҳ "
					}
				}
			});
	
	//��ȡ�걨����
	 $.ajax({
			url : '',
			type : 'post',
			dataType : 'json',			
			success : function(data) {						
          
				for ( var i=0;i<data.length;i++) {
					$("#deptS").after(
							"<option value="+data[i].aid+">"
									+ data[i].dept + "</option>");
					
				}
				}
			
		});	
	
});

////ȫѡ��ѡ
$("#ck1").on("click",function() {
	
			if ($(this).prop("checked") == true) {
				$("#basecheck input[name='idname']").prop(
						"checked", true);
			} else {
				$("#basecheck input[name='idname']").prop(
						"checked", false);
			}
		});
$(".icon-filter").on("click", function() {	
	$('#hide_ul').toggle();
});


//ͬ������
// //////////״ֵ̬1�� 2�� 3�� 4�� ��������������
$('#confirmDate').click(function() {
	
					var date=$("#valideDate").val();					
					var i=0;
					var recordstr='(';
					var infostr="[";
					$("input[type='checkbox'][name='idname']:checked").each(function() {					
						
						userid = $(this).closest('tr').find('td:eq(13)').text();
						if(i!=0){
							recordstr=recordstr+","+$(this).val();
							infostr=infostr+',{userid:"'+userid+'",basename:"'+ $(this).closest('tr').find('td:eq(13)').text()+'"}';
							
						}else{
							recordstr=recordstr+$(this).val();
							infostr=infostr+'{userid:"'+userid+'",basename:"'+ $(this).closest('tr').find('td:eq(13)').text()+'"}';
						}					
										
							i++;
						});
				
                    recordstr=recordstr+')';
                    infostr=infostr+']';
                    
                    $.ajax({
						url : '',
						type : 'post',
						dataType : 'json',
						data : {
							"resordstr" : resordstr,
							"infostr" : info_str,
							"date":date
						},
						success : function(msg) {						
							$("#valideDate").val("10");
							$("#applyConfirm").modal('hide');
							Spage.draw(false);
							}
						
					});
				});

//�ܾ�����
////////////״ֵ̬1�� 2�� 3�� 4�� ��������������
$('#certain').click(function() {				
					var i=0;
					var recordstr='(';
					var infostr="[";
					var reason=$("#reason").val();
					$("input[type='checkbox'][name='idname']:checked").each(function() {					
						
						userid = $(this).closest('tr').find('td:eq(13)').text();
						if(i!=0){
							recordstr=recordstr+","+$(this).val();
							infostr=infostr+',{userid:"'+userid+'",basename:"'+ $(this).closest('tr').find('td:eq(13)').text()+'"}';
							
						}else{
							recordstr=recordstr+$(this).val();
							infostr=infostr+'{userid:"'+userid+'",basename:"'+ $(this).closest('tr').find('td:eq(13)').text()+'"}';
						}					
										
							i++;
						});
				
                 recordstr=recordstr+')';
                 infostr=infostr+']';                
                 $.ajax({
						url : '',
						type : 'post',
						dataType : 'json',
						data : {
							"resordstr" : resordstr,
							"infostr" : info_str,
							"reason":reason,
						},
						success : function(msg) {						
							$("#reason").val("");
							$("#reasonConfirm").modal('hide');
							Spage.draw(true);
							}
						
					});

             	            	
});

$(document).on("click", "#close", function() {	
	$("#reason").val("");
	
});

$(document).on("click", "#close1", function() {	
	$("#valideDate").val("10");
	
});

$(document).on("click", "#refuse", function() {	
	
	var chk_value =[];
	$('input[name="idname"]:checked').each(function(){
	chk_value.push($(this).val());
	});	
	
	 if(chk_value.length==0)
	 {
		 bootbox.alert({
				message : "������ѡ��һ��",
				size : 'small'
			});
		 return;
	  }
	$("#reasonConfirm").modal('show');
	
});

$(document).on("click", "#confirm", function() {	
	
	var chk_value =[];
	$('input[name="idname"]:checked').each(function(){
	chk_value.push($(this).val());
	});	
	
	 if(chk_value.length==0)
	 {
		 bootbox.alert({
				message : "������ѡ��һ��",
				size : 'small'
			});
		 return;
	  }
	$("#applyConfirm").modal('show');
	
});

$(document).on("click", "#scanDetail", function() {	
	
	var basename=$(this).closest('tr').find('td:eq(1)').text();
	var basetype=$(this).closest('tr').find('td:eq(1)').text();
	var dept=$(this).closest('tr').find('td:eq(3)').text();
	var landrarea=$(this).closest('tr').find('td:eq(4)').text();
	var buildingarea=$(this).closest('tr').find('td:eq(5)').text();
	var undertake=$(this).closest('tr').find('td:eq(10)').text();
	var username=$(this).closest('tr').find('td:eq(7)').text();
	var userphone=$(this).closest('tr').find('td:eq(8)').text();
	var major=$(this).closest('tr').find('td:eq(9)').text();
	var address=$(this).closest('tr').find('td:eq(8)').text();
	var resource=$(this).closest('tr').find('td:eq(11)').text();
	
	$("#basename").val(basename);
	$("#basetype").val(basetype);
	$("#dept0").val(dept);
	$("#landarea").val(landarea);
	$("#buildingarea").val(buildingarea);
	$("#undertakeCount").val(undertake);
	$("#username").val(username);
	$("#userphone").val(userphone);
	$("#major_oriented").val(major);
	$("#linkAddress").val(address);
	$("#resource").prop("href",resource);
	
	$("#scan").modal('show');
	
});

$("#submitS").click(function() {	
	
	var dept=$("deptSh").val();
	
	
	$('#basecheck').DataTable(
			{
				"aLengthMenu" : [ 5, 10, 20, 30 ], // ��ָ̬����ҳ��ÿҳ��ʾ�ļ�¼����
				"lengthChange" : true, // �Ƿ����øı�ÿҳ��ʾ���������ݵĿؼ�
				"bSort" : true,
				"ordering":true,
				"serverSide" : true,
				"bDestroy":true,
				"iDisplayLength": 5,				
				"dom" : 'tipr<"bottom"l>',
				"ajax" : {
					"url" : "",
					"type" : "POST",
					"data":{"dept":dept}
				},
				"aoColumns" : [ 
				{
					"mData" : "id",					
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "2%",
				}, { // aoColumns������ʱ������������ָ���У������г������С�
					"mData" : "name",
					"orderable" : true, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%"
				}, { // aoColumns������ʱ������������ָ���У������г������С�
					"mData" : "type",
					"orderable" : true, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%"
				},{
					"mData" : "applydp",
					"orderable" : true, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "landarea",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%"
				}, 
				
				{
					"mData" : "constructionarea",					
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%"
				},
				{
					"mData" : "land_address",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"visible":false,
					"sWidth" : "6%"
				},
				{
					"mData" : "username",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%"
				}, {
					"mData" : "phone",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "major",
					"visible":false,
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "undertake",
					"visible":false,
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				},
				{
					"mData" : "material_path",
					"visible":false,
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				},
				 {
					"mData" : "userid",
					"visible":false,
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "id",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",
					"render":function(data,type, row){
						return data="<button type='button' class='btn btn-warning btn-xs' value='"+ la_id
									+ "' id='scanDetail'>�鿴</button>";
					}

				}

				],
				"columnDefs" : [ {
					"orderable" : false, // ��������
					"targets" : [ 0 ], // ָ������
					"data" : "id",
					"render" : function(data, type, row) {
						
						return '<input type="checkbox" value="'
								+ data
								+ '" name="idname" />';
					}
				} ],
				"language" : {
					"lengthMenu" : "ÿҳ _MENU_ ����¼",
					"zeroRecords" : "û���ҵ���¼",
					"info" : "�� _PAGE_ ҳ ( �ܹ� _PAGES_ ҳ )",
					"infoEmpty" : "�޼�¼",
					"infoFiltered" : "(�� _MAX_ ����¼����)",
					"sSearch" : "ģ����ѯ��",
					"oPaginate" : {
						"sFirst" : "��ҳ",
						"sPrevious" : " ��һҳ ",
						"sNext" : " ��һҳ ",
						"sLast" : " βҳ "
					}
				}
			});
	
});

