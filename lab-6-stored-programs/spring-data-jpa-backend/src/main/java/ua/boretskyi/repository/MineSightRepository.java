package ua.boretskyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.boretskyi.domain.MineSightEntity;

@Repository
public interface MineSightRepository extends JpaRepository<MineSightEntity, Integer> {

    @Procedure("insert_into_mine_sight")
    Integer insertWithProcedure(@Param("country_p") String country, @Param("city_p") String city, @Param("title_p") String title);
}
