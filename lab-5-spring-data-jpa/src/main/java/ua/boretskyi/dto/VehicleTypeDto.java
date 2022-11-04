package ua.boretskyi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "vehicleType", collectionRelation = "vehicleTypes")
public class VehicleTypeDto extends RepresentationModel<VehicleTypeDto> {
    private Integer id;
    private String type;
}
