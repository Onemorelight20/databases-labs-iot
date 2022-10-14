package ua.boretskyi.domain;

import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FatigueMonitoring {
    private Integer id;
    private Integer driverId;
    private Integer workShiftId;
    private Boolean isCritical;
    private String fatigueLevelTitle;
    private Integer locationId;
    private Integer vehicleSpeedKmPerH;
    private Timestamp recordTime;
}
