package ua.boretskyi.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "company", schema = "bohdan_boretskyi_smartcap")
public class CompanyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Basic
    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "company_mine_sight",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "mine_sight_id"))
    private Set<MineSightEntity> mineSightEntities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<MineSightEntity> getMineSightEntities() {
        return mineSightEntities;
    }

    public void setMineSightEntities(Set<MineSightEntity> mineSightEntities) {
        this.mineSightEntities = mineSightEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyEntity that = (CompanyEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
