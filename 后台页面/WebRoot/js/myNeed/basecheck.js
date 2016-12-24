$(document).ready(function() {
	
	var Spage = $('#basecheck').DataTable(
			{
				"aLengthMenu" : [ 5, 10, 20, 30 ], // ��ָ̬����ҳ��ÿҳ��ʾ�ļ�¼����
				"lengthChange" : true, // �Ƿ����øı�ÿҳ��ʾ���������ݵĿؼ�
				"bSort" : true,
				"ordering":true,
				"serverSide" : true,
				"iDisplayLength": 5,				
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
					"mData" : "baseType",
					"orderable" : true, // ��������
					"sDefaultContent" : "",
					"sWidth" : "2%"
				}, {
					"mData" : "applyDept",
					"orderable" : true, // ��������
					"sDefaultContent" : "",
					"sWidth" : "6%",

				}, {
					"mData" : "landarea",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "6%"
				}, 
				
				{
					"mData" : "buildingarea",
					"bSortable":true,
					"orderable" : true, // ��������
					"sDefaultContent" : "",
					"sWidth" : "10%"
				},
				{
					"mData" : "address",
					"orderable" : false, // ��������
					"sDefaultContent" : "",
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
					"orderable" : true, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "undertake",
					"visible":false,
					"orderable" : false, // ��������
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "material_path",
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
						return data="<button type='button' class='btn btn-warning btn-xs' data-id='"+ la_id
									+ "' id='frame1_scan'>�鿴</button>";
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