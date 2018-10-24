<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我要租房</title>
        
	<!--CSS-->
	<link rel="stylesheet" type="text/css" href="theme-triton/resources/theme-triton-all_1.css"/>
	<link rel="stylesheet" type="text/css" href="theme-triton/resources/theme-triton-all_2.css"/>

	<!-- <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	

	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"> -->
	<link href="dist/css/vendor/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="dist/css/navbar.css" rel="stylesheet">
    <link href="docs/assets/css/demo.css" rel="stylesheet">

    <style type="text/css">
    	.search-label, .search-label span {
    		width: 50px !important;
    	}
    </style>

	<!--JS-->
	<script type="text/javascript" src="js/ext-all.js"></script>
	<script type="text/javascript" src="js/region.js"></script>
	<script sec="house.js"></script>
	<script type="text/javascript">
		Ext.onReady(function(){
		var  houseRentStore = 	Ext.create('Ext.data.Store', {
		    storeId:'houseRentStore',
		    proxy: {
				type: 'ajax',
				url: '/showHouseRent',
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
			},
			listeners: {}
		});
		
		function reload(btn) {
			var store = Ext.data.StoreManager.lookup('houseRentStore');
			var sorter = store.getSorters();
			sorter.clear();
			var newSorter=[{
				property:'id',
				direction:'DESC'
			}];
			store.setSorters(newSorter);
			
		}
		
		function publishTimeUp(btn) {
			var store = Ext.data.StoreManager.lookup('houseRentStore');
			var sorter = store.getSorters();
			sorter.clear();
			var accord = this.getText();
			if(accord=='发布时间▲'){
				var newSorter=[{
					property:'publishTime',
					direction:'asc'
				}];
				store.setSorters(newSorter);
				this.setText('发布时间▼');
			}else{
				var newSorter=[{
					property:'publishTime',
					direction:'DESC'
				}];
				store.setSorters(newSorter);
				this.setText('发布时间▲');
			}
		}
		
		function priceUp(btn) {
			var store = Ext.data.StoreManager.lookup('houseRentStore');
			var sorter = store.getSorters();
			sorter.clear();
			var accord = this.getText();
			if(accord=='价格▲'){
				var newSorter=[{
					property:'rent',
					direction:'asc'
				}];
				store.setSorters(newSorter);
				this.setText('价格▼');
			}else{
				var newSorter=[{
					property:'rent',
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
			        region: 'north',     // position for region
			        xtype: 'panel',
			        height: 58,
			        split: false,         // enable resizing
			        //margins: '0 5 5 5',
			        html: '<div class="row demo-row">' +
							'<div class="col">' +
							'<nav class="navbar navbar-inverse navbar-embossed navbar-expand-lg" role="navigation">' +
							'<a class="navbar-brand" href="index.jsp">Le Home</a>' +
							'<div class="collapse navbar-collapse" id="navbar-collapse-01">' +
							'<ul class="nav navbar-nav mr-auto">' +
							'<li><a href="index.jsp">首页</a></li>' +
							'<li><a href="house.jsp"><img src="http://localhost:8081/Customer/dist/images/卖房.png">买房</a></li>' +
							'<li><a href="houserent.jsp"><img src="http://localhost:8081/Customer/dist/images/租房.png">租房</a></li>' +
							'<li style="width:600px;"></li>'+
							'<c:if test="${not empty sessionScope.user.realname}">'+
							'<li><a href="http://localhost:8081/showUser/information?id=${sessionScope.user.id}">${sessionScope.user.realname}<img src="http://localhost:8081/Customer/upload/user/${sessionScope.user.faceImage}" style="width:30px;height:30px;border-radius:15px;"></a></li>'+
							'<li><a href="#"onclick="logout()">退出</a></li>'+
							'</c:if>'+
							'<c:if test="${empty sessionScope.user.realname}">'+
							'<li><a href="http://localhost:8081/Customer/login&registration.html">登录/注册</a></li>'+
							'</c:if>'+
							'</ul>' +
							'</div>' +
							'</nav>' +
							'</div>' +
							'</div>',
			    },{
			        //title: 'Center Region',
			        region: 'center',     // center region is required, no width/height specified
			        xtype: 'panel',
			        //layout: 'fit',
			        scrollable: true,
					autosrcoll: true,
			        //margins: '5 5 0 0',
			        margin: '0 0 0 0',
			        items:[{
			        	xtype:'panel',
			        	//layout: 'fit',
				        margin: '50 180 0 180',
				        items:[{
				        	xtype:'panel',
				        	title: '我要租房',
				        	items:[{
				        		xtype: 'fieldcontainer',
				        		fieldLabel: '地&nbsp;&nbsp;&nbsp;&nbsp;区',
				        		labelAlign: 'right',
				        		labelClsExtra: 'search-label',
				                items: [{
				                    xtype: 'segmentedbutton',
				                    id: 'sgbtn',
				                    layout: 'column',
				                    reference: 'positionBtn',
				                    listeners:{
				    					change: function(btn){
				    					 	 var store = Ext.data.StoreManager.lookup('houseRentStore');
				    					 	value=btn.up("fieldcontainer").down("segmentedbutton").getValue();
				    						Ext.apply(store.proxy.extraParams, {region:value});
				    						store.load({params:{start:0, limit:20, page:1}}); 
				    					},
				    					render: function() {
											var regions = Ext.create('region');
											Ext.getCmp('sgbtn').add({text: regions.getAt(0).get('name'), value: regions.getAt(0).get('value'), pressed: true});
											for (var i = 1; i < regions.getCount(); i++) {
												var record = regions.getAt(i);
												Ext.getCmp('sgbtn').add({text: record.get('name'), value: record.get('value')});
											}
										}
				    				}
				                }]
				            },{
			                	xtype:'fieldcontainer',
			                	fieldLabel: '价&nbsp;&nbsp;&nbsp;&nbsp;格',
			                	labelAlign: 'right',
			        			labelClsExtra: 'search-label',
			                	items:[{
				                    xtype: 'segmentedbutton',
				                    layout: 'column',
				                    items: [{
				                        text: '不限',
				                        pressed: true,
				                        value:''
				                    }, {
				                        text: '500元以下',
				                        value: '500'
				                    }, {
				                        text: '500-800元',
				                        value: '800'
				                    },{
				                        text: '800-1000元',
				                        value: '1000'
				                    }, {
				                        text: '1000~1500元',
				                        value: '1500'
				                    }, {
				                        text: '1500~2000元',
				                        value: '2000'
				                    } , {
				                        text: '2000~3000元',
				                        value: '3000'
				                    }, {
				                        text: '3000~5000元',
				                        value: '5000'
				                    }, {
				                        text: '5000元以上',
				                        value: '5001'
				                    }],listeners:{
				    					change: function(btn){
				    						var store =	Ext.data.StoreManager.lookup('houseRentStore');
				    						value=btn.up("fieldcontainer").down("segmentedbutton").getValue()*1;
				    						Ext.apply(store.proxy.extraParams, {rent:value});
				    						store.load({params:{start:0, limit:20, page:1}});
				    					}
				    				}
				    			}]
			                },{
			                	xtype:'fieldcontainer',
			                	fieldLabel: '房&nbsp;&nbsp;&nbsp;&nbsp;型',
			                	labelAlign: 'right',
			        			labelClsExtra: 'search-label',
			                	items:[{
				                    xtype: 'segmentedbutton',
				                    layout: 'column',
				                    items: [{
				                        text: '不限',
				                        pressed: true,
				                        value:''
				                    }, {
				                        text: '一室',
				                        value: '1'
				                    }, {
				                        text: '二室',
				                        value: '2'
				                    },{
				                        text: '三室',
				                        value: '3'
				                    }, {
				                        text: '四室',
				                        value: '4'
				                    }, {
				                        text: '五室及以上',
				                        value: '5'
				                    }],listeners:{
				    					change: function(btn){
				    					 	var store = Ext.data.StoreManager.lookup('houseRentStore');
				    					 	value=btn.up("fieldcontainer").down("segmentedbutton").getValue()*1;
				    						Ext.apply(store.proxy.extraParams, {room:value});
				    						store.load({params:{start:0, limit:20, page:1}}); 
				    					}
				    				}
				    			}]
				            },{
			                	xtype:'fieldcontainer',
			                	fieldLabel: '房&nbsp;&nbsp;&nbsp;&nbsp;型',
			                	labelAlign: 'right',
			        			labelClsExtra: 'search-label',
			                	items:[{
				                    xtype: 'segmentedbutton',
				                    layout: 'column',
				                    items: [{
				                        text: '不限',
				                        pressed: true,
				                        value:''
				                    }, {
				                        text: '整租',
				                        value: '整租'
				                    }, {
				                        text: '合租',
				                        value: '合租'
				                    }],listeners:{
				    					change: function(btn){
				    					 	var store = Ext.data.StoreManager.lookup('houseRentStore');
				    					 	value=btn.up("fieldcontainer").down("segmentedbutton").getValue();
				    						Ext.apply(store.proxy.extraParams, {rentType: value});
				    						store.load({params:{start:0, limit:20, page:1}}); 
				    					}
				    				}
				    			}]
				            }
			            ]
					},{
			        	xtype: 'gridpanel',
			            cls: 'allRecordsCls',
			            //scrollable: false,
			            //autosrcoll: true,
			            hideHeaders: true,
			            border: false,
			           
			            routeId: 'all',
			            store: Ext.data.StoreManager.lookup('houseRentStore'),
			            viewConfig: {
			                preserveScrollOnRefresh: true,
			                stripeRows: false
			            },
			            columns: [
			                {
			                    xtype: 'gridcolumn',
			                    renderer: function(value, metaData, record, rowIndex) {
			                        var page = "<div class='media' style='background-color:rgba(); padding: 16px;'>"+
			                        "<div class='media-left'>"+
									    "<a href='#'>"+
									      "<img class='media-object' src='/Customer/upload/houseRent/"+record.data.image+"' alt='...' height='160px' width='215px'>"+
									    "</a>"+
									  "</div>"+
									  "<div class='media-body'>"+
									    "<b><a target='_blank' href='http://localhost:8081/showHouseRent/houseRentDetail/"+record.data.id+" '><h4 style='text-indent:30px;'>"+record.data.title+"</h4></a></b>"+
									    "<p style='text-indent:30px; font-size:15px; color: #797979;'><span> "+record.data.room+"室"+record.data.hall+"厅</span><em> | </em><span> "+record.data.area+"m²</span><em> | </em><span> 第"+record.data.floor+"层</span>"+"</p>"+
									    "<p style='text-indent:30px; font-size:15px; color: #797979;'><b>"+record.data.region+"&nbsp;&nbsp;&nbsp;</b>"+record.data.address+"</p>"+
									    "<p style='text-indent:30px; font-size:15px; '><span style='color: #e88a78; background-color: #fcf4f1; border-radius: 16px; padding: 2px 9px;'>"+record.data.rentType+"</span></p>"+
									  "</div>"+
									  "<div class='media-right'>"+
									  "<p style='color:#eb5f00;font-weight:700; font-size: 30px; font-family:verdana; margin: 50px 0;' ><span class='font-size:40px;'>"+record.data.rent+"</span>元/月</p>"+
									  "</div>"+
									"</div>";
			                        return page;
			                    },
			                    dataIndex: 'content',
			                    flex: 1
			                }
			            ],
			            tbar: {
							
							items: ['->',{
								text: '默认排序',
								iconCls: 'x-fa fa-refresh',
								handler:reload
								
							},{
								text: '价格▲',
								handler: priceUp
							}, {
								text: '发布时间▲',
								handler: publishTimeUp
							}]
						},
			            dockedItems: [
			                {
			                    xtype: 'pagingtoolbar',
			                    dock: 'bottom',
			                    displayInfo: true,
			                    bind: Ext.data.StoreManager.lookup('houseRentStore'),
			                }
			            ]}
			        ]
	        	}],
			        
			    renderTo: Ext.getBody()}]
			});
		});
	</script>
	<script src="http://localhost:8081/Customer/assets/js/jquery.2.1.1.min.js"></script>
	<script>
		function logout(){
			$.ajax({
	                type: 'post',
	                url: "http://localhost:8081/users/logout",
	                cache: false,
	                processData: false,
	                contentType: false,
	            }).success(function (data) {
	            	window.location.reload();
	            }).error(function () {
	        });
		}
	</script>
</head>

<body>

</body>
</html>