package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.VehicleTypeController;
import ua.boretskyi.model.MedicalInfo;
import ua.boretskyi.model.VehicleType;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.CommandExplanation.FIND_BY;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class VehicleTypeView implements GeneralView {

    private final VehicleTypeController vehicleTypeController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final VehicleType nullObject = VehicleType.nullObject();
    private static final String TABLE_NAME = "VehicleType";
    private static final int BASE_NUM = 19;

    public VehicleTypeView(VehicleTypeController vehicleTypeController) {
        this.vehicleTypeController = vehicleTypeController;

        menu = new LinkedHashMap<>();

        menu.put(String.valueOf(BASE_NUM), String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(), BASE_NUM + "1", TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(), BASE_NUM  + "2", TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(), BASE_NUM + "3", TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(), BASE_NUM + "4", TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(), BASE_NUM + "5", TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(), BASE_NUM + "6", TABLE_NAME, "type"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createVehicleType);
        methodsMenu.put(BASE_NUM + "2", this::updateVehicleType);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromVehicleType);
        methodsMenu.put(BASE_NUM + "4", this::findAllVehicleTypes);
        methodsMenu.put(BASE_NUM + "5", this::findVehicleTypeById);
        methodsMenu.put(BASE_NUM + "6", this::findVehicleTypeByType);
    }

    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createVehicleType() {
        VehicleType vehicleType = getObjectFromConsole();
        Optional<VehicleType> result = vehicleTypeController.create(vehicleType);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void updateVehicleType() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        VehicleType vehicleType = getObjectFromConsole();
        Optional<VehicleType> result = vehicleTypeController.update(id, vehicleType);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void deleteFromVehicleType() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        if (vehicleTypeController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }

    private void findAllVehicleTypes() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<VehicleType> infos = vehicleTypeController.findAll();
        infos.forEach(System.out::println);
    }

    private void findVehicleTypeByType() {
        System.out.printf(ENTER_FIELD.getText(), "type");
        String type = input.nextLine();

        Optional<VehicleType> result = vehicleTypeController.findByType(type);
        System.out.println(result.orElse(nullObject));
    }

    private void findVehicleTypeById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        Integer id = Integer.parseInt(input.nextLine());

        Optional<VehicleType> result = vehicleTypeController.findById(id);
        System.out.println(result.orElse(nullObject));
    }

    private VehicleType getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "type");
        String type = input.nextLine();
        return new VehicleType(null, type);
    }
}
