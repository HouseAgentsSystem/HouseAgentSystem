Ext.define('HouseAgentSystem.view.trade.Trade', {		//1.修改文件路径
      extend: 'Ext.container.Container',	//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'trade',
	height:Ext.Element.getViewportHeight()-104,//必须设置高，否则无法使用btrade布局
    controller: 'tradeViewController',		
    viewModel : {type: 'tradeViewModel'},	
	requires: [
        'Ext.layout.container.Border'
    ],
    layout:'border',
    margin: '20 20 20 20',
    items: [{
		title: 'Navigation',
		region:'west',
		width: 180,
		collapsible: true,
		margins: '5 0 0 0',
		cmargins: '5 5 0 0',
		split: true,
		xtype: 'tradeNavTree'
	},{
		title: 'center',
		region:'center',
		margins: '5 0 0 0',
		cmargins: '5 5 0 0',
		xtype: 'tradeGrid'
	}]
});
