package rw.ac.rca.termOneExam.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import java.util.Arrays;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    private ICityRepository cityRepositoryMock;

    @InjectMocks
    private CityService cityService;

    @Test
    public void getAll_Success() {

        when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City("Kigali",10),
                new City("Muhanga",10)));

        assertEquals("Kigali",cityService.getAll().get(0).getName());
        assertEquals("Muhanga",cityService.getAll().get(1).getName());
        assertEquals(50,cityService.getAll().get(1).getFahrenheit());
    }

    @Test
    public void getById_Success() {

        when(cityRepositoryMock.findById(100L)).thenReturn(Optional.of(new City("Kigali",10)));

        assertEquals("Kigali",cityService.getById(100).get().getName());
        assertEquals(10,cityService.getById(100).get().getWeather());
    }




    @Test
    public void saveCity_Success () {
        CreateCityDTO city = new CreateCityDTO();
        city.setName("Karongi");
        city.setWeather(20);

        City savedCity = new City();
        savedCity.setName("Karongi");
        savedCity.setWeather(20);
        savedCity.setFahrenheit(68);

        when(cityRepositoryMock.save(any())).thenReturn(savedCity);


        City saved = cityService.save(city);

        assertEquals("Karongi", savedCity.getName());
        assertEquals(68, savedCity.getFahrenheit());

    }

    @Test
    public void existsByName_Success () {
        CreateCityDTO city = new CreateCityDTO();
        city.setName("Kigali");
        city.setWeather(20);

        when(cityRepositoryMock.existsByName("Kigali")).thenReturn(true);

        assertEquals(true, cityService.existsByName("Kigali"));
    }
}