import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Car {
    int id;
    String make;
    String model;
    int year;
    String color;
    double price;
    String registrationNumber;

    public Car(int id, String make, String model, int year, String color, double price, String registrationNumber) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return id + " " + make + " " + model + " " + year + " " + color + " $" + price + " " + registrationNumber;
    }

    public static void saveToFile(String fileName, List<Car> cars) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Car car : cars) {
                writer.write(car.toString() + "\n");
            }
        }
    }

    public static List<Car> filterByMake(List<Car> cars, String make) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.make.equalsIgnoreCase(make)) {
                result.add(car);
            }
        }
        return result;
    }

    public static List<Car> filterByModelAndAge(List<Car> cars, String model, int yearsInUse) {
        List<Car> result = new ArrayList<>();
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (Car car : cars) {
            if (car.model.equalsIgnoreCase(model) && (currentYear - car.year) > yearsInUse) {
                result.add(car);
            }
        }
        return result;
    }

    public static List<Car> filterByYearAndPrice(List<Car> cars, int year, double minPrice) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.year == year && car.price > minPrice) {
                result.add(car);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, "Toyota", "Camry", 2010, "White", 9000, "ABC123"));
        cars.add(new Car(2, "Honda", "Civic", 2015, "Black", 12000, "XYZ789"));
        cars.add(new Car(3, "Ford", "Mustang", 2012, "Red", 15000, "LMN456"));
        cars.add(new Car(4, "Toyota", "Corolla", 2018, "Blue", 13000, "JKL321"));
        cars.add(new Car(5, "BMW", "X5", 2020, "Grey", 45000, "PQR987"));

        // Filter by make
        List<Car> toyotaCars = filterByMake(cars, "Toyota");
        saveToFile("toyota_cars.txt", toyotaCars);

        // Filter by model and age (e.g., older than 5 years)
        List<Car> oldCivics = filterByModelAndAge(cars, "Civic", 5);
        saveToFile("old_civics.txt", oldCivics);

        // Filter by year and price (e.g., from year 2015 and price > 10000)
        List<Car> cars2015Expensive = filterByYearAndPrice(cars, 2015, 10000);
        saveToFile("cars_2015_expensive.txt", cars2015Expensive);
    }
}
