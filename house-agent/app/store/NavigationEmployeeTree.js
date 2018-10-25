﻿Ext.define('HouseAgentSystem.store.NavigationEmploeeTree', {
    extend: 'Ext.data.TreeStore',

    storeId: 'NavigationEmploeeTree',

    fields: [{
        name: 'text'
    }],

    root: {
        expanded: true,
        children: [
            {
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
               text: '日程管理',
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
                hidden: true,
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
