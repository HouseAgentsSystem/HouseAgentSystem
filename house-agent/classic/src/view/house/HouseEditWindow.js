Ext.define('HouseAgentSystem.view.house.HouseEditWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.houseEditWindow',

	controller: 'houseController',
	autoShow: true,
	modal: true,
	layout: 'fit',
	width: 500,
	height: 400,
	closable: true,
	title: '编辑房源',
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
            name: 'title',
            fieldLabel: '标题'
        },{
            name: 'price',
            fieldLabel: '价格'
        },{
            name: 'area',
            fieldLabel: '面积'
        },{
            name: 'address',
            fieldLabel: '详细地址'
        },{
            name: 'orientation',
            fieldLabel: '朝向'
        },{
            name: 'decorateLevel',
            fieldLabel: '装修等级'
        },{
            name: 'floor',
            fieldLabel: '楼层'
        },{
            name: 'room',
            fieldLabel: '房'
        },{
            name: 'hall',
            fieldLabel: '厅'
        }],
		buttons: [{
			text: 'Save',
			handler: 'submitEditForm'
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