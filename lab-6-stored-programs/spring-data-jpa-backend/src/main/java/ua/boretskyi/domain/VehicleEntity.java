package ua.boretskyi.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "vehicle", schema = "bohdan_boretskyi_smartcap")
public class VehicleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Basic
    @Column(name = "brand")
    private String brand;
    @Basic
    @Column(name = "model")
    private String model;
    @Basic
    @Column(name = "manufacturing_date")
    private Date manufacturingDate;
    @Basic
    @Column(name = "serial_number")
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id", referencedColumnName = "id")
    private VehicleTypeEntity vehicleType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public VehicleTypeEntity getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeEntity vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleEntity that = (VehicleEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(brand, that.brand) && Objects.equals(model, that.model) && Objects.equals(manufacturingDate, that.manufacturingDate) && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(vehicleType, that.vehicleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, manufacturingDate, serialNumber, vehicleType);
    }
}
