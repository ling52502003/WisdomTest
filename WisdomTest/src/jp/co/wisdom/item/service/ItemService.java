package jp.co.wisdom.item.service;

import java.util.List;

import jp.co.wisdom.item.entity.Item;
import jp.co.wisdom.item.entity.UserItem;

public interface ItemService {

	public Item queryItemByid(int id);

	public List<Item> queryItemBy();

	public int deleteItem(int id);

	public int insertItem(Item item);

	public List<Item> queryItems(String key, String _orderBy);

	public UserItem queryValidUser(String _userID, String _password);

	public UserItem queryUser(String id);

	public UserItem[]  queryUsers();
	
	public int updateItem(Item item);

}
