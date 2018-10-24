Ext.define('HouseAgentSystem.store.NavigationTree', {
    extend: 'Ext.data.TreeStore',

    storeId: 'NavigationTree',

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
            },{
                text: '管理员查看员工排名',
                iconCls: 'x-fa fa-bar-chart',
                viewType: 'adminStaffRanking',
                leaf: true
            },{
                text: '员工排名',
                iconCls: 'x-fa fa-bar-chart',
                viewType: 'staffRanking',
                leaf: true
            },{
                text: '门店排名',
                iconCls: 'x-fa fa-bar-chart',
                viewType: 'storeRanking',
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
            },{
               text: '日历',
               iconCls: 'fa fa-calendar',
               viewType: 'calendar-days-view',
               leaf: true
           },{
               text: '员工管理',
               iconCls: 'fa fa-users',
               viewType: 'staff',
               leaf: true
           },{
               text: '个人信息',
               iconCls: 'fa fa-users',
               viewType: 'staffInfo',
               leaf: true
           },{
               text: '客户模块',
               iconCls: 'fa fa-user',
               viewType: 'user',
               leaf: true
           }
        ]
    }
});
