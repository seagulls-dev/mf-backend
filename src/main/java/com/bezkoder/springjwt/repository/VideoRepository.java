package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.VideoModel;

public interface VideoRepository extends JpaRepository<VideoModel, Long> {

	List<VideoModel> findByUserIdAndImageId(long userId, long imageId);

}
