package com.company;
import java.awt.*;

public class Volvo240 extends Vehicle {

    private final static double trimFactor = 1.25;


    /**Specifies the cars attributes
     *
     * @param position where the cars position is
     */

    public Volvo240(Position position){
        super(4, 100, Color.black, "Volvo240", position, 2.5, 1.5,
                true);
        stopEngine();

    }


    /**This method says how fast the car can go
     *
     * @param enginePower how strong the motor is
     * @return returns the cars top speed
     */
    @Override
    public double speedFactor(double enginePower){
        return enginePower * 0.01 * trimFactor;
    }
}


