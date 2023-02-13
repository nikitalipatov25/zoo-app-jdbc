import animals.*;
import service.ZooDBService;
import service.ZooService;

import java.sql.*;
import java.util.*;

import static service.ZooDBService.truncateTable;


public class Main {
    public static void main(String[] args) throws Exception {

        String dbUrl = "jdbc:postgresql://localhost/zoo";

        truncateTable(dbUrl);
        createTable(dbUrl);

        ArrayList<NewAnimals> animals = new ArrayList<>();
        animals.add(new NewAnimals("Bob", 4, "wolf", true, "gray", "Eurasia"));
        animals.add(new NewAnimals("Ivan", 4, "tiger", true, "yellow", "Eurasia"));
        animals.add(new NewAnimals("John", 4, "bear", true, "black", "North America"));
        ZooDBService.loadAnimals(dbUrl, animals);

        System.out.println("1 - Посмотреть всех животных " + "2 - Посмотреть конкретных животных"
                + " 3 - Посмотреть животных по признакам"
                + " 4 - Позвать животное" + " 5 - Убрать животное"
                + " 6 - Добавить животное" + " 7 - Изменить имя животному"
                + " 8 - Сравнить двух животных"
                + " 0 - Покинуть зоопарк");
        Scanner scanner = new Scanner(System.in);

        boolean isWorking = true;

        while (isWorking) {

            int num = scanner.nextInt();

            switch (num) {
                case 1 -> ZooDBService.selectAnimals(dbUrl);
                case 2 -> ZooDBService.animalsByType(dbUrl);
                //case 3 -> ZooService.selectSign(animals);
                case 4 -> ZooDBService.callAnimal(dbUrl);
                case 5 -> ZooDBService.deleteAnimal(dbUrl);
                case 6 -> ZooDBService.addAnimal(dbUrl);
                case 7 -> ZooDBService.renameAnimal(dbUrl);
                case 8 -> ZooDBService.assertAnimals(dbUrl);
                case 0 -> isWorking = false;
                default -> throw new IllegalStateException("Вы выбрали неверное действие: " );
            }
        }
    }

    private static void createTable(String dbUrl) {

        String query = "CREATE TABLE IF NOT EXISTS animals " +
                "(ID SERIAL PRIMARY KEY," +
                " NAME TEXT, " +
                " LEGS INT, " +
                " TYPE VARCHAR(50), " +
                " ISPREDATOR BOOLEAN, " +
                " COLOR VARCHAR(50), " +
                " AREA VARCHAR(50))";

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}