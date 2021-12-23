package fr.uge.jee.hibernate.students;


import javax.persistence.*;
import java.util.Objects;

//@Entity
//@Table(name = "Addresses")
@Embeddable
public class Address {

    //@Id
    //@GeneratedValue
    //*@Column(name = "ADDRESSID")
    //private long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STREET_NUMBER")
    private int streetNumber;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIP_CODE")
    private int zipCode;

    public Address(){} // for Hibernate

    public Address(String address, String city, int zipCode, int streetNumber){
        this.address = Objects.requireNonNull(address);
        this.city = Objects.requireNonNull(city);
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }


    public String getAddress() {
        return address;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }
}
