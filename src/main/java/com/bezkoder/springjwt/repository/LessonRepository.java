package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.LessonModel;


public interface LessonRepository extends JpaRepository<LessonModel, Long> {

}
