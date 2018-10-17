Ext.define('HouseAgentSystem.store.AreaStore', {
	extend: 'Ext.data.Store',
	storeId:'areaStore',
	alias: 'store.areaStore',
	model:'HouseAgentSystem.model.AreaModel',

	data: {
		'regions': [{
	        "name": '不限',
	        "value": 0
	    },
		{
	        "name": '50m²以下',
	        "value": 50
	    },
	    {
	        "name": '50~70m²',
	        "value": 70
	    },
	    {
	        "name": '70~100m²',
	        "value": 100
	    },
	    {
	        "name": '100~150m²',
	        "value": 150
	    },
	    {
	        "name": '150~180m²',
	        "value": 180
	    },
	    {
	        "name": '180m²以上',
	        "value": 181
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
