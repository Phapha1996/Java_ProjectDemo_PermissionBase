package web.UI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Privilege;
import domain.Role;
import service.impl.SercurityService;

public class AddRolePrivilegeUI extends HttpServlet{

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String role_id = request.getParameter("id");
			SercurityService ss = new SercurityService();
			//搜索获得当前角色
			Role role = ss.findRoleForID(role_id);
			request.setAttribute("role", role);
			//列出当前角色所拥有的权限
			List<Privilege> role_priList = ss.getRolePrivilege(role_id);
			request.setAttribute("role_priList", role_priList);
			//列出所有的权限
			List<Privilege> priList = ss.getAllPrivilege();
			
			//排除掉已经拥有过的权限
			/*List<Privilege> changePriList = new LinkedList();//排除的集合
			for(int i=0;i<priList.size();i++){
				for(Privilege p:role_priList){
					if((priList.get(i).getId()).equals(p.getId())){
						changePriList.add(priList.get(i));
					}
				}
			}*/
				boolean a = priList.removeAll(role_priList);//将已经出现过的权限排除，将未授权的权限保留
			
				request.setAttribute("priList",priList);
			
			request.getRequestDispatcher("/jsp/addRolePrivilege.jsp").forward(request, response);
			return;
		} catch (SQLException e) {
			String message = "http：500对不起，服务器出现未知错误，正在抢修中...";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

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
