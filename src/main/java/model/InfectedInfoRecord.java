package model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;
import java.util.TreeSet;

/**
 * Provides and object representing an entry for a particular location of information regarding the number of infectedPeople and the incidence rate
 * for a particular date. It implements comparable to sort these by date when put in a container.
 */
public class InfectedInfoRecord implements Comparable<InfectedInfoRecord> {

    private final TreeSet<Integer> numberOfInfectedSet = new TreeSet<>();
    private final TreeSet<Double>  incidenceRateSet = new TreeSet<>();

    private final LocalDate recordDate;

    /**
     * This is the constructor
     * @param recordDate LocalDate showing the date of the data provided in this record
     * @param numberOfInfected int number of people infected by covid provided by the user posting in the webService
     * @param currentCity City The city object to which this data corresponds. This data is used to obtain the population
     *                   for the calculation of the incidenceRate.
     */
    public InfectedInfoRecord(LocalDate recordDate, int numberOfInfected , City currentCity) {

        numberOfInfectedSet.add(numberOfInfected);
        this.recordDate = recordDate;

        incidenceRateSet.add(CalculateIncidenceRate(numberOfInfected, currentCity.getPopulation()));
    }

    private double CalculateIncidenceRate(int numberOfInfected,int population){

        DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
        df.setRoundingMode(RoundingMode.CEILING);
        double incidenceRateResult = (double)numberOfInfected/(double)population*100000.0;
        return Double.parseDouble(df.format(incidenceRateResult));

    }

    /**
     * Adding another number of infected people and another incidence rate if there is more than one entry for the same
     * date to the numberOfInfectedSet and incidenceRateSet attributes
     * @param numberOfInfected int another number of infected people for the same date
     * @param currentCity City object corresponding to the city that has more data for the same date
     * @return boolean true if everything is added with no issue
     */
    public boolean addIncidenceRateValueToSameDate(int numberOfInfected,City currentCity){

        numberOfInfectedSet.add(numberOfInfected);
        incidenceRateSet.add(CalculateIncidenceRate(numberOfInfected, currentCity.getPopulation()));
        return true;
    }

    @Override
    public int compareTo(InfectedInfoRecord newRecord) {
        if(recordDate.isBefore(newRecord.recordDate)) {
            return -1;
        } else if (recordDate.isAfter(newRecord.recordDate)){
            return 1;
        } else {
            return 0;
        }
    }

    //GETTERS
    public TreeSet<Integer> getNumberOfInfected() {
        return numberOfInfectedSet;
    }

    public TreeSet<Double>  getIncidenceRate() {
        return incidenceRateSet;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }
}
