Ext.define('HouseAgentSystem.model.rentapplyapprove.RentApplyApproveModel', {
    extend: 'HouseAgentSystem.model.Base',
    fields: [	//需要修改
		 {type: 'int' ,name: 'id'}
		,{type: 'string' ,name: 'region'}//地区
		,{type: 'float',name: 'rent'}  //月租金
        ,{type: 'float',name: 'area'}   //面积
		,{type: 'int' ,name: 'room'}     //室
        ,{type: 'int' ,name: 'hall'}      //厅
        ,{type: 'int' ,name: 'floor'}       //楼层
        ,{type: 'string' ,name: 'title'}    //标题=地区+几室+几厅+有无卫生间+是否整租-----------------------
		,{type: 'string' ,name: 'address'}    //详细地址
        ,{type: 'string' ,name: 'images'}   //图片
        ,{type: 'string' ,name: 'video'}    //视频

        ,{type: 'boolean' ,name: 'isEntireRent'}//是否整租-----------------------
        ,{type: 'boolean' ,name: 'isbed'}   //is床
        ,{type: 'boolean' ,name: 'isWasher'}//is洗衣机
        ,{type: 'boolean' ,name: 'isAirConditioning'}//Is空调
        ,{type: 'boolean' ,name: 'isBalcony'}   //is阳台
        ,{type: 'boolean' ,name: 'isRefrigerator'}  //Is冰箱
        ,{type: 'boolean' ,name: 'isToilet'}    //Is厕所
        ,{type: 'boolean' ,name: 'isKitchen'}   //Is厨房
        ,{type: 'boolean' ,name: 'isCalorifier'}//is热水器

        ,{type: 'string' ,name: 'introduce'}    // 详细介绍
        ,{type: 'date' ,name: 'applyTime'}    // 申请日期-----------------------
        ,{type: 'string' ,name: 'state'}    // 状态-----------------------
        ,{type: 'string' ,name: 'userId'}   //申请人-----------------------
        ,{type: 'string' ,name: 'taskId'}
        ,{type: 'string' ,name: 'assignee'} //经办人
        // ,{type: 'date' ,name: 'taskCreateTime'}
        ,{type: 'string' ,name: 'taskDefinitionKey'}
    ]
});