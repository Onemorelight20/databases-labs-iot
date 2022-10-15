package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.FatigueLevelController;
import ua.boretskyi.model.FatigueLevel;
import ua.boretskyi.model.Location;
import ua.boretskyi.model.VehicleType;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.CommandExplanation.FIND_BY;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class FatigueLevelView implements GeneralView {

    private final FatigueLevelController fatigueLevelController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final FatigueLevel nullObject = FatigueLevel.nullObject();
    private static final String TABLE_NAME = "FatigueLevel";
    private static final int BASE_NUM = 13;

    public FatigueLevelView(FatigueLevelController fatigueLevelController) {
        this.fatigueLevelController = fatigueLevelController;

        menu = new LinkedHashMap<>();

        menu.put(String.valueOf(BASE_NUM), String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(), BASE_NUM + "1", TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(), BASE_NUM + "2", TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(), BASE_NUM + "3", TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(), BASE_NUM + "4", TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(), BASE_NUM + "5", TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(), BASE_NUM + "6", TABLE_NAME, "level_title"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createFatigueLevel);
        methodsMenu.put(BASE_NUM + "2", this::updateFatigueLevel);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromFatigueLevel);
        methodsMenu.put(BASE_NUM + "4", this::findAllFatigueLevels);
        methodsMenu.put(BASE_NUM + "5", this::findFatigueLevelById);
        methodsMenu.put(BASE_NUM + "6", this::findFatigueLevelByLevelTitle);
    }

    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createFatigueLevel () {
        FatigueLevel fatigueLevel = this.getObjectFromConsole();
        Optional<FatigueLevel> result = fatigueLevelController.create(fatigueLevel);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void updateFatigueLevel() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        String id = input.nextLine();

        FatigueLevel fatigueLevel = this.getObjectFromConsole();
        Optional<FatigueLevel> result = fatigueLevelController.update(id, fatigueLevel);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_UPDATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_UPDATED.getText(), TABLE_NAME);
        });
    }

    private void deleteFromFatigueLevel() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        String id = input.nextLine();

        if (fatigueLevelController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }

    private void findAllFatigueLevels() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<FatigueLevel> fatigueLeves = fatigueLevelController.findAll();
        fatigueLeves.forEach(System.out::println);
    }
    private void findFatigueLevelById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        String id = input.nextLine();

        Optional<FatigueLevel> result = fatigueLevelController.findById(id);
        System.out.println(result.orElse(nullObject));
    }
    private void findFatigueLevelByLevelTitle() {
        System.out.printf(ENTER_FIELD.getText(), "level_title");
        String levelTitle = input.nextLine();

        Optional<FatigueLevel> result = fatigueLevelController.findByTitle(levelTitle);
        System.out.println(result.orElse(nullObject));
    }

    private FatigueLevel getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "level_title");
        String levelTitle = input.nextLine();
        return new FatigueLevel(levelTitle);
    }
}
