package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Util.DBConnectionUtil;
import Util.DefineUtil;
import model.bean.Category;

public class CategoryDAO {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	public ArrayList<Category> getItems() {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM category ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Category objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listCat;
	}
	public int delCat(int catId) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM category WHERE id = ? OR parent_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			pst.setInt(2, catId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}DBConnectionUtil.close(pst, conn);
		return result;
	}
	public int addCat(Category objCat) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO category(name, parent_id) VALUE(?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getParent_id());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	public int numberOfItems() {
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM category";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, st, conn);
		}
		return 0;
	}
	public ArrayList<Category> getItemsPagination(int offset) {
		ArrayList<Category> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM category ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()){
				Category objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
				listItem.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}
	public ArrayList<Category> getParentItems(int id) {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM category WHERE parent_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()){
				Category objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listCat;
	}
	public ArrayList<Category> getNotParentItems(int id) {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM category WHERE parent_id != ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()){
				Category objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listCat;
	}
	public Category getItem(int catId) {
		Category objCat = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM category WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			if(rs.next()){
				objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return objCat;
	}
	public int editCat(Category objCat) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE category SET name = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	public int editSubCat(Category objCat) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE category SET name = ?, parent_id = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getParent_id());
			pst.setInt(3, objCat.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	
}
