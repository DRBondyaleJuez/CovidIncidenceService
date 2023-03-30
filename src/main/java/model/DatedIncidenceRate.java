package model;

import java.time.LocalDate;
import java.util.TreeSet;

/**
 * Provides object represented the basic unit of information return by the API's get. The date and the incidence rate in one object."
 */
public class DatedIncidenceRate {

    private final LocalDate date;
    private final TreeSet<Double> incidenceRate;

    /**
     * This is the constructor.
     * @param date LocalDate corresponding to the date the incident number is posted.
     * @param incidenceRate TreeSet<Double> Tree set container sorting the incidence rates if there is more than one by
     *                      date
     */
    public DatedIncidenceRate(LocalDate date, TreeSet<Double> incidenceRate) {
        this.date = date;
        this.incidenceRate = incidenceRate;
    }

    //GETTERS
    public LocalDate getDate() {
        return date;
    }

    public TreeSet<Double> getIncidenceRate() {
        return incidenceRate;
    }
}
