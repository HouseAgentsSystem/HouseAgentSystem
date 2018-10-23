Ext.define('HouseAgentSystem.model.user.UserModel', {
	extend: 'HouseAgentSystem.model.Base',
	requires: [
		'Ext.data.proxy.Rest'
	],
	fields: [
		{type: 'int',name: 'id'},
		{type: 'string',name: 'FaceImg'},
		{type: 'string',name: 'realname'},
		{type: 'string',name: 'sex'},
		{type: 'string',name: 'phoneNumber'},
		{type: 'string',name: 'store'}
	],
	proxy: {
		type: 'rest',
		url: '/user',
	}
});