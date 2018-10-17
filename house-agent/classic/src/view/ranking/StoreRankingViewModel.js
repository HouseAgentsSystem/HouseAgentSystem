Ext.define('HouseAgentSystem.view.ranking.StoreRankingViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.storeRankingViewModel',
    stores:{
            storeBarData:{type:'storeRankingStore'}
    }
});
