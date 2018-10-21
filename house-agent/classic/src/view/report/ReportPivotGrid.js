Ext.define('HouseAgentSystem.view.report.ReportPivotGrid', {
    extend: 'Ext.pivot.Grid',
    xtype: 'report-pivot-grid',
    controller: 'report',

    requires: [
        'HouseAgentSystem.store.report.Report',
        'HouseAgentSystem.view.report.ReportController',
        'Ext.pivot.plugin.Exporter',
        'Ext.pivot.plugin.Configurator'
    ],

    title: '经营数据报表',
    width: 750,
    height: Ext.Element.getViewportHeight() - 64,
    collapsible: true,
    multiSelect: true,

    selModel: {
        type: 'cellmodel'
    },

    // Set this to false if multiple dimensions are configured on leftAxis and
    // you want to automatically expand the row groups when calculations are ready.
    startRowGroupsCollapsed: false,

    // Set this to false if multiple dimensions are configured on topAxis and
    // you want to automatically expand the col groups when calculations are ready.
    startColGroupsCollapsed: false,
    //适当删去一些，具体没试过怎么删
    plugins: {
        pivotexporter: true,
        pivotconfigurator: {
            id: 'configurator',
            // It is possible to configure a list of fields that can be used to
            // configure the pivot grid.
            // If no fields list is supplied then all fields from the Store model
            // are fetched automatically
            fields: [{
                dataIndex: 'actualPrice',
                header: '销售额',
                // You can even provide a default aggregator function to be used
                // when this field is dropped
                // on the agg dimensions
                aggregator: 'min',
                formatter: 'number("0")',

                settings: {
                    // Define here in which areas this field could be used
                    allowed: ['aggregate'],
                    // Set a custom style for this field to inform the user that
                    // it can be dragged only to "Values"
                    style: {
                        fontWeight: 'bold'
                    },
                    // Define here custom formatters that ca be used on this dimension
                    formatters: {
                        '0': 'number("0")',
                        '0%': 'number("0%")'
                    }
                }
            }, {
                dataIndex: 'agencyFees',
                header: '中介费',

                settings: {
                    // Define here in which areas this field could be used
                    allowed: 'aggregate',
                    // Define here what aggregator functions can be used when
                    // this field is used as an aggregate dimension
                    aggregators: ['sum', 'avg', 'count'],
                    // Set a custom style for this field to inform the user that
                    // it can be dragged only to "Values"
                    style: {
                        fontWeight: 'bold'
                    },
                    // Define here custom renderers that can be used on this dimension
                    renderers: {
                        'Colored 0,000.00': 'coloredRenderer'
                    },
                    // Define here custom formatters that ca be used on this dimension
                    formatters: {
                        '0': 'number("0")',
                        '0.00': 'number("0.00")',
                        '0,000.00': 'number("0,000.00")',
                        '0%': 'number("0%")',
                        '0.00%': 'number("0.00%")'
                    }
                }
            },  {
                dataIndex: 'storeName',
                header: '门店',

                settings: {
                    // Define here what aggregator functions can be used when
                    // this field is used as an aggregate dimension
                    aggregators: ['count']
                }
            }, {
                dataIndex: 'staffName',
                header: '员工',

                settings: {
                    // Define here what aggregator functions can be used
                    // when this field is used as an aggregate dimension
                    aggregators: ['count']
                }
            }, {
                dataIndex: 'year',
                header: 'Year',

                settings: {
                    // Define here what aggregator functions can be used
                    // when this field is used as an aggregate dimension
                    aggregators: ['count'],
                    // Define here in which areas this field could be used
                    allowed: ['leftAxis', 'topAxis']
                }
            }, {
                dataIndex: 'month',
                header: 'Month',
                labelRenderer: 'monthLabelRenderer',

                settings: {
                    // Define here what aggregator functions can be used when this
                    // field is used as an aggregate dimension
                    aggregators: ['count'],
                    // Define here in which areas this field could be used
                    allowed: ['leftAxis', 'topAxis']
                }
            }]
        }
    },
    //重点
    matrix: {
        type: 'local',
        store: { 
            type: 'report'
        },

        // change the text of the column generated for all left axis dimensions
        textRowLabels: '员工',
        // change the width of the column generated for all left axis dimensions
        compactViewColumnWidth: 210,
        // Set layout type to "compact"
        viewLayoutType: 'outline',

        // Configure the aggregate dimensions. Multiple dimensions are supported.
        //设置统计的地方,平均值、总和等等。
        aggregate: [{
            dataIndex: 'actualPrice',
            header: '房产额',
            aggregator: 'sum',
            width: 90,
            renderer: 'dataRenderer'
        },{
            dataIndex: 'agencyFees',
            header: '中介费',
            aggregator: 'sum',
            width: 90,
            renderer: 'dataRenderer'
        }],

        // Configure the left axis dimensions that will be used to generate
        // the grid rows
        //左
        leftAxis: [{
            dataIndex: 'storeName',
            header: '分店'
        }, {
            dataIndex: 'staffName',
            header: '员工',
            sortable: false
        }],

        /**
         * Configure the top axis dimensions that will be used to generate
         * the columns.
         *
         * When columns are generated the aggregate dimensions are also used.
         * If multiple aggregation dimensions are defined then each top axis
         * result will have in the end a column header with children columns
         * for each aggregate dimension defined.
         */
         //上
        topAxis: [{
            dataIndex: 'year',
            header: 'Year'
        }, {
            dataIndex: 'month',
            header: 'Month',
            labelRenderer: 'monthLabelRenderer'     //将month转化为英文月份显示
        }]
    },
    //展开&缩起
    listeners: {
        pivotgroupexpand: 'onPivotGroupExpand',
        pivotgroupcollapse: 'onPivotGroupCollapse'
    },
 
    tbar: [{
        text: '折叠',
        menu: [{
            text: '全部折叠',
            handler: 'collapseAll'
        },{
            text: '全部展开',
            handler: 'expandAll'
        }]
    },{
        //Subtotals 位置
        text: '子统计位置',
        menu: {
            defaults: {
                xtype: 'menucheckitem',
                group:  'subtotals',
                checkHandler: 'subtotalsHandler'
            },
            items: [{
                text: '前',
                values: 'First',
                checked: true
            },{
                text: '后',
                values: 'Last'
            },{
                text: '不显示',
                values: 'None'
            }]
        }
    },{
        //Totals 位置
        text: '统计位置',
        menu: {
            defaults: {
                xtype: 'menucheckitem',
                group:  'totals',
                checkHandler: 'totalsHandler'
            },
            items: [{
                text: '前',
                values: 'First',
                checked: true
            },{
                text: '后',
                values: 'Last'
            },{
                text: '不显示',
                values: 'None'
            }]
        }
    },{
        text: '数据渲染',
        menu: [{
            text: '原始数据',
            handler: 'originalData'
        },{
            text: '简化数据',
            handler: 'simpleData'
        }]
    }],

    listeners: {
        // this event notifies us when the document was saved
        documentsave: 'onDocumentSave',
        beforedocumentsave: 'onBeforeDocumentSave'
    },
    header: {
        itemPosition: 1, // after title before collapse tool
        items: [{
            ui: 'default-toolbar',
            xtype: 'button',
            text: '导出 ...',
            menu: {
                defaults: {
                    handler: 'exportTo'
                },
                items: [{
                    text:   'Excel xlsx (all items)',
                    cfg: {
                        type: 'excel07',
                        ext: 'xlsx'
                    }
                },{
                    text:   'Excel xlsx (visible items)',
                    cfg: {
                        type: 'excel07',
                        onlyExpandedNodes: true,
                        ext: 'xlsx'
                    }
                },{
                    text: 'Excel xml (all items)',
                    cfg: {
                        type: 'excel03',
                        ext: 'xml'
                    }
                },{
                    text:   'Excel xml (visible items)',
                    cfg: {
                        type: 'excel03',
                        onlyExpandedNodes: true,
                        ext: 'xml'
                    }
                },{
                    text:   'HTML (all items)',
                    cfg: {
                        type: 'html'
                    }
                },{
                    text:   'HTML (visible items)',
                    cfg: {
                        type: 'html',
                        onlyExpandedNodes: true
                    }
                }]
            }
        }]
    }
});