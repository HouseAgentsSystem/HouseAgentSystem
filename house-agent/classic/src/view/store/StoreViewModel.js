Ext.define('HouseAgentSystem.view.store.StoreViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.storeViewModel',

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
		storeLists: {type: 'storeGridStore'},
        regionLists: {type: 'regionStore'}
    }
});
