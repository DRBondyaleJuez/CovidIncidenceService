package model;

import java.time.LocalDate;
import java.util.TreeSet;

public class DatedIncidenceRate {

    private final LocalDate date;
    private final TreeSet<Double> incidenceRate;

    public DatedIncidenceRate(LocalDate date, TreeSet<Double> incidenceRate) {
        this.date = date;
        this.incidenceRate = incidenceRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public TreeSet<Double> getIncidenceRate() {
        return incidenceRate;
    }
}
