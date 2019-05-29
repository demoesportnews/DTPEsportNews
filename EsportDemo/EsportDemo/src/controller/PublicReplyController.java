package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//reply
public class PublicReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicReplyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("aid"));
		out.print("<div class='entity_comment_from'> "+
			        "<form action='javascript: void(0)' onsubmit='return commentreply("+id+")'> "+
			            "<div class='form-group'>" +
			                "<input type='text' class='form-control' id='inputEmail' placeholder='Email'>"+
			            "</div>"+
			            "<div class='form-group comment'>"+
			                "<textarea class='form-control' id='inputComment' placeholder='Comment'></textarea>"+
			            "</div>"+
			            "<button type='submit' class='btn btn-submit red'>Submit</button> "+
			        "</form>" +
			    "</div>");
	}
}
