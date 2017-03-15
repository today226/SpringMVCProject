package resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.spring.mvc.common.map.CommandMap;



public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver {
	
	//resolverArgument �޼���� �Ķ���Ϳ� ��Ÿ ������ �޾Ƽ� ���� ��ü�� ��ȯ.
	//�̸� ���ؼ� ���� Controller�� Map<String,Object> ������ CommandMap�̶�� ���� �� ���̴�
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		CommandMap commandMap = new CommandMap();
		
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		Enumeration<?> enumeration = request.getParameterNames();
		
		String key = null;
		String[] values = null;
		
		//request�� �ִ� ���� iterator�� �̿��Ͽ� �ϳ��� �������� �����̴�
		while(enumeration.hasMoreElements()){
			key = (String) enumeration.nextElement();
			values = request.getParameterValues(key);
			if(values != null){
				//request�� ����ִ� ��� Ű�� ���� commandMap�� ����
				commandMap.put(key, (values.length > 1) ? values:values[0]); 
			}
		}
		return commandMap;
	}

	//supportsParameter �޼���� Resolver�� ���� �������� �˻��ϴ� ����
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return CommandMap.class.isAssignableFrom(parameter.getParameterType());
	}

}
