Ext.create('Ext.window.Window', {
	//extend: 'Ext.window.Window',
    id: 'EditWindow',
    height: 220,
    minHeight: 100,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: '查看详情',
    closable: true,
    constrain: true,
    closeAction: "hidden",
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
            fieldLabel: '标题',
            name:'title'
        },{
        	xtype: 'textfield',
        	fieldLabel: '图片',
        	name:'images'
        }, {
            xtype: 'textfield',
            fieldLabel: '地址',
            name:'address'
        }, {
            xtype: 'textfield',
            fieldLabel: '租金',
            name:'rent'
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


