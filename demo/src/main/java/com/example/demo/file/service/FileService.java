package com.example.demo.file.service;

import java.util.Map;

public interface FileService {
	Map addExtension(String extension);

	Map listExtensions();

	Map deleteExtension(String extension);
}
