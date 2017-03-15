package com.spring.mvc.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//���ͼ��ʹ� HandlerInterceptorAdapter �÷����� ��� �޾Ƽ� �����
public class LoggerInterceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);
	
	/*
	���ͼ����� ��Ȯ�� ��Ī�� �ڵ鷯 ���ͼ��� (Handler Interceptor)�̴�. ���ͼ��ʹ� DispatcherServlet�� ��Ʈ�ѷ��� ȣ���ϱ� ��,�Ŀ� ��û�� ������ ����ä�� ������ �� �ֵ��� ���ش�
	��ó����� ��ó������ �޼��带 ����Ѵ�.
	*/
	
	//preHandler()�� ��Ʈ�ѷ��� ȣ��Ǳ� ���� ����(��ó����)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		if(log.isDebugEnabled()){
			log.debug("============================= START =============================");
			log.debug(" Request URI \t: " + request.getRequestURI());
		}
		return super.preHandle(request, response, handler);
	}
	
	//postHandle()�� ��Ʈ�ѷ��� ����ǰ� �� �Ŀ� ȣ��(��ó����)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object Handler, ModelAndView modelAndView) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("============================= END =============================\n");
        }
    }
}
