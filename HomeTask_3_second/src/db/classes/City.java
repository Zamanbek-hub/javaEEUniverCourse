package db.classes;

public class City {
    private Long id;
    private String name;
    private League league;

    public City() {
    }

    public City(String name, League league) {
        this.name = name;
        this.league = league;
    }

    public City(Long id, String name, League league) {
        this.id = id;
        this.name = name;
        this.league = league;
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
