package ua.boretskyi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MedicalInfo {
    private Integer id;
    private Integer driverId;
    private String sightState;
    private String bloodType;
    private Integer doctorId;
    private Date updatedAt;
}
