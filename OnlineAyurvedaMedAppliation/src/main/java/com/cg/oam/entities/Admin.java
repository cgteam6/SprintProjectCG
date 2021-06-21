package com.cg.oam.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="admintable")
public class Admin{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer AdminId;
	String password; 
	String Adminname;
	@OneToOne
	User user;
	

	public Admin() {
	}


	public Admin(Integer AdminId,String Adminname, String password, User user) {
		super();
		this.AdminId = AdminId;
		this.password = password;
		this.user = user;
		this.Adminname=Adminname;
	}


	public String getName() {
		return Adminname;
	}


	public void setName(String Adminname) {
		this.Adminname = Adminname;
	}


	public int getId() {
		return AdminId;
	}


	public void setId(Integer AdminId) {
		this.AdminId = AdminId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Admin [AdminId=" + AdminId + ", password=" + password + ", user=" + user + "]";
	}




	
}
