Ext.define('HouseAgentSystem.model.house.HouseModel', {
	extend: 'HouseAgentSystem.model.Base',
	requires: [
		'Ext.data.proxy.Rest'
	],
	fields: [
        {
            type: 'int',
            name: 'id'
        },
        {
            type: 'string',
            name: 'title'
        },
        {
            type: 'int',
            name: 'price'
        },
        {
            type: 'string',
            name: 'region'
        },
        {
            type: 'int',
            name: 'area'
        },{
            type: 'string',
            name: 'storeName'
        }
        
    ],
    proxy: {
    	type: 'rest',
    	url: '/house'
    }

});