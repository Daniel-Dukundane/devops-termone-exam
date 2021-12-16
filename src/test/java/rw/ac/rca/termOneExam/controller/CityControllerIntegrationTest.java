package rw.ac.rca.termOneExam.controller;

import org.json.JSONException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll_success() throws JSONException {
        String response = this.restTemplate.getForObject("/api/cities/all", String.class);
        System.out.println(response);
        JSONAssert.assertEquals("[{\"id\":101,\"name\":\"Muhanga\",\"weather\":15.0,\"fahrenheit\":59},{\"id\":102,\"name\":\"Kicukiro\",\"weather\":28.0,\"fahrenheit\":82.4},{\"id\":103,\"name\":\"Gasabo\",\"weather\":24.0,\"fahrenheit\":75.2},{\"id\":104,\"name\":\"Gicumbi\",\"weather\":26.0,\"fahrenheit\":78.8}]", response, true);
    }

    @Test
    public void getById_success() {

        ResponseEntity<City> response = this.restTemplate.getForEntity("/api/cities/id/101", City.class);

        System.out.println(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(15.0, response.getBody().getWeather());
        assertEquals(59, response.getBody().getFahrenheit());
        assertEquals("Muhanga", response.getBody().getName());
    }

    @Test
    public void getById_failed() {

        ResponseEntity<APICustomResponse> response = this.restTemplate.getForEntity("/api/cities/id/1000", APICustomResponse.class);

        System.out.println(response);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("City not found with id 1000",response.getBody().getMessage());
    }

    @Test
    public void createItem_Success() {

        CreateCityDTO cityDTO=new CreateCityDTO();

        cityDTO.setName("Musanze");
        cityDTO.setWeather(30);

        ResponseEntity<City> response = this.restTemplate.postForEntity("/api/cities/add", cityDTO, City.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Musanze",response.getBody().getName());
        assertEquals(86,response.getBody().getFahrenheit());
    }

    @Test
    public void createItem_BAD_REQUEST() {

        CreateCityDTO cityDTO=new CreateCityDTO();

        cityDTO.setName("Muhanga");

        cityDTO.setWeather(19.0);

        ResponseEntity<City> response = this.restTemplate.postForEntity("/api/cities/add", cityDTO, City.class);

        assertEquals(400, response.getStatusCodeValue());
    }


}
