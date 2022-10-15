package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.VehicleController;
import ua.boretskyi.model.Vehicle;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.sql.Date;
import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.CommandExplanation.FIND_BY;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class VehicleView implements GeneralView {

    private final VehicleController vehicleController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Vehicle nullObject = Vehicle.nullObject();
    private static final String TABLE_NAME = "Vehicle";
    private static final int BASE_NUM = 20;

    public VehicleView(VehicleController vehicleController) {
        this.vehicleController = vehicleController;

        menu = new LinkedHashMap<>();

        menu.put(String.valueOf(BASE_NUM), String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(), BASE_NUM + "1", TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(), BASE_NUM + "2", TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(), BASE_NUM + "3", TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(), BASE_NUM + "4", TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(), BASE_NUM + "5", TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(), BASE_NUM + "6", TABLE_NAME, "serialNumber"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createVehicle);
        methodsMenu.put(BASE_NUM + "2", this::updateVehicle);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromVehicle);
        methodsMenu.put(BASE_NUM + "4", this::findAllVehicles);
        methodsMenu.put(BASE_NUM + "5", this::findVehicleById);
        methodsMenu.put(BASE_NUM + "6", this::findVehicleBySerialNumber);
    }


    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createVehicle() {
        Vehicle vehicle = this.getObjectFromConsole();
        Optional<Vehicle> result = vehicleController.create(vehicle);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void updateVehicle() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        Vehicle vehicle = this.getObjectFromConsole();
        Optional<Vehicle> result = vehicleController.update(id, vehicle);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void deleteFromVehicle() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        if (vehicleController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }

    private void findVehicleBySerialNumber() {
        System.out.printf(ENTER_FIELD.getText(), "serial_number");
        String serialNumber = input.nextLine();

        Optional<Vehicle> result = vehicleController.findBySerialNumber(serialNumber);
        System.out.println(result.orElse(nullObject));
    }

    private void findVehicleById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        Integer id = Integer.parseInt(input.nextLine());

        Optional<Vehicle> result = vehicleController.findById(id);
        System.out.println(result.orElse(nullObject));
    }

    private void findAllVehicles() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<Vehicle> infos = vehicleController.findAll();
        infos.forEach(System.out::println);
    }

    private Vehicle getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "brand");
        String brand = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "model");
        String model = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "manufacturing_date");
        Date manufacturingDate = Date.valueOf(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "licence_plate_number");
        String licencePlateNumber = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "serial_number");
        String serialNumber = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "vehicle_type_id");
        Integer vehicleTypeId = Integer.parseInt(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "weight_in_kilos");
        Integer weightInKilos = Integer.parseInt(input.nextLine());
        return new Vehicle(null, brand, model, manufacturingDate, licencePlateNumber, serialNumber, vehicleTypeId, weightInKilos);
    }
}
