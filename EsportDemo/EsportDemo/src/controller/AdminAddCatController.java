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

//addCat
public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAddCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String catName = request.getParameter("name");
		int parent_id = Integer.parseInt(request.getParameter("parent_id"));
		CategoryDAO catDAO = new CategoryDAO();
		Category objCat = new Category(0, catName, parent_id);
		if(catDAO.addCat(objCat) > 0){
			response.sendRedirect(request.getContextPath()+"/admin/category/index?msg=1");
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/category/add.jsp?msg=4");
		}
	}

}
