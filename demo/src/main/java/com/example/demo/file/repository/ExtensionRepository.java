package com.example.demo.file.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.file.entity.ExtensionEntity;

@Repository
public interface ExtensionRepository extends JpaRepository<ExtensionEntity, Integer> {
	List<ExtensionEntity> findAllByValue(String value);
}
