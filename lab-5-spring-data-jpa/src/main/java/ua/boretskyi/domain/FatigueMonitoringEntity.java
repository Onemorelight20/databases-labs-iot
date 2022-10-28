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

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private DriverEntity driver;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private VehicleEntity vehicle;
    @ManyToOne
    @JoinColumn(name = "mine_sight_id", referencedColumnName = "id")
    private MineSightEntity mineSight;
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

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public MineSightEntity getMineSight() {
        return mineSight;
    }

    public void setMineSight(MineSightEntity mineSight) {
        this.mineSight = mineSight;
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
        return Objects.equals(id, that.id) && Objects.equals(driver, that.driver) && Objects.equals(vehicle, that.vehicle) && Objects.equals(mineSight, that.mineSight) && Objects.equals(fatigueLevelTitle, that.fatigueLevelTitle) && Objects.equals(recordTime, that.recordTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driver, vehicle, mineSight, fatigueLevelTitle, recordTime);
    }
}
