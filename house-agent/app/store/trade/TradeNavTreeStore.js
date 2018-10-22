Ext.define('HouseAgentSystem.store.trade.TradeNavTreeStore', {
    extend: 'Ext.data.TreeStore',//TreePanel使用TreeStore
    alias: 'store.tradeNavTreeStore',
    id: 'tradeNavTreeStore',  
	proxy: {
		type: 'ajax',
		url: '/trade/findNodes',	//后台Controller中的接口url地址
		reader: {
			type:'json'
		}
	},
	//defaultRootId : 1,
	root: {
		text : 'LeHome',
		expanded : false	 //发送node=root
	}
});
