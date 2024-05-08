package com.example.demo.file.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "extensions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionEntity {
	@Id
	@Column(name = "extension_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seq;

	@Column(name = "value")
	private String value;
}
