<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--CSS-->
<link rel="stylesheet" type="text/css"
	href="http://localhost:8081/Customer/theme-triton/resources/theme-triton-all_1.css" />
<link rel="stylesheet" type="text/css"
	href="http://localhost:8081/Customer/theme-triton/resources/theme-triton-all_2.css" />

<!-- <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	

	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"> -->
<link href="http://localhost:8081/Customer/dist/css/vendor/bootstrap.min.css" rel="stylesheet">

<!-- Loading Flat UI -->
<link href="http://localhost:8081/Customer/dist/css/flat-ui.css" rel="stylesheet">
<link href="http://localhost:8081/Customer/docs/assets/css/demo.css" rel="stylesheet">
<!--JS-->
<script type="text/javascript" src="http://localhost:8081/Customer/js/ext-all.js"></script>
<script type="text/javascript">
	Ext.onReady(function(){

		function getUrlParam(param) {
	        var params = Ext.urlDecode(location.search.substring(1));
	        return param ? params[param] : params;
	    };
	    
		var houseStore = Ext.create('Ext.data.Store', {
		    storeId:'houseStore',
		    proxy: {
				type: 'ajax',
				url: '/showStore/getHouse/'+ ${requestScope.store.id},//Ext.get("storeId").dom.innerHTML,
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
			pageSize: 8,
			sorters: {
				direction: 'DESC',property: 'id'
			}
		});
		
		var staffStore = Ext.create('Ext.data.Store', {
		    storeId:'staffStore',
		    proxy: {
				type: 'rest',
				url: '/showStore/getStaff/'+ ${requestScope.store.id},//Ext.get("storeId").dom.innerHTML,
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
		    items: [{
		        //title: 'North Region is resizable',
		        region: 'north',     // position for region
		        xtype: 'panel',
		        height: 55,
		        split: false,         // enable resizing
		        //margin: '0 5 5 5',
		        html: '<div class="row demo-row">'+
				        '<div class="col">'+
				          '<nav class="navbar navbar-inverse navbar-embossed navbar-expand-lg" role="navigation">'+
				            '<a class="navbar-brand" href="#">Flat UI</a>'+
				            '<div class="collapse navbar-collapse" id="navbar-collapse-01">'+
				              '<ul class="nav navbar-nav mr-auto">'+
				                '<li><a href="#fakelink">首页</a></li>'+
				                '<li><a href="house.html"><img src="http://localhost:8081/Customer/dist/images/卖房.png">买房</a></li>'+
				                '<li><a href="login.html"><img src="http://localhost:8081/Customer/dist/images/租房.png">租房</a></li>'+
				                '<li><a href=""><img src="http://localhost:8081/Customer/dist/images/出租.png">我要出租</a></li>'+
				               '</ul>'+
				            '</div>'+
				          '</nav>'+
				        '</div>'+
				      '</div>',
		    },{
		        //title: 'Center Region',
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
			        height: 100,
			        split: false,         // enable resizing
			        border: false,
			        //margins: '0 5 5 5',
			        html: '门店信息'
		        },{
		        	xtype: 'panel',
		        	region: 'center', 
		        	margin: '0 0 0 100',
		        	padding: '20 0 0 0',
		        	border: false,
		        	layout: 'fit',
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
									      "<img class='media-object' src='http://localhost:8081/Customer/images/"+record.data.images+"' alt='...' height='190px' width='260px'>"+
									    "</a>"+
									  "</div>"+
									  "<div class='media-body'>"+
									    "<b><a target='_blank' href='http://localhost:8081/showHouse/details?id="+record.data.id+" '><h4 style='text-indent:50px;'>"+record.data.title+"</h4></a></b>"+
									    "<h4 style='color:red;font-family:verdana;'align='right'>"+record.data.price+"万</h4>"+
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
		        	}]},{
		            	region: 'east', 
		            	xtype: 'panel',
		            	width: 230,
				        split: false,
				        border: false,
				        margin: '0 100 0 0',
				        padding: '20 0 0 20',
				        items:[{
				        	xtype: 'gridpanel',
					        split: false,         // enable resizing
					        hideHeaders: true,
					        //margin: '0 5 5 5',
					        paddings: '5 5 5 5',
					        //border: false,
					        store: Ext.data.StoreManager.lookup('staffStore'),
				            viewConfig: {
				                preserveScrollOnRefresh: true,
				                stripeRows: false
				            },
				            columns: [
			                {
			                    xtype: 'gridcolumn',
			                    //text: '门店推荐经纪人',
			                    renderer: function(value, metaData, record, rowIndex) {
			                        var page = "<div class='media' style='background-color:rgba();margin: 10px;'>"+
									  "<div class='media-left'>"+
									    "<a href='#'>"+
									      "<img class='media-object' src='http://localhost:8081/Customer/images/face-img/"+record.data.faceImg+"' alt='...' height='90px' width='70px'>"+
									    "</a>"+
									  "</div>"+
									  "<a target='_blank' href='http://localhost:8081/staff/details?id="+record.data.id+" '>"+
									  "<div class='media-body'>"+
									    "</br><p style='color:black; text-indent:10px; font-size:15px;'>"+record.data.realname+"</p>"+
									    //"<h4 style='font-size:30px;>"+record.data.realname+"</h4></b>"+
									    "<p style='color:black; text-indent:10px; font-size:10px;'>"+record.data.phoneNumber+
									    //"<p style='text-indent:50px; font-size:16px;'><b>"+record.data.region+"&nbsp;&nbsp;&nbsp;</b>"+record.data.address+"</p>"+
									  "</div>"+
									  "</a>"
									"</div>";
			                        return page;
			                    },
			                    dataIndex: 'content',
			                    flex: 1
			                }],
			                tbar:[{
			                	xtype: 'tbtext',
			                	text: '门店推荐经纪人'
			                }]
				        }]
		            }]
        	}],
		    renderTo: 'mainPanel'
		});
		
	    //console.log(getUrlParam('id'))
	    //Ext.Msg.alert('Title', getUrlParam('id'), function(){});
	});
</script>
</head>
<body>
<div id='mainPanel'></div>
</body>
</html>