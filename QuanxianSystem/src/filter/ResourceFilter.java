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
			//如果发现这个资源没有查找到，那么这个资源就不存在数据库，也就是不需要权限
			if(resource==null){		
				chain.doFilter(request, response);
				return;
			}
			
			List<Privilege> pris = ss.getResourceNeedPri(resource.getId());
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			//如果发现用户没有登录，那么就请先登录
			if(user==null){
				request.setAttribute("message", "您没有权限，请先<a href='"+request.getContextPath()+"/servlet/LoginServletUI'>登录</a>再来！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			
			
			List<Privilege> list= ss.getUserPrivileges(user);
			
			if(list.containsAll(pris)){
				String message = "欢迎您，尊敬的"+user.getUsername();
				request.setAttribute("message", message);
				chain.doFilter(request, response);
				return;
			}
			
			//如果代码顺利往下跑，那么该用户就没有权限进行访问。
			request.setAttribute("message","对不起"+user.getUsername()+"，您没有权限访问，请与管理员联系。");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}


}
