Ext.define('HouseAgentSystem.store.NavigationAdminTree', {
    extend: 'Ext.data.TreeStore',

    storeId: 'NavigationAdminTree',

    fields: [{
        name: 'text'
    }],

    root: {
        expanded: true,
        children: [
            {
                text: '经营总览',
                iconCls: 'x-fa fa-bar-chart',
                expanded: false,
                selectable: false,
                children: [
                    {
                        text: '员工排名',
                        iconCls: 'x-fa fa-bar-chart',
                        viewType: 'adminStaffRanking',
                        leaf: true
                    },{
                        text: '门店排名',
                        iconCls: 'x-fa fa-bar-chart',
                        viewType: 'storeRanking',
                        leaf: true
                    },{
                        text: '报表',
                        iconCls: 'x-fa fa-apple',
                        rowCls: 'nav-tree-badge',
                        viewType: 'report',
                        routeId: 'report', // routeId defaults to viewType
                        leaf: true
                    }
                ]
            },{
                text: '租房申请审批模块',
                iconCls: 'x-fa fa-address-card',
                viewType: 'rentapplyApproveCenterPanel',
                leaf: true
            },{
                text: '门店管理模块',
                iconCls: 'x-fa fa-address-card',
                //rowCls: 'nav-tree-badge nav-tree-badge-new',
                viewType: 'store',
                leaf: true
            },
            // {
            //     text: '租房申请管理模块',
            //     iconCls: 'x-fa fa-address-card',
            //     viewType: 'rentapplyCenterPanel',
            //     leaf: true
            // },
            
                

            {
                text: '交易管理模块',
                iconCls: 'x-fa fa-bar-chart',
                viewType: 'trade',
                leaf: true
            },{
               text: '客户模块',
               iconCls: 'fa fa-user',
               viewType: 'user',
               leaf: true
           },
            {
                text: 'Pages',
                iconCls: 'x-fa fa-leanpub',
                hidden: true,
                expanded: false,
                selectable: false,
                //routeId: 'pages-parent',
                //id: 'pages-parent',

                children: [
                    {
                        text: '404 Error',
                        iconCls: 'x-fa fa-exclamation-triangle',
                        viewType: 'page404',
                        leaf: true
                    },
                    {
                        text: 'Lock Screen',
                        iconCls: 'x-fa fa-lock',
                        viewType: 'lockscreen',
                        leaf: true
                    },

                    {
                        text: 'Login',
                        iconCls: 'x-fa fa-check',
                        viewType: 'login',
                        leaf: true
                    }
                ]
            }
        ]
    }
});
