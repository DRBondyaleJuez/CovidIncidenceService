package core.web.responses;

import model.DatedIncidenceRate;

import java.util.ArrayList;

/**
 * Provides an encapsulation of the information for the response returned from the city incidence get request.
 */
public class GetCityIncidenceResponse {
    private String country;
    private String city;
    private String message;
    private int numberOfDatesWithIncidenceRateInfo;
    private ArrayList<DatedIncidenceRate> queriedIncidenceResponseList;

    /**
     * This is the constructor with all the parameter needed to build the content of the response
     * @param city String Name of the city requested
     * @param country String name of the country of the city requested
     * @param message String message associated with the response status
     * @param queriedIncidenceResponseList ArrayList<DatedIncidenceRate> a list of this class which encapsulates dates
     *                                     and incidenceRates associated to that date. The dates contained are only for
     *                                     the interval specified in the request.
     */
    public GetCityIncidenceResponse(String city, String country, String message, ArrayList<DatedIncidenceRate> queriedIncidenceResponseList) {
        this.country = country;
        this.city = city;
        this.message = message;
        this.queriedIncidenceResponseList = queriedIncidenceResponseList;
        if(queriedIncidenceResponseList == null){
            numberOfDatesWithIncidenceRateInfo = 0;
        } else {
            numberOfDatesWithIncidenceRateInfo = queriedIncidenceResponseList.size();
        }
    }

    //GETTERS
    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getMessage() {
        return message;
    }

    public int getNumberOfDatesWithIncidenceRateInfo() {
        return numberOfDatesWithIncidenceRateInfo;
    }

    public ArrayList<DatedIncidenceRate> getQueriedIncidenceResponseList() {
        return queriedIncidenceResponseList;
    }

}
