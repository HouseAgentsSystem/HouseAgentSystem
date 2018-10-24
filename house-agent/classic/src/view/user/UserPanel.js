Ext.define('HouseAgentSystem.view.user.UserPanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'userPanel',

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
            title: 'UserGrid Results',
            scrollable: false,
            bind:'{userLists}',
            selModel: {type: 'checkboxmodel',checkOnly: true},
            tbar: [{
                    xtype: 'combobox',
                    reference:'searchFieldName',
                    hideLabel: true,
                    store:Ext.create("Ext.data.Store", {
                        fields: ["name", "value"],
                        data: [
                            { name: '客户姓名', value: 'realname' }
                        ]
                }),
                displayField: 'name',//展示的字段
                valueField:'value',//获取值的字段
                value:'realname',//设置默认值
                editable: false,//不可编辑
                queryMode: 'local',
                triggerAction: 'all',
                emptyText: 'Select a state...',
                width: 135
            }, '-',{
                xtype:'textfield',
                reference:'searchFieldValue',
                name:'staffPanelSearchField'
            }, '-',{
                text: 'Search',
                iconCls: 'fa fa-search',
                handler: 'quickSearch'
            }],
            columns: [
                {xtype: 'gridcolumn',width: 40,dataIndex: 'id',text: 'id',hidden:true},
                {xtype:'gridcolumn',width: 75,cls:'content-column',dataIndex:'faceImage',text:'FaceImg',
                    renderer: function(value) {
                        return "<img src='/Customer/upload/user/"+value+"' alt='Profile Pic' height='40px' width='40px'>";
                    }},
                {xtype:'gridcolumn',cls:'content-column',width: 150,dataIndex:'realname',text:'姓名',flex: 1},
                {xtype:'gridcolumn',cls:'content-column',dataIndex:'phoneNumber',text:'电话号码',flex: 1},
								{xtype:'datecolumn',cls:'content-column',dataIndex:'createTime',text:'创建时间',flex: 1},
								{xtype:'gridcolumn',cls:'content-column',dataIndex:'status',text:'状态',flex: 1,
										renderer: function(value) {
											if(value == 1)
												return '<span style="color:green;">正常</span>';
											else if(value == 0)
												return '<span style="color:red;">已禁用</span>';
										}
								},
                {xtype: 'actioncolumn',cls: 'content-column', width: 100,text: '操作',tooltip: 'edit ',
                    items: [
												{xtype: 'button',iconCls: 'x-fa fa-ban'    ,handler: 'onDisableButton'}
                    ]
                }
            ],
            dockedItems: [{
                xtype: 'pagingtoolbar',
                dock: 'bottom',
                displayInfo: true,
                bind: '{userLists}'
            }]
        }]
});
