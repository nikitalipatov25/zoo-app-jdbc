package service;

import animals.Animals;
import animals.NewAnimals;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ZooDBService {

//    System.out.println("1 - Посмотреть всех животных " + "2 - Посмотреть конкретных животных"
//            + " 3 - Посмотреть животных по признакам"
//            + " 4 - Позвать животное" + " 5 - Убрать животное"
//            + " 6 - Добавить животное" + " 7 - Изменить имя животному"
//            + " 8 - Сравнить двух животных"
//            + " 0 - Покинуть зоопарк");

    public static String loadAnimals(String dbUrl, ArrayList<NewAnimals> animals) {

        String query = "INSERT INTO animals (name,legs,type,ispredator,color,area) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {

            connection.setAutoCommit(false);

            for (NewAnimals animal : animals) {

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
                animals.add(resultSet.getString(2));
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

        ArrayList<NewAnimals> animals = new ArrayList<>();

        String animal = scanner.nextLine();

        String[] split = animal.split(", ");

        animals.add(new NewAnimals(split[0], Integer.parseInt(split[1]), split[2], Boolean.parseBoolean(split[3]), split[4], split[5]));

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

        String first = "SELECT * FROM animals WHERE name = ? ";
        String second = "SELECT * FROM animals WHERE name = ? ";

        try (Connection connection = DriverManager.getConnection(dbUrl, "postgres", "root");
             PreparedStatement statement1 = connection.prepareStatement(first);
             PreparedStatement statement2 = connection.prepareStatement(second)) {

            statement1.setString(1, firstName);
            statement2.setString(1, secondName);

            ResultSet firstAnimal = statement1.executeQuery();
            ResultSet secondAnimal = statement2.executeQuery();

            ArrayList<String> plus = new ArrayList<>();
            for (int i = 0; i <= firstAnimal.getMetaData().getColumnCount(); i++) {
                for (int j = 0; j <= secondAnimal.getMetaData().getColumnCount(); j++) {
                    if ((firstAnimal.getString(i)).toString().equals((secondAnimal.getString(j)).toString())) {
                        plus.add((String) firstAnimal.getString(1));
                    }
                }
            }
            System.out.println(plus);


            ArrayList<String> minus = new ArrayList<>();
//            System.out.println("Несовпадающие признаки");
//            for (int i = 0; i < first.length; i++) {
//                if (!first[i].equals(second[i])) {
//                    minus.add(first[i] + " - " + second[i]);
//                    //System.out.println(first[i] + " - " + second[i]);
//                }
//            }

            return "Совпадающие признаки - " + plus + " . Несовпадающие признаки - " + minus;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
