<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

	<head>
		<title>LeHome</title>
		<meta charset="utf-8" />

		<!--CSS-->
		<link rel="stylesheet" type="text/css" href="theme-triton/resources/theme-triton-all_1.css" />
		<link rel="stylesheet" type="text/css" href="theme-triton/resources/theme-triton-all_2.css" />

		<!-- <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	

	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"> -->
		<link href="dist/css/vendor/bootstrap.min.css" rel="stylesheet">

		<!-- Loading Flat UI -->
		<link href="dist/css/navbar.css" rel="stylesheet">
		<link href="docs/assets/css/demo.css" rel="stylesheet">

		<style type="text/css">
			.search-label,
			.search-label span {
				width: 50px !important;
			}
		</style>

		<!--JS-->
		<script type="text/javascript" src="js/ext-all.js"></script>
		<script type="text/javascript" src="js/region.js"></script>
		<script sec="house.js"></script>
		<script type="text/javascript" src="assets/js/jquery.2.1.1.min.js" ></script>
		<script type="text/javascript">
			Ext.onReady(function() {
				var myStore = Ext.create('Ext.data.Store', {
					storeId: 'simpsonsStore',
					proxy: {
						type: 'rest',
						url: '/house',
						reader: {
							type: 'json',
							rootProperty: 'content',
							totalProperty: 'totalElements'
						},
						writer: {
							type: 'json'
						},
						simpleSortMode: true
					},
					autoLoad: true,
					autoSync: true,
					remoteSort: true,
					pageSize: 8,
					sorters: {
						direction: 'DESC',
						property: 'id'
					},
					listeners: {}
				});
				function reload(btn) {
					var store = Ext.data.StoreManager.lookup('simpsonsStore');
					var sorter = store.getSorters();
					sorter.clear();
					var newSorter=[{
						property:'id',
						direction:'DESC'
					}];
					store.setSorters(newSorter);
					
				}
				function areaUp(btn) {
					var store = Ext.data.StoreManager.lookup('simpsonsStore');
					var sorter = store.getSorters();
					sorter.clear();
					var accord = this.getText();
					if(accord=='面积▲'){
						var newSorter=[{
							property:'area',
							direction:'asc'
						}];
						store.setSorters(newSorter);
						this.setText('面积▼');
					}else{
						var newSorter=[{
							property:'area',
							direction:'DESC'
						}];
						store.setSorters(newSorter);
						this.setText('面积▲');
					}
				}
				function priceUp(btn) {
					var store = Ext.data.StoreManager.lookup('simpsonsStore');
					var sorter = store.getSorters();
					sorter.clear();
					var accord = this.getText();
					if(accord=='价格▲'){
						var newSorter=[{
							property:'price',
							direction:'asc'
						}];
						store.setSorters(newSorter);
						this.setText('价格▼');
					}else{
						var newSorter=[{
							property:'price',
							direction:'DESC'
						}];
						store.setSorters(newSorter);
						this.setText('价格▲');
					}
				}
				Ext.create('Ext.container.Viewport', {
					//width: 800,
					//height: 1500,
					requires: [
						'Ext.grid.Panel',
						'Ext.toolbar.Paging',
						'Ext.grid.column.Date'
					],
					title: 'Border Layout',
					layout: 'border',
					items: [{
						//title: 'North Region is resizable',
						region: 'north', // position for region
						xtype: 'panel',
						height: 58,
						split: false, // enable resizing
						html: '<div class="row demo-row">' +
							'<div class="col">' +
							'<nav class="navbar navbar-inverse navbar-embossed navbar-expand-lg" role="navigation">' +
							'<a class="navbar-brand" href="#">Flat UI</a>' +
							'<div class="collapse navbar-collapse" id="navbar-collapse-01">' +
							'<ul class="nav navbar-nav mr-auto">' +
							'<li><a href="#fakelink">首页</a></li>' +
							'<li><a href="house.jsp"><img src="http://localhost:8081/Customer/dist/images/卖房.png">买房</a></li>' +
							'<li><a href="login.html"><img src="http://localhost:8081/Customer/dist/images/租房.png">租房</a></li>' +
							'<li><a href=""><img src="http://localhost:8081/Customer/dist/images/出租.png">我要出租</a></li>' +
							'<li style="width:600px;"></li>'+
							'<c:if test="${not empty sessionScope.user.realname}">'+
							'<li><a href="../showUser/information?id=${sessionScope.user.id}">${sessionScope.user.realname}<img src="../Customer/upload/user/${sessionScope.user.faceImage}" style="width:30px;height:30px;border-radius:15px;"></a></li>'+
							'<li><a href="#"onclick="logout()">退出</a></li>'+
							'</c:if>'+
							'<c:if test="${empty sessionScope.user.realname}">'+
							'<li><a href="../Customer/login&registration.html">登录/注册</a></li>'+
							'</c:if>'+
							'</ul>' +
							'</div>' +
							'</nav>' +
							'</div>' +
							'</div>',
					}, {
						//title: 'Center Region',
						region: 'center', // center region is required, no width/height specified
						xtype: 'panel',
						//layout: 'fit',
						scrollable: true,
						autosrcoll: true,
						//margins: '5 5 0 0',
						margin: '0 0 0 0',
						items: [{
							xtype: 'panel',
							//layout: 'fit',
							margin: '50 180 80 180',
							items: [{
								xtype: 'panel',
								title: '我要买房',
								items: [{
									xtype: 'fieldcontainer',
									fieldLabel: '地&nbsp;&nbsp;&nbsp;&nbsp;区',
									labelAlign: 'right',
									labelClsExtra: 'search-label',
									items: [{
										xtype: 'segmentedbutton',
										id: 'sgbtn',
										layout: 'column',
										reference: 'positionBtn',
										listeners: {
											change: function(btn) {
												var store = Ext.data.StoreManager.lookup('simpsonsStore');
												text = btn.up("fieldcontainer").down("segmentedbutton").getValue();
												Ext.apply(store.proxy.extraParams, {
													location: text
												});
												store.load({
													params: {
														start: 0,
														limit: 20,
														page: 1
													}
												});
											},
											render: function() {
												var regions = Ext.create('region');
												Ext.getCmp('sgbtn').add({
													text: regions.getAt(0).get('name'),
													value: regions.getAt(0).get('value'),
													pressed: true
												});
												for(var i = 1; i < regions.getCount(); i++) {
													var record = regions.getAt(i);
													Ext.getCmp('sgbtn').add({
														text: record.get('name'),
														value: record.get('value')
													});
												}
											}
										}
									}]
								}, {
									xtype: 'fieldcontainer',
									fieldLabel: '价&nbsp;&nbsp;&nbsp;&nbsp;格',
									labelAlign: 'right',
									labelClsExtra: 'search-label',
									items: [{
										xtype: 'segmentedbutton',
										layout: 'column',
										items: [{
											text: '不限',
											pressed: true,
											value: ''
										}, {
											text: '50万以下',
											value: '50'
										}, {
											text: '50~100万',
											value: '100'
										}, {
											text: '100~150万',
											value: '150'
										}, {
											text: '150~300万',
											value: '300'
										}, {
											text: '300~500万',
											value: '500'
										}, {
											text: '500~1000万',
											value: '1000'
										}, {
											text: '1000万以上',
											value: '1001'
										}],
										listeners: {
											change: function(btn) {
												var store = Ext.data.StoreManager.lookup('simpsonsStore');
												text = btn.up("fieldcontainer").down("segmentedbutton").getValue() * 1;
												Ext.apply(store.proxy.extraParams, {
													price: text,
													state:1
												});
												store.load({
													params: {
														start: 0,
														limit: 20,
														page: 1
													}
												});
											}
										}
									}]
								}, {
									xtype: 'fieldcontainer',
									fieldLabel: '面&nbsp;&nbsp;&nbsp;&nbsp;积',
									labelAlign: 'right',
									labelClsExtra: 'search-label',
									items: [{
										xtype: 'segmentedbutton',
										layout: 'column',
										items: [{
											text: '不限',
											pressed: true,
											value: ''
										}, {
											text: '50m²以下',
											value: '50'
										}, {
											text: '50~70m²',
											value: '70'
										}, {
											text: '70~100m²',
											value: '100'
										}, {
											text: '100~150m²',
											value: '150'
										}, {
											text: '150~180m²',
											value: '180'
										}, {
											text: '180m²以上',
											value: '181'
										}],
										listeners: {
											change: function(btn) {
												var store = Ext.data.StoreManager.lookup('simpsonsStore');
												text = btn.up("fieldcontainer").down("segmentedbutton").getValue() * 1;
												Ext.apply(store.proxy.extraParams, {
													roomArea: text
												});
												store.load({
													params: {
														start: 0,
														limit: 20,
														page: 1
													}
												});
											}
										}
									}]
								}, {
									xtype: 'fieldcontainer',
									fieldLabel: '房&nbsp;&nbsp;&nbsp;&nbsp;型',
									labelAlign: 'right',
									labelClsExtra: 'search-label',
									items: [{
										xtype: 'segmentedbutton',
										layout: 'column',
										items: [{
											text: '不限',
											pressed: true,
											value: ''
										}, {
											text: '1室',
											value: '1'
										}, {
											text: '2室',
											value: '2'
										}, {
											text: '3室',
											value: '3'
										}, {
											text: '4室',
											value: '4'
										}, {
											text: '5室及其以上',
											value: '5'
										}],
										listeners: {
											change: function(btn) {
												var store = Ext.data.StoreManager.lookup('simpsonsStore');
												text = btn.up("fieldcontainer").down("segmentedbutton").getValue() * 1;
												Ext.apply(store.proxy.extraParams, {
													room: text
												});
												store.load({
													params: {
														start: 0,
														limit: 20,
														page: 1
													}
												});
											}
										}
									}]
								}]
							}, {
								xtype: 'gridpanel',
								cls: 'allRecordsCls',
								hideHeaders: true,
								border: false,

								routeId: 'all',
								store: Ext.data.StoreManager.lookup('simpsonsStore'),
								viewConfig: {
									preserveScrollOnRefresh: true,
									stripeRows: false
								},
								tbar: {
									
									items: ['->',{
										text: '默认排序',
										iconCls: 'x-fa fa-refresh',
										handler:reload
										
									},{
										text: '价格▲',
										handler: priceUp
									}, {
										text: '面积▲',
										value:'up',
										reference: 'AreaUp',
										handler: areaUp
									}]
								},
								columns: [{
									xtype: 'gridcolumn',
									renderer: function(value, metaData, record, rowIndex) {
										var page = "<div class='media' style='background-color:rgba(0,1,1,0.1);'>" +
											"<div class='media-left'>" +
											"<a href='#'>" +
											"<img class='media-object' src='upload/house/" + record.data.imgs[0] + "' alt='...' height='190px' width='260px'>" +
											"</a>" +
											"</div>" +
											"<div class='media-body'>" +
											"<b><a target='_blank' href='../showHouse/details?id=" + record.data.id + " '><h4 style='text-indent:50px;'>" + record.data.title + "</h4></a></b>" +
											"<h4 style='color:red;font-family:verdana;'align='right'>" + record.data.price + "万</h4>" +
											"<p style='text-indent:50px; font-size:17px;'><span> " + record.data.room + "室" + record.data.hall + "厅</span><em> | </em><span> " + record.data.area + "m²</span><em> | </em><span> 第" + record.data.floor + "层</span><em> | </em><span>" + record.data.buildDate + "年建</span>" + "</p>" +
											"<p style='text-indent:50px; font-size:16px; color:black;font-weight: bold;'>" + record.data.region + "&nbsp;&nbsp;&nbsp;" + record.data.address + "</p>" +
											"</div>" +
											"</div>";
										return page;
									},
									dataIndex: 'content',
									flex: 1
								}],
								dockedItems: [{
									xtype: 'pagingtoolbar',
									dock: 'bottom',
									displayInfo: true,
									bind: Ext.data.StoreManager.lookup('simpsonsStore'),
								}]
							}]
						}],

						renderTo: Ext.getBody()
					}]
				});
			});
		</script>
		<script>
			function logout(){
				$.ajax({
		                type: 'post',
		                url: "/users/logout",
		                cache: false,
		                processData: false,
		                contentType: false,
		            }).success(function (data) {
		            	window.location.href="house.jsp";
		            }).error(function () {
		        });
			}
		</script>
	</head>
		
	<body>
	</body>

</html>