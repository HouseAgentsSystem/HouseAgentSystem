Ext.define('HouseAgentSystem.view.staff.StaffFaceImgUploadWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.staffFaceImgUploadWindow',
	
	controller: 'staffViewController',
	layout : "form",
	title : '选择头像',
	width : 500,
	bodyPadding: 10,
	plain : true,
	modal : true,
	closeAction : "hide",
	items: [{
		xtype:'form',
		id: 'uploadForm',
		reference: 'uploadForm',
		//enctype : "multipart/form-data",
		items: [{
			xtype: 'filefield',
			name: 'faceImg',
			fieldLabel: 'Photo',
			labelWidth: 40,
			msgTarget: 'side',
			allowBlank: false,
			anchor: '100%',
			buttonText: 'Select Photo...'
		}]
	}],
	buttons: [{
		text: 'Upload',
		handler: 'faceImgUpload',
		}]
});