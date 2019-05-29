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
import model.bean.Comment;
import model.dao.CommentDAO;

//adminSearchComment
public class AdminSearchCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminSearchCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/search.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String newsName = request.getParameter("search");
		CommentDAO commentDAO = new CommentDAO();
		ArrayList<Comment> listCommentSearch = commentDAO.getListCommentSearch(newsName);
		request.setAttribute("listComment", listCommentSearch);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/search.jsp");
		rd.forward(request, response);
	}

}
