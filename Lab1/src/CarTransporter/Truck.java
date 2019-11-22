package CarTransporter;

import com.company.Position;
import com.company.Vehicle;

import java.awt.*;
import java.util.Stack;


/**The Truck class describes what the trucks methods are.
 * As it also extends Vehicle, the truck class has the same method as found in Vehicle
 */
public class Truck extends Vehicle {
    private int maxNrCars;
    private boolean rampIsUp = true;
    public Stack<Vehicle> carsInTruck = new Stack<>();
    private double maxCarLength;
    private double maxCarWidth;

    /**Describes the trucks attributes
     *
     * @param position the trucks positions
     * @param maxNrCars max number of cars the truck can load
     * @param rampIsUp says if the trucks ramp is up or not
     */
    public Truck(Position position, int maxNrCars, boolean rampIsUp) {
        super(2, 125, Color.BLACK, "Toyota X100", position, 9, 2,
                true);
        this.maxNrCars = 3;
        this.rampIsUp = rampIsUp;
        this.maxCarLength = 3;
        this.maxCarWidth = 2;
    }


    /** If the truck's not moving then it lowers the ramp
     *
     */

    public void lowerRamp(){
        if(getCurrentSpeed() == 0){
            rampIsUp = false;
        }
    }

    /**If the truck's not moving it raises the ramp
     *
     */
    public void raiseRamp(){
        if(getCurrentSpeed() == 0){
            rampIsUp = true;
        }
    }


    /** the method checks if the car is close to the truck or not
     *
     * @param car the vehicle we want the truck to load
     * @return if the car is close to the truck or not
     */
    private boolean carIsClose(Vehicle car){
     Position posCar = car.getPosition();
     Position posTruck = this.getPosition();

     if(posCar.getY() > posTruck.getY() -1 && posCar.getY() < posTruck.getY() +1 &&
             posCar.getX() > posTruck.getX() -1 && posCar.getX() < posTruck.getX() +1 && car.carNotLoaded) {
         return true;
     }
     return false;
     }

    /** Load the car onto the truck, is its requirements are met
     *
     * @param car the vehicle we want the truck to load
     */

    public void loadCar(Vehicle car){
        lowerRamp();
        if(!rampIsUp && carIsClose(car) && carsInTruck.size() < maxNrCars && car.getLengthCar() < this.maxCarLength &&
                car.getWidthCar() < this.maxCarWidth){
            carsInTruck.push(car);
            car.carNotLoaded = false;
            car.setPosition(this.getPosition());

        }
    }

    /**Unloads the last car that was loaded
     *
     */

    public void unloadCar(){
        if(!rampIsUp && carsInTruck.size() > 0){
            Vehicle v = carsInTruck.pop();
            double n = 0.9;
            Position pos = new Position(n, this.getPosition().getY() - 0.2);
            v.setPosition(pos);
            n = n - 0.1;
            v.carNotLoaded = true;
        }
    }

    /**
     * Raises the ramp and moves the truck
     * If the truck has loaded car, the method sets the cars positions to the trucks position
     */

    @Override
    public void move(){
        raiseRamp();
        super.move();
        for(Vehicle c : carsInTruck){
            c.setPosition(this.getPosition());
        }
    }

    @Override
    public double speedFactor(double enginePower) {
        return 0;
    }
}
