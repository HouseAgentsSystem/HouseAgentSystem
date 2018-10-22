Ext.define('HouseAgentSystem.view.trade.TradeViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.tradeViewController',
	
    onNavFilterFieldChange: function(field, value) {
        var me = this,
            tree = Ext.getCmp('tradeNavTree'),
            store = tree.getStore();
        if (value) {
            field.getTrigger('clear').show();
            tree.filterByText(value);
        } else {
            me.rendererRegExp = null;
            store.clearFilter();
            field.getTrigger('clear').hide();
            store.load();
        }
    },

    onNavFilterClearTriggerClick: function() {
        this.getReferences().navtreeFilter.setValue();
    },

    onNavFilterSearchTriggerClick: function() {
        var field = this.getReferences().navtreeFilter;

        this.onNavFilterFieldChange(field, field.getValue());
    },

    tradeSelect: function(view, record, index, eOpts) {
        console.log(view);
        if(record) {
        	var win = Ext.getCmp('tradeGrid').up('container').add(Ext.widget('tradeDetailWindow')).show();
        	win.down('form').getForm().loadRecord(record);
        }
        console.log(record);
    },

    submitTradeAddForm: function(btn) {
    	var win = btn.up('window');
		var form = win.down('form').getForm();
		if (form.isValid()) {
	        form.submit({
	            url: '/trade/addTrade',
				method: 'POST',
	            waitMsg: '记录交易中...',
	            success: function(form, action) {
					var result = action.result;
					if(result.success){
						Ext.Msg.alert('操作成功', result.msg, function() {
							win.close();
						});
					}else{
						Ext.Msg.alert('操作失败', result.msg);
					}
				},
				failure: function(form, action) {
					console.log("sdfio");
					var result = action.result;
					Ext.Msg.alert('操作失败', result.msg);
				}
	        });
	    }
    }
});