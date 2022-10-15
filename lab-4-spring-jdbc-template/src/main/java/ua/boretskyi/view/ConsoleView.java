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

    public ConsoleView(CompanyView companyView, DoctorView doctorView, DriverView driverView,
                       MedicalInfoView medicalInfoView, VehicleTypeView vehicleTypeView, VehicleView vehicleView,
                        FatigueLevelView fatigueLevelView, LocationView locationView, WorkShiftView workShiftView,
                         MineSightView mineSightView, FatigueMonitoringView fatigueMonitoringView, SensorView sensorView) {
        views = new ArrayList<>();
        views.add(companyView);
        views.add(doctorView);
        views.add(driverView);
        views.add(fatigueLevelView);
        views.add(fatigueMonitoringView);
        views.add(locationView);
        views.add(medicalInfoView);
        views.add(mineSightView);
        views.add(sensorView);
        views.add(vehicleTypeView);
        views.add(vehicleView);
        views.add(workShiftView);

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
            if (key.length() != 3)
                System.out.println(menu.get(key));
        }
    }

    private void outputSubmenu(String fig) {
        System.out.println("\nsubmenu:");
        for (String key : menu.keySet()) {
            if (key.startsWith(fig))
                System.out.println(menu.get(key));
        }
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.length() == 2) {
                outputSubmenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Tried to get method for key " + keyMenu);
            }
        } while (!keyMenu.equals("Q"));
    }
}
