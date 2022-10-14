package ua.boretskyi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MineSight {
    private Integer id;
    private String country;
    private String city;
    private String title;
    private Integer areaInSquareMeters;
}
