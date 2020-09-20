package classes;

import java.sql.Date;

public class Student {
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private Date birthdate; // java.util.Date
    private String iin;
    private boolean isGrant;
    private String specialty;

    public Student() {
    }

    public Student(String name, String surname, String middleName, Date birthdate, String iin, boolean isGrant, String specialty) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.birthdate = birthdate;
        this.iin = iin;
        this.isGrant = isGrant;
        this.specialty = specialty;
    }

    public Student(Long id, String name, String surname, String middleName, Date birthdate, String iin, boolean isGrant, String specialty) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.birthdate = birthdate;
        this.iin = iin;
        this.isGrant = isGrant;
        this.specialty = specialty;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public boolean isGrant() {
        return isGrant;
    }

    public void setGrant(boolean grant) {
        isGrant = grant;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
