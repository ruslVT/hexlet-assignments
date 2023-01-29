package exercise.service;

import com.fasterxml.jackson.core.type.TypeReference;
import exercise.HttpClient;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeather(long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));

        String weatherOfCity = client.get("http://weather/api/v2/cities/" + city.getName());

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(weatherOfCity, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // END
}
