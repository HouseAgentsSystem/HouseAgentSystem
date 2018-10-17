Ext.define('HouseAgentSystem.view.house.HouseModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.houseModel',

    requires: [
        'Ext.data.Store',
        'Ext.data.proxy.Memory',
        'Ext.data.field.Integer',
        'Ext.data.field.String',
        'Ext.data.field.Date',
        'Ext.data.field.Boolean',
        'Ext.data.reader.Json'
    ],

    stores: {
        // allResults: {
        //     type: 'searchresults'
        // },

        // usersResults: {
        //     type: 'searchusers'
        // },
        
        // inboxResults: {
        //     type: 'inbox'
        // }
        houselists: {
           type: 'houseGridStore' 
       }
    }
});
