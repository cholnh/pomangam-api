package com.mrporter.pomangam._bases.securities.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class CORSFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Autowired
    private AtomicReference<Health> health;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        //HttpServletResponse response = ServletReqNResUtils.asHttpServletResponse(servletResponse);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;



        if(health.get().getStatus().toString().equals("UP")) {
            // credential 사용 시 : Origin을 vue-web서버 url(포트포함)으로 설정 후, Credentials 주석 풀기, vue서버 withCredentials 주석풀기

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
            //response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, Authorization");
            //response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials");

            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            String path = ((HttpServletRequest) servletRequest).getServletPath().substring(0, 7);
            if(path.equals("/health")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}


