package db.clasess;

public class Ticket {
    private Long id;
    private String fromCity;
    private String toCity;
    private int price;
    private int duration;

    public Ticket() {
    }

    public Ticket(String fromCity, String toCity, int price, int duration) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.price = price;
        this.duration = duration;
    }

    public Ticket(Long id, String fromCity, String toCity, int price, int duration) {
        this.id = id;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.price = price;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    @Override
    public java.lang.String toString() {
        return "Ticket{" +
                "id=" + id +
                ", fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                ", price=" + price +
                ", duration=" + duration;
    }
}
