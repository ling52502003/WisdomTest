package jp.co.wisdom.base.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import jp.co.wisdom.item.entity.Item;
import jp.co.wisdom.item.entity.UserItem;

/**
 * Class Name: BaseAction.java
 *
 * @author
 * @version 1.0
 */
public class BaseAction extends ActionSupport implements SessionAware{


	private static final long serialVersionUID = -2983580695806850367L;

	/**
	 *
	 */
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/**
	 *
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 *
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 *
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 取得访问域名
	 *
	 * @return
	 */
	public String getURL() {
		return getRequest().getServerName();
	}

	/**
	 * 存放Cookie
	 *
	 * @param name
	 * @param value
	 */
	public void addCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(60 * 60 * 24);
		cookie.setPath("/");
		getResponse().addCookie(cookie);
	}

	/**
	 * 删除Cookie
	 *
	 * @param name
	 * @param value
	 */
	public void removeCookie(String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		getResponse().addCookie(cookie);
	}

	/**
	 * Cookie取得
	 *
	 * @return
	 * @throws Exception
	 */
	public String getCookie(String name) {
		try {
			Cookie[] cookies = getRequest().getCookies();
			for (Cookie cookie : cookies) {
				cookie.setPath("/");
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 获取IP地址
	 */
	public String getIpAddress() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 現在のユーザを保持するセッション変数を示します。
	 */
	protected static final String SESSION_CURRENT_USER = "currentUser";

	/**
	 * エラーメッセージを保持します。
	 */
	private String _errorMessage;

	/**
	 * セッション情報を保持します。
	 */
	private Map<String, Object> _session;

	/**
	 * 構築します。
	 */
	public BaseAction() {
		_errorMessage = null;
		_session = null;
	}

	/**
	 * エラーメッセージを取得します。
	 *
	 * @return
	 */
	public String getErrorMessage() {
		return _errorMessage;
	}

	/**
	 * セッション情報を保存します。
	 */
	public void setSession(Map<String, Object> session) {
		_session = session;
	}

	/**
	 * 現在のユーザを取得します。
	 *
	 * @return
	 */
	public UserItem getCurrentUser() {
		return (UserItem) _session.get(SESSION_CURRENT_USER);
	}

	/**
	 * 現在のユーザを設定します。
	 *
	 * @param user
	 */
	protected void setCurrentUser(UserItem user) {
		_session.put(SESSION_CURRENT_USER, user);
	}

	/**
	 * エラーを表示します。
	 *
	 * @param errorMessage
	 * @return
	 */
	protected String showError(String errorMessage) {
		_errorMessage = errorMessage;

		return "error";
	}

	/**
	 * アイテムを取得します。
	 *
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	protected Item queryItem(String id) {
		List items = new ArrayList();
		if (items.size() == 0) {
			return null;
		}
		return (Item) items.get(0);
	}

}
