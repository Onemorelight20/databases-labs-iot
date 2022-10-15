package ua.boretskyi.view.impl;

import org.springframework.stereotype.Component;
import ua.boretskyi.controller.custom.MineSightController;
import ua.boretskyi.model.MineSight;
import ua.boretskyi.view.GeneralView;
import ua.boretskyi.view.Printable;

import java.util.*;

import static ua.boretskyi.view.utill.CommandExplanation.*;
import static ua.boretskyi.view.utill.CommandExplanation.FIND_BY;
import static ua.boretskyi.view.utill.Message.*;

@Component
public class MineSightView implements GeneralView {

    private final MineSightController mineSightController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final MineSight nullObject = MineSight.nullObject();
    private static final String TABLE_NAME = "MineSight";
    private static final int BASE_NUM = 11;

    public MineSightView(MineSightController mineSightController) {
        this.mineSightController = mineSightController;

        menu = new LinkedHashMap<>();

        menu.put(String.valueOf(BASE_NUM), String.format(TABLE_SELECT.getExplanation(), BASE_NUM, TABLE_NAME));
        menu.put(BASE_NUM + "1", String.format(CREATE_RECORD.getExplanation(), BASE_NUM * 10 + 1, TABLE_NAME));
        menu.put(BASE_NUM + "2", String.format(UPDATE_RECORD.getExplanation(), BASE_NUM * 10 + 2, TABLE_NAME));
        menu.put(BASE_NUM + "3", String.format(DELETE_RECORD.getExplanation(), BASE_NUM * 10 + 3, TABLE_NAME));
        menu.put(BASE_NUM + "4", String.format(FIND_ALL.getExplanation(), BASE_NUM * 10 + 4, TABLE_NAME));
        menu.put(BASE_NUM + "5", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 5, TABLE_NAME, "ID"));
        menu.put(BASE_NUM + "6", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 6, TABLE_NAME, "company_id"));
        menu.put(BASE_NUM + "7", String.format(FIND_BY.getExplanation(), BASE_NUM * 10 + 7, TABLE_NAME, "title"));

        methodsMenu = new LinkedHashMap<>();

        methodsMenu.put(BASE_NUM + "1", this::createMineSight);
        methodsMenu.put(BASE_NUM + "2", this::updateMineSight);
        methodsMenu.put(BASE_NUM + "3", this::deleteFromMineSight);
        methodsMenu.put(BASE_NUM + "4", this::findAllMineSights);
        methodsMenu.put(BASE_NUM + "5", this::findMineSightById);
        methodsMenu.put(BASE_NUM + "6", this::findMineSightsByCompanyId);
        methodsMenu.put(BASE_NUM + "7", this::findMineSightByTitle);
    }

    @Override
    public Map<String, String> getMenu() {
        return menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return methodsMenu;
    }

    private void createMineSight() {
        MineSight mineSight = this.getObjectFromConsole();
        Optional<MineSight> result = mineSightController.create(mineSight);

        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_CREATED.getText(), TABLE_NAME, value), () -> {
            System.err.printf(RECORD_WAS_NOT_CREATED.getText(), TABLE_NAME);
        });
    }

    private void updateMineSight() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        Integer id = Integer.parseInt(input.nextLine());

        MineSight mineSight = this.getObjectFromConsole();
        Optional<MineSight> result = mineSightController.update(id, mineSight);
        result.ifPresentOrElse(value -> System.out.printf(RECORD_WAS_UPDATED.getText(), TABLE_NAME, value), () ->
                System.err.printf(RECORD_WAS_NOT_UPDATED.getText(), TABLE_NAME));
    }

    private void deleteFromMineSight() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        int id = Integer.parseInt(input.nextLine());

        if (mineSightController.delete(id)) {
            System.out.printf(RECORD_WAS_DELETED.getText(), TABLE_NAME);
        } else {
            System.err.printf(RECORD_WAS_NOT_DELETED.getText(), TABLE_NAME);
        }
    }

    private void findAllMineSights() {
        System.out.printf(TABLE.getText(), TABLE_NAME);
        List<MineSight> mineSights = mineSightController.findAll();
        mineSights.forEach(System.out::println);
    }

    private void findMineSightById() {
        System.out.printf(ENTER_FIELD.getText(), "id");
        Integer id = Integer.parseInt(input.nextLine());

        Optional<MineSight> result = mineSightController.findById(id);
        System.out.println(result.orElse(nullObject));
    }

    private void findMineSightsByCompanyId() {
        System.out.printf(ENTER_FIELD.getText(), "company_id");
        Integer id = Integer.parseInt(input.nextLine());

        List<MineSight> result = mineSightController.getMineSightsOfCompanyWithId(id);
        result.forEach(System.out::println);
    }

    private void findMineSightByTitle() {
        System.out.printf(ENTER_FIELD.getText(), "title");
        String title = input.nextLine();

        Optional<MineSight> result = mineSightController.findByTitle(title);
        System.out.println(result.orElse(nullObject));
    }

    private MineSight getObjectFromConsole() {
        System.out.printf(ENTER_FIELD.getText(), "country");
        String country = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "city");
        String city = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "title");
        String title = input.nextLine();
        System.out.printf(ENTER_FIELD.getText(), "area_in_square_meters");
        Integer area = Integer.parseInt(input.nextLine());
        return new MineSight(null, country, city, title, area);
    }
}
