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
import model.bean.Admin;
import model.bean.News;
import model.dao.AdminDAO;
import model.dao.NewDAO;

//admin/admin/index
public class AdminIndexAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexAdminController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		AdminDAO adminDAO = new AdminDAO();
		int numberOfItems = adminDAO.countItems();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / 6);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		if(currentPage > numberOfPages || currentPage < 1){
			currentPage = 1;
		}
		int offset = (currentPage - 1) * 6;
		ArrayList<Admin> listAdmin = adminDAO.getItems(offset);
		request.setAttribute("listAdmin", listAdmin);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/admin/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
