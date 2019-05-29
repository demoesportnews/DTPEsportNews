package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.News;
import model.dao.NewDAO;

//detail
public class PublicDetailIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicDetailIndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		int idCat = Integer.parseInt(request.getParameter("idCat"));
		NewDAO newDAO = new NewDAO();
		News objNew = newDAO.getItem(id);
		if(objNew == null){
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		// increase viewcounter
		HttpSession session = request.getSession();
		String hasVisited = (String) session.getAttribute("hasVisited: " + id);
		if(hasVisited == null){
			session.setAttribute("hasVisited: " + id, "yes");
			session.setMaxInactiveInterval(3600);
			newDAO.increaseView(id);
		}
		ArrayList<News> listRelatedNew = newDAO.getRelatedNew(idCat);
		request.setAttribute("objNew", objNew);
		request.setAttribute("listRelatedNew", listRelatedNew);
		RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
