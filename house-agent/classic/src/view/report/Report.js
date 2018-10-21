Ext.define('HouseAgentSystem.view.report.Report', {
    extend: 'Ext.container.Container',
    xtype: 'report',

    // requires: [
    //     'Ext.ux.layout.ResponsiveColumn'
    // ],

    //controller: 'orderView',
    // viewModel: {
    //     type: 'dashboard'
    // },

    layout: 'fit',
    
    // listeners: {
    //     hide: 'onHideView'
    // },

    items: [
        {
            xtype: 'report-pivot-grid',

            // 60% width when viewport is big enough,
            // 100% when viewport is small
            //userCls: 'big-60 small-100'
        }
    ]
    // html:'订单模块正在准备中......'
});
