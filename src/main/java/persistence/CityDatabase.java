package persistence;

import model.City;
import java.util.TreeMap;

/**
 * Provides the storage of the city and incidence data in the form of a tree Map attribute where the key is a String
 * called KeyName which consists in the city name and country name combined and the value being City class objects.
 */
public class CityDatabase {

    private final TreeMap<String, City> cityTreeMap;

    /**
     * This is the constructor. The tree map attribute is instantiated
     */
    public CityDatabase() {
        this.cityTreeMap = new TreeMap<>();
    }

    /** This method puts Cities in the tree map with their corresponding key if it is absent. If key has a value already then the
     * population value of the city being added is used to update the population value of the City object stored at that key.
     * @param updatedCity City object that wants to be introduced in the tree Map
     */
    public void updateCityDatabase(City updatedCity){

        cityTreeMap.putIfAbsent(updatedCity.getKeyName(), updatedCity);

        City matchingCity = cityTreeMap.get(updatedCity.getKeyName());

        matchingCity.setPopulation(updatedCity.getPopulation());

    }

    //GETTER
    public City getSingleCity(String keyName){
        return cityTreeMap.get(keyName);
    }


}
