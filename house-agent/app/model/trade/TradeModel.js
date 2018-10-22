Ext.define('HouseAgentSystem.model.trade.TradeModel', {
    extend: 'HouseAgentSystem.model.Base',
    requires: [
		'Ext.data.proxy.Ajax'
	],
    fields: [
	    {type: 'int',name: 'id'},
	    {type: 'date',name: 'saleDate', dateFormat:'Y/m/d H:i:s'},
	    {type: 'float',name: 'agencyFees'},
	    {type: 'float',name: 'actualPrice'},
	    {type: 'string',name: 'buyerName'},
	    {type: 'string',name: 'buyerPhoneNumber'},
	    {type: 'string',name: 'ownerName'},
	    {type: 'string',name: 'ownerPhoneNumber'},
	    {type: 'string',name: 'houseRegion'},
	    {type: 'string',name: 'houseAddress'},
	    {type: 'string',name: 'houseSummary'},
	    {type: 'string',name: 'storeName'},
	    {type: 'string',name: 'staffName'}
	]
});
