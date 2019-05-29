package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.AuthUtil;
import model.dao.NewDAO;

//delNews
public class AdminDelNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDelNewsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		NewDAO newDAO = new NewDAO();
		if(newDAO.del(id) > 0){
			response.sendRedirect(request.getContextPath()+"/admin/news/index?msg=3");
			return;
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/news/index?err=3");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
