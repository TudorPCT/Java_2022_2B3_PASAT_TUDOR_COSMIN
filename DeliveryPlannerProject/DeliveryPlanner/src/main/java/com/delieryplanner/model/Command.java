package com.delieryplanner.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Command {
    @Id
    @GeneratedValue(generator="my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=1)
    private Long id = null;

    private Double latitude;

    private Double longitude;

    private int capacity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Warehouse warehouse;

    public Command(double latitude, double longitude, int capacity, Warehouse warehouse) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
        this.warehouse = warehouse;
    }
}
