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

//login
public class AuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthLoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO adminDAO = new AdminDAO();
		String username = request.getParameter("Username");
		String password = StringUtil.md5(request.getParameter("Password"));
		Admin userInfo = adminDAO.getItemByUsernamePassword(username, password);
		
		if(userInfo != null){
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
			if(userInfo.getPosition()==3)
				response.sendRedirect(request.getContextPath() + "/index");
			else
				response.sendRedirect(request.getContextPath() + "/admin/news/index");
		} else{
			response.sendRedirect(request.getContextPath() + "/login?msg=0");
		}
	}
}
