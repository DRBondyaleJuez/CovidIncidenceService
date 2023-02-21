package core.web.responses;

import model.DatedIncidenceRate;

import java.util.ArrayList;

public class GetCityIncidenceResponse {
    private String country;
    private String city;
    private String message;
    private int numberOfDatesWithIncidenceRateInfo;
    private ArrayList<DatedIncidenceRate> queriedIncidenceResponseList;

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
