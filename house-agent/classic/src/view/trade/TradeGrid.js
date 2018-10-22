/**
*订单模块子视图
*/
Ext.define('HouseAgentSystem.view.trade.TradeGrid', {
      extend: 'Ext.grid.Panel',
	id:'tradeGrid',
    xtype: 'tradeGrid',
    
    
	title:'<b>订单列表</b>',
	bind:'{tradeLists}',
	//selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: 'ID',sortable:true ,dataIndex:'id',hidden:true},
        {text: '卖方' ,sortable:true ,dataIndex:'ownerName', width:120, flex:1},
        {text: '买方' ,sortable:true ,dataIndex:'buyerName' , width:120, flex:1},
        {text: '经办人' ,sortable:true ,dataIndex:'staffName' , width:120, flex:1},
        {text: '中介费', sortable:true ,dataIndex:'agencyFees', flex:1},
		{text: '实际售价',sortable:true ,dataIndex:'actualPrice', width:125, flex:1},
		{text: '交易时间'  ,sortable:true ,dataIndex:'saleDate', width:165
			,renderer: Ext.util.Format.dateRenderer('Y/m/d'), flex:1}
		
	],	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{tradeLists}',
		displayInfo: true,
		displayMsg: '第{0}-{1}条  共{2}条',
		emptyMsg: "没有任何记录"
	}),
	listeners: {
		select: 'tradeSelect'
	}
});
