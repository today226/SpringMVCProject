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
		
		//첨부파일은 Multipart 형식의 데이터이며, HttpServletRequest에 담겨서 서버로 전송
		//HttpServletRequest 그 자체로는 Multipart형식의 데이터를 조작하는데 어려움이 있기 때문에, MultipartHttpServletRequset 형식으로 형변환
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		/*Iterator를 이용하여 Map에 있는 데이터를 while문을 이용하여 순차적으로 접근하려고 한다. 
		  아까 boardWrite.jsp에서 <input type="file" name="file">이라는 태그를 추가했었다. 여기서 name="file"을 봐야한다. 
		 JSP내에서 작성된 데이터가 서버로 전송될 때에는 태그의 name값을 키(key)로 해서 값(value)가 전송. 
		  즉, request에 값이 전달될때에도 Map과 마찬가지로 key,value 쌍의 형식으로 데이터가 저장된다. 
		  위의 태그에서 name은 "file" 이라는 값이었고, reqeust에서 "file"이라는 키를 통해서 데이터를 가져올수 있는데, 
		 "file"이라는 키를 알고있지만, 실제로 개발을 하면, name값은 여러가지 다른 이름으로 넘어올수 있다. 
		  따라서 Iterator를 통해서 모든 name값을 알아서 가져오게 하면, 개발자는 name이 무엇인지를 몰라도, 쉽게 그 값을 사용 할 수 있다
		 */
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		while(iterator.hasNext()){
			//multipartHttpServletRequest의 getFile() 메서드는 request에 저장된 파일의 name을 인자로 받는다.
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
