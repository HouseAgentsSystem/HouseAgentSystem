Ext.define('HouseAgentSystem.store.DistributionStore', {
	extend: 'Ext.data.Store',
	storeId:'priceStore',
	alias: 'store.distributionStore',
	model:'HouseAgentSystem.model.DistributionModel',

	data: {
		'regions': [{
	        "name": '不限',
	        "value": 0
	    },
		{
	        "name": '未分配',
	        "value": 1
	    },
	    {
	        "name": '已分配',
	        "value": 2
	    }]
	},

	proxy: {
		type: 'memory',
		reader: {
			type: 'json',
			rootProperty: 'regions'
		}
	},
	autoLoad: true
});
