package com.mustgo.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String city;
    private String district;
    private String dong;

    protected Address(){
    }

    public Address(String city, String district, String dong) {
        this.city = city;
        this.district = district;
        this.dong = dong;
    }
}
