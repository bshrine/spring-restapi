
package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.City;
import com.staxrt.tutorial.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CityController {

  @Autowired
  private CityRepository cityRepository;

  @GetMapping("/getallcities")
  public List<City> getAllCities() {
    return cityRepository.findAll();
  }

  @GetMapping("/cities")
  public Map<String, Object> getCitysByPostcode(@RequestParam Long initialPostCode, @RequestParam Long lastPostCode)
      throws ResourceNotFoundException {
	  List<City> cityList = cityRepository.findCitybyPostCode(initialPostCode, lastPostCode);
	  Map<String, Object> map = new HashMap<>();
	    map.put("Count", cityList.size());
	    map.put("Data", cityList);  
	  return map;
  }

  @PostMapping("/savecity")
  public City createCity(@Valid @RequestBody City city) {
    return cityRepository.save(city);
  }


}
