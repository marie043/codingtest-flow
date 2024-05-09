package com.example.demo.file.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.file.service.FileService;
import com.example.demo.file.vo.ExtensionVo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileController {
	private final FileService fileService;

	@PostMapping("/file")
	public Map addFile(@RequestParam("file") MultipartFile file){
		System.out.println(file.getName());
		return null;
	}

	@PostMapping("/extension")
	public Map addExtension(@RequestBody @Valid  ExtensionVo vo){
		return fileService.addExtension(vo.getExtension());
	}

	@GetMapping("/extension")
	public Map listExtensions(){
		return fileService.listExtensions();
	}

	@DeleteMapping("/extension")
	public Map deleteExtension(@RequestBody @Valid ExtensionVo vo){
		return fileService.deleteExtension(vo.getExtension());
	}

}
