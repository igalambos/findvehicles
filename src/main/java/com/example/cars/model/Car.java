package com.example.cars.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String make;

    @Basic(optional = false)
    @Column(nullable = false)
    private String model;

    @Basic(optional = false)
    @Column(nullable = false)
    private Long mileage;

    @Basic(optional = false)
    @Column(nullable = false)
    private Long term;

    @OneToMany(
            mappedBy = "car",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Price> prices = new ArrayList<>();

    @OneToMany(
            mappedBy = "car",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<OtherInformation> otherInformation = new ArrayList<>();

    public void addPrice(Price price) {
        this.prices.add(price);
        price.setCar(this);
    }

    public void addOtherInformation(OtherInformation otherInformation) {
        this.otherInformation.add(otherInformation);
        otherInformation.setCar(this);
    }

    public Car() {
    }

    public Car(String make, String model, Long mileage, Long term) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.term = term;
    }
}
