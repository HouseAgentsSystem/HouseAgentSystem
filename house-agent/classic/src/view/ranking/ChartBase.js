Ext.define('HouseAgentSystem.view.ranking.ChartBase', {
    extend: 'Ext.Panel',

    height: 500,
    ui: 'light',
    layout: 'fit',

    platformConfig: {
        classic: {
            cls: 'quick-graph-panel shadow',
            headerPosition: 'bottom'
        }
    },

    defaults: {
        width: '100%'
    }
});
