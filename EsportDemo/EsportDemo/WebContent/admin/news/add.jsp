﻿<%@page import="model.dao.CategoryDAO"%>
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
				<h2>Thêm bài viết</h2>
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
							if(request.getParameter("msg") != null ){
								int msg = Integer.parseInt(request.getParameter("msg"));
								if(msg == 0){
									out.print("Thêm thất bại");
								}
							}
							%>
								<form role="form" method="post" action=""
									enctype="multipart/form-data" id="form">
									<div class="form-group">
										<label for="name">Tên tin</label> <input type="text" id="name"
											value="" name="name" class="form-control" required="required" />
									</div>

									<div class="form-group">
										<label for="category">Danh mục</label> <select id="cat_id"
											name="cat_id" class="form-control" required="required">
											<%
                                        	CategoryDAO catDAO = new CategoryDAO();
                                        	ArrayList<Category> listSubCat = (ArrayList<Category>) catDAO.getNotParentItems(0);
                                        		if(listSubCat != null && listSubCat.size() > 0){
                                        			for(Category objSubCat: listSubCat){
                                        %>
											<option value="<%=objSubCat.getId()%>"><%=objSubCat.getName() %></option>
											<%}}%>
										</select>
									</div>
									<div class="form-group">
										<label for="picture">Hình ảnh</label> <input type="file"
											name="picture" />
									</div>
									<div class="form-group">
										<label for="preview">Mô tả</label>
										<textarea id="preview" class="form-control" rows="3"
											name="preview" required="required"></textarea>
									</div>
									<div class="form-group">
										<label for="detail">Chi tiết</label>
										<textarea ckeditor="ckeditor" id="detail" class="form-control"
											id="detail" rows="5" name="detail"></textarea>
									</div>
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Thêm</button>
									<button type="reset" name="reset"
										class="btn btn-default btn-md">Reset</button>
								</form>
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
			messages: {
				name:{
					required: "(Tên bài hát không được trống)",
				},
			cat_id:{
				required: "(Chọn danh mục)",
			},
			preview:{
				required: "(Mô tả không được trống)",
			},
			}
		});
	});
</script>
<script>
	var editor = CKEDITOR.replace('detail');// truyền vào id
	CKFinder.setupCKEditor(editor, 'library/ckfinder/');
</script>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>