<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="model.bean.Admin"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>AdminCP | DTP Esport</title>
<!-- BOOTSTRAP STYLES-->
<link
	href="<%=request.getContextPath() %>/templates/admin/assets/css/bootstrap.css"
	rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link
	href="<%=request.getContextPath() %>/templates/admin/assets/css/font-awesome.css"
	rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link
	href="<%=request.getContextPath() %>/templates/admin/assets/css/custom.css"
	rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<!-- JQUERY -->
<script type="text/javascript"
	src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-3.2.1.js"></script>
<!-- VALIDATE -->
<script type="text/javascript"
	src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.validate.min.js"></script>
<!-- CKEDITOR -->
<script type="text/javascript"
	src="<%=request.getContextPath() %>/library/ckeditor/ckeditor.js"></script>
<!-- CKFINDER -->
<script type="text/javascript"
	src="<%=request.getContextPath() %>/library/ckfinder/ckfinder.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
.error {
	color: red;
	font-weight: bold;
	font-style: italic;
}
</style>
</head>

<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=request.getContextPath()%>/admin/news/index">DTP Esport</a>
			</div>
			 <%
            	Admin userInfo = null;
            	if(session.getAttribute("userInfo") != null) {
            		userInfo = (Admin) session.getAttribute("userInfo");
            	
			 %>
			<div style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;">
					Xin chào, <b><%=userInfo.getFullname() %> </b> &nbsp;
				 <a
					href="<%=request.getContextPath()%>/logout"
					class="btn btn-danger square-btn-adjust">Đăng xuất
				</a>
			</div>
			<div style="color: white; padding: 15px 700	px 5px 50px; float: right; font-size: 20px;">
				 <a
					href="<%=request.getContextPath()%>/index">Trang chủ
				</a>
			</div>
			<%} %>
		</nav>
		<!-- /. NAV TOP  -->