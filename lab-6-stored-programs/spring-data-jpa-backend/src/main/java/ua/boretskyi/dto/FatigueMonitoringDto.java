package ua.boretskyi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import ua.boretskyi.domain.DriverEntity;
import ua.boretskyi.domain.MineSightEntity;
import ua.boretskyi.domain.VehicleEntity;

import java.sql.Timestamp;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "fatigueMonitoring", collectionRelation = "fatigueMonitorings")
public class FatigueMonitoringDto extends RepresentationModel<FatigueMonitoringDto> {
    private Integer id;
    private Integer driverId;
    private Integer vehicleId;
    private Integer mineSightId;
    private String fatigueLevelTitle;
    private Timestamp recordTime;
}
