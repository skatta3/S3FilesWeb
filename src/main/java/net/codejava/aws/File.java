package net.codejava.aws;

public class File {

	protected int id;
	protected String name;
	protected String description;
	protected String email1;
	protected String email2;
	protected String email3;
	protected String email4;
	protected String email5;
	
	
	public File() {
	}


	public File(int id, String name, String description, String email1, String email2, String email3, String email4,
			String email5) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.email1 = email1;
		this.email2 = email2;
		this.email3 = email3;
		this.email4 = email4;
		this.email5 = email5;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getEmail1() {
		return email1;
	}


	public void setEmail1(String email1) {
		this.email1 = email1;
	}


	public String getEmail2() {
		return email2;
	}


	public void setEmail2(String email2) {
		this.email2 = email2;
	}


	public String getEmail3() {
		return email3;
	}


	public void setEmail3(String email3) {
		this.email3 = email3;
	}


	public String getEmail4() {
		return email4;
	}


	public void setEmail4(String email4) {
		this.email4 = email4;
	}


	public String getEmail5() {
		return email5;
	}


	public void setEmail5(String email5) {
		this.email5 = email5;
	}
	

}
