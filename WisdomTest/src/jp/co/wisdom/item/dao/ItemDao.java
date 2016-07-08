package jp.co.wisdom.item.dao;

import java.util.List;
import java.util.Map;

import jp.co.wisdom.item.entity.Item;


public interface ItemDao {
	 
	public Item queryItemByid(int id);
	 
	public List<Item> queryItemBy();
	 
	public int deleteItem(int id);
	 
	public int insertItem(Item item);
	 
	public List<Item> queryItems(Map map);
	
	public int updateItem(Item item);
}
