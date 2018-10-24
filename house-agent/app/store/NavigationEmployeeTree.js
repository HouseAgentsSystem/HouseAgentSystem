Ext.define('HouseAgentSystem.store.NavigationEmploeeTree', {
    extend: 'Ext.data.TreeStore',

    storeId: 'NavigationEmploeeTree',

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
                text: '房源管理模块',
                iconCls: 'x-fa fa-home',
                //rowCls: 'nav-tree-badge nav-tree-badge-new',
                viewType: 'house',
                leaf: true
            },{
                text: '交易管理模块',
                iconCls: 'x-fa fa-bar-chart',
                viewType: 'trade',
                leaf: true
            },{
               text: '日历',
               iconCls: 'fa fa-calendar',
               viewType: 'calendar-days-view',
               leaf: true
           },{
               text: '个人信息',
               iconCls: 'fa fa-users',
               viewType: 'staffInfo',
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
