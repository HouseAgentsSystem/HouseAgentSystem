Ext.define('HouseAgentSystem.view.staff.StaffInfo', {
    extend: 'Ext.panel.Panel',
	xtype: 'staffInfo',
	requires:[
        'Ext.grid.Panel'
    ], 
	
	controller: 'staffViewController',
	
    width: 500,
	buttonAlign:'left',
    scrollable: true,
    title: 'Personal information',
    modal:true,
    layout: 'center',
	
    items: [{
		xtype: 'form',
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
            src: 'resources/images/user-profile/default.jpg',
			listeners:{
				el : {
					click : "openUploadWindow"
// 					function() {
// 						var win = new Ext.create('Ext.window.Window',{
// 							controller: 'staffViewController',
// 							xtype : 'form',
// 							layout : "form",
// 							reference: 'uploadForm',
// 							id:'uploadForm',
// 							title : '选择头像',
// 							width : 500,
// 							bodyPadding: 10,
// 							plain : true,
// 							modal : true,
// 							closeAction : "hide",
// 							items: [{
// 								xtype: 'filefield',
// 								name: 'faceImg',
// 								fieldLabel: 'Photo',
// 								labelWidth: 50,
// 								msgTarget: 'side',
// 								allowBlank: false,
// 								anchor: '100%',
// 								buttonText: 'Select Photo...'
// 							}],
// 							buttons: [{
// 								text: 'Upload',
// 								handler: 'faceImgUpload',
// 							}]
// 						});
// 						win.show();
// 					}
				}
			}	
        },{
            xtype: 'textfield',
            fieldLabel: '姓名',
			id:"relaName",
            name:'realname',
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
			Ext.Ajax.request({  
				url: '/staff/findMyInfo',  
				method:'GET',
				success: function (response) {
                    var json = Ext.util.JSON.decode(response.responseText);
					//调用文本域的方法，设置它的值~
					if(json.position == '总经理')
						Ext.getCmp('store').setEditable(false);
					Ext.getCmp('id').setValue(json.id);
					//Ext.getCmp('feceImg').setValue(json.faceImg);
					Ext.getCmp('relaName').setValue(json.realname);
					Ext.getCmp('sex').setValue(json.sex);
					Ext.getCmp('phoneNumber').setValue(json.phoneNumber);
					Ext.getCmp('position').setValue(json.position);
					Ext.getCmp('store').setValue(json.name);
					
					
//					console.log(json.id);
// 					console.log(json.realname);
// 					console.log(json.sex);
// 					console.log(json.phoneNumber);
// 					console.log(json.position);
// 					console.log(json.store);
                }
			});
		}
	},
    buttons:  [{
        xtype: 'button',
        text: 'Edit',
        handler: 'submitEditForm2'//预留提交事件，在ViewController中实现。
    }]
});