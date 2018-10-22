Ext.define('HouseAgentSystem.view.trade.TradeAddWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.tradeAddWindow',
    height: 300,
    minHeight: 300,
    minWidth: 400,
    width: 500,
    scrollable: true,
    title: 'Add Store Window',
    closable: true,
    constrain: true,
    controller: 'tradeViewController',
    
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
            fieldLabel: '买方用户名',
            name:'userName'
        }, {
            xtype: 'textfield',
            fieldLabel: '实际售价',
            name:'actualPrice'
        },{
            xtype: 'textfield',
            fieldLabel: '中介费',
            name:'agencyFees'
        }]
    }],
	buttons: ['->',{
        xtype: 'button',
        text: 'Submit',
        handler: 'submitTradeAddForm'
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    },'->']
});
