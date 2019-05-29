<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý tin</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<%Admin curr = (Admin) session.getAttribute("userInfo"); %>
		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row">
								<div class="col-sm-6">
									<%if(curr.getPosition()<=2) {%>
									<a href="<%=request.getContextPath() %>/addNews"
										class="btn btn-success btn-md"><i class="fa fa-plus"></i>Thêm</a>
									<%} else {%>
									<b>Cần vị trí cao hơn để đăng bài</b>
									<%} %>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="post"
										action="<%=request.getContextPath()%>/adminSearchNews">
										<input type="submit" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="text" name="search" value=""
											class="form-control input-sm" placeholder="Nhập tên tin"
											style="float: right; width: 300px;" />
										<div style="clear: both"></div>
									</form>
									<br />
								</div>
							</div>

							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th width="50px">ID</th>
										<th width="450px">Tên tin</th>
										<th width="150px">Danh mục</th>
										<th width="160px">Hình ảnh</th>
										<th width="50px">Status</th>
										<th width="150px">Người đăng</th>
										<th width="110px">Chức năng</th>
									</tr>
								</thead>
								<%
                                ArrayList<News> listNew = (ArrayList<News>) request.getAttribute("listNew");
                                if(listNew != null || listNew.size() > 0){
                                	for(News objNew: listNew){
                                		String edit = request.getContextPath()+"/editNews?id="+objNew.getId();
                                		String del = request.getContextPath()+"/delNews?id="+objNew.getId();
                                %>
								<tbody>
									<tr>
										<td class="center"><%=objNew.getId()%></td>
										<td class="center"><%=objNew.getName()%></td>
										<td class="center"><%=objNew.getCatName() %></td>
										<td class="center"><img width="150px" height="100px"
											src="<%=request.getContextPath() %>/files/<%=objNew.getPicture() %>"
											alt="<%=objNew.getPicture()%>" /></td>
										<td class="center" id="new<%=objNew.getId()%>">
											<%if(objNew.getStatus() == 1) {%>
											<a href="javascript:void(0)"
											onclick="return getStatus(<%=objNew.getId()%>, <%=objNew.getStatus()%>)"><img
												src="<%=request.getContextPath()%>/files/active.gif"></a>
											<% } else {%> <a href="javascript: void(0)"
											onclick="return getStatus(<%=objNew.getId()%>, <%=objNew.getStatus()%>)"><img
												src="<%=request.getContextPath()%>/files/deactive.gif"></a>
											<%} %>
										</td>
										<td class="center"><%=objNew.getUserName()%></td>
										<td class="center" align="center">
										
										<%if(curr.getPosition()==1) {%>
												<a href="<%=edit %>" title="" class="btn btn-primary" title="Edit News"><i
													class="fa fa-edit "></i>
												</a>
												<a href="<%=del %>" onclick="return confirm('Bạn có chắc chắn muốn xóa tin')"
													title="" class="btn btn-danger" title="Delete News"><i
													class="fa fa-trash-o"></i>
												</a>
											<%} else if(curr.getPosition()==2) {
													if(curr.getId()==objNew.getWho_create()){		
											%>
													<a href="<%=edit %>" title="" class="btn btn-primary" title="Edit News"><i
													class="fa fa-edit "></i>
													</a>
													<a href="<%=del %>" onclick="return confirm('Bạn có chắc chắn muốn xóa tin')"
														title="" class="btn btn-danger" title="Delete News"><i
														class="fa fa-trash-o"></i>
													</a>
											<%} } %>
										</td>
									</tr>
								</tbody>
								<%}} else { %>
								<p align="center">Chưa có tin nào</p>
								<%} %>
							</table>
							<script type="text/javascript">
                            	function getStatus(id, status){
                            		$.ajax({
                            			url: '<%=request.getContextPath()%>/admin/news/index',
                            			type: 'POST',
                            			cache: false,
                            			data:{
                            				aid: id,
                            				astatus: status,
                            			},
                            			success: function(data){
                            				$("#new"+id).html(data);
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
							%>
							<div class="row">
								<div class="col-sm-6"></div>
								<div class="col-sm-6" style="text-align: right;"></div>
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>