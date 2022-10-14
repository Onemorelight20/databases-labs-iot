package ua.boretskyi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.boretskyi.view.ConsoleView;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private ConsoleView consoleView;
    public Application(ConsoleView consoleView){
        this.consoleView = consoleView;
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consoleView.show();
    }
}
