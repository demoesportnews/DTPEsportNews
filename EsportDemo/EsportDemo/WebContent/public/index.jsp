<%@page import="model.bean.News"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/templates/public/assets/inc/header.jsp"%>

<section id="feature_news_section" class="feature_news_section">
	<div class="container">
		<div class="row">
			<div class="col-md-7">
				<div class="feature_article_wrapper">
				<%
                ArrayList<News> listNew = (ArrayList<News>) request.getAttribute("listNew");
            	ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
            	if(listNew != null || listNew.size() > 0){
            		int i = 0;
            		for(News objHotNew: listNew){
            			i += 1;
            			if(i <= 1){
                %>
					<div class="feature_article_img">
						<img class="img-responsive"
							src="<%=request.getContextPath()%>/files/<%=objHotNew.getPicture()%>"
							alt="feature-top">
					</div>
					<!-- feature_article_img -->

					<div class="feature_article_inner">
						<div class="tag_lg red">Tin Nóng</div>
						<div class="feature_article_title">
							<h1>
								<a
									href="<%=request.getContextPath()%>/detail?id=<%=objHotNew.getId()%>&idCat=<%=objHotNew.getCategory()%>"
									target="_self" title="<%=objHotNew.getName()%>"><%=objHotNew.getName()%></a>
							</h1>
						</div>
						<!-- feature_article_title -->

						<div class="feature_article_date">
							<a href="#" target="_self"><%=objHotNew.getUserName()%></a> ,<a
								href="#" target="_self"><%=objHotNew.getDate_create()%></a>
						</div>
						<!-- feature_article_date -->

						<div class="feature_article_content">
							<%
								if (objHotNew.getPreview().length() < 120) {
												out.print(objHotNew.getPreview());
											} else
												out.print(objHotNew.getPreview().substring(0, 120) + "...");
							%>
						</div>
						<!-- feature_article_content -->

						<div class="article_social">
							<span><i class="far fa-eye"></i><a href="#"><%=objHotNew.getCount()%></a>Views</span>
						</div>
						<!-- article_social -->

					</div>
					<!-- feature_article_inner -->

				</div>
				<!-- feature_article_wrapper -->
				<%
					}
						}
					}
				%>
			</div>
			<!-- col-md-7 -->
			<%
				NewDAO newDAO1 = new NewDAO();
				ArrayList<News> listPopularNewHeader = (ArrayList<News>) newDAO1.getPopularNew();
				if (listPopularNewHeader != null || listPopularNewHeader.size() > 0) {
					int i = 0;
					String last = "last_";
					for (News objTopViewedNew : listPopularNewHeader) {
						i += 1;

						if (i <= 2) {
			%>
			<div class="col-md-5">
				<div
					class="feature_static_<%if (i == 2)
							out.print(last);%>wrapper">
					<div class="feature_article_img">
						<img class="img-responsive"
							src="<%=request.getContextPath()%>/templates/public/assets/img/feature-top2.jpg"
							alt="feature-top">
					</div>
					<!-- feature_article_img -->

					<div class="feature_article_inner">
						<div class="tag_lg purple">Top Viewed</div>
						<div class="feature_article_title">
							<h1>
								<a
									href="<%=request.getContextPath()%>/detail?id=<%=objTopViewedNew.getId()%>&idCat=<%=objTopViewedNew.getCategory()%>"
									target="_self" title="<%=objTopViewedNew.getName()%>"> <%
 	if (objTopViewedNew.getName().length() < 50) {
 					out.print(objTopViewedNew.getName());
 				} else
 					out.print(objTopViewedNew.getName().substring(0, 50) + "...");
 %>
								</a>
							</h1>
						</div>
						<!-- feature_article_title -->

						<div class="feature_article_date">
							<a href="#" target="_self"><%=objTopViewedNew.getUserName()%></a>,<a
								href="#" target="_self"><%=objTopViewedNew.getDate_create()%></a>
						</div>
						<!-- feature_article_date -->

						<div class="feature_article_content">
							<%
								if (objTopViewedNew.getPreview().length() < 90) {
												out.print(objTopViewedNew.getPreview());
											} else
												out.print(objTopViewedNew.getPreview().substring(0, 90) + "...");
							%>
						</div>
						<!-- feature_article_content -->

						<div class="article_social">
							<span><i class="far fa-eye"></i><a href="#"><%=objTopViewedNew.getCount()%></a>Views</span>
						</div>
						<!-- article_social -->

					</div>
					<!-- feature_article_inner -->

				</div>
				<!-- feature_static_wrapper -->

			</div>
			<!-- col-md-5 -->
			<%
				}
					}
				}
			%>

		</div>
		<!-- Row -->

	</div>
	<!-- container -->

</section>
<!-- Feature News Section -->

<section id="category_section" class="category_section">
	<div class="container">
		<div class="row">
			<div class="col-md-8">

				<%
					if (listCat != null || listCat.size() > 0) {
						for (Category objCat : listCat) {
				%>

				<div class="category_section camera">
					<div class="article_title header_orange">
						<h2><%=objCat.getName()%></h2>
					</div>
					<!-- article_title -->
					<%
						int i = 0;
								for (News objNew : listNew) {

									if (objNew.getParent_id() == objCat.getId()) {
										i += 1;
										if (i <= 4) {
					%>
					<div class="category_article_wrapper">
						<div class="row">
							<div class="col-md-5">
								<div class="top_article_img">
									<a href="" title="<%=objNew.getName()%>"> <img
										class="img-responsive"
										src="<%=request.getContextPath()%>/files/<%=objNew.getPicture()%>"
										alt="feature-top" width="400px" height="400px">
									</a>
								</div>
								<!-- top_article_img -->

							</div>
							<div class="col-md-7">
								<span class="tag orange"><%=objNew.getCatName()%></span>


								<div class="category_article_title">
									<h2>
										<a
											href="<%=request.getContextPath()%>/detail?id=<%=objNew.getId()%>&idCat=<%=objNew.getCategory()%>"
											target="_self" title="<%=objNew.getName()%>"><%=objNew.getName()%></a>
									</h2>
								</div>
								<!-- category_article_title -->

								<div class="article_date">
									<a href="#"><%=objNew.getDate_create()%></a>, by: <a href="#"><%=objNew.getUserName()%></a>
								</div>
								<!----article_date------>
								<!-- category_article_wrapper -->

								<div class="category_article_content">
									<%
										if (objNew.getPreview().length() < 70) {
																out.print(objNew.getPreview());
															} else
																out.print(objNew.getPreview().substring(0, 70) + "...");
									%>
								</div>
								<!-- category_article_content -->

								<div class="media_social">
									<span><a href="#"><i class="far fa-eye"></i><%=objNew.getCount()%></a>
										Views</span>
								</div>
								<!-- media_social -->

							</div>
							<!-- col-md-7 -->

						</div>
						<!-- row -->

					</div>
					<%
						}
									}
								}
					%>
					<!-- category_article_wrapper -->

					<p class="divider">
						<a
							href="<%=request.getContextPath()%>/cat?catId=<%=objCat.getId()%>">Xem
							thêm&nbsp;&raquo;</a>
					</p>
				</div>
				<%
					}
					}
				%>
				<!-- Camera News Section -->


				<!-- Camera News Section -->
			</div>
			<!-- Left Section -->

			<%@include file="/templates/public/assets/inc/right-bar.jsp"%>

		</div>
		<!-- Row -->

	</div>
	<!-- Container -->

</section>
<!-- Category News Section -->
<div class="col-md-1"></div>
<div class="col-md-8">
	<div class="category_section camera">
		<div class="article_title header_orange">
			<h2>Esport Video</h2>
		</div>
	</div>
</div>
<br />
<br />
<br />
<section id="video_section" class="video_section">
	<div class="container">
		<div class="well">
			<div class="row">
				<div class="col-md-6">
					<div class="embed-responsive embed-responsive-4by3">
						<iframe class="embed-responsive-item"
							src="https://www.youtube.com/embed/V2se49fAnXs" frameborder="0"
							allowfullscreen></iframe>
					</div>
					<!-- embed-responsive -->

				</div>
				<!-- col-md-6 -->

				<div class="col-md-3">
					<div class="embed-responsive embed-responsive-4by3">
						<iframe class="embed-responsive-item"
							src="https://www.youtube.com/embed/1hHJUTOH2bQ"></iframe>
					</div>
					<!-- embed-responsive -->

					<div class="embed-responsive embed-responsive-4by3 m16">
						<iframe class="embed-responsive-item"
							src="https://www.youtube.com/embed/gWFbJBJFBxo"></iframe>
					</div>
					<!-- embed-responsive -->

				</div>
				<!-- col-md-3 -->

				<div class="col-md-3">
					<div class="embed-responsive embed-responsive-4by3">
						<iframe class="embed-responsive-item"
							src="https://www.youtube.com/embed/Kw4lLVJ9Z1M"></iframe>
					</div>
					<!-- embed-responsive -->

					<div class="embed-responsive embed-responsive-4by3 m16">
						<iframe class="embed-responsive-item"
							src="https://www.youtube.com/embed/T2gSlJTdjZ4"></iframe>
					</div>
					<!-- embed-responsive -->

				</div>
				<!-- col-md-3 -->

			</div>
			<!-- row -->

		</div>
		<!-- well -->

	</div>
	<!-- Container -->

</section>
<!-- Video News Section -->

<%@include file="/templates/public/assets/inc/footer.jsp"%>