Ext.create('Ext.window.Window', {
	//extend: 'Ext.window.Window',
    id: 'DetailWindow22',
    height: 600,
    minHeight: 300,
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
        defaults:{
        	readOnly: true
        },
        ariaLabel: 'Enter your name',
        items: [{
            xtype: 'textfield',
            fieldLabel: 'id',
            name:'id',
            hidden: true,
            readOnly: true
        }, {
            xtype: 'datefield',
            fieldLabel: '申请日期',
            name:'applyTime',
            format: 'Y/m/d H:i:s'
        }, {
            xtype: 'textfield',
            fieldLabel: '标题',
            name:'title'
        }, {
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
            fieldLabel: '地区',
            name:'region'
        }, {
            xtype: 'textfield',
            fieldLabel: '详细介绍',
            name:'introduce'
        }, {
            xtype: 'textfield',
            fieldLabel: '楼层',
            name:'floor'
        }, {
            xtype: 'textfield',
            fieldLabel: '室',
            name:'room'
        }, {
            xtype: 'textfield',
            fieldLabel: '厅',
            name:'hall'
        }, {
        	xtype: 'textfield',
        	itemId: 'backReason',
            fieldLabel: '审核不通过原因',
            name:'backReason',
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
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    },'->']
});