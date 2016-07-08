package jp.co.wisdom.user.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import jp.co.wisdom.base.entity.BaseObject;

public class User extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 167281642280391967L;
	/**
	 * 测试用户ID
	 */
	private Long id;
	/**
	 * 用户账号
	 */
	private String account;
	/**
	 * 用户密码
	 */
	private String password;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	} 
}
