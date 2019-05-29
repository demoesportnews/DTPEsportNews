<%@page import="model.dao.AdminDAO"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>

<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý bình luận</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row">
								<form method="post"
										action="<%=request.getContextPath()%>/adminSearchComment">
										<input type="submit" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="text" name="search" value=""
											class="form-control input-sm" placeholder="Nhập tên tin"
											style="float: right; width: 300px;" />
										<div style="clear: both"></div>
								</form>
								<br />
							</div>

							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th width="">ID</th>
										<th>Tên tin</th>
										<th width="">Người bình luận</th>
										<th>Nội dung</th>
										<th width="110px">Quản lí</th>
									</tr>
								</thead>
								<%
                                ArrayList<Comment> listComment = (ArrayList<Comment>) request.getAttribute("listComment");
                                if(listComment != null || listComment.size() > 0){
                                	for(Comment objComment: listComment){
                                		String edit = request.getContextPath()+"/editNews?id="+objComment.getId();
                                		String del = request.getContextPath()+"/delNews?id="+objComment.getId();
                                		AdminDAO newAd = new AdminDAO();
                                		Admin commentMaker = newAd.getItem(objComment.getId_user());
                                %>
								<tbody>
									<tr>
										<td class="center"><%=objComment.getId() %></td>
										<td class="center"><%=objComment.getNews() %></td>
										<td class="center"><%=commentMaker.getFullname() %></td>
										<td class="center"><%=objComment.getContent() %></td>
										<td class="center" id="comment<%=objComment.getId()%>">
											<%if(objComment.getStatus() == 1){%> <a
											href="javascript: void(0)"
											onclick="return getStatus(<%=objComment.getId()%>, <%=objComment.getStatus()%>)"><img
												src="<%=request.getContextPath()%>/files/active.gif"></a>
											<% } else{%> <a href="javascript: void(0)"
											onclick="return getStatus(<%=objComment.getId()%>, <%=objComment.getStatus()%>)"><img
												src="<%=request.getContextPath()%>/files/deactive.gif"></a>
											<%} %>
										</td>

									</tr>
								</tbody>
								<%}} else { %>
								<p align="center">Chưa có bình luận nào</p>
								<%} %>
							</table>
							<script type="text/javascript">
                            	function getStatus(id, status){
                            		$.ajax({
                            			url: '<%=request.getContextPath()%>/admin/comment/index',
                            			type: 'POST',
                            			cache: false,
                            			data:{
                            				aid: id,
                            				astatus: status,
                            			},
                            			success: function(data){
                            				$("#comment"+id).html(data);
                            			},
                            			error: function(){
                            				alert('Có lỗi xảy ra');
                            			}
                            		});
                            		return false;
                            	}
                            </script>
							<%
								int numberOfPages = (Integer) request.getAttribute("numberOfPages");
								int currentPage = (Integer) request.getAttribute("currentPage");
								int lowbound = currentPage - 2;
								int upbound = currentPage + 2;
							%>
							<div class="row">
								<div class="col-sm-5"></div>
								<div class="col-sm-7" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
											<%if(currentPage == 1){%>
											<li class="paginate_button previous disabled"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a href="#"> << </a></li>
											<%} else{%>
											<li class="paginate_button previous"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a
												href="<%=request.getContextPath()%>/admin/comment/index?page=<%=currentPage - 1%>">
													<< </a></li>
											<%}%>

											<%
                                           	for(int i = 1; i <= numberOfPages; i++){
                                           			if(i == 1 && i < lowbound){%>
											<li class="paginate_button "
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/comment/index?page=<%=i%>"><%=i %></a></li>
											<li class="disabled"><span>...</span></li>
											<%} else if(i == numberOfPages && i > upbound){%>
											<li class="disabled"><span>...</span></li>
											<li class="paginate_button "
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/comment/index?page=<%=i%>"><%=i %></a></li>
											<%} else if(i >= lowbound && i <= upbound){
                                           						if(currentPage == i){%>
											<li class="paginate_button active"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/comment/index?page=<%=i%>"><%=i %></a></li>
											<%} else{%>
											<li class="paginate_button"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/comment/index?page=<%=i%>"><%=i %></a></li>
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
												href="<%=request.getContextPath()%>/admin/comment/index?page=<%=currentPage + 1%>">
													>> </a></li>
											<%}%>
										</ul>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
	</div>
</div>
<script>
    document.getElementById("comment").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>