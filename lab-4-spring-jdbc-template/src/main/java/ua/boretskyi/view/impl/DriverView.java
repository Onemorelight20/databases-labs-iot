package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.DriverController;
import ua.boretskyi.model.Driver;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.CommandExplanation.FIND_BY;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class DriverView implements GeneralView {

    private final DriverController driverController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Driver nullObject = Driver.nullObject();
    private static final String TABLE_NAME = "Driver";
    private static final int BASE_NUM = 12;

    public DriverView(DriverController driverController) {
        this.driverController = driverController;

        menu = new LinkedHashMap<>();

        menu.put(String.valueOf(BASE_NUM), String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(),  BASE_NUM + "1", TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(),  BASE_NUM + "2", TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(),  BASE_NUM + "3", TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(),  BASE_NUM + "4", TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(),  BASE_NUM + "5", TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(),  BASE_NUM + "6", TABLE_NAME, "phoneNumber"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createDriver);
        methodsMenu.put(BASE_NUM + "2", this::updateDriver);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromDriver);
        methodsMenu.put(BASE_NUM + "4", this::findAllDrivers);
        methodsMenu.put(BASE_NUM + "5", this::findDriverById);
        methodsMenu.put(BASE_NUM + "6", this::findDriverByPhoneNumber);
    }

    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createDriver() {
        Driver driver = this.getObjectFromConsole();
        Optional<Driver> result =driverController.create(driver);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }
    private void updateDriver() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        Driver driver = this.getObjectFromConsole();
        Optional<Driver> result = driverController.update(id, driver);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_UPDATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_UPDATED.getText(), TABLE_NAME);
        });
    }
    private void deleteFromDriver() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        if (driverController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }
    private void findAllDrivers() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<Driver> drivers = driverController.findAll();
        drivers.forEach(System.out::println);
    }
    private void findDriverById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        Optional<Driver> result = driverController.findById(id);
        System.out.println(result.orElse(nullObject));
    }
    private void findDriverByPhoneNumber() {
        System.out.printf(ENTER_FIELD.getText(), "phoneNumber");
        String phoneNumber = input.nextLine();

        Optional<Driver> result = driverController.findByPhoneNumber(phoneNumber);
        System.out.println(result.orElse(nullObject));
    }

    private Driver getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "name");
        String name = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "surname");
        String surname = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "companyId");
        Integer companyId = Integer.parseInt(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "phoneNumber");
        String phoneNumber = input.nextLine();
        return new Driver(null, name, surname, companyId, phoneNumber);
    }
}
