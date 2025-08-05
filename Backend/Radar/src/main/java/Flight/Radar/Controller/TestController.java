package Flight.Radar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import Flight.Radar.Service.RadarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("FlightRadar")
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private RadarService radarService;

    @GetMapping("/test")
    public JsonNode getRawFlights() {
        return radarService.fetchRawFlightData().block();
    }

}
