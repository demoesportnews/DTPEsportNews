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

//editUser
public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminEditUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		AdminDAO adminDAO = new AdminDAO();
		Admin objAdmin = adminDAO.getItem(id);
		request.setAttribute("objAdmin", objAdmin);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/admin/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		int position = Integer.parseInt(request.getParameter("position"));
		password = StringUtil.md5(password);
		int id = Integer.parseInt(request.getParameter("id"));
		AdminDAO adminDAO = new AdminDAO();
		Admin objAdmin = new Admin(0, username, password, fullname, position);
		if(adminDAO.edit(objAdmin, id) > 0){
			response.sendRedirect(request.getContextPath() + "/admin/admin/index?msg=2");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/admin/edit.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
