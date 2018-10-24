Ext.define('HouseAgentSystem.view.staff.StaffPanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'staffPanel',

    requires: [
        'Ext.grid.Panel',
        'Ext.toolbar.Paging',
        'Ext.form.field.ComboBox',
        'Ext.selection.CheckboxModel',
        'Ext.grid.column.RowNumberer',
        'Ext.grid.column.Date',
        'Ext.selection.CheckboxModel'
    ], 
    //controller: 'searchresults',
   // viewModel: {type: 'orderViewModel'},
    layout: 'fit',
    items: [{
            xtype: 'gridpanel',
            cls: 'user-grid',
            title: 'StaffGrid Results',
            scrollable: false,
            bind:'{staffLists}',
            selModel: {type: 'checkboxmodel',checkOnly: true},
            tbar: [{
                    xtype: 'combobox',
                    reference:'searchFieldName',
                    hideLabel: true,
                    store:Ext.create("Ext.data.Store", {
                        fields: ["name", "value"],
                        data: [
                            { name: '员工姓名', value: 'realName' },
                            { name: '性别', value: 'sex' }
                        ]
                }),
                displayField: 'name',//展示的字段
                valueField:'value',//获取值的字段
                value:'realName',//设置默认值
                editable: false,//不可编辑
                queryMode: 'local',
                triggerAction: 'all',
                emptyText: 'Select a state...',
                width: 135,
                listeners:{
                    select: 'searchComboboxSelectChuang'
                }
            }, '-',{
                xtype:'textfield',
                reference:'searchFieldValue',
                name:'staffPanelSearchField'
            }, '-',{
                xtype:'combobox',
                hideLabel: true,
                hidden:true,
                reference:'searchSexFieldValue',
                name:'staffPanelSearchField',
								store:Ext.create("Ext.data.Store", {
                    fields: ["name", "value"],
                    data: [
                        { name: '男', value: '男' },
                        { name: '女', value: '女' }
                    ]
            	}),
	        	displayField: 'name',//展示的字段
	            valueField:'value',//获取值的字段
	            value:'男',//设置默认值
	            editable: false,//不可编辑
	            queryMode: 'local',
	            triggerAction: 'all'
            }, '-',{
                text: 'Search',
                iconCls: 'fa fa-search',
                handler: 'quickSearch'
            }, '->',{
                text: 'Add',
                tooltip: 'Add a new row',
                iconCls: 'fa fa-plus',  
                handler: 'openAddWindow'    
            },'-',{
                text: 'Removes',
                tooltip: 'Remove the selected item',
                iconCls:'fa fa-trash',
                disabled: true,
                itemId: 'orderGridPanelRemove',
                handler: 'deleteMoreRows'
            }],
            listeners: {
                selectionchange: function(selModel, selections){
                	this.down('#staffGridPanelRemove').setDisabled(selections.length === 0);
                }
            },
            columns: [
                {xtype: 'gridcolumn',width: 40,dataIndex: 'id',text: 'id',hidden:true},
                {xtype:'gridcolumn',width: 75,cls:'content-column',dataIndex:'faceImg',text:'FaceImg',
                    renderer: function(value) {
                        if(value)
							return "<img src='/Customer/upload/staff/"+value+"' alt='Profile Pic' height='40px' width='40px'>";
						else
							return "<img src='/Customer/upload/staff/default.jpg' alt='Profile Pic' height='40px' width='40px'>";
						}
					},
                {xtype:'gridcolumn',cls:'content-column',width: 150,dataIndex:'realName',text:'姓名'},
                {xtype:'gridcolumn',cls:'content-column',width: 100,dataIndex:'sex',text:'性别'},
                {xtype:'gridcolumn',cls:'content-column',dataIndex:'phoneNumber',text:'电话号码',flex: 1},
                {xtype:'gridcolumn',cls:'content-column',dataIndex:'position',text:'职位',flex: 1},
                {xtype:'gridcolumn',cls:'content-column',dataIndex:'name',text:'所属门店',flex: 1},
                {xtype: 'actioncolumn',cls: 'content-column', width: 100,text: '操作',tooltip: 'edit ',
                    items: [
                        {xtype: 'button',iconCls: 'x-fa fa-pencil' ,handler: 'onEditButton'},
                        {xtype: 'button',iconCls: 'x-fa fa-close'  ,handler: 'onDeleteButton'}
                    ]
                }
            ],
            dockedItems: [{
                xtype: 'pagingtoolbar',
                dock: 'bottom',
                displayInfo: true,
                bind: '{staffLists}'
            }]
        }]
});
