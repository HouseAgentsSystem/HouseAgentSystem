<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

	<head>
		<title>个人资料</title>
		<meta charset="utf-8" />
		<link href="../Customer/assets/css/bootstrap.min.css" rel="stylesheet">

		<!-- Loading Flat UI -->
		<link href="../Customer/dist/css/navbar.css" rel="stylesheet">
		<link href="../Customer/docs/assets/css/demo.css" rel="stylesheet">
		<!--JS-->
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
									<a href="../Customer/house.html"><img src="http://localhost:8081/Customer/dist/images/卖房.png">买房</a>
								</li>
								<li>
									<a href="login.html"><img src="http://localhost:8081/Customer/dist/images/租房.png">租房</a>
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
				<div class="nav col-sm-3">
					<ul style="border:0.5px solid #c7c7c7;">
						<li>
							<a class="active">个人资料</a>
						</li>
						<li>
							<a>我要出租</a>
						</li>
						<li>
							<a href="../Customer/houseRentGrid.html">我的出租</a>
						</li>
					</ul>
				</div>
				<div class="col-sm-9" style="height: 600px;border:0.5px solid #c7c7c7; background-color: #FFFFFF;">
					<h4>个人信息</h4>
					<hr>
					<form>
						<table>
							<tr>
								<td><h4 style="line-height: 50px;text-indent:5em;">头 像：</h4></td>
								<td style="text-indent:4em;">
									<img src="../Customer/images/${requestScope.user.faceImage}" alt="..." class="img-circle img-thumbnail" style="width: 100px; height: 100px;" id="xmTanImg">
									<input type="file"id="xdaTanFileImg" onchange="xmTanUploadImg(this)" accept="image/*">
									<input type="text" id="imgUrl" value="${requestScope.user.faceImage}"/>
								</td>
							</tr>
							<tr>
								<td><h4 style="line-height: 50px;text-indent:5em;">用户名：</td></td>
								<td><input type="text" value="${requestScope.user.userName}" class="form-control"/ ></td>
							</tr>
							<tr>
								<td><h4 style="line-height: 50px;text-indent:5em;">真实姓名：</td></td>
								<td><input type="text" value="${requestScope.user.realname}" class="form-control"/ ></td>
							</tr>
							<tr>
								<td><h4 style="line-height: 50px;text-indent:5em;">手机号码：</td></td>
								<td><input type="text" value="${requestScope.user.phoneNumber}" class="form-control"/ ></td>
							</tr>
							<tr>
								<td></td>
								<td><button class="btn btn-primary">提交</button></td>
							</tr>
						</table>
						
					</form>
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
	<script type="text/javascript">            
            //判断浏览器是否支持FileReader接口
            if (typeof FileReader == 'undefined') {
                document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
                //使选择控件不可操作
                document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
            }

            //选择图片，马上预览
            function xmTanUploadImg(obj) {
                var file = obj.files[0];
                
                console.log(obj);console.log(file);
                console.log("file.size = " + file.size);  //file.size 单位为byte

                var reader = new FileReader();

                //读取文件过程方法
                reader.onloadstart = function (e) {
                    console.log("开始读取....");
                }
                reader.onprogress = function (e) {
                    console.log("正在读取中....");
                }
                reader.onabort = function (e) {
                    console.log("中断读取....");
                }
                reader.onerror = function (e) {
                    console.log("读取异常....");
                }
                reader.onload = function (e) {
                    console.log("成功读取....");

                    var img = document.getElementById("xmTanImg");
                    img.src = e.target.result;
                    document.getElementById("imgUrl").value=document.getElementById('xdaTanFileImg').value;
                    //或者 img.src = this.result;  //e.target == this
                }

                reader.readAsDataURL(file)
            }
        </script>
</html>