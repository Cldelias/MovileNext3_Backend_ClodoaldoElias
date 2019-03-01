package com.br.cldelias.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Client() {
		this.id = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static class Builder {
		
		private Client client;
		
		public Builder() {
			this.client = new Client();
		}
		
		public Builder withName(String name) {
			this.client.setName(name);
			return this;
		}
		
		public Builder withEmail(String email) {
			this.client.setEmail(email);
			return this;
		}
		
		public Builder withPassword(String password) {
			this.client.setPassword(password);
			return this;
		}
		
		public Client build() throws Exception {
			if (this.client.getName() == null || this.client.getName().isEmpty()) {
				throw new IllegalArgumentException("name of client invalid");
			}
			if (this.client.getEmail() == null || this.client.getEmail().isEmpty()) {
				throw new IllegalArgumentException("email of client invalid");
			}
			
			if (this.client.getPassword() == null || this.client.getPassword().isEmpty()) {
				throw new IllegalArgumentException("passWord of client invalid");
			}
			return client;
			
		}
	}

}
