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
						<a class="navbar-brand" href="#">Flat UI</a>
						<div class="collapse navbar-collapse" id="navbar-collapse-01">
							<ul class="nav navbar-nav mr-auto">
								<li>
									<a href="#fakelink">首页</a>
								</li>
								<li>
									<a href="../Customer/house.jsp"><img src="http://localhost:8081/Customer/dist/images/卖房.png">买房</a>
								</li>
								<li>
									<a href="login.html"><img src="http://localhost:8081/Customer/dist/images/租房.png">租房</a>
								</li>
								<li>
									<a href=""><img src="http://localhost:8081/Customer/dist/images/出租.png">我要出租</a>
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
		<div class="container" style="height: 500px;">
			<h3>推荐房源></h3>
			<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="upload/house/1.jpg" alt="..." style="height: 500px; width: 100%;">
						<div class="carousel-caption">
							<h2 style="color: #009CEB;">震惊，东莞松山湖高档小区急售，100平售价仅需</h2>
							<h1 style="color: red;">500万</h1>
						</div>
					</div>
					<div class="item">
						<img src="upload/house/2.jpg" alt="..." style="height: 500px; width: 100%;">
						<div class="carousel-caption">
							...
						</div>
					</div>
					<div class="item">
						<img src="upload/house/3.jpg" alt="..." style="height: 500px; width: 100%;">
						<div class="carousel-caption">
							...
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
			      <img src="..." alt="...">
			      <div class="caption">
			        <h3>Thumbnail label</h3>
			        <p>...</p>
			        <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="js/jquery.2.1.1.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</html>