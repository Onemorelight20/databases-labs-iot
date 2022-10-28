package ua.boretskyi.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "fatigue_monitoring", schema = "bohdan_boretskyi_smartcap")
public class FatigueMonitoringEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Basic
    @Column(name = "driver_id")
    private Integer driverId;
    @Basic
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    @Basic
    @Column(name = "mine_sight_id")
    private Integer mineSightId;
    @Basic
    @Column(name = "fatigue_level_title")
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
