package core.web.requests;

public class PostCityInfoRequest {

    private String currentCity;
    private String country;
    private int population;

    public PostCityInfoRequest(String currentCity, String country, int population) {
        this.currentCity = currentCity;
        this.country = country;
        this.population = population;
    }

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
