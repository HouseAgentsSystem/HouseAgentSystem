/**
*订单模块子视图
*/
Ext.define('HouseAgentSystem.view.trade.TradeGrid', {
      extend: 'Ext.grid.Panel',
	id:'tradeGrid',
    xtype: 'tradeGrid',
    
    
	title:'<b>订单列表</b>',
	bind:'{tradeLists}',
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: 'ID'			  ,sortable:true ,dataIndex:'id',hidden:true},
        {text: '订单编号' ,sortable:true ,dataIndex:'tradeNumber' ,width:120},
		{text: '创建时间'  ,sortable:true ,dataIndex:'createTime'  ,width:165
			,renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},
		{text: '优先级',sortable:true ,dataIndex:'level'    ,width:125},
		{text: '价格'		  ,sortable:true ,dataIndex:'price' ,flex:1}
	],	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{tradeLists}',
		displayInfo: true,
		displayMsg: '第{0}-{1}条  共{2}条',
		emptyMsg: "没有任何记录",
		items:['-', {
			//xtype:'button',
			//text: 'Add',
			iconCls: 'x-fa fa-plus',
			listeners: {
                //click: 'tradeGridOpenAddWindow'
            }
			//handler: 'tradeGridOpenAddWindow'
		}, {
			iconCls: 'x-fa fa-edit'
			//,handler: 'tradeGridOpenEditWindow'
		}, {
			iconCls: 'x-fa fa-trash'
			//,handler: 'tradeGridDeleteDate'
		}]
	})
});
