Ext.define('HouseAgentSystem.profile.Phone', {
    extend: 'Ext.app.Profile',

    requires: [
        'HouseAgentSystem.view.phone.*'
    ],

    // Map phone profile views to generic xtype aliases:
    //
    views: {
        email: 'HouseAgentSystem.view.phone.email.Email',
        inbox: 'HouseAgentSystem.view.phone.email.Inbox',
        compose: 'HouseAgentSystem.view.phone.email.Compose',

        searchusers: 'HouseAgentSystem.view.phone.search.Users'
    },

    isActive: function () {
        return Ext.platformTags.phone;
    },

    launch: function () {
        // Add a class to the body el to identify the phone profile so we can
        // override CSS styles easily. The framework adds x-phone so we could
        // use it but this way the app controls a class that is always present
        // when this profile isActive, regardless of the actual device type.
        Ext.getBody().addCls('phone');
    }
});
