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

//subcat
public class PublicSubCatIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicSubCatIndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		int subCatId = Integer.parseInt(request.getParameter("subcatId"));
		NewDAO newDAO = new NewDAO();
		
		//request.setAttribute("listNewsBySubCatId", listNewsBySubCatId);
		int numberOfItems = newDAO.countItems(subCatId);
		int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
		}
		if(currentPage > numberOfPages || currentPage < 1){
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		ArrayList<News> listNewsBySubCatId = newDAO.getItemsPaginationBySubCatId(offset, subCatId);
		request.setAttribute("listNewsBySubCatId", listNewsBySubCatId);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/public/subcategory.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
