package Scania;

import com.company.Position;
import com.company.Vehicle;

import java.awt.*;

public class Scania extends Vehicle {
    private double angle;
    private double amount;

    /**Describes the attributes of the truck
     *
     * @param position the trucks position
     * @param angle the angle of the flatbed
     * @param amount how much you want to lift or lower the flatbed
     */
    public Scania(Position position, double angle, double amount) {
        super(2, 125, Color.BLACK, "Scania", position, 5, 2,
                true);
        this.angle = angle;
        this.amount = amount;
    }


    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    /** Lifts the flatbed
     *
     * @param amount how much you want to life the flatbed
     */
    public void liftFlatbed(double amount){
        if(getCurrentSpeed() == 0 && angle > 0){
            decrementAngle(amount);
        }
    }

    /**Lowers the flatbed
     *
     * @param amount how much you want to lower the flatbed
     */

    public void lowerFlatbed(double amount){
        if(getCurrentSpeed() == 0 && angle == 0){
            incrementAngle(amount);
        }
    }


    /**decreases the angle of the flatbed
     *
     * @param amount how much you want to lower the flatbed
     */

    private void decrementAngle(double amount) {
        angle = Math.max(0, angle - amount);
    }


    /** Increases the angle of the flatbed
     *
     * @param amount how much you want to life the flatbed
     */
    private void incrementAngle(double amount){
        angle = Math.min(70, amount + angle);
    }


    @Override
    public double speedFactor(double enginePower) {
        return 0;
    }

    /**Checks if the trucks can move. If yes, it moves!
     *
     */

    @Override
    public void move(){
        if(canTruckMove()){
            super.move();
        }
    }

    /**Checks if the truck's flatbed is down or not. If it's down the truck can't move
     *
     * @return says if the truck can move or not
     */

    public boolean canTruckMove(){
        /*if(angle == 0 ){
            return true;
        }
        System.out.println("Cannot drive while flatbed is down");
        return false;*/
        return angle == 0;
        }
    }

