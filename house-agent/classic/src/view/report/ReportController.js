Ext.define('HouseAgentSystem.view.report.ReportController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.report',

    requires: [
        'Ext.exporter.text.CSV',
        'Ext.exporter.text.TSV',
        'Ext.exporter.text.Html',
        'Ext.exporter.excel.Xml',
        'Ext.exporter.excel.Xlsx',
        'Ext.exporter.excel.PivotXlsx'
    ],
    events: ['beforedocumentsave', 'documentsave', 'dataready'],

    yearLabelRenderer: function(value){
        return 'Year ' + value;
    },

    monthLabelRenderer: function(value){
        return Ext.Date.monthNames[value];
    },

    expandAll: function(){
        this.getView().expandAll();
    },

    collapseAll: function(){
        this.getView().collapseAll();
    },
    //根据菜单选择的值来设置SubTotalsPosition位置
    subtotalsHandler: function(button, checked){
        if(!checked) {
            return;
        }
        // reconfigure the pivot grid with new settings
        this.getView().reconfigurePivot({
            rowSubTotalsPosition: button.values.toLowerCase(),
            colSubTotalsPosition: button.values.toLowerCase()
        });
    },
    //根据菜单选择的值来设置SubTotalsPosition位置
    totalsHandler: function(button, checked){
        if(!checked) {
            return;
        }

        // reconfigure the pivot grid with new settings
        this.getView().reconfigurePivot({
            rowGrandTotalsPosition: button.values.toLowerCase(),
            colGrandTotalsPosition: button.values.toLowerCase()
        });
    },

    onPivotGroupExpand: function(matrix, type, group){
        Ext.log( (group ? 'Group "' + group.name + '" expanded on ' : 'All groups expanded on ') + type);
    },

    onPivotGroupCollapse: function(matrix, type, group){
        Ext.log( (group ? 'Group "' + group.name + '" collapsed on ' : 'All groups collapsed on ') + type);
    },

    //数据渲染方式
    simpleData: function(){             //简化数据
        
        window.MyReportDataRenderer = 'simple';
        //重新渲染界面
        var thisView = this.getView();
        thisView.getStore().reload();
    },

    originalData: function(){           //原始数据
        window.MyReportDataRenderer = 'original';
        //重新渲染界面
        var thisView = this.getView();
        thisView.getStore().reload();
    },

    //数据渲染函数
    dataRenderer: function(value){
        if(window.MyReportDataRenderer == 'simple'){
            if(value>9999){
                return Math.floor(value/100)/100+'万';
            }
        }
        return value;
    },

    // exporter
    // exportToPivotXlsx: function () {
    //     this.doExport({
    //         type: 'pivotxlsx',
    //         matrix: this.getView().getMatrix(),
    //         title: '乐家-经营报表',
    //         fileName: 'ExportPivot.xlsx'
    //     });
    // },

    exportTo: function(btn){
        var cfg = Ext.merge({
            title: '乐家-经营报表',
            fileName: '乐家-经营报表' + (btn.cfg.onlyExpandedNodes ? 'Visible' : '') + '.' + (btn.cfg.ext || btn.cfg.type)
        }, btn.cfg);

        this.doExport(cfg)
    },

    doExport: function (config) {
        this.getView().saveDocumentAs(config).then(null, this.onError);
    },

    onError: function (error) {
        Ext.Msg.alert('Error', typeof error === 'string' ? error : 'Unknown error');
    },

    onBeforeDocumentSave: function (view) {
        // view.mask('Document is prepared for export. Please wait ...');
        view.mask('正在导出...');
    },

    onDocumentSave: function (view) {
        view.unmask();
    }


});
