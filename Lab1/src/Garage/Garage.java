package Garage;


import com.company.Vehicle;

import java.util.ArrayList;

/**The class Garage describes what type of cars it can handle and how many
 *
 * @param <T> Type of vehicle
 */

public class Garage <T extends Vehicle>{
    private int maxNrOfCars;
    public ArrayList<T> carsInGarage = new ArrayList<>();

    /**describes the attributes of garage
     *
     * @param maxNrOfCars  how many cars the garage can take in
     */

    public Garage(int maxNrOfCars) {
        this.maxNrOfCars = maxNrOfCars;
    }

    /**Checks if the garage has room for more cars and if the garage can take in that type of car
     * If the garage does not take that type of model it throws an IllegalArgumentException.
     * @param car the car we want to load to garage
     */

    public void loadCarToGarage(T car){
        if(carsInGarage.size() < maxNrOfCars){
            carsInGarage.add(car);
            car.carNotLoaded = false;
        }
    }

    /**checks if there's cars in the garage and if it's the type we want to unload. If yes, it unloads it
     *
     * @param car the car we want to unload
     * @return the car that was unloaded
     */

    public T unloadCarFromGarage(T car){
        if(!carsInGarage.isEmpty()){
            for(T cars : carsInGarage){
                if(cars == car){
                    T temp = carsInGarage.get(carsInGarage.indexOf(cars));
                    carsInGarage.remove(cars);
                    cars.carNotLoaded = true;
                    return temp;
                }
            }
        } return null;
    }
}
