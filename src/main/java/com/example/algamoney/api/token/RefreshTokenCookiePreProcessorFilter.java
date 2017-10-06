package com.example.algamoney.api.token;

import com.example.algamoney.api.config.AuthorizationServerConfig;
import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * Created by victor on 01/10/2017.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {

    public static final String URI_OAUTH_TOKEN = "/oauth/token";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (URI_OAUTH_TOKEN.equalsIgnoreCase(req.getRequestURI())
                && AuthorizationServerConfig.REFRESH_TOKEN.equals(req.getParameter("grant_type"))
                && req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if(cookie.getName().equals(AuthorizationServerConfig.REFRESH_TOKEN)){
                    String refreshToken = cookie.getValue();
                    req = new MyServletRequestWrapper(req,refreshToken);
                }
            }

        }

        chain.doFilter(req,response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    static class MyServletRequestWrapper extends HttpServletRequestWrapper{

        private final String refreshToken;

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request The request to wrap
         * @throws IllegalArgumentException if the request is null
         */
        public MyServletRequestWrapper(HttpServletRequest request,String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String,String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put(AuthorizationServerConfig.REFRESH_TOKEN,new String[]{refreshToken});
            return map;
        }
    }
}
