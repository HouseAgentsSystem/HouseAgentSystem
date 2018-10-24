<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta name="description" content="Flat UI Kit Free is a Twitter Bootstrap Framework design and Theme, this responsive framework includes a PSD and HTML version." />

		<meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">

		<!-- Loading Bootstrap -->
		<link href="http://localhost:8081/Customer/dist/css/vendor/bootstrap.min.css" rel="stylesheet">

		<!-- Loading Flat UI -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<!-- <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
		<link href="http://localhost:8081/Customer/dist/css/navbar.css" rel="stylesheet">
		<link rel="shortcut icon" href="http://localhost:8081/Customer/dist/favicon.ico">
		<style type="text/css">
			h3{color: #2b5454;}
			p{margin-left:5px; font-size:18px;}
		</style>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=VyA3AiLqbNrhGrsa4DAACgaiCOA7V9uo"></script>
		<meta charset="UTF-8">
		<title>详情</title>
	</head>

	<body>
		<div class="row demo-row">
				<div class="col">
					<nav class="navbar navbar-inverse navbar-embossed navbar-expand-lg" role="navigation">
						<div class="container">
							<a class="navbar-brand" href="../Customer/index.jsp">Le Home</a>
							<div class="collapse navbar-collapse" id="navbar-collapse-01">
								<ul class="nav navbar-nav mr-auto">
									<li>
										<a href="../Customer/index.jsp">首页</a>
									</li>
									<li>
										<a href="../Customer/house.jsp"><img src="http://localhost:8081/Customer/dist/images/卖房.png">买房</a>
									</li>
									<li>
										<a href="../Customer/houserent.jsp"><img src="http://localhost:8081/Customer/dist/images/租房.png">租房</a>
									</li>
									<li style="width:360px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
									<c:if test="${not empty sessionScope.user.realname}">
											<li><a href="../showUser/information?id=${sessionScope.user.id}">${sessionScope.user.realname}<img src="../Customer/upload/user/${sessionScope.user.faceImage}" style="width:30px;height:30px;border-radius:15px;"></a></li>
										<li>
											<a href="#" onclick="logout()">退出</a>
										</li>
									</c:if>
									<c:if test="${empty sessionScope.user.realname}">
										<li>
											<a href="../Customer/login&registration.html">登录/注册</a>
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
				<b><h3>${requestScope.house.title}</h3></b>
			</div>
			<div class="row">
				<b><p style='font-size:20px;'><span style="color: red;">${requestScope.house.price}万</span><em>&nbsp;|&nbsp;</em><span>${requestScope.house.area}平方米</span><em>&nbsp;|&nbsp;</em><span>${requestScope.house.buildDate}年建造</span><em>&nbsp;|&nbsp;</em><span>${requestScope.house.room}房${requestScope.house.hall}厅</span><em>&nbsp;|&nbsp;</em><span>${requestScope.house.buildDate}年建造</span><em>&nbsp;|&nbsp;</em><span>${requestScope.house.region}</span></p></b>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-7" style="border:0.5px solid #c7c7c7;">
					<h4>房屋情况</h4><hr>
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					  <!-- Indicators -->
					  
					
					  <!-- Wrapper for slides -->
					  <div class="carousel-inner" role="listbox" style="height: 400px;">
					    <div class="item active">
					      <video
				            id="my-player"
				            class="video-js"
				            controls
				            preload="auto"
				            data-setup='{}' style="height: 400px; width: 100%;">
				            <source src="../Customer/upload/house/${requestScope.house.video}" type="video/mp4"></source>
				           <!-- <source src="http://iurevych.github.com/Flat-UI-videos/big_buck_bunny.webm" type="video/webm"></source>-->
				            <p class="vjs-no-js">
				              To view this video please enable JavaScript, and consider upgrading to a
				              web browser that
				              <a href="http://videojs.com/html5-video-support/" target="_blank">
				                supports HTML5 video
				              </a>
				            </p>
				          </video>
					    </div>
					    <c:forEach items="${requestScope.house.imgs}" var="img" varStatus="id">    
							<div class="item">
						      <img src="../Customer/upload/house/${img}" alt="..." style="height: 400px; width: 100%;">
						    </div>
					    </c:forEach>
					  </div>
					
					  <!-- Controls -->
					  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					    <span class="sr-only">Previous</span>
					  </a>
					  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					    <span class="sr-only">Next</span>
					  </a>
					</div>
				</div>
				<div class="col-md-offset-1 col-md-4">
				    <div class="thumbnail">
				      <h4>经纪人信息</h4><hr>
				      <img src="/Customer/upload/staff/${requestScope.house.faceImg}" alt="..." style="height: 250px;">
				      <div class="caption">
				        <h4><a href="/showStaffStore/getStaff/${requestScope.house.staffId}">姓名：${requestScope.house.staffName}</a></h4>
				        <h4>手机：${requestScope.house.phoneNumber}</h4>
				        <h4><a href="/showStore/getStore/${requestScope.house.storeId}">所属门店：${requestScope.house.storeName}</a></h4>
				      </div>
				    </div>
				  </div>
			</div><br><br><br>
			<div class="row">
				<div class="container">
					<div style="border:0.5px solid #c7c7c7; width: 100%;">
						<h4>房屋信息</h4><hr>
						<table width="100%">
							<tr>
								<td width="50%"><p>房屋户型：${requestScope.house.room}室${requestScope.house.hall}厅</p></td>
								<td width="50%"><p>房屋价格：${requestScope.house.price}万</p></td>
							</tr>
							<tr>
								<td width="50%"><p>房屋面积：${requestScope.house.area}平方米</p></td>
								<td width="50%"><p>房屋朝向：${requestScope.house.orientation}</p></td>
							</tr>
							<tr>
								<td width="50%"><p>装修程度：${requestScope.house.decorateLevel}</p></td>
								<td width="50%"><p>房屋年限：满${requestScope.house.propertyRights}年</p></td>
							</tr>
							<tr>
								<td width="50%"><p>建造年代：${requestScope.house.buildDate}年</p></td>
								<td width="50%"><p>房屋类型：${requestScope.house.type}</p></td>
							</tr>
							<tr>
								<td width="50%"><p>房屋楼层：${requestScope.house.floor}楼</p></td>
								<td width="50%"><p>是否有电梯：${requestScope.house.isElevator}</p></td>
							</tr>
							<tr width="100%"><p>详细地址：${requestScope.house.address}</p></tr>
						</table>
					</div>
				</div>
			</div>
			<hr>
			<div class="row" >
				<div class="col-md-7" style="border:0.5px solid #c7c7c7;">
					<h4>周边情况</h4><hr>
					<div style="width: 100%;">
						<div id="allmap" style="height: 400px; width:100%;"></div>
						<br>
					</div>
				</div>
				
				<div class="col-md-4 col-md-offset-1" style="border:0.5px solid #c7c7c7;">
					<h4>核心卖点</h4><hr>
					 <p>${requestScope.house.introduce}</p>
				</div>
			</div>
		</div>
		<br><br><br>
		<div class="row demo-row">
				<div class="col" style="background-color: rgba(222,222,222,0.48)">
					<br><br>
					<p align="center">邮箱 service6LomeHome@qq.com   Copyright © 2018-20** www.LeHome.com All Rights Reserved ICP号：粤 B2-26666668</p>
					<br><br>
				</div>
		</div>
		<script src="../Customer/assets/js/jquery.2.1.1.min.js"></script>
	    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	    <!-- <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	    <script type="text/javascript">
			// 百度地图API功能
			var map = new BMap.Map("allmap");// 创建Map实例
			var mPoint = new BMap.Point(${requestScope.house.longitude},${requestScope.house.latitude});  
			
			var icon=new BMap.Icon("../Customer/docs/assets/images/house.png", new BMap.Size(32,32));
			var marker = new BMap.Marker(mPoint,{icon:icon});
			var traffic = new BMap.TrafficLayer();      
			map.addTileLayer(traffic);  
			map.enableScrollWheelZoom();
			map.centerAndZoom(mPoint,15);
			map.enableScrollWheelZoom(true);
			map.addControl(new BMap.NavigationControl());
			map.addControl(new BMap.NavigationControl());    
			map.addControl(new BMap.ScaleControl());
			map.addControl(new BMap.OverviewMapControl());
			map.addControl(new BMap.MapTypeControl());
			var circle = new BMap.Circle(mPoint,1000,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
		    map.addOverlay(circle);
			map.addOverlay(marker);
		    var local =  new BMap.LocalSearch(map, {renderOptions: {map: map, autoViewport: false}});  
		    var myKeys = ["公交站"];
			local.searchNearby(myKeys,mPoint,1000);
			var school =  new BMap.LocalSearch(map, {renderOptions: {map: map, autoViewport: false}});  
		    school.searchNearby('学校',mPoint,1000);
		
			marker.setAnimation(BMAP_ANIMATION_BOUNCE);
		</script>
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
	</body>

</html>