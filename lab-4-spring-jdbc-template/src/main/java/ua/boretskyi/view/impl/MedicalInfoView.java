package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.MedicalInfoController;
import java.sql.Date;

import ua.boretskyi.model.MedicalInfo;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.CommandExplanation.FIND_BY;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class MedicalInfoView implements GeneralView {

    private final MedicalInfoController medicalInfoController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final MedicalInfo nullObject = MedicalInfo.nullObject();
    private static final String TABLE_NAME = "MedicalInfo";
    private static final int BASE_NUM = 4;

    public MedicalInfoView(MedicalInfoController medicalInfoController) {
        this.medicalInfoController = medicalInfoController;

        menu = new LinkedHashMap<>();

        menu.put(String.valueOf(BASE_NUM), String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(), BASE_NUM * 10 + 1, TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(), BASE_NUM * 10 + 2, TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(), BASE_NUM * 10 + 3, TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(), BASE_NUM * 10 + 4, TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 5, TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 6, TABLE_NAME, "driverId"));
        menu.put(BASE_NUM + "7", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 6, TABLE_NAME, "doctorId"));
        menu.put(BASE_NUM + "8", String.format("  %d - Find %s by %s and %s", BASE_NUM * 10 + 6, TABLE_NAME, "driverId", "doctorId"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createMedicalInfo);
        methodsMenu.put(BASE_NUM + "2", this::updateMedicalInfo);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromMedicalInfo);
        methodsMenu.put(BASE_NUM + "4", this::findAllMedicalInfos);
        methodsMenu.put(BASE_NUM + "5", this::findMedicalInfoById);
        methodsMenu.put(BASE_NUM + "6", this::findMedicalInfoByDriverId);
        methodsMenu.put(BASE_NUM + "7", this::findMedicalInfoByDoctorId);
        methodsMenu.put(BASE_NUM + "8", this::findMedicalInfoByDriverAndDoctorId);
    }

    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createMedicalInfo() {
        MedicalInfo medicalInfo = getObjectFromConsole();
        Optional<MedicalInfo> result = medicalInfoController.create(medicalInfo);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void updateMedicalInfo() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        MedicalInfo medicalInfo = getObjectFromConsole();
        Optional<MedicalInfo> result = medicalInfoController.update(id, medicalInfo);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void deleteFromMedicalInfo() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        if (medicalInfoController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }

    private void findAllMedicalInfos() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<MedicalInfo> infos = medicalInfoController.findAll();
        infos.forEach(System.out::println);
    }

    private void findMedicalInfoById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        Optional<MedicalInfo> result = medicalInfoController.findById(id);
        System.out.println(result.orElse(nullObject));
    }

    private void findMedicalInfoByDriverId() {
        System.out.printf(ENTER_FIELD.getText(), "driverId");
        int driverId = Integer.parseInt(input.nextLine());

        Optional<MedicalInfo> result = medicalInfoController.findLatestInfoForDriverWithId(driverId);
        System.out.println(result.orElse(nullObject));
    }

    private void findMedicalInfoByDoctorId() {
        System.out.printf(ENTER_FIELD.getText(), "doctorId");
        int doctorId = Integer.parseInt(input.nextLine());

        Optional<MedicalInfo> result = medicalInfoController.findById(doctorId);
        System.out.println(result.orElse(nullObject));
    }

    private void findMedicalInfoByDriverAndDoctorId() {
        System.out.printf(ENTER_FIELD.getText(), "driverId");
        int driverId = Integer.parseInt(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "doctorId");
        int doctorId = Integer.parseInt(input.nextLine());

        Optional<MedicalInfo> result = medicalInfoController.findLatestInfoForDriverAndDoctorWithIds(driverId, doctorId);
        System.out.println(result.orElse(nullObject));
    }

    private MedicalInfo getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "driverId");
        Integer driverId = Integer.parseInt(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "sightState");
        String sightState = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "bloodType");
        String bloodType = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "doctorId");
        Integer doctorId = Integer.parseInt(input.nextLine());
        System.out.printf(ENTER_FIELD.getText(), "updatedAt");
        Date updatedAt = Date.valueOf(input.nextLine());
        return new MedicalInfo(null, driverId, sightState, bloodType, doctorId, updatedAt);
    }

}
