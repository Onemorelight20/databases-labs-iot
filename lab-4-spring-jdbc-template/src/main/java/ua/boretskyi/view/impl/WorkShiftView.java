package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.WorkShiftController;
import ua.boretskyi.model.WorkShift;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class WorkShiftView implements GeneralView {

    private final WorkShiftController workShiftController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final WorkShift nullObject = WorkShift.nullObject();
    private static final String TABLE_NAME = "WorkShift";
    private static final int BASE_NUM = 21;

    public WorkShiftView(WorkShiftController workShiftController) {
        this.workShiftController = workShiftController;

        menu = new LinkedHashMap<>();

        menu.put(String.valueOf(BASE_NUM), String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(), BASE_NUM + "1", TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(), BASE_NUM + "2", TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(), BASE_NUM + "3", TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(), BASE_NUM + "4", TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(), BASE_NUM + "5", TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(), BASE_NUM + "6", TABLE_NAME, "by vehicleId"));
        menu.put(BASE_NUM + "7", String.format(FIND_BY.getExplanation(), BASE_NUM + "7", TABLE_NAME, "by driverId"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createWorkShift);
        methodsMenu.put(BASE_NUM + "2", this::updateWorkShift);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromWorkShift);
        methodsMenu.put(BASE_NUM + "4", this::findAllWorkShifts);
        methodsMenu.put(BASE_NUM + "5", this::findWorkShiftById);
        methodsMenu.put(BASE_NUM + "6", this::findWorkShiftsByVehicleId);
        methodsMenu.put(BASE_NUM + "7", this::findWorkShiftsByDriverId);
    }

    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createWorkShift() {
        WorkShift workShift = this.getObjectFromConsole();
        Optional<WorkShift> result = workShiftController.create(workShift);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void updateWorkShift() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        Integer id = Integer.parseInt(input.nextLine());

        WorkShift workShift = this.getObjectFromConsole();
        Optional<WorkShift> result = workShiftController.update(id, workShift);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_UPDATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_UPDATED.getText(), TABLE_NAME);
        });
    }

    private void deleteFromWorkShift() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        Integer id = Integer.parseInt(input.nextLine());

        if (workShiftController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.out.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }

    private void findAllWorkShifts() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<WorkShift> workShiftList = workShiftController.findAll();
        workShiftList.forEach(System.out::println);
    }

    private void findWorkShiftById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        Optional<WorkShift> result = workShiftController.findById(id);
        System.out.println(result.orElse(nullObject));
    }

    private void findWorkShiftsByVehicleId() {
        System.out.printf(ENTER_FIELD.getText(), "vehicle_id");
        int vehicleId = Integer.parseInt(input.nextLine());

        List<WorkShift> result = workShiftController.getWorkShiftsOfVehicleWithId(vehicleId);
        result.forEach(System.out::println);
    }

    private void findWorkShiftsByDriverId() {
        System.out.printf(ENTER_FIELD.getText(), "driver_id");
        int driverId = Integer.parseInt(input.nextLine());

        List<WorkShift> result = workShiftController.getWorkShiftsOfDriverWithId(driverId);
        result.forEach(System.out::println);
    }

    private WorkShift getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "driverId");
        Integer driverId = Integer.parseInt(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "vehicleId");
        Integer vehicleId = Integer.parseInt(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "medicalInfoId");
        Integer medicalInfoId = Integer.parseInt(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "mineSightId");
        Integer mineSightId = Integer.parseInt(input.nextLine());
        Timestamp recordTime = Timestamp.valueOf(LocalDateTime.now());
        return new WorkShift(null, driverId, vehicleId, medicalInfoId, mineSightId, recordTime, null);
    }
}
