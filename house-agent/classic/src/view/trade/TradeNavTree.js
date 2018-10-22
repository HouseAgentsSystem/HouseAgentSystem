Ext.define('HouseAgentSystem.view.trade.TradeNavTree', {
    extend: 'Ext.tree.Panel',
    mixins:['HouseAgentSystem.view.trade.TreeFilter'],
    id:'tradeNavTree',
	xtype: 'tradeNavTree',
	title:'<b>组织架构</b>',
	bind:'{navTreeLists}',
	
	dockedItems: [{
        xtype: 'textfield',
        reference: 'navtreeFilter',
        dock: 'top',
        emptyText: 'Search',

        triggers: {
            clear: {
                cls: 'x-form-clear-trigger',
                handler: 'onNavFilterClearTriggerClick',
                hidden: true,
                scope: 'controller'
            },
            search: {
                cls: 'x-form-search-trigger',
                weight: 1,
                handler: 'onNavFilterSearchTriggerClick',
                scope: 'controller'
            }
        },

        listeners: {
            change: 'onNavFilterFieldChange',
            buffer: 300
        }
    }],

	listeners: {
		'select': function(view, record, index, eOpts) {
			
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
			var store = Ext.getCmp('tradeNavTree').getStore();
			Ext.apply(store.proxy.extraParams, {nodeId: node.data.nodeId});
		}
	}			
});