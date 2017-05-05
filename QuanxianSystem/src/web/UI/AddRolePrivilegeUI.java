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
			//������õ�ǰ��ɫ
			Role role = ss.findRoleForID(role_id);
			request.setAttribute("role", role);
			//�г���ǰ��ɫ��ӵ�е�Ȩ��
			List<Privilege> role_priList = ss.getRolePrivilege(role_id);
			request.setAttribute("role_priList", role_priList);
			//�г����е�Ȩ��
			List<Privilege> priList = ss.getAllPrivilege();
			
			//�ų����Ѿ�ӵ�й���Ȩ��
			/*List<Privilege> changePriList = new LinkedList();//�ų��ļ���
			for(int i=0;i<priList.size();i++){
				for(Privilege p:role_priList){
					if((priList.get(i).getId()).equals(p.getId())){
						changePriList.add(priList.get(i));
					}
				}
			}*/
				boolean a = priList.removeAll(role_priList);//���Ѿ����ֹ���Ȩ���ų�����δ��Ȩ��Ȩ�ޱ���
			
				request.setAttribute("priList",priList);
			
			request.getRequestDispatcher("/jsp/addRolePrivilege.jsp").forward(request, response);
			return;
		} catch (SQLException e) {
			String message = "http��500�Բ��𣬷���������δ֪��������������...";
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
