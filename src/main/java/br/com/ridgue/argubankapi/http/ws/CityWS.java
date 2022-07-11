package br.com.ridgue.argubankapi.http.ws;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.http.domain.response.CityResponse;
import br.com.ridgue.argubankapi.usecase.city.FindCityUsecase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

import static br.com.ridgue.argubankapi.http.ws.url.BaseWS.INTERNAL_MESSAGE_ERROR;
import static br.com.ridgue.argubankapi.http.ws.url.URLMapping.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(ROOT_API_PATH)
@AllArgsConstructor
@Slf4j
public class CityWS {
    private final FindCityUsecase findCityUsecase;

    @GetMapping(ROOT_API_WS_FIND_ALL_CITY)
    public ResponseEntity<CityResponse> findAll() {
        return ResponseEntity.ok(new CityResponse(findCityUsecase.findAll()));
    }

    @GetMapping(ROOT_API_WS_FIND_CITY_BY_ID)
    public ResponseEntity<CityResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(new CityResponse(Collections.singletonList(findCityUsecase.findById(id))));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(
                    new CityResponse("NOT_FOUND", e.getMessage()));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_CITY_BY_NAME)
    public ResponseEntity<CityResponse> findByName(@PathVariable("name") String name) {
        try {
            return ResponseEntity.ok(new CityResponse(findCityUsecase.findByName(name)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(
                    new CityResponse("NOT_FOUND", e.getMessage()));
        }
    }
}
