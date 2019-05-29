package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.StringUtil;
import model.bean.Admin;
import model.dao.AdminDAO;

@WebServlet("/AuthRegisterController")
public class AuthRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AuthRegisterController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/register.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		AdminDAO userDAO = new AdminDAO();
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		String fullname = request.getParameter("fullname-input");
		int position = 2;
		System.out.println(username);
		System.out.println(password);
		System.out.println(fullname);
		
		
		Admin objUser = new Admin(0, username, password, fullname, position);
		if(userDAO.add(objUser) > 0) {
			response.sendRedirect(request.getContextPath()+"/login?msg=1");
		} else {
			response.sendRedirect(request.getContextPath()+"/login?msg=2");
		}
	}

}
