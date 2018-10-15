Ext.define('HouseAgentSystem.view.store.StoreEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.storeEditWindow',
    height: 300,
    minHeight: 300,
    minWidth: 500,
    width: 500,
    scrollable: true,
    title: 'Edit Store Window',
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
        }, {
            xtype: 'textfield',
            fieldLabel: '经理',
            editable: false,
            name:'managerName'
        }, {
            xtype: 'textfield',
            fieldLabel: '联系电话',
            editable: false,
            name:'managerPhone'
        }]
    }],
   buttons: ['->',{
        xtype: 'button',
        text: 'Submit',
        handler: 'submitEditForm'
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    },'->']
  
});
