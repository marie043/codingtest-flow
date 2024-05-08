package com.example.demo.file.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtensionVo {
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]{1,20}$")
	private String extension;
}
