package jp.co.wisdom.item.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.wisdom.item.dao.ItemDao;
import jp.co.wisdom.item.dao.UserItemDao;
import jp.co.wisdom.item.entity.Item;
import jp.co.wisdom.item.entity.UserItem;
import jp.co.wisdom.item.service.ItemService;
import jp.co.wisdom.user.dao.UserDao;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
	@Autowired
	private UserItemDao userItemDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private UserDao userDao;

	public UserItemDao getUserItemDao() {
		return userItemDao;
	}

	public void setUserItemDao(UserItemDao userItemDao) {
		this.userItemDao = userItemDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Item queryItemByid(int id) {
		return itemDao.queryItemByid(id);
	}

	public List<Item> queryItemBy() {
		return itemDao.queryItemBy();
	}

	public int deleteItem(int id) {
		return itemDao.deleteItem(id);
	}
	
	public int updateItem(Item item) {
		return itemDao.updateItem(item);
	}

	public int insertItem(Item item) {
		return itemDao.insertItem(item);
	}

	public List<Item> queryItems(String key, String _orderBy) {

		Map keyMap = new HashMap();
		if(key == null){
			key = "";
		}
		keyMap.put("like_id", "%"+key+"%");
		keyMap.put("like_name", "%"+key+"%");
		keyMap.put("order_key", _orderBy == null?1:_orderBy);
		List<Item> queryItems = itemDao.queryItems(keyMap);
		return queryItems;
	}

	public UserItem queryValidUser(String _userID, String _password) {
		UserItem user = new UserItem();
		user.setId(_userID);
		user.setPassword(_password);
		UserItem userRet = null;
		userRet = userItemDao.queryValidUser(user);
		return userRet;
	}

	public UserItem queryUser(String id) {
		return userItemDao.queryUser(id);
	}

	public UserItem[] queryUsers() {
		List users = userItemDao.queryUsers();
		return (UserItem[]) users.toArray(new UserItem[0]);
	}

}
