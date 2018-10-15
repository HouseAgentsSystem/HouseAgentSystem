Ext.define('HouseAgentSystem.model.RegionModel', {
    extend: 'HouseAgentSystem.model.Base',
    fields: [
	    {type: 'string',name: 'name'},
	    {type: 'string',name: 'value'}
	],
	proxy: {
		type: 'memory',
		reader: {
			type: 'json',
			rootProperty: 'regions'
		}
	}
});