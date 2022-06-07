package com.delieryplanner.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString
@Getter
public class Warehouse {
    @Id
    @GeneratedValue(generator="my_seq1")
    @SequenceGenerator(name="my_seq1",sequenceName="MY_SEQ1", allocationSize=1)
    private Long id = null;

    private String town;

    private Double latitude;
    private Double longitude;

    @OneToMany(
            mappedBy = "id",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Car> cars = new ArrayList<>();


    public Warehouse(String town, Double latitude, Double longitude)
    {
        this.town=town;
        this.cars = null;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
