package web.contrallor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.SercurityService;
import domain.Role;
import domain.User;

public class GetListUserole extends HttpServlet {

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
		String message = "目前还没有任何数据！<a href='"+request.getContextPath()+"/servlet/AddUserUI'>点击此处去添加用户</a>";
		try {
			SercurityService ss = new SercurityService();
			List<User> list = ss.getAllUser();
			if (list.size() == 0) {
				request.setAttribute("message", message);
				request.getRequestDispatcher("/message.jsp").forward(request,response);
				return;
			}
			request.setAttribute("users", list);
			request.getRequestDispatcher("/jsp/showUsersList.jsp").forward(request, response);
			return;
		
		} catch (SQLException e) {
			message = "500服务器出错！正在维修当中！";
			request.setAttribute("message", message);
			e.printStackTrace();
			request.getRequestDispatcher("/message.jsp").forward(request,response);
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
