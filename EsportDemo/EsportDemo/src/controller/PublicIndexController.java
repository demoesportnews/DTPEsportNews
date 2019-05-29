package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDAO;
import model.dao.NewDAO;

//index
public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		NewDAO newDAO = new NewDAO();
		ArrayList<News> listNew = newDAO.getItems();
		request.setAttribute("listNew", listNew);
		CategoryDAO catDAO = new CategoryDAO();
		ArrayList<Category> listCat = catDAO.getParentItems(0);
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
