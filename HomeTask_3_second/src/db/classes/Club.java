package db.classes;

public class Club {
    private Long id;
    private String name;
    private String description;
    private int foundedYear;
    private City city;

    public Club() {
    }

    public Club(String name, String description, int foundedYear, City city) {
        this.name = name;
        this.description = description;
        this.foundedYear = foundedYear;
        this.city = city;
    }

    public Club(Long id, String name, String description, int foundedYear, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.foundedYear = foundedYear;
        this.city = city;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
