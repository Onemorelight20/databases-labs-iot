package ua.boretskyi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class VehicleType {
    private Integer id;
    private String type;

    public static VehicleType nullObject() {
        return new VehicleType(null, null);
    }
}
