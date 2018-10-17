Ext.define('HouseAgentSystem.view.store.StoreViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.storeViewController',
    /********************************************** Controller View *****************************************************/
    /*Add*/
	openAddWindow:function(toolbar, rowIndex, colIndex){
		toolbar.up('panel').up('container').add(Ext.widget('storeAddWindow')).show();
	},
    /*Edit*/
	openEditWindow:function(grid, rowIndex, colIndex){
         var record = grid.getStore().getAt(rowIndex);
		//获取选中数据的字段值：console.log(record.get('id')); 或者 console.log(record.data.id);
		if (record ) {
			var win = grid.up('container').add(Ext.widget('storeEditWindow'));
			win.show();
			win.down('form').getForm().loadRecord(record);
		}
	},
	/*Search More*/	
	openSearchWindow:function(toolbar, rowIndex, colIndex){
		toolbar.up('panel').up('container').add(Ext.widget('storeSearchWindow')).show();
	},
	
	regionComboboxSelectChange:function(combo,record,index){
		
		var searchRegion = this.lookupReference('regionFieldValue').getValue();
		
		var store =	combo.up('gridpanel').getStore();
		Ext.apply(store.proxy.extraParams, {region:""});//, storeName:"", managerName:""
		
		if(searchRegion != '全市'){
			Ext.apply(store.proxy.extraParams, {region: searchRegion});
		}
		
		store.load({params:{start:0, limit:20, page:1}});
		
	},
	/********************************************** Submit / Ajax / Rest *****************************************************/
	/*Add Submit*/	
	submitAddForm:function(btn){
		var win    = btn.up('window');
		var form = win.down('form');
		var record = Ext.create('HouseAgentSystem.model.store.StoreModel');
		var values  =form.getValues();//获取form数据
		record.set(values);
		record.save();
		Ext.data.StoreManager.lookup('storeGridStore').load();
		win.close();
	},
	/*Edit Submit*/	
	submitEditForm:function(btn){
		var win    = btn.up('window');
		var store = Ext.data.StoreManager.lookup('storeGridStore');
		var values  = win.down('form').getValues();//获取form数据
		var record = store.getById(values.id);//获取id获取store中的数据
		record.set(values);//rest put 
		//store.load();
		win.close();
	},
		
	/*Quick Search*/	
	quickSearch:function(btn){
		var searchField = this.lookupReference('searchFieldName').getValue();
		var searchValue = this.lookupReference('searchFieldValue').getValue();
		
		var store =	btn.up('gridpanel').getStore();
		//var store = Ext.getCmp('userGridPanel').getStore();// Ext.getCmp(）需要在StorePanel设置id属性
		Ext.apply(store.proxy.extraParams, {storeName:"",managerName:""});
		
		if(searchField==='storeName'){
			Ext.apply(store.proxy.extraParams, {storeName: searchValue});
		}
		if(searchField==='managerName'){
			Ext.apply(store.proxy.extraParams, {managerName: searchValue});
		}
		store.load({params:{start:0, limit:20, page:1}});
	},
	submitSearchForm:function(btn){
		var store =	Ext.data.StoreManager.lookup('storeGridStore');
		var win = btn.up('window');
		var form = win.down('form');
		var values  = form.getValues();
		Ext.apply(store.proxy.extraParams, {storeNumber:"",createTimeStart:"",createTimeEnd:""});
		Ext.apply(store.proxy.extraParams,{
			storeNumber:values.storeNumber,
			createTimeStart:Ext.util.Format.date(values.createTimeStart, 'Y/m/d H:i:s'),
			createTimeEnd:Ext.util.Format.date(values.createTimeEnd, 'Y/m/d H:i:s')
		});
		store.load({params:{start:0, limit:20, page:1}});
		win.close();
	},
	/*Delete One Row*/	
	deleteOneRow:function(grid, rowIndex, colIndex){
	   Ext.MessageBox.confirm('提示', '确定要进行删除操作吗？数据将无法还原！',
  			function(btn, text){
            	if(btn=='yes'){
            		var store = grid.getStore();
					var record = store.getAt(rowIndex);
					store.remove(record);//DELETE //http://localhost:8081/store/112
					//store.sync();
				}
        	}
        , this);
	},

 	backToAll: function(btn) {
 		var store =	btn.up('gridpanel').getStore();
		//var store = Ext.getCmp('userGridPanel').getStore();// Ext.getCmp(）需要在StorePanel设置id属性
		
		this.lookupReference('regionFieldValue').setValue("全市");
		this.lookupReference('searchFieldName').setValue("店名");
		this.lookupReference('searchFieldValue').setValue("");

		// if(searchField==='storeName'){
		// 	Ext.apply(store.proxy.extraParams, {storeName: searchValue});
		// }
		// if(searchField==='managerName'){
		// 	Ext.apply(store.proxy.extraParams, {managerName: searchValue});
		// }

		Ext.apply(store.proxy.extraParams, {region:"", storeName:"", managerName:""});
		store.load({params:{start:0, limit:20, page:1}});
 	},

	/*Disable*/	
	onDisableButton:function(grid, rowIndex, colIndex){
		Ext.Msg.alert("Title","Click Disable Button");
	}
});