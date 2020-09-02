package db.classes;

public class Car {
    private Long id;
    private String name;
    private String model;
    private int year;
    private int price;

    public Car(Long id, String name, String model, int year, int price) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public Car(String name, String model, int year, int price) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public Car() {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
