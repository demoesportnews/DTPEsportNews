package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.AuthUtil;
import model.dao.CategoryDAO;
import model.dao.NewDAO;

//delCat
public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDelCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int catId = Integer.parseInt(request.getParameter("catId"));
		CategoryDAO catDAO = new CategoryDAO();
		NewDAO newDAO = new NewDAO();
		if(catDAO.delCat(catId) > 0 && newDAO.del(catId) > 0){
			response.sendRedirect(request.getContextPath()+"/admin/category/index?msg=3");
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/category/index?msg=6");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
