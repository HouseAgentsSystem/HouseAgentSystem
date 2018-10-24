Ext.define('HouseAgentSystem.view.staff.StaffInfo', {
    extend: 'Ext.panel.Panel',
	xtype: 'staffInfo',
	id: 'staffInfo',
	requires:[
        'Ext.grid.Panel'
    ], 
	
	controller: 'staffViewController',
	
    //width: 500,
    height: Ext.Element.getViewportHeight()-200,
	buttonAlign:'left',
    scrollable: true,
    title: 'Personal information',
    modal:true,
    layout: 'center',
	
    items: [{
		xtype: 'form',
		id: 'staffInfoForm',
        padding: '10px',
		width: 400,
		height:480,
		border:true,
        items: [{
            xtype: 'textfield',
            fieldLabel: 'id',
			id:'id',
            name:'id',  //最重要的属性
            hidden: true,
            readOnly: true
        }, {
            xtype: 'image',
			name:'头像',
			id:'faceImg',
            height: 140,
            width: 140,
			style:{
				borderRadius: '50%',//显示圆形图片
				marginLeft:'120px',
				marginTop:'30px',
				marginBottom:'30px',
			},
            alt: 'current user image',
            src: '/Customer/upload/staff/default.jpg',
			listeners:{
				el : {
					click : "openUploadWindow"
				}
			}	
        },{
            xtype: 'textfield',
            fieldLabel: '姓名',
			id:"relaName",
            name:'realName',
			style:{
				marginLeft:'50px'
			}
        }, {
            xtype: 'textfield',
            fieldLabel: '性别',
			id:'sex',
            name:'sex',
			style:{
				marginLeft:'50px'
			}
        }, {
            xtype: 'textfield',
            fieldLabel: '电话',
			id:'phoneNumber',
            name:'phoneNumber',
			style:{
				marginLeft:'50px'
			}
        }, {
            xtype: 'textfield',
            fieldLabel: '职位',
			id: 'position',
            name:'position',
			style:{
				marginLeft:'50px'
			}
        }, {
            xtype: 'textfield',
            fieldLabel: '所属门店',
			id: 'store',
            name:'store',
			style:{
				marginLeft:'50px'
			}
        }]
    }],
	listeners:{
		render:function(){
			Ext.getCmp('infoReload').click();
		}
	},
    buttons:  [{
        xtype: 'button',
        text: 'Edit',
        handler: 'submitEditForm2'//预留提交事件，在ViewController中实现。
    },{
    	xtype: 'button',
    	id: 'infoReload',
    	text: '刷新',
    	hidden: true,
    	listeners: {
    		click: function(){
				Ext.Ajax.request({  
					url: '/staff/findMyInfo',  
					method:'GET',
					success: function (response) {
	                    var json = Ext.util.JSON.decode(response.responseText);
						//调用文本域的方法，设置它的值~
						if(json.position == '总经理')
							Ext.getCmp('store').setEditable(false);
						Ext.getCmp('id').setValue(json.id);
						//console.log(Ext.getCmp('faceImg'));
						Ext.getCmp('faceImg').getEl().dom.src=('/Customer/upload/staff/'+json.faceImg);
						Ext.getCmp('relaName').setValue(json.realname);
						Ext.getCmp('sex').setValue(json.sex);
						Ext.getCmp('phoneNumber').setValue(json.phoneNumber);
						Ext.getCmp('position').setValue(json.position);
						Ext.getCmp('store').setValue(json.name);
						
	                }
				});
			}
    	}
    }]
});