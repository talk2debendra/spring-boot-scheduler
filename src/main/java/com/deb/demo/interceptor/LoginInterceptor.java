package com.deb.demo.interceptor;

import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class LoginInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = Logger.getLogger(LoginInterceptor.class.getName());
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {	
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request,	HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate,max-stale=0,post-check=0,pre-check=0");
		response.setHeader("Pragma", "no-cache"); 
		response.setDateHeader("Expires", 0);		
		int sessionTimeoutValue = request.getSession().getMaxInactiveInterval();
		Cookie cookie = new Cookie("TimeoutCookie", "1");
		cookie.setMaxAge(sessionTimeoutValue);
		response.addCookie(cookie);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
