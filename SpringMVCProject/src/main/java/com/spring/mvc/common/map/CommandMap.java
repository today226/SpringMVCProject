package com.spring.mvc.common.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//여기서 문제는 HandlerMethodArgumentResolver는 컨트롤러의 파라미터가 Map 형식이면 동작하지 않는다
//스프링 3.1에서 HandlerMethodArgumentResolver를 이용하여 그러한 기능을 만들더라도 컨트롤러의 파라미터가 Map 형식이면 우리가 설정한 클래스가 아닌
//스프링에서 기본적으로 설정된 ArgumentResolver를 거치게 된다
//스프링의 <mvc:annotation-driven/> 선언하게 되면 위에서 이야기한것처럼 동작하게 된다, 선언하지 않으면 Map을 사용해도 문제 없다

//Request에 담겨있는 파라미터를 Map에 담아주는 역활을 하는 클래스다
//Map을 상속 받게 되면 작성할 ArgumentResolver를 거치지 않게 된다
public class CommandMap {
	Map<String, Object> map = new HashMap<String, Object>();
	
	public Object get(String key){
		return map.get(key);
	}
	
	public void put(String key, Object value){
		map.put(key, value);
	}
	
	public Object remove(String key){
		return map.remove(key);
	}
	
	public boolean containsKey(String key){
		return map.containsKey(key);
	}
	
	public boolean containsValue(Object value){
		return map.containsValue(value);
	}
	
	public void clear(){
		map.clear();
	}
	
	public Set<Entry<String, Object>> entrySet(){
		return map.entrySet();
	}
	
	public  Set<String> keySet(){
		return map.keySet();
	}
	
	public boolean isEmpty(){
		return map.isEmpty();
	}
	
	public void putAll(Map<? extends String, ?extends Object> m){
		map.putAll(m);
	}
	
	//CommandMap을 Map과 똑같이 사용 할 수 있도록 getMap 메서드를 추가
	public Map<String, Object> getMap(){
		return map;
	}
}
