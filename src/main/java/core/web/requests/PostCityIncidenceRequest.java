package core.web.requests;

import java.time.LocalDate;

/**
 * Provides an encapsulation of the object necessary to perform the post of a city incidence in the webService. This parameter
 * will be required in the body of the request.
 */
public class PostCityIncidenceRequest {

    private String currentCity;
    private String country;
    private int numberOfInfected;
    private LocalDate recordDate;

    /** This is the constructor with all the parameters needed for the post request.
     * @param currentCity String name of the city where the incidence value will be posted/added
     * @param country String name of the city's country
     * @param numberOfInfected int number of infected people in that particular date in that particular city
     * @param recordDate LocalDate the date of the data provided
     */
    public PostCityIncidenceRequest(String currentCity, String country, int numberOfInfected, LocalDate recordDate) {
        this.currentCity = currentCity;
        this.country = country;
        this.numberOfInfected = numberOfInfected;
        this.recordDate = recordDate;
    }

    //GETTERS
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
