Ext.define('HouseAgentSystem.view.staff.StaffViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.staffViewModel',

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
		staffLists: {type: 'staffGridStroe'}
    }
});
