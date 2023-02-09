import animals.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {

        ArrayList<Animals> animals = new ArrayList<>();
        loadAnimals(animals);
        String name;

        System.out.println("1 - Посмотреть всех животных " + "2 - Посмотреть конкретных животных" + " 3 - Посмотреть животных по признакам"
                + " 4 - Позвать животное" + " 5 - Убрать животное" + " 6 - Добавить животное" + " 7 - Изменить имя животному");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        switch (num) {
            case 1: System.out.println(animals);
                break;
            case 2: selectAnimals(animals);
                break;
            case 3: selectSign(animals);
                break;
            case 4: callAnimal(animals);
                break;
            case 5:
                deleteAnimal(animals);
                break;
            case 6: addAnimal(animals);
                break;
            case 7:
                System.out.println("Введите имя");
                name = scanner.nextLine();
                renameAnimal(animals, name);
                break;
        }
    }

    public static void saveAnimals(ArrayList<Animals> animals) throws IOException {
        FileWriter fw = new FileWriter("animals.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (Animals animal : animals) {
            bw.write(animal.toString());
            bw.newLine();
        }
        bw.close();
    }

    public static ArrayList<Animals> loadAnimals(ArrayList<Animals> animals) throws Exception {

        FileReader fr = new FileReader("animals.txt");
        Scanner scanner = new Scanner(fr);
        scanner.useDelimiter(", *");
        ArrayList<String> stringsAnimals = new ArrayList<>();
        while (scanner.hasNext()) {
            stringsAnimals.add(scanner.nextLine());
        }

        String[] newAnimal;
        for (int i = 0; i < stringsAnimals.size(); i++) {
            newAnimal = stringsAnimals.get(i).split(", ");
            switch (newAnimal[0]) {
                case ("Tiger") :animals.add(new Tiger(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                    break;
                case ("Wolf") : animals.add(new Wolf(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                    break;
                case ("Penguin") : animals.add(new Penguin(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                    break;
                case ("Bear") : animals.add(new Bear(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                    break;
                case ("Kangaroo") : animals.add(new Kangaroo(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                    break;
            }
        }

        fr.close();
        return animals;
    }


    public static void selectAnimals(ArrayList<Animals> animals) {
        System.out.println("1 - Посмотреть Тигров " + "2 - Посмотреть Волков " + "3 - Посмотреть Пингвинов " + "4 - Посмотреть Кенгуру " + "5 - Посмотреть Медведей");

        Scanner scanner = new Scanner(System.in);
        int animalNum = scanner.nextInt();

        Class questionMark;

        switch (animalNum) {
            case 1: questionMark = Tiger.class;
                break;
            case 2: questionMark = Wolf.class;
                break;
            case 3: questionMark = Penguin.class;
                break;
            case 4: questionMark = Kangaroo.class;
                break;
            case 5: questionMark = Bear.class;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + animalNum);
        }
        animalsByType(animals, questionMark);
    }

    public static void selectSign(ArrayList<Animals> animals) {
        System.out.println("1 - Количество ног " + "2 - Хищник? " + "3 - Цвет " + "4 - Ареал обитания ");

        Scanner scanner = new Scanner(System.in);
        int sign = scanner.nextInt();

        switch (sign) {
            case 1 -> {
                System.out.println("Введите кол-во ног");
                int numOfLegs = scanner.nextInt();
                System.out.println(animals.stream().filter(p -> p.legsNumber == numOfLegs).toList());
            }
            case 2 -> {
                boolean predator = scanner.nextBoolean();
                System.out.println(animals.stream().filter(p -> p.isPredator == predator).toList());
            }
            case 3 -> {
                System.out.println("Введите цвет");
                String color = scanner.nextLine();
                System.out.println(animals.stream().filter(p -> p.color.equals(color)).toList());
            }
            case 4 -> {
                System.out.println("Введите ареал обитания");
                String area = scanner.nextLine();
                System.out.println(animals.stream().filter(p -> p.area.equals(area)).toList());
            }
        }
    }

    public static void animalsByType(ArrayList<Animals> animals, Class questionMark) {
        for (Animals animal : animals) {
            if (animal.getClass().equals(questionMark)) {
                System.out.println(animal);
            }
        }
    }

    public static void callAnimal(ArrayList<Animals> animals) {
        System.out.println("Введите имя");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println(animals.stream().filter(p -> p.name.equals(name)).toList());
    }

    public static void deleteAnimal(ArrayList<Animals> animals) throws IOException {
        System.out.println("Введите имя");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        animals.removeIf(p -> p.name.equals(name));
        saveAnimals(animals);
    }

    public static void addAnimal(ArrayList<Animals> animals) {

    }

    public static void renameAnimal(ArrayList<Animals> animals, String name) {

    }
}