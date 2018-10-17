Ext.define('HouseAgentSystem.view.house.House', {
    extend: 'Ext.container.Container',
    xtype: 'house',

    requires: [],

    controller: 'houseController',
    viewModel: {
        type: 'houseModel'
    },

    layout: 'fit',

    items: [
        {xtype: 'housePanel'}
    ]
});
