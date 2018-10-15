Ext.define('HouseAgentSystem.model.store.StoreModel', {
    extend: 'HouseAgentSystem.model.Base',
    requires: [
		'Ext.data.proxy.Rest'
	],
    fields: [
	    {type: 'int',name: 'id'},
	    {type: 'string',name: 'storeName'},
	    {type: 'string',name: 'address'},
	    {type: 'string',name: 'managerName'},
	    {type: 'string',name: 'managerPhone'}
	],
	proxy: {
		type: 'rest',
		url: '/store',
	}
});
