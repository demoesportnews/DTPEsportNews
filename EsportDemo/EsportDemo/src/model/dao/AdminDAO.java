package model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Util.DBConnectionUtil;
import model.bean.Admin;
import model.bean.News;

public class AdminDAO {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	public ArrayList<Admin> getItems() {
		ArrayList<Admin> listAdmin = new ArrayList<>();
		String sql = "SELECT * FROM user ORDER BY id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Admin objAdmin = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("position"));
				listAdmin.add(objAdmin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, st, conn);
		}
		return listAdmin;
	}
	public int add(Admin objAdmin) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO user(username, password, fullname, position) VALUES(?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objAdmin.getUsername());
			pst.setString(2, objAdmin.getPassword());
			pst.setString(3, objAdmin.getFullname());
			pst.setInt(4, objAdmin.getPosition());
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
		String sql = "DELETE FROM user WHERE id = ?";
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
	public Admin getItem(int id) {
		Admin objAdmin = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				objAdmin = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("position"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return objAdmin;
	}
	public int edit(Admin objAdmin, int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE user SET username = ?, password = ?, fullname = ?, position = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objAdmin.getUsername());
			pst.setString(2, objAdmin.getPassword());
			pst.setString(3, objAdmin.getFullname());
			pst.setInt(4, objAdmin.getPosition());
			pst.setInt(5, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	public int countItems() {
		int count = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) as count FROM user";
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
	public ArrayList<Admin> getItems(int offset) {
		ArrayList<Admin> listAdmin = new ArrayList<>();
		String sql = "SELECT * FROM user ORDER BY id DESC LIMIT ?,?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, 6);
			rs = pst.executeQuery();
			while(rs.next()){
				Admin objAdmin = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("position"));
				listAdmin.add(objAdmin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listAdmin;
	}
	public Admin getItemByUsernamePassword(String username, String password) {
		Admin objUser = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()){
				objUser = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("position"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return objUser;
	}
	public ArrayList<Admin> searchAdmin(String search) {
		ArrayList<Admin> listAdmin = new ArrayList<>();
		String sql = "SELECT * FROM user WHERE fullname LIKE ? ORDER BY id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+search+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				Admin objAdmin = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("position"));
				listAdmin.add(objAdmin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listAdmin;
	}
}
