package com.staxrt.tutorial;

import com.staxrt.tutorial.model.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllCities() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/cities",
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetCityByPostCode() {
		City city = restTemplate.getForObject(getRootUrl() + "/cities/385256", City.class);
		System.out.println(city.getSuburbName());
		Assert.assertNotNull(city);
	}

	@Test
	public void testCreateCity() {
		City city = new City();
		city.setSuburbName("test");
	

		ResponseEntity<City> postResponse = restTemplate.postForEntity(getRootUrl() + "/cities", city, City.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePost() {
		int postcode = 380092;
		City city = restTemplate.getForObject(getRootUrl() + "/cities/" + postcode, City.class);
		city.setSuburbName("test");

		restTemplate.put(getRootUrl() + "/cities/" + postcode, city);

		City updatedCity = restTemplate.getForObject(getRootUrl() + "/cities/" + postcode, City.class);
		Assert.assertNotNull(updatedCity);
	}

	@Test
	public void testDeletePost() {
		int postcode = 245893;
		City city = restTemplate.getForObject(getRootUrl() + "/cities/" + postcode, City.class);
		Assert.assertNotNull(city);

		restTemplate.delete(getRootUrl() + "/cities/" + postcode);

		try {
			city = restTemplate.getForObject(getRootUrl() + "/cities/" + postcode, City.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}
