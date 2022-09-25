package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    public static void save(Path path, Car car) {
        String json = car.serialize();
        Path fullPath = Paths.get(path.toUri()).toAbsolutePath().normalize();
        try {
            Files.writeString(fullPath, json, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) {
        Car car = null;
        Path fullPath = Paths.get(path.toUri()).toAbsolutePath().normalize();
        String content = "";
        try {
            content = Files.readString(fullPath);
            car = Car.unserialize(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return car;
    }
}
// END
