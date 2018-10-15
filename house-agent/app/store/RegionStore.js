Ext.define('HouseAgentSystem.store.RegionStore', {
	extend: 'Ext.data.Store',
	storeId:'regionStore',
	alias: 'store.regionStore',
	model:'HouseAgentSystem.model.RegionModel',

	data: {
		'regions': [{
	        "name": '全市',
	        "value": ''
	    },
		{
	        "name": '松山湖',
	        "value": '松山湖'
	    },
	    {
	        "name": '莞城',
	        "value": '莞城'
	    },
	    {
	        "name": '大岭山',
	        "value": '大岭山'
	    },
	    {
	        "name": '虎门',
	        "value": '虎门'
	    },
	    {
	        "name": '长安',
	        "value": '长安'
	    },
	    {
	        "name": '大朗',
	        "value": '大朗'
	    },
	    {
	        "name": '厚街',
	        "value": '厚街'
	    },
	    {
	        "name": '南城',
	        "value": '南城'
	    },
	    {
	        "name": '樟木头',
	        "value": '樟木头'
	    },
	    {
	        "name": '塘厦',
	        "value": '塘厦'
	    },
	    {
	        "name": '东城',
	        "value": '东城'
	    },
	    {
	        "name": '常平',
	        "value": '常平'
	    },
	    {
	        "name": '凤岗',
	        "value": '凤岗'
	    },
	    {
	        "name": '万江',
	        "value": '万江'
	    },
	    {
	        "name": '寮步',
	        "value": '寮步'
	    },
	    {
	        "name": '黄江',
	        "value": '黄江'
	    },
	    {
	        "name": '沙田',
	        "value": '沙田'
	    },
	    {
	        "name": '清溪',
	        "value": '清溪'
	    },
	    {
	        "name": '中堂',
	        "value": '中堂'
	    },
	    {
	        "name": '桥头',
	        "value": '桥头'
	    },
	    {
	        "name": '石龙',
	        "value": '石龙'
	    },
	    {
	        "name": '石碣',
	        "value": '石碣'
	    },
	    {
	        "name": '横沥',
	        "value": '横沥'
	    },
	    {
	        "name": '道滘',
	        "value": '道滘'
	    },
	    {
	        "name": '茶山',
	        "value": '茶山'
	    },
	    {
	        "name": '麻涌',
	        "value": '麻涌'
	    },
	    {
	        "name": '石排',
	        "value": '石排'
	    },
	    {
	        "name": '东坑',
	        "value": '东坑'
	    },
	    {
	        "name": '高埗',
	        "value": '高埗'
	    },
	    {
	        "name": '企石',
	        "value": '企石'
	    },
	    {
	        "name": '谢岗',
	        "value": '谢岗'
	    },
	    {
	        "name": '洪梅',
	        "value": '洪梅'
	    },
	    {
	        "name": '望牛墩',
	        "value": '望牛墩'
	    }]
	},

	proxy: {
		type: 'memory',
		reader: {
			type: 'json',
			rootProperty: 'regions'
		}
	},
	autoLoad: true
});
