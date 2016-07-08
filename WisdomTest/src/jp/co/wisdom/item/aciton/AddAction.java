package jp.co.wisdom.item.aciton;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jp.co.wisdom.base.action.BaseAction;
import jp.co.wisdom.item.entity.Item;
import jp.co.wisdom.item.entity.UserItem;
import jp.co.wisdom.item.service.ItemService;

/**
 * 追加をおこなうアクションです。
 */
@Controller("AddAction")
@Scope("prototype")
public class AddAction extends BaseAction {

	/**
	 * シリアルバージョンUID。
	 */
	private static final long serialVersionUID = 1L;

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
	private String name;

	/**
	 * ユーザIDを保持します。
	 */
	private String userId;

	private String year;

	private String month;

	private String day;

	@Autowired
	private ItemService itemService;

	public AddAction() {
		_users = null;
		_calendar = Calendar.getInstance();
		_calendar.clear();
	}

	/**
	 * 追加画面を表示します。
	 * 
	 * @return
	 */
	public String show() {
		_users = itemService.queryUsers();
		_calendar = Calendar.getInstance();
		year =String.valueOf(_calendar.get(Calendar.YEAR));
		day =String.valueOf(_calendar.get(Calendar.DAY_OF_MONTH));
		month =String.valueOf(_calendar.get(Calendar.MONTH) +1);
		return "success";
	}

	/**
	 * 実行します。
	 * 
	 * @return
	 */
	public String execute() {
		Item targetItem = new Item();
		UserItem user = null;
		try {
			user = itemService.queryUser(userId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (user == null) {
			return showError("不正なパラメータです。");
		}
		try {
			targetItem.setUser(user.getId());
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
			targetItem.setExpire_date(expireDate);
			targetItem.setName(name);

			itemService.insertItem(targetItem);

		} catch (Exception ex) {
			ex.printStackTrace();
			return showError(ex.getMessage());
		}

		return "success";
	}

	/**
	 * ユーザ一覧を取得します。
	 * 
	 * @return
	 */
	public UserItem[] getUsers() {
		return _users;
	}

	public String getName() {
		return name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public String getDay() {
		return day;
	}

	public void setName(String name) {
		this.name = name;
	}

}
