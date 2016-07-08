package jp.co.wisdom.user.service;

import java.util.List;

import jp.co.wisdom.user.entity.User;


public interface UserService {
	 /**
	  *  取得所有用户
	  *  @@author Lingqing
	  *  @return 用户列表
	  */
	 public List<User> getUsers()throws Exception;
	 /**
	  *  取得用户信息
	  *  @@author Lingqing
	  *  @return User对象
	  *  @throws Exception
	  */
	 public User getUserInfo(User user)throws Exception;
	 /**
	  *  保存用户
	  *  @@author Lingqing
	  *  @param user
	  *  @return
	  *  @throws Exception
	  */
	 public void saveUser(User user)throws Exception;
	 /**
	  *  删除用户
	  *  @@author Lingqing
	  *  @param id
	  *  @return
	  */
	 public int deleteUser(Long id)throws Exception;
}
