package persistence;

import model.City;
import model.InfectedInfoRecord;

import java.time.LocalDate;
import java.util.TreeMap;
import java.util.TreeSet;

public class CityDatabase {

    private final TreeMap<String, City> cityTreeMap;

    public CityDatabase() {
        this.cityTreeMap = new TreeMap<>();
    }

    public void updateCityDatabase(City updatedCity){

        cityTreeMap.putIfAbsent(updatedCity.getKeyName(), updatedCity);

        City matchingCity = cityTreeMap.get(updatedCity.getKeyName());

        matchingCity.setPopulation(updatedCity.getPopulation());

    }

    public City getSingleCity(String keyName){
        return cityTreeMap.get(keyName);
    }


}
