<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<nav class="navbar-default navbar-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">
			<li class="text-center"><img
				src="<%=request.getContextPath() %>/templates/admin/assets/img/find_user.png"
				class="user-image img-responsive" /></li>
			<li><a id="song"
				href="<%=request.getContextPath()%>/admin/news/index"><i
					class="fa fa-list fa-3x"></i> Quản lý tin</a></li>
			<li><a id="category"
				href="<%=request.getContextPath()%>/admin/category/index"><i
					class="fa fa-list fa-3x"></i> Quản lý danh mục</a></li>
			<li><a id="user"
				href="<%=request.getContextPath()%>/admin/admin/index"><i
					class="fa fa-user fa-3x"></i> Quản lý người dùng</a></li>
			<li><a id="comment"
				href="<%=request.getContextPath()%>/admin/comment/index"><i
					class="fa fa-list fa-3x"></i> Quản lý bình luận</a></li>
		</ul>
	</div>
</nav>
<!-- /. NAV SIDE  -->