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

/*	1.HandlerMethodArgumentResolver 란?
	HandlerMethodArgumentResolver는 스프링 3.1에서 추가된 인터페이스다. 스프링 3.1 이전에는 WebArgumentResolver 라는 인터페이스였는데, 
	스프링 3.1 이후부터 HandlerMethodArgumentResolver 라는 이름으로 바뀌었다
	
	이것이 하는 역할은 다음과 같다. 
	스프링 사용 시, 컨트롤러(Controller)에 들어오는 파라미터(Parameter)를 수정하거나 공통적으로 추가를 해주어야 하는 경우가 있다
	예를 들어, 로그인을 한 사용자의 사용자 아이디나 닉네임등을 추가하는것을 생각해보자
	보통 그런 정보는 세션(Session)에 담아놓고 사용하는데, DB에 그러한 정보를 입력할 때에는 결국 세션에서 값을 꺼내와서 파라미터로 추가를 해야한다
	그런 경우가 뭐 하나나 두번 정도 있다면 몰라도, 여러번 사용되는 값을 그렇게 일일히 세션에서 가져오는건 상당히 번거로운 일이다
	HandlerMethodArgumentResolver 는 사용자 요청이 Controller에 도달하기 전에 그 요청의 파라미터들을 수정할 수 있도록 해준다
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