package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Util.DBConnectionUtil;
import model.bean.Comment;

public class CommentDAO {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	public int addComment(Comment objComment) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO comment(id_news, id_user, news, content, status, parent_id) "
					+ "VALUE(?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objComment.getId_news());
			pst.setInt(2, objComment.getId_user());
			pst.setString(3, objComment.getNews());
			pst.setString(4, objComment.getContent());
			pst.setInt(5, objComment.getStatus());
			pst.setInt(6, objComment.getParent_id());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	public ArrayList<Comment> getListComment(int id){
		ArrayList<Comment> listComment = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM comment WHERE id_news = ? AND status = 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()){
				Comment objComment = new Comment(rs.getInt("id"), rs.getInt("id_news"), rs.getInt("id_user"), rs.getString("news"), 
					 rs.getString("content"), rs.getInt("status"), rs.getInt("parent_id"));
				listComment.add(objComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listComment;
	}
	public ArrayList<Comment> getComments(int offset) {
		ArrayList<Comment> listComment = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM comment ORDER BY id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, 5);
			rs = pst.executeQuery();
			while(rs.next()){
				Comment objComment = new Comment(rs.getInt("id"), rs.getInt("id_news"),rs.getInt("id_user"), rs.getString("news"), 
						 rs.getString("content"), rs.getInt("status"), rs.getInt("parent_id"));
				listComment.add(objComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listComment;
	}
	public int editstatus(int id, int i) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE comment SET status = ? WHERE id = ?";
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
	public int countItems() {
		int count = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) as count FROM comment";
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
	public ArrayList<Comment> getListCommentSearch(String newsName) {
		ArrayList<Comment> listComment = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT c.id, c.id_news, c.id_user, c.news, c.content, c.status, c.parent_id, "
				+ "n.id, n.category FROM comment AS c INNER JOIN news AS n ON c.id_news = n.id "
				+ "WHERE n.name LIKE ? ORDER BY n.id";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+newsName+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				Comment objComment = new Comment(rs.getInt("id"), rs.getInt("id_news"), rs.getInt("id_user"), rs.getString("news"), 
						 rs.getString("content"), rs.getInt("status"), rs.getInt("parent_id"));
				listComment.add(objComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listComment;
	}
}
