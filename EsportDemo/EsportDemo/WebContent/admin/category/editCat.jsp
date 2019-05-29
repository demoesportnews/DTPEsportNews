<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.NewDAO"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Sửa Danh Mục Cha</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<%
							if(request.getParameter("err") != null ){
								int msg = Integer.parseInt(request.getParameter("msg"));
								if(msg == 4){
									out.print("Sửa thất bại");
								}
							}
                            String name = request.getParameter("name");
                            Category objCat = (Category) request.getAttribute("objCat");
                            if(objCat != null){
							%>
								<form role="form" method="post" id="form">
									<div class="form-group">
										<label for="name">Tên Danh Mục</label> <input type="text"
											id="name" value="<%=objCat.getName() %>" name="name"
											class="form-control" />
									</div>
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Sửa</button>
									<button type="reset" name="reset"
										class="btn btn-default btn-md">Reset</button>
								</form>
								<%} else { %>
								<p>Không tồn tại danh mục này</p>
								<%} %>
							</div>
						</div>
					</div>
				</div>
				<!-- End Form Elements -->
			</div>
		</div>
		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>
<script>
	$().ready(function(){
		// validate the form when it is submitted
		var validator = $("#form").validate({
			errorPlacement: function(error, element){
				$(element).closest("form").find("label[for='"+element.attr("id")+"']").append(error);
			},
			errorElement: "span",
			rules: {
				name: {
					required: true,
				},
			},
			messages: {
				name:{
					required: "(Tên danh mục không được trống)",
				},
			}
		});
	});
</script>
<script>
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>