Ext.define('HouseAgentSystem.view.staff.StaffAddWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.staffAddWindow',
    height: 300,
    minHeight: 200,
    //minWidth: 300,
    width: 500,
    scrollable: true,
    title: 'staff Add Window',
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
        },{
            xtype: 'textfield',
            fieldLabel: '员工姓名',
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
            fieldLabel: '电话号码',
            name:'phoneNumber'
        }]
    }],
    buttons:  [{
        xtype: 'button',
        text: 'Submit',
        handler: 'submitAddForm'//预留提交事件
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    }]
});