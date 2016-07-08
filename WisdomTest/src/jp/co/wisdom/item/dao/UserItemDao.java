package jp.co.wisdom.item.dao;

import java.util.List;

import jp.co.wisdom.item.entity.UserItem;

public interface UserItemDao {
	public UserItem queryValidUser(UserItem user);
	public UserItem queryUser(String id);
	public List<UserItem> queryUsers();
}
