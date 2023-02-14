package service;

import animals.Animals;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ZooDBService {

//    System.out.println("1 - Посмотреть всех животных " + "2 - Посмотреть конкретных животных"
//            + " 3 - Посмотреть животных по признакам"
//            + " 4 - Позвать животное" + " 5 - Убрать животное"
//            + " 6 - Добавить животное" + " 7 - Изменить имя животному"
//            + " 8 - Сравнить двух животных"
//            + " 0 - Покинуть зоопарк");

    public static void createTable(String dbUrl) {

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

    public static String loadAnimals(String dbUrl, ArrayList<Animals> animals) {

        String query = "INSERT INTO animals (name,legs,type,ispredator,color,area) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {

            connection.setAutoCommit(false);

            for (Animals animal : animals) {

                statement.setString(1, animal.getName());
                statement.setInt(2, animal.getLegs());
                statement.setString(3, animal.getType());
                statement.setBoolean(4, animal.isPredator());
                statement.setString(5, animal.getColor());
                statement.setString(6, animal.getArea());

                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void selectAnimals(String dbUrl) {

        String query = "SELECT * FROM animals";

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ArrayList<String> animals = new ArrayList<>();

            while (resultSet.next()) {
                animals.add(resultSet.getString(4) + " " + resultSet.getString(2));
            }
            System.out.println(animals);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void truncateTable(String dbUrl) {

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);

            statement.executeUpdate("DROP TABLE animals");
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void animalsByType(String dbUrl) {

        String query = "SELECT name,type FROM animals WHERE type = ? ";

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {

            System.out.println("Введите тип животного");
            Scanner scanner = new Scanner(System.in);

            statement.setString(1, scanner.nextLine());

            ResultSet resultSet = statement.executeQuery();

            ArrayList<String> animals = new ArrayList<>();

            if (!resultSet.next()) {
                System.out.println("Таких животных нет");
            } else {
                do {
                    animals.add("Тип " + resultSet.getString(2) + ", Имя " + resultSet.getString(1));
                } while (resultSet.next());
                System.out.println(animals);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String callAnimal(String dbUrl) {

        System.out.println("Введите имя");
        Scanner scanner = new Scanner(System.in);

        String value = "";

        String query = "SELECT name,type,color FROM animals WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, scanner.nextLine());

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Никто из животных не откликнулся");
                value = "Никто из животных не откликнулся";
            } else {
                System.out.println("К Вам пришел " + resultSet.getString("type") +
                        " " + resultSet.getString("name") + " " + resultSet.getString("color") + " цвета");
                value = "К Вам пришел " + resultSet.getString("type") +
                        " " + resultSet.getString("name") + " " + resultSet.getString("color") + " цвета";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String deleteAnimal(String dbUrl) {

        System.out.println("Введите имя");
        Scanner scanner = new Scanner(System.in);

        String find = "SELECT name FROM animals WHERE name = ?";
        String delete = "DELETE FROM animals WHERE name = ?";

        String value = "";

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             PreparedStatement statement1 = connection.prepareStatement(find);
             PreparedStatement statement2 = connection.prepareStatement(delete)) {

            String name = scanner.nextLine();

            statement1.setString(1, name);
            ResultSet animal = statement1.executeQuery();

            if (!animal.next()) {
                System.out.println("Таких животных нет");
                value = "Таких животных нет";
            } else {
                statement2.setString(1, name);
                statement2.execute();
                System.out.println("Животное с именем " + name + " удалено");
                value = "Животное с именем " + name + " удалено";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String addAnimal(String dbUrl) {

        System.out.println("Добавьте животное");
        Scanner scanner = new Scanner(System.in);

        ArrayList<Animals> animals = new ArrayList<>();

        String animal = scanner.nextLine();

        String[] split = animal.split(", ");

        animals.add(new Animals(split[0], Integer.parseInt(split[1]), split[2], Boolean.parseBoolean(split[3]), split[4], split[5]));

        loadAnimals(dbUrl, animals);

        System.out.println("Животное" + split[2] + " " + split[0] + " добавлено");
        return "Животное" + split[2] + " " + split[0] + " добавлено";
    }

    public static String renameAnimal(String dbUrl) {

        System.out.println("Введите имя животного");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Введите новое имя животного");
        String newName = scanner.nextLine();

        String query = "UPDATE animals SET name = ? WHERE name = ? ";

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newName);
            statement.setString(2, name);

            statement.executeUpdate();

            System.out.println("Животное " + name + " теперь зовут " + newName);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Животное " + name + " теперь зовут " + newName;
    }

    public static String assertAnimals(String dbUrl) {

        System.out.println("Введите имя 1-го животного");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        System.out.println("Введите имя 2-го животного");
        String secondName = scanner.nextLine();
        ResultSet resultSet = null;
        ArrayList<String> animal1 = new ArrayList<>();
        ArrayList<String> animal2 = new ArrayList<>();
        ArrayList<String> plus = new ArrayList<>();
        ArrayList<String> minus = new ArrayList<>();

        String query = "SELECT * FROM animals WHERE name = ? ";

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, firstName);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                animal1.add(resultSet.getString(2) + ", " + Integer.parseInt(resultSet.getString(3)) + ", " + resultSet.getString(4)
                        + ", " + Boolean.parseBoolean(resultSet.getString(5)) + ", " + resultSet.getString(6) + ", " + resultSet.getString(7));
                System.out.println(animal1);
            }

            statement.setString(1, secondName);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                animal2.add(resultSet.getString(2) + ", " + Integer.parseInt(resultSet.getString(3)) + ", " + resultSet.getString(4)
                        + ", " + Boolean.parseBoolean(resultSet.getString(5)) + ", " + resultSet.getString(6) + ", " + resultSet.getString(7));
                System.out.println(animal2);
            }

            String[] firstAnimal = animal1
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining())
                .split(", ");

            String[] secondAnimal = animal2
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining())
                    .split(", ");

            for (int i = 0; i < firstAnimal.length; i++) {
                    if (firstAnimal[i].equals(secondAnimal[i])) {
                        plus.add(firstAnimal[i]);
                    } else minus.add(firstAnimal[i] + " - " + secondAnimal[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Совпадающие признаки - " + plus + " . Несовпадающие признаки - " + minus);
        return "Совпадающие признаки - " + plus + " . Несовпадающие признаки - " + minus;
    }

    public static String selectSign(String dbUrl) {
        System.out.println("1 - Количество ног " + "2 - Хищник? " + "3 - Цвет " + "4 - Ареал обитания ");

        Scanner scanner = new Scanner(System.in);
        int sign = scanner.nextInt();
        Scanner param = new Scanner(System.in);
        String query = "";
        String result = "";
        ResultSet resultSet = null;

            try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root")) {

                switch (sign) {
                    case 1 -> {
                        System.out.println("Введите кол-во ног");
                        query = "SELECT * FROM animals WHERE legs = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setInt(1, param.nextInt());
                        resultSet = statement.executeQuery();
                    }
                    case 2 -> {
                        System.out.println("Хищник?");
                        query = "SELECT * FROM animals WHERE ispredator = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setBoolean(1, param.nextBoolean());
                        resultSet = statement.executeQuery();
                    }
                    case 3 -> {
                        System.out.println("Введите цвет");
                        query = "SELECT * FROM animals WHERE color = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, param.nextLine());
                        resultSet = statement.executeQuery();
                    }
                    case 4 -> {
                        System.out.println("Введите ареал обитания");
                        query = "SELECT * FROM animals WHERE area = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, param.nextLine());
                        resultSet = statement.executeQuery();
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + sign);
                }

                if (!resultSet.next()) {
                    System.out.println("Таких животных нет");
                    result = "Никто из животных не откликнулся";
                } else {
                    do {
                        System.out.println(resultSet.getString("type") + " " + resultSet.getString("name") + " "
                                + resultSet.getString("legs") + " " + resultSet.getString("ispredator") + " "
                                + resultSet.getString("color") + " " + resultSet.getString("area"));
                        result = resultSet.getString("type") + " " + resultSet.getString("name") + " "
                                + resultSet.getString("legs") + " " + resultSet.getString("ispredator") + " "
                                + resultSet.getString("color") + " " + resultSet.getString("area");
                    } while (resultSet.next());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return result;
    }

}
