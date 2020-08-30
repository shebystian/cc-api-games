package cl.kranio.cc.model;

import java.io.Serializable;

public class GameRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4240385730894962433L;
	private String name;
	private String category;
	private int year;
	
	public GameRequest() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}
