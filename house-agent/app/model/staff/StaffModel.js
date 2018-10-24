Ext.define('HouseAgentSystem.model.staff.StaffModel', {
	extend: 'HouseAgentSystem.model.Base',
	requires: [
		'Ext.data.proxy.Rest'
	],
	fields: [
		{type: 'string',name: 'id'},
		{type: 'string',name: 'faceImg'},
		{type: 'string',name: 'realName'},
		{type: 'string',name: 'sex'},
		{type: 'string',name: 'phoneNumber'},
		{type: 'string',name: 'name'}
	],
	proxy: {
		type: 'rest',
		url: '/staff',
	}
});