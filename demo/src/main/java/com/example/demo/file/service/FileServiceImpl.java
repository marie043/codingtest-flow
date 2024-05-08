package com.example.demo.file.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.file.entity.ExtensionEntity;
import com.example.demo.file.repository.ExtensionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
	private final ExtensionRepository extensionRepository;

	@Override
	public Map addExtension(String extension) {
		Map<String, String> data = new HashMap<>();
		// 이미 존재 하는 extension 인지 확인
		List<ExtensionEntity> list = extensionRepository.findAllByValue(extension);
		if(!list.isEmpty()){
			data.put("msg", "이미 존재 하는 확장자");
			data.put("extension", null);
			return data;
		}
		// 존재 하지 않다면 추가
		ExtensionEntity entity = new ExtensionEntity();
		entity.setValue(extension);
		entity = extensionRepository.save(entity);
		data.put("msg", null);
		data.put("extension", entity.getValue());
		return data;
	}
}
