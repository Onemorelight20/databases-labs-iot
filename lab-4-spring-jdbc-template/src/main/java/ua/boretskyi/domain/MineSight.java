package ua.boretskyi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MineSight {
    private Integer id;
    private String country;
    private String city;
    private String title;
    private Integer areaInSquareMeters;
}
