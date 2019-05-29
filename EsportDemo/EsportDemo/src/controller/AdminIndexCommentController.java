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

import Util.AuthUtil;
import model.bean.Comment;
import model.dao.CommentDAO;
import model.dao.NewDAO;

//admin/comment/index
public class AdminIndexCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		CommentDAO commentDAO = new CommentDAO();
		int numberOfItems = commentDAO.countItems();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / 5);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
		}
		if(currentPage > numberOfPages || currentPage < 1){
			currentPage = 1;
		}
		int offset = (currentPage - 1) * 5;
		ArrayList<Comment> listComment = commentDAO.getComments(offset);
		request.setAttribute("listComment", listComment);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		CommentDAO commentDAO = new CommentDAO();
		int id = Integer.parseInt(request.getParameter("aid"));
		int status = Integer.parseInt(request.getParameter("astatus"));
		if(1 == status){
			if(commentDAO.editstatus(id, 2) > 0){
			out.print("<a href='javascript: void(0)' onclick='return getStatus("+id+","+ 2+")'><img src='"+request.getContextPath()+"/files/deactive.gif' alt='deactive' ></a>");
			}
		} else if(2 == status){
			if(commentDAO.editstatus(id, 1) > 0){
			out.print("<a href='javascript: void(0)' onclick='return getStatus("+id+","+ 1+")'><img src='"+request.getContextPath()+"/files/active.gif' alt='active' ></a>");
			}
		}
	}

}
