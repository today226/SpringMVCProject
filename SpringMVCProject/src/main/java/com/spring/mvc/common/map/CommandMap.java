package com.spring.mvc.common.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//���⼭ ������ HandlerMethodArgumentResolver�� ��Ʈ�ѷ��� �Ķ���Ͱ� Map �����̸� �������� �ʴ´�
//������ 3.1���� HandlerMethodArgumentResolver�� �̿��Ͽ� �׷��� ����� ������� ��Ʈ�ѷ��� �Ķ���Ͱ� Map �����̸� �츮�� ������ Ŭ������ �ƴ�
//���������� �⺻������ ������ ArgumentResolver�� ��ġ�� �ȴ�
//�������� <mvc:annotation-driven/> �����ϰ� �Ǹ� ������ �̾߱��Ѱ�ó�� �����ϰ� �ȴ�, �������� ������ Map�� ����ص� ���� ����

//Request�� ����ִ� �Ķ���͸� Map�� ����ִ� ��Ȱ�� �ϴ� Ŭ������
//Map�� ��� �ް� �Ǹ� �ۼ��� ArgumentResolver�� ��ġ�� �ʰ� �ȴ�
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
	
	//CommandMap�� Map�� �Ȱ��� ��� �� �� �ֵ��� getMap �޼��带 �߰�
	public Map<String, Object> getMap(){
		return map;
	}
}
