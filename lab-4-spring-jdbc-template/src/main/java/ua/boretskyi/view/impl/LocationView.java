package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.LocationController;
import ua.boretskyi.model.Driver;
import ua.boretskyi.model.Location;
import ua.boretskyi.model.WorkShift;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class LocationView implements GeneralView {

    private final LocationController locationController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Location nullObject = Location.nullObject();
    private static final String TABLE_NAME = "Location";
    private static final int BASE_NUM = 9;

    public LocationView(LocationController locationController) {
        this.locationController = locationController;

        menu = new LinkedHashMap<>();

        menu.put(String.valueOf(BASE_NUM), String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(), BASE_NUM * 10 + 1, TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(), BASE_NUM * 10 + 2, TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(), BASE_NUM * 10 + 3, TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(), BASE_NUM * 10 + 4, TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 5, TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_ALL.getExplanation(), BASE_NUM * 10 + 6, TABLE_NAME, "by vehicleId"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createLocation);
        methodsMenu.put(BASE_NUM + "2", this::updateLocation);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromLocation);
        methodsMenu.put(BASE_NUM + "4", this::findAllLocations);
        methodsMenu.put(BASE_NUM + "5", this::findLocationById);
        methodsMenu.put(BASE_NUM + "6", this::findLocationByVehicleId);
    }

    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createLocation() {
        Location location = this.getObjectFromConsole();
        Optional<Location> result = locationController.create(location);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }
    private void updateLocation() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        Integer id = Integer.parseInt(input.nextLine());

        Location location = this.getObjectFromConsole();
        Optional<Location> result = locationController.update(id, location);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_UPDATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_UPDATED.getText(), TABLE_NAME);
        });
    }
    private void deleteFromLocation() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        if (locationController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }
    private void findAllLocations() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<Location> locations = locationController.findAll();
        locations.forEach(System.out::println);
    }
    private void findLocationById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        Optional<Location> result = locationController.findById(id);
        System.out.println(result.orElse(nullObject));
    }
    private void findLocationByVehicleId() {
        System.out.printf(ENTER_FIELD.getText(), "vehicle_id");
        int vehicleId = Integer.parseInt(input.nextLine());

        List<Location> result = locationController.getAllLocationOfVehicleWithId(vehicleId);
        result.forEach(System.out::println);
    }

    private Location getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "latitude");
        BigDecimal latitude = BigDecimal.valueOf(Integer.parseInt(input.nextLine()));
        System.out.printf(ENTER_FIELD.getText(), "longitude");
        BigDecimal longitude = BigDecimal.valueOf(Integer.parseInt(input.nextLine()));

        Timestamp recordTime = Timestamp.valueOf(LocalDateTime.now());
        System.out.printf(ENTER_FIELD.getText(), "vehicleId");
        Integer vehicleId = Integer.parseInt(input.nextLine());
        return new Location(null, latitude, longitude, recordTime, vehicleId);
    }
}
