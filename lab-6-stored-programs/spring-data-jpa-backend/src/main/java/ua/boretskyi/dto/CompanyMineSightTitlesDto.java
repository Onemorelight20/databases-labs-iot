package ua.boretskyi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "driver", collectionRelation = "drivers")
public class CompanyMineSightTitlesDto {
    private final String companyTitle;
    private final String mineSightTile;
}
