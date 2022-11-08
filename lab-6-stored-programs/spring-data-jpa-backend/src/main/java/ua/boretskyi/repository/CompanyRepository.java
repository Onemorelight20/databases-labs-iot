package ua.boretskyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.boretskyi.domain.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
    @Procedure("insert_10_records_into_company_table")
    void insertTenCompanies();

    @Procedure("companies_ids_sum")
    Integer getCompaniesIdSum();

    @Procedure("create_2_tables_and_insert_data_dinamically")
    void createTwoTablesAndInsertDataDynamically();
}
