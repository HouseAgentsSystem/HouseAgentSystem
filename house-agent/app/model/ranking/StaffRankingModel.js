Ext.define('HouseAgentSystem.model.ranking.StaffRankingModel', {
    extend: 'HouseAgentSystem.model.Base',
    fields: [
        {type:'float',name:'total'},
        {type:'string',name:'staffId'},
        {type:'string',name:'staffName'},
        {type:'string',name:'storeName'}
    ]
});
