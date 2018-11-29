package com.example.demo.jinx.file;


import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
		public Page<Void> fileUpload(MultipartFile fileUp);
}
