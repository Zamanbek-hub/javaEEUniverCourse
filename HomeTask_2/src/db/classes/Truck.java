package db.classes;

import interfaces.Transports;

public class Truck implements Transports {
    private Long id;
    private String name;
    private String model;
    private double liftingCapacity;
    private int price;
    private int trailerPrice;

    public Truck() {
    }

    public Truck(String name, String model, double liftingCapacity, int price, int trailerPrice) {
        this.name = name;
        this.model = model;
        this.liftingCapacity = liftingCapacity;
        this.price = price;
        this.trailerPrice = trailerPrice;
    }

    public Truck(Long id, String name, String model, double liftingCapacity, int price, int trailerPrice) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.liftingCapacity = liftingCapacity;
        this.price = price;
        this.trailerPrice = trailerPrice;
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

    public double getLiftingCapacity() {
        return liftingCapacity;
    }

    public void setLiftingCapacity(double liftingCapacity) {
        this.liftingCapacity = liftingCapacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTrailerPrice() {
        return trailerPrice;
    }

    public void setTrailerPrice(int trailerPrice) {
        this.trailerPrice = trailerPrice;
    }

    // Interface methods
    public String getTransportName(){
        return this.model + "-" + this.name;
    }
    public String getTransportDescription(){
        return "This truck can lift " + this.liftingCapacity + " KG" +
                ", with trailer price " + this.price;
    }
    public int getTransportPrice(){
        return this.price;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", liftingCapacity=" + liftingCapacity +
                ", price=" + price +
                ", trailerPrice=" + trailerPrice +
                '}';
    }
}
