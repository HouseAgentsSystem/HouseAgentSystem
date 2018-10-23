Ext.define('HouseAgentSystem.view.staff.StaffEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.staffEditWindow',
    height: 400,
    minHeight: 300,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: 'staff Edit Window',
    closable: true,
    modal:true,
    layout: 'fit',
    items: [{
        xtype: 'form',
        layout: 'form',
        padding: '10px',
        ariaLabel: 'Enter your name',
        items: [{
            xtype: 'textfield',
            fieldLabel: 'id',
            name:'id',  //最重要的属性
            hidden: true,
            readOnly: true
        }, {
            xtype: 'textfield',
            fieldLabel: '姓名',
            name:'realname'
        }, {
            xtype: 'combobox',
            fieldLabel: '性别',
            name:'sex',
			width:200,
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
			queryMode: 'local'	
		}, {
            xtype: 'textfield',
            fieldLabel: '电话',
            name:'phoneNumber'
        }, {
            xtype: 'textfield',
            fieldLabel: '职位',
            name:'position'
        }, {
            xtype: 'combobox',
            fieldLabel: '所属门店',
            name:'store',
			editable: false,
			store: Ext.create("HouseAgentSystem.store.store.StoreGridStroe"),
			displayField: 'storeName',
			valueField:'id',
			queryMode: 'local',
			triggerAction: 'all',
			emptyText: 'Select a store...',
        }]
    }],
    buttons:  [{
        xtype: 'button',
        text: 'Submit',
        handler: 'submitEditForm'//预留提交事件，在ViewController中实现。
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    }]
});