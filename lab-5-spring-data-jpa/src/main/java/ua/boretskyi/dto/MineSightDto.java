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
@Relation(itemRelation = "mineSight", collectionRelation = "mineSights")
public class MineSightDto extends RepresentationModel<MineSightDto> {
    private Integer id;
    private String country;
    private String city;
    private String title;
}
