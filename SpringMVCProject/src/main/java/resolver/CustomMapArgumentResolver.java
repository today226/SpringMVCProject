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
	
	//resolverArgument 메서드는 파라미터와 기타 정보를 받아서 실제 객체를 반환.
	//이를 위해서 추후 Controller의 Map<String,Object> 형식을 CommandMap이라고 변경 할 것이다
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		CommandMap commandMap = new CommandMap();
		
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		Enumeration<?> enumeration = request.getParameterNames();
		
		String key = null;
		String[] values = null;
		
		//request에 있는 값을 iterator를 이용하여 하나씩 가져오는 로직이다
		while(enumeration.hasMoreElements()){
			key = (String) enumeration.nextElement();
			values = request.getParameterValues(key);
			if(values != null){
				//request에 담겨있는 모든 키와 값을 commandMap에 저장
				commandMap.put(key, (values.length > 1) ? values:values[0]); 
			}
		}
		return commandMap;
	}

	//supportsParameter 메서드는 Resolver가 적용 가능한지 검사하는 역할
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return CommandMap.class.isAssignableFrom(parameter.getParameterType());
	}

}
