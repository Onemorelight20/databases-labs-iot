package ua.boretskyi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Location {
    private Integer id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Timestamp recordTime;
    private Integer vehicleId;

    public static Location nullObject(){
        return new Location(null, null, null, null, null);
    }
}
