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
		<link rel="stylesheet" type="text/css" href="/Customer/assets/font-awesome/4.2.0/css/font-awesome.min.css"/>
		<style type="text/css">
			h3{color: #2b5454;}
			p{margin: 5px; font-size:15px; letter-spacing: 1.5px;}
			.detail{color: #797979;}
			.detail span{color: black; font-size:18px;}
			.titiles span{font-size:22px;}
			em{color: #e6e6e6;}
			.supporting img{width: 90%;}
			.supporting p{text-align:center;font-size:13px;}
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
							<a class="navbar-brand" href="#">Flat UI</a>
							<div class="collapse navbar-collapse" id="navbar-collapse-01">
								<ul class="nav navbar-nav mr-auto">
									<li>
										<a href="#fakelink">首页</a>
									</li>
									<li>
										<a href="/Customer/house.html"><img src="http://localhost:8081/Customer/dist/images/卖房.png">买房</a>
									</li>
									<li>
										<a href="/Customer/houserent.jsp"><img src="http://localhost:8081/Customer/dist/images/租房.png">租房</a>
									</li>
									<li>
										<a href=""><img src="http://localhost:8081/Customer/dist/images/出租.png">我要出租</a>
									</li>
								</ul>
							</div>
						</div>
					</nav>
				</div>
		</div>
		<div class="container">
			<div class="row">
				<b><h3>${requestScope.houseRent.title}</h3></b>
			</div>
			<div class="row">
				<b><p style="" class="titiles">
					<span style="color: #eb5f00;">${requestScope.houseRent.rent}<span style="font-size: 15px;">元/月</span></span><em>&nbsp;|&nbsp;</em>
					<span>${requestScope.houseRent.area}</span>平方米<em>&nbsp;|&nbsp;</em>
					<span>${requestScope.houseRent.room}</span>房<span>${requestScope.houseRent.hall}</span>厅<em>&nbsp;|&nbsp;</em>
					<span>${requestScope.houseRent.region}</span>&nbsp;&nbsp;
					<span style="color: #e88a78; background-color: #fcf4f1; border-radius: 16px; padding: 3px 9px; font-size: 13px;">${requestScope.houseRent.rentType}</span>
				</p></b>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-8" style="border:0.5px solid #c7c7c7;">
					<h4>租房展示</h4><hr>
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
				            <source src="/Customer/video/${requestScope.houseRent.video}" type="video/mp4"></source>
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
					    <c:forEach items="${requestScope.houseRent.imgs}" var="img" varStatus="id">    
							<div class="item">
						      <img src="/Customer/images/${img}" alt="..." style="height: 400px; width: 100%;">
						    </div>
					    </c:forEach>
					  </div>
					
					  <!-- Controls -->
					  <a style="color:#34495e;" class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					    <span class="sr-only">Previous</span>
					  </a>
					  <a style="color:#34495e;" class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					    <span class="sr-only">Next</span>
					  </a>
					</div><br>
				</div>
				<div class="col-md-offset-0 col-md-4">
				    <div class="thumbnail">
				      <h4>房东信息</h4><hr>
				      <img src="/Customer/images/${requestScope.houseRent.userFaceImage}" alt="..." style="height: 250px;width: 200px">
				      <div class="caption">
				        <h4>姓名：${requestScope.houseRent.userRealname}</h4>
				        <h4>手机：${requestScope.houseRent.userPhoneNumber}</h4>
				      </div>
				    </div>
				  </div>
			</div><br><br>
			<div class="row">
				<div class="container col-md-offset-0 col-md-8" style="">
					<div>
						<h4>租房信息</h4><hr>
						<table width="100%">
							<tr>
								<td><p style="color:#eb5f00;font-weight:600;"><span style="font-weight:750; font-size: 20px;">${requestScope.houseRent.rent}</span>&nbsp;元/月</p></td>
								</tr>
							<tr>
								<td><p class="detail">租房户型：<span>${requestScope.houseRent.room}室${requestScope.houseRent.hall}厅</span></p></td>
								<td><p class="detail">租房面积：<span>${requestScope.houseRent.area}平方米</span></p></td>
								<td><p class="detail">租房类型：<span>${requestScope.houseRent.rentType}</span></p></td>
							</tr>
							<tr>
								<td><p class="detail">租房楼层：<span>${requestScope.houseRent.floor}楼</span></p></td>
								<td><p class="detail">详细地址：<span>${requestScope.houseRent.address}</span></p></td>
							</tr>
							<tr><td><br></td></tr>
						</table>
					</div>
				</div>
			</div>
			
			<div class="row" >
				<div class="col-md-12">
					<h4>配套设施</h4><div class="col-md-8" style="padding-left: 0;"><hr style="margin-top: 5px;"></div><div class="col-md-4" style="height: 41px;"></div>
					<div class="col-md-1 supporting">
						<img src="/Customer/images/supporting/${requestScope.houseRent.isBed}Bed.png" alt="">
						<p>床</p>
					</div>
					<div class="col-md-1 supporting">
						<img src="/Customer/images/supporting/${requestScope.houseRent.isWasher}Washer.png" alt="">
						<p>洗衣机</p>
					</div>
					<div class="col-md-1 supporting">
						<img src="/Customer/images/supporting/${requestScope.houseRent.isAirConditioning}AirConditioning.png" alt="">
						<p>空调</p>
					</div>
					<div class="col-md-1 supporting">
						<img src="/Customer/images/supporting/${requestScope.houseRent.isBalcony}Balcony.png" alt="">
						<p>阳台</p>
					</div>
					<div class="col-md-1 supporting">
						<img src="/Customer/images/supporting/${requestScope.houseRent.isRefrigerator}Refrigerator.png" alt="">
						<p>冰箱</p>
					</div>
					<div class="col-md-1 supporting">
						<img src="/Customer/images/supporting/${requestScope.houseRent.isToilet}Toilet.png" alt="">
						<p>厕所</p>
					</div>
					<div class="col-md-1 supporting">
						<img src="/Customer/images/supporting/${requestScope.houseRent.isKitchen}Kitchen.png" alt="">
						<p>厨房</p>
					</div>
					<div class="col-md-1 supporting">
						<img src="/Customer/images/supporting/${requestScope.houseRent.isCalorifier}Calorifier.png" alt="">
						<p>热水器</p>
					</div>
				</div>
			</div>
			<br>
			<div class="row" >
				<div class="col-md-8 col-md-offset-0" style="">
					<h4>租房描述</h4><hr>
					 <p class="col-md-11">${requestScope.houseRent.introduce}</p>
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
		<script src="/Customer/assets/js/jquery.2.1.1.min.js"></script>
	    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	</body>
</html>