Ext.define('HouseAgentSystem.view.ranking.StaffRanking', {
    extend: 'Ext.container.Container',
    xtype: 'staffRanking',

    requires: [
      
        'HouseAgentSystem.view.ranking.StaffRankingPanel',
        'HouseAgentSystem.view.ranking.StaffRankingViewModel',
        'Ext.ux.layout.ResponsiveColumn'
    ],
    controller: 'rankingController',
    viewModel: {
        type: 'staffRankingViewModel'
    },

    layout: 'responsivecolumn',

    defaults: {
        defaults: {
            animation : !Ext.isIE9m && Ext.os.is.Desktop
        }
    },

    items: [
        {
            xtype: 'staffRankingPanel',
            userCls: 'big-50 small-100'
        }
    ]
});
