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
@Relation(itemRelation = "medicalInfo", collectionRelation = "medicalInfos")
public class MedicalInfoDto extends RepresentationModel<MedicalInfoDto> {
    private Integer id;
    private String driverPhoneNumber;
    private String sightState;
    private String bloodType;
    private Date updatedAt;
}
