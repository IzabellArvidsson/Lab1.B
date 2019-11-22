package com.company;

import CarTransporter.Truck;
import Garage.Garage;
import Scania.Scania;
import org.junit.Test;


import static org.junit.Assert.*;

public class CarTest {

    private Position startingPosition = new Position(0,0);
    private Position carsPosition = new Position(0, 0.5);

//----------------------Volvo240 & Saab95-------------------
    @Test
    public void testMove(){
        Vehicle c = new Volvo240(startingPosition);
        c.move();
        assertTrue(startingPosition.getY() > 0);
    }


    @Test
    public void testTurnLeft(){
        Vehicle c = new Volvo240(startingPosition);
        c.turnLeft();

        assertSame(c.getCurrentDirection(), Vehicle.direction.WEST);
    }

    @Test
    public void testTurnRight(){
        Vehicle c = new Volvo240(startingPosition);
        c.turnRight();

        assertSame(c.getCurrentDirection(), Vehicle.direction.EAST);
    }

   @Test
    public void testGas(){
        Vehicle c = new Volvo240(startingPosition);
        double currentSpeed = c.getCurrentSpeed();

        c.gas(1);

        assertTrue(c.getCurrentSpeed() > currentSpeed );
    }

    @Test
    public void testBrake(){
        Vehicle c = new Volvo240(startingPosition);
        c.gas(1);
        double currentSpeed = c.getCurrentSpeed();
        c.brake(1);

        assertTrue(c.getCurrentSpeed()< currentSpeed);
    }

    @Test
    public void testStartEngine(){
        Vehicle c = new Volvo240(startingPosition);
        double currentSpeed = c.getCurrentSpeed();
        c.startEngine();

        assertTrue(c.getCurrentSpeed() > currentSpeed);
    }

    @Test
    public void testStopEngine(){
        Vehicle c = new Volvo240(startingPosition);
        c.gas(1);
        c.stopEngine();

        assertEquals(0, c.getCurrentSpeed(), 0.0);
    }

    @Test
    public void testSpeedFactorVolvo(){
        Vehicle c = new Volvo240(startingPosition);
        assertEquals(c.speedFactor(100), 1.25, 0.00001);
    }

    @Test
    public void testSpeedFactorSaab(){
        Vehicle c = new Saab95( startingPosition);
        assertEquals(c.speedFactor(125), 1.25, 0.00001 );
    }

    //------------------Scania----------------------

    @Test
    public void testLiftFlatbed(){
        Scania s = new Scania(startingPosition, 15, 20);
        s.liftFlatbed(20);

        assertTrue(s.getAngle() < 15);
    }

    @Test
    public void testLowerFlatbed(){
        Scania s = new Scania(startingPosition, 0, 20);
        s.lowerFlatbed(20);

        assertTrue(s.getAngle() > 0);
    }

    @Test
    public void testMoveScaniaP1(){
        Scania t = new Scania(startingPosition, 0, 10);

        t.move();
        assertTrue(t.getCurrentSpeed() > 0);
    }

    @Test
    public void testMoveScaniaP2(){
        Scania t = new Scania(startingPosition, 10, 10);

        t.move();
        assertEquals(0, t.getCurrentSpeed(), 0.0);
    }

    //-------------------Truck------------------------

    @Test
    public void testLoadTruck(){
        Truck truck = new Truck(startingPosition, 3, true );
        Volvo240 car = new Volvo240(startingPosition);

        truck.move();
        truck.loadCar(car);

        assertTrue(truck.carsInTruck.isEmpty());
    }

    @Test
    public void testLoadCarToTruck(){
        Truck truck = new Truck(startingPosition, 3, true );
        Volvo240 car = new Volvo240(carsPosition);

        truck.loadCar(car);

        assertFalse(truck.carsInTruck.isEmpty());
    }

    @Test
    public void testCarMoveInTruck(){
        Truck truck = new Truck(startingPosition, 3, true );
        Volvo240 car = new Volvo240(carsPosition);

        truck.loadCar(car);
        car.move();

        assertSame(car.getPosition(), truck.getPosition());
    }

    @Test
    public void testUnloadCarTruck(){
        Truck t = new Truck(startingPosition, 3, true);
        Volvo240 car = new Volvo240(carsPosition);

        t.loadCar(car);
        t.unloadCar();

        assertTrue(t.carsInTruck.isEmpty());
    }

    @Test
    public void testUnloadCarTruckMove(){
        Truck t = new Truck(startingPosition, 3, true);
        Volvo240 car = new Volvo240(carsPosition);

        t.loadCar(car);
        t.move();
        t.unloadCar();

        assertEquals(1, t.carsInTruck.size());
    }


    //-----------------------Garage------------------------

    @Test
    public void testLoadCarToGarage(){
        Garage <Volvo240> g = new Garage<Volvo240>(3);
        Volvo240 car = new Volvo240(startingPosition);
        g.loadCarToGarage(car);

        assertTrue(g.carsInGarage.size() > 0);
    }

/*
    @Test
    public void testLoadWrongCarToGarage(){
        Garage <Volvo240> g = new Garage <Volvo240>(3);
        Vehicle car = new Saab95(startingPosition);

        g.loadCarToGarage(car);
        assertEquals(0, g.carsInGarage.size());
    }*/

    @Test
    public void testUnloadCarFromGarage(){
        Garage <Vehicle> g = new Garage<>(5);
        Saab95 car = new Saab95(startingPosition);
        g.loadCarToGarage(car);

        g.unloadCarFromGarage(car);
        assertEquals(g.carsInGarage.size(), 0);
    }
}
