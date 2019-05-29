package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.DefineUtil;
import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDAO;
import model.dao.NewDAO;

public class PublicCatIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicCatIndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		int catId = Integer.parseInt(request.getParameter("catId"));
		CategoryDAO catDAO = new CategoryDAO();
		Category objCat = catDAO.getItem(catId);
		request.setAttribute("objCat", objCat);
		NewDAO newDAO = new NewDAO();
		ArrayList<News> listNewsByCatId= newDAO.getItemsByParentId(catId);
		int numberOfItems = listNewsByCatId.size();
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
		ArrayList<News> listNewsByCatIdPanigation = newDAO.getItemsPagination(offset,catId);
		request.setAttribute("listNewsByCatId", listNewsByCatIdPanigation);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/public/category.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
