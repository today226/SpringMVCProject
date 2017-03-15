package com.spring.mvc.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.mvc.dao.SampleDAO;
import com.spring.mvc.service.SampleService;

@Service("sampleService")
public class SampleServiceImpl implements SampleService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map)
			throws Exception {
		return sampleDAO.selectBoardList(map);
	}

	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		sampleDAO.insertBoard(map);
		
		//÷�������� Multipart ������ �������̸�, HttpServletRequest�� ��ܼ� ������ ����
		//HttpServletRequest �� ��ü�δ� Multipart������ �����͸� �����ϴµ� ������� �ֱ� ������, MultipartHttpServletRequset �������� ����ȯ
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		/*Iterator�� �̿��Ͽ� Map�� �ִ� �����͸� while���� �̿��Ͽ� ���������� �����Ϸ��� �Ѵ�. 
		  �Ʊ� boardWrite.jsp���� <input type="file" name="file">�̶�� �±׸� �߰��߾���. ���⼭ name="file"�� �����Ѵ�. 
		 JSP������ �ۼ��� �����Ͱ� ������ ���۵� ������ �±��� name���� Ű(key)�� �ؼ� ��(value)�� ����. 
		  ��, request�� ���� ���޵ɶ����� Map�� ���������� key,value ���� �������� �����Ͱ� ����ȴ�. 
		  ���� �±׿��� name�� "file" �̶�� ���̾���, reqeust���� "file"�̶�� Ű�� ���ؼ� �����͸� �����ü� �ִµ�, 
		 "file"�̶�� Ű�� �˰�������, ������ ������ �ϸ�, name���� �������� �ٸ� �̸����� �Ѿ�ü� �ִ�. 
		  ���� Iterator�� ���ؼ� ��� name���� �˾Ƽ� �������� �ϸ�, �����ڴ� name�� ���������� ����, ���� �� ���� ��� �� �� �ִ�
		 */
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		while(iterator.hasNext()){
			//multipartHttpServletRequest�� getFile() �޼���� request�� ����� ������ name�� ���ڷ� �޴´�.
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false){
				 log.debug("------------- file start -------------");
		            log.debug("name : "+multipartFile.getName());
		            log.debug("filename : "+multipartFile.getOriginalFilename());
		            log.debug("size : "+multipartFile.getSize());
		            log.debug("-------------- file end --------------\n");
			}
		}
	}

	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map)
			throws Exception {
		sampleDAO.updateHitCnt(map);
		Map<String, Object> resultMap = sampleDAO.selectBoardDetail(map);
		return resultMap;
	}

	@Override
	public void updateBoard(Map<String, Object> map) throws Exception {
		sampleDAO.updateBoard(map);
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		sampleDAO.deleteBoard(map);
		
	}
}
