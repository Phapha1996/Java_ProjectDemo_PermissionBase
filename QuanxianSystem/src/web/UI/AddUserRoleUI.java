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
			//�ҳ���ǰ�û���ӵ�еĽ�ɫ
			List<Role> user_roles = ss.getUserRole(user_id);
			request.setAttribute("user_roles",user_roles);
			//�г�ϵͳӵ�еĽ�ɫ
			List<Role> systemRolesHavenot = ss.getallRole();
			//��ϵͳӵ�еĽ�ɫ���û��Ѿ��еĽ�ɫ�ų�
			systemRolesHavenot.removeAll(user_roles);
			request.setAttribute("hasNot", systemRolesHavenot);
			
			//���ظ���ʾ��
			request.getRequestDispatcher("/jsp/addUserRole.jsp").forward(request, response);
			return ;
		} catch (SQLException e) {
			request.setAttribute("message", "http:500---�Բ��𣬷���������δ֪����");
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
