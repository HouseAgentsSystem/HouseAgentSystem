Ext.define('HouseAgentSystem.store.ranking.StaffRankingStore', {
	extend: 'Ext.data.Store',
	storeId:'staffRankingStore',
	alias: 'store.staffRankingStore',
	model:'HouseAgentSystem.model.ranking.StaffRankingModel',
	// 连接后台数据
	proxy: {
		// type: 'rest',
		type : 'ajax',
		// url: '/userInfo/userDel',
		url: '/tradeRanking/staff',
		reader:{
			type:'json',
			rootProperty:'content',//对应后台返回的结果集名称
		},
		writer: {
			type: 'json'
		}
	},
	autoLoad: true,
	autoSync: true
	
	//本地假数据，测试
	// data:{
	// 	'lists':[
	// 		{
 //            	"total": 1200,
 //            	"staffName": "小蓝",
 //                "storeName":"蓝哥店"
 //            },
 //        	{
 //           	 	"total": 3000,
 //           		 "staffName": "小黄",
 //                 "storeName":"黄哥店"
 //        	},
 //        	{
 //            	"total": 2000,
 //            	"staffName": "小白"
 //        	},
 //        	{
 //           	 	"total": 3000,
 //           		 "staffName": "小绿"
 //        	},
 //        	{
 //            	"total": 2000,
 //            	"staffName": "小白"
 //        	},
 //        	{
 //           	 	"total": 3000,
 //           		 "staffName": "小菜"
 //        	},
 //        	{
 //            	"total": 2000,
 //            	"staffName": "小1"
 //        	},{
 //           	 	"total": 3000,
 //           		 "staffName": "小2"
 //        	},
 //        	{
 //            	"total": 2000,
 //            	"staffName": "小3"
 //        	}
 //        ]
	// },
	// proxy: {
 //        type: 'memory',
 //        //url: '~api/search/users'  //mvc url  xxx.json
 //        reader:{
 //            type:'json',
 //            rootProperty:'lists'
 //        }
 //    },
	// autoLoad: true
	
});
