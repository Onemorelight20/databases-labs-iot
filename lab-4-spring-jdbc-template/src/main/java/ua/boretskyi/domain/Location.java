package ua.boretskyi.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Location {
    private Integer id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Timestamp recordTime;
    private Integer vehicleId;
}
