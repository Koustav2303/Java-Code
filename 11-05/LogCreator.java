import java.io.FileWriter;
import java.io.IOException;

public class LogCreator {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("app.log")) {
            writer.write("Application started at 2026-05-11\n");
            System.out.println("Log file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}