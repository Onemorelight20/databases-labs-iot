package ua.boretskyi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Date;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "vehicle", collectionRelation = "vehicles")
public class VehicleDto extends RepresentationModel<VehicleDto> {
    private Integer id;
    private String brand;
    private String model;
    private Date manufacturingDate;
    private String serialNumber;
    private String vehicleType;
}
