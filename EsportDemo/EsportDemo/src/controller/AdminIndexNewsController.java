package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.DefineUtil;
import model.bean.Admin;
import model.bean.News;
import model.dao.NewDAO;
import Util.AuthUtil;

//admin/news/index
public class AdminIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexNewsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		NewDAO newDAO = new NewDAO();
		int numberOfItems = newDAO.countItems();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE); // 4 item/page
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
		}
		if(currentPage > numberOfPages || currentPage < 1){
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;// 1 item/page
		ArrayList<News> listNew = newDAO.getItems(offset);
		request.setAttribute("listNew", listNew);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		NewDAO newDAO = new NewDAO();
		int id = Integer.parseInt(request.getParameter("aid"));
		int status = Integer.parseInt(request.getParameter("astatus"));
		if(1 == status){
			if(newDAO.editstatus(id, 2) > 0){
			out.print("<a href='javascript: void(0)' onclick='return getStatus("+id+","+ 2+")'><img src='"+request.getContextPath()+"/files/deactive.gif' alt='deactive' ></a>");
			}
		} else if(2 == status){
			if(newDAO.editstatus(id, 1) > 0){
			out.print("<a href='javascript: void(0)' onclick='return getStatus("+id+","+ 1+")'><img src='"+request.getContextPath()+"/files/active.gif' alt='active' ></a>");
			}
		}
	}

}
