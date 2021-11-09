package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.SceneModel;

public interface SceneRepository extends JpaRepository<SceneModel, Long> {

	List<SceneModel> findByUserId(String uid);

}
