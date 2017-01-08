package cn.com.bookAdmin.user.service;

import cn.com.bookAdmin.user.model.User;

public class UserService {
	
	public User login(User form) throws UserException{
		
		if(form.getName() == null || form.getName() == "") throw new UserException("用户名不能为空！");
		if(form.getPassword() == null || form.getPassword() == "") throw new UserException("密码不能为空");
		if(!form.getName().equals("lixuan")) throw new UserException("用户名错误");
		if(!form.getPassword().equals("lixuan")) throw new UserException("密码错误");
		return form;
		
	}

}
