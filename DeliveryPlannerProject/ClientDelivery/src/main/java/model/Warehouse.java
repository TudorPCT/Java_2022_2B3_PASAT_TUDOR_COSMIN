package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Warehouse {

    private Long id;
    private String town;
    private Double latitude;
    private Double longitude;

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", town='" + town + '\'' +
                '}';
    }
}
