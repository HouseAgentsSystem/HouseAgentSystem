Ext.define('HouseAgentSystem.view.house.HouseSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.houseSearchWindow',
    height: 300,
    minHeight: 300,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: 'Search More Window',
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
        },{
            xtype: 'textfield',
            fieldLabel: 'House Number',
            name:'houseNumber'
        }, {
                xtype: 'datefield',
                format: 'Y/m/d H:i:s',
                fieldLabel: 'From',
                name: 'createTimeStart'
            
            }, {
                xtype: 'datefield',
                format: 'Y/m/d H:i:s',
                fieldLabel: 'To',
                name: 'createTimeEnd'
                
         }]
    }],
    buttons:  [{
        xtype: 'button',
        text: 'Submit',
        handler: 'submitSearchForm'//预留提交事件
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    }]
});