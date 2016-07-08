package jp.co.wisdom.item.aciton;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jp.co.wisdom.base.action.BaseAction;
import jp.co.wisdom.item.entity.UserItem;
import jp.co.wisdom.item.service.ItemService;

/**
 * ログイン処理。
 */
@Controller("LoginAction")
@Scope("prototype")
public class LoginAction extends BaseAction {

	/**
	 * シリアルバージョンUID。
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ユーザIDを保持します。
	 */
	private String userId;

	/**
	 * パスワードを保持します。
	 */
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 実行します。
	 */
	public String show() {
		return "success";
	}

	@Autowired
	private ItemService itemService;

	/**
	 * 実行します。
	 */
	public String execute() {
		UserItem user = queryUser();
		if (user == null) {
			return showError("ユーザIDもしくはパスワードが不正です。");
		}

		setCurrentUser(user);
		return "success";
	}

	/**
	 * ユーザを取得します。
	 * 
	 * @return
	 * @throws ServletException
	 */
	private UserItem queryUser() {
		// パラメータのチェック
		if (userId == null) {
			return null;
		}
		if (password == null) {
			return null;
		}
		if (isValidUserID(userId) == false) {
			return null;
		}
		if (isValidPassword(password) == false) {
			return null;
		}

		UserItem user = null;

		try {
			user = itemService.queryValidUser(userId, password);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}

	/**
	 * 有効なユーザIDかどうかを判定します。
	 * 
	 * @param name
	 * @return
	 */
	private boolean isValidUserID(String name) {
		return isAlphaOrDigit(name);
	}

	/**
	 * 有効なパスワードかどうかを判定します。
	 * 
	 * @param password
	 * @return
	 */
	private boolean isValidPassword(String password) {
		return isAlphaOrDigit(password);
	}

	/**
	 * 文字列が半角英数字から構成されているかどうかを判定します。
	 * 
	 * @param str
	 * @return
	 */
	private boolean isAlphaOrDigit(String str) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (isAlphaOrDigit(ch) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 文字が半角英数字かどうかを判定します。
	 * 
	 * @param ch
	 * @return
	 */
	private boolean isAlphaOrDigit(char ch) {
		if ('A' <= ch && ch <= 'Z') {
			return true;
		}
		if ('a' <= ch && ch <= 'z') {
			return true;
		}
		if ('0' <= ch && ch <= '9') {
			return true;
		}
		return false;
	}

}
