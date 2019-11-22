package com.company;

public class Position {
    private double x;
    private double y;

    /**Sets the position of an object.
     *
     * @param x - the x coordinate
     * @param y - the y coordinate
     */

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

}
