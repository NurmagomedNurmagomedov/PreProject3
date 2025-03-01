package model;



public class Car {
    String model;
    int year;
    double speed;

    public void accelerate(double amount) {
        speed += amount;
    }
}
