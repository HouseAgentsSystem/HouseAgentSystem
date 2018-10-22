Ext.define('HouseAgentSystem.view.trede.TradeDetailWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.tradeDetailWindow',

	controller: 'tradeViewController',
	autoShow: true,
	modal: true,
	layout: 'fit',
	width: 500,
	height: 580,
	closable: true,
	title: '二手房交易详情',
	items: [{
		id: 'editForm',
		xtype: 'form',
		layout: {type: 'vbox', align: 'stretch'},
		bodyPadding: 10,
		scrollable: true,
		defaults: {
			labelWidth: 100,
			labelSeparator: '',
			readOnly: true
		},
		defaultType: 'textfield',
		items: [
		{
            name: 'id',
            fieldLabel: 'ID',
            hidden: true
        },
        {
            name: 'houseSummary',
            fieldLabel: '二手房简介'
        },
        {
            name: 'houseAddress',
            fieldLabel: '地址'
        },
        {
            name: 'ownerName',
            fieldLabel: '卖方姓名'
        },
        {
            name: 'ownerPhoneNumber',
            fieldLabel: '联系方式'
        },
        {
            name: 'buyerName',
            fieldLabel: '买方姓名'
        },
        {
            name: 'buyerPhoneNumber',
            fieldLabel: '联系方式'
        },{
        	xtype: 'datefield',
            name: 'saleDate',
            fieldLabel: '交易日期',
            format: 'Y/m/d'
        },
        {
            name: 'actualPrice',
            fieldLabel: '实际售价'
        },
        {
            name: 'agencyFees',
            fieldLabel: '中介费'
        },
        {
            name: 'staffName',
            fieldLabel: '经办人'
        },
        {
            name: 'storeName',
            fieldLabel: '所属门店'
        }],
		buttons: [{
			text: '关闭',
	        handler: function(btn) {
	            btn.up('window').close();
	        }
		}]
	}]
});