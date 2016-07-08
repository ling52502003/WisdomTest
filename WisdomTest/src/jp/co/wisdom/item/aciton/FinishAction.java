package jp.co.wisdom.item.aciton;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jp.co.wisdom.base.action.BaseAction;
import jp.co.wisdom.base.action.ModifyException;
import jp.co.wisdom.item.entity.Item;
import jp.co.wisdom.item.service.ItemService;

/**
 * 完了/未完了切替をおこなうアクション。
 */
@Controller("FinishAction")
@Scope("prototype")
public class FinishAction extends BaseAction{

	/**
	 * シリアルバージョンUID。
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * アイテムIDを保持します。
	 */
	private String _itemID;

	/**
	 * キーワードを保持します。
	 */
	private String _keyword;

	/**
	 * アイテムのIDを設定します。
	 * 
	 * @param id
	 * @return
	 */
	public void setItem_id(String id) {
		_itemID = id;
	}

	/**
	 * キーワードを設定します。
	 * 
	 * @param keyword
	 */
	public void setKeyword(String keyword) {
		_keyword = keyword;
	}

	/**
	 * キーワードを取得します。
	 * 
	 * @return
	 */
	public String getKeyword() {
		return _keyword;
	}
	
	@Autowired
	private ItemService itemService;
	/**
	 * 実行します。
	 * 
	 * @return
	 */
	public String execute() {
		try {
			Item item = itemService.queryItemByid(Integer.parseInt(_itemID));
			modifyItem(item);

			if (_keyword != null) {
				return "search";
			} else {
				return "list";
			}
		} catch(ModifyException e) {
			return showError(e.getMessage());
		}
	}

	/**
	 * アイテムを更新します。
	 */
	public void modifyItem(Item item) throws ModifyException {
		if (item.getFinished_date() == null) {
			// 完了に変更
			Calendar calendar = Calendar.getInstance();
			item
					.setFinished_date(new Date(calendar.getTimeInMillis()));
		} else {
			// 未完了に変更
			item.setFinished_date(null);
		}
		itemService.updateItem(item);
	}
}
