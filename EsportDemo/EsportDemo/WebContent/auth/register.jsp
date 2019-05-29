<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Register to DPT Esport</title>
	
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
	<link rel="stylesheet"
		href="<%=request.getContextPath() %>/templates/auth/css/style.css"
		type="text/css" media="all">
	
	<!-- Fonts -->
	<link href="//fonts.googleapis.com/css?family=Quicksand:300,400,500,700"
		rel="stylesheet">
	<!-- //Fonts -->
	<!-- VALIDATE ACCOUNT -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery-3.2.1.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.validate.min.js"></script>
	
	<style>
	.error {	
		color: red;
		font-weight: bold;
		font-style: italic;
	}
	</style>
	
</head>
<body>
	<!-- for register popup -->
	<div id="small-dialog1" class="mfp-hide" style="margin: 50px 0 0 500px;">
		<div class="contact-form1">
			<div class="contact-w3-agileits">
				<h3>Register Form</h3>
				
				<form action="#" method="post" id = "form" onSubmit = "return checkPassword(this)">
					<div class="form-sub-w3ls">
						<input placeholder="User Name" type="text"  id="username" name="username" >
						<div class="icon-agile">
							<i class="fa fa-user" aria-hidden="true"></i>
						</div>
					</div>
					<div class="form-sub-w3ls">
						<input placeholder="Full Name" type="text" id="fullname-input" name="fullname-input" >
						<div class="icon-agile">
							<i class="fa fa-envelope-o" aria-hidden="true"></i>
						</div>
					</div>
					<div class="form-sub-w3ls">
						<input placeholder="Password" type="password" id="password" name="password">
						<div class="icon-agile">
							<i class="fa fa-unlock-alt" aria-hidden="true"></i>
						</div>
					</div>
					<div class="form-sub-w3ls">
						<input placeholder="Confirm Password" type="password" id="confirm-password" name="confirm-password" >
						<div class="icon-agile">
							<i class="fa fa-unlock-alt" aria-hidden="true"></i>
						</div>
						<span id = 'message'></span> 
					</div>
					<div class="login-check">
						<label class="checkbox"><input type="checkbox"
							name="checkbox" checked="">I Accept Terms & Conditions</label>
					</div>
					<div class="register-link">
                                <p>
                                    Đã có tài khoản?
                                    <a href="<%=request.getContextPath()%>/login" style="color:#4ce35f">Đăng nhập</a>
                                </p>
                    </div>
					<div class="submit-w3l">
						<input type="submit" value="Register">
					</div>
					<div class="register-link">  
                        <br/> <a href="<%=request.getContextPath()%>/index" style="color:aqua">Quay lại trang chủ</a>
                    </div>
				</form>
			</div>
		</div>
	</div>
	<script>   
        function checkPassword(form) { 
            password1 = form.password.value; 
            password2 = form.confirm-password.value; 

            // If Not same return False.     
            if (password1 != password2) { 
                alert ("\nPassword did not match: Please try again...") 
                return false; 
            } 

            // If same return True. 
            else{ 
                alert("Password Match: Welcome to GeeksforGeeks!") 
                return true; 
            } 
        }
        </script>
        <script>
		$('#password, #confirm-password').on('keyup', function () {
		  if ($('#password').val() == $('#confirm-password').val()) 
		  {
		    $('#message').html('Mật khẩu khớp').css('color', 'aqua');
		    var check =false;
		  } 
		  else 
		  { 
			$('#message').html('Mật khẩu xác nhận không khớp').css('color', 'red');
		    var check =true;
		  }
		});
		
		$(document).ready(function() {
			$("#form").validate({
				rules:{
					"username": {
						required:true,
						minlength: 5,
						maxlength: 20,
					},
					"password": {
						required:true,
						minlength: 6,
					},
					"confirm-password": {
						required:true,
					},
					"fullname-input": {
						required:true,
						minlength: 6,
						maxlength: 30,
					}
				},
				messages: {
					"username": {
						required:  "<br/>Tên đăng nhập không được để trống",
						minlength: "<br/>Tên đăng nhập tối thiểu 5 ký tự",
						maxlength: "<br/>Tên đăng nhập tối đa 12 ký tự",
					},
					"password": {
						required:"<br/>Vui lòng nhập mật khẩu",
						minlength:"<br/>Mật khẩu tối thiểu 6 ký tự",
					},
					"confirm-password": {
						required:"<br/>Vui lòng xác nhận mật khẩu",
					},
					"fullname-input": {
						required:"<br/>Vui lòng nhập họ tên đầy đủ",
						minlength: "<br/>Họ tên đầy đủ tối thiểu 6 ký tự",
						maxlength: "<br/>Họ tên đầy đủ tối đa 30 ký tự",
					}
				}
			});
		});
	</script>
	<div class="w3footeragile">
		<p>
			&copy; 2019 TTCN Login Form. All Rights Reserved | Design by <a
				href="https://www.facebook.com/BNu2098" target="_blank">DPT Esport</a>
		</p>
	</div>
</body>
</html>