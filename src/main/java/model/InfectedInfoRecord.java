package model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;
import java.util.TreeSet;

public class InfectedInfoRecord implements Comparable<InfectedInfoRecord> {

    private final TreeSet<Integer> numberOfInfectedSet = new TreeSet<>();
    private final TreeSet<Double>  incidenceRateSet = new TreeSet<>();

    private final LocalDate recordDate;

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
