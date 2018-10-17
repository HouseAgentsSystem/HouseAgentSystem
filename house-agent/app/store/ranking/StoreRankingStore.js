Ext.define('HouseAgentSystem.store.ranking.StoreRankingStore', {
	extend: 'Ext.data.Store',
	storeId:'storeRankingStore',
	alias: 'store.storeRankingStore',
	model:'HouseAgentSystem.model.ranking.StoreRankingModel',
	// 连接后台数据
	proxy: {
		// type: 'rest',
		type : 'ajax',
		// url: '/userInfo/userDel',
		url: '/admin/tradeRanking/store',
		reader:{
			type:'json',
			rootProperty:'content',//对应后台返回的结果集名称
		},
		writer: {
			type: 'json'
		}
	},
	autoLoad: true,
	// autoSync: true
	
	//本地假数据，测试
	// data:{
	// 	'lists':[
	// 		{
 //            	"total": 1200,
 //            	"staffName": "小黄"
 //            },
 //        	{
 //           	 	"total": 3000,
 //           		 "staffName": "小黄"
 //        	},
 //        	{
 //            	"total": 2000,
 //            	"staffName": "小白"
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
