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
import javax.servlet.http.Part;

import Util.AuthUtil;
import Util.DefineUtil;
import Util.FileUtil;
import model.bean.News;
import model.dao.NewDAO;

//editNews
@MultipartConfig
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminEditNewsController() {
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
		NewDAO newDAO = new NewDAO();
		News objNew = newDAO.getItem(id);
		request.setAttribute("objNew", objNew);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		NewDAO newDAO = new NewDAO();
		// phần tử k phải là file
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		int cat_id = Integer.parseInt(request.getParameter("cat_id"));
		int id = Integer.parseInt(request.getParameter("id"));
		// lấy thông tin file
		Part filePart = request.getPart("picture");
		// get dữ liệu cũ
		News objNew = newDAO.getItem(id);
		if(objNew == null){
			response.sendRedirect(request.getContextPath() + "/admin/news/index.jsp?err=1");
			return;
		}
		
		String dirPath = request.getServletContext().getRealPath("")+DefineUtil.DIR_UPLOAD; // đường dẫn thực từ ổ đĩa.
		File createDir = new File(dirPath);
		if(!createDir.exists()){
			createDir.mkdir();
		}
		String fileName = filePart.getSubmittedFileName();
		if(fileName.isEmpty()){
			fileName = objNew.getPicture();
		} else{
			fileName = FileUtil.rename(fileName);
		}
		String filePath = dirPath + File.separator + fileName;
		
		News objeditNew = new News(id, name, preview, detail, null, 0, fileName, 1, 1, cat_id, "",0, "");
		if(newDAO.editItem(objeditNew) > 0){
			if(!fileName.isEmpty()){
				// xóa file cũ
				String oldFilePathName = dirPath + File.separator + objeditNew.getPicture();
				File oldFile = new File(oldFilePathName);
				if(oldFile.exists()){
					oldFile.delete();
				}
				// ghi file
				filePart.write(filePath);
				
				response.sendRedirect(request.getContextPath() + "/admin/news/index?msg=2");
			}
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
