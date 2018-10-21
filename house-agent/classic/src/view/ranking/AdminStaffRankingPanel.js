Ext.define('HouseAgentSystem.view.ranking.AdminStaffRankingPanel', {
    extend: 'HouseAgentSystem.view.ranking.ChartBase',
    xtype: 'adminStaffRankingPanel',

    requires: [
        'Ext.chart.CartesianChart',
        'Ext.chart.axis.Category',
        'Ext.chart.axis.Numeric',
        'Ext.chart.series.Bar'
    ],

    // title: 'Bar Chart',
    // iconCls: 'x-fa fa-bar-chart',
    // title: '经营数据报表',
    items: [{
        xtype: 'cartesian',  //笛卡尔坐标的图表
        reference: 'chart',
        captions: {
            title: '员工排名 - 营业额'
            // credits: {
            //     text: 'Data: Browser Stats 2012\n' +
            //     'Source: http://www.w3schools.com/',
            //     align: 'left'
            // }
        },
        scrollable:'x',
        colors: [
            '#6aa5db'
        ],
        bind: '{staffBarData}',
        legend: {
            type: 'sprite',
            docked: 'bottom'
        },
        axes: [{                //轴线
            type: 'category',  //有限集合
            fields: [
                'staffName'
            ],
            position: 'bottom'
        },{
            type: 'numeric',   //连续数字
            fields: [
                'total',
            ],
            grid: {
                odd: {
                    fill: '#e8e8e8'
                }
            },
            position: 'left'
        }],
        series: [{
            type: 'bar',
            title: ['销售额'],
            xField: 'staffName'
            ,yField: [
                'total'
            ],
            stacked: false,
            style: {
                opacity: 0.80
            },
            highlight: {
                fillStyle: '#00DDDD'
            },
            tooltip: {
                trackMouse: true,
                renderer: 'onBarTipRender2'
            }
        }],

        tbar:[{
                xtype: 'combobox',
                reference:'searchFieldName1',
                hideLabel: true,
                store:Ext.create("Ext.data.Store", {
                    fields: ["name", "value"],
                    data: [
                        { name: '一月', value: '1' },
                        { name: '二月', value: '2' },
                        { name: '三月', value: '3' },
                        { name: '四月', value: '4' },
                        { name: '五月', value: '5' },
                        { name: '六月', value: '6' },
                        { name: '七月', value: '7' },
                        { name: '八月', value: '8' },
                        { name: '九月', value: '9' },
                        { name: '十月', value: '10' },
                        { name: '十一月', value: '11' },
                        { name: '十二月', value: '12' }
                    ]
                }),
                displayField: 'name',
                valueField:'value',
                value:'1',
                editable: false,
                queryMode: 'local',
                triggerAction: 'all',
                emptyText: 'Select a month...',
                width: 135,
                listeners:{
                    select: 'searchCombobox'
                }
            },{
                xtype: 'combobox',
                reference:'searchFieldName2',
                // bind: '{storeLists}',
                hideLabel: true,
                // store:'storeGridStore',
                store: Ext.create("HouseAgentSystem.store.store.StoreGridStore"),
                displayField: 'storeName',
                valueField:'id',
                editable: false,
                queryMode: 'local',
                triggerAction: 'all',
                emptyText: 'Select a store...',
                width: 135,
                listeners:{
                    beforeRender:'addExtraData',
                    select: 'searchCombobox'
                }
            }]

    }]
});
