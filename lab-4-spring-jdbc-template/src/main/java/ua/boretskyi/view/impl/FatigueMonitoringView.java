package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.FatigueMonitoringController;
import ua.boretskyi.model.FatigueMonitoring;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class FatigueMonitoringView implements GeneralView {


    private final FatigueMonitoringController fatigueMonitoringController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final FatigueMonitoring nullObject = FatigueMonitoring.nullObject();
    private static final String TABLE_NAME = "FatigueMonitoring";
    private static final int BASE_NUM = 12;

    public FatigueMonitoringView(FatigueMonitoringController fatigueMonitoringController) {
        this.fatigueMonitoringController = fatigueMonitoringController;

        menu = new LinkedHashMap<>();

        menu.put(String.valueOf(BASE_NUM), String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(), BASE_NUM * 10 + 1, TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(), BASE_NUM * 10 + 2, TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(), BASE_NUM * 10 + 3, TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(), BASE_NUM * 10 + 4, TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 5, TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 6, TABLE_NAME, "driverId"));
        menu.put(BASE_NUM + "7", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 7, TABLE_NAME, "workShiftId"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createFatigueMonitoring);
        methodsMenu.put(BASE_NUM + "2", this::updateFatigueMonitoring);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromFatigueMonitoring);
        methodsMenu.put(BASE_NUM + "4", this::findAllFatigueMonitorings);
        methodsMenu.put(BASE_NUM + "5", this::findFatigueMonitoringById);
        methodsMenu.put(BASE_NUM + "6", this::findFatigueMonitoringsByDriverId);
        methodsMenu.put(BASE_NUM + "7", this::findFatigueMonitoringsByWorkShiftId);
    }


    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createFatigueMonitoring() {
        FatigueMonitoring fatigueMonitoring = this.getObjectFromConsole();
        Optional<FatigueMonitoring> result = fatigueMonitoringController.create(fatigueMonitoring);

        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void updateFatigueMonitoring() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        FatigueMonitoring fatigueMonitoring = this.getObjectFromConsole();
        Optional<FatigueMonitoring> result = fatigueMonitoringController.update(id, fatigueMonitoring);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_UPDATED.getText(), TABLE_NAME, value), () ->
                System.err.printf(RECORD_WAS_NOT_UPDATED.getText(), TABLE_NAME));
    }

    private void deleteFromFatigueMonitoring() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        if (fatigueMonitoringController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }

    private void findAllFatigueMonitorings() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<FatigueMonitoring> fatigueMonitorings = fatigueMonitoringController.findAll();
        fatigueMonitorings.forEach(System.out::println);
    }

    private void findFatigueMonitoringById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        Integer id = Integer.parseInt(input.nextLine());

        Optional<FatigueMonitoring> result = fatigueMonitoringController.findById(id);
        System.out.println(result.orElse(nullObject));
    }

    private void findFatigueMonitoringsByDriverId() {
        System.out.printf(ENTER_FIELD.getText(), "driver_id");
        Integer id = Integer.parseInt(input.nextLine());

        List<FatigueMonitoring> fatigueMonitorings = fatigueMonitoringController.getRecordsForDriverWithId(id);
        fatigueMonitorings.forEach(System.out::println);
    }

    private void findFatigueMonitoringsByWorkShiftId() {
        System.out.printf(ENTER_FIELD.getText(), "work_shift_id");
        Integer id = Integer.parseInt(input.nextLine());

        List<FatigueMonitoring> fatigueMonitorings = fatigueMonitoringController.getRecordsForWorkShiftWithId(id);
        fatigueMonitorings.forEach(System.out::println);
    }

    private FatigueMonitoring getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "driver_id");
        Integer driverId = Integer.valueOf(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "work_shift_id");
        Integer workShiftId = Integer.valueOf(input.nextLine());

        System.out.printf(ENTER_FIELD.getText(), "is_critical[true/false]");
        Boolean isCritical = Boolean.valueOf(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "fatigue_level_title");
        String fatigueLevelTitle = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "location_id");
        Integer locationId = Integer.valueOf(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "vehicle_speed_km_per_h");
        Integer vehicleSpeedPerH = Integer.valueOf(input.nextLine());
        Timestamp recordTime = Timestamp.valueOf(LocalDateTime.now());
        return new FatigueMonitoring(null, driverId, workShiftId, isCritical, fatigueLevelTitle, locationId, vehicleSpeedPerH, recordTime);
    }
}
