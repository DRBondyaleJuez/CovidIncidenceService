package core.web.responses;

import org.springframework.http.HttpStatus;

public enum ResponseEnum {

    UNREGISTERED_CITY("Remember city names and country must be in english. The city name for that country is not present in the database. " +
            "If you consider these have been correctly spelled please insert the cities information i.e. country and population.", HttpStatus.NOT_FOUND),
    WRONG_INCIDENCE_VALUE("The  introduced incidence value format is not correct.", HttpStatus.BAD_REQUEST),
    WRONG_YEAR("The date of the information posted precedes the beginning of the COVID pandemic, september 2019 or is after the current date", HttpStatus.BAD_REQUEST),
    WRONG_DATE("The parameters of the date are invalid. Date format should be dd/mm/yyyy.", HttpStatus.BAD_REQUEST),
    INCORRECT_CITY_COUNTRY_FORMAT("Remember city names and country must be in english. " +
            "The city name or country have at least one problematic character i.e. it only accepts English alphabetic letter, spaces and apostrophe.", HttpStatus.BAD_REQUEST),
    CITY_INFO_EMPTY("There is no information on the incidence in the selected dates.", HttpStatus.NO_CONTENT),
    CITY_INFO_SUCCESSFULLY_RETRIEVED("This is the incidence information for the city between the introduced dates.", HttpStatus.OK),
    CITY_INFO_UPDATED("Thank you for introducing the information of a city. This information will be reviewed.", HttpStatus.ACCEPTED);

    private final String text;
    private HttpStatus status;

    ResponseEnum(final String text,HttpStatus status){
        this.text = text;
        this.status = status;
    }

    @Override
    public String toString() {
        return text;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
