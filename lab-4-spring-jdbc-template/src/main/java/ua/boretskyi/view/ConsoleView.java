package ua.boretskyi.view;

import org.springframework.stereotype.Component;
import ua.boretskyi.view.impl.CompanyView;
import ua.boretskyi.view.impl.DoctorView;
import ua.boretskyi.view.impl.DriverView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class ConsoleView {

    private final CompanyView companyView;
    private final DoctorView doctorView;
    private final DriverView driverView;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    public ConsoleView(CompanyView companyView, DoctorView doctorView, DriverView driverView) {
        this.companyView = companyView;
        this.doctorView = doctorView;
        this.driverView = driverView;

        menu = new LinkedHashMap<>();
        methodsMenu = new LinkedHashMap<>();
        insertDataIntoMenu();
        insertDataIntoMethodsMenu();
    }

    private void insertDataIntoMenu(){
        menu.putAll(companyView.getMenu());
        menu.putAll(doctorView.getMenu());
        menu.putAll(driverView.getMenu());
        menu.put("Q", "Quit");
    }
    private void insertDataIntoMethodsMenu(){
        methodsMenu.putAll(companyView.getMethodsMenu());
        methodsMenu.putAll(doctorView.getMethodsMenu());
        methodsMenu.putAll(driverView.getMethodsMenu());
    }

    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {
        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }
}
