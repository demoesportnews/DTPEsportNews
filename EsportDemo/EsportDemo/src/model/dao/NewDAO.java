package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Util.DBConnectionUtil;
import Util.DefineUtil;
import model.bean.Admin;
import model.bean.Category;
import model.bean.News;

public class NewDAO {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	public ArrayList<News> getItems() {
		ArrayList<News> listNews = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id, n.name, preview, detail, date_create, count,"
				+ " picture, who_create, status, category, c.name AS categoryName,"
				+ " c.parent_id AS parent_id, u.username AS userName FROM news AS n INNER JOIN"
				+ " category AS c ON n.category = c.id INNER JOIN user AS u ON n.who_create = u.id WHERE status = 1 ORDER BY n.id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				News objNew = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
				listNews.add(objNew);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listNews;
	}
	public ArrayList<News> getItemsByParentId(int catId) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id, n.name, preview, detail, date_create, count, picture, who_create"
				+ ", status, category, c.name AS categoryName, c.parent_id AS parent_id, u.username AS"
				+ " userName FROM news AS n INNER JOIN category AS c ON n.category = c.id INNER JOIN user"
				+ " AS u ON n.who_create = u.id WHERE status = 1 AND c.parent_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			while(rs.next()){
				News objNew = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
				listNews.add(objNew);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listNews;
	}
	/*public News getItemFirst(int catId) {
		News objFirst = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT MAX(v.id) FROM (SELECT n.id, n.name, preview, detail, date_create, count, picture, sub_cat_id, who_create, s.name AS subName, c.id as catId, c.name AS catName, a.name AS adName, a.id AS adId FROM news AS n INNER JOIN sub_category AS s ON n.sub_cat_id = s.id INNER JOIN category AS c ON s.cat_id = c.id INNER JOIN admin AS a ON n.who_create = a.id WHERE c.id = ? ORDER BY n.id DESC) AS v";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			if(rs.next()){
				objFirst = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"), rs.getString("picture"), rs.getInt("sub_cat_id"), rs.getInt("who_create"), rs.getString("subName"), rs.getInt("catId"), rs.getString("catName"), rs.getString("adName"), rs.getInt("adId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return objFirst;
	}*/
	public ArrayList<News> getPopularNew() {
		ArrayList<News> listNews = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id, n.name, preview, detail, date_create, count,"
				+ " picture, who_create, status, category, c.name AS categoryName,"
				+ " c.parent_id AS parent_id, u.username AS userName FROM news AS n INNER JOIN"
				+ " category AS c ON n.category = c.id INNER JOIN user AS u ON n.who_create = u.id WHERE status = 1 ORDER BY n.count DESC LIMIT 6";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				News objNew = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
				listNews.add(objNew);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listNews;
	}
	public News getItem(int id) {
		News objNew = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id, n.name, preview, detail, date_create, count,"
				+ " picture, who_create, status, category, c.name AS categoryName,"
				+ " c.parent_id AS parent_id, u.username AS userName FROM news AS n INNER JOIN"
				+ " category AS c ON n.category = c.id INNER JOIN user AS u ON n.who_create = u.id WHERE n.id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				objNew = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return objNew;
	}
	public ArrayList<News> getItemsBySubCatId(int subCatId) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id, n.name, preview, detail, date_create, count, picture, who_create"
				+ ", status, category, c.name AS categoryName, c.parent_id AS parent_id, u.username AS"
				+ " userName FROM news AS n INNER JOIN category AS c ON n.category = c.id INNER JOIN user"
				+ " AS u ON n.who_create = u.id WHERE status = 1 AND c.id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, subCatId);
			rs = pst.executeQuery();
			while(rs.next()){
				News objNew = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
				listNews.add(objNew);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listNews;
	}
	public int numberOfItems(int catId) {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM news WHERE cat_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			if(rs.next()){
				int count  = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return 0;
	}
	public ArrayList<News> getItemsPagination(int offset, int catId) {
		ArrayList<News> listItems = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql =  "SELECT n.id, n.name, preview, detail, date_create, count,"
				+ " picture, who_create, status, category, c.name AS categoryName,"
				+ " c.parent_id AS parent_id, u.username AS userName FROM news AS n"
				+ " INNER JOIN category AS c ON n.category = c.id INNER JOIN user AS u"
				+ " ON n.who_create = u.id WHERE status = 1 AND c.parent_id = ? ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()){
				News objNew =  new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
				listItems.add(objNew);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}
	public int addItem(News objNew) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO news (name, preview, detail, picture, "
				+ " who_create, category) VALUE(?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNew.getName());
			pst.setString(2, objNew.getPreview());
			pst.setString(3, objNew.getDetail());
			pst.setString(4, objNew.getPicture());
			pst.setInt(5, objNew.getWho_create());
			pst.setInt(6, objNew.getCategory());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	public int editItem(News objeditNew) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE news SET name = ?, preview = ?,detail = ?,picture = ?, category = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objeditNew.getName());
			pst.setString(2, objeditNew.getPreview());
			pst.setString(3, objeditNew.getDetail());
			pst.setString(4, objeditNew.getPicture());
			pst.setInt(5, objeditNew.getCategory());
			pst.setInt(6, objeditNew.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	public int del(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM news WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	public int editstatus(int id, int i) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE news SET status = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setInt(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	public ArrayList<News> getRelatedNew(int idCat) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id, n.name, preview, detail, date_create, count, picture, who_create"
				+ ", status, category, c.name AS categoryName, c.parent_id AS parent_id, u.username AS"
				+ " userName FROM news AS n INNER JOIN category AS c ON n.category = c.id INNER JOIN user"
				+ " AS u ON n.who_create = u.id WHERE category = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			while(rs.next()){
				News objNew = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
				listNews.add(objNew);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listNews;
	}
	public int countItems(int subCatId) {
		int count = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) as count FROM news WHERE category = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, subCatId);
			rs = pst.executeQuery();
			if(rs.next()){
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	public ArrayList<News> getItemsPaginationBySubCatId(int offset, int subCatId) {
		ArrayList<News> listItems = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql =  "SELECT n.id, n.name, preview, detail, date_create, count,"
				+ " picture, who_create, status, category, c.name AS categoryName,"
				+ " c.parent_id AS parent_id, u.username AS userName FROM news AS n"
				+ " INNER JOIN category AS c ON n.category = c.id INNER JOIN user AS u"
				+ " ON n.who_create = u.id WHERE status = 1 AND c.id = ? ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, subCatId);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()){
				News objNew =  new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
				listItems.add(objNew);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}
	public int countItems() {
		int count = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) as count FROM news";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public ArrayList<News> getItems(int offset) {
		ArrayList<News> listItems = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql =  "SELECT n.id, n.name, preview, detail, date_create, count,"
				+ " picture, who_create, status, category, c.name AS categoryName,"
				+ " c.parent_id AS parent_id, u.username AS userName FROM news AS n"
				+ " INNER JOIN category AS c ON n.category = c.id INNER JOIN user AS u"
				+ " ON n.who_create = u.id ORDER BY n.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()){
				News objNew =  new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
				listItems.add(objNew);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}
	public int delNewByAdminId(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM news WHERE who_create = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	public ArrayList<News> searchNews(String search) {
		ArrayList<News> listItems = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id, n.name, preview, detail, date_create, count,"
				+ " picture, who_create, status, category, c.name AS categoryName,"
				+ " c.parent_id AS parent_id, u.username AS userName FROM news AS n"
				+ " INNER JOIN category AS c ON n.category = c.id INNER JOIN user AS u"
				+ " ON n.who_create = u.id WHERE status = 1 AND n.name LIKE ? ORDER BY n.id";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+search+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				News objNew =  new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
				listItems.add(objNew);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}
	public ArrayList<News> searchNewsAdmin(String search) {
		ArrayList<News> listItems = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT n.id, n.name, preview, detail, date_create, count,"
				+ " picture, who_create, status, category, c.name AS categoryName,"
				+ " c.parent_id AS parent_id, u.username AS userName FROM news AS n"
				+ " INNER JOIN category AS c ON n.category = c.id INNER JOIN user AS u"
				+ " ON n.who_create = u.id WHERE n.name LIKE ? ORDER BY n.id";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+search+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				News objNew =  new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getInt("count"),
						rs.getString("picture"), rs.getInt("who_create"), rs.getInt("status"), 
						rs.getInt("category"), rs.getString("categoryName"),rs.getInt("parent_id"), rs.getString("userName"));
				listItems.add(objNew);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItems;
	}
	public void increaseView(int id) {
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE news SET count = count+1 WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
	}
}
