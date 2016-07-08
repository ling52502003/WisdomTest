package jp.co.wisdom.item.aciton;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jp.co.wisdom.base.action.BaseAction;
import jp.co.wisdom.item.entity.Item;
import jp.co.wisdom.item.entity.UserItem;
import jp.co.wisdom.item.service.ItemService;

/**
 * 一覧を表示するアクション。
 */
@Controller("ListAction")
@Scope("prototype")
public class ListAction extends BaseAction {

	/**
	 * シリアルバージョンUID。
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * アイテムを保持します。
	 */
	private Item[] _items;

	/**
	 * ソートキーを保持します。
	 */
	private String _orderBy;

	/**
	 * アイテムの一覧を取得します。
	 * 
	 * @return
	 */
	public Item[] getItems() {
		return _items;
	}

	/**
	 * ソートキーを保持します。
	 * 
	 * @param orderBy
	 */
	public void setOrder_by(String orderBy) {
		_orderBy = orderBy;
	}

	/**
	 * ソートキーを取得します。
	 * 
	 * @return
	 */
	public String getOrder_by() {
		return _orderBy;
	}

	/**
	 * 現在時刻を取得します。
	 * 
	 * @return
	 */
	public long getCurrentTime() {
		// 現在時刻を取得(期限比較用: 分以降は0にリセット)
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long currentTime = calendar.getTimeInMillis();
		return currentTime;
	}

	@Override
	public UserItem getCurrentUser() {
		return super.getCurrentUser();
	}

	@Autowired
	private ItemService itemService;

	/**
	 * 実行します。
	 */
	public String show() {
		try {
			List<Item> items = itemService.queryItems(null, _orderBy);
			_items = (Item[]) items.toArray(new Item[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "success";
	}

}
