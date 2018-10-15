Ext.define('HouseAgentSystem.profile.Tablet', {
    extend: 'Ext.app.Profile',

    requires: [
        'HouseAgentSystem.view.tablet.*'
    ],

    // Map tablet/desktop profile views to generic xtype aliases:
    //
    views: {
        email: 'HouseAgentSystem.view.tablet.email.Email',
        inbox: 'HouseAgentSystem.view.tablet.email.Inbox',
        compose: 'HouseAgentSystem.view.tablet.email.Compose',

        searchusers: 'HouseAgentSystem.view.tablet.search.Users'
    },

    isActive: function () {
        return !Ext.platformTags.phone;
    }
});
