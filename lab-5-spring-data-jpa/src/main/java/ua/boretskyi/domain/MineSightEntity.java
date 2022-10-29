package ua.boretskyi.domain;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "mine_sight", schema = "bohdan_boretskyi_smartcap")
public class MineSightEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "mineSightEntities", fetch = FetchType.LAZY)
    private Set<CompanyEntity> companyEntitySet;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<CompanyEntity> getCompanyEntitySet() {
        return companyEntitySet;
    }

    public void setCompanyEntitySet(Set<CompanyEntity> companyEntitySet) {
        this.companyEntitySet = companyEntitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MineSightEntity that = (MineSightEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(country, that.country) && Objects.equals(city, that.city) && Objects.equals(title, that.title) && Objects.equals(companyEntitySet, that.companyEntitySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, title, companyEntitySet);
    }
}
