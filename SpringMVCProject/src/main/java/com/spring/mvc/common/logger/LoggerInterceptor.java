package com.spring.mvc.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터는 HandlerInterceptorAdapter 플래스를 상속 받아서 만든다
public class LoggerInterceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);
	
	/*
	인터셉터의 정확한 명칭은 핸들러 인터셉터 (Handler Interceptor)이다. 인터셉터는 DispatcherServlet이 컨트롤러를 호출하기 전,후에 요청과 응답을 가로채서 가공할 수 있도록 해준다
	전처리기와 후처리기의 메서드를 등록한다.
	*/
	
	//preHandler()은 컨트롤러가 호출되기 전에 실행(전처리기)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		if(log.isDebugEnabled()){
			log.debug("============================= START =============================");
			log.debug(" Request URI \t: " + request.getRequestURI());
		}
		return super.preHandle(request, response, handler);
	}
	
	//postHandle()은 컨트롤러가 실행되고 난 후에 호출(후처리기)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object Handler, ModelAndView modelAndView) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("============================= END =============================\n");
        }
    }
}
