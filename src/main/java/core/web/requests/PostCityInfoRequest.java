package core.web.requests;

/**
 * Provides an encapsulation of the object necessary to perform the post of a city's information in the webService. This
 * parameter will be required in the body of the request.
 */
public class PostCityInfoRequest {

    private String currentCity;
    private String country;
    private int population;

    /**
     * This is the constructor with all the parameters needed for the post request.
     * @param currentCity String the name of the city
     * @param country String the name of the city's country
     * @param population int population of the city
     */
    public PostCityInfoRequest(String currentCity, String country, int population) {
        this.currentCity = currentCity;
        this.country = country;
        this.population = population;
    }

    //GETTERS
    public String getCurrentCity() {
        return currentCity;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }
}
