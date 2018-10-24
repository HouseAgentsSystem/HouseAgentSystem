Ext.define('HouseAgentSystem.view.rentapplyapprove.task.Details', {
    extend: 'Ext.window.Window',
    alias: 'widget.details',
    requires: [
        'Ext.button.Button',
        'Ext.form.RadioGroup',
        'Ext.form.field.*'
    ],
    title:'查看详情',
    id:'rentapplyDetails',
    height: 600,
    minHeight: 450,
    minWidth: 300,
    width: 450,
    scrollable: true,
    // listeners: {
    //     render: function(){
    //     	var detailsForm =  Ext.getCmp('rentapplyDetails').down('form');
    //     	console.log(detailsForm);

    //     	var items = {
    //     		xtype: 'datefield',
    //         	fieldLabel: '申请日期'
    //     	}
    //     	detailsForm.add(items);
    //     }
    // },
    // bind: '{rentapplyApproveStore}',
    bodyPadding: 5,
    // height: 8000,
    // width:5000,
    bodyBorder: true,
    defaults: {
        anchor: '100%'
    },
    fieldDefaults: {
        labelAlign: 'left',
        msgTarget: 'none',
        invalidCls: '' 
    },
    items: [{
        xtype: 'form',
        // layout: 'form',
        padding: '10px',
        defaults:{
        	readOnly: true
        },
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
        }, 
        {
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
            fieldLabel: '整租',
            name:'isEntireRent',
          //   renderer: function(val) {
	         //    if (val ==true) {
		        //     return '<span style="color:blue;">整租</span>';
		        // } else {
		        //     return '<span style="color:yellow;">合租</span>';
		        // }
		        // return val;
          //   }
        }, {
            xtype: 'textfield',
            fieldLabel: '床',
            name:'isBed'
        }, {
            xtype: 'textfield',
            fieldLabel: '洗衣机',
            name:'isWasher'
        }, {
            xtype: 'textfield',
            fieldLabel: '空调',
            name:'isAirConditioning'
        }, {
            xtype: 'textfield',
            fieldLabel: '阳台',
            name:'isBalcony'
        }, {
            xtype: 'textfield',
            fieldLabel: '冰箱',
            name:'isRefrigerator'
        }, {
            xtype: 'textfield',
            fieldLabel: '厕所',
            name:'isToilet'
        }, {
            xtype: 'textfield',
            fieldLabel: '厨房',
            name:'isKitchen'
        }, {
            xtype: 'textfield',
            fieldLabel: '热水器',
            name:'isCalorifier'
        }
	    ]
    }, {
    	xtype: 'panel',
    	layout: 'column',
    	items:[{

    	}]
    }],

   	bbar: ['>',{
		xtype: 'button',
		ui: 'gray',
		text: '取消',
		handler:function(btn){
			var win = btn.up('window');
	        if (win) {
	            win.close();
	        }
		}
	}]
});
