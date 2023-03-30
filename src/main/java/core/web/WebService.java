package core.web;

import controller.Controller;
import core.web.requests.PostCityIncidenceRequest;
import core.web.requests.PostCityInfoRequest;
import core.web.responses.PostCityInfoResponse;
import core.web.responses.ResponseEnum;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import persistence.CityDatabase;
import model.IncidenceDataAndResponseEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import core.web.responses.GetCityIncidenceResponse;
import core.web.responses.PostCityIncidenceResponse;

import java.time.LocalDate;

/**
 * Provides the RestController of the spring framework to handle all endpoints of the service. This class has the
 * @RestController annotation to allow interaction with the Application Class which manages the start point of the
 * service with the @SpringBootApplication annotation
 */
@RestController
public class WebService {

    private final Controller controller;

    /**
     * This is the constructor where a CityDatabase class object is instantiated to use as a parameter in the instantiation
     * of a controller class object that will allow communication with the persistence (CityDatabase) and with the models
     * needed for the service
     */
    public WebService() {
        CityDatabase currentCityDatabase = new CityDatabase();
        controller = new Controller(currentCityDatabase);
    }

    @RequestMapping(value="/index" , method=RequestMethod.GET)
    public String getGreetings(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate recordDate) {
        return "Hello world: " + recordDate.getDayOfMonth();
    }

    /**
     * Endpoint to request retrieve the information on the incidence rate of a particular city between a particular interval of
     * dates providing the following parameters
     * @param currentCity String name of the city
     * @param country String name of the city's country
     * @param initialDate LocalDate in the format yyyy-MM-dd the start of the date interval
     * @param finalDate LocalDate in the format yyyy-MM-dd the start of the end interval
     * @return ResponseEntity<GetCityIncidenceResponse> which contains the corresponding HTTP status and data associated
     * with the request performed
     */
    @GetMapping("/requestCityIncidence")
    public ResponseEntity<GetCityIncidenceResponse> getCityIncidence(@RequestParam String currentCity, @RequestParam String country, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate initialDate, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate finalDate) {

        IncidenceDataAndResponseEnum retrieveIncidenceDataResponse = controller.retrieveIncidenceResponse(currentCity,country,initialDate,finalDate);
        GetCityIncidenceResponse responseSectionOfResponseEntity = new GetCityIncidenceResponse(currentCity,country,retrieveIncidenceDataResponse.getResponseEnum().toString(),retrieveIncidenceDataResponse.getQueriedIncidenceResponseList());
        HttpStatus responseStatus = retrieveIncidenceDataResponse.getResponseEnum().getStatus();
        return new ResponseEntity<>(responseSectionOfResponseEntity, responseStatus);
    }

    /**
     * Endpoint to request adding the information on a number of case of a particular city between at a particular date
     * providing the following body
     * @param postCityIncidenceRequest PostCityIncidenceRequest object which is a class that encapsulates all the necessary
     *                                 components for this request
     * @return ResponseEntity<PostCityIncidenceResponse> which contains the corresponding HTTP status and a message associated
     * with the request performed
     */
    @PostMapping("/updateCityIncidence")
    public ResponseEntity<PostCityIncidenceResponse> postCityIncidence(@RequestBody PostCityIncidenceRequest postCityIncidenceRequest) {

        ResponseEnum responseEnum = controller.insertNewInfectedRecord(postCityIncidenceRequest);
        PostCityIncidenceResponse response = new PostCityIncidenceResponse(responseEnum.toString());
        HttpStatus responseStatus = responseEnum.getStatus();

        return new ResponseEntity<>(response,responseStatus);
    }

    /**
     * Endpoint to request adding the information of a particular city between at a particular date providing the
     * following body
     * @param postCityInfoRequest PostCityInfoRequest object which is a class that encapsulates all the necessary
     *                            components for this request
     * @return ResponseEntity<PostCityInfoResponse> which contains the corresponding HTTP status and a message associated
     * with the request performed
     */
    @PostMapping("/updateCityInfo")
    public ResponseEntity<PostCityInfoResponse> postCityInfo(@RequestBody PostCityInfoRequest postCityInfoRequest) {
        ResponseEnum responseEnum = controller.insertNewCityInfo(postCityInfoRequest);
        PostCityInfoResponse response = new PostCityInfoResponse(responseEnum.toString());
        HttpStatus responseStatus = responseEnum.getStatus();

        return new ResponseEntity<>(response,responseStatus);
    }

}
