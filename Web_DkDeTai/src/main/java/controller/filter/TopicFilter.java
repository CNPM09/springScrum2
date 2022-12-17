package controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegistrationPeriod;
import service.RegistrationPriodService;

@WebFilter("/lecturer/manage-topic/add")
public class TopicFilter extends HttpFilter implements Filter {
       
    
	
	public TopicFilter() {
        super();
    }

	public void destroy() {
	
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// cast object type 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		RegistrationPeriod period = null;
		RegistrationPriodService rps = new RegistrationPriodService(req, res);
		period = rps.getRegistrationPeriod(Byte.parseByte("1"));
		
		if (period != null) {
			System.out.println("exist registraition period");
			HttpSession session = req.getSession();
			session.setAttribute("period", period);
			chain.doFilter(request, response);
		}else {
			// forward to login page
			
			String url = "/lecturer/manage-topic";
			req.setAttribute("notExistPeriod", "1");
			req.getRequestDispatcher(url).forward(req, res);
		}
				
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
}