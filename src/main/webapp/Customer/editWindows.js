//更新事件	
function submitEditForm(btn){
	var win = btn.up('window');
	var store = Ext.data.StoreManager.lookup('simpsonsStore');
	console.log(store);
	var values = win.down('form').getValues();	//获取form数据
	var record = store.getById(values.id);			//获取id获取store中的数据
	record.set(values);
	store.load();
	win.close();
};
Ext.create('Ext.window.Window', {
	//extend: 'Ext.window.Window',
    id: 'EditWindow',
    height: 600,
    minHeight: 300,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: '重新申请',
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
            fieldLabel: '地址',
            name:'address'
        }, {
            xtype: 'textfield',
            fieldLabel: '租金',
            name:'rent'
        }, {
            xtype: 'textfield',
            fieldLabel: '面积(平方米)',
            name:'area'
        }, {
            xtype: 'textfield',
            fieldLabel: '详细介绍',
            name:'introduce'
        },{
        	xtype: 'textfield',
        	itemId: 'backReason',
            fieldLabel: '审核不通过原因',
            name:'backReason',
            readOnly: true,
//            renderer:function(val){
//            	if(val==null){
//            		this.up("form").getCompentent("backReason").setHidden(true);
//            	}
//            }
            getClass: function(v, meta, rec) {
	            if (rec.get('state')!='REFUSE') {
	                  return 'x-hidden';
	            }
	            return '';
			}
        }]
    }],
	buttons: ['->',{
        xtype: 'button',
        text: 'Submit',
        handler: submitEditForm
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    },'->']
});
