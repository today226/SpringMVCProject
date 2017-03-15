package com.spring.mvc.common.util;

import java.util.List;
import java.util.Map;

import com.spring.mvc.common.map.CommandMap;

//String / List / Map / [] 형태의 Object들의 null 그리고 빈값을 체크
public class ObjectUtils {
	public static boolean isEmpty(Object s){
		if(s == null){
			return true;
		}
		
		if((s instanceof String) && (((String)s).trim().length() == 0)){
			return true;
		}
		
		if(s instanceof Map){
			return ((Map<?, ?>)s).isEmpty();
		}
		
		if(s instanceof CommandMap){
			return ((CommandMap)s).isEmpty();
		}
		
		if (s instanceof List) { 
			return ((List<?>)s).isEmpty(); 
		} 
		if (s instanceof Object[]) { 
			return (((Object[])s).length == 0); 
		} return false;
	}
}
