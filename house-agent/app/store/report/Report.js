// Ext.define('Admin.store.report.Report', {
//   extend: 'Ext.data.Store',
//   alias: 'store.report',
//   model:'Admin.model.report.ReportModel',
//   storeId: 'ReportPivotStore',
//   proxy: {
//     type: 'rest',
//     url: '/report',
//     reader:{
//       type:'json',
//       rootProperty:'content',//对应后台返回的结果集名称
//       totalProperty: 'totalElements'//分页需要知道总记录数
//     },
//     writer: {
//       type: 'json'
//     },
//     simpleSortMode: true  //简单排序模式
//   },
//   autoLoad: true,
//   autoSync: true,
//   remoteSort: true,//全局排序
//   pageSize: 20,
//   sorters: {
//     direction: 'DESC',property: 'id'
//   }
// });


Ext.define('HouseAgentSystem.store.report.Report', {
    extend: 'Ext.data.Store',
    alias: 'store.report',

    model: 'HouseAgentSystem.model.report.ReportModel',
    //注意日期格式
    // data: { items: [
    //     { id: '1', company: "公司1", person: "人员1", value:"5001", number:"11", date:"2018-09-04" },
    //     { id: '2', company: "公司1", person: "人员1", value:"5001", number:"12", date:"2018-09-05" },
    //     { id: '3', company: "公司1", person: "人员2", value:"5005", number:"13", date:"2019-10-04" },
    //     { id: '4', company: "公司1", person: "人员3", value:"5006", number:"14", date:"2018-11-04" },
    //     { id: '5', company: "公司2", person: "人员4", value:"5002", number:"15", date:"2018-09-04" },
    //     { id: '6', company: "公司3", person: "人员5", value:"5003", number:"16", date:"2018-09-04" },
    //     { id: '7', company: "公司4", person: "人员6", value:"5004", number:"17", date:"2018-09-04" }
    // ]},

    proxy: {
        // load using HTTP
        type: 'ajax',
        limitParam: null,
        url: '/report/findAll',
        // the return will be JSON, so lets set up a reader
        reader: {
            type: 'json',
            rootProperty:'content',//对应后台返回的结果集名称
        },
        writer: {
            type: 'json'
        }

    },
    autoLoad: true

    // proxy: {
    //     type: 'memory',
    //     reader: {
    //         type: 'json',
    //         rootProperty: 'items'
    //     }
    // }

});