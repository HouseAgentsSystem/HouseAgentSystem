Ext.define('HouseAgentSystem.model.user.UserModel', {
	extend: 'HouseAgentSystem.model.Base',
	requires: [
		'Ext.data.proxy.Rest'
	],
	fields: [
		{type: 'int',name: 'id'},
		{type: 'string',name: 'faceImage'},
		{type: 'string',name: 'realname'},
		{type: 'string',name: 'phoneNumber'},
		{type: 'date',name: 'createTime',dateFormat:'Y/m/d H:i:s'},
		{type: 'string',name: 'status'},
	],
	proxy: {
		type: 'rest',
		url: '/users',
	}
});