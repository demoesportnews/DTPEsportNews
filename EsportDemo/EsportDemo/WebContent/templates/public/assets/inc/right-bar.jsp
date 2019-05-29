<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.News"%>
<%@page import="model.dao.NewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-md-4">
	<div class="widget">
		<div class="widget_title widget_black">
			<h2>Tin xem nhiều</h2>
		</div>
		<%
    NewDAO newDAO = new NewDAO();
    ArrayList<News> listPopularNew = (ArrayList<News>) newDAO.getPopularNew();
    if(listPopularNew != null || listPopularNew.size() > 0){
    	for(News objNew: listPopularNew){
    %>
		<div class="media">
			<div class="media-left">
				<a
					href="<%=request.getContextPath()%>/detail?id=<%=objNew.getId()%>&idCat=<%=objNew.getCategory()%>"><img
					class="media-object"
					src="<%=request.getContextPath() %>/files/<%=objNew.getPicture() %>"
					alt="Generic placeholder image" width="100px;" height="100px;"></a>
			</div>
			<div class="media-body">
				<h3 class="media-heading">
					<a
						href="<%=request.getContextPath()%>/detail?id=<%=objNew.getId()%>&idCat=<%=objNew.getCategory()%>"
						target="_self"><%=objNew.getName() %></a>
				</h3>
				<span class="media-date"><a href="#"><%=objNew.getDate_create() %></a>,
					by: <a href="#"><%=objNew.getUserName() %></a></span>

				<div class="widget_article_social">
					<span> <a href="single.html" target="_self"> <i
							class="far fa-eye"></i><%=objNew.getCount() %></a> Views
					</span>
				</div>
			</div>
		</div>
		<%}} %>
	</div>
	<!-- Popular News -->

	<!-- col-md-4 -->

</div>