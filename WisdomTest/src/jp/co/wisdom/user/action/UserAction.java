package jp.co.wisdom.user.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jp.co.wisdom.base.action.BaseAction;
import jp.co.wisdom.user.entity.User;
import jp.co.wisdom.user.service.UserService;

/**
 *@author ling
 */
@Controller("UserAction")
@Scope("prototype")
public class UserAction extends BaseAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6748220203102435515L;
	
	@Autowired
	private UserService userService;
	/**
	 * 用户列表对象
	 */
	private List<User> userList;
	/**
	 *  用户
	 */
	private User userInfo;
	
    /**
     *  取得用户列表
     *  @author ling
     *  @return
     *  @throws Exception
     */
	public String getUser() throws Exception {
		 
		userList = userService.getUsers();
		return SUCCESS;
	}
	/**
	 *  保存用户
	 *  @author ling
	 *  @return
	 *  @throws Exception
	 */
	public String saveUser() throws Exception { 
		userService.saveUser(userInfo);
		 System.out.println("取得ID="+userInfo.getId());
		return SUCCESS;
	}
	
	/**
	 *  Function:修改用户
	 *  @author ling
	 *  @return
	 *  @throws Exception
	 */
	public String editUser()throws Exception { 
		userInfo = userService.getUserInfo(userInfo);
		return SUCCESS;
	}
	
	/**
	 *  删除用户
	 *  @author ling
	 *  @return
	 *  @throws Exception
	 */
	public String deleteUser()throws Exception { 
		if(userInfo!=null&&userInfo.getId()!=null){
			userService.deleteUser(userInfo.getId());
		}
		return SUCCESS;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}

 
}
