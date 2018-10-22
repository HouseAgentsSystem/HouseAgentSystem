Ext.define('HouseAgentSystem.view.house.HouseController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.houseController',

    // TODO - Add control logic or remove is not needed
    myMethod: function(){
    	Ext.Msg.alert('test', 'hello world');
    },

    
 //    function(model, records){
	//     if(records[0]){
	//         this.up('form').getForm().loadRecord(records[0]);
	//     }
	// }
	
	regionComboboxSelectChange:function(combo,record,index){
		
		var searchRegion = this.lookupReference('regionFieldValue').getValue();
		
		var store =	combo.up('gridpanel').getStore();
		Ext.apply(store.proxy.extraParams, {location:""});//, storeName:"", managerName:""
		
		if(searchRegion != '全市'){
			Ext.apply(store.proxy.extraParams, {location: searchRegion});
		}
		
		store.load({params:{start:0, limit:20, page:1}});
		
	},
	priceComboboxSelectChange:function(combo,record,index){
		
		var searchPrice = this.lookupReference('priceFieldValue').getValue();
		
		var store =	combo.up('gridpanel').getStore();
		Ext.apply(store.proxy.extraParams, {price:0});//, storeName:"", managerName:""
		
		if(searchPrice != '不限'){
			Ext.apply(store.proxy.extraParams, {price: searchPrice});
		}
		
		store.load({params:{start:0, limit:20, page:1}});
		
	},
	areaComboboxSelectChange:function(combo,record,index){
		
		var searchArea = this.lookupReference('areaFieldValue').getValue();
		
		var store =	combo.up('gridpanel').getStore();
		Ext.apply(store.proxy.extraParams, {roomArea:0});//, storeName:"", managerName:""
		
		if(searchArea != '不限'){
			Ext.apply(store.proxy.extraParams, {roomArea: searchArea});
		}
		
		store.load({params:{start:0, limit:20, page:1}});
		
	},
	excelUpload: function(btn) {
		var grid = btn.up('grid');
		var win = btn.up('window');
		Ext.MessageBox.confirm('提示', '确定上传操作吗？', 
			function(btn, text) {
				if(btn == 'yes') {
					var form = this.lookupReference('firstForm').getForm();
					
				    if (form.isValid()) {
				        form.submit({
				            url: '/house/excelupload',
				            waitMsg: 'Uploading your excel...',
				            success: function(form, action) {
								var result = action.result;
								if(result.success){
									Ext.Msg.alert('操作成功', result.msg, function() {
										grid.getStore().reload();
										win.close();
									});
								}else{
									Ext.Msg.alert('操作失败', result.msg);
								}
							}
				        });
				    }
				}
			}, this);
	},
	rarUpload: function(btn) {
		var win = btn.up('window');
		var grid = btn.up('grid');
		Ext.MessageBox.confirm('提示', '确定上传操作吗？', 
			function(btn, text) {
				if(btn == 'yes') {
					var form = this.lookupReference('secondForm').getForm();
					
				    if (form.isValid()) {
				        form.submit({
				            url: '/house/rarupload',
							method: 'POST',
				            waitMsg: 'Uploading your rar...',
				            success: function(form, action) {
								var result = action.result;
								if(result.success){
									Ext.Msg.alert('操作成功', result.msg, function() {
										win.close();
									});
								}else{
									Ext.Msg.alert('操作失败', result.msg);
								}
							}
				        });
				    }
				}
			}, this);
	},

	openAddWindow: function(btn) {
		btn.up('grid').up('container').add(Ext.widget('houseAddWindow')).show();
	},
	openAddMoreWindow: function(btn) {
		btn.up('grid').up('container').add(Ext.widget('houseAddMoreWindow')).show();
	},
	submitAddForm: function(btn) {
		var win    = btn.up('window');
		var form = win.down('form');
		var record = Ext.create('HouseAgentSystem.model.house.HouseModel');
		var values  =form.getValues();//获取form数据
       	record.set(values);
        record.save();
        Ext.data.StoreManager.lookup('houseGridStore').load();
        win.close();

		//...
	},
	
	removeMore: function(btn) {
	var grid = btn.up('grid');
	//console.log(grid);
	Ext.Msg.confirm('提示', '确定删除操作吗？数据将无法还原！',
		function(btn, text) {
			if(btn == 'yes') {
				var stores = grid.getStore();
				//console.log(stores);
				//var record = store.getAt();
				var records = grid.getSelectionModel().getSelection();
				//console.log(records);
				stores.remove(records);
			}
		}, this);
	},

	onEditButton: function(grid, rowIndex, colIndex) {
    	//var win = Ext.widget('houseEditWindow').show();
    	//console.log(grid);//console.log(colIndex);//console.log(v3);console.log(v4);
    	record = grid.getStore().getAt(rowIndex);
    	//console.log(record);
    	if(record){
    		//console.log(grid.up('form'));
    		//1
    		var win = grid.up('container').add(Ext.widget('houseEditWindow')).show();
	        win.down('form').getForm().loadRecord(record);
	        //2,需要定义id editForm
	        //Ext.getCmp("editForm").getForm().loadRecord(record);
	    }
	},
	submitEditForm: function(btn) {
		//var form = btn.up('window').down('form');
		//...
		var win    = btn.up('window');
		var store = Ext.data.StoreManager.lookup('houseGridStore');
    	var values  = win.down('form').getValues();//获取form数据
    	var record = store.getById(values.id);//获取id获取store中的数据
    	record.set(values);
        	//store.load();
         win.close();
	},
	
	onDeleteButton: function(grid, rowIndex, colIndex) {
		Ext.MessageBox.confirm('提示', '确定进行删除操作吗？数据将无法还原！',
  			function(btn, text){
            	if(btn=='yes'){
            		var store = grid.getStore();
					var record = store.getAt(rowIndex);
					store.remove(record);//DELETE//http://localhost:8081/house/112
					//store.sync();
				}
        	}
        , this);
	},
	onTradeButton:function(grid, rowIndex, colIndex){
		var record = grid.getStore().getAt(rowIndex);
		if (record ) {
			var win = grid.up('container').add(Ext.widget('tradeAddWindow'));
			win.show();
			win.down('form').getForm().loadRecord(record);
		}
	}
	
});
