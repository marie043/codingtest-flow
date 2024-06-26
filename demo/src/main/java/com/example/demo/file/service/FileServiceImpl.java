package com.example.demo.file.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public Map addFile(MultipartFile file) {
		Map<String, String> data = new HashMap<>();
		if(file == null){
			data.put("msg", "파일이 입력되지 않았습니다.");
			return data;
		}
		String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		List<ExtensionEntity> list = extensionRepository.findAllByValue(extension);
		if(list.isEmpty()) {
			data.put("msg", "파일이 정상적으로 업로드 되었습니다.");
		}else {
			data.put("msg", "파일이 확장자 차단에 의하여 업로드 실패 했습니다.");
		}
		return data;
	}
}
