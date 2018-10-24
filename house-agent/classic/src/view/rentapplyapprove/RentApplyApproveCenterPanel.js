Ext.define('HouseAgentSystem.view.rentapplyapprove.RentApplyApproveCenterPanel', {
    extend: 'Ext.container.Container',
    xtype: 'rentapplyApproveCenterPanel',
	layout:'fit',
	controller: 'rentapplyApproveViewController',
    viewModel : { type: 'rentapplyApproveViewModel'},
	items: [{xtype:'rentapplyApproveGrid'}]	//需要修改
});