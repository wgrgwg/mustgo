package com.mustgo.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String city;
    private String district;
    private String street;
    private String zipcode;

    protected Address(){
    }

    public Address(String city, String district, String street, String zipcode) {
        this.city = city;
        this.district = district;
        this.street = street;
        this.zipcode = zipcode;
    }
}
