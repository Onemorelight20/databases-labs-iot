package ua.boretskyi.domain;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Sensor {
    private Integer id;
    private String brand;
    private String model;
    private Date dateInstalled;
    private Integer vehicleId;
}
