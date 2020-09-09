package db.classes;

import interfaces.Transports;

public class Car implements Transports {
    private Long id;
    private String name;
    private String model;
    private String carcaseType; // sedan, hatchback, cabriolet etc..
    private int maxSpeed;
    private double engineVolume;
    private int price;
    private int year;

    public Car() {
    }

    public Car(String name, String model, String carcaseType, int maxSpeed, double engineVolume, int price, int year) {
        this.name = name;
        this.model = model;
        this.carcaseType = carcaseType;
        this.maxSpeed = maxSpeed;
        this.engineVolume = engineVolume;
        this.price = price;
        this.year = year;
    }

    public Car(Long id, String name, String model, String carcaseType, int maxSpeed, double engineVolume, int price, int year) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.carcaseType = carcaseType;
        this.maxSpeed = maxSpeed;
        this.engineVolume = engineVolume;
        this.price = price;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarcaseType() {
        return carcaseType;
    }

    public void setCarcaseType(String carcaseType) {
        this.carcaseType = carcaseType;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Interface methods
    public String getTransportName(){
        return this.model + "-" + this.name;
    }
    public String getTransportDescription(){
        return this.carcaseType + ", from " +
                this.year + " year, with max speed: " +
                this.maxSpeed + " km/h, " +
                this.engineVolume + " liters";
    }
    public int getTransportPrice(){
        return this.price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", carcaseType='" + carcaseType + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", engineVolume=" + engineVolume +
                ", price=" + price +
                ", year=" + year +
                '}';
    }
}
