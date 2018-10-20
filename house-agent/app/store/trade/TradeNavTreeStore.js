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
	root : {
		text : 'LeHome',
		expanded : false	 //发送node=root
	},
	listeners : {
		'beforeexpand' : function(store,eOpts){
			//点击父亲节点的菜单会将节点的id通过ajax请求，将到后台
			console.log(store.data);
			console.log(eOpts.node.data);
			//this.proxy.extraParams.Data = store.data.nodeId;
			Ext.apply(store.proxy.extraParams, {nodeId: eOpts.node.data.nodeId});
		}
    }
});
