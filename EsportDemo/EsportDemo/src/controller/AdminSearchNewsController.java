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
import model.bean.News;
import model.dao.NewDAO;

public class AdminSearchNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminSearchNewsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String search = request.getParameter("search");
		NewDAO newDAO = new NewDAO();
		ArrayList<News> listNewSearch = newDAO.searchNewsAdmin(search);
		request.setAttribute("listNew", listNewSearch);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/search.jsp");
		rd.forward(request, response);
	}

}
