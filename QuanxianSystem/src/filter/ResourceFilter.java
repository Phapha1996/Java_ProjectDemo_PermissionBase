package filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Privilege;
import domain.Resource;
import domain.User;
import service.impl.SercurityService;

public class ResourceFilter implements Filter {

	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		try{
			String uri = request.getRequestURI();
			SercurityService ss = new SercurityService();
			Resource resource = ss.findSourceForURI(uri);
			//������������Դû�в��ҵ�����ô�����Դ�Ͳ��������ݿ⣬Ҳ���ǲ���ҪȨ��
			if(resource==null){		
				chain.doFilter(request, response);
				return;
			}
			
			List<Privilege> pris = ss.getResourceNeedPri(resource.getId());
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			//��������û�û�е�¼����ô�����ȵ�¼
			if(user==null){
				request.setAttribute("message", "��û��Ȩ�ޣ�����<a href='"+request.getContextPath()+"/servlet/LoginServletUI'>��¼</a>������");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			
			
			List<Privilege> list= ss.getUserPrivileges(user);
			
			if(list.containsAll(pris)){
				String message = "��ӭ�����𾴵�"+user.getUsername();
				request.setAttribute("message", message);
				chain.doFilter(request, response);
				return;
			}
			
			//�������˳�������ܣ���ô���û���û��Ȩ�޽��з��ʡ�
			request.setAttribute("message","�Բ���"+user.getUsername()+"����û��Ȩ�޷��ʣ��������Ա��ϵ��");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}


}
