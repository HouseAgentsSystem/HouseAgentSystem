Ext.define('HouseAgentSystem.view.house.HouseAddWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.houseAddWindow',
    height: 200,
    minHeight: 200,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: 'House Edit Window',
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
            name:'id',
            hidden: true,
            readOnly: true
        }, {
            xtype: 'textfield',
            fieldLabel: 'House Number',
            name:'houseNumber'
        }, {
            xtype: 'datefield',
            fieldLabel: 'Create Time',
            name:'createTime',
            format: 'Y/m/d H:i:s'
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