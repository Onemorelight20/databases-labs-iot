package ua.boretskyi.view;

import org.springframework.stereotype.Component;
import ua.boretskyi.view.impl.*;

import java.util.*;

@Component
public class ConsoleView {

    private final List<GeneralView> views;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    // implement FatigueMonitoring view & MineSight view
    public ConsoleView(CompanyView companyView, DoctorView doctorView, DriverView driverView,
                       MedicalInfoView medicalInfoView, VehicleTypeView vehicleTypeView, VehicleView vehicleView,
                        FatigueLevelView fatigueLevelView, LocationView locationView, WorkShiftView workShiftView,
                         MineSightView mineSightView, FatigueMonitoringView fatigueMonitoringView) {
        views = new ArrayList<>();
        views.add(companyView);
        views.add(doctorView);
        views.add(driverView);
        views.add(medicalInfoView);
        views.add(vehicleTypeView);
        views.add(vehicleView);
        views.add(fatigueLevelView);
        views.add(locationView);
        views.add(workShiftView);
        views.add(mineSightView);
        views.add(fatigueMonitoringView);

        menu = new LinkedHashMap<>();
        methodsMenu = new LinkedHashMap<>();
        insertDataIntoMenu();
        insertDataIntoMethodsMenu();
    }

    private void insertDataIntoMenu(){
        views.forEach((view) -> menu.putAll(view.getMenu()));
        menu.put("Q", "Quit");
    }
    private void insertDataIntoMethodsMenu(){
        views.forEach((view) -> methodsMenu.putAll(view.getMethodsMenu()));
    }

    private void outputMenu() {
        System.out.println("\nmenu:");
        for (String key : menu.keySet()) {
            System.out.println("KEY IS " + key);
            if (key.length() != 3)
                System.out.println(menu.get(key));
        }
    }

    private void outputSubmenu(String fig) {
        System.out.println("\nsubmenu:");
        for (String key : menu.keySet()) {
            System.out.println("SUBKEY IS " + key);
            if (key.startsWith(fig)) System.out.println(menu.get(key));
        }
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubmenu(keyMenu);
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
