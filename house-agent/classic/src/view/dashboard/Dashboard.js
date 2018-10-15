Ext.define('HouseAgentSystem.view.dashboard.Dashboard', {
    extend: 'Ext.container.Container',
    xtype: 'HouseAgentSystemdashboard',

    requires: [
        'Ext.ux.layout.ResponsiveColumn'
    ],

    controller: 'dashboard',
    viewModel: {
        type: 'dashboard'
    },

    layout: 'responsivecolumn',
    
    listeners: {
        hide: 'onHideView'
    },
	html:'HouseAgentSystemdashboard'
    //items: []
});
