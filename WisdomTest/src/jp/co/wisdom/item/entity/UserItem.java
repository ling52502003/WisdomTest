package jp.co.wisdom.item.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import jp.co.wisdom.base.entity.BaseObject;

/**
 * ユーザ情報を保持します。
 */
public class UserItem extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 167281642280391967L;
	/**
	 * IDを保持します。
	 */
	private String id;

	/**
	 * 名前を保持します。
	 */
	private String name;

	/**
	 * パスワードを保持します。
	 */
	private String password;

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
