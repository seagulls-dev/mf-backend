package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bezkoder.springjwt.models.ImageModel;
import java.lang.Long;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	
	Optional<ImageModel> findByName(String name);
	
	

	
	//native query 
	//	  @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
	//	  User findByEmailAddress(String emailAddress);
	//Or ImageModel findOrderByCreationDateDesc()
	//@Query("select img from ImageModel img order by img.creation_date desc ")
	//	ImageModel findBylastUploadDate();
	//last uploaded image, query should be with userId
	List<ImageModel> findByOrderByCreationDateDesc();


	List<ImageModel> findByUserId(Long user_id);

	Optional<ImageModel> findByUserIdAndName(long userId, String imageName);
	Optional<ImageModel> findByUserIdAndId(long userId, long imageId);


	
}