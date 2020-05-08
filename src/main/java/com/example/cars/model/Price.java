package com.example.cars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "prices")
public class Price extends BaseEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private Float amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Car car;

    public Price() {
    }

    public Price(Float amount) {
        this.amount = amount;
    }
}
