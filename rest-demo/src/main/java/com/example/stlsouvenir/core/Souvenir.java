package com.example.stlsouvenir.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table( name = "SOUVENIR" )
public class Souvenir {
    private long id;
    private String name;
    private String description;
    private int totalSold;
    private int numInStock;

    public Souvenir() {}

    public void setId(long id) {
        this.id = id;
    }    

    @Id
    @JsonProperty
    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }   

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    @JsonProperty
    @Column (name = "TOTAL_SOLD")
    public int getTotalSold() {
        return totalSold;
    }

    public void setNumInStock(int numInStock) {
        this.numInStock = numInStock;
    }

    @JsonProperty
    @Column (name = "NUM_IN_STOCK")
    public int getNumInStock() {
        return numInStock;
    }
}
