package web.UI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Role;
import domain.User;
import service.impl.SercurityService;

public class AddUserRoleUI extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String user_id = request.getParameter("id");
			SercurityService ss = new SercurityService();
			User user = ss.findUserForID(user_id);
			request.setAttribute("user",user);
			//找出当前用户所拥有的角色
			List<Role> user_roles = ss.getUserRole(user_id);
			request.setAttribute("user_roles",user_roles);
			//列出系统拥有的角色
			List<Role> systemRolesHavenot = ss.getallRole();
			//将系统拥有的角色中用户已经有的角色排除
			systemRolesHavenot.removeAll(user_roles);
			request.setAttribute("hasNot", systemRolesHavenot);
			
			//返回给显示层
			request.getRequestDispatcher("/jsp/addUserRole.jsp").forward(request, response);
			return ;
		} catch (SQLException e) {
			request.setAttribute("message", "http:500---对不起，服务器出现未知错误");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			e.printStackTrace();
			}
			
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
