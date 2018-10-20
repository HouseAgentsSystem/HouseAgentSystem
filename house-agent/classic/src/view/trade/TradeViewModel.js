/**
	1.绑定到主视图
	2.通过bind属性绑定到具体的子视图
8*/
Ext.define('HouseAgentSystem.view.trade.TradeViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.tradeViewModel',
	stores: {
        navTreeLists: {
            type: 'tradeNavTreeStore',
            autoLoad: true
        },
		tradeLists: {
            type: 'tradeGridStore',
            autoLoad: true
        }
    }
});
