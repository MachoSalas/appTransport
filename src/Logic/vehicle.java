/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author Alejandro
 */
public class vehicle {

    private int enrollment;
    private String model;
    private String color;
    private int capacity_passenger;

    public vehicle() {
    }

    public vehicle(int enrollment, String model, String color, int capacity_passenger) {
        this.enrollment = enrollment;
        this.model = model;
        this.color = color;
        this.capacity_passenger = capacity_passenger;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCapacity_passenger() {
        return capacity_passenger;
    }

    public void setCapacity_passenger(int capacity_passenger) {
        this.capacity_passenger = capacity_passenger;
    }

}
