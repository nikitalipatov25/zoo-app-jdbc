import animals.*;
import service.ZooDBService;

import java.util.*;

import static service.ZooDBService.createTable;
import static service.ZooDBService.truncateTable;


public class Main {
    public static void main(String[] args) throws Exception {

        final String dbUrl = "jdbc:postgresql://localhost/zoo";

        truncateTable(dbUrl);
        createTable(dbUrl);

        ArrayList<Animals> animals = new ArrayList<>();
        animals.add(new Animals("Bob", 4, "wolf", true, "gray", "Eurasia"));
        animals.add(new Animals("Ivan", 4, "tiger", true, "yellow", "Eurasia"));
        animals.add(new Animals("John", 4, "bear", true, "black", "North America"));
        ZooDBService.loadAnimals(dbUrl, animals);

        System.out.println(" 1 - Посмотреть всех животных " + "\n"
                + " 2 - Посмотреть конкретных животных" + "\n"
                + " 3 - Посмотреть животных по признакам" + "\n"
                + " 4 - Позвать животное" + " 5 - Убрать животное" + "\n"
                + " 6 - Добавить животное" + "\n" + " 7 - Изменить имя животному" + "\n"
                + " 8 - Сравнить двух животных" + "\n"
                + " 0 - Покинуть зоопарк");
        Scanner scanner = new Scanner(System.in);

        boolean isWorking = true;

        while (isWorking) {

            int num = scanner.nextInt();

            switch (num) {
                case 1 -> ZooDBService.selectAnimals(dbUrl);
                case 2 -> ZooDBService.animalsByType(dbUrl);
                case 3 -> ZooDBService.selectSign(dbUrl);
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
}