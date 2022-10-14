package ua.boretskyi.domain;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Vehicle {
    private Integer id;
    private String brand;
    private String model;
    private Date manufacturingDate;
    private String licencePlateNumber;
    private String serialNumber;
    private Integer vehicleTypeId;
    private Integer weightInKilos;
}
