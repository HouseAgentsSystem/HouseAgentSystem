<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${requestScope.staff.realname}</title>
<!--CSS-->
	<link rel="stylesheet" type="text/css" href="http://localhost:8081/Customer/theme-triton/resources/theme-triton-all_1.css" />
	<link rel="stylesheet" type="text/css" href="http://localhost:8081/Customer/theme-triton/resources/theme-triton-all_2.css" />
	<link href="http://localhost:8081/Customer/dist/css/vendor/bootstrap.min.css" rel="stylesheet">
	<!-- Loading Flat UI -->
	<link href="http://localhost:8081/Customer/dist/css/flat-ui.css" rel="stylesheet">
	<link href="http://localhost:8081/Customer/docs/assets/css/demo.css" rel="stylesheet">
	<style type="text/css">
		h3{color: #2b5454;}
		p{margin: 5px; font-size:15px; letter-spacing: 1.5px;}
		.detail{color: #797979;}
		.detail span{color: black; font-size:18px;}
	</style>
	<script type="text/javascript" src="http://localhost:8081/Customer/js/ext-all.js"></script>
	<script type="text/javascript">
		
		Ext.onReady(function(){
			
			function getUrlParam(param) {
						var params = Ext.urlDecode(location.search.substring(1));
						return param ? params[param] : params;
				};
			
			var staffStore = Ext.create('Ext.data.Store', {
					storeId:'staffStore',
					proxy: {
					type: 'rest',
					url: '/showStaffStore/getStaff/'+ ${requestScope.staff.id},//Ext.get("storeId").dom.innerHTML,
					reader:{
						type:'json',
						rootProperty:'content',
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
				pageSize: 6,
				//sorters: {
				//	direction: 'DESC',property: 'id'
				//},
				listeners: {}
			});
			
			Ext.create('Ext.container.Viewport', {
					//width: 800,
					height: 1500,
					requires: [
							'Ext.grid.Panel',
							'Ext.toolbar.Paging',
							'Ext.grid.column.Date'
					],
					title: 'Border Layout',
					defaults: {border: false},
					layout: 'border',
					scrollable: true,
					autosrcoll: true,
					items: [{
							//title: 'North Region is resizable',
							region: 'north',     // position for region
							xtype: 'panel',
							height: 55,
							split: false,         // enable resizing
							//margin: '0 5 5 5',
							html: '<div class="row demo-row">' +
					'<div class="col">' +
					'<nav class="navbar navbar-inverse navbar-embossed navbar-expand-lg" role="navigation">' +
					'<a class="navbar-brand" href="index.jsp">Le Home</a>' +
					'<div class="collapse navbar-collapse" id="navbar-collapse-01">' +
					'<ul class="nav navbar-nav mr-auto">' +
					'<li><a href="index.jsp">首页</a></li>' +
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
					},{
							region: 'center',     // center region is required, no width/height specified
							xtype: 'panel',
							//layout: 'fit',
							layout: 'border',
							scrollable: true,
							autosrcoll: true,
							//margin: '0 30 0 30',
							items:[{
								region: 'north',     
								xtype: 'panel',
								height: 118,
								split: false,         // enable resizing
								border: false,
								margin: '15 5 0 100',
								html: "<img src='/Customer/upload/staff/${requestScope.staff.faceImg}' style='float:left;width:100px;height:150px;'>"+
									"<div class='row col-md-8 col-md-offset-2' float:right; >"+
										"<b><h3>${requestScope.staff.realname}的店铺</h3></b>"+
									"</div>"+
									'<div class="row col-md-8 col-md-offset-2">'+'<b><p style="" class="detail">'+
									'联系方式：<span>${requestScope.staff.phoneNumber}</span><span>${requestScope.houseRent.hall}</span><em>&nbsp;&nbsp;</em>'+
										"</p></b>"+
									"</div>"
							},{
								xtype: 'panel',
								region: 'center', 
								margin: '0 100 0 100',
								padding: '20 0 0 0',
								border: false,
								height: 1000,
								//layout: 'fit',
								items:[{
									xtype: 'gridpanel',
										cls: 'allRecordsCls',
										border: false,
										hideHeaders: true,
										routeId: 'all',
										store: Ext.data.StoreManager.lookup('houseStore'),
										viewConfig: {
												preserveScrollOnRefresh: true,
												stripeRows: false
										},
										columns: [
												{
														xtype: 'gridcolumn',
														border: false,
														renderer: function(value, metaData, record, rowIndex) {
																var page = "<div class='media' style='background-color:rgba();margin: 10px;'>"+
											"<div class='media-left'>"+
												"<a href='#'>"+
													"<img class='media-object' src='http://localhost:8081/Customer/upload/house/"+record.data.imgs[0]+"' alt='...' height='190px' width='260px'>"+
												"</a>"+
											"</div>"+
											"<div class='media-body'>"+
												"<b><a target='_blank' href='http://localhost:8081/showHouse/details?id="+record.data.id+" '><h4 style='text-indent:50px;'>"+record.data.title+"</h4></a></b>"+
												"<h4 style='color:#eb5f00;font-family:verdana;'align='right'>"+record.data.price+"万</h4>"+
												"<p style='text-indent:50px; font-size:17px;'><span> "+record.data.room+"室"+record.data.hall+"厅</span><em> | </em><span> "+record.data.area+"m²</span><em> | </em><span> 第"+record.data.floor+"层</span><em> | </em><span>"+record.data.buildDate+"年建</span>"+"</p>"+
												"<p style='text-indent:50px; font-size:16px;'><b>"+record.data.region+"&nbsp;&nbsp;&nbsp;</b>"+record.data.address+"</p>"+
											"</div>"+
										"</div>";
																return page;
														},
														dataIndex: 'content',
														flex: 1
												}
										],
											tbar:[{
												xtype: 'tbtext',
												text: '推荐二手房'
											}],
										dockedItems: [
												{
														xtype: 'pagingtoolbar',
														dock: 'bottom',
														displayInfo: true,
														displayMsg: '共{2}条记录',
														afterPageText: "页,共{0}页", 
														beforePageText: "第",
														bind: Ext.data.StoreManager.lookup('houseStore')
												}
										]
								}],
								}]
						}],
					renderTo: Ext.getBody()
			});
		});
	</script>
</head>
<body>
</body>
</html>