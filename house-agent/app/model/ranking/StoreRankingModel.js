Ext.define('HouseAgentSystem.model.ranking.StoreRankingModel', {
    extend: 'HouseAgentSystem.model.Base',

    fields: [
        {type:'float',name:'total'},
        {type:'int',name:'storeId'},
        {type:'string',name:'storeName'}
    ]
});
