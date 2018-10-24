<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>乐家Home</title>
		<link href="http://localhost:8081/Customer/dist/css/vendor/bootstrap.min.css" rel="stylesheet">

		<!-- Loading Flat UI -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<!-- <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
		<link href="http://localhost:8081/Customer/dist/css/navbar.css" rel="stylesheet">
		<link rel="shortcut icon" href="http://localhost:8081/Customer/dist/favicon.ico">
		<style type="text/css">
			h3 {
				color: #2b5454;
			}
			
			p {
				margin-left: 5px;
				font-size: 18px;
			}
		</style>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=VyA3AiLqbNrhGrsa4DAACgaiCOA7V9uo"></script>
	</head>

	<body>
		<div class="row demo-row">
			<div class="col">
				<nav class="navbar navbar-inverse navbar-embossed navbar-expand-lg" role="navigation">
					<div class="container">
						<a class="navbar-brand" href="index.jsp">Le Home</a>
						<div class="collapse navbar-collapse" id="navbar-collapse-01">
							<ul class="nav navbar-nav mr-auto">
								<li style="margin-left: ;">
									<a href="index.jsp">首页</a>
								</li>
								<li>
									<a href="../Customer/house.jsp"><img src="http://localhost:8081/Customer/dist/images/卖房.png">买房</a>
								</li>
								<li>
									<a href="login.html"><img src="http://localhost:8081/Customer/dist/images/租房.png">租房</a>
								</li>
								<li style="width:360px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<c:if test="${not empty sessionScope.user.realname}">
									<li>
										<a href="../showUser/information?id=${sessionScope.user.id}">${sessionScope.user.realname}<img src="../Customer/upload/user/${sessionScope.user.faceImage}" style="width:30px;height:30px;border-radius:15px;"></a>
									</li>
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
			<h3>优质房源></h3>
			<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="border:1px solid #eee;">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="item">
						<div class="row" style="width: 100%; height: 500px;">
							<div style="width: 50%; height: 500px;">
								<img src="images/1.jpg" alt="..." style="height: 450px; width: 100%;">
								<h4 align="center"><a href="#">伴山花园</a></h4>
							</div>
							<div style="width: 47%; height: 500px; margin-left: 30px;">
								<div class="row" style="height: 45%;">
									<div style="width: 45%; ">
										<img src="images/3.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">中核·锦悦府：建面约95-160㎡的三房</a></h5>
									</div>
									<div style="width: 45%; margin-left: 20px;">
										<img src="images/4.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">家族荣誉︱传世府邸 10月下旬将加推</a></h5>
									</div>
								</div>
								<div class="row" style="height: 45%;">
									<div style="width: 45%;">
										<img src="images/5.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">人生进阶︱公望街地铁旁府藏天幕公馆</a></h5>
									</div>
									<div style="width: 45%; margin-left: 20px; ">
										<img src="images/6.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">良渚新城精工水岸成品洋房 中粮梦栖祥云将</a></h5>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="row" style="width: 100%; height: 500px;">
							<div style="width: 50%; height: 500px;">
								<img src="images/2.jpg" alt="..." style="height: 450px; width: 100%;">
								<h4 align="center"><a href="#">遥看珠水百滘韵致 美的绿城凤起兰庭城墅鉴</a></h4>
							</div>
							<div style="width: 47%; height: 500px; margin-left: 30px;">
								<div class="row" style="height: 45%;">
									<div style="width: 45%; ">
										<img src="images/8.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">美林湖3成首付19万起</a></h5>
									</div>
									<div style="width: 45%; margin-left: 20px;">
										<img src="images/9.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">万亿恒大醇熟打造！</a></h5>
									</div>
								</div>
								<div class="row" style="height: 45%;">
									<div style="width: 45%;">
										<img src="images/10.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">城市的山，心中的墅丨万科元培里主城唯墅新</a></h5>
									</div>
									<div style="width: 45%; margin-left: 20px; ">
										<img src="images/11.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">充满奋斗朝气，拥抱生活元气</a></h5>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="item active">
						<div class="row" style="width: 100%; height: 500px;">
							<div style="width: 50%; height: 500px;">
								<img src="images/山水江南.jpg" alt="..." style="height: 450px; width: 100%;">
								<h4 align="center"><a href="#">蓝光雍锦湾：打造的墅质社区</a></h4>
							</div>
							<div style="width: 47%; height: 500px; margin-left: 30px;">
								<div class="row" style="height: 45%;">
									<div style="width: 45%; ">
										<img src="images/恒大.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">戛纳湾：不限购物业 民水民电带家电交付</a></h5>
									</div>
									<div style="width: 45%; margin-left: 20px;">
										<img src="images/莞深城.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">大华碧云天：醇熟中环 鎏金生活</a></h5>
									</div>
								</div>
								<div class="row" style="height: 45%;">
									<div style="width: 45%;">
										<img src="images/恒大江湾.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">宝华帝华园：90㎡户型鉴赏</a></h5>
									</div>
									<div style="width: 45%; margin-left: 20px; ">
										<img src="images/碧桂园沙田.jpg" alt="..." style="height: 192px; width: 100%;">
										<h5 align="center"><a href="#">绿地御景潮源：“潮”起南北湖 “源”价</a></h5>
									</div>
								</div>
							</div>
						</div>
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
			<hr>
			<h3>最新租房</h3>
			<hr>
			<div class="row" style="width: 100%; height: 500px; margin-left: 5px;">
					<div class="" style="width: 25%;">
						<div class="thumbnail">
							<img src="images/24.jpg" alt="...">
							<div class="caption">
								<h4>业主诚心出租精装3房</h4>
								<p>桥头镇 - 桥头</p>
								<h3 style="color: red;">800/月</h3>
							</div>
						</div>
					</div>
					<div class="" style="width: 25%;">
						<div class="thumbnail">
							<img src="images/21.jpg" alt="...">
							<div class="caption">
								<h4>功苑广场业主诚心出租</h4>
								<p>常平镇 - 常平火车站</p>
								<h3 style="color: red;">2600/月</h3>
							</div>
						</div>
					</div>
					<div class="" style="width: 25%;">
						<div class="thumbnail">
							<img src="images/23.jpg" alt="...">
							<div class="caption">
								<h4>大朗碧桂园南北朝向好</h4>
								<p>大朗镇 - 大朗</p>
								<h3 style="color: red;">2800/月</h3>
							</div>
						</div>
					</div>
					<div class="" style="width: 25%;">
						<div class="thumbnail">
							<img src="images/22.jpg" alt="...">
							<div class="caption">
								<h4>虎门公园旁金色家园小区</h4>
								<p>虎门镇 - 虎门公园</p>
								<h3 style="color: red;">3000/月</h3>
							</div>
						</div>
					</div>
			</div>
			<hr>
			<h3>关于我们></h3>
			<hr>
			<div class="row">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="images/房子.png" alt="...">
						<div class="caption">
							<h3 style="color: #009CEB;">代售房源数 : <b>80</b></h3>

						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="images/租房.png" alt="...">
						<div class="caption">
							<h3 style="color: #009CEB;">代售出租数 : <b>80</b></h3>

						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="images/交易成功.png" alt="...">
						<div class="caption">
							<h3 style="color: #009CEB;">成功交易 : <b>30例</b></h3>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row demo-row">
				<div class="col" style="background-color: rgba(222,222,222,0.48)">
					<br><br>
					<p align="center">邮箱 service6LomeHome@qq.com   Copyright © 2018-20** www.LeHome.com All Rights Reserved ICP号：粤 B2-26666668</p>
					<br><br>
				</div>
		</div>
	</body>
	<script type="text/javascript" src="js/jquery.2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
</html>