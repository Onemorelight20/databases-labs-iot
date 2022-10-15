package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.DoctorController;
import ua.boretskyi.model.Doctor;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class DoctorView implements GeneralView {

    private final DoctorController doctorController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Doctor nullObject = Doctor.nullObject();
    private static final String TABLE_NAME = "Doctor";
    private static final int BASE_NUM = 11;

    public DoctorView(DoctorController doctorController) {
        this.doctorController = doctorController;

        menu = new LinkedHashMap<>();


        menu.put("11", String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(),  BASE_NUM + "1", TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(),  BASE_NUM + "2", TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(),  BASE_NUM + "3", TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(),  BASE_NUM + "4", TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(),  BASE_NUM + "5", TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(),  BASE_NUM + "6", TABLE_NAME, "phoneNumber"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createDoctor);
        methodsMenu.put(BASE_NUM + "2", this::updateDoctor);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromDoctor);
        methodsMenu.put(BASE_NUM + "4", this::findAllDoctors);
        methodsMenu.put(BASE_NUM + "5", this::findDoctorById);
        methodsMenu.put(BASE_NUM + "6", this::findDoctorByPhoneNumber);
    }

    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createDoctor() {
        Doctor doctor = this.getObjectFromConsole();
        Optional<Doctor> result = doctorController.create(doctor);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void updateDoctor() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        Doctor doctor = this.getObjectFromConsole();
        Optional<Doctor> result = doctorController.update(id, doctor);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_UPDATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_UPDATED.getText(), TABLE_NAME);
        });
    }

    private void deleteFromDoctor() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        if (doctorController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }

    private void findAllDoctors() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<Doctor> doctors = doctorController.findAll();
        doctors.forEach(System.out::println);
    }

    private void findDoctorById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        Optional<Doctor> result = doctorController.findById(id);
        System.out.println(result.orElse(nullObject));
    }

    private void findDoctorByPhoneNumber() {
        System.out.printf(ENTER_FIELD.getText(), "phoneNumber");
        String phoneNumber = input.nextLine();

        Optional<Doctor> result = doctorController.findByPhoneNumber(phoneNumber);
        System.out.println(result.orElse(nullObject));
    }

    private Doctor getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "name");
        String name = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "surname");
        String surname = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "phoneNumber");
        String phoneNumber = input.nextLine();
        return new Doctor(null, name, surname, phoneNumber);
    }
}
