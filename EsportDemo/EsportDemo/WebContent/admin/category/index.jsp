<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý danh mục</h2>
			</div>
			<%
            if(request.getParameter("msg") != null){
            	int msg = Integer.parseInt(request.getParameter("msg"));
            	if(msg == 1) out.print("<p>Thêm thành công</p>");
            	if(msg == 2) out.print("<p>Sửa thành công</p>");
            	if(msg == 3) out.print("<p>Xóa thành công</p>");
            	if(msg == 6) out.print("<p>Xóa không thành công</p>");
            }	
            %>
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
								<div class="col-sm-6">
									<%Admin curr = (Admin) session.getAttribute("userInfo");
										if(curr.getPosition() == 1) {%>
									<a href="<%=request.getContextPath() %>/addCat"
										class="btn btn-success btn-md"><i class="fa fa-plus"></i>Thêm</a>
									<%} else {%>
									<p>Chỉ có Quản lý mới có quyền thao tác </p>
									<%} %>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<br />
								</div>
							</div>

							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Danh mục</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<%
                                ArrayList<Category> listCatParent = (ArrayList<Category>) request.getAttribute("listCatParent");
                                ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
                                
                                if(listCatParent != null || listCatParent.size() > 0){
                                	for(Category objCatParent: listCatParent){
                                %>
								<tbody>
									<tr>
										<td><%=objCatParent.getId() %></td>
										<td class="center">
											<ul>
												<li><%=objCatParent.getName() %>
													<ul>
														<%
	                                        		for(Category objCat: listCat){
	                                        			if(objCat.getParent_id() == objCatParent.getId()) {%>
														<li><%=objCat.getName()%>
															<%if(curr.getPosition()==1) { %>
																<a href="<%=request.getContextPath() %>/editSubCat?subCatId=<%=objCat.getId() %>"
																title="" class="" title="Edit News">
																<span><i
																	class="fa fa-edit "></i></span>
																</a> 
																<a  href="<%=request.getContextPath() %>/delCat?catId=<%=objCat.getId() %>"
																	onclick="return confirm('Bạn muốn xóa danh mục tin <%=objCat.getName() %>')"
																	title="" class="" title="Delete News"><i
																class="fa fa-trash-o"></i></a>
																<%} %>
														</li>
														<%} 
	                                        			
	                                        		}%>
													</ul></li>
											</ul>
										</td>
										<td class="center" align="center">
										<%if(curr.getPosition()==1) {%>
											<a  href="<%=request.getContextPath() %>/editCat?catId=<%=objCatParent.getId() %>"
												title="" class="btn btn-primary" title="Edit News"><i
													class="fa fa-edit "></i></a> <a
												href="<%=request.getContextPath() %>/delCat?catId=<%=objCatParent.getId() %>"
												onclick="return confirm('Bạn muốn xóa danh mục tin <%=objCatParent.getName() %>')"
												title="" class="btn btn-danger" title="Delete News"><i
													class="fa fa-trash-o"></i>
											</a>
											<%} %>
										</td>

									</tr>
								</tbody>
								<%}} else { %>
								<p align="center">Chưa có tin nào</p>
								<%} %>
							</table>
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
												href="<%=request.getContextPath()%>/admin/category/index?page=<%=currentPage - 1%>">
													<< </a></li>
											<%}%>

											<%
                                           	for(int i = 1; i <= numberOfPages; i++){
                                           			if(i == 1 && i < lowbound){%>
											<li class="paginate_button "
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/category/index?page=<%=i%>"><%=i %></a></li>
											<li class="disabled"><span>...</span></li>
											<%} else if(i == numberOfPages && i > upbound){%>
											<li class="disabled"><span>...</span></li>
											<li class="paginate_button "
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/category/index?page=<%=i%>"><%=i %></a></li>
											<%} else if(i >= lowbound && i <= upbound){
                                           						if(currentPage == i){%>
											<li class="paginate_button active"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/category/index?page=<%=i%>"><%=i %></a></li>
											<%} else{%>
											<li class="paginate_button"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/category/index?page=<%=i%>"><%=i %></a></li>
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
												href="<%=request.getContextPath()%>/admin/category/index?page=<%=currentPage + 1%>">
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>