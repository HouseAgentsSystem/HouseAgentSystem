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
                text: 'Dashboard',
                iconCls: 'x-fa fa-desktop',
                rowCls: 'nav-tree-badge nav-tree-badge-new',
                viewType: 'HouseAgentSystemdashboard',
                routeId: 'dashboard', // routeId defaults to viewType
                leaf: true
            },{
                text: '门店管理模块',
                iconCls: 'x-fa fa-address-card',
                //rowCls: 'nav-tree-badge nav-tree-badge-new',
                viewType: 'store',
                leaf: true
            },{
                text: '房源管理模块',
                iconCls: 'x-fa fa-home',
                //rowCls: 'nav-tree-badge nav-tree-badge-new',
                viewType: 'house',
                leaf: true
            },
            // {
            //     text: '租房申请管理模块',
            //     iconCls: 'x-fa fa-address-card',
            //     viewType: 'rentapplyCenterPanel',
            //     leaf: true
            // },
            {
                text: '租房申请审批模块',
                iconCls: 'x-fa fa-address-card',
                viewType: 'rentapplyApproveCenterPanel',
                leaf: true
            },{
                text: '管理员查看员工排名',
                iconCls: 'x-fa fa-bar-chart',
                viewType: 'adminStaffRanking',
                leaf: true
            },{
                text: '门店排名',
                iconCls: 'x-fa fa-bar-chart',
                viewType: 'storeRnking',
                leaf: true
            },{
                text: '交易管理模块',
                iconCls: 'x-fa fa-bar-chart',
                viewType: 'trade',
                leaf: true
            },{
                text: '报表',
                iconCls: 'x-fa fa-apple',
                rowCls: 'nav-tree-badge',
                viewType: 'report',
                routeId: 'report', // routeId defaults to viewType
                leaf: true
            },
            {
                text: 'Pages',
                iconCls: 'x-fa fa-leanpub',
                expanded: false,
                selectable: false,
                //routeId: 'pages-parent',
                //id: 'pages-parent',

                children: [
                    {
                        text: 'Blank Page',
                        iconCls: 'x-fa fa-file-o',
                        viewType: 'pageblank',
                        leaf: true
                    },

                    {
                        text: '404 Error',
                        iconCls: 'x-fa fa-exclamation-triangle',
                        viewType: 'page404',
                        leaf: true
                    },
                    {
                        text: '500 Error',
                        iconCls: 'x-fa fa-times-circle',
                        viewType: 'page500',
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
                    },
                    {
                        text: 'Register',
                        iconCls: 'x-fa fa-pencil-square-o',
                        viewType: 'register',
                        leaf: true
                    },
                    {
                        text: 'Password Reset',
                        iconCls: 'x-fa fa-lightbulb-o',
                        viewType: 'passwordreset',
                        leaf: true
                    }
                ]
            }
        ]
    }
});
