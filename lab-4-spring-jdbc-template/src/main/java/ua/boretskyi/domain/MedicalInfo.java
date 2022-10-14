package ua.boretskyi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@NoArgsConstructor
@Data
public class MedicalInfo {
    private Integer id;
    private Integer driverId;
    private String sightState;
    private String bloodType;
    private Integer doctorId;
    private Date updatedAt;
}
