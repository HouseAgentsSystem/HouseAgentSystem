Ext.define('HouseAgentSystem.store.PriceStore', {
	extend: 'Ext.data.Store',
	storeId:'priceStore',
	alias: 'store.priceStore',
	model:'HouseAgentSystem.model.PriceModel',

	data: {
		'regions': [{
	        "name": '不限',
	        "value": 0
	    },
		{
	        "name": '50万以下',
	        "value": 50
	    },
	    {
	        "name": '50~100万',
	        "value": 100
	    },
	    {
	        "name": '100~150万',
	        "value": 150
	    },
	    {
	        "name": '150~300万',
	        "value": 300
	    },
	    {
	        "name": '300~500万',
	        "value": 500
	    },
	    {
	        "name": '500~1000万',
	        "value": 1000
	    },
	    {
	        "name": '1000万以上',
	        "value": 1001
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
