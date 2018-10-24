Ext.define('HouseAgentSystem.Application', {
    extend: 'Ext.app.Application',
    
    name: 'HouseAgentSystem',

    stores: [
        'NavigationTree',
        'NavigationAdminTree',
        'NavigationEmploeeTree',
        'NavigationManagerTree'
    ],

    defaultToken : 'dashboard',

    // The name of the initial view to create. This class will gain a "viewport" plugin
    // if it does not extend Ext.Viewport.
    //
    mainView: 'HouseAgentSystem.view.main.Main',

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
