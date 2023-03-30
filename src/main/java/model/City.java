package model;

import java.time.LocalDate;
import java.util.*;

/**
 * Provides an object that represent the city profile containing its country, population and infected record information.
 */
public class City {

    private String name;
    private String country;
    private int population;
    private final TreeMap<LocalDate,InfectedInfoRecord> infectedInfoRecords;

    /**
     * This is the constructor where the information will be provided normally by a client posting using the webService
     * @param name String name of the city
     * @param country String name of the country
     * @param population int the number of people living in the city at the moment of the object creation
     */
    public City(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
        infectedInfoRecords = new TreeMap<>();
    }

    /**
     * Add new information of people in infected at a certain date.
     * This information is added to the infectedInfoRecords treeMap using the date as the key and the InfectedInfoRecord
     * as the value. If there is no content for this city the key and value are added and if there is already information
     * for this city for that date the information is added to that particular date key value.
     * @param newRecord InfectedInfoRecord the objects contained by the infectedInfoRecords treeMap
     */
    public void updateInfectedInfoRecords(InfectedInfoRecord newRecord){
        if(infectedInfoRecords.get(newRecord.getRecordDate()) != null){
            infectedInfoRecords.get(newRecord.getRecordDate()).addIncidenceRateValueToSameDate(newRecord.getNumberOfInfected().first(),this);
        } else {
            infectedInfoRecords.put(newRecord.getRecordDate(),newRecord);
        }
    }

    /**
     *
     * @param initialDate LocalDate indicating the beginning of the date interval the information is going to be searched in.
     * @param finalDate LocalDate indicating the end of the date interval the information is going to be search in.
     * @return ArrayList of DatedIncidenceRatObject a list of these objects indicating the date and the value or values
     * of incidence reported for those dates contain between the date interval in the arguments.
     */
    public ArrayList<DatedIncidenceRate> getIncidenceBetweenDates(LocalDate initialDate, LocalDate finalDate) {
        ArrayList<DatedIncidenceRate> datedIncidenceList = new ArrayList<>();
        SortedMap<LocalDate,InfectedInfoRecord> subMapOfInfectedRecords = infectedInfoRecords.subMap(initialDate,finalDate.plusDays(1));

        for (var entry: subMapOfInfectedRecords.entrySet()) {
                datedIncidenceList.add(new DatedIncidenceRate(entry.getValue().getRecordDate(), entry.getValue().getIncidenceRate()));
        }

        return datedIncidenceList;
    }

    //GETTERS
    public TreeMap<LocalDate,InfectedInfoRecord> getInfectedInfoRecords() {
        return infectedInfoRecords;
    }

    public String getName() {
        return name;
    }

    /**
     * This method provides a String that results from combining the city name and the country name in lower case to
     * generate a String that serves as a key for the map type storage system used to locally store the City object in a
     * CityDatabase object with a Map container
     * @return String combination of the city name and country name generating the key for this city object.
     */
    public String getKeyName() {
        return (name + " " + country).toLowerCase(Locale.ROOT);
    }

    public int getPopulation() {
        return population;
    }

    //SETTERS
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
