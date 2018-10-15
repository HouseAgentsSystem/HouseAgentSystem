Ext.define('HouseAgentSystem.store.store.StoreGridStore', {
	extend: 'Ext.data.Store',
	storeId:'storeGridStore',
	alias: 'store.storeGridStore',
	model:'HouseAgentSystem.model.store.StoreModel',
	proxy: {
		type: 'rest',
		url: '/store',
		reader:{
			type:'json',
			rootProperty:'content',//对应后台返回的结果集名称
			totalProperty: 'totalElements'//分页需要知道总记录数
		},
		writer: {
			type: 'json'
		},
		
		simpleSortMode: true	//简单排序模式
	},
	autoLoad: true,
	autoSync: true,
	remoteSort: true,//全局(远程)排序
	pageSize: 15,
	sorters: {
		direction: 'DESC',property: 'id'
	}
});
