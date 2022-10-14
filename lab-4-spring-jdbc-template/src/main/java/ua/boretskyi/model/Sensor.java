package ua.boretskyi.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Sensor {
    private Integer id;
    private String brand;
    private String model;
    private Date dateInstalled;
    private Integer vehicleId;

    public static Sensor nullObject() {
        return new Sensor(null, null, null, null, null);
    }
}
