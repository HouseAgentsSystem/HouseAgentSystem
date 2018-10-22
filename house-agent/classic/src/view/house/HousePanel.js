Ext.define('HouseAgentSystem.view.house.HousePanel', {
	extend: 'Ext.panel.Panel',
	xtype: 'housePanel',

	requires: [
		'Ext.grid.Panel',
		'Ext.toolbar.Paging',
		'Ext.grid.column.Date',
		'Ext.selection.CheckboxModel',
		'Ext.form.field.ComboBox'
	],
	cls: 'shadow',
	activeTab: 0,
	/*margin: 20,*/
	layout: 'fit',
	items: [{
		xtype: 'gridpanel',
		cls: 'user-grid',
		title: '房源管理',
		routeId: 'house',
		bind: '{houselists}',
		scrollable: false,
		// bind: {
		//     store: '{houselists}',
		//     selection: '{theRow}'
		// },
		selModel: {
			type: 'checkboxmodel'
		},
		columns: [{
				xtype: 'gridcolumn',
				width: 40,
				dataIndex: 'id',
				text: 'ID',
				hidden: true,
				flex: 1
			},
			{
				xtype: 'gridcolumn',
				cls: 'content-column',
				dataIndex: 'title',
				text: '标题',
				flex: 1
			},
			{
				xtype: 'gridcolumn',
				cls: 'content-column',
				dataIndex: 'storeName',
				text: '所属门店',
				flex: 1
			},
			{
				xtype: 'gridcolumn',
				cls: 'content-column',
				dataIndex: 'region',
				text: '所属地区',
				flex: 1
			},
			{
				xtype: 'gridcolumn',
				cls: 'content-column',
				dataIndex: 'area',
				text: '面积',
				flex: 1
			},
			{
				xtype: 'gridcolumn',
				cls: 'content-column',
				dataIndex: 'price',
				text: '价格',
				flex: 1
			},
			{
				xtype: 'actioncolumn',
				items: [{
						xtype: 'button',
						iconCls: 'x-fa fa-pencil',
						handler: 'onEditButton'
					},
					{
						xtype: 'button',
						iconCls: 'x-fa fa-close',
						handler: 'onDeleteButton'
					},
					{
						xtype: 'button',
						iconCls: 'x-fa fa-gavel',
						tooltip: '记录交易',
						handler: 'onTradeButton'
					}
				],

				cls: 'content-column',
				width: 120,
				dataIndex: 'bool',
				text: '操作',
				tooltip: 'edit '
			}
		],
		tbar: {
			items: [{
					xtype: 'combobox',
					reference: 'regionFieldValue',
					fieldLabel: '所&nbsp;&nbsp;&nbsp;&nbsp;属&nbsp;&nbsp;&nbsp;&nbsp;地&nbsp;&nbsp;&nbsp;&nbsp;区',
					labelClsExtra: 'locationLabel',
					labelAlign: 'right',
					store: Ext.create("HouseAgentSystem.store.RegionStore"),
					displayField: 'name',
					valueField: 'value',
					value: '全市',
					editable: false,
					queryMode: 'local',
					triggerAction: 'all',
					//emptyText: 'Select a state...',
					width: 200,
					listeners: {
						select: 'regionComboboxSelectChange'
					}
				},{
					xtype: 'combobox',
					reference: 'priceFieldValue',
					fieldLabel: '价&nbsp;&nbsp;&nbsp;&nbsp;格',
					labelClsExtra: 'locationLabel',
					labelAlign: 'right',
					store: Ext.create("HouseAgentSystem.store.PriceStore"),
					displayField: 'name',
					valueField: 'value',
					value: '不限',
					editable: false,
					queryMode: 'local',
					triggerAction: 'all',
					//emptyText: 'Select a state...',
					width: 250,
					listeners: {
						select: 'priceComboboxSelectChange'
					}
				},{
					xtype: 'combobox',
					reference: 'areaFieldValue',
					fieldLabel: '面&nbsp;&nbsp;&nbsp;&nbsp;积',
					labelClsExtra: 'locationLabel',
					labelAlign: 'right',
					store: Ext.create("HouseAgentSystem.store.AreaStore"),
					displayField: 'name',
					valueField: 'value',
					value: '不限',
					editable: false,
					queryMode: 'local',
					triggerAction: 'all',
					//emptyText: 'Select a state...',
					width: 250,
					listeners: {
						select: 'areaComboboxSelectChange'
					}
				},
				'->', {
					text: '批量导入',
					tooltip: 'Add more new row',
					iconCls: 'fa fa-folder-open',
					handler: 'openAddMoreWindow'
				}, {
					text: '增加',
					tooltip: 'Add a new row',
					iconCls: 'fa fa-plus-square',
					handler: 'openAddWindow'
				}, '-', {
					text: '删除所选',
					tooltip: 'Remove the selected item',
					iconCls: 'fa fa-trash',
					disabled: true,
					itemId: 'houseGridPanelRemove',
					handler: 'removeMore'
					// bind: {
					//     disabled: '{!theRow}'
					// }
				}
			]
		},
		dockedItems: [{
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			//itemId: 'userPaginationToolbar',
			displayInfo: true,
			bind: '{houselists}'
		}],
		listeners: {
			selectionchange: function(selMOdel, selections) {
				this.down('#houseGridPanelRemove').setDisabled(selections.length === 0);
			}
		}
	}]
});