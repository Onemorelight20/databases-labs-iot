package ua.boretskyi.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fatigue_level", schema = "bohdan_boretskyi_smartcap")
public class FatigueLevelEntity {
    @Id
    @Column(name = "level_title")
    private String levelTitle;

    public String getLevelTitle() {
        return levelTitle;
    }

    public void setLevelTitle(String levelTitle) {
        this.levelTitle = levelTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FatigueLevelEntity that = (FatigueLevelEntity) o;
        return Objects.equals(levelTitle, that.levelTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelTitle);
    }
}
