Ext.define('HouseAgentSystem.view.user.User', {
    extend: 'Ext.container.Container',
    xtype: 'user',
 
    controller: 'userViewController',
    viewModel: {type: 'userViewModel'},
    requires: [
    	"Ext.layout.container.Center"
    ],
    layout: 'fit',
    items: [{xtype:'userPanel'}]
});
