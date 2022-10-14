package ua.boretskyi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Doctor {
    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;
}
