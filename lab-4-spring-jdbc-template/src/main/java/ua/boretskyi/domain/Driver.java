package ua.boretskyi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Driver {
    private Integer id;
    private String name;
    private String surname;
    private Integer companyId;
    private String phoneNumber;
}
