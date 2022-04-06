package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.City;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	@Query("SELECT city FROM City city WHERE city.postCodes BETWEEN ?1 AND ?2 ORDER BY city.suburbName")
	public List<City> findCitybyPostCode(Long initialPostCode, Long lastPostCode);
	
	
}
