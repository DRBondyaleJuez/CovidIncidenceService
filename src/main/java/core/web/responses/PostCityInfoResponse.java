package core.web.responses;

/**
 * Provides an encapsulation of the information for the response returned from the city incidence post request.
 */
public class PostCityInfoResponse {

    private String message;

    /**
     * This is the constructor with all the parameter needed to build the content of the response
     * @param message String message associated with the response status
     */
    public PostCityInfoResponse(String message) {
        this.message = message;
    }

    //GETTERS
    public String getMessage() {
        return message;
    }

}
