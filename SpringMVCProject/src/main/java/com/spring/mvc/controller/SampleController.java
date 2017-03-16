package com.spring.mvc.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.common.logger.ParameterLogger;
import com.spring.mvc.common.map.CommandMap;
import com.spring.mvc.service.SampleService;

/*	1.HandlerMethodArgumentResolver ��?
	HandlerMethodArgumentResolver�� ������ 3.1���� �߰��� �������̽���. ������ 3.1 �������� WebArgumentResolver ��� �������̽����µ�, 
	������ 3.1 ���ĺ��� HandlerMethodArgumentResolver ��� �̸����� �ٲ����
	
	�̰��� �ϴ� ������ ������ ����. 
	������ ��� ��, ��Ʈ�ѷ�(Controller)�� ������ �Ķ����(Parameter)�� �����ϰų� ���������� �߰��� ���־�� �ϴ� ��찡 �ִ�
	���� ���, �α����� �� ������� ����� ���̵� �г��ӵ��� �߰��ϴ°��� �����غ���
	���� �׷� ������ ����(Session)�� ��Ƴ��� ����ϴµ�, DB�� �׷��� ������ �Է��� ������ �ᱹ ���ǿ��� ���� �����ͼ� �Ķ���ͷ� �߰��� �ؾ��Ѵ�
	�׷� ��찡 �� �ϳ��� �ι� ���� �ִٸ� ����, ������ ���Ǵ� ���� �׷��� ������ ���ǿ��� �������°� ����� ���ŷο� ���̴�
	HandlerMethodArgumentResolver �� ����� ��û�� Controller�� �����ϱ� ���� �� ��û�� �Ķ���͵��� ������ �� �ֵ��� ���ش�
 */
@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	@RequestMapping(value="/sample/openBoardList.com")
    public ModelAndView openBoardList(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/sample/boardList");
    	
    	List<Map<String,Object>> list = sampleService.selectBoardList(commandMap.getMap());
    	mv.addObject("list", list);
    	
    	return mv;
    }
	
	@RequestMapping(value="/sample/testMapArgumentResolver.com")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("");
		
		if(commandMap.isEmpty() == false){
			Iterator<Entry<String, Object>> iterator = commandMap.getMap().entrySet().iterator();
			Entry<String, Object> entry = null;
			while(iterator.hasNext()){
				entry = iterator.next();
				log.debug("key : " + entry.getKey() + ", value : " + entry.getValue());
			}
		}
		
		ParameterLogger parameterLogger = new ParameterLogger(); 
		parameterLogger.outputParameters(commandMap);
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardWrite.com")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardWrite");
		return mv;
	}
	
	@RequestMapping(value="/sample/insertBoard.com")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.com");
		sampleService.insertBoard(commandMap.getMap(), request);
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardDetail.com")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardDetail");
		
		Map<String, Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardUpdate.com")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("/sample/boardUpdate");
	     
	    Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
	    mv.addObject("map", map);
	     
	    return mv;
	}

	@RequestMapping(value="/sample/updateBoard.com")
	public ModelAndView updateBoard(CommandMap commandMap) throws Exception{
		ParameterLogger parameterLogger = new ParameterLogger(); 
		parameterLogger.outputParameters(commandMap);
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.com");
		sampleService.updateBoard(commandMap.getMap());
		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}
	
	@RequestMapping(value="/sample/deleteBoard.com")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.com");
		sampleService.deleteBoard(commandMap.getMap());
		return mv;
	}
}