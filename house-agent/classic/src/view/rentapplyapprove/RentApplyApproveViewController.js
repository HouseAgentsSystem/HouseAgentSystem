Ext.define('HouseAgentSystem.view.rentapplyapprove.RentApplyApproveViewController', {
    extend: Ext.app.ViewController,
    alias: 'controller.rentapplyApproveViewController',
    //1.签收任务
    onClickRentApplyApproveClaimButton: function(view, recIndex, cellIndex, item, e, record) {
        Ext.Ajax.request({
            url: '/rentapply/claim/' + record.get('taskId'),
            method: 'post',
            success: function(response, options) {
                var json = Ext.util.JSON.decode(response.responseText);
                if (json.success) {
                    Ext.Msg.alert('操作成功', json.msg, function() {
                        view.getStore().reload();
                    });
                } else {
                    Ext.Msg.alert('操作失败', json.msg);
                }
            }
        });
    },
    //2.创建审批表单（并绑定Task id）
    setCurrentView: function(view, form, title) {
		var cfg = Ext.apply({
			xtype: 'rentapplyApproveWindow',
			items: [{xtype: form}]
		},{
			title: title
		});
		var win = Ext.widget(cfg);
        view.up('panel').up('container').add(win);
        return win;
    },
    onClickRentApplyApproveCompleteWindowButton: function(view, recIndex, cellIndex, item, e, record) {
    	//选中点击的行
        var win = this.setCurrentView(view,"adminAudit", '管理员审批');
        win.down('form').getForm().loadRecord(record);

        

    },
    onClickRentApplyDetailsWindowButton: function(view, recIndex, cellIndex, item, e, record) {

        if (record ) {
            var win = view.up('container').add(Ext.widget('details'));
            win.show();
            win.down('form').getForm().loadRecord(record);
            
            var detailsForm =  Ext.getCmp('rentapplyDetails').down('panel');
            Ext.each(record.data.imgs, function(val){
                var items = {
                    src:'/Customer/upload/houseRent/'+val,
                    xtype: 'image',
                    fieldLabel: '图片',
                    cls: 'header-right-profile-image',
                    height: 300,
                    width: 350,
                    alt:'current rentHouse image',
                };
                detailsForm.add(items);
            });
        }

        // win.down('form').getForm().loadRecord(record);
    },
    //3.封装审批表单数据,并以Ajax提交到后台完成任务的流程变量封装对象中。
	complete: function(url, variables,form){
		// 转换JSON为字符串
	    var keys = "", values = "", types = "";
		if (variables) {
			Ext.each(variables, function (item) {
				if (keys != "") {
					keys += ",";
					values += ",";
					types += ",";
				}
				keys += item.key;
				values += item.value;
				types += item.type;
            });
		}
		Ext.Ajax.request({
            url: url,
            method: 'post',
            params : { 
			 	keys: keys,
		        values: values,
		        types: types
			}, 
            success: function(response, options) {
                var json = Ext.util.JSON.decode(response.responseText);
                if (json.success) {
                    Ext.Msg.alert('操作成功', json.msg, function() {
                    	form.up('window').close();
                        //grid.getStore().reload();
                        Ext.data.StoreManager.lookup('rentapplyApproveStore').load();
                    });
                } else {
                    Ext.Msg.alert('操作失败', json.msg);
                }
            }
        });
	},
	//管理员审批
    onClickAdminAuditFormSubmitButton: function(btn) {
    	var form = btn.up('form');
    	var values = form.getValues();
    	var url = '/rentapply/complete/' + values.taskId;
    	var variables = [{
			key: 'adminPass',
			value: values.adminPass,//获取表单选择的value
			type: 'B'
		},{
			key: 'adminBackReason',
			value: values.adminBackReason,//获取表单选择的value
			type: 'S'
		}];
        this.complete(url,variables,form);
    }
});