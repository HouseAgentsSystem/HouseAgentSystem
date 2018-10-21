Ext.define('HouseAgentSystem.view.report.ReportModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.report',

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
        reportResults: {
            type: 'report'
        }
    }
});
