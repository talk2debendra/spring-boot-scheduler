package com.deb.demo.interceptor;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class SessionVariables {


	
	public static String getUserName(){
		Object sessionValue = getSession().getAttribute("userName");
		return sessionValue != null ? (String)sessionValue : null;
	}

	public static void setUserName(String userName) {
		getSession().setAttribute("userName", userName);
	}

	@SuppressWarnings("unused")
	public static void setSessionToNull(){
		HttpSession httpSession = getSession();
		httpSession = null;

	}

	public static HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		return session;
	}

}



