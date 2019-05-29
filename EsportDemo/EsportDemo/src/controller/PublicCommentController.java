package controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.AuthUtil;
import model.bean.Admin;
import model.bean.Comment;
import model.dao.CommentDAO;
import model.dao.AdminDAO;;

//comment
public class PublicCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("aemail");
		String content = request.getParameter("acontent");
		String news = request.getParameter("news");
		int newsId = Integer.parseInt(request.getParameter("news_id"));
		CommentDAO commentDAO = new CommentDAO();
		int userid = 3;
		if(AuthUtil.checkLogin(request, response)){
			HttpSession session = request.getSession();
			Admin curr = (Admin) session.getAttribute("userInfo");
			userid = curr.getId();
		}
		AdminDAO ad = new AdminDAO();
		Admin maker = ad.getItem(userid);
		String fullname = maker.getFullname();
		Comment objComment = new Comment(0, newsId, userid, news, content, 1, 0);
		if(commentDAO.addComment(objComment) > 0){
			out.print("<div class='media-left'>"+
							"<a href='#'>"+
                				"<img alt='64x64' class='media-object' "+
                				" src='"+request.getContextPath()+"/templates/public/assets/img/reader_img1.jpg' data-holder-rendered='true'>"+
                			"</a>"+
                		"</div>"+
                		"<div class='media-body' id=''> "+
			            "<h2 class='media-heading'>"+fullname+"</h2>" + content +
			            "<div class='entity_vote'> "+
			                "<a href='#'><span class='reply_ic'>Reply </span></a> "+
			            "</div>" +
			        "</div>");
		}else{
			out.print("Có lỗi xảy ra, không thể bình luận!");
		}
		
	}

}
