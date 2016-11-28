package es.albarregas.filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class UTF8Filter
 */
@WebFilter(filterName = "UTF8Filter", urlPatterns = {"/*"})
public class Filtro implements Filter {

    private String encoding;

    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
        encoding = fConfig.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

}