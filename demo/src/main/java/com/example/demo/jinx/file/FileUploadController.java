package com.example.demo.jinx.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("file")
public class FileUploadController {
	private static final Log logger = LogFactory.getLog(FileUploadController.class);
	@Autowired
	private FileUploadService fileUploadService;
	
	
	@RequestMapping(value = "fileUpload", method = RequestMethod.GET)
	public String showFileUpload() {
		logger.info("showPersonal() ");
		return "fileUpload";
	}
	
	// 修改头像页面
		@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
		@ResponseBody
		public Page<Void> fileUpload(MultipartFile fileUp) {
			logger.info("fileUp:" + fileUp);
			Page<Void> page;
			try {
				page = fileUploadService.fileUpload(fileUp);
			} catch (Exception e) {
				e.printStackTrace();
				page = new Page<Void>(1, "系统异常");
				logger.info("error");
			}
			return page;
		}
		
		

}
