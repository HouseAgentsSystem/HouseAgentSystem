/*
 * This file launches the application by asking Ext JS to create
 * and launch() the Application class.
 */
Ext.application({
    extend: 'HouseAgentSystem.Application',

    name: 'HouseAgentSystem',

    requires: [
        // This will automatically load all classes in the HouseAgentSystem namespace
        // so that application classes do not need to require each other.
        'HouseAgentSystem.*'
    ],

    // The name of the initial view to create.
    mainView: 'HouseAgentSystem.view.main.Main'
});
