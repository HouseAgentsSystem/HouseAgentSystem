Ext.define('HouseAgentSystem.view.house.HouseAddMoreWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.houseAddMoreWindow',
	requires: ['Ext.form.field.ComboBox'],
	height: 200,
	minHeight: 200,
	minWidth: 300,
	width: 300,
	scrollable: true,
	title: '批量导入',
	closable: true,
	modal: true,
	layout: 'fit',
	items: [{
		type: 'panel',
		items: [{
			bodyPadding: '10 10 0',
			reference: 'firstForm',
			id: 'excelForm',
			xtype: 'form',
			fileUpload: true,
			method: 'POST',
			enctype: 'multipart/form-data',
			//hideLabel: true,
			defaults: {
				anchor: '100%',
				allowBlank: false,
				msgTarget: 'side',
				labelWidth: 0
			},

			items: [{
				xtype: 'filefield',
				emptyText: 'Select an excel',
				name: 'file',
				hideLabel: true,
				buttonText: 'excel导入',
				reference: 'excelFile',
				buttonOnly: true,
				listeners: {
					change: 'excelUpload'
				}
			}]

		}, {
			bodyPadding: '10 10 0',
			reference: 'secondForm',
			id: 'rarForm',
			xtype: 'form',
			fileUpload: true,
			method: 'POST',
			enctype: 'multipart/form-data',
			//hideLabel: true,
			defaults: {
				anchor: '100%',
				allowBlank: false,
				msgTarget: 'side',
				labelWidth: 0
			},

			items: [{
				xtype: 'filefield',
				emptyText: 'Select an rar',
				name: 'file',
				hideLabel: true,
				buttonText: 'rar导入',
				buttonOnly: true,

				listeners: {
					change: 'rarUpload'
				}
			}]

		}]
	}],
	buttons: []
});