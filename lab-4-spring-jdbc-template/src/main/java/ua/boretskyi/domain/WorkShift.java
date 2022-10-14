package ua.boretskyi.domain;

import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WorkShift {
    private Integer id;
    private Integer driverId;
    private Integer vehicleId;
    private Integer medicalInfoId;
    private Integer mineSightId;
    private Timestamp beginAt;
    private Timestamp endAt;
}
