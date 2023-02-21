package core.web.requests;

import java.time.LocalDate;

public class PostCityIncidenceRequest {

    private String currentCity;
    private String country;
    private int numberOfInfected;
    private LocalDate recordDate;

    public PostCityIncidenceRequest(String currentCity, String country, int numberOfInfected, LocalDate recordDate) {
        this.currentCity = currentCity;
        this.country = country;
        this.numberOfInfected = numberOfInfected;
        this.recordDate = recordDate;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public String getCountry() {
        return country;
    }

    public int getNumberOfInfected() {
        return numberOfInfected;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }
}
