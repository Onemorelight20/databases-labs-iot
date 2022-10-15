package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.SensorController;
import ua.boretskyi.model.Sensor;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.sql.Date;
import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.CommandExplanation.FIND_BY;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class SensorView implements GeneralView {

    private final SensorController sensorController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Sensor nullObject = Sensor.nullObject();
    private static final String TABLE_NAME = "Sensor";
    private static final int BASE_NUM = 7;

    public SensorView(SensorController sensorController) {
        this.sensorController = sensorController;

        menu = new LinkedHashMap<>();

        menu.put(String.valueOf(BASE_NUM), String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(), BASE_NUM * 10 + 1, TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(), BASE_NUM * 10 + 2, TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(), BASE_NUM * 10 + 3, TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(), BASE_NUM * 10 + 4, TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 5, TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 6, TABLE_NAME, "vehicle_id"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createSensor);
        methodsMenu.put(BASE_NUM + "2", this::updateSensor);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromSensor);
        methodsMenu.put(BASE_NUM + "4", this::findAllSensors);
        methodsMenu.put(BASE_NUM + "5", this::findSensorById);
        methodsMenu.put(BASE_NUM + "6", this::findSensorByVehicleId);
    }



    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createSensor() {
        Sensor sensor = getObjectFromConsole();
        Optional<Sensor> result = sensorController.create(sensor);

        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void updateSensor() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        Sensor sensor = getObjectFromConsole();
        Optional<Sensor> result = sensorController.update(id, sensor);

        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_UPDATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_UPDATED.getText(), TABLE_NAME);
        });
    }

    private void deleteFromSensor() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        if (sensorController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }

    private void findAllSensors() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<Sensor> sensors = sensorController.findAll();
        sensors.forEach(System.out::println);
    }

    private void findSensorById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        Integer id = Integer.parseInt(input.nextLine());

        Optional<Sensor> result = sensorController.findById(id);
        System.out.println(result.orElse(nullObject));
    }

    private void findSensorByVehicleId() {
        System.out.printf(ENTER_FIELD.getText(), "vehicle_id");
        Integer vehicleId = Integer.parseInt(input.nextLine());

        List<Sensor> sensors = sensorController.getVehicleSensors(vehicleId);
        sensors.forEach(System.out::println);
    }

    private Sensor getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "brand");
        String brand = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "model");
        String model = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "date_installed");
        Date dateInstalled = Date.valueOf(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "vehicle_id");
        Integer vehicleId = Integer.parseInt(input.nextLine());
        return new Sensor(null, brand, model, dateInstalled, vehicleId);
    }
}
