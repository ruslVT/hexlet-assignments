package exercise.controller;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getCityInfo(@PathVariable long id) {
        return weatherService.getWeather(id);
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> getAllCitiesOrByPrefixFilter(
            @RequestParam(required = false, value = "name")
            String prefix) {

        if (prefix == null) {
            List<City> allCities = cityRepository.findAllByOrderByNameAsc();
            List<Map<String, String>> allCitiesWithTemperature = new ArrayList<>();

            for (City city : allCities) {
                Map<String, String> weather = weatherService.getWeather(city.getId());
                allCitiesWithTemperature.add(Map.of("temperature", weather.get("temperature"),
                        "name", city.getName()));
            }

            return allCitiesWithTemperature;
        }

        List<City> filteredCitiesByPrefix = cityRepository.findByNameStartingWithIgnoreCase(prefix);
        List<Map<String, String>> filteredCitiesWithTemperature = new ArrayList<>();

        for (City city : filteredCitiesByPrefix) {
            Map<String, String> weather = weatherService.getWeather(city.getId());
            filteredCitiesWithTemperature.add(Map.of("temperature", weather.get("temperature"),
                    "name", city.getName()));
        }

        return filteredCitiesWithTemperature;
    }
    // END
}

