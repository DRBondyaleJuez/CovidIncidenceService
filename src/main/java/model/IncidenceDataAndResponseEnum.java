package model;

import core.web.responses.ResponseEnum;

import java.util.ArrayList;

public class IncidenceDataAndResponseEnum {

    private ResponseEnum responseEnum;
    private ArrayList<DatedIncidenceRate> queriedIncidenceResponseList;

    public IncidenceDataAndResponseEnum(ResponseEnum currentResponseEnum, ArrayList<DatedIncidenceRate>incidenceResponseSubset) {
        this.responseEnum = currentResponseEnum;
        queriedIncidenceResponseList = incidenceResponseSubset;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public ArrayList<DatedIncidenceRate> getQueriedIncidenceResponseList() {
        return queriedIncidenceResponseList;
    }
}
