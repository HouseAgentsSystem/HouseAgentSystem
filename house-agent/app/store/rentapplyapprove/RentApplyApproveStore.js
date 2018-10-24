Ext.define('HouseAgentSystem.store.rentapplyapprove.RentApplyApproveStore', {
    extend: 'Ext.data.Store',
    storeId:'rentapplyApproveStore',
    alias: 'store.rentapplyApproveStore',
    model: 'HouseAgentSystem.model.rentapplyapprove.RentApplyApproveModel',
    //pageSize: 25,
    proxy: {
        type: 'ajax',
        url: '/rentapply/tasks', 			//需要修改
        reader : new Ext.data.JsonReader({  
            type : 'json',  
            rootProperty  : 'content',
            totalProperty : 'totalElements'
        })
        ,simpleSortMode: true
    },
    remoteSort: true,
    sorters: [{ property: 'id',direction: 'desc'}],
    autoLoad: true
});	