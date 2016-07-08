package jp.co.wisdom.item.entity;

import java.sql.Date;

import jp.co.wisdom.base.entity.BaseObject;

/**
 * アイテム情報を保持します。
 */
public class Item extends BaseObject {

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
	 * 名前を保持します。
	 */
	private String user;

	/**
	 * 名前を保持します。
	 */
	private String userName;

	
	/**
	 * 期限を保持します。
	 */
	private Date expire_date;

	/**
	 * 終了した日時を保持します。
	 */
	private Date finished_date;

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}

	public Date getFinished_date() {
		return finished_date;
	}

	public void setFinished_date(Date finished_date) {
		this.finished_date = finished_date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return null;
	}
}
