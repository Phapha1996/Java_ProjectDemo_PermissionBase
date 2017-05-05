package web.UI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.SercurityService;
import domain.Privilege;
import domain.Resource;
import domain.Role;

public class NeedPrivilegeUI extends HttpServlet {

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
			String resource_id = request.getParameter("resource_id");
			SercurityService ss = new SercurityService();
			//搜索获得当前资源对象
			Resource resource = ss.findResourceForID(resource_id);
			request.setAttribute("resource", resource);
			//列出当前资源所需要的权限
			List<Privilege> res_priList = ss.getResourceNeedPri(resource_id);
			request.setAttribute("res_priList", res_priList);
			//列出所有的权限
			List<Privilege> priList = ss.getAllPrivilege();
			
			//排除掉已经需要过的权限
			/*List<Privilege> changePriList = new LinkedList();//排除的集合
			for(int i=0;i<priList.size();i++){
				for(Privilege p:role_priList){
					if((priList.get(i).getId()).equals(p.getId())){
						changePriList.add(priList.get(i));
					}
				}
			}*/
				boolean a = priList.removeAll(res_priList);//将已经需要的权限排除，将未需要的权限
			
				request.setAttribute("priList",priList);
			
			request.getRequestDispatcher("/jsp/addResourcePrivilege.jsp").forward(request, response);
			return;
		} catch (SQLException e) {
			String message = "http：500对不起，服务器出现未知错误，正在抢修中...";
			request.setAttribute("message", message);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
