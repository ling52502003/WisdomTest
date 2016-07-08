package jp.co.wisdom.item.aciton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jp.co.wisdom.base.action.BaseAction;
import jp.co.wisdom.base.action.ModifyException;
import jp.co.wisdom.item.entity.Item;
import jp.co.wisdom.item.service.ItemService;

/**
 * 削除をおこなうアクション。
 */
@Controller("DeleteAction")
@Scope("prototype")
public class DeleteAction extends BaseAction {

	/**
	 * シリアルバージョンUID。
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * アイテムIDを保持します。
	 */
	private String _itemID;

	/**
	 * アイテムを保持します。
	 */
	private Item _item;

	/**
	 * アイテムIDを設定します。
	 * 
	 * @param id
	 */
	public void setItem_id(String id) {
		_itemID = id;
	}

	/**
	 * アイテムIDを取得します。
	 * 
	 * @return
	 */
	public String getItem_id() {
		return _itemID;
	}

	/**
	 * アイテムを取得します。
	 * 
	 * @return
	 */
	public Item getItem() {
		return _item;
	}

	@Autowired 
	private ItemService itemService;

	/**
	 * 追加画面を表示します。
	 * 
	 * @return
	 */
	public String show() {
		try {
		_item = itemService.queryItemByid(Integer.parseInt(_itemID));
	}catch(Exception ex) {
		ex.printStackTrace();
		return showError(ex.getMessage());
	}
		return "success";
	}

	/**
	 * 実行します。
	 * 
	 * @return
	 */
	public String execute() {
		try{
			itemService.deleteItem(Integer.parseInt(_itemID));
		}catch(Exception ex) {
			ex.printStackTrace();
			return showError(ex.getMessage());
		}
		return "success";
	}
}
