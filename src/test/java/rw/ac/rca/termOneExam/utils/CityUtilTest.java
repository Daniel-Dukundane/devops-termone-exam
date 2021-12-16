package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CityUtilTest {

    @Mock
    private ICityRepository cityRepositoryMock;

    List<City> mock = mock(List.class);


    @Test
    public void notMoreThan40CelsiusTest_success(){
        Boolean citiesWithWeatherGreater40Checker = false;

        when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City("Kigali",10),
                new City("Musanze",10)));

        for (City city: cityRepositoryMock.findAll())
            if(city.getWeather() > 40)
                citiesWithWeatherGreater40Checker = true;


        assertEquals(false, citiesWithWeatherGreater40Checker);
    }

    @Test
    public void notMoreThan40CelsiusTest_Failed(){
        Boolean citiesWithWeatherGreater40Checker = false;

        when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City("Kigali",10),
                new City("Musanze",10), new City("Karongi",50)));


        for (City city: cityRepositoryMock.findAll())
            if(city.getWeather() > 40)
                citiesWithWeatherGreater40Checker = true;



        assertEquals(true, citiesWithWeatherGreater40Checker);

    }

    @Test
    public void containsKigaliAndMusanzeTest_success(){
        Boolean containsCityMUSANZE = false;
        Boolean containsCityKIGALI = false;

        when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City("Kigali",10),
                new City("Musanze",10)));


        for (City city: cityRepositoryMock.findAll()){

            if(city.getName().equals("Kigali") ) {
                containsCityKIGALI = true;
            }
            if(city.getName() == "Musanze") {
                containsCityMUSANZE = true;
            }

        }


        assertEquals(true, containsCityKIGALI);
        assertEquals(true, containsCityMUSANZE);
    }

    @Test
    public void testSpying() {

        ArrayList<City> arrayListSpy =  spy(ArrayList.class);

        arrayListSpy.add(new City("Kigali",30));

        System.out.println(arrayListSpy.get(0));//Test0
        System.out.println(arrayListSpy.size());//1
        assertEquals(1,arrayListSpy.size());

        arrayListSpy.add(new City("Kamonyi",30));
        arrayListSpy.add(new City("Karongi",20));

        System.out.println(arrayListSpy.size());//3
        assertEquals(3,arrayListSpy.size());

        when(arrayListSpy.size()).thenReturn(5);

        System.out.println(arrayListSpy.size());//5
        assertEquals(5,arrayListSpy.size());

        arrayListSpy.add(new City("Gicumbi",30));

        System.out.println(arrayListSpy.size());//5
        assertEquals(5,arrayListSpy.size());
    }


    @Test
    public void testMocking() {

        ArrayList<City> arrayListMock =  mock(ArrayList.class);

        System.out.println(arrayListMock.get(0));//null
        System.out.println(arrayListMock.size());//0
        assertEquals(0,arrayListMock.size());

        arrayListMock.add(new City("Kigali",30));
        arrayListMock.add(new City("Karongi",20));

        System.out.println(arrayListMock.size());//0
        assertEquals(0,arrayListMock.size());

        when(arrayListMock.size()).thenReturn(5);
        System.out.println(arrayListMock.size());//5
        assertEquals(5,arrayListMock.size());


    }



}