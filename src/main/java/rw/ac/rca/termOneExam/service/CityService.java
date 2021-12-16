package rw.ac.rca.termOneExam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

@Service
public class CityService {

	@Autowired
	private ICityRepository cityRepository;

	public Optional<City> getById(long id) {

		Optional<City> cityOptional = cityRepository.findById(id);
		cityOptional.get().setFahrenheit((cityOptional.get().getWeather() * 9/5) + 32);

		return cityOptional;
	}

	public List<City> getAll() {

		List<City> cities = new ArrayList<>();

		for(City city: cityRepository.findAll()) {

			city.setFahrenheit( (city.getWeather() * 9/5) + 32);
			cities.add(city);

		}

		return cities;
	}

	public boolean existsByName(String name) {

		return cityRepository.existsByName(name);
	}

	public City save(CreateCityDTO dto) {
		City city =  new City(dto.getName(), dto.getWeather());
		city.setFahrenheit( (dto.getWeather() * 9/5) + 32);
		return cityRepository.save(city);
	}
}