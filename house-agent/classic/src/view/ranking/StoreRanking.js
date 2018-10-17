Ext.define('HouseAgentSystem.view.ranking.StoreRanking', {
    extend: 'Ext.container.Container',
    xtype: 'storeRanking',

    requires: [
      
        'HouseAgentSystem.view.ranking.StoreRankingPanel',
        'HouseAgentSystem.view.ranking.StoreRankingViewModel',
        'Ext.ux.layout.ResponsiveColumn'
    ],
    controller: 'rankingController',
    viewModel: {
        type: 'storeRankingViewModel'
    },

    layout: 'responsivecolumn',

    defaults: {
        defaults: {
            animation : !Ext.isIE9m && Ext.os.is.Desktop
        }
    },

    items: [
        {
            xtype: 'storeRankingPanel',
            userCls: 'big-50 small-100'
        }
    ]
});
