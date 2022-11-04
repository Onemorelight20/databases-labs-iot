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
@Relation(itemRelation = "driver", collectionRelation = "drivers")
public class DriverDto extends RepresentationModel<DriverDto> {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String companyName;
    private final String phoneNumber;
}
