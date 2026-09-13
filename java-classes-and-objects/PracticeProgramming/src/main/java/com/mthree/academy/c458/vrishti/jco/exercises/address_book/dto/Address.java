package com.mthree.academy.c458.vrishti.jco.exercises.address_book.dto;

/**
 * Domain Object
 */
public class Address {

    //Attributes
    private long id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postCode;

    //Constructors
    public Address() {}
    public Address(long id) {
        this.id = id;
    }

    //Getter Methods
    public long getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getStreet() {return street;}
    public String getCity() {return city;}
    public String getState() {return state;}
    public String getCountry() {return country;}
    public String getPostCode() {return postCode;}

    //Setter Methods
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setStreet(String street) {this.street = street;}
    public void setCity(String city) {this.city = city;}
    public void setState(String state) {this.state = state;}
    public void setCountry(String country) {this.country = country;}
    public void setPostCode(String postCode) {this.postCode = postCode;}

}
