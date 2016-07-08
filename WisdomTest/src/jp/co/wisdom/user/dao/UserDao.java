package jp.co.wisdom.user.dao;

import java.util.List;

import jp.co.wisdom.user.entity.User;


public interface UserDao {
	 /**
	  *  取得所有用户
	  *  @@author Lingqing
	  *  @return 用户列表
	  */
	 public List<User> getUser(); 
	 /**
	  *  根据用户账号信息取得用户
	  *  @@author Lingqing
	  *  @return
	  */
	 public User getUser(User user);
	 
	 /**
	  *  添加用户
	  *  @@author Lingqing
	  *  @param user
	  *  @return
	  */
	 public void insertUser(User user);
	 /**
	  *  更新用户信息
	  *  @@author Lingqing
	  *  @param user
	  *  @return
	  */
	 public int updateUser(User user);
	 /**
	  *  删除用户
	  *  @@author Lingqing
	  *  @param id
	  *  @return
	  */
	 public int deleteUser(Long id);
}
