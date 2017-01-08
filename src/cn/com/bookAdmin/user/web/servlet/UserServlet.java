package cn.com.bookAdmin.user.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.bookAdmin.user.model.User;
import cn.com.bookAdmin.user.service.UserException;
import cn.com.bookAdmin.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet{
	
	private UserService userService = new UserService();
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("session_user", user);
			return "r:/adminjsps/admin/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/adminjsps/login.jsp";
		}
	}

}
