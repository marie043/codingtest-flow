package com.example.demo.file.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Override
	public Map listExtensions() {
		Map<String, Object> data = new HashMap<>();
		List<ExtensionEntity> entities = extensionRepository.findAll();
		if(entities.isEmpty()){
			data.put("msg", "차단된 확장자가 없습니다.");
			data.put("list", null);
			return data;
		}
		List<String> list = entities.stream().map(entity -> {
			return entity.getValue();
		}).collect(Collectors.toList());
		data.put("msg", null);
		data.put("list", list);
		return data;
	}

	@Override
	public Map deleteExtension(String extension) {
		Map<String, String> data = new HashMap<>();
		List<ExtensionEntity> list = extensionRepository.findAllByValue(extension);
		if(list.isEmpty()) {
			data.put("msg", "등록 되지 않는 확장자 입니다.");
			return data;
		}
		for(ExtensionEntity entity : list){
			extensionRepository.delete(entity);
		}
		data.put("msg", null);
		return data;
	}
}
