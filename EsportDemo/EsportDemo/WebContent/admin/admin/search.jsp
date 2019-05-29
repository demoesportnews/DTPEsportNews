<%@page import="model.bean.Admin"%>
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
				<h2>Quản lý Người Dùng</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<%
        String msg = request.getParameter("msg");
        String err = request.getParameter("err");
        if("1".equals(msg)){
        	out.print("<span style=\"background: yellow; color: green; font-weight: bold; padding: 5px;\">Thêm người dùng thành công</span>");
        }
        if("3".equals(msg)){
        	out.print("<span style=\"background: yellow; color: green; font-weight: bold; padding: 5px;\">Xóa người dùng thành công</span>");
        }
        if("3".equals(err)){
        	out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa người dùng thất bại</span>");
        }
        %>
		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row">
								<div class="col-sm-6">
									<a href="<%=request.getContextPath() %>/addUser"
										class="btn btn-success btn-md"><i class="fa fa-plus"></i>Thêm</a>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="post"
										action="<%=request.getContextPath()%>/adminSearchUser">
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
										<th>ID</th>
										<th>Tên đăng nhập</th>
										<th>Họ tên</th>
										<th>Chức vụ</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<%
                                ArrayList<Admin> listAdmin = (ArrayList<Admin>) request.getAttribute("listAdmin");
								Admin curr = (Admin) session.getAttribute("userInfo");
                                if(listAdmin != null && listAdmin.size() > 0){
                                	for(Admin objAdmin: listAdmin){
                                		String del = request.getContextPath()+"/delUser?id="+objAdmin.getId();
                                		String edit = request.getContextPath()+"/editUser?id="+objAdmin.getId();
                                %>
								<tbody>
									<tr>
										<td class="center"><%=objAdmin.getId() %></td>
										<td class="center"><%=objAdmin.getUsername()%></td>
										<td class="center"><%=objAdmin.getFullname() %></td>
										<td class="center">
											<% if(objAdmin.getPosition() == 1) out.print("Quản lí");
											   if(objAdmin.getPosition() == 2) out.print("Nhân viên");
											   if(objAdmin.getPosition() == 3) out.print("Người dùng");
											%>
										</td>
										<td class="center" align="center">
											<%if(curr.getPosition() == 1) {%>
												<a href="<%=edit%>"
													title="" class="btn btn-primary" title="Edit Admin"><i
														class="fa fa-edit "></i></a> 
												<a href="<%=del%>"
													onclick="return confirm('Bạn muốn xóa người dùng <%=objAdmin.getFullname()%>') "
													title="" class="btn btn-danger" title="Delete Admin"><i
														class="fa fa-trash"></i></a>
											<%} 
											else if (curr.getPosition()==2) { 
												if(curr.getId()==objAdmin.getId() || objAdmin.getPosition() == 3){
											%>
												<a href="<%=edit %>"
												title="" class="btn btn-primary" title="Edit Admin"><i
													class="fa fa-edit "></i></a> 
												<a href="<%=del%>"
													onclick="return confirm('Bạn muốn xóa người dùng <%=objAdmin.getFullname()%>') "
													title="" class="btn btn-danger" title="Delete Admin"><i
														class="fa fa-trash"></i></a>
												<%}}
											else if(curr.getPosition()==3) {
												if(curr.getId()==objAdmin.getId()){
											%>
												<a href="<%=edit %>"
													title="" class="btn btn-primary" title="Edit Admin"><i
														class="fa fa-edit "></i></a> 
												<a href="<%=del%>"
													onclick="return confirm('Bạn muốn xóa người dùng <%=objAdmin.getFullname()%>') "
													title="" class="btn btn-danger" title="Delete Admin"><i
														class="fa fa-trash"></i></a>
												
											<%}} %>
										</td>
									</tr>
									<%}} else { %>
									<tr>
										<td colspan="5" align="center">Chưa có người dùng nào</td>
									</tr>
									<%} %>
								</tbody>

							</table>
						
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
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>