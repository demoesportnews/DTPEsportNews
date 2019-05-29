<%@page import="model.bean.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Sửa người dùng</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<%
			Admin curr = (Admin) session.getAttribute("userInfo");
        	String err = request.getParameter("err");
        	String userName = request.getParameter("username");
        	String fullName = request.getParameter("fullname");
        	int position = 0;
            Admin objAdmin = (Admin) request.getAttribute("objAdmin");
            if(objAdmin != null){
            	userName = objAdmin.getUsername();
            	fullName = objAdmin.getFullname();
            	position = objAdmin.getPosition();
            }
        	if("1".equals(err)){
        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Có lỗi khi sửa</span>");
        	}
        	if("2".equals(err)){
        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Tên đăng nhập đã tồn tại</span>");
        	}
        %>
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">

								<form role="form" action="" method="post" id="form">
									<div class="form-group">
										<label for="username">Tên đăng nhập</label> <input type="text"
											id="username"
											value="<%if(userName != null) out.print(userName);%>"
											name="username" class="form-control" />
									</div>
									<div class="form-group">
										<label for="password">Mật khẩu</label> <input type="password"
											id="password" value="" name="password" class="form-control" />
									</div>
									<div class="form-group">
										<label for="fullname">Họ tên</label> <input type="text"
											id="fullname"
											value="<%if(fullName != null) out.print(fullName); %>"
											name="fullname" class="form-control" />
									</div>
									<%if(curr.getPosition() == 3) { %>
									<div class="form-group">
										<label for="category">Chức vụ</label> <select id="position"
											name="position" class="form-control" required="required">
											<option <%if(position==3) out.print("selected='selected'");%>
												value="3">Người dùng</option>
										</select>
									</div>
									<%} if(curr.getPosition()==2) { %>
									<div class="form-group">
										<label for="category">Chức vụ</label> <select id="position"
											name="position" class="form-control" required="required">
											<option <%if(position==2) out.print("selected='selected'");%>
												value="2">Nhân viên</option>
											<option <%if(position==3) out.print("selected='selected'");%>
												value="3">Người dùng</option>
										</select>
									</div>
									<%} if(curr.getPosition()==1){ %>
										<div class="form-group">
										<label for="category">Chức vụ</label> <select id="position"
											name="position" class="form-control" required="required">
											<option <%if(position==1) out.print("selected='selected'");%>
												value="1">Quản lí</option>
											<option <%if(position==2) out.print("selected='selected'");%>
												value="2">Nhân viên</option>
											<option <%if(position==3) out.print("selected='selected'");%>
												value="3">Người dùng</option>
										</select>
									</div>
									<%} %>
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Sửa</button>
									<button type="reset" name="reset"
										class="btn btn-default btn-md">Reset</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- End Form Elements -->
			</div>
		</div>
		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>
<script>
	$().ready(function(){
		// validate the form when it is submitted
		var validator = $("#form").validate({
			errorPlacement: function(error, element){
				$(element).closest("form").find("label[for='"+element.attr("id")+"']").append(error);
			},
			errorElement: "span",
			rules: {
				username: {
					required: true,
					minlength: 8,
					maxlength: 20,
				},
				password:{
					required: true,
					minlength: 4,
					maxlength: 16,
				},
				fullname:{
					required: true,
					minlength: 6,
					maxlength: 25,
				},
			},
			messages: {
				username:{
					required: "(Tên đăng nhập không được trống)",
					minlength: "(Tên đăng nhập có độ dài từ 8 đến 20 kí tự)",
					maxlength: "(Tên đăng nhập có độ dài từ 8 đến 20 kí tự)",
				},
				password:{
					required: "(Mật khẩu không được trống)",
					minlength: "(Mật khẩu có độ dài từ 6 đến 16 kí tự)",
					maxlength: "(Mật khẩu có độ dài từ 6 đến 16 kí tự)",
				},
				fullname:{
					required: "(Họ tên không được trống)",
					minlength: "(Họ tên có độ dài từ 6 đến 25 kí tự)",
					maxlength: "(Họ tên có độ dài từ 6 đến 25 kí tự)",
				},
			}
		});
	});
</script>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>