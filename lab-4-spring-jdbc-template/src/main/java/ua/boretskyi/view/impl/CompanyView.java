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
    private static final int BASE_NUM = 10;


    @Autowired
    public CompanyView(CompanyController companyController) {
        this.companyController = companyController;

        menu = new LinkedHashMap<>();
        menu.put("10", String.format(TABLE_SELECT.getExplanation(), 10, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(), BASE_NUM + "1", TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(), BASE_NUM + "2", TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(),  BASE_NUM + "3", TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(),  BASE_NUM + "4", TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(), BASE_NUM + "5", TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(),  BASE_NUM + "6", TABLE_NAME, "title"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createCompany);
        methodsMenu.put(BASE_NUM + "2", this::updateCompany);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromCompany);
        methodsMenu.put(BASE_NUM + "4", this::findAllCompanies);
        methodsMenu.put(BASE_NUM + "5", this::findCompanyById);
        methodsMenu.put(BASE_NUM + "6", this::findCompanyByTitle);
    }

    public Map<String, String> getMenu() {
        return menu;
    }

    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

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
