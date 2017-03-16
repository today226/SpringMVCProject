package com.spring.mvc.common.serviceImpl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spring.mvc.common.dao.CommonDAO;
import com.spring.mvc.common.service.CommonService;

@Service("commonService")
public class CommonServiceImpl implements CommonService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
}
