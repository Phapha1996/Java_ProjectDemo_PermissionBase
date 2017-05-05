package web.contrallor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Privilege;
import service.impl.SercurityService;

public class RemoveRolePrivilege extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String privilege_id = request.getParameter("privilege_id");
			String role_id = request.getParameter("role_id");
			SercurityService ss = new SercurityService();

			ss.removeRolePrivilege(privilege_id,role_id);
			//得到当前角色拥有的所有权限
			List<Privilege> list = ss.getRolePrivilege(role_id);
			//得到系统拥有的所有权限
			List<Privilege> priList = ss.getAllPrivilege();
			//得到当前角色还未拥有的权限
			priList.removeAll(list);
			
			
			request.setAttribute("role",ss.findRoleForID(role_id));
			request.setAttribute("role_priList",ss.getRolePrivilege(role_id));
			request.setAttribute("priList", priList);
			
			
			request.getRequestDispatcher("/jsp/addRolePrivilege.jsp").forward(request, response);
		
		} catch (SQLException e) {
			request.setAttribute("message", "服务器出现未知错误");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
