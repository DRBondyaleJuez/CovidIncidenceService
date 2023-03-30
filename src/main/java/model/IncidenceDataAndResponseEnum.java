package model;

import core.web.responses.ResponseEnum;

import java.util.ArrayList;

/**
 * Provides an encapsulation of the webService response enum which corresponds to a HTTPResponse and the data provided
 * by the webService when requesting the the incidence of a particular city in a particular date interval.
 */
public class IncidenceDataAndResponseEnum {

    private ResponseEnum responseEnum;
    private ArrayList<DatedIncidenceRate> queriedIncidenceResponseList;

    /**
     * This is the constructor.
     * @param currentResponseEnum ResponseEnum depending on the behaviour of the request to the webService this will
     *                           correspond to a successful or a client error response
     * @param incidenceResponseSubset ArrayList<DatedIncidenceRate> a list of DatedIncidenceRate object which corresponds
     *                                to the requested date interval. It can be empty.
     */
    public IncidenceDataAndResponseEnum(ResponseEnum currentResponseEnum, ArrayList<DatedIncidenceRate>incidenceResponseSubset) {
        this.responseEnum = currentResponseEnum;
        queriedIncidenceResponseList = incidenceResponseSubset;
    }

    //GETTERS
    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public ArrayList<DatedIncidenceRate> getQueriedIncidenceResponseList() {
        return queriedIncidenceResponseList;
    }
}
