package com.ratel.common.domain;

public class Encrypt {

	private String password;
	
	private String salt;
	
	private String md5Passwd;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getMd5Passwd() {
		return md5Passwd;
	}

	public void setMd5Passwd(String md5Passwd) {
		this.md5Passwd = md5Passwd;
	}

	@Override
	public String toString() {
		return "Encrypt [password=" + password + ", salt=" + salt + ", md5Passwd=" + md5Passwd + "]";
	}
	
	
	
}
