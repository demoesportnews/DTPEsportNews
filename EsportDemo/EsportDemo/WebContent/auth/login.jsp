<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>

<!-- Head -->
<head>

<title>Login to DPT Management</title>

<!-- Meta-Tags -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords"
	content="Existing Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
			<!-- //Meta-Tags -->
			
			<link href="css/popuo-box.css" rel="stylesheet" type="text/css"
				media="all" />
			
			<!-- Style -->
			<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/auth/css/style.css" type="text/css" media="all">
			
			<!-- Fonts -->
			<link href="//fonts.googleapis.com/css?family=Quicksand:300,400,500,700"
				rel="stylesheet">
			<!-- //Fonts -->
			<!-- VALIDATE ACCOUNT -->
			<script type="text/javascript"
				src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery-3.2.1.js"></script>
			<script type="text/javascript"
				src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.validate.min.js">
	</script>
	<style>
	.error {
		color: red;
		font-weight: bold;
		font-style: italic;
	}
	</style>
			
</head>

<!-- //Head -->

<!-- Body -->
<body>
	<h1></h1>
	<div class="w3layoutscontaineragileits">
		<h2>DPT Esport Login</h2>
		<form id="formLogin" action="<%=request.getContextPath()%>/login"
			method="post">
			<%
			if(request.getParameter("msg") != null){
				int msg = Integer.parseInt(request.getParameter("msg"));
				switch(msg) {
					case 0: out.print("Tên đăng nhập hoặc mật khẩu không đúng!");
					break;
					case 1: out.print("Tạo tài khoản thành công!");
					break;
					case 2: out.print("Có lỗi trong quá trình xử lý!");
					break;
				}%>
			<%} %>

			<input type="text" name="Username" placeholder="USERNAME" value=""
				required=""> <input type="password" name="Password"
				placeholder="PASSWORD" value="" required="">
			<ul class="agileinfotickwthree">
				<li><input type="checkbox" id="brand1" value=""> <label
					for="brand1"><span></span>Remember me</label> <a href="#">Forgot
						password?</a></li>
			</ul>
			<div class="aitssendbuttonw3ls">
				<input type="submit" value="LOGIN">
				
				<div class="clear"></div>
			</div>
			<div class="aitssendbuttonw3ls">
				<p>
					To register new account <span>→</span> <a class="w3_play_icon1"
						href="<%=request.getContextPath()%>/register"> Click Here</a>
				</p>
			</div>
			<div>  
               <p> <br/><a href="<%=request.getContextPath()%>/index" style="color: aqua;">Quay lại trang chủ</a>
               </p>
			</div>
			
		</form>
	</div>
	<div class="w3footeragile">
		<p>
			&copy; 2019 TTCN Login Form. All Rights Reserved | Design by <a
				href="https://www.facebook.com/BNu2098" target="_blank">DPT Esport</a>
		</p>
	</div>
	
</body>
<!-- //Body -->

</html>