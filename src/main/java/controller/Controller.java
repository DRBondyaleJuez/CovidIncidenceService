package controller;

import core.web.requests.PostCityIncidenceRequest;
import core.web.requests.PostCityInfoRequest;
import core.web.responses.ResponseEnum;
import model.*;
import persistence.CityDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Provides an object that acts as a intermediary between the web service, the model and the persistence. It performs the
 * actions necessary to fulfill the requests of the web service
 */
public class Controller {

    private final CityDatabase cityDatabase;

    public Controller(CityDatabase cityDatabase) {
        this.cityDatabase = cityDatabase;
    }

    public ResponseEnum insertNewCityInfo(PostCityInfoRequest postCityInfoRequest){

        if(!postCityInfoRequest.getCurrentCity().matches("[a-zA-Z- ']+")){
            return ResponseEnum.INCORRECT_CITY_COUNTRY_FORMAT;
        }

        String cleanedName = postCityInfoRequest.getCurrentCity().replaceAll(" |-|'","");
        String cleanedCountry = postCityInfoRequest.getCountry().replaceAll(" |-|'","");

        City currentUpdatedCity = new City(cleanedName , cleanedCountry , postCityInfoRequest.getPopulation());
        cityDatabase.updateCityDatabase(currentUpdatedCity);
        return ResponseEnum.CITY_INFO_UPDATED;
    }

    public ResponseEnum insertNewInfectedRecord(PostCityIncidenceRequest postCityIncidenceRequest){

        //Generating the local date for the record object instantiation
        if(postCityIncidenceRequest.getRecordDate().isBefore(LocalDate.of(2019,9,1)) || postCityIncidenceRequest.getRecordDate().isAfter(LocalDate.now())){
            return ResponseEnum.WRONG_YEAR;
        }

        //Searching for the City where the record is going to be added
        if(!isCityAndCountryFormatCorrect(postCityIncidenceRequest.getCurrentCity(), postCityIncidenceRequest.getCountry())){
            return ResponseEnum.INCORRECT_CITY_COUNTRY_FORMAT;
        }

        String keyName = generateKeyName(postCityIncidenceRequest.getCurrentCity(), postCityIncidenceRequest.getCountry());
        City cityWithInfectedRecordUpdate = cityDatabase.getSingleCity(keyName);
        if(cityWithInfectedRecordUpdate == null) return ResponseEnum.UNREGISTERED_CITY;

        //If everything is correct the record can be instantiated and add to the city's infected record info
        InfectedInfoRecord newInfectedRecord = new InfectedInfoRecord(postCityIncidenceRequest.getRecordDate(), postCityIncidenceRequest.getNumberOfInfected(), cityWithInfectedRecordUpdate);
        cityWithInfectedRecordUpdate.updateInfectedInfoRecords(newInfectedRecord);

        return ResponseEnum.CITY_INFO_UPDATED;
    }

    public IncidenceDataAndResponseEnum retrieveIncidenceResponse(String cityName, String country, LocalDate preliminaryInitialDate, LocalDate preliminaryFinalDate){

        LocalDate initialDate;
        LocalDate finalDate;

            if(preliminaryInitialDate.isBefore(preliminaryFinalDate) || preliminaryInitialDate.isEqual(preliminaryFinalDate)){
                initialDate = preliminaryInitialDate;
                finalDate = preliminaryFinalDate;
            } else {
                initialDate = preliminaryFinalDate;
                finalDate = preliminaryInitialDate;
            }


        //Searching for the City where the record is going to be added
        if(!isCityAndCountryFormatCorrect(cityName,country)){
            return new IncidenceDataAndResponseEnum(ResponseEnum.INCORRECT_CITY_COUNTRY_FORMAT,null);
        }

        String keyName = generateKeyName(cityName,country);
        City cityWithIncidenceToRetrieve = cityDatabase.getSingleCity(keyName);
        if(cityWithIncidenceToRetrieve == null) return new IncidenceDataAndResponseEnum(ResponseEnum.UNREGISTERED_CITY,null);

        //If everything is correct the record can be instantiated and add to the city's infected record info
        ArrayList<DatedIncidenceRate> incidenceList = cityWithIncidenceToRetrieve.getIncidenceBetweenDates(initialDate,finalDate);
        ResponseEnum appropriateResponseEnum;

        if(incidenceList.isEmpty()){
            appropriateResponseEnum = ResponseEnum.CITY_INFO_EMPTY;
        } else {
            appropriateResponseEnum = ResponseEnum.CITY_INFO_SUCCESSFULLY_RETRIEVED;
        }

        return new IncidenceDataAndResponseEnum(appropriateResponseEnum,incidenceList);
    }

    private boolean isCityAndCountryFormatCorrect(String cityName,String countryName){
        return countryName.matches("[a-zA-Z- ']+") && cityName.matches("[a-zA-Z- ']+");
    }

    private String generateKeyName(String cityName, String country){
        String cleanedName = cityName.replaceAll(" |-|'","");
        String cleanedCountry = country.replaceAll(" |-|'","");

        return (cleanedName + " " + cleanedCountry).toLowerCase(Locale.ROOT);
    }

}
