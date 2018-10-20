Ext.define('HouseAgentSystem.view.trade.TradeNavTree', {
      extend: 'Ext.tree.Panel',
    id:'tradeNavTree',
	xtype: 'tradeNavTree',
	title:'<b>组织架构</b>',
	bind:'{navTreeLists}',
	listeners: {
		'select': function(view, record, index, eOpts) {
			//alert(record.id);
			console.log(view);
			var store = Ext.getCmp('tradeGrid').getStore();
			if(record.id!='root'){
				Ext.apply(store.proxy.extraParams, {
					nodeId: record.data.nodeId, isLeaf: record.data.leaf
				});  
			}else{
				Ext.apply(store.proxy.extraParams, {
					nodeId:null, isLeaf:record.leaf
				});  
			}
			store.load(); 
		},
		'beforeitemexpand': function(node, eOpts){
			//点击父亲节点的菜单会将节点的id通过ajax请求，将到后台
			console.log(node.data);
			console.log(eOpts);
			var store = Ext.getCmp('tradeNavTree').getStore();
			//this.proxy.extraParams.Data = store.data.nodeId;
			//store.proxy.extraParams.Data = node.data.nodeId;
			Ext.apply(store.proxy.extraParams, {nodeId: node.data.nodeId});
			//store.load();
		}
	}			
});