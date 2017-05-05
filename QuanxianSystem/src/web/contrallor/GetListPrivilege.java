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

public class GetListPrivilege extends HttpServlet {

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
			
		String message = "";
		try{
			SercurityService ss = new SercurityService();
			List<Privilege> list = ss.getAllPrivilege();
			if(list.size()!=0){
				request.setAttribute("Privileges", list);
				request.getRequestDispatcher("/jsp/privileges.jsp").forward(request, response);
			}else{
				message = "对不起，权限还未添加！！<a href='"+request.getContextPath()+"/servlet/AddPrivilegeUI'>点击此处去添加权限</a>";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
			
		}catch(SQLException e){
			message = "404查询失败！服务器出现未知错误！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

	
	public void init() throws ServletException {
		
	}

}
