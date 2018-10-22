<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<title>我要出租</title>
		<meta charset="utf-8" />
		<link href="../Customer/assets/css/bootstrap.min.css" rel="stylesheet">

		<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
		<!-- Loading Flat UI -->
		<link href="../Customer/dist/css/navbar.css" rel="stylesheet">
		<link href="../Customer/docs/assets/css/demo.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />
		<!--JS-->
		<script type="text/javascript" src="js/ext-all.js"></script>
		<script src="editWindows.js"></script>
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
	
	<body style="background-color: #f1f1f1;">
		<div class="row demo-row">
			<div class="col">
				<nav class="navbar navbar-inverse navbar-embossed navbar-expand-lg" role="navigation">
					<div class="container">
						<a class="navbar-brand" href="#">乐家 Home</a>
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
								<li style="width:160px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<c:if test="${not empty sessionScope.user.realname}">
									<li>
										<a href="../showUser/information?id=${sessionScope.user.id}">${sessionScope.user.realname}<img src="../Customer/upload/user/${sessionScope.user.faceImage}" style="width:30px"></a>
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
			<div class="row">
				<div class="nav col-sm-3">
					<ul style="border:0.5px solid #c7c7c7;">
						<li>
							<a href="../showUser/information?id=${sessionScope.user.id}">个人资料</a>
						</li>
						<li>
							<a class="active" href="../Customer/addHouseRent.jsp">我要出租</a>
						</li>
						<li>
							<a href="../Customer/houseRentGrid.jsp">我的出租</a>
						</li>
					</ul>
				</div>
				<div class="col-sm-9">
					<div style=" border: solid #000000 1px; width: 800px; height: 1400px;" >
						<iframe src="houseRentAddForm.jsp" width="100%" height="100%" frameborder="0"></iframe>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="../Customer/assets/js/jquery.2.1.1.min.js"></script>
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

</html>