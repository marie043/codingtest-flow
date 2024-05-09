package com.example.demo.file.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	Map addExtension(String extension);

	Map listExtensions();

	Map deleteExtension(String extension);

	Map addFile(MultipartFile file);
}
