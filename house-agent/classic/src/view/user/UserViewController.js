Ext.define('HouseAgentSystem.view.user.UserViewController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.userViewController',
			 
// 	/*Edit*/
// 	onEditButton:function(grid, rowIndex, colIndex){
// 		var record = grid.getStore().getAt(rowIndex);
// 		if (record ) {
// 			var win = grid.up('container').add(Ext.widget('staffEditWindow'));
// 			win.show();
// 			win.down('form').getForm().loadRecord(record);
// 		}
// 	},
// 	
// 	openEditWindow:function(grid, rowIndex, colIndex){
// 		var win = grid.up('panel').add(Ext.widget('staffEditWindow'));
// 		win.show();
// 		win.down('form').getForm().loadRecord(record);
// 	},
// 	
// 	//更新事件	
// 	submitEditForm:function(btn){
// 		var win = btn.up('window');
// 		var store = Ext.data.StoreManager.lookup('staffGridStroe');
// 		var values = win.down('form').getValues();	//获取form数据
// 		var record = store.getById(values.id);			//获取id获取store中的数据
// 		record.set(values);
// 		store.load();
// 		win.close();
// 	},

// 	openUploadWindow:function(){
// 		this.getView().add(Ext.widget('staffFaceImgUploadWindow')).show();;
// 		// var win = this.getView().up('window').add(Ext.widget('staffEditWindow'));
// 	},
	
	//快捷查询
	quickSearch:function(btn){
		var searchField = this.lookupReference('searchFieldName').getValue();
		var searchValue = this.lookupReference('searchFieldValue').getValue();
  		
		var store =	btn.up('gridpanel').getStore();
		//var store = Ext.getCmp('userGridPanel').getStore();// Ext.getCmp(）需要在staffPanel设置id属性
		Ext.apply(store.proxy.extraParams, {realname:""});
		if(searchField==='realname'){
			Ext.apply(store.proxy.extraParams, {realname:searchValue});
		}
		store.load({params:{start:0, limit:10, page:1}});
	},
	
	/*改变客户状态*/
	onDisableButton:function(grid, rowIndex, colIndex){
		var record = grid.getStore().getAt(rowIndex);
		var store = Ext.data.StoreManager.lookup('userGridStroe');
		console.log("前："+record.get('status'));
		if(record.get('status') == 1)
			record.set('status', 0);
		else if(record.get('status') == 0)
			record.set('status', 1);
		console.log("后："+record.get('status'));
		console.log(record);
		store.load();
// 		Ext.Ajax.request({ 
// 			url : '/users',
// 			method : 'POST',
// 			success: function(form, action){  
// 				Ext.Msg.alert('Success', '修改成功！');
// 			},
// 			failure: function(fp) {
// 				Ext.Msg.alert('Failure', '修改失败！');
// 			}
// 		})
		
// 		record.set(values);
// 		store.load();
// 		win.close();
	}
});
