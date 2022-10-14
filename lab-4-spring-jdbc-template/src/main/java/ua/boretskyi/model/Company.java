package ua.boretskyi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Company {
    private Integer id;
    private String title;

    public static Company nullObject() {
        return new Company(null, null);
    }
}
