package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.AuthUtil;
import Util.DefineUtil;
import model.bean.Category;
import model.dao.CategoryDAO;


//admin/category/index
public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		CategoryDAO catDAO = new CategoryDAO();
		ArrayList<Category> listCatParent = catDAO.getParentItems(0);
		ArrayList<Category> listCat = catDAO.getNotParentItems(0);
		request.setAttribute("listCatParent", listCatParent);
		request.setAttribute("listCat", listCat);
		int numberOfItems = listCatParent.size();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		if(currentPage > numberOfPages || currentPage < 1){
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberOfItems", numberOfItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
