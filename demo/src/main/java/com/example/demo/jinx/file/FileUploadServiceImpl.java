package com.example.demo.jinx.file;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	private static final Log logger = LogFactory
			.getLog(FileUploadServiceImpl.class);

	@Override
	public com.example.demo.jinx.file.Page<Void> fileUpload(
			MultipartFile file) {
		logger.info("fileUp1:" + file);
		Page<Void> page;
		try {
			if (file.isEmpty()) {
				 String fileName = file.getOriginalFilename();
			        int size = (int) file.getSize();
			        logger.info(fileName + "-->" + size);
			        String path = "C:/Users/GP/Desktop/myProject/demo/demo/file" ;
			        File dest = new File(path + "/" + fileName);
			        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
			            dest.getParentFile().mkdir();
			        }
			            file.transferTo(dest); //保存文件
			            page = new Page<Void>(2, "保存成功");
			} else {
				page = new Page<Void>(2, "数据为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = new Page<Void>(3, "系统异常");
			logger.info("error");
		}
		return page;
	}
}
