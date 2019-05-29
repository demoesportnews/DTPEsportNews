<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TTCN - Esport</title>

<link rel='stylesheet'
	href='https://use.fontawesome.com/releases/v5.5.0/css/all.css'
	integrity='sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU'
	crossorigin='anonymous'>

<!-- favicon -->
<link
	href="<%=request.getContextPath() %>/templates/public/assets/img/favicon.png"
	rel=icon>

<!-- web-fonts -->
<link
	href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,700,500'
	rel='stylesheet' type='text/css'>

<!-- Bootstrap -->
<link
	href="<%=request.getContextPath() %>/templates/public/assets/css/bootstrap.min.css"
	rel="stylesheet">

<!-- font-awesome -->
<link
	href="<%=request.getContextPath() %>/templates/public/assets/fonts/font-awesome/font-awesome.min.css"
	rel="stylesheet">
<!-- Mobile Menu Style -->
<link
	href="<%=request.getContextPath() %>/templates/public/assets/css/mobile-menu.css"
	rel="stylesheet">

<!-- Owl carousel -->
<link
	href="<%=request.getContextPath() %>/templates/public/assets/css/owl.carousel.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath() %>/templates/public/assets/css/owl.theme.default.min.css"
	rel="stylesheet">
<!-- Theme Style -->
<link
	href="<%=request.getContextPath() %>/templates/public/assets/css/style.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">
	<div id="main-wrapper">
		<!-- Page Preloader -->
		<div id="preloader">
			<div id="status">
				<div class="status-mes"></div>
			</div>
		</div>
		<!-- preloader -->

		<div class="uc-mobile-menu-pusher">
			<div class="content-wrapper">
				<section id="header_section_wrapper" class="header_section_wrapper">
					<div class="container">
						<div class="header-section">
							<div class="row">
								<div class="col-md-4">
									<div class="left_section">
										<!-- Date -->
										<div id="date"></div>
										<script>
											var  d = new Date();
											var ngay = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
											var thang = ["January","February","March","April","May","June","July","August","September","October","November","December"];
											document.getElementById("date").innerHTML = ngay[d.getDay()]+ ", " + thang[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();
										</script>
										<div class="social">
											<a class="icons-sm fb-ic"  href="https://www.facebook.com/BNu2098 " target="_blank"><i class="fa fa-facebook"></i></a>
											<!--Twitter-->
											<a class="icons-sm tw-ic"  href="https://www.facebook.com/BNu2098 " target="_blank"><i class="fa fa-twitter"></i></a>
											<!--Google +-->
											<a class="icons-sm inst-ic"  href="https://www.facebook.com/BNu2098 " target="_blank"><i class="fa fa-instagram"></i></a>
											<!--Linkedin-->
											<a class="icons-sm tmb-ic"  href="https://www.facebook.com/BNu2098 " target="_blank"><i class="fa fa-tumblr"> </i></a>
											<!--Pinterest-->
											<a class="icons-sm rss-ic"  href="https://www.facebook.com/BNu2098 " target="_blank"><i class="fa fa-rss"> </i></a>
										</div>
										<!-- Top Social Section -->
									</div>
									<!-- Left Header Section -->
								</div>
								<div class="col-md-4">
									<div class="logo">
										<a href="<%=request.getContextPath()%>/index"><img
											src="<%=request.getContextPath() %>/templates/public/assets/img/DPT.jpg"
											alt="Tech NewsLogo"></a>
									</div>
									<!-- Logo Section -->
								</div>
								<% Admin curr = (Admin) session.getAttribute("userInfo");%>
								<div class="col-md-4">
									<div class="right_section">
									<%if(curr == null) {%>
										<ul class="nav navbar-nav">
											<li><a href="<%=request.getContextPath()%>/login">Login</a></li>
											<li><a href="<%=request.getContextPath()%>/register">Register</a></li>
										</ul>
										<%} else {
										String edit = request.getContextPath()+"/editUser?id="+curr.getId(); %>
										<ul class="nav navbar-nav">
											<li><a href="<%=edit%>"><b>Hello <%=curr.getFullname() %></b></a></li>
											<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
										</ul>
										<%} %>
										
										<ul class="nav-cta hidden-xs">
											<li class="dropdown"><a href="#" data-toggle="dropdown"
												class="dropdown-toggle"><i class="fa fa-search"></i></a>
												<ul class="dropdown-menu">
													<li>
														<div class="head-search">
															<form action="<%=request.getContextPath() %>/search"
																method="post" role="form">
																<!-- Input Group -->
																<div class="input-group">
																	<input type="text" name="information"
																		class="form-control" placeholder="Nhập tìm kiếm">
																	<span class="input-group-btn">
																		<button type="submit" class="btn btn-primary">Search</button>
																	</span>
																</div>
															</form>
														</div>
													</li>
												</ul></li>
										</ul>
										<!-- Search Section -->
									</div>
									<!-- Right Header Section -->
								</div>
							</div>
						</div>
						<!-- Header Section -->

						<div class="navigation-section">
							<nav class="navbar m-menu navbar-default">
								<div class="container">
									<!-- Brand and toggle get grouped for better mobile display -->
									<div class="navbar-header">
										<button type="button" class="navbar-toggle collapsed"
											data-toggle="collapse" data-target="#navbar-collapse-1">
											<span class="sr-only">Toggle navigation</span> <span
												class="icon-bar"></span> <span class="icon-bar"></span> <span
												class="icon-bar"></span>
										</button>
									</div>
									<!-- Collect the nav links, forms, and other content for toggling -->
									<div class="collapse navbar-collapse" id="#navbar-collapse-1">
										<ul class="nav navbar-nav main-nav">
											<li class="active"><a
												href="<%=request.getContextPath()%>/index">Trang chủ</a></li>
											<%
				                           		CategoryDAO catDAO = new CategoryDAO();
				                            	ArrayList<Category> listCatHeader = catDAO.getParentItems(0);
				                    			ArrayList<Category> listSubCatHeader = catDAO.getNotParentItems(0);
				                    			if(listCatHeader != null || listCatHeader.size() > 0){
				                    				for(Category objCat: listCatHeader){
				                    					
				                            %>
											<li class="dropdown m-menu-fw"><a href="#"
												data-toggle="dropdown" class="dropdown-toggle"><%=objCat.getName() %>
													<span><i class="fa fa-angle-down"></i></span></a>

												<ul class="dropdown-menu">

													<!-- <div class="m-menu-content"> -->
													<!-- <ul class="col-sm-3"> -->
													<li class="dropdown-header" align="center"><%=objCat.getName() %></li>
													<%
				                                for(Category objSubCatheader: listSubCatHeader){
				                                	if(objSubCatheader.getParent_id() == objCat.getId()){
				                                %>
													<li align="center"><a
														href="<%=request.getContextPath()%>/subcat?subcatId=<%=objSubCatheader.getId()%>"><%=objSubCatheader.getName() %></a></li>
													<%}} %>
													<!-- </ul> -->
													<!-- </div> -->

												</ul></li>
											<%}} %>
										</ul>
									</div>
									<!-- .navbar-collapse -->
								</div>
								<!-- .container -->
							</nav>
							<!-- .nav -->
						</div>
						<!-- .navigation-section -->
					</div>
					<!-- .container -->
				</section>
				<!-- header_section_wrapper -->