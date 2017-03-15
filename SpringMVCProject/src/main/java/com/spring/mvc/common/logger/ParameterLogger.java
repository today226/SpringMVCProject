package com.spring.mvc.common.logger;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.spring.mvc.common.map.CommandMap;

public class ParameterLogger {
	protected Log log = LogFactory.getLog(ParameterLogger.class);
	public void outputParameters(CommandMap commandMap){
		
		if(commandMap.isEmpty() == false){
			Iterator<Entry<String, Object>> iterator = commandMap.getMap().entrySet().iterator();
			Entry<String, Object> entry = null;
			while(iterator.hasNext()){
				entry = iterator.next();
				log.debug("key : " + entry.getKey() + ", value : " + entry.getValue());
			}
		}
	}
}
