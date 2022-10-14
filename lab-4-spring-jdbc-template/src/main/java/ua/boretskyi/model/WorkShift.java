package ua.boretskyi.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class WorkShift {
    private Integer id;
    private Integer driverId;
    private Integer vehicleId;
    private Integer medicalInfoId;
    private Integer mineSightId;
    private Timestamp beginAt;
    private Timestamp endAt;

    public static WorkShift nullObject() {
        return new WorkShift(null, null, null, null, null, null, null);
    }
}
