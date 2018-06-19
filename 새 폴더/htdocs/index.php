
<html lang="en">
<?php
	session_start();
	?>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Anyar-HTML Bootstrap theme</title>



	<style>
	
.myButton {
	-moz-box-shadow: 0px 0px 0px 2px #9fb4f2;
	-webkit-box-shadow: 0px 0px 0px 2px #9fb4f2;
	box-shadow: 0px 0px 0px 2px #9fb4f2;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #7892c2), color-stop(1, #476e9e));
	background:-moz-linear-gradient(top, #7892c2 5%, #476e9e 100%);
	background:-webkit-linear-gradient(top, #7892c2 5%, #476e9e 100%);
	background:-o-linear-gradient(top, #7892c2 5%, #476e9e 100%);
	background:-ms-linear-gradient(top, #7892c2 5%, #476e9e 100%);
	background:linear-gradient(to bottom, #7892c2 5%, #476e9e 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#7892c2', endColorstr='#476e9e',GradientType=0);
	background-color:#7892c2;
	-moz-border-radius:10px;
	-webkit-border-radius:10px;
	border-radius:10px;
	border:1px solid #4e6096;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:19px;
	padding:12px 37px;
	text-decoration:none;
	text-shadow:0px 1px 0px #283966;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #476e9e), color-stop(1, #7892c2));
	background:-moz-linear-gradient(top, #476e9e 5%, #7892c2 100%);
	background:-webkit-linear-gradient(top, #476e9e 5%, #7892c2 100%);
	background:-o-linear-gradient(top, #476e9e 5%, #7892c2 100%);
	background:-ms-linear-gradient(top, #476e9e 5%, #7892c2 100%);
	background:linear-gradient(to bottom, #476e9e 5%, #7892c2 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#476e9e', endColorstr='#7892c2',GradientType=0);
	background-color:#476e9e;
}
.myButton:active {
	position:relative;
	top:1px;
}

	</style>
	<!--jquery & tmap appkey -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>simpleMap</title>
	<script src="https://api2.sktelecom.com/tmap/js?version=1&format=javascript&appKey=697870fc-4410-4ace-a2de-525221b9667f"></script>

	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>

	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/animate.css">
	<link href="css/style.css" rel="stylesheet" />
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<header>
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="navigation">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse.collapse">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<div class="navbar-brand">
							<a href="index.html">
								<h1>For Electronic Car</h1>
							</a>
						</div>
					</div>

					<div class="navbar-collapse collapse">
						<div class="menu">
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation">
									<a href="#home" class="active">Home</a>
								</li>
								<li role="presentation">
									<a href="#about">Application</a>
								</li>
								<li role="presentation">
									<a href="#services">Services</a>
								</li>
								<li role="presentation">
									<a href="#contact">Intro & contact</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</nav>
	</header>

	<div id="home">
		<div class="slider">

 <a href="#" class="myButton" style="margin-left:47%; margin-right:47%" onclick="button_click();" value="버튼1">LOGIN</a>
<script>
function button_click(){
	location.href = '/test111/test1.php';
}
</script>
        

		</div>
	</div>

	<section id="about">
			<div class="container">
			<div class="container">
						<div class="center">
							<div class="col-md-6 col-md-offset-3">
								<h2>APPLICATION</h2>
								<hr>
								<p class="lead">FOR ELECTRONIC CAR</p>
							</div>
						</div>
					</div>
					<div id="map_div" style="float:left ">
					</div>
	
					<div style="width: 25%; float:right; color:black; border:1px solid black; padding:20px">
						<div style="border:1px solid blue; padding:10px">
							<div style="font-size:20px;">
								현재 차량 상태
							</div>
							<hr style="margin:5px;">
								
							<div>
	
								<p style="padding:6px" id="car_number"></p>
								<hr style="margin:5px;">
								<p id="lat_info_car"  style="padding:6px"></p>
								<hr style="margin:5px;">
								<p id="lon_info_car" style="padding:6px"></p>
								<hr style="margin:6px;">
								<p id="km_info_car" style="padding:6px"></p>
								<hr style="margin:5px;" style="padding:6px">
								<p id="time"></p>
	
							</div>
						</div>
						<div>
							<hr>
							<div style="border:1px solid red; padding:10px">
								<div style="font-size:20px;">
									전기 충전소 정보
								</div>
								<hr style="margin:5px;">
								
								<div>
									<p id="station_name" style="padding:6px"></p>
									<hr style="margin:5px;">
									<p id="lat_info_station" style="padding:6px"></p>
									<hr style="margin:5px;">
									<p id="lon_info_station" style="padding:6px"></p>
									<hr style="margin:5px;">
									<p id="rapid" style="padding:6px"></p>
									<hr style="margin:5px;">
									<p id="slow" style="padding:6px"></p>
									<hr style="margin:5px;">
									<p id="distance"style="padding:6px"></p> 
								</div>
							</div>
						</div>
					</div>
				</div>
	
		<!-- <div class="container">
			<div class="center">
				<div class="col-md-6 col-md-offset-3">
					<h2>About Us</h2>
					<hr>
					<p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut</p>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-sm-6 wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="300ms">
					<div class="progress progress-striped active">
						<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
						 style="width: 40%">
							40% - eCommerce
						</div>
					</div>
					<div class="progress progress-striped active">
						<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
							20% - Ruby
						</div>
					</div>
					<div class="progress progress-striped active">
						<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
						 style="width: 60%">
							60% - User interface
						</div>
					</div>
					<div class="progress progress-striped active">
						<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
						 style="width: 80%">
							80% - WordPress
						</div>
					</div>
				</div>
			
				<div class="col-sm-6 wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="600ms">
					<div class="accordion">
						<div class="panel-group" id="accordion1">
							<div class="panel panel-default">
								<div class="panel-heading active">
									<h3 class="panel-title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1">
											Web Design
											<i class="fa fa-angle-right pull-right"></i>
										</a>
									</h3>
								</div>

								<div id="collapseOne1" class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="media accordion-inner">
											<div class="pull-left">
												<img class="img-responsive" src="img/accordion1.png">
											</div>
											<div class="media-body">
												<h4>Adipisicing elit</h4>
												<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore</p>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseTwo1">
											Lorem ipsum dolor sit amet
											<i class="fa fa-angle-right pull-right"></i>
										</a>
									</h3>
								</div>
								<div id="collapseTwo1" class="panel-collapse collapse">
									<div class="panel-body">
										<p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia
											aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor.
											<br> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut non cupidatat skateboard
											dolor brunch.</p>
									</div>
								</div>
							</div>

							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseThree1">
											Lorem ipsum dolor sit amet
											<i class="fa fa-angle-right pull-right"></i>
										</a>
									</h3>
								</div>
								<div id="collapseThree1" class="panel-collapse collapse">
									<div class="panel-body">
										<p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia
											aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor.
											<br> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut non cupidatat skateboard
											dolor brunch.</p>
									</div>
								</div>
							</div>

							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseFour1">
											Lorem ipsum dolor sit amet
											<i class="fa fa-angle-right pull-right"></i>
										</a>
									</h3>
								</div>
								<div id="collapseFour1" class="panel-collapse collapse">
									<div class="panel-body">
										<p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia
											aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor.
											<br> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut non cupidatat skateboard
											dolor brunch.</p>
									</div>
								</div>
							</div>
						</div>
					
					</div>
				</div>

			</div>
	
		</div> -->
		
	</section>
	

	<div id="services">
		<div class="container">
			<div class="center">
				<div class="col-md-6 col-md-offset-3">
					<h2>services</h2>
					<hr>
					<p class="lead">전기차 사용자를 위한 게시판입니다.</p>
				</div>
			</div>
			<a href="#" class="myButton" style="margin-left:45%; margin-right:48%" onclick="button2_click();" value="버튼2">board</a>
<script>
function button2_click(){
	location.href = '/test111/board/list.php';
}
</script>
		</div>


		<div class="container">
			<div class="text-center">
				<div class="col-md-3 wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="300ms">
					<img src="img/services/services1.png">
					<h3>Fully Responsive</h3>
					<p>Lorem ipsum dolor sit ame consectetur adipisicing elit</p>
				</div>
				<div class="col-md-3 wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="600ms">
					<img src="img/services/services5.png">
					<h3>Retina Ready</h3>
					<p>Lorem ipsum dolor sit ame consectetur adipisicing elit</p>
				</div>
				<div class="col-md-3 wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="900ms">
					<img src="img/services/services6.png">
					<h3>Fresh and Clean</h3>
					<p>Lorem ipsum dolor sit ame consectetur adipisicing elit</p>
				</div>
				<div class="col-md-3 wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="1200ms">
					<img src="img/services/services1.png">
					<h3>Easy to Customize</h3>
					<p>Lorem ipsum dolor sit ame consectetur adipisicing elit</p>
				</div>
			</div>
		</div>
	</div>

	<div id="feature">
		<div class="container">
			<div class="center">
							<div class="col-md-6 col-md-offset-3">
								<h2>사용한 Language</h2>
								<hr>
							</div>
						</div>
			<div class="text-center">
				<div class="col-md-4">
					<div class="hi-icon-wrap hi-icon-effect wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="300ms">
						<i class="fa fa-book"></i>
						<h3>HTML5</h3>
						
					</div>
				</div>
				<div class="col-md-4">
					<div class="hi-icon-wrap hi-icon-effect wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="600ms">
						<i class="fa fa-heart-o"></i>
						<h3>CSS3</h3>
						
					</div>
				</div>
				<div class="col-md-4">
					<div class="hi-icon-wrap hi-icon-effect wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="900ms">
						<i class="fa fa-cloud"></i>
						<h3>PHP & Javascript</h3>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="contact">
		<div class="container">
				<div class="container">
						<div class="center">
							<div class="col-md-6 col-md-offset-3">
								<h2>INTRO & CONTACT</h2>
								<hr>
								<p class="lead">KOREATECH COMPUTER SCIENCE ENGINEER</p>
							</div>
						</div>
					</div>
			<div class="col-lg-8">
				<div class="map">
				<img src="./assets/kut.png" style="width:600px">
				</div>
			</div>
			<section id="contact-page">
				<div class="container">
					
					<div class="center">
						<h3>Drop Your Message</h3>
						<p>Sed do eiusmod tempor incididunt ut labore et dolore magna</p>
					</div>
					<div class="col-lg-4">
						<div class="row contact-wrap">
							<div class="status alert alert-success" style="display: none"></div>
							<form id="main-contact-form" class="contact-form" name="contact-form" method="post" action="sendemail.php">
								<div class="form-group">
									<label>Name *</label>
									<input type="text" name="name" class="form-control" required="required">
								</div>
								<div class="form-group">
									<label>Email *</label>
									<input type="email" name="email" class="form-control" required="required">
								</div>
								<div class="form-group">
									<label>Subject *</label>
									<input type="text" name="subject" class="form-control" required="required">
								</div>
								<div class="form-group">
									<label>Message *</label>
									<textarea name="message" id="message" required="required" class="form-control" rows="8"></textarea>
								</div>
								<div class="form-group">
									<button type="submit" name="submit" class="btn btn-primary btn-lg" required="required">Submit Message</button>
								</div>
							</form>
						</div>
						<!--/.row-->
					</div>
				</div>
				<!--/.container-->
			</section>
			<!--/#contact-page-->
		</div>
	</div>

	<footer>
		<div class="container">
			<div class="col-md-4 wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="300ms">
				<h4>About Us</h4>
				<p>한국기술교육대학교 2014136118 조든솔</p>
				<p>한국기술교육대학교 2016136054 백송희 </p>
				<div class="contact-info">
					<ul>
						<li>
							<i class="fa fa-home fa"></i >충청남도 천안시 동남구 병천면 충절로 1600 </li>
						<li>
							<i class="fa fa-phone fa"></i> +10 5274 0453</li>
						<li>
							<i class="fa fa-envelope fa"></i> kut6118@naver.com</li>
					</ul>
				</div>
			</div>

			<div class="col-md-4 wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="600ms">
				<div class="text-center">
					<h4>Photo Gallery</h4>
					<ul class="sidebar-gallery">
						<li>
							<a href="#">
								<img src="./assets/pic1.jpg"style="float:left ;width:108px; height:63px; margin:15px" alt="" />
							</a>
						</li>
						<li >
							<a href="#">
								<img src="./assets/pic2.jpg"style="float:left; width:108px; height:63px; margin:15px" alt="" />
							</a>
						</li>
						<li >
							<a href="#">
								<img src="./assets/pic3.jpg" style="width:108px; height:63px" alt="" />
							</a>
						</li>		
					</ul>
				</div>
			</div>

			<div class="col-md-4 wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="900ms">
				<div class="">
					<h4>Newsletter Registration</h4>
					<p>Subscribe today to receive the latest Corpboot news via email. You may unsubscribe from this service at any time</p>
					<form class="form-inline">
						<div class="form-group">
							<input type="email" class="form-control" id="exampleInputEmail3" placeholder="Enter your Email...">
						</div>
						<button type="submit" class="btn btn-default">Subscribe</button>
					</form>
				</div>
			</div>

		</div>
	</footer>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/functions.js"></script>
	<script src="js/asd.js"></script>


</body>

</html>