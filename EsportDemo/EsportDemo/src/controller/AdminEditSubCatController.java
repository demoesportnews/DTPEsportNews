package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.AuthUtil;
import model.bean.Category;
import model.dao.CategoryDAO;

//editSubCat
public class AdminEditSubCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminEditSubCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int subCatId = Integer.parseInt(request.getParameter("subCatId"));
		CategoryDAO catDAO = new CategoryDAO();
		Category objSubCat = catDAO.getItem(subCatId);
		request.setAttribute("objSubCat", objSubCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/editSubCat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String name = request.getParameter("name");
		int parent_id = Integer.parseInt(request.getParameter("parent_id"));
		int subCatId = Integer.parseInt(request.getParameter("subCatId"));
		CategoryDAO catDAO = new CategoryDAO();
		Category objCat = new Category(subCatId, name, parent_id);
		if(catDAO.editSubCat(objCat) > 0){
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=2");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/editSubCat.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
