<%@page import="model.bean.Comment"%>
<%@page import="model.dao.CommentDAO"%>
<%@page import="model.dao.AdminDAO"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/templates/public/assets/inc/header.jsp"%>


<section id="entity_section" class="entity_section">
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<%
					News objDetailNew = (News) request.getAttribute("objNew");
					if(objDetailNew != null){
						String category = objDetailNew.getCatName();
						String news = objDetailNew.getName();
						int news_id = objDetailNew.getId();
				%>
				<div class="entity_wrapper">
					<div class="entity_title header_purple">
						<h1
							style="color: white; background-color: #1abc9c; padding: 5px 5px; border-radius: 5px;"><%=category%></h1>
					</div>
					<div class="entity_title">
						<h2><%=objDetailNew.getName() %></h2>
					</div>
					<!-- entity_title -->

					<div class="entity_meta">
						<a><%=objDetailNew.getDate_create() %></a>, by: <a target="_self"><%=objDetailNew.getUserName() %></a>
						<span> <a href="" target="_self"> | <i
								class="far fa-eye"></i> <%=objDetailNew.getCount() %></a> Views
						</span>
					</div>

					<!-- entity_meta -->

					<div class="entity_content">
						<%=objDetailNew.getDetail() %>
					</div>
					<!-- entity_content -->
				</div>

				<!-- Bình luận -->

				<div class="readers_comment">
					<div class="entity_inner__title header_purple">
						<h2>Bình luận người đọc</h2>
					</div>
					<!-- entity_title -->
					<%
						CommentDAO commentDAO = new CommentDAO();
						AdminDAO adminDAO = new AdminDAO();
						ArrayList<Comment> listComment = (ArrayList<Comment>) commentDAO.getListComment(objDetailNew.getId());
						if(listComment != null && listComment.size() > 0){
							for(Comment objComment: listComment){
								Admin maker = adminDAO.getItem(objComment.getId_user());
					%>
					<div class="media">
						<div class="media-left">
							<a href="#"> <img alt="64x64" class="media-object"
								src="<%=request.getContextPath() %>/templates/public/assets/img/reader_img1.jpg"
								data-holder-rendered="true">
							</a>
						</div>
						<div class="media-body" id="">
							<h2 class="media-heading"><%=maker.getFullname() %></h2>
							<%=objComment.getContent() %>
							<div class="entity_vote">
								 <a href="javascript: void (0)" onclick="return reply(<%=objComment.getId()%>)" > 
								 <span class="reply_ic"> Reply </span>
								</a>
							</div>
							<div class="media" id="reply<%=objComment.getId()%>"></div>
						</div>
					</div>
					<!-- Ajax for add reply comment -->
					<script type="text/javascript">
			    	function commentreply(id){
			    		var email = document.getElementById('inputEmail').value;
			    		var content = document.getElementById('inputComment').value;
			    		$.ajax({
			    			url: '<%=request.getContextPath()%>/comment?news=<%=news%>&news_id=<%=news_id%>&parent_id=<%=objComment.getId()%>',
			    			type: 'POST',
			    			cache: false,
			    			data:{
			    				aemail: email,
			    				acontent: content,
			    			},
			    			success: function(data){
			    				$("#reply"+id).html(data);
			    			},
			    			error: function(){
			    				alert('Có lỗi xảy ra');
			    			}
			    		});
			    		return false;
			    	}
   					</script>
					<!-- Ajax for reply comment -->
					<script type="text/javascript">
    	function reply(id){
    		$.ajax({
    			url: '<%=request.getContextPath()%>/reply',
    			type: 'POST',
    			cache: false,
    			data:{
    				aid: id,
    			},
    			success: function(data){
    				$("#reply"+id).html(data);
    			},
    			error: function(){
    				alert('Có lỗi xảy ra');
    			}
    		});
    		return false;
    	}
    </script>
	<!-- Ajax for Add Comment -->
	<%}} else { %>
	<p>Chưa có bình luận nào</p>
	<%} %>
	<script type="text/javascript">
   	function comment(){
   		var email = "hello";
   		var content = document.getElementById('inputComment').value;
   		$.ajax({
   			url: '<%=request.getContextPath()%>/comment?news=<%=objDetailNew.getName()%>&news_id=<%=objDetailNew.getId()%>',
   			type: 'POST',
   			cache: false,
   			data:{
   				aemail: email,
   				acontent: content,
   			},
   			success: function(data){
   				$("#addcomment").html(data);
   			},
   			error: function(){
   				alert('Có lỗi xảy ra');
   			}
   		});
   		return false;
		}
		</script>
					<!-- Add comment -->
					<div class="media" id="addcomment">
						<div class="media-left"></div>
						<div class="media-body" id=""></div>
					</div>
					<!-- media end -->
				</div>
				<!--Readers Comment-->

				<div class="entity_comments">
					<div class="entity_inner__title header_black">
						<h2>Thêm bình luận</h2>
			</div>
						<% 	Admin currUser = (Admin) session.getAttribute("userInfo"); 
							if(currUser!= null) {
						%>
						<!--Entity Title -->
						<div class="entity_comment_from">
							<form action="javascript: void(0)" onsubmit="return comment()">
								<div class="form-group comment">
									<textarea class="form-control" value="" id="inputComment"
										placeholder="Comment"></textarea>
								</div>
								<button type="submit" class="btn btn-submit red">Submit</button>
							</form>
						</div>
						<%} else {%>
						<div class="entity_comment_from">
							<a
									href="<%=request.getContextPath()%>/login"
									target="_self"> Đăng Nhập
							</a>
							<b> Để Bình luận</b>
						</div>
						<%} %>
					<!--Entity Comments From -->
				</div>
				<!--Entity Comments -->
			</div>

			<%}else{ %>
			<p>Không thể hiển thị tin</p>
			<%} %>
			<!--Left Section-->
			<!--Right bar start  -->
			<div class="col-md-4">
				<div class="widget">
					<div class="widget_title widget_black">
						<h2>Tin liên quan</h2>
					</div>
					<%
					    ArrayList<News> listRelatedNews = (ArrayList<News>) request.getAttribute("listRelatedNew");
						if(listRelatedNews != null || listRelatedNews.size() >0){
							for(News objRelatedNew: listRelatedNews){
								if(objRelatedNew.getId() != objDetailNew.getId()){
				    %>
					<div class="media">
						<div class="media-left">
							<a
								href="<%=request.getContextPath()%>/detail?id=<%=objRelatedNew.getId()%>&idCat=<%=objRelatedNew.getCategory()%>"><img
								class="media-object"
								src="<%=request.getContextPath() %>/files/<%=objRelatedNew.getPicture() %>"
								width="100px;" height="100px;" alt="Generic placeholder image"></a>
						</div>
						<div class="media-body">
							<h3 class="media-heading">
								<a
									href="<%=request.getContextPath()%>/detail?id=<%=objRelatedNew.getId()%>&idCat=<%=objRelatedNew.getCategory()%>"
									target="_self"><%=objRelatedNew.getName() %></a>
							</h3>
							<span class="media-date"><a href="#"><%=objRelatedNew.getDate_create() %></a>,
								by: <a href="#"><%=objRelatedNew.getUserName()%></a></span>

							<div class="widget_article_social">
								<span> <a href="single.html" target="_self"> <i
										class="far fa-eye"></i><%=objRelatedNew.getCount() %></a> Views
								</span> 
							</div>
						</div>
					</div>
					<%}} %>
				</div>
				<%} %>
				<!-- Popular News -->

				<!-- col-md-4 -->

			</div>
			<!--Right bar end  -->
		</div>
		<!--Right Section-->
	</div>
	<!-- row -->

	</div>
	<!-- container -->

</section>
<!-- Entity Section Wrapper -->


<%@include file="/templates/public/assets/inc/footer.jsp"%>