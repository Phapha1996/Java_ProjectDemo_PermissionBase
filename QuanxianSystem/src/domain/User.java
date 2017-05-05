package domain;

import java.util.HashSet;
import java.util.Set;

public class User {

	private String id;
	private String username;
	private String password;
	private Set roles = new HashSet();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set getRoles() {
		return roles;
	}
	public void setRoles(Set roles) {
		this.roles = roles;
	}
	
	
	@Override
	public boolean equals(Object obj){
		return this.getId().equals(((User) obj).getId());
	}
	
	@Override
	public int hashCode(){
		return super.hashCode();
	}
	
}
