package ua.boretskyi.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
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
