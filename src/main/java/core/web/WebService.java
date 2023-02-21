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
 *
 */
@RestController
public class WebService {

    private final Controller controller;

    public WebService() {
        CityDatabase currentCityDatabase = new CityDatabase();
        controller = new Controller(currentCityDatabase);
    }


    @RequestMapping(value="/index" , method=RequestMethod.GET)
    public String getGreetings(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate recordDate) {
        return "Hello world: " + recordDate.getDayOfMonth();
    }
    /*
    @RequestMapping(value="/index" , method=RequestMethod.GET)
    public String getGreetings() {
        return "Hello world: ";
    }

     */

    @GetMapping("/requestCityIncidence")
    public ResponseEntity<GetCityIncidenceResponse> getCityIncidence(@RequestParam String currentCity, @RequestParam String country, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate initialDate, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate finalDate) {

        IncidenceDataAndResponseEnum retrieveIncidenceDataResponse = controller.retrieveIncidenceResponse(currentCity,country,initialDate,finalDate);
        GetCityIncidenceResponse responseSectionOfResponseEntity = new GetCityIncidenceResponse(currentCity,country,retrieveIncidenceDataResponse.getResponseEnum().toString(),retrieveIncidenceDataResponse.getQueriedIncidenceResponseList());
        HttpStatus responseStatus = retrieveIncidenceDataResponse.getResponseEnum().getStatus();
        return new ResponseEntity<>(responseSectionOfResponseEntity, responseStatus);
    }
/*
    @PostMapping("/updateCityIncidence")
    public ResponseEntity<PostCityIncidenceResponse> postCityIncidence(@RequestParam String currentCity, @RequestParam String country, @RequestParam int numberOfInfected, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate recordDate) {

        ResponseEnum responseEnum = controller.insertNewInfectedRecord(currentCity,country, recordDate, numberOfInfected);
        PostCityIncidenceResponse response = new PostCityIncidenceResponse(responseEnum.toString());
        HttpStatus responseStatus = responseEnum.getStatus();

        return new ResponseEntity<>(response,responseStatus);
    }
 */

    @PostMapping("/updateCityIncidence")
    public ResponseEntity<PostCityIncidenceResponse> postCityIncidence(@RequestBody PostCityIncidenceRequest postCityIncidenceRequest) {

        ResponseEnum responseEnum = controller.insertNewInfectedRecord(postCityIncidenceRequest);
        PostCityIncidenceResponse response = new PostCityIncidenceResponse(responseEnum.toString());
        HttpStatus responseStatus = responseEnum.getStatus();

        return new ResponseEntity<>(response,responseStatus);
    }


    @PostMapping("/updateCityInfo")
    public ResponseEntity<PostCityInfoResponse> postCityInfo(@RequestBody PostCityInfoRequest postCityInfoRequest) {
        ResponseEnum responseEnum = controller.insertNewCityInfo(postCityInfoRequest);
        PostCityInfoResponse response = new PostCityInfoResponse(responseEnum.toString());
        HttpStatus responseStatus = responseEnum.getStatus();

        return new ResponseEntity<>(response,responseStatus);
    }

    private int[] extractParameterFromDateString(String dateString){
        // TODO: delete this method and use a date
        String[] parametersString = dateString.split("/");
        int[] dateParametersInt = new int[3];
        for (int i = 0; i < dateParametersInt.length; i++) {
            dateParametersInt[i] = Integer.parseInt(parametersString[i]);
        }
        return dateParametersInt;
    }
}
