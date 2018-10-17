Ext.define('HouseAgentSystem.view.house.HouseEditWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.houseEditWindow',

	controller: 'houseController',
	autoShow: true,
	modal: true,
	layout: 'fit',
	width: 500,
	height: 200,
	closable: true,
	title: '编辑用户信息',
	items: [{
		id: 'editForm',
		xtype: 'form',
		layout: {type: 'vbox', align: 'stretch'},
		bodyPadding: 10,
		scrollable: true,
		defaults: {
			labelWidth: 100,
			labelSeparator: '',
		},
		defaultType: 'textfield',
		items: [
		{
            name: 'id',
            fieldLabel: 'ID',
            hidden: true
        },
        {
            name: 'houseNumber',
            fieldLabel: 'House Number'
        },{
        	xtype: 'datefield',
            name: 'createTime',
            //xtype: 'datepicker',
            //width: 200,
            //defaultValue: '1999.9.9',
            fieldLabel: 'Create Time',
            format: 'Y/m/d H:i:s'
        }],
		buttons: [{
			text: 'Save',
			handler: 'submitEditForm'
			// function() {
			// 	if (this.up('form').getForm().isValid()) {
			// 	// this would submit the form to the configured url
			// 	// this.up('form').getForm().submit(); / AJAX
			// 		this.up('form').getForm().reset();
			// 		this.up('window').hide();
			// 		Ext.MessageBox.alert('Thank you!', 'submit Success!');
			// 	}
			// }
		},
		{
			text: 'Cancel',
			handler: function() {
				this.up('form').getForm().reset();
				this.up('window').hide();
			}
		}]
	}]
	// ,
	// afterRender: function() {
	// 	var me = this;
	// 	me.callParent(arguments);
	// 	me.syncSize();
	// 	Ext.on(me.resizeListeners = {
	// 		resize: me.onViewportResize,
	// 		scope: me,
	// 		buffer: 50
	// 	});
	// },
	// doDestroy: function() {
	// 	Ext.un(this.resizeListeners);
	// 	this.callParent();
	// },
	// onViewportResize: function() {
	// 	this.syncSize();
	// },
	// syncSize: function(){
	// 	var width = Ext.Element.getViewportWidth(),
	// 	height = Ext.Element.getViewportHeight();
	// 	this.setSize(Math.floor(width*0.6), Math.floor(height*0.6));
	// 	this.setXY([Math.floor(width*0.05), Math.floor(height*0.05)]);
	// }
});