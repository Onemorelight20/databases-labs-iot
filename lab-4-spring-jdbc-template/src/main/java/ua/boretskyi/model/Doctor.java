package ua.boretskyi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Doctor {
    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;

    public static Doctor nullObject() {
        return new Doctor(null, null, null, null);
    }
}
