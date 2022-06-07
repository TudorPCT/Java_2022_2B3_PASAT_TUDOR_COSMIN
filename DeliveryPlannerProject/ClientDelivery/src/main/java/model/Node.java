package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Node {

    Long id;
    Double latitude;
    Double longitude;
    int capacity;

    @Override
    public String toString() {
        return  id +
                ". latitude=" + latitude +
                ", longitude=" + longitude +
                ", capacity=" + capacity;
    }
}
