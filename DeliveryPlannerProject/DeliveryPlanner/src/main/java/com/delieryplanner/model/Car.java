package com.delieryplanner.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Car {
    @Id
    @GeneratedValue(generator="my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=1)
    private Long id = null;
    private int capacity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Warehouse warehouse;


    public Car(int capacity, Warehouse warehouse) {
        this.capacity = capacity;
        this.warehouse = warehouse;
    }
}
