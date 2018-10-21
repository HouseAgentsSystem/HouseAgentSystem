// Ext.define('Admin.model.report.ReportModel', function() {
//     var regions = {
//         "Belgium": 'Europe',
//         "Netherlands": 'Europe',
//         "United Kingdom": 'Europe',
//         "Canada": 'North America',
//         "United States": 'North America',
//         "Australia": 'Australia'
//     };

//     return {
//         extend: 'Admin.model.Base',

//         fields: [
//             {name: 'id',        type: 'int'},
//             {name: 'company',   type: 'string'},
//             {name: 'country',   type: 'string'},
//             {name: 'person',    type: 'string'},
//             {name: 'date',      type: 'date', dateFormat: 'c'},
//             {name: 'value',     type: 'float', allowNull: true},
//             {name: 'number',  type: 'float', allowNull: true},
//             {
//                 name: 'year',
//                 calculate: function(data){
//                     return parseInt(Ext.Date.format(data.date, "Y"), 10);
//                 }
//             },{
//                 name: 'month',
//                 calculate: function(data){
//                     return parseInt(Ext.Date.format(data.date, "m"), 10) - 1;
//                 }
//             }
//             ,{
//                 name: 'continent',
//                 calculate: function(data){
//                     return regions[data.country];
//                 }
//             }
//         ]
//     };
// });

Ext.define('HouseAgentSystem.model.report.ReportModel', {
    extend: 'HouseAgentSystem.model.Base',

    fields: [
        {name: 'storeName',   type: 'string'},                      //门店名字
        // {name: 'country',   type: 'string'},
        {name: 'staffName',    type: 'string'},                     //员工名
        {name: 'saleDate',      type: 'date', dateFormat: 'c'},     //销售日期
        {name: 'actualPrice',     type: 'float', allowNull: true},  //实际售价
        {name: 'agencyFees',  type: 'float', allowNull: true},      //中介费
        {
            name: 'year',
            calculate: function(data){
                return parseInt(Ext.Date.format(data.saleDate, "Y"), 10);
            }
        },{
            name: 'month',
            calculate: function(data){
                return parseInt(Ext.Date.format(data.saleDate, "m"), 10) - 1;
            }
        }
        // ,{
        //     name: 'continent',
        //     calculate: function(data){
        //         return regions[data.country];
        //     }
        // }
    ]
});