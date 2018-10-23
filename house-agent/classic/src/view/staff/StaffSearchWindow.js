Ext.define('HouseAgentSystem.view.staff.StaffSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.staffSearchWindow',
    height: 250,
    minHeight: 200,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: 'Staff Search Window',
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
            name:'id',      //最重要的属性
            hidden: true,
            readOnly: true
        }, {
            xtype: 'textfield',
            fieldLabel: 'Order Number',
            name:'orderNumber'
        }]
    }],
    buttons:  ['->',{
        xtype: 'button',
        text: 'Submit',
        handler: 'submitSearchForm'//预留提交事件，在ViewController中实现。
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    }]
});