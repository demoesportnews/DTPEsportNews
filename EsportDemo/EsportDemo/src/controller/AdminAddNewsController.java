package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Util.AuthUtil;
import Util.DefineUtil;
import Util.FileUtil;
import model.bean.Admin;
import model.bean.News;
import model.dao.NewDAO;

//addNews
@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAddNewsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") != null){
			Admin objUser = (Admin) session.getAttribute("userInfo");
			int who_create = objUser.getId();
			NewDAO newDAO = new NewDAO();
			String name = request.getParameter("name");
			String preview = request.getParameter("preview");
			String detail = request.getParameter("detail");
			int cat_id = Integer.parseInt(request.getParameter("cat_id"));
			// lấy thông tin file
			Part filePart = request.getPart("picture");
			String fileName = filePart.getSubmittedFileName();
			if(!"".equals(fileName)){
				// đổi tên file
				fileName = FileUtil.rename(fileName);
				String dirPath = request.getServletContext().getRealPath("") + DefineUtil.DIR_UPLOAD;
				File createDir = new File(dirPath);
				if(!createDir.exists()){
					createDir.mkdir();
				}
				String filePath = dirPath + File.separator + fileName;
				filePart.write(filePath);
				System.out.println(filePath);
			}
			News objNew = new News(0, name, preview, detail, null, 0, fileName, who_create, 0, cat_id, "",0, "");
			if(newDAO.addItem(objNew) > 0){
				response.sendRedirect(request.getContextPath() + "/admin/news/index?msg=1");
				return;
			}else{
				RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp?err=1");
				return;
			}
		}
		
		
	}

}
