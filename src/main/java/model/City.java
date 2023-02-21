package model;

import java.time.LocalDate;
import java.util.*;

public class City {

    private String name;
    private String country;
    private int population;
    private final TreeMap<LocalDate,InfectedInfoRecord> infectedInfoRecords;

    public City(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
        infectedInfoRecords = new TreeMap<>();
    }

    public void updateInfectedInfoRecords(InfectedInfoRecord newRecord){
        if(infectedInfoRecords.get(newRecord.getRecordDate()) != null){
            infectedInfoRecords.get(newRecord.getRecordDate()).addIncidenceRateValueToSameDate(newRecord.getNumberOfInfected().first(),this);
        } else {
            infectedInfoRecords.put(newRecord.getRecordDate(),newRecord);
        }
    }

    public ArrayList<DatedIncidenceRate> getIncidenceInDates(LocalDate initialDate, LocalDate finalDate) {
        ArrayList<DatedIncidenceRate> datedIncidenceList = new ArrayList<>();
        SortedMap<LocalDate,InfectedInfoRecord> subMapOfInfectedRecords = infectedInfoRecords.subMap(initialDate,finalDate.plusDays(1));

        for (var entry: subMapOfInfectedRecords.entrySet()) {
                datedIncidenceList.add(new DatedIncidenceRate(entry.getValue().getRecordDate(), entry.getValue().getIncidenceRate()));
        }

        return datedIncidenceList;
    }

    public TreeMap<LocalDate,InfectedInfoRecord> getInfectedInfoRecords() {
        return infectedInfoRecords;
    }

    public String getName() {
        return name;
    }

    public String getKeyName() {
        return (name + " " + country).toLowerCase(Locale.ROOT);
    }

    public int getPopulation() {
        return population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}
