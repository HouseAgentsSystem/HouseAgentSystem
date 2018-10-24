Ext.define('HouseAgentSystem.view.authentication.AuthenticationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.authentication',

    //TODO: implement central Facebook OATH handling here

    onFaceBookLogin : function() {
        this.redirectTo('dashboard', true);
    },

    onLoginButton: function(btn){
        var me = this;
        Ext.Ajax.request({
            url: '/login',
            method: 'post',
            params: {
                userName: btn.up("form").getForm().findField("userid").getValue(),
                password: btn.up("form").getForm().findField("password").getValue()
            },
            success: function(response, options) {
                var json = Ext.util.JSON.decode(response.responseText);
                if(json.success){
                    me.redirectTo('dashboard', true);
                    Ext.getCmp('loginUserName').setText(json.map.userName);
                    //根据不同的 角色 去加载不同的NavigationTree?
                    //用户角色:  json.map.group
                    console.log(json.map.userName);
                    var navigationTreeList = Ext.getCmp('main-view-detail-wrap').getComponent('navigationTreeList');
                    if(json.map.group=='admin'){
                        navigationTreeList.setStore('NavigationAdminTree');
                    }else if(json.map.group=='manager'){
                        navigationTreeList.setStore('NavigationManagerTree');
                    }else if(json.map.group=='emploee'){
                        navigationTreeList.setStore('NavigationEmploeeTree');
                    }
                    
                }else{
                    Ext.Msg.alert('登录失败', json.msg);
                }
            }
        });
    },


    onLoginAsButton: function() {
        this.redirectTo('login', true);
    },

    onNewAccount:  function() {
        this.redirectTo('register', true);
    },

    onSignupClick:  function() {
        this.redirectTo('dashboard', true);
    },

    onResetClick:  function() {
        this.redirectTo('dashboard', true);
    }
    
});