Ext.define('HouseAgentSystem.view.staff.StaffViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.staffViewController',
    
    openAddWindow:function(toolbar, rowIndex, colIndex){
		toolbar.up('panel').up('container').add(Ext.widget('staffAddWindow')).show();
	},
    /*Add*/
	submitAddForm:function(btn){
//		var win    = btn.up('window');
//		var form = win.down('form');
//		var record = Ext.create('Admin.model.staff.StaffModel');
//		var values  =form.getValues();//获取form数据
//		var id = null;
//		console.log(values);
//        record.set(values);
//        record.set(id);
//	    record.save();
//	    Ext.data.StoreManager.lookup('staffGridStroe').load();
//	    win.close();
		var win  = btn.up('window');
		var form = win.down('form');
		form.getForm().submit({       
				url:'/staff',
				method : 'POST',
				success: function(form, action){    
						Ext.Msg.alert('Success', action.result.msg,function(){
								btn.up('window').close();
								Ext.data.StoreManager.lookup('staffGridStroe').load();
						});       
				}, 
				failure: function(form, action){
						Ext.Msg.alert('Error', action.result.msg);
				}
		});
	},
    
	/*Edit*/
	onEditButton:function(grid, rowIndex, colIndex){
		var record = grid.getStore().getAt(rowIndex);
		if (record ) {
			var win = grid.up('container').add(Ext.widget('staffEditWindow'));
			win.show();
			win.down('form').getForm().loadRecord(record);
		}
	},
	
	openEditWindow:function(grid, rowIndex, colIndex){
		var win = grid.up('panel').add(Ext.widget('staffEditWindow'));
		win.show();
		win.down('form').getForm().loadRecord(record);
	},
	
	//更新事件	
	submitEditForm:function(btn){
		var win = btn.up('window');
		var store = Ext.data.StoreManager.lookup('staffGridStroe');
		console.log(store);
		var values = win.down('form').getValues();	//获取form数据
		var record = store.getById(values.id);			//获取id获取store中的数据
		record.set(values);
		store.load();
		win.close();
	},
	
	submitEditForm2:function(btn){
		var win  = btn.up('panel');
		var form = win.down('form');
		console.log("修改的id" + form.getForm().getValues()['id']),
		form.getForm().submit({
			url : '/staff/updateInfo',	
			method : 'POST',
			success : function(form, action) {
				Ext.Msg.alert("提示","修改成功!");   
			},
			failure : function(form, action) {   
				Ext.Msg.alert("提示","修改失败!");   
			} 
		})
	},

	openUploadWindow:function(){
		this.getView().add(Ext.widget('staffFaceImgUploadWindow')).show();;
		// var win = this.getView().up('window').add(Ext.widget('staffEditWindow'));
	},

	/*faceImgUpload*/
	faceImgUpload:function(btn) {
		var form = this.lookupReference('uploadForm').getForm();
		var win = btn.up('window');
		//var form = Ext.getCmp('uploadForm').getForm();
		if(form.isValid()) {
			form.submit({
				url: '/staff/upload',
				method:'POST',
				waitMsg: 'Uploading your photo...',
				success: function(fp) {
					Ext.Msg.alert('Success', '上传成功！');
					console.log(Ext.getCmp('staffInfoForm'));
					//myview.render('grid-example');
					win.close();
					Ext.getCmp('infoReload').click();
					//Ext.getCmp('staffInfo').render();
					//this.redirectTo("staffInfo");
					//Ext.getCmp('staffInfo').up('container').render();
				},
				failure: function(fp) {
					Ext.Msg.alert('Failure', '上传失败！');
				}
			});
		}
	},


	/*Delete One Row*/	
	onDeleteButton:function(grid, rowIndex, colIndex){
	   Ext.MessageBox.confirm('提示', '确定要进行删除操作吗？数据将无法还原！',
  			function(btn, text){
						if(btn=='yes'){
						var store = grid.getStore();
						var record = store.getAt(rowIndex);
						store.remove(record);//DELETE//http://localhost:8081/staff/112
						//store.sync();
					}
				}
        , this);
	},
	/*Delete More Rows*/	
	deleteMoreRows:function(btn, rowIndex, colIndex){
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
		if (selModel.hasSelection()) {
			Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
				if (button == "yes") {
					var rows = selModel.getSelection();
					var selectIds = []; //要删除的id
					Ext.each(rows, function (row) {
						selectIds.push(row.data.id);
					});
					Ext.Ajax.request({ 
						url : '/staff/deletes', 
						method : 'post', 
						params : { 
							//ids[] :selectIds
							ids :selectIds
						}, 
						success: function(response, options) {
							var json = Ext.util.JSON.decode(response.responseText);
							if(json.success){
								Ext.Msg.alert('操作成功', json.msg, function() {
								grid.getStore().reload();
							});
							}else{
								Ext.Msg.alert('操作失败', json.msg);
							}
						}
					});
				}
			});
		}else {
			Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
		}
	},

	searchComboboxSelectChuang:function(combo, record, index) {
	  	var searchField = this.lookupReference('searchFieldName').getValue();
	  	if (searchField === 'sex') {
	    	this.lookupReference('searchFieldValue').hide();
	    	this.lookupReference('searchSexFieldValue').show();
	  	} else {
	    	this.lookupReference('searchFieldValue').show();
	    	this.lookupReference('searchSexFieldValue').hide();
	  	}
	},
	
	//快捷查询
	quickSearch:function(btn){
		var searchField = this.lookupReference('searchFieldName').getValue();
		var searchValue = this.lookupReference('searchFieldValue').getValue();

		var searchSexFieldValue = this.lookupReference('searchSexFieldValue').getValue();
  		
		var store =	btn.up('gridpanel').getStore();
		//var store = Ext.getCmp('userGridPanel').getStore();// Ext.getCmp(）需要在staffPanel设置id属性
		Ext.apply(store.proxy.extraParams, {realname:"",sex:""});
		if(searchField==='realname'){
			Ext.apply(store.proxy.extraParams, {realname:searchValue});
		}
		if(searchField==='sex'){
			Ext.apply(store.proxy.extraParams, {sex:searchSexFieldValue});
		}
		store.load({params:{start:0, limit:10, page:1}});
	},

	//高级查询
	openSearchWindow:function(toolbar, rowIndex, colIndex) {
  		toolbar.up('panel').up('container').add(Ext.widget('staffSearchWindow')).show();
	},
	submitSearchForm:function(btn){
		var store =	Ext.data.StoreManager.lookup('staffGridStroe');
		var win = btn.up('window');
		var form = win.down('form');
		var values  = form.getValues();
		Ext.apply(store.proxy.extraParams, {staffNumber:"",createTimeStart:"",createTimeEnd:""});
		Ext.apply(store.proxy.extraParams,{
			staffNumber:values.staffNumber,
			createTimeStart:Ext.util.Format.date(values.createTimeStart, 'Y/m/d H:i:s'),
			createTimeEnd:Ext.util.Format.date(values.createTimeEnd, 'Y/m/d H:i:s')
		});
		store.load({params:{start:0, limit:20, page:1}});
		win.close();
	},
	
	
	onClickUploadFaceImg: function (btn) {
        var form = btn.up('window').down('form');;
        form.getForm().submit({       
            //url:'/process-definition',
            method : 'POST',
            waitMsg: '正在上传，请耐心等待....',
            success: function(form, action){    
                Ext.Msg.alert('Success', action.result.msg,function(){
                    btn.up('window').close();
                    Ext.data.StoreManager.lookup('processDefinitionStroe').load();
                });       
            }, 
            failure: function(form, action){
                Ext.Msg.alert('Error', action.result.msg);
            }
        });
    },
		
		changeFaceImg:function(){
				Ext.Msg.alert('Error');
		}
});
