<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta name="description" content="Flat UI Kit Free is a Twitter Bootstrap Framework design and Theme, this responsive framework includes a PSD and HTML version." />

		<meta name="viewport" content="width=1000, initial-scale=1.0, maximum-scale=1.0">

		<!-- Loading Bootstrap -->
		<link href="http://localhost:8081/Customer/dist/css/vendor/bootstrap.min.css" rel="stylesheet">

		<!-- Loading Flat UI -->
		<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
						<a class="navbar-brand" href="#">Flat UI</a>
						<div class="collapse navbar-collapse" id="navbar-collapse-01">
							<ul class="nav navbar-nav mr-auto">
								<li>
									<a href="#fakelink">首页</a>
								</li>
								<li>
									<a href="house.html"><img src="../Admin/resources/images/卖房.png">买房</a>
								</li>
								<li>
									<a href="login.html"><img src="../Admin/resources/images/租房.png">租房</a>
								</li>
								<li>
									<a href=""><img src="../Admin/resources/images/出租.png">我要出租</a>
								</li>
							</ul>
						</div>
					</nav>
				</div>
		</div>
		<div class="container">
			<div class="row">
				<b><h3>${requestScope.house.title}</h3></b>
			</div>
			<div class="row">
				<b><p style='font-size:20px;'><span style="color: red;">${requestScope.house.price}万</span><em>&nbsp;|&nbsp;</em><span></span><em>&nbsp;|&nbsp;</em><span>${requestScope.house.area}平方米</span><em>&nbsp;|&nbsp;</em><span>2009年建造</span><em>&nbsp;|&nbsp;</em></p></b>
			</div>
			<div class="row">
				<div class="col-md-7" style="background-color: #f1f1f1;">
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					  <!-- Indicators -->
					  <ol class="carousel-indicators">
					    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
					    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
					    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
					    <li data-target="#carousel-example-generic" data-slide-to="4"></li>
					    <li data-target="#carousel-example-generic" data-slide-to="5"></li>
					  </ol>
					
					  <!-- Wrapper for slides -->
					  <div class="carousel-inner" role="listbox" style="height: 400px;">
					    <div class="item active">
					      <video
				            id="my-player"
				            class="video-js"
				            controls
				            preload="auto"
				            poster="../Customer/docs/assets/images/video/poster.jpg"
				            data-setup='{}' style="height: 400px; width: 100%;">
				            <source src="../Customer/docs/assets/images/video/poster.mp4" type="video/mp4"></source>
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
					    <div class="item">
					      <img src="../Admin/${requestScope.house.images}" alt="..." style="height: 400px; width: 100%;">
					    </div>
					    <div class="item">
					      <img src="../Admin/resources/images/img3.jpg" alt="..." >
					    </div>
					    <div class="item">
					      <img src="../Admin/resources/images/img4.jpg" alt="..." >
					    </div>
					    <div class="item">
					      <img src="../Admin/resources/images/img2.jpg" alt="..." >
					    </div>
					    <div class="item">
					      <img src="../Admin/resources/images/img3.jpg" alt="..." >
					    </div>
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
				<div class="col-md-5" style="background-color: #f1f1f1;">
					<b><h3>房屋信息</h3></b>
					<table width="100%">
						<tr>
							<td width="50%"><p>房屋户型：${requestScope.house.room}室${requestScope.house.hall}厅</p></td>
							<td width="50%"><p>房屋价格：${requestScope.house.price}万</p></td>
						</tr>
						<tr>
							<td width="50%"><p>房屋面积：${requestScope.house.area}平方米</p></td>
							<td width="50%"><p>房屋朝向：南</p></td>
						</tr>
						<tr>
							<td width="50%"><p>装修程度：精装修</p></td>
							<td width="50%"><p>房屋年限：满5年</p></td>
						</tr>
						<tr>
							<td width="50%"><p>建造年代：2009年</p></td>
							<td width="50%"><p>房屋类型：普通住宅</p></td>
						</tr>
						<tr>
							<td width="50%"><p>房屋权限：70年</p></td>
							<td width="50%"><p>房屋性质：商品房</p></td>
						</tr>
						<tr>
							<td width="50%"><p>房屋楼层：15楼</p></td>
							<td width="50%"><p>是否有电梯：是</p></td>
						</tr>
						<tr width="100%"><p>详细地址：${requestScope.house.address}</p></tr>
					</table>
				</div>
			</div><br><br><br>
			<div class="row" >
				<div class="col-md-7" style="background-color: #f1f1f1;">
					<h4>周边情况</h4><br>
					<div style="width: 100%;">
						<div id="allmap" style="height: 400px; width:100%;"></div>
						<br>
					</div>
				</div>
				<div class="col-md-5" style="background-color: #f1f1f1;">
					<h4>核心卖点</h4><br><br>
					 1、采光明厨明厅，采光，视野无遮挡，阳光清风自由来。<br>
						2、户型正气南北通透，大气实用，户型设计合理，使用面积达90％<br>
						3、房间景观毎户赠送入户花园，客厅双阳台，视野广看花园。<br>
						【楼盘配套】<br>
						1、交通便捷---15分到达口，30分深圳市生活圈。<br>
						2、商业市场---位于商业购物，五酒店，篮球场等<br>
						3、配套成熟---自带幼儿园到高中三即到，银行，医院都已入驻。<br>
						房东置换，诚心出售，欢迎看房，随时恭候您的到来。<br>
						小区环境优雅，配套成熟，交通便利，居住舒适！<br>
				</div>
			</div>
		</div>
		<br><br><br>
		<div class="row demo-row">
				<div class="col" style="background-color: rgba(222,222,222,0.48)">
					<br><br>
					<p align="center">邮箱 service@58ganji.com   Copyright © 2018-20** www.LeHome.com All Rights Reserved ICP号：粤 B2-20090008</p>
					<br><br>
				</div>
		</div>
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	    <script type="text/javascript">
			// 百度地图API功能
			var map = new BMap.Map("allmap");            // 创建Map实例
			var mPoint = new BMap.Point((${requestScope.house.longitude}),(${requestScope.house.latitude}));  
			
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
	</body>

</html>