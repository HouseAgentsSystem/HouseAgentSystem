Ext.define('HouseAgentSystem.view.house.HouseAddWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.houseAddWindow',
    height: 600,
    minHeight: 600,
    minWidth: 1230,
    width: 1230,
    scrollable: false,
    title: '添加房源信息',
    closable: true,
    modal:true,
    layout: 'fit',
    html:'<iframe src="http://localhost:8081/Upload/index.html" width="100%" height="100%" frameborder="0"></iframe>'
});