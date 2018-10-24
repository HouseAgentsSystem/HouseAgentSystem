Ext.define('HouseAgentSystem.view.store.StoreAddWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.storeAddWindow',
    height: 450,
    minHeight: 450,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: 'Add Store Window',
    closable: true,
    constrain: true,
    
    defaultFocus: 'textfield',
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
            name:'id',
            hidden: true,
            readOnly: true
        }, {
            xtype: 'textfield',
            fieldLabel: '店名',
            name:'storeName'
        }, {
            xtype: 'textfield',
            fieldLabel: '地址',
            name:'address'
        },{},{
            xtype: 'tbtext',
            text: '门店经理信息'
        },{
            xtype: 'textfield',
            fieldLabel: '员工账号',
            name:'userName'
        },{
            xtype: 'textfield',
            fieldLabel: '员工姓名',
            name:'realName'
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
            fieldLabel: '电话号码',
            name:'phoneNumber'
        }]
    }],
	buttons: ['->',{
        xtype: 'button',
        text: 'Submit',
        handler: 'submitAddForm'
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    },'->']
});
