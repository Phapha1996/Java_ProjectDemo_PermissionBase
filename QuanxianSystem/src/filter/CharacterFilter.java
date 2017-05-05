package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterFilter implements Filter {

	private String charset;
	private static final String DEFAULTCHARSET = "UTF-8";
	public CharacterFilter() {
		super();
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if(charset==null){
			request.setCharacterEncoding(DEFAULTCHARSET);
			response.setCharacterEncoding(DEFAULTCHARSET);
			response.setContentType("text/html;charset='"+DEFAULTCHARSET+"'");
		}else{
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset='"+charset+"'");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

		charset = config.getInitParameter("charset");
	}

}
