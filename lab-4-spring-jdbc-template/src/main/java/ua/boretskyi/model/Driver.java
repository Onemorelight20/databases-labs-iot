package ua.boretskyi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Driver {
    private Integer id;
    private String name;
    private String surname;
    private Integer companyId;
    private String phoneNumber;

    public static Driver nullObject() {
        return new Driver(null, null, null, null, null);
    }
}
