Ext.define('HouseAgentSystem.view.store.Store', {
    extend: 'Ext.container.Container',
    xtype: 'store',
    //requires: [],
    //controller: 'store',				//viewController:代码与视图分离。声明视图绑定的事件，可以多个视图共享。
    //viewModel: {type: 'storelist'},	//viewModel：配置Stote数据源。多个视图共享Store。
    
    controller: 'storeViewController',
    viewModel: {type: 'storeViewModel'},
    	
    layout: 'fit',
    items: [{xtype:'storePanel'}]
    //html:'订单管理模块'
});
