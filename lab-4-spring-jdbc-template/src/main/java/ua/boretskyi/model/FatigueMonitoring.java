package ua.boretskyi.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class FatigueMonitoring {
    private Integer id;
    private Integer driverId;
    private Integer workShiftId;
    private Boolean isCritical;
    private String fatigueLevelTitle;
    private Integer locationId;
    private Integer vehicleSpeedKmPerH;
    private Timestamp recordTime;

    public static FatigueMonitoring nullObject() {
        return new FatigueMonitoring(
                null, null, null,
                null, null, null,
                null, null
        );
    }
}
