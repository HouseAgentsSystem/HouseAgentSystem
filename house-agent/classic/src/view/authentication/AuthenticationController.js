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
                    Ext.getCmp('loginUserName').setText(json.map.realName);
                    Ext.getCmp('loginUserImg').setSrc('/Customer/upload/staff/'+json.map.faceImg);
                    //根据不同的 角色 去加载不同的NavigationTree?
                    //用户角色:  json.map.role
                    console.log(json.map.realName);
                    var navigationTreeList = Ext.getCmp('main-view-detail-wrap').getComponent('navigationTreeList');
                    if(json.map.role=='管理员'){
                        navigationTreeList.setStore('NavigationAdminTree');
                    }else if(json.map.role=='经理'){
                        navigationTreeList.setStore('NavigationManagerTree');
                    }else if(json.map.role=='员工'){
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