Ext.define('HouseAgentSystem.view.staff.Staff', {
    extend: 'Ext.container.Container',
    xtype: 'staff',
 
    controller: 'staffViewController',
    viewModel: {type: 'staffViewModel'},
    	
    layout: 'fit',
    items: [{xtype:'staffPanel'}]
});
