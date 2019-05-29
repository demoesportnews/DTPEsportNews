package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.dao.NewDAO;

@WebServlet("/PublicSearchController")
public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicSearchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String search = request.getParameter("information");
		NewDAO newDAO = new NewDAO();
		ArrayList<News> listNewSearch = newDAO.searchNews(search);
		request.setAttribute("listNewSearch", listNewSearch);
		RequestDispatcher rd = request.getRequestDispatcher("/public/search.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
