package com.filter;

import javax.servlet.*;
import java.io.IOException;

public class TempFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException{
        System.out.println(" --filter init() --");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(" -- filter doFilter() --");

        //request filter
        request.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println(" -- filter destroy() --");
    }

}
