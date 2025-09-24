package ru.stqa.mantis.manager;

import org.junit.jupiter.api.DynamicNode;
import org.openqa.selenium.io.CircularOutputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class JamesCliHelper extends HelperBase {

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) throws IOException, InterruptedException {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "java", "-cp", "james-server-jpa-app.lib/*",
                    "org.apache.james.cli.ServerCmd", "AddUser", email, password);

            processBuilder.directory(new File(manager.property("james.workingDir")));

            Process process = processBuilder.start();

            // Читаем вывод команды
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            while ((line = errorReader.readLine()) != null) {
                output.append("[ERROR] ").append(line).append("\n");
            }

            int exitCode = process.waitFor();

            System.out.println("Exit code: " + exitCode);
            System.out.println("Output: " + output.toString());

            if (exitCode != 0) {
                throw new RuntimeException("Failed to add user. Exit code: " + exitCode);
            }

        } catch (Exception e) {
            System.err.println("Ошибка при добавлении пользователя " + email + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}

