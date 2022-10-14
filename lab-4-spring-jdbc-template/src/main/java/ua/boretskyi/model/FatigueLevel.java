package ua.boretskyi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class FatigueLevel {
    private String levelTitle;

    public static FatigueLevel nullObject() {
        return new FatigueLevel(null);
    }
}
