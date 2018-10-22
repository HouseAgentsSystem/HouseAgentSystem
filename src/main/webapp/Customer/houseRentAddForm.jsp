<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>HTML5多文件预览上传</title>
		<!-- 引用控制层插件样式 -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/zyUpload.css" type="text/css">
		<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
		<!-- 引用核心层插件 -->
		<script type="text/javascript" src="js/zyFile.js"></script>
		<!-- 引用控制层插件 -->
		<script type="text/javascript" src="js/zyUpload.js"></script>
		<!-- 引用初始化JS -->
		<script type="text/javascript" src="js/demo.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=VyA3AiLqbNrhGrsa4DAACgaiCOA7V9uo"></script>
	</head>

	<body>
		<div class="container">
			<h4>租房信息</h4>
			<hr>
			<form id="form1" class="form-horizontal" action="/showHouseRent/addHouseRent">
				<input type="text" value="${sessionScope.user.id}" name="id" hidden="true"/>
				<div style="width: 600px; margin-left: 100px;margin-top: 20px;">
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">标题</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="inputPassword3" placeholder="标题" style="width: 200px;" name="title">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">月租金</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="inputPassword3" placeholder="月租金" style="width: 200px;" name="rent">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">面积(平方米)</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword3" placeholder="面积" style="width: 200px;" name="area">
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">地区</label>
						<div class="col-sm-10">
							<select class="form-control" style="width: 100px;" name="region">
								<option value="松山湖">松山湖</option>
								<option value="大岭山">大岭山</option>
								<option value="东城">东城</option>
								<option value="莞城">莞城</option>
								<option value="虎门">虎门</option>
								<option value="大朗">大朗</option>
								<option value="长安">长安</option>
								<option value="厚街">厚街</option>
								<option value="南城">南城</option>
								<option value="樟木头">樟木头</option>
								<option value="塘厦">塘厦</option>
								<option value="常平">常平</option>
								<option value="凤岗">凤岗</option>
								<option value="万江">万江</option>
								<option value="寮步">寮步 </option>
								<option value="黄江">黄江</option>
								<option value="沙田">沙田</option>
								<option value="清溪">清溪</option>
								<option value="中堂">中堂</option>
								<option value="桥头">桥头</option>
								<option value="石龙">石龙</option>
								<option value="横沥">横沥</option>
								<option value="道滘">道滘</option>
								<option value="茶山">茶山</option>
								<option value="麻涌">麻涌 </option>
								<option value="石排">石排</option>
								<option value="东坑">东坑</option>
								<option value="高埗">高埗</option>
								<option value="企石">企石</option>
								<option value="谢岗">谢岗</option>
								<option value="红梅">红梅</option>
								<option value="望牛墩">望牛墩</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">详细地址</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword3" placeholder="详细地址" style="width: 350px;" name="address">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">楼层</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword3" placeholder="楼层" style="width: 200px;" name="floor">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">室</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword3" placeholder="房间数" style="width: 200px;" name="room">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">厅</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPassword3" placeholder="厅" style="width: 200px;" name="hall">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">详细介绍</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="inputPassword3" placeholder="详细介绍" style="width: 350px;" name="introduce"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">是否整租</label>
						<div class="col-sm-10">
							<label class="radio-inline">
							  <input type="radio" name="isEntireRent" id="inlineRadio1" value="true" checked> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isEntireRent" id="inlineRadio2" value="false"> 否
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">床</label>
						<div class="col-sm-10">
							<label class="radio-inline">
							  <input type="radio" name="isBed" id="inlineRadio1" value="true" checked> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isBed" id="inlineRadio2" value="false"> 否
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">卫生间</label>
						<div class="col-sm-10">
							<label class="radio-inline">
							  <input type="radio" name="isToilet" id="inlineRadio1" value="true" checked> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isToilet" id="inlineRadio2" value="false"> 否
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">厨房</label>
						<div class="col-sm-10">
							<label class="radio-inline">
							  <input type="radio" name="isKitchen" id="inlineRadio1" value="true" checked> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isKitchen" id="inlineRadio2" value="false"> 否
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">热水器</label>
						<div class="col-sm-10">
							<label class="radio-inline">
							  <input type="radio" name="isCalorifier" id="inlineRadio1" value="true" checked> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isCalorifier" id="inlineRadio2" value="false"> 否
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">阳台</label>
						<div class="col-sm-10">
							<label class="radio-inline">
							  <input type="radio" name="isBalcony" id="inlineRadio1" value="true" checked> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isBalcony" id="inlineRadio2" value="false"> 否
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">空调</label>
						<div class="col-sm-10">
							<label class="radio-inline">
							  <input type="radio" name="isAirConditioning" id="inlineRadio1" value="true" checked> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isAirConditioning" id="inlineRadio2" value="false"> 否
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">洗衣机</label>
						<div class="col-sm-10">
							<label class="radio-inline">
							  <input type="radio" name="isWasher" id="inlineRadio1" value="true" checked> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isWasher" id="inlineRadio2" value="false"> 否
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">冰箱</label>
						<div class="col-sm-10">
							<label class="radio-inline">
							  <input type="radio" name="isRefrigerator" id="inlineRadio1" value="true" checked> 是
							</label>
							<label class="radio-inline">
							  <input type="radio" name="isRefrigerator" id="inlineRadio2" value="false"> 否
							</label>
						</div>
					</div>
					<input type="text" name="supporting" id="supporting" hidden="true"/>
					<input type="text" name="images" id="images" hidden="true"/>
					<input type="text" name="video" id="vedio" hidden="true"/>
				</div>
			</form>
			<form enctype="multipart/form-data" action="/house/vedioUpload" method="post" id="form2" class="form-horizontal">
				<div style="width: 600px; margin-left: 100px;margin-top: 20px;">
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">视频选择</label>
						<div class="col-sm-10">
							<input type="file" id="videourl" class="btn btn-default" onchange="getUrl()" name="fileList"/>
						</div>
					</div>
				</div>
			</form>
			<div id="demo" class="demo" style="width: 600px; height: 400px;margin-left: 100px;"></div>
			<p align="right"><button class="btn btn-info" style="background-color: #00b7ee; width: 80px;"onclick="submit()">提交</button></p>
		</div>
		<div style="display:none">
			<script src="http://s24.cnzz.com/stat.php?id=4273731&web_id=4273731" language="JavaScript"></script>
			<script src="http://s20.cnzz.com/stat.php?id=5240441&web_id=5240441" language="JavaScript"></script>
			<script type="text/javascript">
				var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
				document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Faca36f9d36d03a04d61c93f28c896386' type='text/javascript'%3E%3C/script%3E"));
			</script>
			<script>
				function getUrl() {
					var file = $("#videourl").val();
					function getFileName(o) {
						var pos = o.lastIndexOf("\\");
						return o.substring(pos + 1);
					}
					$("#vedio").val(getFileName(file))
				}
				function form2Upload(){
					var fileList = new FormData($('#form2')[0]);
			            $.ajax({
			                type: 'post',
			                url: "/houseRent/vedioUpload",
			                data: fileList,
			                cache: false,
			                processData: false,
			                contentType: false,
			            }).success(function (data) {
			            	$("#form1").submit();
			            }).error(function () {
			            });
				}
				function submit(){
					var isBed=$('input:radio[name="isBed"]:checked').val();
					var isWasher=$('input:radio[name="isWasher"]:checked').val();
					var isAirConditioning=$('input:radio[name="isAirConditioning"]:checked').val();
					var isBalcony=$('input:radio[name="isBalcony"]:checked').val();
					var isRefrigerator=$('input:radio[name="isRefrigerator"]:checked').val();
					var isToilet=$('input:radio[name="isToilet"]:checked').val();
					var isKitchen=$('input:radio[name="isKitchen"]:checked').val();
					var isCalorifier=$('input:radio[name="isCalorifier"]:checked').val();
					$("#supporting").val(isBed+','+isWasher+','+isAirConditioning+','+isBalcony+','+
					isRefrigerator+','+isToilet+','+isKitchen+','+isCalorifier);
					form2Upload();
				}
			</script>
		</div>
	</body>

</html>