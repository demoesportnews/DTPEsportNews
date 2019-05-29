package model.bean;

public class Comment {
	private int id;
	private int id_news;
	private int id_user;
	private String news;
	private String content;
	private int status;
	private int parent_id;
	public Comment() {
		super();
	}
	public Comment(int id, int id_news,int id_user, String news, String content, int status, int parent_id) {
		super();
		this.id = id;
		this.id_news = id_news;
		this.id_user = id_user;
		this.news = news;
		this.content = content;
		this.status = status;
		this.parent_id = parent_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_news() {
		return id_news;
	}
	public void setId_news(int id_news) {
		this.id_news = id_news;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
	
	
}
