Ext.define('HouseAgentSystem.view.rentapplyapprove.RentApplyApproveGrid', {
    extend: 'Ext.grid.Panel',
	xtype:'rentapplyApproveGrid',
	title: '租房审核',		//需要修改
	iconCls: 'fa-arrow-circle-o-up',
	bind: '{rentapplyApproveStore}',//调用组件4
	columns: [{
			xtype: 'actioncolumn',
			items: [{
				xtype: 'button',
				iconCls: 'x-fa fa-pencil',
				tooltip: '签收任务',
				getClass: function(v, meta, rec) {
		            if (rec.get('assignee')!='') {
		                  return 'x-hidden';
		            }
		            return 'x-fa fa-pencil';
				},
				handler: 'onClickRentApplyApproveClaimButton'	//ajax  taskId
			},{
				xtype: 'button',
				iconCls: 'x-fa fa-edit',
				tooltip: '审批任务',
				getClass: function(v, meta, rec) {
		            if (rec.get('assignee')=='') {
		                  return 'x-hidden';
		            }
		            return 'x-fa fa-edit';
				},
				handler: 'onClickRentApplyApproveCompleteWindowButton'	//taskDefinitionKey 动态表单
			},{
				xtype: 'button',
				iconCls: 'x-fa fa-edit',
				tooltip: '详情',
				handler: 'onClickRentApplyDetailsWindowButton'	//taskDefinitionKey 动态表单
			}],
			cls: 'content-column',
			width: 120,
			dataIndex: 'bool',
			text: 'Actions',
			tooltip: 'edit '
		}
		,{header: 'id' 			,dataIndex: 'id',width: 60,sortable: true	,hidden:true}
		,{header: 'state',dataIndex: 'state',width: 60,sortable: true,
            renderer: function(val) {
	            if (val =='APPROVAL') {
		            return '<span style="color:green;">正在审核</span>';
		        } else if (val =='REFUSE') {
		            return '<span style="color:blue;">审核不通过</span>';
		        } else if (val =='CANCEL') {
		            return '<span style="color:orange;">取消申请</span>';
		        }else{
		        	return '<span style="color:red;">审核通过</span>';
		        }
		        return val;
            }
		}
		,{header: 'userId'  ,dataIndex: 'userId',width: 60,sortable: true}
		,{header: 'title' 	,dataIndex: 'title',width: 150,sortable: true}
		,{header: 'rent' 	,dataIndex: 'rent',width: 150,sortable: true}
		,{header: 'applyTime' 	,dataIndex: 'applyTime',width: 150,sortable: true,renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')}
		,{header: 'isEntireRent'  	,dataIndex: 'isEntireRent',width: 80,sortable: true,
            renderer: function(val) {
	            if (val ==true) {
		            return '<span style="color:blue;">整租</span>';
		        } else {
		            return '<span style="color:yellow;">合租</span>';
		        }
		        return val;
            }
        }
	]
	// ,dockedItems: [{
	//     xtype: 'pagingtoolbar',
	//     dock: 'bottom',
	// 	bind: '{rentapplyApproveStore}',	//调用组件4
	//     displayInfo: true,
	//     items: ['-',{
 //            text: 'Add',
 //            iconCls: 'x-fa fa-plus',
	// 		listeners: {
	// 			click: 'onClickRentApplyApproveGridAddButton'
 //            }
 //        }, '-',{
 //            text: 'Update',
 //            iconCls: 'x-fa fa-pencil',
 //            listeners: {
	// 			click: 'onClickRentApplyApproveGridUpdateButton'
 //            }
 //        }, '-', {
 //            text: 'Delete',
 //            iconCls: 'x-fa fa-close',
	// 		listeners: {
	// 			click: 'onClickRentApplyApproveGridDeleteButton'
 //            }
 //        }]
	// }]
});
