Ext.define('HouseAgentSystem.view.rentapplyapprove.RentApplyApproveViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.rentapplyApproveViewModel',
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
    	rentapplyApproveStore: {type: 'rentapplyApproveStore'}//调用组件2
    }
});
