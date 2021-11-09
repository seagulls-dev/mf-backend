package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.MarkerModel;

public interface MarkerRepository extends JpaRepository<MarkerModel, Long> {

}
