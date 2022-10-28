package ua.boretskyi.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "medical_info", schema = "bohdan_boretskyi_smartcap")
public class MedicalInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Basic
    @Column(name = "driver_id")
    private Integer driverId;
    @Basic
    @Column(name = "sight_state")
    private String sightState;
    @Basic
    @Column(name = "blood_type")
    private String bloodType;
    @Basic
    @Column(name = "updated_at")
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getSightState() {
        return sightState;
    }

    public void setSightState(String sightState) {
        this.sightState = sightState;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalInfoEntity that = (MedicalInfoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(driverId, that.driverId) && Objects.equals(sightState, that.sightState) && Objects.equals(bloodType, that.bloodType) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driverId, sightState, bloodType, updatedAt);
    }
}
