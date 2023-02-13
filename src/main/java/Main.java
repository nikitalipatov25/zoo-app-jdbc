import zoo_animals.*;
import zoo_service.ZooService;

import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {

        ArrayList<Animals> animals = new ArrayList<>();
        ZooService.loadAnimals(animals);

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
                case 1 -> System.out.println(animals);
                case 2 -> ZooService.selectAnimals(animals);
                case 3 -> ZooService.selectSign(animals);
                case 4 -> ZooService.callAnimal(animals);
                case 5 -> ZooService.deleteAnimal(animals);
                case 6 -> ZooService.addAnimal(animals);
                case 7 -> ZooService.renameAnimal(animals);
                case 8 -> ZooService.equalAnimals(animals);
                case 0 -> isWorking = false;
                default -> throw new IllegalStateException("Вы выбрали неверное действие: " );
            }
        }
    }
}