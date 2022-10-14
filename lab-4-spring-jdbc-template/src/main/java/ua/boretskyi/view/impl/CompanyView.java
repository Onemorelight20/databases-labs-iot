package ua.boretskyi.view.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.CompanyController;
import ua.boretskyi.model.Company;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;
import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.Message.*;

import java.util.*;

@Component
public class CompanyView implements GeneralView {
    private final CompanyController companyController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Company nullObject = Company.nullObject();
    private static final String TABLE_NAME = "Company";


    @Autowired
    public CompanyView(CompanyController companyController) {
        this.companyController = companyController;

        menu = new LinkedHashMap<>();
        menu.put("1", String.format(TABLE_SELECT.getExplanation(), 1, TABLE_NAME));
        menu.put("11", String.format(CREATE_RECORD.getExplanation(), 11, TABLE_NAME));
        menu.put("12", String.format(UPDATE_RECORD.getExplanation(), 12, TABLE_NAME));
        menu.put("13", String.format(DELETE_RECORD.getExplanation(), 13, TABLE_NAME));
        menu.put("14", String.format(FIND_ALL.getExplanation(), 14, TABLE_NAME));
        menu.put("15", String.format(FIND_BY.getExplanation(), 15, TABLE_NAME, "ID"));
        menu.put("16", String.format(FIND_BY.getExplanation(), 15, TABLE_NAME, "title"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put("11", this::createCompany);
        methodsMenu.put("12", this::updateCompany);
        methodsMenu.put("13", this::deleteFromCompany);
        methodsMenu.put("14", this::findAllCompanies);
        methodsMenu.put("15", this::findCompanyById);
        methodsMenu.put("16", this::findCompanyByTitle);
    }

    public Map<String, String> getMenu() {
        return menu;
    }

    public Map<String, Printable> getMethodsMenu() { return methodsMenu; }

    private void createCompany() {
        System.out.printf(ENTER_FIELD.getText(), "title");
        String title = input.nextLine();
        Company company = new Company(null, title);
        Optional<Company> result = companyController.create(company);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void updateCompany() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        System.out.printf(ENTER_NEW_FIELD.getText(), "title");
        String title = input.nextLine();
        Company company = new Company(null, title);
        Optional<Company> result = companyController.update(id, company);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_UPDATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_UPDATED.getText(), TABLE_NAME);
        });
    }

    private void deleteFromCompany() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        if (companyController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }

    private void findAllCompanies() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<Company> companies = companyController.findAll();
        companies.forEach(System.out::println);
    }

    private void findCompanyById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        Optional<Company> result = companyController.findById(id);
        System.out.println(result.orElse(nullObject));
    }

    private void findCompanyByTitle() {
        System.out.printf(ENTER_FIELD.getText(), "title");
        String title = input.nextLine();

        Optional<Company> result = companyController.findByTitle(title);
        System.out.println(result.orElse(nullObject));
    }
}
