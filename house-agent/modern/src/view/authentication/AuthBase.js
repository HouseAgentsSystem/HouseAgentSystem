Ext.define('HouseAgentSystem.view.authentication.AuthBase', {
    extend: 'Ext.Panel',
    controller: 'auth',

    requires: [
        'Ext.layout.VBox'
    ],

    baseCls: 'auth-locked',

    layout: {
        type: 'vbox',
        align: 'center',
        pack: 'center'
    }
});
