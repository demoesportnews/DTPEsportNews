package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableInterceptor.INACTIVE;

import Util.AuthUtil;
import model.bean.Category;
import model.dao.CategoryDAO;

//editCat
public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminEditCatController() {
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
		int catId = Integer.parseInt(request.getParameter("catId"));
		CategoryDAO catDAO = new CategoryDAO();
		Category objCat = catDAO.getItem(catId);
		request.setAttribute("objCat", objCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/editCat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String name = request.getParameter("name");
		int catId = Integer.parseInt(request.getParameter("catId"));
		CategoryDAO catDAO = new CategoryDAO();
		Category objCat = new Category(catId, name, 0);
		if(catDAO.editCat(objCat) > 0){
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=2");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/editCat.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
