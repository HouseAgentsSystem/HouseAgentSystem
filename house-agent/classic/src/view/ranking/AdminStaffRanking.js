Ext.define('HouseAgentSystem.view.ranking.AdminStaffRanking', {
    extend: 'Ext.container.Container',
    xtype: 'adminStaffRanking',

    requires: [
        'HouseAgentSystem.view.ranking.AdminStaffRankingPanel',
        
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
            xtype: 'adminStaffRankingPanel',
            userCls: 'big-50 small-100'
        }
    ]
});
