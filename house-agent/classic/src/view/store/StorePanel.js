Ext.define('HouseAgentSystem.view.store.StorePanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'storePanel',
    requires: [
    	//'HouseAgentSystem.view.AdvancedVType',
        'Ext.grid.Panel',
        'Ext.toolbar.Paging',
        'Ext.form.field.ComboBox',
        'Ext.selection.CheckboxModel',
        'Ext.form.field.Date',
        'Ext.grid.column.Date'
    ],
    
    layout: 'fit',
    items: [{
            xtype: 'gridpanel',
            cls: 'user-grid',
            title: 'StoreGrid Results',
            //routeId: 'user',
            bind: '{storeLists}',
            scrollable: false,
            selModel: {type: 'checkboxmodel'},
            //viewConfig: {enableTextSelection: false},
            columns: [
                {xtype: 'gridcolumn',width: 40,dataIndex: 'id',text: '门店Id',hidden:true},
                {xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'storeName',text: '店名',flex: 1},
                {xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'address',text: '地址',flex: 1},
                {xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'managerName',text: '经理',flex: 1},
                {xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'managerPhone',text: '联系电话',flex: 1},
                {xtype: 'actioncolumn',cls: 'content-column', width: 120,text: '操作',tooltip: 'edit ',
                    items: [
                        {xtype: 'button', iconCls: 'x-fa fa-pencil' ,handler: 'openEditWindow'},
                        {xtype: 'button',iconCls: 'x-fa fa-close'	,handler: 'deleteOneRow'},
                        {xtype: 'button',iconCls: 'x-fa fa-ban'	 	,handler: 'onDisableButton'}
                    ]
                }
            ],
            tbar: [{
            	xtype: 'combobox',
	            reference:'regionFieldValue',
	            fieldLabel: '所&nbsp;&nbsp;&nbsp;&nbsp;属&nbsp;&nbsp;&nbsp;&nbsp;地&nbsp;&nbsp;&nbsp;&nbsp;区',
	            labelClsExtra: 'locationLabel',
	            labelAlign: 'right',
	   //          store:Ext.create("Ext.data.Store", {
				//     fields: ["value"],
				//     data: [
				//       	{ value: '全市' },
				// 		{ value: '松山湖' }
				//     ]
				// }),
				//bind: '{regionLists}',
	            //bind: {store: {regionLists}},
	            store: Ext.create("HouseAgentSystem.store.RegionStore"),
	            displayField: 'name',
	            valueField:'value',
	            value:'全市',
	            editable: false,
	            queryMode: 'local',
	            triggerAction: 'all',
	            //emptyText: 'Select a state...',
	            width: 200,
	            listeners:{
					select: 'regionComboboxSelectChange'
				}
            },'-',{
	            xtype: 'combobox',
	            reference:'searchFieldName',
	            hideLabel: true,
	            store:Ext.create("Ext.data.Store", {
				    fields: ["name", "value"],
				    data: [
				      	{ name: '店名', value: 'storeName' },
						{ name: '经理', value: 'managerName' }
				    ]
				}),
	            displayField: 'name',
	            valueField:'value',
	            value:'storeName',
	            editable: false,
	            queryMode: 'local',
	            triggerAction: 'all',
	            //emptyText: 'Select a state...',
	            width: 100
	   //          ,
	   //          listeners:{
				// 	select: 'searchComboboxSelectChange'
				// }
	        }, {
            	xtype:'textfield',
            	width: 150,
            	reference:'searchFieldValue',
            	name:'storePanelSearchField'
		    },{
		        text: '搜索',
		        iconCls: 'fa fa-search',
		        handler: 'quickSearch'
		    }, '->',{
		        text: '添加门店',
		        tooltip: 'Add a new row',
		        iconCls: 'fa fa-plus',
		        handler: 'openAddWindow'	
		    },'-',{
		        text: '返回总表',
		        //tooltip: 'Remove the selected item',
		        iconCls:'fa fa-undo',
		        //itemId: 'storeGridPanelRemove',
		        //disabled: true,
		        handler: 'backToAll'	
		    }],			
            dockedItems: [{
                xtype: 'pagingtoolbar',
                dock: 'bottom',
                //itemId: 'userPaginationToolbar',
                displayInfo: true,
                bind: '{storeLists}'
            }]
   //          ,
   //          listeners: {
			// 	selectionchange: function(selModel, selections){
			// 		this.down('#storeGridPanelRemove').setDisabled(selections.length === 0);
			// 	}
			// }		
        }]
});