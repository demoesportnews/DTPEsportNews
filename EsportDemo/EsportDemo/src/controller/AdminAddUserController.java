package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.AuthUtil;
import Util.StringUtil;
import model.bean.Admin;
import model.dao.AdminDAO;

//addUser
public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAddUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/admin/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		int position = Integer.parseInt(request.getParameter("position"));
		password = StringUtil.md5(password);
		AdminDAO adminDAO = new AdminDAO();
		Admin objAdmin = new Admin(0, username, password, fullname, position);
		if(adminDAO.add(objAdmin) > 0){
			response.sendRedirect(request.getContextPath()+"/admin/admin/index?msg=1");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/admin/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
