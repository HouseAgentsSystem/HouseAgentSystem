Ext.define('HouseAgentSystem.view.ranking.RankingController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.rankingController',
    addExtraData:function(combo,record,index){
        var store = combo.getStore();
        store.add({storeName:'所有门店', id: '0'});
        combo.setValue('0');
    },
   	searchCombobox:function(combo,record,index){
        var monthField = this.lookupReference('searchFieldName1');
        var storeIdField = this.lookupReference('searchFieldName2');
		var monthValue = monthField==null?null:monthField.getValue();
        var storeIdValue = storeIdField==null?null:storeIdField.getValue();
		var store =	combo.up('cartesian').getStore();
		Ext.apply(store.proxy.extraParams, {month:monthValue, storeId:storeIdValue});
		store.load();
	},

	onBarTipRender1: function (tooltip, record, item) {
        // var fieldIndex = Ext.Array.indexOf(item.series.getYField(), item.field),
        //     browser = item.series.getTitle()[fieldIndex];
        // tooltip.setHtml(browser + ' on ' +
        //     record.get('employeeName') + ': ' +
        //     record.get(item.field).toFixed(1));
        tooltip.setHtml(record.get(item.field).toFixed(1));
    },
    onBarTipRender2: function (tooltip, record, item) {
        // var fieldIndex = Ext.Array.indexOf(item.series.getYField(), item.field),
        //     browser = item.series.getTitle()[fieldIndex];
        // tooltip.setHtml(browser + ' on ' +
        //     record.get('employeeName') + ': ' +
        //     record.get(item.field).toFixed(1));
        tooltip.setHtml(record.get(item.field).toFixed(1) + ' 所属门店: ' + record.get('storeName'));
    },
    onBarTipRender: function(tooltip, record, item){
        tooltip.setHtml(record.get(item.field).toFixed(1) + ' 经理: ' + record.get('managerName'));
    }
});