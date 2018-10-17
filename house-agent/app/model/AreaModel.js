Ext.define('HouseAgentSystem.model.AreaModel', {
    extend: 'HouseAgentSystem.model.Base',
    fields: [
	    {type: 'string',name: 'name'},
	    {type: 'string',name: 'value'}
	],
	proxy: {
		type: 'memory',
		reader: {
			type: 'json',
			rootProperty: 'areas'
		}
	}
});