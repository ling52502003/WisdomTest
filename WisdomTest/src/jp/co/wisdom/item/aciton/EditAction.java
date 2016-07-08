package jp.co.wisdom.item.aciton;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jp.co.wisdom.base.action.BaseAction;
import jp.co.wisdom.base.action.ModifyException;
import jp.co.wisdom.item.entity.Item;
import jp.co.wisdom.item.entity.UserItem;
import jp.co.wisdom.item.service.ItemService;

/**
 * 編集をおこなうアクション。
 */
@Controller("EditAction")
@Scope("prototype")
public class EditAction extends BaseAction {

	/**
	 * シリアルバージョンUID。
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * アイテムIDを保持します。
	 */
	private String _itemID;

	/**
	 * ユーザを保持します。
	 */
	private UserItem[] _users;

	/**
	 * カレンダーを保持します。
	 */
	private Calendar _calendar;

	/**
	 * 作業項目名を保持します。
	 */
	private String _name;

	/**
	 * ユーザIDを保持します。
	 */
	private String _userID;

	/**
	 * 完了したかどうかを保持します。
	 */
	private boolean _finished;

	/**
	 * アイテムのIDを保持します。
	 * 
	 * @param id
	 */
	public void setItem_id(String id) {
		_itemID = id;
	}

	/**
	 * アイテムのIDを取得思案す。
	 * 
	 * @return
	 */
	public String getItem_id() {
		return _itemID;
	}

	private String year;

	private String month;

	private String day;

	@Autowired
	private ItemService itemService;

	/**
	 * 追加画面を表示します。
	 * 
	 * @return
	 */
	public String show() {

		try {
			// Item currentItem = _repository.queryItem(_itemID);
			Item currentItem = itemService.queryItemByid(Integer.parseInt(_itemID));
			_users = itemService.queryUsers();
			_calendar = Calendar.getInstance();
			_calendar.setTime(currentItem.getExpire_date());
			year = String.valueOf(_calendar.get(Calendar.YEAR));
			day = String.valueOf(_calendar.get(Calendar.DAY_OF_MONTH));
			month = String.valueOf(_calendar.get(Calendar.MONTH) +1);
			_name = currentItem.getName();
			// _userID = currentItem.getUser().getId();
			_finished = (currentItem.getFinished_date() != null);
		} catch (Exception ex) {
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
		try {
			Item item = new Item();
			modifyItem(item);
			return "success";
		} catch (ModifyException e) {
			return showError(e.getMessage());
		}
	}

	/**
	 * ユーザIDを設定します。
	 * 
	 * @param id
	 */
	public void setUser_id(String id) {
		_userID = id;
	}

	/**
	 * ユーザIDを取得します。
	 * 
	 * @return
	 */
	public String getUser_id() {
		return _userID;
	}

	/**
	 * 名前を保持します。
	 * 
	 * @param name
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * 名前を取得します。
	 * 
	 * @return
	 */
	public String getName() {
		return _name;
	}

	/**
	 * 完了したかどうかを設定します。
	 * 
	 * @param finished
	 */
	public void setFinished(String finished) {
		if ("true".equals(finished)) {
			_finished = true;
		} else {
			_finished = false;
		}
	}

	/**
	 * 完了したかどうかを取得します。
	 * 
	 * @return
	 */
	public String getFinished() {
		if (_finished) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * ユーザ一覧を取得します。
	 * 
	 * @return
	 */
	public UserItem[] getUsers() {
		return _users;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * アイテムを更新します。
	 */
	public void modifyItem(Item item) throws ModifyException {
		UserItem user = null;
		try {
			user = itemService.queryUser(_userID);

			if (user == null) {
				throw new ModifyException("不正なパラメータです。");
			}
			item.setUser(user.getId());
			_calendar = Calendar.getInstance();
			if (year != null) {
				_calendar.set(Calendar.YEAR, Integer.parseInt(year));
			}
			if (month != null) {
				_calendar.set(Calendar.MONTH, Integer.parseInt(month) -1);
			}
			if (day != null) {
				_calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
			} 
			Date expireDate = new Date(_calendar.getTimeInMillis());
			item.setExpire_date(expireDate);
			item.setName(_name);
			item.setId(_itemID);
			if (_finished) {
				if (item.getFinished_date() == null) {
					Calendar calendar = Calendar.getInstance();
					item.setFinished_date(new Date(calendar.getTimeInMillis()));
				}
			} else {
				item.setFinished_date(null);
			}

			itemService.updateItem(item);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
