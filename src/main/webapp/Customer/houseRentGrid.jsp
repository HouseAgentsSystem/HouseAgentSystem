<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

	<head>
		<title>出租列表</title>
		<meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="/Customer/theme-triton/resources/theme-triton-all_1.css" />
		<link rel="stylesheet" type="text/css" href="/Customer/theme-triton/resources/theme-triton-all_2.css" />
		<link href="/Customer/assets/css/bootstrap.min.css" rel="stylesheet">

		<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
		<!-- Loading Flat UI -->
		<link href="/Customer/dist/css/navbar.css" rel="stylesheet">
		<link href="/Customer/docs/assets/css/demo.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="/Customer/assets/font-awesome/4.2.0/css/font-awesome.min.css" />
		<!--JS-->
		<script type="text/javascript" src="/Customer/js/ext-all.js"></script>
		<script src="/Customer/editWindows.js"></script>
		<script src="/Customer/detailWindows.js"></script>
		<style type="text/css">
			* {
				margin: 0;
				padding: 0;
			}
			/*nav styles*/
			
			.nav ul {
				background: white;
				border-top: 6px solid hsl(180, 40%, 60%);
				width: 200px;
				margin: 5em auto;
			}
			
			.nav ul li {
				list-style-type: none;
				/*relative positioning for list items along with overflow hidden to contain the overflowing ripple*/
				position: relative;
				overflow: hidden;
			}
			
			.nav ul li a {
				font: normal 14px/28px Montserrat;
				color: hsl(180, 40%, 40%);
				display: block;
				padding: 10px 15px;
				text-decoration: none;
				cursor: pointer;
				/*since the links are dummy without href values*/
				/*prevent text selection*/
				user-select: none;
				/*static positioned elements appear behind absolutely positioned siblings(.ink in this case) hence we will make the links relatively positioned to bring them above .ink*/
				position: relative;
			}
			/*.ink styles - the elements which will create the ripple effect. The size and position of these elements will be set by the JS code. Initially these elements will be scaled down to 0% and later animated to large fading circles on user click.*/
			
			.nav .ink {
				display: block;
				position: absolute;
				background: hsl(180, 40%, 80%);
				border-radius: 100%;
				transform: scale(0);
			}
			/*animation effect*/
			
			.nav .ink.animate {
				animation: ripple 0.65s linear;
			}
			
			@keyframes ripple {
				/*scale the element to 250% to safely cover the entire link and fade it out*/
				100% {
					opacity: 0;
					transform: scale(2.5);
				}
			}
			
			.active {
				background-color: #70c2c2;
			}
			
			.red{
				color: red;
			}
			.orange{
				color: orange;
			}
			.blue{
				color: deepskyblue;
			}
			.green{
				color:#00AB1D
			}
			
		</style>
	</head>
	<script>
		Ext.onReady(function() {
			Ext.setGlyphFontFamily('FontAwesome');
			var myStore = Ext.create('Ext.data.Store', {
				storeId: 'simpsonsStore',
				fields: [
				    {type: 'int',name: 'id'},
				    {type: 'date',name: 'applyTime'}
				],
				proxy: {
					type: 'rest',
					url: '/rentapply',
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
			function openEditWindow(grid, rowIndex, colIndex){
			     var record = grid.getStore().getAt(rowIndex);
				//获取选中数据的字段值：console.log(record.get('id')); 或者 console.log(record.data.id);
				if (record ) {
					var win = Ext.getCmp('EditWindow');//.add(Ext.getCmp('orderEditWindow'));
					//console.log(win);
					//var win = Ext.create('orderEditWindow');
					win.show();
					win.down('form').getForm().loadRecord(record);
				}
			};
			function openDetailWindow(grid, rowIndex, colIndex){
			     var record = grid.getStore().getAt(rowIndex);
				//获取选中数据的字段值：console.log(record.get('id')); 或者 console.log(record.data.id);
				if (record ) {
					var win2 = Ext.getCmp('DetailWindow22');//.add(Ext.getCmp('orderEditWindow'));
					//console.log(win);
					//var win = Ext.create('orderEditWindow');
					win2.show();
					win2.down('form').getForm().loadRecord(record);
				}
			};
			function onClickRentedButton(view, recIndex, cellIndex, item, e, record) {
			    Ext.Ajax.request({
			        url: '/rentapply/changeStates/rented/' + record.get('id'),
			        method: 'Get',
			        success: function(response, options) {
			            var json = Ext.util.JSON.decode(response.responseText);
			            if (json.success) {
			                Ext.Msg.alert('操作成功', json.msg, function() {
			                    view.getStore().reload();
			                });
			            } else {
			                Ext.Msg.alert('操作失败', json.msg);
			            }
			        }
			    });
			};
			function onClickDownButton(view, recIndex, cellIndex, item, e, record) {
			    Ext.Ajax.request({
			        url: '/rentapply/changeStates/down/' + record.get('id'),
			        method: 'Get',
			        success: function(response, options) {
			            var json = Ext.util.JSON.decode(response.responseText);
			            if (json.success) {
			                Ext.Msg.alert('操作成功', json.msg, function() {
			                    view.getStore().reload();
			                });
			            } else {
			                Ext.Msg.alert('操作失败', json.msg);
			            }
			        }
			    });
			};
			function onClickCancelButton(view, recIndex, cellIndex, item, e, record) {
			    Ext.Ajax.request({
			        url: '/rentapply/changeStates/cancel/' + record.get('id'),
			        method: 'Get',
			        success: function(response, options) {
			            var json = Ext.util.JSON.decode(response.responseText);
			            if (json.success) {
			                Ext.Msg.alert('操作成功', json.msg, function() {
			                    view.getStore().reload();
			                });
			            } else {
			                Ext.Msg.alert('操作失败', json.msg);
			            }
			        }
			    });
			};
			Ext.create('Ext.Panel', {
				requires: [
					'Ext.grid.Panel',
					'Ext.toolbar.Paging',
					'Ext.grid.column.Date',
					'Ext.grid.column.Date'
				],
				renderTo: 'houseRentGrid',
				height: 800,
				width: 700,
				title: '出租列表',
				items: [{
					xtype: 'gridpanel',
					cls: 'allRecordsCls',
					store: Ext.data.StoreManager.lookup('simpsonsStore'), //storeId
					columns: [{
							header: 'id',
							dataIndex: 'id',
							hidden: true
						},
						{
							header: '申请日期',
							dataIndex: 'applyTime',
							flex: 2,
							renderer: Ext.util.Format.dateRenderer('Y/m/d H:i')
						},
						{
							header: '标题',
							dataIndex: 'title',
							flex: 1
						},
						{
							header: '地址',
							dataIndex: 'address',
							flex: 1
						},
						{
							header: '月租金',
							dataIndex: 'rent',
							flex: 1
						},
						{
							header: '状态',
							dataIndex: 'state',
							renderer:function(val){
								if (val =="APPROVAL") {
						            return '<span style="color:blue;">正在审核</span>';
						        } else if(val == "REFUSE") {
						            return '<span style="color:blue;">审核不通过</span>';
						        } else if(val == "RENTING"){
						        	return '<span style="color:blue;">正在租房</span>';
						        } else if(val == "RENTED"){
						        	return '<span style="color:blue;">已租</span>';
						        } else if(val == "DOWN"){
						        	return '<span style="color:blue;">下架</span>';
						        } else if(val == "CANCEL"){
						        	return '<span style="color:blue;">取消审核</span>';
						        }
						        return val;
							}
						}, {
							xtype: 'actioncolumn',
							cls: 'content-column',
							width: 150,
							text: '操作',
							tooltip: 'edit ',
							value:'',
							items: [{
									//查看
									tooltip: '查看详情',
									xtype: 'button',
									glyph: 0xf002,
									iconCls:'green',
									value:'look',
									handler: openDetailWindow
								},'-',{
									//重新申请
									tooltip: '重新申请',
									xtype: 'button',
									/* glyph: 0xf040, */
									iconCls:'blue',
									value:'edit',
									handler: openEditWindow,
									getClass: function(v, meta, rec) {
							            if (rec.get('state')!='REFUSE') {
							                  return 'x-hidden';
							            }
							            return 'x-fa fa-edit';
									}
								},'-',{
									//设置为已租
									tooltip: '已租',
									xtype: 'button',
									/* glyph: 0xf00d, */
									iconCls:'red',
									value:'delete',
									getClass: function(v, meta, rec) {
							            if (rec.get('state')!='RENTING') {
							                  return 'x-hidden';
							            }
							            return 'x-fa fa-gavel';
									},
									handler: onClickRentedButton
								},'-',{
									//设置为下架
									tooltip: '下架',
									xtype: 'button',
									/* glyph: 0xf05e, */
									iconCls: 'orange',
									value:'',
									getClass: function(v, meta, rec) {
							            if (rec.get('state')!='RENTING' && rec.get('state')!='RENTED') {
							                  return 'x-hidden';
							            }
							            return 'x-fa fa-minus-circle';
									},
									handler: onClickDownButton
								},'-',{
									//取消申请
									tooltip: '取消申请',
									xtype: 'button',
									/* glyph: 0xf05e, */
									iconCls: 'orange',
									value:'',
									getClass: function(v, meta, rec) {
							            if (rec.get('state')!='REFUSE') {
							                  return 'x-hidden';
							            }
							            return 'x-fa fa-ban';
									},
									handler: onClickCancelButton
								}
							]
						}
					],
					dockedItems: [{
						xtype: 'pagingtoolbar',
						dock: 'bottom',
						displayInfo: true,
						bind: Ext.data.StoreManager.lookup('simpsonsStore'),
					}]
				}]
			});
		});
	</script>
	<body style="background-color: #f1f1f1;">
		<div class="row demo-row">
			<div class="col">
				<nav class="navbar navbar-inverse navbar-embossed navbar-expand-lg" role="navigation">
					<div class="container">
						<a class="navbar-brand" href="/Customer/index.jsp">Le Home</a>
						<div class="collapse navbar-collapse" id="navbar-collapse-01">
							<ul class="nav navbar-nav mr-auto">
								<li>
									<a href="/Customer/index.jsp">首页</a>
								</li>
								<li>
									<a href="/Customer/house.jsp"><img src="/Customer/dist/images/卖房.png">买房</a>
								</li>
								<li>
									<a href="/Customer/houserent.jsp"><img src="/Customer/dist/images/租房.png">租房</a>
								</li>
								<li style="width:140px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<c:if test="${not empty sessionScope.user.realname}">
									<li><a href="/showUser/information">${sessionScope.user.realname}<img src="/Customer/upload/user/${sessionScope.user.faceImage}" style="width:30px;height:30px;border-radius:15px;"></a></li>
									<li>
										<a href="#" onclick="logout()">退出</a>
									</li>
								</c:if>
								<c:if test="${empty sessionScope.user.realname}">
									<li>
										<a href="/Customer/login&registration.html">登录/注册</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</nav>

			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="nav col-sm-3">
					<ul style="border:0.5px solid #c7c7c7;">
						<li>
							<a href="/showUser/information">个人资料</a>
						</li>
						<li>
							<a href="/showUser/addHouseRent">我要出租</a>
						</li>
						<li>
							<a class="active" href="/showUser/houseRentGrid">我的出租</a>
						</li>
					</ul>
				</div>
				<div class="col-sm-9">
					<div id="houseRentGrid"></div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="/Customer/assets/js/jquery.2.1.1.min.js"></script>
	<script type="text/javascript">
		//jQuery time
		var parent, ink, d, x, y;
		$(".nav ul li a").click(function(e) {
			parent = $(this).parent();
			//create .ink element if it doesn't exist
			if(parent.find(".ink").length == 0)
				parent.prepend("<span class='ink'></span>");

			ink = parent.find(".ink");
			//incase of quick double clicks stop the previous animation
			ink.removeClass("animate");

			//set size of .ink
			if(!ink.height() && !ink.width()) {
				//use parent's width or height whichever is larger for the diameter to make a circle which can cover the entire element.
				d = Math.max(parent.outerWidth(), parent.outerHeight());
				ink.css({
					height: d,
					width: d
				});
			}

			//get click coordinates
			//logic = click coordinates relative to page - parent's position relative to page - half of self height/width to make it controllable from the center;
			x = e.pageX - parent.offset().left - ink.width() / 2;
			y = e.pageY - parent.offset().top - ink.height() / 2;

			//set the position and add class .animate
			ink.css({
				top: y + 'px',
				left: x + 'px'
			}).addClass("animate");
		})
	</script>
	<script src="/Customer/assets/js/jquery.2.1.1.min.js"></script>
		<script>
			function logout(){
				$.ajax({
		                type: 'post',
		                url: "/users/logout",
		                cache: false,
		                processData: false,
		                contentType: false,
		            }).success(function (data) {
		            	window.location.reload();
		            }).error(function () {
		        });
			}
	</script>
</html>