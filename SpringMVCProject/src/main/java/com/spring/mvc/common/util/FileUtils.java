package com.spring.mvc.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//@Component 어노테이션을 이용하여 이 객체의 관리를 스프링이 담당하도록 할 계획이다
@Component("fileUtils")
public class FileUtils {
	private static final String filePath = "F:\\fileUpload\\file";
	
	public List<Map<String, Object>> parseInsertFileInfo(Map<String, Object> map, 
			HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		//클라이언트에서 전송된 파일 정보를 담아서 반환을 해줄 List이다. 다중파일전송을 하도록 수정할 계획이기 때문에 미리 그에 맞도록 구성
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;
		
		String boardIdx = (String)map.get("IDX");
		
		//저장할 폴더가 없으면 폴더 생성
		File file = new File(filePath);
		if(file.exists() == false){
			file.mkdirs();
		}
		
		while(iterator.hasNext()){
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false){
				originalFileName = multipartFile.getOriginalFilename();
				//파일 확장자 가져오기
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() +  originalFileExtension;
				
				file = new File(filePath + storedFileName);
				//지정된 경로에 파일을 생성
				multipartFile.transferTo(file);
				
				listMap = new HashMap<String, Object>();
				listMap.put("BOARD_IDX", boardIdx);
                listMap.put("ORIGINAL_FILE_NAME", originalFileName);
                listMap.put("STORED_FILE_NAME", storedFileName);
                listMap.put("FILE_SIZE", multipartFile.getSize());
                list.add(listMap);
			}
		}
		
		return list;
	}
}
