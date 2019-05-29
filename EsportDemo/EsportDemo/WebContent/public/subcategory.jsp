<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/templates/public/assets/inc/header.jsp"%>

<section class="breadcrumb_section">
	<%
		ArrayList<News> listNewsBySubCatId = (ArrayList<News>) request.getAttribute("listNewsBySubCatId");
		String category = "";
		if(listNewsBySubCatId != null && listNewsBySubCatId.size() > 0){
			category = listNewsBySubCatId.get(0).getCatName(); 
		}
	%>
	<div class="container">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#">Trang chủ</a></li>
				<li class="active"><a href="#"><%=category%></a></li>
			</ol>
		</div>
	</div>
</section>

<div class="container">
	<div class="row">
		<div class="col-md-8">
			<div class="entity_wrapper">
				<div class="entity_title header_purple">
					<h1
						style="color: white; background-color: #1abc9c; padding: 5px 0px 5px 5px; border-radius: 5px;"><%=category%></h1>
				</div>
				<!-- entity_title -->
				<div class="entity_wrapper">
					<div class="row">
						<%
							if (listNewsBySubCatId != null || listNewsBySubCatId.size() > 0) {
								for (News objNew : listNewsBySubCatId) {
						%>
						<div class="col-md-6">
							<div class="category_article_body">
								<div class="top_article_img">
									<img class="img-fluid"
										src="<%=request.getContextPath()%>/files/<%=objNew.getPicture() %>"
										alt="feature-top" width="350px;" height="200px;">
								</div>
								<!-- top_article_img -->

								<div class="category_article_title">
									<h5>
										<a
											href="<%=request.getContextPath()%>/detail?id=<%=objNew.getId()%>&idCat=<%=objNew.getCategory()%>"
											target=""><%=objNew.getName()%></a>
									</h5>
								</div>
								<!-- category_article_title -->

								<div class="article_date">
									<a href="#"><%=objNew.getDate_create()%></a>, by: <a href="#"><%=objNew.getUserName()%></a>
								</div>
								<!-- article_date -->

								<div class="category_article_content">
									<%
										if (objNew.getPreview().length() < 70)
													out.print(objNew.getPreview());
												else
													out.print(objNew.getPreview().substring(0, 70) + "...");
									%>
								</div>
								<!-- category_article_content -->

								<div class="article_social">
									<span><a href="#"><i class="far fa-eye"></i><%=objNew.getCount()%></a>
										Views</span>
								</div>
								<!-- article_social -->

							</div>
							<!-- category_article_body -->
							<div class="widget_advertisement"></div>
							<!-- widget_advertisement -->
						</div>
						<%
							}
							} else {%>
						<h2>Không có bài viết nào</h2>
						<%}
						%>
						<!-- col-md-6 -->


					</div>
					<!-- row -->
					<%
						int numberOfPages = (Integer) request.getAttribute("numberOfPages");
						int currentPage = (Integer) request.getAttribute("currentPage");
						int lowbound = currentPage - 2;
						int upbound = currentPage + 2;
					%>

					<nav aria-label="Page navigation" class="pagination_section">
						<ul class="pagination">
							<%if(currentPage == 1){%>
							<li class="paginate_button previous disabled"
								aria-controls="dataTables-example" tabindex="0"
								id="dataTables-example_previous"><a href="#"> << </a></li>
							<%} else{%>
							<li class="paginate_button previous"
								aria-controls="dataTables-example" tabindex="0"
								id="dataTables-example_previous"><a
								href="<%=request.getContextPath()%>/admin/news/index?page=<%=currentPage - 1%>">
									<< </a></li>
							<%}%>

							<%
                                           	for(int i = 1; i <= numberOfPages; i++){
                                           			if(i == 1 && i < lowbound){%>
							<li class="paginate_button " aria-controls="dataTables-example"
								tabindex="0"><a
								href="<%=request.getContextPath()%>/admin/news/index?page=<%=i%>"><%=i %></a></li>
							<li class="disabled"><span>...</span></li>
							<%} else if(i == numberOfPages && i > upbound){%>
							<li class="disabled"><span>...</span></li>
							<li class="paginate_button " aria-controls="dataTables-example"
								tabindex="0"><a
								href="<%=request.getContextPath()%>/admin/news/index?page=<%=i%>"><%=i %></a></li>
							<%} else if(i >= lowbound && i <= upbound){
                                           						if(currentPage == i){%>
							<li class="paginate_button active"
								aria-controls="dataTables-example" tabindex="0"><a
								href="<%=request.getContextPath()%>/admin/news/index?page=<%=i%>"><%=i %></a></li>
							<%} else{%>
							<li class="paginate_button" aria-controls="dataTables-example"
								tabindex="0"><a
								href="<%=request.getContextPath()%>/admin/news/index?page=<%=i%>"><%=i %></a></li>
							<%}
                                           			}
                                           		}
                                            %>
							<%
	                                            if(currentPage == numberOfPages){%>
							<li class="paginate_button next disabled"
								aria-controls="dataTables-example" tabindex="0"
								id="dataTables-example_previous"><a href=""> >> </a></li>
							<%} else{%>
							<li class="paginate_button next"
								aria-controls="dataTables-example" tabindex="0"
								id="dataTables-example_previous"><a
								href="<%=request.getContextPath()%>/admin/news/index?page=<%=currentPage + 1%>">
									>> </a></li>
							<%}%>
						</ul>
					</nav>
					<!-- navigation -->
				</div>

			</div>
			<!-- entity wrapper -->
		</div>
		<!-- col-md-8 -->
		<%@include file="/templates/public/assets/inc/right-bar.jsp"%>
	</div>
	<!-- row -->

</div>
<!-- container -->

<%@include file="/templates/public/assets/inc/footer.jsp"%>
