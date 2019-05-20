package com.smelp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name = "AUTH_USER")
@Data
public class DUser {
	
	    @Id
	    private String id;
	    @Column(length = 32)
	    private String name;
	    @Column(length = 32)
	    private String account;
	    @Column(length = 64)
	    private String pwd;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
	    
	    
	    

}
