package model;

public class User {

	private String name;
	private String password;
	private String securityLevel;
	
	public User(String name, String password, String securityLevel) {
		super();
		this.name = name;
		this.password = password;
		this.securityLevel = securityLevel;
	}
	
	public User (String name, String password) {
		this.name = name;
		this.password = password;
		this.securityLevel = "r";
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getSecurityLevel() {
		return securityLevel;
	}
	
	

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}

	@Override
	public String toString() {
		
		return "This is user: " + name;
	}
	
	
	
}