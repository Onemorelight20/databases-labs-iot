package ua.boretskyi.domain;

import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "fatigue_monitoring", schema = "bohdan_boretskyi_smartcap")
@ToString
public class FatigueMonitoringEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Basic
    @Column
    private Integer driverId;

    @Basic
    @Column
    private Integer vehicleId;
    @Basic
    @Column
    private Integer mineSightId;

    @Column
    private String fatigueLevelTitle;
    @Basic
    @Column(name = "record_time")
    private Timestamp recordTime;

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

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getMineSightId() {
        return mineSightId;
    }

    public void setMineSightId(Integer mineSightId) {
        this.mineSightId = mineSightId;
    }

    public String getFatigueLevelTitle() {
        return fatigueLevelTitle;
    }

    public void setFatigueLevelTitle(String fatigueLevelTitle) {
        this.fatigueLevelTitle = fatigueLevelTitle;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FatigueMonitoringEntity that = (FatigueMonitoringEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(driverId, that.driverId) && Objects.equals(vehicleId, that.vehicleId) && Objects.equals(mineSightId, that.mineSightId) && Objects.equals(fatigueLevelTitle, that.fatigueLevelTitle) && Objects.equals(recordTime, that.recordTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driverId, vehicleId, mineSightId, fatigueLevelTitle, recordTime);
    }
}
