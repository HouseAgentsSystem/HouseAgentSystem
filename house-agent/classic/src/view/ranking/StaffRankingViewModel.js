Ext.define('HouseAgentSystem.view.ranking.StaffRankingViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.staffRankingViewModel',
    stores:{
            staffBarData:{type:'staffRankingStore'}
    }
});
