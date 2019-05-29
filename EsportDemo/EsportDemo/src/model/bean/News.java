package model.bean;

import java.sql.Timestamp;

public class News {
	private int id;
	private String name;
	private String preview;
	private String detail;
	private Timestamp date_create;
	private int count;
	private String picture;
	private int who_create;
	private int status;
	private int category;
	private String catName;
	private int parent_id;
	private String userName;
	
	
	public News() {
		super();
	}


	public News(int id, String name, String preview, String detail, Timestamp date_create, int count, String picture,
			int who_create, int status, int category, String catName, int parent_id, String userName) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.date_create = date_create;
		this.count = count;
		this.picture = picture;
		this.who_create = who_create;
		this.status = status;
		this.category = category;
		this.catName = catName;
		this.parent_id = parent_id;
		this.userName = userName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPreview() {
		return preview;
	}


	public void setPreview(String preview) {
		this.preview = preview;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public Timestamp getDate_create() {
		return date_create;
	}


	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public int getWho_create() {
		return who_create;
	}


	public void setWho_create(int who_create) {
		this.who_create = who_create;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getCategory() {
		return category;
	}


	public void setCategory(int category) {
		this.category = category;
	}


	public String getCatName() {
		return catName;
	}


	public void setCatName(String catName) {
		this.catName = catName;
	}


	public int getParent_id() {
		return parent_id;
	}


	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
